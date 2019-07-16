package com.century.test_project_spolenov.model.goods;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "goods", schema = "test")
public class Goods {
    @Id
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @Column(name = "name", nullable = false)
    private String name;
}
