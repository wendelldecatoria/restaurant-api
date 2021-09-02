package com.wendecator.restaurant.models;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date created_at = new Date();

    @OneToMany(mappedBy = "order", fetch = FetchType.EAGER)
    private List<Item> items;

    public Order(){}

    public Order(Long id) {
        this.id = id;
    }

    public Order(Long id, Date created_at) {
        this.id = id;
        this.created_at = created_at;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreated_at() {
        return created_at;
    }

    @PreUpdate
    public void setCreated_at() {
        this.created_at = new Date();
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}
