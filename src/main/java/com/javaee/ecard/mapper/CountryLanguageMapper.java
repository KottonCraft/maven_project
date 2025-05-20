package com.javaee.ecard.mapper;

import com.javaee.ecard.model.CountryLanguage;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CountryLanguageMapper {
    @Select("select countryCode,language from countrylanguage")
    public List<CountryLanguage> selectAllCountryLanguages();

    @Insert("INSERT INTO countrylanguage VALUES(#{countryCode},#{language},#{isOfficial}, #{percentage})")
    public void insertCountryLanguage(CountryLanguage countryLanguage);

    @Update("UPDATE countrylanguage SET LANGUAGE=#{language} WHERE countrycode=#{countryCode} AND LANGUAGE=#{language}")
    public void updateContryLanguage(CountryLanguage countryLanguage);

    @Delete("DELETE FROM countrylanguage WHERE countrycode=#{countryCode} AND LANGUAGE=#{language}")
    public void deleteContryLanguage(String countryCode, String language);
}
