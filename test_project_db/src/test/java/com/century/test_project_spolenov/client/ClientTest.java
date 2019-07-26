package com.century.test_project_spolenov.client;

import com.century.test_project_spolenov.model.client.Client;
import com.century.test_project_spolenov.model.order.Order;
import com.century.test_project_spolenov.repository.client.ClientRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Date;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath:/spring-db-test.xml"})
@TestMethodOrder(MethodOrderer.Random.class)
class ClientTest {
    @Autowired
    private ClientRepository clientRepository;

    @Test
    void getAllClientsTest(){
        List<Client> totalClients = clientRepository.findAll();
        assertNotNull(totalClients);

        assertFalse(totalClients.isEmpty());

        totalClients.forEach(r -> log.info(r.toString()));
    }

    @Test
    void getClientByIdTest(){
        final int TEST_ID = 1;
        Client existingClient = clientRepository.findAll().get(0);
        existingClient.setId(TEST_ID);

        clientRepository.save(existingClient);

        Client Client = clientRepository.findById(TEST_ID).orElse(null);
        assertNotNull(Client);

        assertEquals(Client.getId(), TEST_ID);
    }

    @Test
    void deleteClientByWholeEntityTest(){
        List<Client> totalClients = clientRepository.findAll();
        int sizeBefore = totalClients.size();

        Client client = totalClients.stream()
                .findFirst()
                .orElse(null);
        assertNotNull(client);

        assertDoesNotThrow(() -> clientRepository.delete(client));
        assertFalse(clientRepository.findById(client.getId()).isPresent());
        assertEquals(clientRepository.findAll().size(), sizeBefore - 1);
    }

    @Test
    void saveExistingClientTest(){
        List<Client> totalClients = clientRepository.findAll();
        assertFalse(totalClients.isEmpty());

        int sizeBefore = totalClients.size();

        for(Client o: totalClients){
            assertDoesNotThrow(() -> clientRepository.save(o));
        }
        assertEquals(clientRepository.findAll().size(), sizeBefore);
    }

    @Test
    void saveSameClientWithNewOrderTest(){
        List<Client> totalClients = clientRepository.findAll();
        int sizeBefore = totalClients.size();

        Client existingClient = new Client(totalClients.get(0).getId());
        existingClient.setName(totalClients.get(0).getName());

        Order newOrder = new Order();
        newOrder.setOrderDate(new Date());
        newOrder.setClient(existingClient);
        newOrder.setAddress("new_test_address");

        if(existingClient.getOrders() == null){
            existingClient.setOrders(new HashSet<>());
        }

        existingClient.getOrders().add(newOrder);
        assertDoesNotThrow(() -> clientRepository.save(existingClient));
        assertEquals(clientRepository.findAll().size(), sizeBefore);
    }


    @Test
    void deleteClientByIdTest(){
        Client client = clientRepository.findAll()
                .stream()
                .findAny()
                .orElse(null);
        assertNotNull(client);

        int id =  client.getId();
        Executable ex = () ->  clientRepository.deleteById(id);
        assertDoesNotThrow(ex);
    }

    @Test
    void testToString(){
        assertFalse(new Client().toString().isEmpty());
        assertFalse(clientRepository.findAll().get(0).toString().isEmpty());
    }
}
