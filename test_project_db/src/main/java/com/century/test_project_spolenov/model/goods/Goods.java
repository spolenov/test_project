package com.century.test_project_spolenov.model.goods;

import com.century.test_project_spolenov.model.Element;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

import static javax.persistence.GenerationType.SEQUENCE;

@Data
@Entity
@Table(name = "goods", schema = "test")
public class Goods extends Element implements Serializable {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = SEQUENCE, generator = "GOODS_ID_SEQ")
    @SequenceGenerator(name = "GOODS_ID_SEQ", sequenceName = "test.goods_id_seq", allocationSize = 1)
    private int id;

    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @Column(name = "name", nullable = false)
    private String name;
}
