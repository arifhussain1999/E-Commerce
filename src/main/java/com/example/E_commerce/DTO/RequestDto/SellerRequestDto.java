package com.example.E_commerce.DTO.RequestDto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level= AccessLevel.PRIVATE)
public class SellerRequestDto {

    String name;
    String emailId;
    Integer age;
    String mobNo;
}
