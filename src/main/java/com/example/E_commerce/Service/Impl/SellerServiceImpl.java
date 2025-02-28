package com.example.E_commerce.Service.Impl;

import com.example.E_commerce.DTO.RequestDto.SellerRequestDto;
import com.example.E_commerce.DTO.ResponseDto.SellerResponseDto;
import com.example.E_commerce.Exception.EmailAlreadyPresentException;
import com.example.E_commerce.Model.Seller;
import com.example.E_commerce.Repository.SellerRepository;
import com.example.E_commerce.Service.SellerService;
import com.example.E_commerce.transformer.SellerTransformer;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
public class SellerServiceImpl implements SellerService {

    @Autowired
    SellerRepository sellerRepository;

    public SellerResponseDto addSeller(SellerRequestDto sellerRequestDto) throws EmailAlreadyPresentException {

//        Seller seller = new Seller();
//        seller.setName(sellerRequestDto.getName());
//        seller.setEmailId(sellerRequestDto.getEmailId());
//        seller.setMobNo(sellerRequestDto.getMobNo());
//        seller.setAge(sellerRequestDto.getAge());

        if (sellerRepository.findByEmailId(sellerRequestDto.getEmailId()) != null)
            throw new EmailAlreadyPresentException("Email Id already registered");
        // prepare entity
        Seller seller = SellerTransformer.SellerRequestDtoToSeller(sellerRequestDto);
        Seller savedSeller = sellerRepository.save(seller);

        // prepare response Dto
        SellerResponseDto sellerResponseDto = SellerTransformer.SellerToSellerResponseDto(savedSeller);
        return sellerResponseDto;

    }

   public Seller getSellerById(int id) {
      return  sellerRepository.findById(id) .orElseThrow(() -> new EntityNotFoundException("Seller not found with id " + id));
  }
    public List<Seller> getAllSellers() {
        return sellerRepository.findAll(); // Fetch all sellers
    }
    public Seller getSellerByEmailId(String emailId) {
        return  sellerRepository.findByEmailId(emailId) .orElseThrow(() -> new EntityNotFoundException("Seller not found with id " + emailId));
    }

    public Seller getSellerByAge(int age) {
        return  sellerRepository.findByAge(age) .orElseThrow(() -> new EntityNotFoundException("Seller not found with id " + age));
    }

//    public Seller updateSeller(Integer id, SellerRequestDto sellerRequestDto) {
//        // First, check if the seller exists
//        if (!sellerRepository.existsById(id)) {
//            throw new EntityNotFoundException("Seller not found with id " + id);
//        }
//        return sellerRepository.save(sellerRequestDto); // Save the updated seller to the database
//    }
    public void deleteSeller(Integer id) {
        // Check if the seller exists before attempting to delete
        if (!sellerRepository.existsById(id)) {
            throw new EntityNotFoundException("Seller not found with id " + id);
        }
        sellerRepository.deleteById(id); // Delete the seller from the database
    }

}

