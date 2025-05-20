package com.javaee.ecard.model;

import lombok.Data;

@Data
public class CountryLanguage {
    private String countryCode;
    private String language;

    private String isOfficial;

    private Float percentage;



    @Override
    public String toString() {
        return "CountryLanguage{" +
                "countryCode='" + countryCode + '\'' +
                ", language='" + language + '\'' +
                '}';
    }
}
