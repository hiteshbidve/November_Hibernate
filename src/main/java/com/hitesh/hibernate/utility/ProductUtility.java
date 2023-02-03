package com.hitesh.hibernate.utility;

import java.util.Scanner;

import com.hitesh.hibernate.entity.Product;

public class ProductUtility 
{
	public static Product prepareProductData(Scanner scanner)
	{
		System.out.println("Enter Product Id");
		long productId = scanner.nextLong();
		System.out.println("Enter Product Name");
		String productName = scanner.next();
		System.out.println("Enter Supplier Id");
		long supplierId = scanner.nextLong();
		System.out.println("Enter Category Id");
		long categoryId = scanner.nextLong();
		System.out.println("Enter Quantity of Product");
		int qty = scanner.nextInt();
		System.out.println("Enter Price of Product");
		double productPrice = scanner.nextDouble();
		
		Product product = new Product(productId, productName, supplierId, categoryId, qty, productPrice);
		return product;
		
	}

}
