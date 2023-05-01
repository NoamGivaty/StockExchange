package com.StockExchange.Services;

import com.StockExchange.model.Action;
import com.StockExchange.model.Share;
import com.StockExchange.repo.ActionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ActionService {
    @Autowired
    ActionRepository repository;

    public Iterable<Action> getAllActions() {
        return repository.findAll();
    }
    public Optional<Action> getActionById(Long id) {return repository.findById(id);}
    public Action saveAction(Action action) {return repository.save(action);}
    public void deleteAction(Long id) {
        repository.deleteById(id);
    }
    public List<Action> checkActions(Action newAction){
        List<Action> actions = (List<Action>) getAllActions();
        List<Action> deals = new ArrayList<>();

        for(Action action : actions)
        {
            if(newAction.getShareId()==action.getShareId() && newAction.getPrice()== action.getPrice()
                    && (newAction.isBuyAction() != action.isBuyAction()))
            {
                if(newAction.getAmount() > action.getAmount())
                {
                    newAction.setAmount(newAction.getAmount()-action.getAmount());
                    deals.add(action);
                    deleteAction(action.getId());
                }
                if(newAction.getAmount() < action.getAmount())
                {
                    action.setAmount(action.getAmount()-newAction.getAmount());
                    deals.add(newAction);
                    deleteAction(newAction.getId());
                }
                if(newAction.getAmount() == action.getAmount())
                {
                    deals.add(action);
                    deals.add(newAction);
                    deleteAction(action.getId());
                    deleteAction(newAction.getId());
                }
            }
        }

        return deals;
    }
    public List<Action> getShareActions(Long shareId){
        List<Action> allActions = (List<Action>) getAllActions();
        List<Action> actions = new ArrayList<>();

        for(Action action : allActions){
            if(action.getShareId()==shareId)
                actions.add(action);
        }
        return actions;
    }
}
