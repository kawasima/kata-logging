package net.unit8.kata.shopping.persistence;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "carts")
public class CartJpaEntity implements Serializable {
    @Id
    @GeneratedValue
    private Long id;

    private Long userId;

    @OneToMany(mappedBy = "cart", cascade = { CascadeType.ALL }, orphanRemoval = true)
    private List<CartItemJpaEntity> items = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<CartItemJpaEntity> getItems() {
        return items;
    }

    public void setItems(List<CartItemJpaEntity> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "CartJpaEntity{" +
                "id=" + id +
                ", userId=" + userId +
                ", items=" + items +
                '}';
    }
}
