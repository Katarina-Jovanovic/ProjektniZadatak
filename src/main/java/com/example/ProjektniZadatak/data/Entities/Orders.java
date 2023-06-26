package com.example.ProjektniZadatak.data.Entities;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Objects;

@Entity
public class Orders {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private long id;
    @Basic
    @Column(name = "user_id", nullable = true)
    private Long userId;
    @Basic
    @Column(name = "product_id", nullable = true)
    private Long productId;
    @Basic
    @Column(name = "quantity", nullable = true)
    private Integer quantity;
    @Basic
    @Column(name = "total_price", nullable = true, precision = 2)
    private BigDecimal totalPrice;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Orders orders = (Orders) o;
        return id == orders.id && Objects.equals(userId, orders.userId) && Objects.equals(productId, orders.productId) && Objects.equals(quantity, orders.quantity) && Objects.equals(totalPrice, orders.totalPrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, productId, quantity, totalPrice);
    }
}
