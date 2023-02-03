package com.hitesh.hibernate;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import org.hibernate.Criteria;

import com.hitesh.hibernate.entity.Product;
import com.hitesh.hibernate.service.ProductService;
import com.hitesh.hibernate.utility.ProductUtility;

public class Controller 
{

	public static void main(String[] args) 
	{
		Scanner scanner = new Scanner(System.in);
		boolean option;
		ProductService productService = new ProductService();
		do
		{		
		System.out.println("Enter 1 for Save the Product");
		System.out.println("Enter 2 for Search Product by ProductId");
		System.out.println("Enter 3 for Delete Product by ProductId");
		System.out.println("Enter 4 for Update Product by ProductId");
		System.out.println("Enter 5 for Get All Products");
		System.out.println("Enter 6 for Howmany Products Do You Want to see??????/\"");
		System.out.println("Enter 7 for Get Products By Input");
		System.out.println("Press any Other key for exit");
		int choice = scanner.nextInt();
		switch (choice) 
		{
		case 1 :
		{
			Product product = ProductUtility.prepareProductData(scanner);
			String productAdded = productService.saveProduct(product);
			System.out.println(productAdded);
			break;
		}	
		case 2 :
		{
			System.out.println("Enter Product Id on Which You find the Product");
			long productId = scanner.nextLong();
			Product product = productService.getProductById(productId);
			if(product != null)
			{
				System.out.println(product);
			}
			else
			{
				System.out.println("Product Id "+productId+ " you find Does not Exist.....!!!!!");
			}
			
			break;
		}	
		case 3 :
		{
			System.out.println("Enter ProductId on Which You want to Delete the Product");
			long productId = scanner.nextLong();
			boolean isDeleted = productService.deleteProductById(productId);
			if(isDeleted)
			{
				System.out.println("Your Product Deleted Successfully........!!!!!");
				System.out.println(isDeleted);
			}
			else
			{
				System.out.println("Product Id " +productId+ " you entered does not exist..........!!!!!!!" );
			}
			
			break;
		}
		case 4 :
		{	
			Product product = ProductUtility.prepareProductData(scanner);
			Product updateProductById = productService.updateProduct(product);
			System.out.println("Enter for Update the Product");
		  	
		  	if(updateProductById != null)
		  	{
		  	System.out.println("Updated " +updateProductById);
		  	}
		  	else
		  	{
		  		System.out.println("Product You Searched for are not Exist");
		  	}
			break;
		}
		
		case 5 :
		{
			List<Product> productList = productService.getAllProducts();
			if (productList.isEmpty() || productList == null) 
			{
				System.out.println("No Products Exists");
			}
			else
			{
				for (Product product : productList) 
				{
					System.out.println(product);
				}
			}
			break;
		}
		
		case 6 :
		{
			List<Product> pageProducts = productService.getPageOfProducts();
			if(pageProducts.isEmpty())
			{
				System.out.println("No Product Exist");
			}
			else
			{
				for (Product product : pageProducts) 
				{
					System.out.println(product);
				}
			}
			break;
		}
		
		case 7 :
		{
			List<Product> inputProduct = productService.getProductByInput();
			if(inputProduct.isEmpty())
			{
				System.out.println("No Product Exist");
			}
			else
			{
				for (Product product : inputProduct) 
				{
					System.out.println(product);
				}
			}
			break;
		}
		
		default:
			System.out.println("You Entered Wrong Value");
			System.exit(0);
			break;	
		}
		System.out.println("Do You Want to Continue Press Y/N");
		option = scanner.next().startsWith("y");
		}while(option == true);
		
		System.out.println("Program Terminated");
		
	}

}
