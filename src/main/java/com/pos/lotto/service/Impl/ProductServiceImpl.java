package com.pos.lotto.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pos.lotto.model.Product;
import com.pos.lotto.repository.ProductRepository;
import com.pos.lotto.service.ProductService;

@Service("productService")
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Override
	public Product findById(String id) {
	
		return productRepository.findOne(id);
	}

	@Override
	public void addOrder(Product product) {

		productRepository.save(product);
	}

	@Override
	public void updateOrder(Product product) {

		productRepository.save(product);
	}

	@Override
	public List<Product> findAll() {
		
		return productRepository.findAll();
	}

}
