package com.century.test_project_spolenov.service.client;

import com.century.test_project_spolenov.model.client.Client;
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
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@ContextConfiguration(locations = {"classpath:/spring/test-project-service.xml"})
@TestMethodOrder(MethodOrderer.Random.class)
class ClientServiceImplTest {
    @InjectMocks
    private ClientServiceImpl clientService;
    @Mock
    private CommonService<Client> commonService;

    private Client client = new Client();
    private BaseRequest<Client> request = new BaseRequest<>(new ArrayList<Client>(){{add(client);}});
    private ActionResponse response = okResponse(new ArrayList<Client>(){{add(client);}});

    @BeforeEach
    private void init(){
        when(commonService.getAll()).thenReturn(response);
    }

    @Test
    void getAllTest(){
        assertEquals(clientService.getAll(), response);
    }
}
