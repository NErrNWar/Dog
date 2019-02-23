package org.dog.action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.RequestAware;
import org.dog.Iservice.IProductService;
import org.dog.entity.Category;
import org.dog.entity.Product;
import org.dog.vo.ProductBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller("initAction")
@Scope("prototype")
public class InitAction extends ActionSupport implements RequestAware {
    @Autowired
    private IProductService productService;

    private Map<String,Object> request;

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
        List<Category> categories=getProductService().getAllCategory();

        List<Product> hot=getProductService().getHotProduct();
        List<Product> ne=getProductService().getNewProduct();
        List<Product> li=getProductService().getLifeProduct();
        List<Product> el=getProductService().getElecProduct();
        List<Product> fr=getProductService().getFruitProduct();

        List<ProductBean> hotproduct=new ArrayList<>();
        List<ProductBean> newproduct=new ArrayList<>();
        List<ProductBean> lifeproduct=new ArrayList<>();
        List<ProductBean> elcproduct=new ArrayList<>();
        List<ProductBean> fruproduct=new ArrayList<>();

        for(Product product:hot){
            ProductBean productBean=new ProductBean(product);
            productBean.setFirstimg(getProductService().getFirstProductImage(product).getIpath());
            hotproduct.add(productBean);
        }

        for(Product product:ne){
            ProductBean productBean=new ProductBean(product);
            productBean.setFirstimg(getProductService().getFirstProductImage(product).getIpath());
            newproduct.add(productBean);
        }

        for(Product product:li){
            ProductBean productBean=new ProductBean(product);
            productBean.setFirstimg(getProductService().getFirstProductImage(product).getIpath());
            lifeproduct.add(productBean);
        }

        for(Product product:el){
            ProductBean productBean=new ProductBean(product);
            productBean.setFirstimg(getProductService().getFirstProductImage(product).getIpath());
            elcproduct.add(productBean);
        }

        for(Product product:fr){
            ProductBean productBean=new ProductBean(product);
            productBean.setFirstimg(getProductService().getFirstProductImage(product).getIpath());
            fruproduct.add(productBean);
        }

        request.put("category",categories);
        request.put("hotproduct",hotproduct);
        request.put("newproduct",newproduct);
        request.put("lifeproduct",lifeproduct);
        request.put("elcproduct",elcproduct);
        request.put("fruproduct",fruproduct);

        return SUCCESS;
    }
}
