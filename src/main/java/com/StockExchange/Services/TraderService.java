package com.StockExchange.Services;

import com.StockExchange.model.Share;
import com.StockExchange.model.Trader;
import com.StockExchange.repo.TraderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TraderService {

    @Autowired
    TraderRepository repository;


    public Iterable<Trader> getAllTraders() {
        return repository.findAll();
    }
    public List<String> getAllTradersNames(){
        List<Trader> traders = new ArrayList<>();
        repository.findAll().forEach(traders::add);
        return traders.stream()
                .map(Trader::getName)
                .collect(Collectors.toList());
    }
    public Optional<Trader> getTraderById(Long id) {
        return repository.findById(id);
    }
    public Trader saveTrader(Trader trader) {
        return repository.save(trader);
    }
    public void deleteTrader(long id) {
        repository.deleteById(id);
    }
}
