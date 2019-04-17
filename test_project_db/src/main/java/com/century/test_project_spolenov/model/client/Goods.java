package com.century.test_project_spolenov.model.client;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Goods {
    private int id;
    private BigDecimal price;
    private String name;
}
