package com.StockExchange.Services;

import com.StockExchange.model.Action;
import com.StockExchange.repo.ActionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClientService {
    @Autowired
    ActionService actionService;

    public void setBuyStockAction(Long traderId, Long shareId, float price, int amount) {
        actionService.saveAction(new Action(traderId,shareId,true,price,amount));
    }

    public void setSellStockAction(Long traderId, Long shareId, float price, int amount) {
        actionService.saveAction(new Action(traderId,shareId,false,price,amount));
    }

    public void cancelAction(Long id){
        actionService.deleteAction(id);
    }


}