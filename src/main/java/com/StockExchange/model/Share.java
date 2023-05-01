package com.StockExchange.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "share")
public class Share implements Serializable {
    @Id
    private Long id;
    private String name;
    private float currentPrice;
    private int amount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public float getCurrentPrice() {
        return currentPrice;
    }
    public void setCurrentPrice(float currentPrice) {
        this.currentPrice = currentPrice;
    }
    public int getAmount() {
        return amount;
    }
    public void setAmount(int amount) {
        this.amount = amount;
    }
}
