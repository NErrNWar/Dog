package org.dog.action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.SessionAware;
import org.dog.Iservice.IProductService;
import org.dog.entity.User;
import org.dog.vo.ShopCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.util.Map;

@Controller("shopcartAction")
@Scope("prototype")
public class ShopCartAction extends ActionSupport implements SessionAware {
    private Map<String,Object> session;
    @Autowired
    private IProductService productService;

    public IProductService getProductService() {
        return productService;
    }

    public void setProductService(IProductService productService) {
        this.productService = productService;
    }

    public String execute(){
        ShopCart cart=(ShopCart)session.get("shopCart");
        if(cart==null){
            User u=(User)session.get("user");
            cart=productService.getShopCart(u);
            if(cart==null){
                cart=new ShopCart();
            }
            session.put("shopCart",cart);
        }
        return SUCCESS;
    }

    @Override
    public void setSession(Map<String, Object> map) {
        session=map;
    }
}
