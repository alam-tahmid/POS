package com.pos.lotto.service;

import java.util.List;

import com.pos.lotto.model.Product;

public interface ProductService {

	public Product findById(String id);
	
	public void addOrder(Product product);
	
	public void updateOrder(Product product);
	
	public List<Product> findAll();
}
