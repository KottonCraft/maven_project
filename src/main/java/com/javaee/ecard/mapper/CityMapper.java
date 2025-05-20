package com.javaee.ecard.mapper;

import com.javaee.ecard.model.City;
import org.apache.ibatis.annotations.*;

@Mapper
public interface CityMapper {
    @Insert("INSERT city (NAME, countrycode, district, population) VALUES(#{name},#{countryCode},#{district},#{population})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public void insert(City city);

    @Select("SELECT * FROM city WHERE id = #{id}")
    public City selectById(long id);

    @Update("update city set name=#{name},countryCode=#{countryCode},district=#{district},population=#{population} where id=#{id}")
    public void update(City city);

    @Delete("delete from city where id=#{id}")
    public void deleteById(long id);
}
