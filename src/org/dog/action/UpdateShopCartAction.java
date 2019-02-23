package org.dog.action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.SessionAware;
import org.dog.Iservice.IProductService;
import org.dog.entity.User;
import org.dog.vo.OrderItemBean;
import org.dog.vo.ShopCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@Controller("updateShopCartAction")
@Scope("prototype")
public class UpdateShopCartAction extends ActionSupport implements SessionAware {
    private int pid;
    private int num;
    @Autowired
    private IProductService productService;
    private Map<String,Object> session;
    private Map<String,String> result;

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

    public Map<String, String> getResult() {
        return result;
    }

    public void setResult(Map<String, String> result) {
        this.result = result;
    }

    @Override
    public void setSession(Map<String, Object> map) {
        session=map;
    }

    public String execute(){
        result=new HashMap<String,String>();
        User u=(User)session.get("user");
        if(u==null){
            result.put("result","error");
            result.put("message","用户名为空");
            return SUCCESS;
        }
        ShopCart cart=(ShopCart)session.get("shopCart");
        if(cart==null){
            cart=new ShopCart();
        }

        double count=0;
        Iterator<OrderItemBean> itemBeans=cart.getItems().iterator();
        while(itemBeans.hasNext()){
            OrderItemBean bean=itemBeans.next();
            if(bean.getProductBean().getProid()==pid){
                if(num<=0){
                    itemBeans.remove();
                }
                else{
                    bean.setNum(num);
                    bean.setPrice(num*bean.getProductBean().getPromotePrice());
                    count+=bean.getPrice();
                }
            }
        }

        cart.setPrice(count);
        getProductService().saveOrder(u,cart);

        result.put("result","success");
        result.put("message","");

        return SUCCESS;
    }
}
