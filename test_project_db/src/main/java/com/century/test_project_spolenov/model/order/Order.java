package com.century.test_project_spolenov.model.order;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@Entity
@Table(name = "order", schema = "test")
public class Order {
    @Id
    @Column(name = "id", nullable = false)
    private int id;

    public Order(int id){
        this.id = id;
    }
}
