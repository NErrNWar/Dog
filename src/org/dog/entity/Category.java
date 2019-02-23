package org.dog.entity;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name="Category_inf")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="cat_id")
    private Integer id;

    @Column(nullable=false,unique = true)
    private String name;

    @OneToMany(targetEntity = Product.class,mappedBy = "category")
    private Set<Product> products=new HashSet<Product>();

    @OneToMany(targetEntity = Property.class,mappedBy = "category")
    private Set<Property> properties=new HashSet<Property>();

    @Override
    public int hashCode() {
        int result=(getId()==null)?0:getId().intValue();
        result=result*11+((getName()==null)?0:getName().hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj==this){
            return true;
        }
        if(obj==null){
            return false;
        }
        if(!obj.getClass().equals(getClass())){
            return false;
        }
        Category cat=(Category)obj;
        if(cat.getId()!=getId()){
            return false;
        }
        if(cat.getName()==null){
            if(getName()!=null){
                return false;
            }
        }
        else if(!cat.getName().equals(getName())){
            return false;
        }
        return true;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    public Set<Property> getProperties() {
        return properties;
    }

    public void setProperties(Set<Property> properties) {
        this.properties = properties;
    }
}
