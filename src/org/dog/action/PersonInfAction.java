package org.dog.action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;
import org.dog.Iservice.IOrderService;
import org.dog.Iservice.IProductService;
import org.dog.Iservice.IUserService;
import org.dog.entity.Order;
import org.dog.entity.OrderItem;
import org.dog.entity.Product;
import org.dog.entity.User;
import org.dog.vo.OrderBean;
import org.dog.vo.OrderItemBean;
import org.dog.vo.ProductBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.util.*;

@Controller("personinfAction")
@Scope("prototype")
public class PersonInfAction extends ActionSupport implements RequestAware, SessionAware {
    @Autowired
    private IProductService productService;
    @Autowired
    private IUserService userService;
    @Autowired
    private IOrderService orderService;

    private Map<String,Object> request;
    private Map<String,Object> session;

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

    public IOrderService getOrderService() {
        return orderService;
    }

    public void setOrderService(IOrderService orderService) {
        this.orderService = orderService;
    }

    public String execute(){
        User u= (User) session.get("user");
        Calendar c=u.getBirth();
        String birth=c.get(Calendar.YEAR)+"-"+(c.get(Calendar.MONTH)+1)+"-"+c.get(Calendar.DAY_OF_MONTH);

        List<OrderBean> orderBeanList=new ArrayList<>();
        List<OrderBean> up=new ArrayList<>();
        List<OrderBean> us=new ArrayList<>();
        List<OrderBean> ure=new ArrayList<>();
        List<OrderBean> urei=new ArrayList<>();
        List<Order> orders=getUserService().getAllOrder(u);

        System.out.println("user_____"+u.getUsername());
        System.out.println(orders.size());

        for(Order order:orders){
            System.out.println("order.id=-=-=-"+order.getId());

            OrderBean orderBean=new OrderBean(order);
            List<OrderItem> orderItemList=getOrderService().getOrderItem(order);
            for(OrderItem orderItem:orderItemList){

                System.out.println("orderitem.id=-=--------"+orderItem.getId());

                OrderItemBean orderItemBean=new OrderItemBean(orderItem);
                ProductBean productBean=new ProductBean(orderItem.getProduct());
                productBean.setFirstimg(getProductService().getFirstProductImage(orderItem.getProduct()).getIpath());
                orderItemBean.setProductBean(productBean);
                orderBean.getOrderItemBeans().add(orderItemBean);
            }
            orderBeanList.add(orderBean);
        }

        for(OrderBean orderBean:orderBeanList){
            System.out.println(orderBean.getId());

            orderBean.setSize(orderBean.getOrderItemBeans().size());

            if("unpay".equals(orderBean.getType())){
                up.add(orderBean);
            }
            else if("unsend".equals(orderBean.getType())){
                us.add(orderBean);
            }
            else if("unreceive".equals(orderBean.getType())){
                ure.add(orderBean);
            }
            else if("unreview".equals(orderBean.getType())){
                urei.add(orderBean);
            }
        }

        Iterator<OrderBean> iterator=orderBeanList.iterator();
        while(iterator.hasNext()){
            if("shopCart".equals(iterator.next().getType())){
                iterator.remove();
            }
        }

        request.put("allorder",orderBeanList);
        request.put("unpay",up);
        request.put("unsend",us);
        request.put("unreceive",ure);
        request.put("unreview",urei);
        request.put("birth",birth);

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
