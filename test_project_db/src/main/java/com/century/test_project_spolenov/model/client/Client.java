package com.century.test_project_spolenov.model.client;

import com.century.test_project_spolenov.model.order.Order;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Immutable;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Getter@Setter
@NoArgsConstructor
@Entity
@Immutable
@Table(name = "client", schema = "test")
public class Client {
    @Id
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "client_name", nullable = false, updatable = false)
    private String name;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<Order> orders;

    public Client(int id){
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return id == client.id &&
                name.equals(client.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Client{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
