package com.century.test_project_spolenov.model.order;

import lombok.Data;

@Data
public class Order {
    private int id;

    public Order(int id){
        this.id = id;
    }
}
