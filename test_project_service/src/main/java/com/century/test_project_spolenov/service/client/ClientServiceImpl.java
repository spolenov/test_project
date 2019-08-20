package com.century.test_project_spolenov.service.client;

import com.century.test_project_spolenov.model.client.Client;
import com.century.test_project_spolenov.repository.client.ClientRepository;
import com.century.test_project_spolenov.service.common.CommonService;
import com.century.test_project_spolenov.service.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class ClientServiceImpl implements ClientService {
    private ClientRepository clientRepository;
    private CommonService<Client> commonService;

    @Autowired
    public ClientServiceImpl(ClientRepository clientRepository,
                             CommonService<Client> commonService){
        this.clientRepository = clientRepository;
        this.commonService = commonService;
    }

    @PostConstruct
    private void init(){
        commonService.setRepository(clientRepository);
    }

    @Override
    public Response getAll() {
        return commonService.getAll();
    }
}
