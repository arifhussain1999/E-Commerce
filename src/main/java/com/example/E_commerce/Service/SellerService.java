package com.example.E_commerce.Service;

import com.example.E_commerce.DTO.RequestDto.SellerRequestDto;
import com.example.E_commerce.DTO.ResponseDto.SellerResponseDto;
import com.example.E_commerce.Exception.EmailAlreadyPresentException;
import com.example.E_commerce.Model.Seller;

import java.util.List;

public interface SellerService {

    public SellerResponseDto addSeller(SellerRequestDto sellerRequestDto) throws EmailAlreadyPresentException;
    public Seller getSellerById(int id);
    public List<Seller> getAllSellers();
    public Seller getSellerByEmailId(String emailId);

    public Seller getSellerByAge(int age);

    public void deleteSeller(Integer id);

   // public Seller updateSeller(Integer id, SellerRequestDto sellerRequestDto);

}
