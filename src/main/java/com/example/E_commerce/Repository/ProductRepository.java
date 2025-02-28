package com.example.E_commerce.Repository;

import com.example.E_commerce.Enums.ProductCategory;
import com.example.E_commerce.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {
        List<Product> findByProductCategory(ProductCategory productCategory);

        @Query(value = "select p from Product p where p.price > :price and p.productCategory=:category")
        List<Product> getAllProductsByPriceAndCategory(int price, String category);

    }
