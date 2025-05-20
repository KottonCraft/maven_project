package com.javaee.ecard.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class City {
    private long id;
    private String name;
    private String countryCode;
    private String district;
    private String population;

}
