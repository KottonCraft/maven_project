package com.javaee.ecard.web;

import com.javaee.ecard.model.Card;
import com.javaee.ecard.security.SecurityUser;
import com.javaee.ecard.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class HomeController {
    @Autowired
    private CardService cardService;

    //http://localhost:8889/ecard/home
    @RequestMapping("/home")
    public String index(Model model,
                        @AuthenticationPrincipal SecurityUser currentUser){
        List<Card> cards = cardService.getListByUser(currentUser);

        model.addAttribute("cards", cards);

        return "index";//等于templates/index.html
    }
}
