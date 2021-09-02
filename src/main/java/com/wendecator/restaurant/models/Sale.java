package com.wendecator.restaurant.models;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "sales")
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "order_id")
    private Order order;

    private Float total;
    private Integer discount;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date created_at = new Date();

    public Sale() {

    }

    public Sale(Long id) {
        this.id = id;
    }

    public Sale(Order order, Float total, Integer discount, Date created_at) {
        this.order = order;
        this.total = total;
        this.discount = discount;
        this.created_at = created_at;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Float getTotal() {
        return total;
    }

    public void setTotal(Float total) {
        this.total = total;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date date) {
        this.created_at = new Date();
    }
}
