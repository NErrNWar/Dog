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
import org.dog.vo.OrderBean;
import org.dog.vo.OrderItemBean;
import org.dog.vo.ProductBean;
import org.dog.vo.ShopCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.util.*;

@Controller("createOrderAction")
@Scope("prototype")
public class CreateOrderAction extends ActionSupport implements RequestAware, SessionAware {
    private String orderparam;
    private String receiver;
    private String tel;
    private String address;
    @Autowired
    private IProductService productService;
    @Autowired
    private IUserService userService;
    private Map<String,Object> request;
    private Map<String,Object> session;

    public String getOrderparam() {
        return orderparam;
    }

    public void setOrderparam(String orderparam) {
        this.orderparam = orderparam;
    }

    public IProductService getProductService() {
        return productService;
    }

    public void setProductService(IProductService productService) {
        this.productService = productService;
    }

    public IUserService getUserService() {
        return userService;
    }

    public void setUserService(IUserService userService) {
        this.userService = userService;
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



    public String execute(){
        System.out.println("reci-----------"+getReceiver());
        System.out.println("tel+++++++++++++++"+getTel());
        System.out.println("adre*********"+getAddress());
        System.out.println("param=-=-=-=-=-=-="+getOrderparam());

        List<OrderItemBean> orderItemBeanList=new ArrayList<>();

        double count=0;
        Order order=new Order();
        ShopCart cart=(ShopCart)session.get("shopCart");
        if(cart==null){
            cart=new ShopCart();
        }

        order.setType("unpay");
        User u=(User)session.get("user");
        User ru=userService.checkUser(u.getUsername(),u.getPassword());
        order.setUser(ru);

        String[] params=orderparam.split(",");
        for(int i=0;i<params.length;i++){
            if(params[i]==null||params[i]==""){
                continue;
            }
            OrderItem orderItem=new OrderItem();
            int pid=Integer.parseInt((params[i].split(":"))[0]);

            Product product=productService.getProductById(pid);
            orderItem.setProduct(product);

            int num=Integer.parseInt((params[i].split(":"))[1]);
            orderItem.setNum(num);
            orderItem.setOrder(order);
            orderItem.setPrice(orderItem.getProduct().getPromotePrice()*num);

            OrderItemBean orderItemBean=new OrderItemBean(orderItem);
            orderItemBean.setProductBean(new ProductBean(product));
            orderItemBeanList.add(orderItemBean);

            order.getOrderItems().add(orderItem);

            count+=product.getPromotePrice()*num;

            //从购物车中删除相应货物
            List<OrderItemBean> cartbeans=cart.getItems();
            if(cartbeans!=null){
                Iterator<OrderItemBean> iterator=cartbeans.iterator();
                while(iterator.hasNext()){
                    OrderItemBean obean=iterator.next();
                    if(obean.getProductBean().getProid()==pid){
                        iterator.remove();
                    }
                }
            }
        }

        Iterator<OrderItemBean> iterator=cart.getItems().iterator();
        while(iterator.hasNext()){
            OrderItemBean obean=iterator.next();
            System.out.println("+++++++++++"+obean.getProductBean().getName());
        }


        Calendar c=Calendar.getInstance();
        String orno="";
        orno+=c.get(Calendar.YEAR);
        orno+=c.get(Calendar.MONTH);
        orno+=c.get(Calendar.DAY_OF_MONTH);
        orno+=c.getTimeInMillis();

        order.setPrice(count);
        order.setOrdnum(orno);
        order.setReceiver(getReceiver());
        order.setAddress(getAddress());
        order.setTel(getTel());

        getProductService().saveOrder(order);
        getProductService().saveOrder(u,cart);

        request.put("proitems",orderItemBeanList);
        request.put("orderno",order.getOrdnum());
        request.put("ordercount",order.getPrice());
        request.put("address",order.getAddress());
        request.put("orderid",order.getId());

        session.put("shopCart",cart);

        return SUCCESS;
    }

    @Override
    public void setRequest(Map<String, Object> map) {
        request=map;
    }

    @Override
    public void setSession(Map<String, Object> map) {
        session=map;
    }
}
