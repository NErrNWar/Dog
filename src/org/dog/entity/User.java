package org.dog.entity;

import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "User_inf")
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id")
    private Integer id;

    @Column(nullable=false,unique = true)
    private String username;

    @Column(nullable=false,unique = true)
    private String account;

    @Column(nullable = false)
    private String password;
    private String tel;
    private String sex;
    private String role;

    @Temporal(TemporalType.DATE)
    private Calendar birth;
    private String adress;

    @OneToMany(targetEntity = Product.class,cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinTable(name="favorite_tab",
    joinColumns = @JoinColumn(name="user_id",referencedColumnName = "user_id"),
    inverseJoinColumns = @JoinColumn(name="pro_id",referencedColumnName = "pro_id"))
    private Set<Product> favorite=new HashSet<Product>();

    @OneToMany(targetEntity = Order.class,mappedBy = "user",cascade = CascadeType.ALL)
    private Set<Order> orders=new HashSet<Order>();

    @OneToMany(targetEntity = FeedBack.class,mappedBy = "user",cascade = CascadeType.ALL)
    private Set<FeedBack> feedBacks=new HashSet<FeedBack>();

    @OneToMany(targetEntity = Review.class,mappedBy = "user",cascade = CascadeType.ALL)
    private Set<Review> reviews=new HashSet<Review>();

    @Override
    public int hashCode() {
        int result=((getId()==null)?0:getId());
        result=result*11+((getUsername()==null)?0:getUsername().hashCode());
        result=result*11+((getAccount()==null)?0:getAccount().hashCode());
        result=result*11+((getPassword()==null)?0:getPassword().hashCode());
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
        User u=(User)obj;
        if(u.getId()!=getId()){
            return false;
        }
        if(u.getAccount()==null){
            if(getAccount()!=null){
                return false;
            }
        }
        else if(!u.getAccount().equals(getAccount())){
            return false;
        }
        if(u.getUsername()==null){
            if(getUsername()!=null){
                return false;
            }
        }
        else if(!u.getUsername().equals(getUsername())){
            return false;
        }
        if(u.getPassword()==null){
            if(getPassword()!=null){
                return false;
            }
        }
        else if(!u.getPassword().equals(getPassword())){
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Calendar getBirth() {
        return birth;
    }

    public void setBirth(Calendar birth) {
        this.birth = birth;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public Set<Product> getFavorite() {
        return favorite;
    }

    public void setFavorite(Set<Product> favorite) {
        this.favorite = favorite;
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }

    public Set<FeedBack> getFeedBacks() {
        return feedBacks;
    }

    public void setFeedBacks(Set<FeedBack> feedBacks) {
        this.feedBacks = feedBacks;
    }

    public Set<Review> getReviews() {
        return reviews;
    }

    public void setReviews(Set<Review> reviews) {
        this.reviews = reviews;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
