package com.javaee.ecard.controller;

import com.javaee.ecard.model.Result;
import com.javaee.ecard.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/card")
@RestController("cardAPIController")
public class CardController {
    @Autowired
    private CardService cardService;

    @RequestMapping("/list")
    public Result listAll() {
        return Result.success(cardService.getAll());
    }
}
