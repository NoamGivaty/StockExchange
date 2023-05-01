package com.StockExchange.controller;


import com.StockExchange.model.Share;
import com.StockExchange.Services.ShareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/shares")
public class ShareController {

    @Autowired
    ShareService shareService;
    @GetMapping("/getAllShares")
    public Iterable<Share> getAllShares() {
        return shareService.getAllShares();
    }
    @GetMapping("/getAllSharesNames")
    public List<String> getAllSharesNames() {
        return shareService.getAllSharesNames();
    }
    @GetMapping("/share/{id}")
    public Optional<Share> getShareById(@PathVariable Long id) {
        return shareService.getShareById(id);
    }
    @PostMapping("/saveShare")
    public void saveShare(@RequestBody Share share) {
        shareService.saveShare(share);
    }
    @PostMapping("/updateShare")
    public void updateShare(@RequestParam long id, @RequestParam float price, @RequestParam int amount) {
        shareService.updateShare(id,price,amount);
    }


}
