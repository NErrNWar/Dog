package org.dog.action;

import com.opensymphony.xwork2.ActionSupport;
import org.dog.Iservice.IProductService;
import org.dog.entity.Category;
import org.dog.entity.Product;
import org.dog.entity.Property;
import org.dog.entity.PropertyValue;
import org.dog.vo.ProductBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.util.*;

@Controller("categorySearchAction")
@Scope("prototype")
public class CategorySearchAction extends ActionSupport {
    private String categoryName;
    @Autowired
    private IProductService productService;
    private Map<String,Object> result;

    public IProductService getProductService() {
        return productService;
    }

    public void setProductService(IProductService productService) {
        this.productService = productService;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Map<String, Object> getResult() {
        return result;
    }

    public void setResult(Map<String, Object> result) {
        this.result = result;
    }

    public String execute(){
        Category c=new Category();
        c.setName(categoryName);
        Property p=new Property();
        p.setName("品牌");
        List<PropertyValue> propertyValues=getProductService().getPropertyValue(c,p);
        List<Product> products=getProductService().getProductByCategoryByPage(c,1,20);

        //List<PropertyValueBean> propertyValueBeanList=new ArrayList<>();

        List<ProductBean> productList=new ArrayList<>();

        Set<String> propertyValueSet=new HashSet<String>();

        for(Product product:products){
            System.out.println(product);
            productList.add(new ProductBean(product));
        }

        for(PropertyValue propertyValue:propertyValues){
            propertyValueSet.add(propertyValue.getValue());
        }

        Set<String> productname=new TreeSet<>();
        for(Product pro:products){
            productname.add(pro.getName());
        }

        result=new HashMap<>();
        result.put("propertyValues",new ArrayList<>(propertyValueSet));
        //result.put("products",productList);
        result.put("products",productname);

        return SUCCESS;
    }
}
