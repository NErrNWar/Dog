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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller("searchAction")
@Scope("prototype")
public class SearchAction  extends ActionSupport implements RequestAware {
    @Autowired
    private IProductService productService;
    private Map<String,Object> request;
    private String keyword;

    private Map<String,Object> result;

    private int pageno;

    public int getPageno() {
        return pageno;
    }

    public void setPageno(int pageno) {
        this.pageno = pageno;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
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
        result=new HashMap<>();
        if(getPageno()==0){
            setPageno(1);
        }

        System.out.println("---------------------"+pageno);

        try {
            String key = "%" + getKeyword() + "%";
            List<Product> productList = getProductService().searchByPage(key, pageno, 20);
            List<ProductBean> productBeanList = new ArrayList<ProductBean>();
            long count = getProductService().getSearchCount(keyword);

            long c1=count/20;
            double c2=count/20.0;
            if(c2-c1>0){
                c1++;
            }

            ProductBean pb = null;
            for (Product p : productList) {
                pb = new ProductBean(p);
                pb.setReviewnum(getProductService().getReviewnum(p));
                pb.setFirstimg(getProductService().getFirstProductImage(p).getIpath());
                productBeanList.add(pb);
            }

            result.put("result","success");
            result.put("count",c1);
            result.put("inf", productBeanList);
        }catch (Exception e){
            e.printStackTrace();

            result.put("result","error");
            result.put("message",e.getMessage());
        }

        return SUCCESS;
    }

    @Override
    public void setRequest(Map<String, Object> map) {
        request=map;
    }
}
