package org.dog.vo;

import org.dog.entity.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

public class ProductBean {
    private int proid;
    private String name;
    private String title;
    private String prodes;
    private Calendar createtime;
    private double originalPrice;
    private double promotePrice;
    private int num;
    private int outnum;
    private List<String> infimgpath;
    private List<String> smaimgpath;
    private List<String> detimgpath;
    private List<Review> reviews;
    private List<OrderItem> orderItems;
    private Map<String,String> propertyValues;
    private String firstimg;
    private int reviewnum;

    public ProductBean(){}

    public ProductBean(Product product){
        this.name=product.getName();
        this.createtime=product.getCreateTime();
        this.num=product.getNum();
        this.proid=product.getId();
        this.title=product.getTitle();
        this.prodes=product.getProdes();
        this.originalPrice=product.getOriginalPrice();
        this.promotePrice=product.getPromotePrice();
        if(product.getOutnum()==null){
            this.outnum=0;
        }
        else{
            this.outnum=product.getOutnum();
        }
    }

    public void setImages(List<ProductImage> productImages){
        this.infimgpath=new ArrayList<String>();
        this.smaimgpath=new ArrayList<String>();
        this.detimgpath=new ArrayList<String>();
        for(ProductImage pi:productImages){
            if(pi.getType().equals("inf")){
                infimgpath.add(pi.getIpath());
            }
            else if(pi.getType().equals("detail")){
                detimgpath.add(pi.getIpath());
            }
            else if(pi.getType().equals("small")){
                smaimgpath.add(pi.getIpath());
            }
        }

        firstimg=infimgpath.get(0);
    }

    public int getProid() {
        return proid;
    }

    public void setProid(int proid) {
        this.proid = proid;
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

    public String getProdes() {
        return prodes;
    }

    public void setProdes(String prodes) {
        this.prodes = prodes;
    }

    public Calendar getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Calendar createtime) {
        this.createtime = createtime;
    }

    public double getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(double originalPrice) {
        this.originalPrice = originalPrice;
    }

    public double getPromotePrice() {
        return promotePrice;
    }

    public void setPromotePrice(double promotePrice) {
        this.promotePrice = promotePrice;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public List<String> getInfimgpath() {
        return infimgpath;
    }

    public void setInfimgpath(List<String> infimgpath) {
        this.infimgpath = infimgpath;
    }

    public List<String> getSmaimgpath() {
        return smaimgpath;
    }

    public void setSmaimgpath(List<String> smaimgpath) {
        this.smaimgpath = smaimgpath;
    }

    public List<String> getDetimgpath() {
        return detimgpath;
    }

    public void setDetimgpath(List<String> detimgpath) {
        this.detimgpath = detimgpath;
    }

    public String getFirstimg() {
        return firstimg;
    }

    public void setFirstimg(String firstimg) {
        this.firstimg = firstimg;
    }

    public int getReviewnum() {
        return reviewnum;
    }

    public void setReviewnum(int reviewnum) {
        this.reviewnum = reviewnum;
    }

    public int getOutnum() {
        return outnum;
    }

    public void setOutnum(int outnum) {
        this.outnum = outnum;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public Map<String, String> getPropertyValues() {
        return propertyValues;
    }

    public void setPropertyValues(Map<String, String> propertyValues) {
        this.propertyValues = propertyValues;
    }
}
