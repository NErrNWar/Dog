package org.dog.vo;

import org.dog.entity.FeedBack;
import org.dog.entity.Review;
import org.dog.entity.User;

import java.util.Calendar;
import java.util.List;

public class UserBean {
    private int id;
    private String username;
    private String account;
    private String tel;
    private String sex;
    private Calendar birth;
    private String adress;
    private List<ProductBean> favorite;
    private List<Review> reviews;
    private List<OrderItemBean> orderItemBeans;
    private List<FeedBack> feedBacks;

    public UserBean(){}

    public UserBean(User user){
        this.id=user.getId();
        this.username=user.getUsername();
        this.account=user.getAccount();
        this.tel=user.getTel();
        this.sex=user.getSex();
        this.birth=user.getBirth();
        this.adress=user.getAdress();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public List<ProductBean> getFavorite() {
        return favorite;
    }

    public void setFavorite(List<ProductBean> favorite) {
        this.favorite = favorite;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public List<OrderItemBean> getOrderItemBeans() {
        return orderItemBeans;
    }

    public void setOrderItemBeans(List<OrderItemBean> orderItemBeans) {
        this.orderItemBeans = orderItemBeans;
    }

    public List<FeedBack> getFeedBacks() {
        return feedBacks;
    }

    public void setFeedBacks(List<FeedBack> feedBacks) {
        this.feedBacks = feedBacks;
    }
}
