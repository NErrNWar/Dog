package org.dog.action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.SessionAware;
import org.dog.Iservice.IOrderService;
import org.dog.Iservice.IProductService;
import org.dog.Iservice.IUserService;
import org.dog.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller("addReviewAction")
@Scope("prototype")
public class AddReviewAction extends ActionSupport implements SessionAware {
    @Autowired
    private IOrderService orderService;
    @Autowired
    private IUserService userService;
    @Autowired
    private IProductService productService;

    private int oiid;
    private String content;

    private Map<String,Object> session;
    private Map<String,String> result;

    public IOrderService getOrderService() {
        return orderService;
    }

    public void setOrderService(IOrderService orderService) {
        this.orderService = orderService;
    }

    public int getOiid() {
        return oiid;
    }

    public void setOiid(int oiid) {
        this.oiid = oiid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Map<String, String> getResult() {
        return result;
    }

    public void setResult(Map<String, String> result) {
        this.result = result;
    }

    public IUserService getUserService() {
        return userService;
    }

    public void setUserService(IUserService userService) {
        this.userService = userService;
    }

    public IProductService getProductService() {
        return productService;
    }

    public void setProductService(IProductService productService) {
        this.productService = productService;
    }

    public String execute(){
        result=new HashMap<>();
        try {
            Review r = new Review();
            User u = (User) session.get("user");;
            OrderItem orderItem = orderService.getOrderItemById(oiid);

            r.setContent(getContent());
            r.setCreateTime(Calendar.getInstance());
            r.setProduct(orderItem.getProduct());

            orderItem.setType("reviewed");
            userService.addReview(r,u);
            getOrderService().updateOrderItem(orderItem);

            List<OrderItem> orderItemList=orderService.getOrderItem(orderItem.getOrder());
            boolean falg=true;
            for(OrderItem oitem:orderItemList){
                if(!"reviewed".equals(oitem.getType())){
                    falg=false;
                }
            }

            if(falg){
                Order o=orderItem.getOrder();
                o.setType("");
                orderService.updateOrder(o);
            }

            result.put("result","success");
            result.put("message","");

        }catch (Exception e){
            e.printStackTrace();
            result.put("result","error");
            result.put("message",e.getMessage());
        }

        return SUCCESS;
    }

    @Override
    public void setSession(Map<String, Object> map) {
        session=map;
    }
}
