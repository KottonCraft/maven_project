package com.javaee.ecard.web;

import com.javaee.ecard.model.Card;
import com.javaee.ecard.model.Result;
import com.javaee.ecard.security.SecurityUser;
import com.javaee.ecard.service.CardService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/cards")
@Controller
public class CardController {
    @Autowired
    private CardService cardService;

    @RequestMapping("/search")
    public String search(@RequestParam(value = "keyword", required = false) String keyword,
                         @AuthenticationPrincipal SecurityUser currentUser,
                         Model model){
        List<Card> cards = cardService.searchByKeyword(keyword, currentUser);
        model.addAttribute("cards", cards);
        model.addAttribute("keyword", keyword);
        return "index";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") long id){
        cardService.deleteById(id);
        return "redirect:/home";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable("id") long id, @Valid @RequestBody Card card, BindingResult result,
                         @AuthenticationPrincipal SecurityUser currentUser){
        if (result.hasErrors()) {
            return "index";
        }

        card.setId(id); // 确保ID一致性
        cardService.update(card);
        return "redirect:/home";
    }

    @PostMapping("/create")
    @ResponseBody
    public ResponseEntity<?> create(
            @Valid @RequestBody Card createdCard,
            BindingResult bindingResult,
            @AuthenticationPrincipal SecurityUser currentUser
    ) {
        // 验证逻辑保持不变
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            bindingResult.getFieldErrors().forEach(error ->
                    errors.put(error.getField(), error.getDefaultMessage()));
            return ResponseEntity.badRequest()
                    .body(Result.failed(44001, errors.toString()));
        }
        try {
            cardService.create(createdCard, currentUser);
            return ResponseEntity.ok(
                    Result.success(createdCard.getId()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Result.failed(-1));
        }
    }


}
