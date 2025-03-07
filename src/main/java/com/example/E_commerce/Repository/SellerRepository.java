package com.example.E_commerce.Repository;

import com.example.E_commerce.Model.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SellerRepository extends JpaRepository<Seller,Integer> {
    Optional<Seller> findByEmailId(String emailId);
    Optional<Seller> findByAge(int age);

}
