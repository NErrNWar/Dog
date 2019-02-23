package org.dog.entity;

import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name="OrderItem_inf")
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ori_id")
    private Integer id;

    @ManyToOne(targetEntity = Order.class)
    @JoinColumn(name = "ord_id",referencedColumnName = "ord_id")
    private Order order;

    @ManyToOne(targetEntity = Product.class)
    @JoinColumn(name = "pro_id",referencedColumnName = "pro_id")
    private Product product;

    private Integer num;

    private Double price;

    private String type;

    @Override
    public int hashCode() {
        int result=(getId()==null)?0:getId().intValue();
        result=result*7+((getOrder()==null)?0:((getOrder().getId()==null)?0:getOrder().getId()));
        result=result*7+((getProduct()==null)?0:((getProduct().getId()==null)?0:getProduct().getId()));
        result=result*7+getNum();
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
        OrderItem oi=(OrderItem)obj;
        if(oi.getId()!=getId()){
            return false;
        }
        if(oi.getNum()!=getNum()){
            return false;
        }
        if(oi.getOrder()==null){
            if(getOrder()!=null){
                return false;
            }
        }
        else if(!oi.getOrder().equals(getOrder())){
            return false;
        }
        if(oi.getProduct()==null){
            if(getProduct()!=null){
                return false;
            }
        }
        else if(!oi.getProduct().equals(getProduct())){
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

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
