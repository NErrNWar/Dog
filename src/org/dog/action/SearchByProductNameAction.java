package org.dog.action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.RequestAware;
import org.dog.Iservice.IProductService;
import org.dog.entity.Product;
import org.dog.vo.ProductBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller("searchByProductNameAction")
@Scope("prototype")
public class SearchByProductNameAction extends ActionSupport implements RequestAware {
    @Autowired
    private IProductService productService;
    private Map<String,Object> request;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public IProductService getProductService() {
        return productService;
    }

    public void setProductService(IProductService productService) {
        this.productService = productService;
    }

    @Override
    public void setRequest(Map<String, Object> map) {
        request=map;
    }

    public String execute(){
        List<Product> productList=getProductService().getProductByName(name);
        List<ProductBean> productBeanList=new ArrayList<>();
        for(Product p:productList){
            ProductBean bean=new ProductBean(p);
            bean.setFirstimg(getProductService().getFirstProductImage(p).getIpath());
            productBeanList.add(bean);
        }

        request.put("products",productBeanList);
        return SUCCESS;
    }
}
