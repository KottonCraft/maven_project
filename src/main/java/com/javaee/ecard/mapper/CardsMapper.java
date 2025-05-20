package com.javaee.ecard.mapper;

import com.javaee.ecard.model.Card;
import org.apache.ibatis.annotations.*;

import java.util.List;

//映射名片表操作，向业务层提供服务
@Mapper
public interface CardsMapper {
    @Select("select * from cards ORDER BY is_active DESC, update_time DESC")
    @Results(
            id = "selectResult",
            //通过UsersMapper.selectByUserId查询出名片关联的用户记录
            value = {
                    @Result(property = "user", column = "user_id",
                            one = @One(select = "com.javaee.ecard.mapper.UsersMapper.selectByUserId"))
            }

    )
    public List<Card> selectAll();

    @Select("select * from cards where user_id=#{userId}")
    @ResultMap("selectResult")
    public List<Card> selectCardsByUserId(long userId);


    public List<Card> selectCardsBykeyword(String keyword, Long userId);

    @Select("delete from cards where id=#{id}")
    public void deleteById(long id);

    public void update(Card card);

    public void create(Card card);
}
