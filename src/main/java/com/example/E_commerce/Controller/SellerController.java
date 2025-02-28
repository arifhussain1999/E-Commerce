package com.example.E_commerce.Controller;

import com.example.E_commerce.DTO.RequestDto.SellerRequestDto;
import com.example.E_commerce.DTO.ResponseDto.SellerResponseDto;
import com.example.E_commerce.Model.Seller;
import com.example.E_commerce.Service.SellerService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/seller")
public class SellerController {

    @Autowired
    SellerService sellerService;

    @PostMapping("/add")
    public ResponseEntity addSeller(@RequestBody SellerRequestDto sellerRequestDto) {
        try {
            SellerResponseDto sellerResponseDto = sellerService.addSeller(sellerRequestDto);
            return new ResponseEntity(sellerResponseDto, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


    //get a seller by id
//    @GetMapping("/get/{id}")
//    public Seller GetSellerById(@PathVariable("id") int id){
//       Seller seller=sellerService.getSellerById(id);
//        return seller;
//    }
    @GetMapping("/get/{id}")
    public ResponseEntity<Seller> getSellerById(@PathVariable("id") int id) {
        try {
            Seller seller = sellerService.getSellerById(id);
            return ResponseEntity.ok(seller);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // Return 404 if not found
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null); // Return 500 for any other error
        }
    }
    //get by emailid
    @GetMapping("/get/{emailId}")
    public ResponseEntity<Seller> getSellerByEmailId(@PathVariable("emailId") String emailId) {
        try {
            Seller seller = sellerService.getSellerByEmailId(emailId);
            return ResponseEntity.ok(seller);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // Return 404 if not found
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null); // Return 500 for any other error
        }
    }
    //get all seller
    @GetMapping("/get/all")
    public ResponseEntity<List<Seller>> getAllSellers() {
        List<Seller> sellers = sellerService.getAllSellers();
        return ResponseEntity.ok(sellers);
    }
    //get all seller of perticular age
    @GetMapping("/get/{age}")
    public ResponseEntity<Seller> getSellerByEmailId(@PathVariable("age") int age) {
        try {
            Seller seller = sellerService.getSellerByAge(age);
            return ResponseEntity.ok(seller);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // Return 404 if not found
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null); // Return 500 for any other error
        }
    }
    //update seller info based on email id
//    @PutMapping("/update/{emailId}")
//    public ResponseEntity<Seller> updateSellerByEmailId(@PathVariable("emailId") String emailId, @RequestBody SellerRequestDto sellerRequestDto) {
//        Seller updatedSeller = sellerService.updateSeller(emailId,sellerRequestDto);
//        return ResponseEntity.ok(updatedSeller);
//    }
    //delete by id
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSeller(@PathVariable("id") Integer id) {
        sellerService.deleteSeller(id);
        return ResponseEntity.noContent().build();
    }
}