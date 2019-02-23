package org.dog.entity;

import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

@Entity
@Table(name="ProductImage_inf")
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
public class ProductImage {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="pim_id")
    private Integer id;

    @ManyToOne(targetEntity = Product.class)
    @JoinColumn(name="pro_id",referencedColumnName = "pro_id")
    private Product product;
    private String type;
    private String ipath;

    @Override
    public int hashCode() {
        int result=(getId()==null)?0:getId().intValue();
        result=result*11+((getIpath()==null)?0:getIpath().hashCode());
        result=result*11+((getType()==null)?0:getType().hashCode());
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
        ProductImage pi=(ProductImage)obj;
        if(pi.getId()!=getId()){
            return false;
        }
        if(pi.getIpath()==null){
            if(getIpath()!=null){
                return false;
            }
        }
        else if(!pi.getIpath().equals(getIpath())){
            return false;
        }
        if(pi.getType()==null){
            if(getType()!=null){
                return false;
            }
        }
        else if(!pi.getType().equals(getType())){
            return false;
        }
        return  true;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIpath() {
        return ipath;
    }

    public void setIpath(String ipath) {
        this.ipath = ipath;
    }
}
