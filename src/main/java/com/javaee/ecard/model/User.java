package com.javaee.ecard.model;

import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.util.List;

//映射用户表
@Data
public class User {
  private long id;
  private String username;
  private String password;
  private String email;
  @Pattern(regexp = "^(1[3-9]\\d{9})?$", message = "手机号格式不正确")
  private String phone;
  private List<Card> cards;
}
