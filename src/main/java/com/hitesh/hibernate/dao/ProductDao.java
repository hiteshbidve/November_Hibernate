package com.hitesh.hibernate.dao;

import java.util.List;
import java.util.Scanner;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.hitesh.hibernate.configuration.HibernateConfig;
import com.hitesh.hibernate.entity.Product;

public class ProductDao 
{
	static SessionFactory sessionFactory = HibernateConfig.getSessionFactory();
	
	public String saveProduct(Product product)
	{
		Session session = sessionFactory.openSession();
		try
		{
			Product dbProduct = getProductById(product.getProductId());
			if(dbProduct == null)
			{
				Transaction transaction = session.beginTransaction();
				session.save(product);
				transaction.commit();
				return  +product.getProductId()+ " Product Added Succesfully";
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally 
		{
			session.close();
		}
		return +product.getProductId()+" On Which ProductId You Entered is already Present in Database......!!!!!";
		
	}
	
	public Product getProductById(long productId)
	{
		Session session = sessionFactory.openSession();
		Product product = null;
		try 
		{
			product = session.get(Product.class, productId);
			
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		finally 
		{
			session.close();
		}
		return product;
		
	}
	
	public boolean deleteProductById(long productId)
	{
		Session session = sessionFactory.openSession();
		Product product = null;
		boolean isDeleted = false;
		try
		{
			Transaction transaction = session.beginTransaction();
			product = getProductById(productId);
			session.delete(product);
			transaction.commit();
			isDeleted = true;
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		finally 
		{
			session.close();
	
		}
		return isDeleted;
		
	}
	
	public Product updateProduct(Product product)
	{
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		try
		{
			session.update(product);
			transaction.commit();
		}
		catch(Exception exception)
		{
			exception.printStackTrace();
		}
		finally 
		{
			session.close();
		}
		return product;
	}

	public static List<Product> getAllProducts() 
	{
		Session session = sessionFactory.openSession();
		List<Product> listOfProduct = null;
		Criteria criteria = null;
		Scanner scanner = new Scanner(System.in);
		try 
		{			
			criteria = session.createCriteria(Product.class);
			System.out.println("On Which Base You Want to Sort the Data");
			System.out.println("1. For Product Id in Ascending");
			System.out.println("2. For Product Id in Descending");
			System.out.println("3. For Product Name in Ascending");
			System.out.println("4. For Product Name in Descending");
			System.out.println("5. For Product Price in Ascending");
			System.out.println("6. For Product Price in Descending");
			System.out.println("7. For Exit");
			
			int choice = scanner.nextInt();
			switch (choice) 
			{
			case 1 :
				criteria.addOrder(Order.asc("productId"));
				break;
			case 2 :
				criteria.addOrder(Order.desc("productId"));
				break;
			case 3 :
				criteria.addOrder(Order.asc("productName"));
				break;
			case 4 :
				criteria.addOrder(Order.desc("productName"));
				break;
			case 5 :
				criteria.addOrder(Order.asc("productPrice"));
				break;
			case 6 :
				criteria.addOrder(Order.desc("productPrice"));
				break;
			case 7 :
				System.exit(0);
				break;
			default:
				System.out.println("You Entered Wrong Choice....Please try again.....!!!!");
				break;
			}
			
			listOfProduct = criteria.list();
			
		}
		catch (Exception exception) 
		{
			exception.printStackTrace();
		}
		finally 
		{
			session.close();
		}
		return listOfProduct;
	}

	public List<Product> getPageProducts() 
	{
		Session session = sessionFactory.openSession();
		List<Product> pageProducts = null;
		Scanner scanner = new Scanner(System.in);
		try 
		{
			scanner = new Scanner(System.in);
			Criteria criteria = session.createCriteria(Product.class);
			criteria.setFirstResult(0);
			System.out.println("Enter Howmany Product Do you Want to see?????/");
			int choice = scanner.nextInt();
			criteria.setMaxResults(choice);
			pageProducts = criteria.list();
		} 
		catch (Exception exception) 
		{
			exception.printStackTrace();
		}
		finally
		{
			session.close();
		}
		
		return pageProducts;
	}

	public List<Product> getProductByInput() 
	{
		Session session = sessionFactory.openSession();
		List<Product> listOfSearchedWord = null;
		Scanner scanner = new Scanner(System.in);
		String searchText = null;
		try 
		{
			Criteria criteria = session.createCriteria(Product.class);
			System.out.println("Enter 1 for get Product By Name");
			System.out.println("Enter 2 for get Product based on Price");
			System.out.println("Enter 3 for get Product based on Product Id");
			System.out.println("Enter 4 for get Product based on Supplier Id");
			System.out.println("Enter 5 for get Product based on Category Id");
			System.out.println("Enter 6 for Exit to Search");
			int choice = scanner.nextInt();
			switch (choice) {
			case 1 :
			{
				System.out.println("Type the Word or Text Which You Want to Find");
				searchText = scanner.next();
	 			criteria.add(Restrictions.like("productName", "%"+searchText+"%"));
	 			break;
			}
			case 2 :
			{
				System.out.println("Type Price Value to Get Details");
				double searchValue = scanner.nextDouble();
				criteria.add(Restrictions.gt("productPrice", searchValue));
				break;
			}
			case 3:
				System.out.println("Enter between Product Id You want to Search");
				double searchValueLow = scanner.nextDouble();
				double searchValueHigh = scanner.nextDouble();
				criteria.add(Restrictions.between("productId", searchValueLow - 0.01d, (double)searchValueHigh + 0.01d));
				break;
			case 6 :
				System.exit(0);
				break;
			
			default:
				break;
			}
			
 			listOfSearchedWord = criteria.list();
 			
		} 
		catch (Exception exception) 
		{
			exception.printStackTrace();
		}
		finally
		{
			session.close();
		}
		return listOfSearchedWord;
	}
	
	
}
