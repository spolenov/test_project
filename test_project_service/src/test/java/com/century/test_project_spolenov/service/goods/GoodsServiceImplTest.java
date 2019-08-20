package com.century.test_project_spolenov.service.goods;

import com.century.test_project_spolenov.model.goods.Goods;
import com.century.test_project_spolenov.service.common.CommonService;
import com.century.test_project_spolenov.service.request.BaseRequest;
import com.century.test_project_spolenov.service.response.ActionResponse;
import com.century.test_project_spolenov.util.MockitoExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.ContextConfiguration;

import java.util.ArrayList;

import static com.century.test_project_spolenov.service.response.ActionResponse.okResponse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@ContextConfiguration(locations = {"classpath:/spring/test-project-service.xml"})
@TestMethodOrder(MethodOrderer.Random.class)
class GoodsServiceImplTest {
    @InjectMocks
    private GoodsServiceImpl goodsService;
    @Mock
    private CommonService<Goods> commonService;

    private Goods goods = new Goods();
    private BaseRequest<Goods> request = new BaseRequest<>(new ArrayList<Goods>(){{add(goods);}});
    private ActionResponse response = okResponse(new ArrayList<Goods>(){{add(goods);}});

    @BeforeEach
    private void init(){
        when(commonService.getAll()).thenReturn(response);
        when(commonService.getById(eq(goods.getId()))).thenReturn(response);
        when(commonService.addNew(eq(request))).thenReturn(okResponse());
        when(commonService.deleteOne(eq(goods.getId()))).thenReturn(okResponse());
        when(commonService.updateOne(eq(request))).thenReturn(okResponse());
    }

    @Test
    void getAllTest(){
        assertEquals(goodsService.getAll(), response);
    }

    @Test
    void addNewTest(){
        assertEquals(goodsService.addNew(request), okResponse());
    }

    @Test
    void deleteOneTest(){
        assertEquals(goodsService.deleteOne(goods.getId()), okResponse());
    }

    @Test
    void updateOneTest(){
        assertEquals(goodsService.updateOne(request), okResponse());
    }

    @Test
    void getByIdTest(){
        assertEquals(goodsService.getById(goods.getId()), response);
    }
}
