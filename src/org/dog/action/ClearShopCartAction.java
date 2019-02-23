package org.dog.action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;
import org.dog.Iservice.IProductService;
import org.dog.Iservice.IUserService;
import org.dog.entity.Order;
import org.dog.entity.OrderItem;
import org.dog.entity.Product;
import org.dog.entity.User;
import org.dog.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.util.*;

@Controller("clearShopCartAction")
@Scope("prototype")
public class ClearShopCartAction extends ActionSupport implements RequestAware, SessionAware {
    @Autowired
    private IProductService productService;
    @Autowired
    private IUserService userService;
    private String param;
    private Map<String,Object> request;
    private Map<String,Object> session;

    public IProductService getProductService() {
        return productService;
    }

    public void setProductService(IProductService productService) {
        this.productService = productService;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public IUserService getUserService() {
        return userService;
    }

    public void setUserService(IUserService userService) {
        this.userService = userService;
    }

    @Override
    public void setRequest(Map<String, Object> map) {
        request=map;
    }

    public String execute(){
        User u=(User)session.get("user");
        String[] params=param.split(",");
        OrderBean orderBean=new OrderBean();
        double count=0;
        List<OrderItemBean> orderItemBeans=new ArrayList<>();
        for(int i=0;i<params.length;i++){
            if(params[i]==null||params[i]==""){
                continue;
            }
            int proid= Integer.parseInt(params[i].split(":")[0]);
            Product p=getProductService().getProductById(proid);
            ProductBean productBean=new ProductBean(p);
            productBean.setFirstimg(getProductService().getFirstProductImage(p).getIpath());

            OrderItemBean orderItemBean=new OrderItemBean();
            orderItemBean.setProductBean(productBean);
            int nu=Integer.parseInt(params[i].split(":")[1]);
            orderItemBean.setNum(nu);
            orderItemBean.setPrice(nu*productBean.getPromotePrice());
            orderItemBeans.add(orderItemBean);
            count+=nu*productBean.getPromotePrice();
        }

        orderBean.setPrice(count);
        orderBean.setOrderItemBeans(orderItemBeans);
        request.put("createorder",orderBean);
        request.put("orderparam",param);

        return SUCCESS;
    }

    @Override
    public void setSession(Map<String, Object> map) {
        session=map;
    }
}
