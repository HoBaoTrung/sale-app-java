package com.trung.btHibernate;

import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import pojo.Category;
import pojo.Comment;
import pojo.OrderDetail;
import pojo.Product;
import pojo.SaleOrder;
import pojo.Tag;
import pojo.User;

public class HibernateUtils {
	private static final SessionFactory Factory;
	static {
		Configuration conf = new Configuration();

		Properties pros = new Properties();
		pros.put(Environment.DIALECT,"org.hibernate.dialect.MySQLDialect");
		pros.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
		pros.put(Environment.URL, "jdbc:mysql://localhost:3306/sale_app_java");
		pros.put(Environment.USER, "root");
		pros.put(Environment.PASS, "Bestpro890!@#");
		pros.put(Environment.SHOW_SQL, "true");

		conf.setProperties(pros);
		conf.addAnnotatedClass(Category.class);
		conf.addAnnotatedClass(Product.class);
		conf.addAnnotatedClass(Comment.class);
		conf.addAnnotatedClass(OrderDetail.class);
		conf.addAnnotatedClass(SaleOrder.class);
		conf.addAnnotatedClass(Tag.class);
		conf.addAnnotatedClass(User.class);
		ServiceRegistry service = new StandardServiceRegistryBuilder().applySettings(conf.getProperties()).build();
		Factory = conf.buildSessionFactory(service);
	}
	public static SessionFactory getFactory() {
		return Factory;
	}

}
