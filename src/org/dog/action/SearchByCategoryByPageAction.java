package org.dog.action;

import com.opensymphony.xwork2.ActionSupport;
import org.dog.Iservice.IProductService;
import org.dog.entity.Category;
import org.dog.entity.Product;
import org.dog.vo.ProductBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller("searchByCategoryByPageAction")
@Scope("prototype")
public class SearchByCategoryByPageAction extends ActionSupport {
    private String cat;
    private int page;

    @Autowired
    private IProductService productService;

    private Map<String,Object> result;

    public String getCat() {
        return cat;
    }

    public void setCat(String cat) {
        this.cat = cat;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public IProductService getProductService() {
        return productService;
    }

    public void setProductService(IProductService productService) {
        this.productService = productService;
    }

    public Map<String, Object> getResult() {
        return result;
    }

    public void setResult(Map<String, Object> result) {
        this.result = result;
    }

    public String execute(){

        System.out.println();

        result=new HashMap<>();

        try {
            Category c = new Category();
            c.setName(cat);
            List<Product> productList = getProductService().getProductByCategoryByPage(c, page, 20);
            List<ProductBean> productBeanList=new ArrayList<>();

            for(Product p:productList){

                ProductBean bean=new ProductBean(p);
                bean.setFirstimg(getProductService().getFirstProductImage(p).getIpath());
                productBeanList.add(bean);
            }

            Category ccc=new Category();
            ccc.setId(7);
            Product testp=getProductService().getProductById(178);
            Category testc=getProductService().getProductById(178).getCategory();
            System.out.println(testp.getName()+"______________________:"+testc.getName());
            System.out.println(getProductService().getProductByCategory(ccc).size());

            long c1=getProductService().getSearchCount(cat)/20;
            double c2=getProductService().getSearchCount(cat)/20.0;
            if(c2-c1>0){
                c1++;
            }
            result.put("result","success");
            result.put("count",c1);
            result.put("inf",productBeanList);

            System.out.println(getProductService().getSearchCount(cat));
            System.out.println("11111111111111111111:"+productBeanList.size());
            System.out.println(productBeanList);

        }catch(Exception e){
            e.printStackTrace();
            result.put("result","error");
            result.put("message",e.getMessage());

            System.out.println("message+++++++::"+e.getMessage());

        }
        return SUCCESS;
    }
}
