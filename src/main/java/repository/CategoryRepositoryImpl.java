package repository;

import java.util.List;


import org.hibernate.Session;
import org.hibernate.query.Query;

import com.trung.btHibernate.HibernateUtils;

import pojo.Category;

public class CategoryRepositoryImpl {
	public List<Category> getCategory(){
		try(Session s= HibernateUtils.getFactory().openSession()){
			Query q = s.createQuery("From  Category");
			List<Category> cates =  q.getResultList();
			return cates;
		}
	}
	
	public Category getCateId(int id) {
		try(Session s= HibernateUtils.getFactory().openSession()){
			return s.get(Category.class, id);
		}
	}
}
