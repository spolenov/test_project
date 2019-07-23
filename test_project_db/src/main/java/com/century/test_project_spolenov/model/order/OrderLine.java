package com.century.test_project_spolenov.model.order;

import com.century.test_project_spolenov.model.goods.Goods;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

import static javax.persistence.GenerationType.SEQUENCE;

@Getter @Setter
@NoArgsConstructor
@Entity
@Table(name = "order_line", schema = "test")
public class OrderLine implements Serializable {
    @Id
    @Column(name = "id", nullable = false, updatable = false)
    @GeneratedValue(strategy = SEQUENCE, generator = "ORDER_LINE_ID_SEQ")
    @SequenceGenerator(name = "ORDER_LINE_ID_SEQ", sequenceName = "test.order_line_id_seq", allocationSize = 1)
    private int id;

    @Setter
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "goods_id", referencedColumnName = "id", nullable = false)
    private Goods goods;

    @Column(name = "item_count", nullable = false)
    private int count;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderLine orderLine = (OrderLine) o;
        return this.goods != null &&
                id == orderLine.id &&
                count == orderLine.count &&
                goods.getId() == orderLine.getGoods().getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, count, goods== null? 0: goods.getId());
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("OrderLine{");
        sb.append("id=").append(id);
        sb.append(", count=").append(count);
        sb.append(", goods=").append(goods == null? "<null>" : goods.getId());
        sb.append('}');
        return sb.toString();
    }
}
