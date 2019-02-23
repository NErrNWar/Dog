package org.dog.entity;

import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name="PropertyValue_inf")
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
public class PropertyValue {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="ptv_id")
    private Integer id;
    private String value;

    @ManyToOne(targetEntity=Property.class)
    @JoinColumn(name="pry_id",referencedColumnName = "pry_id")
    private Property property;

    @ManyToOne(targetEntity=Product.class)
    @JoinColumn(name="pro_id",referencedColumnName = "pro_id")
    private Product product;

    @Override
    public int hashCode() {
        int result=(getId()==null)?0:getId().intValue();
        result=result*11+((getProperty()==null)?0:((getProperty().getId()==null)?0:getProperty().getId()));
        result=result*11+((getProduct()==null)?0:((getProduct().getId()==null)?0:getProduct().getId()));
        result=result*11+((getValue()==null)?0:getValue().hashCode());
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
        PropertyValue pv=(PropertyValue)obj;
        if(pv.getId()!=getId()){
            return false;
        }
        if(pv.getProduct()==null){
            if(getProduct()!=null){
                return false;
            }
        }
        else if(!pv.getProduct().equals(getProduct())){
            return false;
        }
        if(pv.getProperty()==null){
            if(pv.getProperty()!=null){
                return false;
            }
        }
        else if(!pv.getProperty().equals(getProperty())){
            return false;
        }
        if(pv.getValue()==null){
            if(getValue()!=null){
                return false;
            }
        }
        else if(!pv.getValue().equals(getValue())){
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

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Property getProperty() {
        return property;
    }

    public void setProperty(Property property) {
        this.property = property;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
