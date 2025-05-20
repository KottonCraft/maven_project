package com.javaee.ecard.model;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * 映射名片表
 */
@Data
public class Card {
   private long id;
   @NotBlank(message = "显示姓名不能为空")
   private String cardName;
   @NotBlank(message = "名片标题不能为空")
   private String cardTitle;
   @NotBlank(message = "职位不能为空")
   private String position;
   @NotBlank(message = "公司不能为空")
   private String company;
   private String contactInfo;
   private int isActive;
   //名片与用户多对一
   private User user;
}
