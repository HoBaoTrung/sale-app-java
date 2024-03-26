package repository;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.trung.btHibernate.HibernateUtils;

import pojo.OrderDetail;
import pojo.Product;

public class statsRevenueByProduct {
	public List<Object[]> statsRevenueByProduct() {
		try(Session s = HibernateUtils.getFactory().openSession()){
			CriteriaBuilder b = s.getCriteriaBuilder();
			CriteriaQuery<Object[]> q = b.createQuery(Object[].class);
			
			Root rP = q.from(Product.class);
			Root rD = q.from(OrderDetail.class);
			q.multiselect(rP.get("id"), rP.get("name"), b.sum(b.prod(rD.get("unitPrice"), rD.get("quantity"))) );
			List<Predicate> predicates = new ArrayList<Predicate>();
			predicates.add((Predicate) b.equal(rP.get("id"), rD.get("product")));
			q.groupBy(rP.get("id"));
			
			Query query = s.createQuery(q);
			return query.getResultList();
		}
	}
}
