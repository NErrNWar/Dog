package org.dog.action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.SessionAware;
import org.dog.Iservice.IProductService;
import org.dog.Iservice.IUserService;
import org.dog.entity.Product;
import org.dog.entity.User;
import org.dog.vo.ProductBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller("getFavorbyPageAction")
@Scope("prototype")
public class GetFavorByPageAction extends ActionSupport implements SessionAware {
    private int page;
    @Autowired
    private IUserService userService;
    @Autowired
    private IProductService productService;
    private Map<String,Object> session;
    private Map<String,Object> result;

    public Map<String, Object> getResult() {
        return result;
    }

    public void setResult(Map<String, Object> result) {
        this.result = result;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
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

    @Override
    public void setSession(Map<String, Object> map) {
        session=map;
    }

    public String execute(){
        result=new HashMap<>();

        try {
            User u = (User) session.get("user");

            int size=getUserService().getFavorite(u).size();
            System.out.println("favor________________"+size);
            List<Product> faors =null;

            if(size>page*20-1) {
                faors = getUserService().getFavorite(u).subList((page - 1) * 20, (page) * 20 - 1);
            }
            else{
                faors=getUserService().getFavorite(u).subList((page-1)*20,size);
            }

            List<ProductBean> productBeanList = new ArrayList<>();

            for (Product p : faors) {
                ProductBean bean = new ProductBean(p);
                bean.setFirstimg(getProductService().getFirstProductImage(p).getIpath());
                productBeanList.add(bean);
            }

            result = new HashMap<String, Object>();
            result.put("count", getUserService().getFavorite(u).size() / 20 + 1);
            result.put("current", page);
            result.put("inf", productBeanList);

            result.put("result","success");
        }catch(Exception e){
            e.printStackTrace();
            result.put("result","error");
            result.put("message",e.getMessage());
        }

        return SUCCESS;
    }

}
