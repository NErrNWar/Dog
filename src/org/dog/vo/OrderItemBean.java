package org.dog.vo;

import org.dog.entity.OrderItem;

import java.util.List;

public class OrderItemBean {
    private ProductBean productBean;
    private int num;
    private double price;
    private int id;
    private String type;

    public OrderItemBean(){}

    public OrderItemBean(OrderItem orderItem){
        this.id=(orderItem.getId()==null)?0:orderItem.getId();
        this.num=(orderItem.getNum()==null)?0:orderItem.getNum();
        this.price=(orderItem.getPrice()==null)?0:orderItem.getPrice();

        if(orderItem.getType()==null||orderItem.getType()==""){
            this.type="unreview";
        }
        else{
            this.type=orderItem.getType();
        }

    }

    public ProductBean getProductBean() {
        return productBean;
    }

    public void setProductBean(ProductBean productBean) {
        this.productBean = productBean;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
