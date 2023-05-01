package com.StockExchange.controller;

import com.StockExchange.Services.ActionService;
import com.StockExchange.Services.ClientService;
import com.StockExchange.model.Action;
import com.StockExchange.model.Share;
import com.StockExchange.model.Trader;
import com.StockExchange.Services.ShareService;
import com.StockExchange.Services.TraderService;
import com.StockExchange.repo.ShareRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.List;


@Controller
public class ClientController {

    @Autowired
    TraderService traderService;
    @Autowired
    ShareService shareService;
    @Autowired
    ActionService actionService;


    @PostMapping("/login")
    public String login(@RequestParam("id") String id, HttpSession session, Model model) {
        Trader trader = traderService.getTraderById(Long.parseLong(id)).get();
        String name = trader.getName();
        float money = trader.getMoney();
        List<Share> shares = (List<Share>) shareService.getAllShares();
        session.setAttribute("traderId", id);
        model.addAttribute("id",id);
        model.addAttribute("name", name);
        model.addAttribute("money", money);
        model.addAttribute("shares", shares);
        return "menu";
    }

    @PostMapping("/logout")
    public String logout(HttpServletRequest request) {
        request.getSession().invalidate();
        return "redirect:/login";
    }

    @GetMapping("/shareInfoAndAction")
    public String showShareInfoAndAction(@RequestParam("shareId") Long shareId,@RequestParam("traderId") Long traderId, Model model) {
        // retrieve the share data using the id
        Share share = shareService.getShareById(shareId).get();
        Trader trader = traderService.getTraderById(traderId).get();

        // add the share data to the model
        model.addAttribute("share", share);
        model.addAttribute("name", trader.getName());
        model.addAttribute("money", trader.getMoney());
        model.addAttribute("id", trader.getId());

        model.addAttribute("actions", actionService.getShareActions(shareId));


        // return the view name for the shareInfoAndAction page
        return "shareInfoAndAction";
    }

    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    @GetMapping("/menu")
    public String showMainPage() {
        return "menu";
    }
}
