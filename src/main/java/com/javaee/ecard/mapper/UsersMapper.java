package com.javaee.ecard.mapper;

import com.javaee.ecard.model.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

//映射用户表操作
@Mapper
public interface UsersMapper {

    @Select("select * from users")
    @Results(
            value = {
                    @Result(property = "id", column = "id"),
                    //通过CardsMapper.selectCardsByUserId查询出用户关联的名片记录
                    @Result(property = "cards", column = "id",
                            many = @Many(
                                    select ="com.javaee.ecard.mapper.CardsMapper.selectCardsByUserId"))
            }
    )
    public List<User> selectAll();

    @Select("select * from users where id=#{userId}")
    public User selectByUserId(long userId);

    @Select("select * from users where username=#{username}")
    public User findByUsername(String username);
}
