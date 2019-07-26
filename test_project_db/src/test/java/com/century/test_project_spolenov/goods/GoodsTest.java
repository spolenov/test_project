package com.century.test_project_spolenov.goods;

import com.century.test_project_spolenov.model.goods.Goods;
import com.century.test_project_spolenov.repository.goods.GoodsRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath:/spring-db-test.xml"})
@TestMethodOrder(MethodOrderer.Random.class)
class GoodsTest {

    @Autowired
    private GoodsRepository goodsRepository;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    void getAllGoodsTest(){
        List<Goods> totalGoods = goodsRepository.findAll();
        assertNotNull(totalGoods);

        assertFalse(totalGoods.isEmpty());

        totalGoods.forEach(r -> log.info(r.toString()));
    }

    @Test
    void saveExistingGoodsTest(){
        List<Goods> totalGoods = goodsRepository.findAll();
        assertFalse(totalGoods.isEmpty());

        int sizeBefore = totalGoods.size();

        for(Goods o: totalGoods){
            assertDoesNotThrow(() -> goodsRepository.save(o));
        }
        assertEquals(goodsRepository.findAll().size(), sizeBefore);
    }

    @Test
    void saveNewGoodsTest(){
        int sizeBefore = goodsRepository.findAll().size();

        Goods newGoods = new Goods();
        newGoods.setName("test_new_goods");
        newGoods.setPrice(BigDecimal.valueOf(0.15));

        assertDoesNotThrow(() -> goodsRepository.save(newGoods));
        assertEquals(goodsRepository.findAll().size(), sizeBefore + 1);
    }

    @Test
    void deleteUnusedGoodsByWholeEntityTest(){
        int sizeBefore = goodsRepository.findAll().size();
        Goods goods = goodsRepository.findById(getUnusedGoodsId()).orElse(null);
        assertNotNull(goods);

        assertDoesNotThrow(() -> goodsRepository.delete(goods));
        assertFalse(goodsRepository.findById(goods.getId()).isPresent());
        assertEquals(goodsRepository.findAll().size(), sizeBefore - 1);
    }

    @Test
    void deleteUsedGoodsByWholeEntityTest(){
        int sizeBefore = goodsRepository.findAll().size();
        Goods goods = goodsRepository.findById(getUsedGoodsId()).orElse(null);
        assertNotNull(goods);

        assertThrows(DataIntegrityViolationException.class, () -> goodsRepository.delete(goods));
        assertTrue(goodsRepository.findById(goods.getId()).isPresent());
        assertEquals(goodsRepository.findAll().size(), sizeBefore);
    }

    @Test
    void deleteUnusedGoodsByIdTest(){
        Executable ex = () ->  goodsRepository.deleteById(getUnusedGoodsId());
        assertDoesNotThrow(ex);
    }

    @Test
    void deleteUsedGoodsByIdTest(){
        Executable ex = () ->  goodsRepository.deleteById(getUsedGoodsId());
        assertThrows(DataIntegrityViolationException.class, ex);
    }

    @Test
    void testToString(){
        assertFalse(new Goods().toString().isEmpty());
        assertFalse(goodsRepository.findAll().get(0).toString().isEmpty());
    }

    private int getUnusedGoodsId(){
        String sql = "SELECT DISTINCT g.id " +
                "FROM test.goods as g " +
                "LEFT JOIN test.order_line line " +
                "ON g.id = line.goods_id " +
                "WHERE line.goods_id IS NULL";
        List<Integer> ids = jdbcTemplate.queryForList(sql, Integer.class);

        assertFalse(ids.isEmpty(),
                "Must have at least one unused goods " +
                        "to execute this test. " +
                        "Check your dataset.");
        return ids.get(0);
    }

    private int getUsedGoodsId(){
        String sql = "SELECT DISTINCT g.id " +
                "FROM test.goods as g " +
                "LEFT JOIN test.order_line line " +
                "ON g.id = line.goods_id " +
                "WHERE line.goods_id IS NOT NULL";
        List<Integer> ids = jdbcTemplate.queryForList(sql, Integer.class);

        assertFalse(ids.isEmpty(),
                "Must have at least one used goods " +
                        "to execute this test. " +
                        "Check your dataset.");
        return ids.get(0);
    }
}
