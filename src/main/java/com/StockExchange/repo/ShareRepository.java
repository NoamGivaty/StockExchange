package com.StockExchange.repo;

import com.StockExchange.model.Share;
import org.springframework.data.repository.CrudRepository;

public interface ShareRepository extends CrudRepository<Share, Long> {
}
