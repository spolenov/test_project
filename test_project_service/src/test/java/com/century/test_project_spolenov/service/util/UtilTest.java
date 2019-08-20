package com.century.test_project_spolenov.service.util;

import com.century.test_project_spolenov.model.goods.Goods;
import com.century.test_project_spolenov.repository.goods.GoodsRepository;
import com.century.test_project_spolenov.util.MockitoExtension;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.test.context.ContextConfiguration;

import static com.century.test_project_spolenov.service.util.ServiceUtils.getEntityClass;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
@ContextConfiguration(locations = {"classpath:/spring/test-project-service.xml"})
@TestMethodOrder(MethodOrderer.Random.class)
class UtilTest {
    @Mock
    GoodsRepository goodsRepository;

    @Test
    void getEntityTypeTest(){
        Class clazz = getEntityClass(goodsRepository.getClass());

        assertNotNull(clazz);
        assertEquals(clazz.getSimpleName(), Goods.class.getSimpleName());
    }
}
