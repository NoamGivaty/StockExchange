package com.StockExchange.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "action")
public class Action implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long TraderId;
    private Long ShareId;
    private boolean isBuyAction; // True - Buy action, False - Sell action
    private float price;
    private int amount;

    public Action() {}

    public Action(Long traderId, Long shareId, boolean isBuyAction, float price, int amount) {
        TraderId = traderId;
        ShareId = shareId;
        this.isBuyAction = isBuyAction;
        this.price = price;
        this.amount = amount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public Long getTraderId() {
        return TraderId;
    }
    public void setTraderId(Long traderId) {
        TraderId = traderId;
    }
    public Long getShareId() {
        return ShareId;
    }
    public void setShareId(Long shareId) {
        ShareId = shareId;
    }
    public boolean isBuyAction() {
        return isBuyAction;
    }
    public void setBuyAction(boolean buyAction) {
        isBuyAction = buyAction;
    }
    public float getPrice() {
        return price;
    }
    public void setPrice(float price) {
        this.price = price;
    }
    public int getAmount() {
        return amount;
    }
    public void setAmount(int amount) {
        this.amount = amount;
    }
}
