package com.StockExchange.model;

import com.StockExchange.model.Share;
import com.StockExchange.model.Trader;

import java.util.List;

public class JsonData {
    private List<Share> shares;
    private List<Trader> traders;

    public List<Share> getShares() {
        return shares;
    }
    public void setShares(List<Share> shares) {
        this.shares = shares;
    }
    public List<Trader> getTraders() {
        return traders;
    }
    public void setTraders(List<Trader> traders) {
        this.traders = traders;
    }
}
