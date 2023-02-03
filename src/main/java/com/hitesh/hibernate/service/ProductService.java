package com.hitesh.hibernate.service;

import java.util.List;
import java.util.Scanner;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;

import com.hitesh.hibernate.dao.ProductDao;
import com.hitesh.hibernate.entity.Product;

public class ProductService 
{
	ProductDao productDao = new ProductDao();

	public String saveProduct(Product product)
	{
		return productDao.saveProduct(product);
	}
	
	public Product getProductById(long productId)
	{
		return productDao.getProductById(productId);
	}
	
	public boolean deleteProductById(long productId)
	{
		return productDao.deleteProductById(productId);
	}
	
	public Product updateProduct(Product product)
	{
		return productDao.updateProduct(product);
	}

	public List<Product> getAllProducts() 
	{	
		return productDao.getAllProducts();
	}

	public List<Product> getPageOfProducts() 
	{
		return productDao.getPageProducts();
		
	}

	public List<Product> getProductByInput() 
	{		
		return productDao.getProductByInput();
	}
}
