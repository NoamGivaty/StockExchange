package com.StockExchange.Services;

import com.StockExchange.model.Share;
import com.StockExchange.repo.ShareRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ShareService {

    @Autowired
    ShareRepository repository;

    public Iterable<Share> getAllShares() {
        return repository.findAll();
    }
    public List<String> getAllSharesNames(){
        List<Share> shares = new ArrayList<>();
        repository.findAll().forEach(shares::add);
        return shares.stream()
                .map(Share::getName)
                .collect(Collectors.toList());
    }
    public Optional<Share> getShareById(Long id) {return repository.findById(id);}
    public Share saveShare(Share share) {return repository.save(share);}
    public Share updateShare(long id, float price, int amount) {
        Share share = repository.findById(id).orElse(null);
        share.setCurrentPrice(price);
        share.setAmount(amount);
        return repository.save(share);
    }
    public void deleteShare(Long id) {
        repository.deleteById(id);
    }

}
