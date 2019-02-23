package org.dog.action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.RequestAware;
import org.dog.Iservice.IProductService;
import org.dog.entity.Category;
import org.dog.entity.Product;
import org.dog.entity.PropertyValue;
import org.dog.vo.ProductBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller("searchByPropertyValueAction")
@Scope("prototype")
public class SearchByPropertyValueAction extends ActionSupport implements RequestAware {
    private Map<String,Object> request;
    @Autowired
    private IProductService productService;
    private String categoryName;
    private String value;

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public IProductService getProductService() {
        return productService;
    }

    public void setProductService(IProductService productService) {
        this.productService = productService;
    }

    public String execute(){
        Category c=new Category();
        PropertyValue pv=new PropertyValue();
        c.setName(getCategoryName());
        pv.setValue(value);
        List<Product> productList=getProductService().getProductByPropertyValue(c,pv);
        List<ProductBean> productBeanList=new ArrayList<>();
        for(Product product:productList){
            ProductBean bean=new ProductBean(product);
            bean.setFirstimg(getProductService().getFirstProductImage(product).getIpath());
            productBeanList.add(bean);
        }
        request.put("products",productBeanList);
        return SUCCESS;
    }

    @Override
    public void setRequest(Map<String, Object> map) {
        request=map;
    }
}
