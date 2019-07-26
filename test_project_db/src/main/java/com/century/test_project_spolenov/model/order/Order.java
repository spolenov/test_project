package com.century.test_project_spolenov.model.order;

import com.century.test_project_spolenov.model.Element;
import com.century.test_project_spolenov.model.client.Client;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import java.util.Set;

import static java.util.Objects.isNull;
import static javax.persistence.GenerationType.SEQUENCE;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "order_head", schema = "test")
public class Order extends Element implements Serializable {
    @Id
    @Column(name = "id", nullable = false, updatable = false)
    @GeneratedValue(strategy = SEQUENCE, generator = "ORDER_HEAD_ID_SEQ")
    @SequenceGenerator(name = "ORDER_HEAD_ID_SEQ", sequenceName = "test.order_head_id_seq", allocationSize = 1)
    private int id;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name="client_id", nullable = false)
    private Client client;

    @Column(name = "date", nullable = false)
    private Date orderDate;

    @Column(name = "address")
    private String address;

    @JsonManagedReference
    @OneToMany(mappedBy = "order",
            targetEntity = OrderLine.class, orphanRemoval = true,
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL)
    private Set<OrderLine> lines;

    public Order(int id){
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return id == order.id &&
                orderDate.equals(order.orderDate) &&
                address.equals(order.address) &&
                Objects.equals(lines, order.lines);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, orderDate, address, lines);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Order{");
        sb.append("id=").append(id);
        sb.append(", client=").append(isNull(client)? "<null>": client.getName());
        sb.append(", orderDate=").append(orderDate);
        sb.append(", address='").append(address).append('\'');
        sb.append(", lines=").append(lines);
        sb.append('}');
        return sb.toString();
    }
}
