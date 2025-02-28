package com.example.E_commerce.DTO.ResponseDto;
import com.example.E_commerce.Enums.ProductStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level= AccessLevel.PRIVATE)
@Builder
public class ProductResponseDto {

        String productName;

        String sellerName;

        int quantity;

        ProductStatus productStatus;
    }

