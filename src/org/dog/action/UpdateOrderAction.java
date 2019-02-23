package org.dog.action;

import com.opensymphony.xwork2.ActionSupport;
import org.dog.Iservice.IOrderService;
import org.dog.Iservice.IProductService;
import org.dog.entity.Order;
import org.dog.entity.OrderItem;
import org.dog.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller("updateOrderAction")
@Scope("prototype")
public class UpdateOrderAction extends ActionSupport {
    @Autowired
    private IOrderService orderService;
    @Autowired
    private IProductService productService;
    private int oid;
    private String type;

    private Map<String,String> result;

    public IOrderService getOrderService() {
        return orderService;
    }

    public void setOrderService(IOrderService orderService) {
        this.orderService = orderService;
    }

    public int getOid() {
        return oid;
    }

    public void setOid(int oid) {
        this.oid = oid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Map<String, String> getResult() {
        return result;
    }

    public void setResult(Map<String, String> result) {
        this.result = result;
    }

    public IProductService getProductService() {
        return productService;
    }

    public void setProductService(IProductService productService) {
        this.productService = productService;
    }

    public String execute(){

        System.out.println(getType());
        System.out.println(getOid());

        result =new HashMap<>();
        try {
            Order o = orderService.getOrderById(getOid());
            if (o != null) {
                o.setType(getType());

                if("unsend".equals(getType())){
                    Calendar calendar=Calendar.getInstance();
                    o.setBuyTime(calendar);
                    List<OrderItem> orderItems=orderService.getOrderItem(o);
                    for(OrderItem item:orderItems){
                        Product p=item.getProduct();
                        int num=(p.getOutnum()==null)?0:p.getOutnum();
                        p.setOutnum(num+item.getNum());
                        getProductService().updateProduct(p);
                    }
                }

                if("unreview".equals(getType())){
                    Calendar calendar=Calendar.getInstance();
                    o.setReceiveTime(calendar);
                    List<OrderItem> orderItems=orderService.getOrderItem(o);
                    for(OrderItem orderItem:orderItems){
                        orderItem.setType("unreview");
                    }
                }

                orderService.updateOrder(o);
            }

            result.put("result","success");
            result.put("message","");

        }catch(Exception e){
            e.printStackTrace();
            result.put("result","error");
            result.put("message",e.getMessage());
        }

        return SUCCESS;
    }
}
