package org.dog.vo;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

public class ShopCart {
    private int id;
    private List<OrderItemBean> items;
    private Calendar createTime;
    private double price;
    private int ordno;
    private int uid;

    public ShopCart(){
        items=new ArrayList<OrderItemBean>();
    }

    public void add(ProductBean productBean,int num){
        boolean flag=false;
        for(OrderItemBean orderItemBean:items){
            if(orderItemBean.getProductBean().getProid()==productBean.getProid()){
                orderItemBean.setNum(orderItemBean.getNum()+num);
                flag=true;
                orderItemBean.setPrice(orderItemBean.getPrice()+num*productBean.getPromotePrice());
            }
        }
        if(!flag){
            OrderItemBean bean=new OrderItemBean();
            bean.setNum(num);
            bean.setProductBean(productBean);
            bean.setPrice(num*productBean.getPromotePrice());
            items.add(bean);
        }
    }

    public void delete(ProductBean productBean,int num){

        Iterator<OrderItemBean> iterator=items.iterator();
        while(iterator.hasNext()){
            OrderItemBean bean=iterator.next();
            if(productBean.getProid()==bean.getProductBean().getProid()) {
                int n = bean.getNum();
                if (n - num > 0) {
                    bean.setNum(n - num);
                } else {
                    iterator.remove();
                }
            }
        }
    }

    public void remove(int prid){
        Iterator<OrderItemBean> iterator=items.iterator();
        while(iterator.hasNext()){
            if(iterator.next().getProductBean().getProid()==prid){
                iterator.remove();
            }
        }
    }

    public List<OrderItemBean> getItems() {
        return items;
    }

    public void setItems(List<OrderItemBean> items) {
        this.items = items;
    }

    public Calendar getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Calendar createTime) {
        this.createTime = createTime;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getOrdno() {
        return ordno;
    }

    public void setOrdno(int ordno) {
        this.ordno = ordno;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
