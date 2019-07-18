package com.century.test_project_spolenov.model.client;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "client", schema = "test")
public class Client {
    @Id
    @Column(name = "id", nullable = false)
    private int id;
}
