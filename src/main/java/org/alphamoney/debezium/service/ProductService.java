package org.alphamoney.debezium.service;

import org.alphamoney.debezium.model.Product;
import org.alphamoney.debezium.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;


     public Product addProduct(Product product){
         return productRepository.save(product);
     }

     public List<Product> getAllProduct(){
         return productRepository.findAll();
     }

     public Product updateProduct(Long productId, Product updateProduct){

         final Product currentProduct= productRepository.findById(productId).orElse(null);
         currentProduct.setName(updateProduct.getName());
         currentProduct.setPrice(updateProduct.getPrice());
         currentProduct.setStock(updateProduct.getStock());
         return productRepository.save(currentProduct);
     }


}
