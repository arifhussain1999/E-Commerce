package com.example.E_commerce.Service;

import com.example.E_commerce.DTO.RequestDto.ProductRequestDto;
import com.example.E_commerce.DTO.ResponseDto.ProductResponseDto;
import com.example.E_commerce.Enums.ProductCategory;
import com.example.E_commerce.Exception.InvalidSellerException;
import com.example.E_commerce.Model.Item;

import java.util.List;

public interface ProductService {

    public ProductResponseDto addProduct(ProductRequestDto productRequestDto) throws InvalidSellerException;
    public List<ProductResponseDto> getAllProductsByCategory(ProductCategory category);
    public List<ProductResponseDto> getAllProductsByPriceAndCategory(int price, String category);

    public void decreaseProductQuantity(Item item) throws Exception;


    }
