package org.dog.vo;

import org.dog.entity.Order;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class OrderBean {
    private List<OrderItemBean> orderItemBeans;
    private double price;
    private int id;
    private String buytime;
    private String receivetime;
    private String orno;
    private String type;
    private int size;

    public OrderBean(){}

    public OrderBean(Order order){
        this.id=order.getId();
        this.price=order.getPrice();
        this.type=order.getType();
        this.orno=order.getOrdnum();
        orderItemBeans=new ArrayList<>();

        Calendar b=order.getBuyTime();
        Calendar r=order.getReceiveTime();

        StringBuilder builder=new StringBuilder();
        if(b!=null){
            builder.append(b.get(Calendar.YEAR)+"-");
            builder.append((b.get(Calendar.MONTH)+1)+"-");
            builder.append(b.get(Calendar.DAY_OF_MONTH));
            buytime=builder.toString();
        }

        StringBuilder b2=new StringBuilder();
        if(r!=null){
            b2.append(r.get(Calendar.YEAR)+"-");
            b2.append((r.get(Calendar.MONTH)+1)+"-");
            b2.append(r.get(Calendar.DAY_OF_MONTH));
            receivetime=b2.toString();
        }

    }


    public List<OrderItemBean> getOrderItemBeans() {
        return orderItemBeans;
    }

    public void setOrderItemBeans(List<OrderItemBean> orderItemBeans) {
        this.orderItemBeans = orderItemBeans;
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

    public String getOrno() {
        return orno;
    }

    public void setOrno(String orno) {
        this.orno = orno;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
