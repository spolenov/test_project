package com.century.test_project_spolenov.client;

import com.century.test_project_spolenov.model.client.Client;
import com.century.test_project_spolenov.repository.client.ClientRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath:/spring-db-test.xml"})
class ClientTest {
    @Autowired
    private ClientRepository clientRepository;

    private Client client = new Client();

    @Test
    void testSaveGoods(){
        client.setId(1000);
        assertNotNull(clientRepository.save(client));
    }
}
