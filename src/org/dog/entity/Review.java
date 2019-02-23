package org.dog.entity;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name="Review_inf")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="rew_id")
    private Integer id;

    @Temporal(TemporalType.TIMESTAMP)
    private Calendar createTime;
    private String content;
    private String type;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name="user_id",referencedColumnName = "user_id")
    private User user;

    @ManyToOne(targetEntity = Product.class)
    @JoinColumn(name="pro_id",referencedColumnName = "pro_id")
    private Product product;

    @Override
    public int hashCode() {
        int result=(getId()==null)?0:getId().intValue();
        result=result*11+((getProduct()==null)?0:((getProduct().getId()==null)?0:getProduct().getId()));
        result=result*11+((getUser()==null)?0:((getUser().getId()==null)?0:getUser().getId()));
        result=result*11+((getCreateTime()==null)?0:getCreateTime().hashCode());
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
        Review r=(Review)obj;
        if(r.getId()!=getId()){
            return false;
        }
        if(r.getProduct()==null){
            if(getProduct()!=null){
                return false;
            }
        }
        else if(!r.getProduct().equals(getProduct())){
            return false;
        }
        if(r.getUser()==null){
            if(getUser()!=null){
                return false;
            }
        }
        else if(!r.getUser().equals(getUser())){
            return false;
        }
        if(r.getCreateTime()==null){
            if(getCreateTime()!=null){
                return false;
            }
        }
        else if(!r.getCreateTime().equals(getCreateTime())){
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

    public Calendar getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Calendar createTime) {
        this.createTime = createTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
