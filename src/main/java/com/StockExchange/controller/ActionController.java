package com.StockExchange.controller;

import com.StockExchange.Services.ActionService;
import com.StockExchange.model.Action;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/actions")
public class ActionController {

    @Autowired
    ActionService actionService;
    @GetMapping("/getAllActions")
    public Iterable<Action> getAllActions() {
        return actionService.getAllActions();
    }
    @GetMapping("/action/{id}")
    public Optional<Action> getActionById(@PathVariable Long id) {
        return actionService.getActionById(id);
    }
    @PostMapping("/saveAction")
    public List<Action> saveAction(@RequestBody Action action) {
        actionService.saveAction(action);
         return actionService.checkActions(action);
    }
    @PostMapping("/deleteAction")
    public void deleteAction(@RequestParam Long id) {actionService.deleteAction(id);
    }
}
