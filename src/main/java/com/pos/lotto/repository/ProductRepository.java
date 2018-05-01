package com.pos.lotto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pos.lotto.model.Product;

@Repository("productRepository")
public interface ProductRepository extends JpaRepository<Product, String> {
}
