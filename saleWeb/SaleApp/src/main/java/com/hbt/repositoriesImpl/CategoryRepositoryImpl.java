/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hbt.repositoriesImpl;

import com.hbt.pojo.Category_;
import java.util.List;
import com.hbt.Repository.CategoryRepository;
import javax.persistence.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;

/**
 *
 * @author admin
 */
@Repository
public class CategoryRepositoryImpl implements CategoryRepository{

    @Autowired
    private LocalSessionFactoryBean factory;
    
    @Override
    public List<Category_> getCategory() {
        Session s = this.factory.getObject().getCurrentSession();
        Query q= s.createNamedQuery("Category.findAll");
        return q.getResultList();
    }
   
}
