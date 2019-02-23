package org.dog.action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.SessionAware;
import org.dog.Iservice.IProductService;
import org.dog.entity.OrderItem;
import org.dog.entity.Product;
import org.dog.entity.User;
import org.dog.vo.OrderItemBean;
import org.dog.vo.ProductBean;
import org.dog.vo.ShopCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.util.Map;

@Controller("addToShopCartAction")
@Scope("prototype")
public class AddToShopCartAction extends ActionSupport implements SessionAware {
    @Autowired
    private IProductService productService;
    private int pid;
    private int num;
    private Map<String,Object> session;
    private String error;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public IProductService getProductService() {
        return productService;
    }

    public void setProductService(IProductService productService) {
        this.productService = productService;
    }

    public String execute(){
        User u=(User)session.get("user");

        ShopCart cart= (ShopCart) session.get("shopCart");
        if(cart==null){
            cart=productService.getShopCart(u);
            if(cart==null){
                cart=new ShopCart();
            }
        }

        Product p=productService.getProductById(pid);
        ProductBean pbean=new ProductBean(p);
        pbean.setFirstimg(productService.getFirstProductImage(p).getIpath());

        cart.add(pbean,num);

        System.out.println("-------------------pid"+pid);
        System.out.println("------------------pbean"+pbean.getProid());

        for(int i=0;i<cart.getItems().size();i++){
            System.out.println("------------------"+cart.getItems().get(i).getProductBean().getProid());
        }

        double count=0;
        for(OrderItemBean orderItem:cart.getItems()){
            count+=orderItem.getPrice();
        }
        cart.setPrice(count);

        productService.saveOrder(u,cart);

        session.put("shopCart",cart);
        return SUCCESS;
    }

    @Override
    public void setSession(Map<String, Object> map) {
        session=map;
    }
}
