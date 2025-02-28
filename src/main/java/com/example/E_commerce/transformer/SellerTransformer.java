package com.example.E_commerce.transformer;

import com.example.E_commerce.DTO.RequestDto.SellerRequestDto;
import com.example.E_commerce.DTO.ResponseDto.SellerResponseDto;
import com.example.E_commerce.Model.Seller;
import lombok.experimental.UtilityClass;

@UtilityClass // used to throw Java Compiler error when object is created
public class SellerTransformer {
         //convert Requestdto to Seller(entity)
        public static Seller SellerRequestDtoToSeller(SellerRequestDto sellerRequestDto){

            return Seller.builder()
                    .name(sellerRequestDto.getName())
                    .age(sellerRequestDto.getAge())
                    .emailId(sellerRequestDto.getEmailId())
                    .mobNo(sellerRequestDto.getMobNo())
                    .build();
        }
        // convert Seller to  SellerRequestdto
        public static SellerResponseDto SellerToSellerResponseDto(Seller seller){

            return SellerResponseDto.builder()
                    .name(seller.getName())
                    .age(seller.getAge())
                    .build();
        }
}
