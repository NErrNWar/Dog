package org.dog.entity;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "Order_inf")
public class Order {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="ord_id")
    private Integer id;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name="user_id",referencedColumnName = "user_id")
    private User user;

    @Temporal(TemporalType.TIMESTAMP)
    private Calendar buyTime;

    @Temporal(TemporalType.TIMESTAMP)
    private Calendar receiveTime;

    @Column(unique = true)
    private String ordnum;
    private Double price;

    @OneToMany(targetEntity = OrderItem.class,mappedBy = "order",cascade = CascadeType.ALL)
    private Set<OrderItem> orderItems=new HashSet<OrderItem>();

    private String receiver;
    private String tel;
    private String address;
    private String type;

    @Override
    public int hashCode() {
        int result = (getId()==null)?0:getId().intValue();
        result=result*7+((getUser()==null)?0:((getUser().getId()==null)?0:getUser().getId()));
        result=result*7+((getBuyTime()==null)?0:getBuyTime().hashCode());
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
        Order o=(Order)obj;
        if(o.getId()!=getId()){
            return false;
        }
        if(o.getUser()==null){
            if(getUser()!=null){
                return false;
            }
        }
        else if(!o.getUser().equals(getUser())){
            return false;
        }
        if(o.getBuyTime()==null){
            if(getBuyTime()!=null){
                return false;
            }
        }
        else if(!o.getBuyTime().equals(getBuyTime())){
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Calendar getBuyTime() {
        return buyTime;
    }

    public void setBuyTime(Calendar buyTime) {
        this.buyTime = buyTime;
    }

    public Calendar getReceiveTime() {
        return receiveTime;
    }

    public void setReceiveTime(Calendar receiveTime) {
        this.receiveTime = receiveTime;
    }

    public String getOrdnum() {
        return ordnum;
    }

    public void setOrdnum(String ordnum) {
        this.ordnum = ordnum;
    }

    public Set<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(Set<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
