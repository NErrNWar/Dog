package org.dog.entity;

import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name="Product_inf")
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Product {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="pro_id")
    private Integer id;

    private String name;
    private String title;
    private Double originalPrice;
    private Double promotePrice;
    private Integer num;
    private Integer outnum;
    private String prodes;

    @Temporal(TemporalType.TIMESTAMP)
    private Calendar createTime;

    @OneToMany(targetEntity = OrderItem.class,mappedBy = "product")
    private Set<OrderItem> orderItems=new HashSet<OrderItem>();

    @OneToMany(targetEntity = ProductImage.class,mappedBy = "product")
    private Set<ProductImage> productImages=new HashSet<ProductImage>();

    @OneToMany(targetEntity = Review.class,mappedBy = "product",cascade = CascadeType.ALL)
    private Set<Review> reviews=new HashSet<Review>();

    @OneToMany(targetEntity = PropertyValue.class,mappedBy = "product")
    private Set<PropertyValue> propertyValues=new HashSet<PropertyValue>();

    @ManyToOne(targetEntity = Category.class)
    @JoinColumn(name="cat_id",referencedColumnName = "cat_id")
    private Category category;

    @Override
    public int hashCode() {
        int result=(getId()==null)?0:getId().intValue();
        result=result*11+((getName()==null)?0:getName().hashCode());
        result=result*11+((getCategory()==null)?0:((getCategory().getId()==null)?0:getCategory().getId()));
        result=result*11+((getTitle()==null)?0:getTitle().hashCode());
        result=result*11+((getCreateTime()==null)?0:getCreateTime().hashCode());
        result= (int) (result*11+getOriginalPrice());
        result= (int) (result*11+getPromotePrice());
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
        Product p=(Product)obj;
        if(p.getId()!=getId()){
            return false;
        }
        if(p.getPromotePrice()!=getPromotePrice()){
            return false;
        }
        if(p.getOriginalPrice()!=getOriginalPrice()){
            return false;
        }
        if(p.getName()!=null){
            if(getName()==null){
                return false;
            }
        }
        else if(!p.getName().equals(getName())){
            return false;
        }
        if(p.getTitle()==null){
            if(getTitle()!=null){
                return false;
            }
        }
        else if(!p.getTitle().equals(getTitle())){
            return false;
        }
        if(p.getCategory()==null){
            if(getCategory()!=null){
                return false;
            }
        }
        else if(!p.getCategory().equals(getCategory())){
            return false;
        }
        if(p.getCreateTime()==null){
            if(getCreateTime()!=null){
                return false;
            }
        }
        else if(!p.getCreateTime().equals(getCreateTime())){
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(Double originalPrice) {
        this.originalPrice = originalPrice;
    }

    public Double getPromotePrice() {
        return promotePrice;
    }

    public void setPromotePrice(Double promotePrice) {
        this.promotePrice = promotePrice;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Calendar getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Calendar createTime) {
        this.createTime = createTime;
    }

    public Set<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(Set<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public Set<ProductImage> getProductImages() {
        return productImages;
    }

    public void setProductImages(Set<ProductImage> productImages) {
        this.productImages = productImages;
    }

    public Set<Review> getReviews() {
        return reviews;
    }

    public void setReviews(Set<Review> reviews) {
        this.reviews = reviews;
    }

    public Set<PropertyValue> getPropertyValues() {
        return propertyValues;
    }

    public void setPropertyValues(Set<PropertyValue> propertyValues) {
        this.propertyValues = propertyValues;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Integer getOutnum() {
        return outnum;
    }

    public void setOutnum(Integer outnum) {
        this.outnum = outnum;
    }

    public String getProdes() {
        return prodes;
    }

    public void setProdes(String prodes) {
        this.prodes = prodes;
    }
}
