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

@Controller("logoueAction")
@Scope("prototype")
public class LogoutAction extends ActionSupport implements SessionAware {
    @Autowired
    private IProductService productService;
    private Map<String,Object> session;

    public IProductService getProductService() {
        return productService;
    }

    public void setProductService(IProductService productService) {
        this.productService = productService;
    }

    public String execute(){
        /**
         * 保存用户信息
         */

        ShopCart cart=(ShopCart)session.get("shopCart");
        if(cart!=null){
            User u=(User)session.get("user");
            productService.saveOrder(u,cart);
        }

        /**
         * 清空用户信息
         */
        session.put("shopCart",null);
        session.put("user",null);

        return SUCCESS;

    }

    @Override
    public void setSession(Map<String, Object> map) {
        this.session=map;
    }
}
