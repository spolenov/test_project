package com.century.test_project_spolenov.service.request;

import com.century.test_project_spolenov.util.MockitoExtension;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
@ContextConfiguration(locations = {"classpath:/spring/test-project-service.xml"})
@TestMethodOrder(MethodOrderer.Random.class)
class RequestValidationTest {
    @Test
    void verifyDataEmptinessOnBaseRequestTest(){
        Request request = new BaseRequest() {
            @Override
            public List getData() {
                return null;
            }
        };

        assertThrows(IllegalArgumentException.class, request::verifyDataNotEmpty);

        request = new BaseRequest() {
            @Override
            public List getData() {
                return new ArrayList();
            }
        };
        assertThrows(IllegalArgumentException.class, request::verifyDataNotEmpty);

        request = new BaseRequest() {
            @Override
            public List getData() {
                return new ArrayList(){{add(new Object());}};
            }
        };
        assertDoesNotThrow(request::verifyDataNotEmpty);
    }
}
