package org.dog.action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.SessionAware;
import org.dog.Iservice.IProductService;
import org.dog.Iservice.IUserService;
import org.dog.entity.Product;
import org.dog.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller("changeFavorAction")
@Scope("prototype")
public class ChangeFavorAction extends ActionSupport implements SessionAware {
    @Autowired
    private IUserService userService;
    @Autowired
    private IProductService productService;
    private int pid;
    private String type;
    private Map<String,String> result;
    private Map<String,Object> session;

    public IUserService getUserService() {
        return userService;
    }

    public void setUserService(IUserService userService) {
        this.userService = userService;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
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
        result=new HashMap<>();
        try {
            /*
            User u = (User) session.get("user");

            User relu = getUserService().getUserById(u.getId());

            List<Product> favorlist=

            getUserService().updateUser(relu);*/

            User u=(User)session.get("user");
            if(u!=null) {
                Product p = new Product();
                p.setId(pid);

                if ("add".equals(getType())) {
                    getUserService().addFavorite(u, p);
                } else if ("rm".equals(getType())) {
                    getUserService().removeFavorite(u, p);
                }
            }
            else{
                result.put("result","exception");
                result.put("message","还没登录");
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

    @Override
    public void setSession(Map<String, Object> map) {
        session=map;
    }
}
