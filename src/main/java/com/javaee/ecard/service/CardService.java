package com.javaee.ecard.service;

import com.javaee.ecard.mapper.CardsMapper;
import com.javaee.ecard.model.Card;
import com.javaee.ecard.model.User;
import com.javaee.ecard.security.SecurityUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 名片业务类，向控制层提供服务
 */
@Service
public class CardService {
    @Autowired
    private CardsMapper cardsMapper;


    public List<Card> getAll() {
       return  cardsMapper.selectAll();
    }

    public List<Card> searchByKeyword(String keyword, SecurityUser currentUser) {
        return cardsMapper.selectCardsBykeyword(keyword, currentUser.getId());
    }

    public void deleteById(long id) {
        cardsMapper.deleteById(id);
    }

    public void update(Card card) {
        cardsMapper.update(card);
    }

    public void create(Card createdCard, SecurityUser currentUser) {
        createdCard.setUser(getUserEntity(currentUser));
        cardsMapper.create(createdCard);
    }

    private User getUserEntity(SecurityUser currentUser){
        User user = new User();
        user.setId(currentUser.getId());
        return user;
    }

    public List<Card> getListByUser(SecurityUser currentUser) {
        return cardsMapper.selectCardsBykeyword(null, currentUser.getId());
    }
}
