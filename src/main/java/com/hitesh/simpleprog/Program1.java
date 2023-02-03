package com.hitesh.simpleprog;

public class Program1 
{

	public static void main(String[] args) 
	{
		System.out.println("Welcome To Hibernate Programme");
		System.out.println("Here we are solving Fibonacci Series Program");
		int no1 = 0, no2 = 1,no3;
		for (int i = 0; i < 10; i++) 
		{
				no3 = no1 + no2;
				System.out.println(no3);
				no1 = no2;
				no2 = no3;
	
		}
		

	}

}
