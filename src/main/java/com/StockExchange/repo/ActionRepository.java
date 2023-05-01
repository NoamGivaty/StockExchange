package com.StockExchange.repo;

import com.StockExchange.model.Action;
import org.springframework.data.repository.CrudRepository;

public interface ActionRepository extends CrudRepository<Action, Long> {
}
