package com.StockExchange.controller;

import com.StockExchange.Services.AdminService;
import com.StockExchange.Services.ShareService;
import com.StockExchange.Services.TraderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    ShareService shareService;
    @Autowired
    TraderService traderService;
    @Autowired
    AdminService adminService;


    @PostMapping("/loadSharesAndTraders")
    public void loadSharesAndTraders() {
        adminService.loadSharsAndTraders();
    }
    @RequestMapping(value = "/updateStockPrices/", method = RequestMethod.GET)
    public ResponseEntity<?> updateStockPrices() throws IOException {
        return new ResponseEntity<>(adminService.updateStockPrices(), HttpStatus.OK);
    }
    @PostMapping("/deleteShare")
    public void deleteShare(@RequestParam Long id) {shareService.deleteShare(id);
    }
    @PostMapping("/deleteTrader")
    public void deleteTrader(@PathVariable Long id) {
        traderService.deleteTrader(id);
    }
    @GetMapping("/startAutoUpdate")
    public void startAutoUpdate() {adminService.startUpdateTask();}
}
