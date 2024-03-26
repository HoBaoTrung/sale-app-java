package repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.trung.btHibernate.HibernateUtils;

import pojo.Product;

public class ProductRepositoryImpl {
	public List<Product> getProducts(Map<String, String> params){
		try(Session s = HibernateUtils.getFactory().openSession()){
			
			CriteriaBuilder b = s.getCriteriaBuilder();
			CriteriaQuery<Product> q = b.createQuery(Product.class);
			
			Root r = q.from(Product.class);
			q.select(r);
			
			List<Predicate> predicates = new ArrayList<>();
			String kw = params.get("kw");
			if(kw != null && !kw.isEmpty()) {
				predicates.add( b.like(r.get("name"), String.format("%%%s%%", kw)));
			}
			String fromPrice = params.get("fromPrice");
			String toPrice = params.get("toPrice");
			
			if (fromPrice!=null && !fromPrice.isEmpty()) 
				predicates.add(b.greaterThanOrEqualTo(r.get("price"), Double.parseDouble(fromPrice)));
			
			
			if (toPrice!=null && !toPrice.isEmpty()) 
				predicates.add(b.lessThanOrEqualTo(r.get("price"), Double.parseDouble(toPrice)));
			
			String cateID = params.get("cateId");
			if (cateID!=null && !cateID.isEmpty()) {
				predicates.add(b.equal(r.get("category").as(Integer.class), Integer.parseInt(cateID)));
			}
			
			q.where( predicates.toArray(new Predicate[predicates.size()]));
			
			q.orderBy(b.desc(r.get("id")));
			
			Query query = s.createQuery(q);
			List<Product> products = query.getResultList();
			
			return products;
		}
	}
	
	public void addOrUpdate(Product p) {
		try(Session s = HibernateUtils.getFactory().openSession()){
			s.saveOrUpdate(p);
		}
	}
}
