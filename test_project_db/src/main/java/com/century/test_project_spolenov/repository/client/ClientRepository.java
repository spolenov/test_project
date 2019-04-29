package com.century.test_project_spolenov.repository.client;

import com.century.test_project_spolenov.model.client.Goods;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Goods, Integer> {
}
