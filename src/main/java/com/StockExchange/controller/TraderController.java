package com.StockExchange.controller;

import com.StockExchange.model.Trader;
import com.StockExchange.Services.TraderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/traders")
public class TraderController {

    @Autowired
    TraderService traderService;

    @GetMapping("/getAllTraders")
    public Iterable<Trader> getAllTraders() {
        return traderService.getAllTraders();
    }
    @GetMapping("/getAllTradersNames")
    public List<String> getAllTradersNames() {
        return traderService.getAllTradersNames();
    }
    @GetMapping("/trader/{id}")
    public Optional<Trader> getTraderById(@PathVariable Long id) {
        return traderService.getTraderById(id);
    }
    @PostMapping("/traders")
    public void saveTrader(@RequestBody Trader trader) {
        traderService.saveTrader(trader);
    }

}
