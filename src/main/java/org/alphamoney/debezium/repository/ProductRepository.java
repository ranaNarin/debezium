package org.alphamoney.debezium.repository;

import org.alphamoney.debezium.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {


}
