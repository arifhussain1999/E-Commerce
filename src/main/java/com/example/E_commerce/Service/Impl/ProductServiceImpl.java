package com.example.E_commerce.Service.Impl;

import com.example.E_commerce.DTO.RequestDto.ProductRequestDto;
import com.example.E_commerce.DTO.ResponseDto.ProductResponseDto;
import com.example.E_commerce.Enums.ProductCategory;
import com.example.E_commerce.Enums.ProductStatus;
import com.example.E_commerce.Exception.InvalidSellerException;
import com.example.E_commerce.Model.Item;
import com.example.E_commerce.Model.Product;
import com.example.E_commerce.Model.Seller;
import com.example.E_commerce.Repository.ProductRepository;
import com.example.E_commerce.Repository.SellerRepository;
import com.example.E_commerce.Service.ProductService;
import com.example.E_commerce.transformer.ProductTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

        @Autowired
        SellerRepository sellerRepository;
        @Autowired
        private ProductRepository productRepository;

        public ProductResponseDto addProduct(@RequestBody ProductRequestDto productRequestDto) throws InvalidSellerException {

            Seller seller;
            try{
                seller =  sellerRepository.findById(productRequestDto.getSellerId()).get();
            }
            catch (Exception e){
                throw new InvalidSellerException("Seller doesn't exist");
            }

            Product product = ProductTransformer.ProductRequestDtoToProduct(productRequestDto);
            product.setSeller(seller);//setting seller that is already have

            // add product to current products of seller
            seller.getProducts().add(product);
            sellerRepository.save(seller);  // saves both seller and product

            // prepare Response Dto
            return ProductTransformer.ProductToProductResponseDto(product);
        }

        public List<ProductResponseDto> getAllProductsByCategory(ProductCategory category){

            List<Product> products = productRepository.findByProductCategory(category);

            List<ProductResponseDto> productResponseDtos = new ArrayList<>();
            for(Product product: products){
                productResponseDtos.add(ProductTransformer.ProductToProductResponseDto(product));
            }

            return productResponseDtos;
        }

        public List<ProductResponseDto> getAllProductsByPriceAndCategory(int price, String category){

            List<Product> products = productRepository.getAllProductsByPriceAndCategory(price,category);

            List<ProductResponseDto> productResponseDtos = new ArrayList<>();
            for(Product product: products){
                productResponseDtos.add(ProductTransformer.ProductToProductResponseDto(product));
            }

            return productResponseDtos;
        }

        public void decreaseProductQuantity(Item item) throws Exception {

            Product product = item.getProduct();
            int quantity = item.getRequiredQuantity();
            int currentQuantity = product.getQuantity();
            if(quantity>currentQuantity){
                throw new Exception("Out of stock");
            }
            product.setQuantity(currentQuantity-quantity);
            if(product.getQuantity()==0){
                product.setProductStatus(ProductStatus.OUT_OF_STOCK);
            }
        }
}
