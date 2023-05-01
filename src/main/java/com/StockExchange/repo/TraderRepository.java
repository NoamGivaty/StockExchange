package com.StockExchange.repo;

import com.StockExchange.model.Trader;
import org.springframework.data.repository.CrudRepository;

public interface TraderRepository extends CrudRepository<Trader, Long> {
}
