package com.trung.btHibernate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.query.Query;

import pojo.Category;
import pojo.Product;
import repository.CategoryRepositoryImpl;
import repository.ProductRepositoryImpl;

public class HibernateDemo {

	public static void main(String[] args) {
		
			
			
//			Query q = s.createQuery("From Category");
//			List<Category> cates = q.getResultList();
//			cates.forEach(c -> System.out.println(c.getId()));
		
//			Map<String, String> params = new HashMap<>();
//			params.put("fromPrice", "18000000");
//			params.put("toPrice", "20000000");
//			
			
			
			CategoryRepositoryImpl s =new CategoryRepositoryImpl();
			s.getCategory().forEach(c -> System.out.println(c.getName()));
		
			ProductRepositoryImpl pro = new ProductRepositoryImpl();
			Product p =new Product();
			p.setName("ABC");
			p.setPrice((long) 1200000);
			p.setCategory(s.getCateId(1));
			pro.addOrUpdate(p);
//			pro.getProducts(params).forEach(p -> System.out.printf("%d - %s - %.1f - %s\n", 
//					p.getId(), p.getName(), p.getPrice(),p.getCategory().getName()));
		
	}

}
