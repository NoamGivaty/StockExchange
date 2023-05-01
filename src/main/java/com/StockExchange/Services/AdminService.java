package com.StockExchange.Services;

import com.StockExchange.model.Share;
import com.StockExchange.model.Trader;
import com.StockExchange.model.JsonData;
import com.StockExchange.util.StockPrice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Random;

@Service
public class AdminService {
    private final static int SECONDS_IN_MINUTE = 60;
    private final static int MILLISECONDS_IN_SECOND = 1000;
    private final static int MINUTES_TO_RUN = 1; // Set to run every 1 minutes
    private final static int DELAY = 0;
    public static final String GREEN = "\033[32m";
    public static final String DEFAULT = "\033[0m";


    @Autowired
    ShareService shareService;
    @Autowired
    TraderService traderService;
    @Autowired
    StockPrice stockPrice;

    private static Timer timer;

    public void loadSharsAndTraders() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            // Load JSON data from file
            File file = new File("BurseJson.json");
            JsonData jsonData = objectMapper.readValue(file, JsonData.class);

            // Save shares
            for (Share share : jsonData.getShares()) {
                shareService.saveShare(share);
            }

            // Save traders
            for (Trader trader : jsonData.getTraders()) {
                traderService.saveTrader(trader);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public Iterable<Share> updateStockPrices() throws IOException {
        Iterable<Share> shares = shareService.getAllShares();
        float currentPrice, newPrice, change;

        for(Share share : shares){
            currentPrice = share.getCurrentPrice();
            if(share.getId()==15 || share.getId()==16 || share.getId()==10 || share.getId()==14)
                change = randomFloat();
            else
                change = stockPrice.getStockChange(share.getName());
            newPrice = currentPrice * ((100 + change)/100);
            shareService.updateShare(share.getId(),newPrice,share.getAmount());
            System.out.println(share.getName() + " last price is: " + currentPrice + " new price is: " + newPrice + " change: " + change);
        }
        System.out.println(GREEN);
        System.out.println("Update finished");
        System.out.println(DEFAULT);
        return shareService.getAllShares();
    }
    public static float randomFloat() {
        float min = -4.00f;
        float max = 4.00f;
        float random = new Random().nextFloat();
        float result = min + (random * (max - min));
        return (float)Math.round(result * 100) / 100;
    }

    public void startUpdateTask() {
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                try {
                    updateStockPrices(); // Call your method here
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }, DELAY, MINUTES_TO_RUN*SECONDS_IN_MINUTE*MILLISECONDS_IN_SECOND);
    }

    public void stopUpdateTask() {
        timer.cancel();
    }
}
