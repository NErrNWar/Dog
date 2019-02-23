package org.dog.action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;
import org.dog.Iservice.IProductService;
import org.dog.Iservice.IUserService;
import org.dog.entity.Product;
import org.dog.entity.Review;
import org.dog.entity.User;
import org.dog.vo.ProductBean;
import org.dog.vo.ReviewBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller("searchByIdAction")
@Scope("prototype")
public class SearchByIdAction extends ActionSupport implements RequestAware, SessionAware {
    private Map<String,Object> request;
    private Map<String,Object> session;
    private int pid;
    @Autowired
    private IProductService productService;
    @Autowired
    private IUserService userService;

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public IProductService getProductService() {
        return productService;
    }

    public void setProductService(IProductService productService) {
        this.productService = productService;
    }

    public IUserService getUserService() {
        return userService;
    }

    public void setUserService(IUserService userService) {
        this.userService = userService;
    }

    public String execute(){
        Product product=getProductService().getProductById(pid);

        System.out.println(product.getOutnum());

        ProductBean bean=new ProductBean(product);
        bean.setImages(getProductService().getProductImage(product));
        bean.setReviewnum(getProductService().getReviewnum(product));
        bean.setPropertyValues(getProductService().getPropertyValue(product));
        request.put("product",bean);

        List<Review> reviews=getProductService().getReViewByProduct(product);
        List<ReviewBean> reviewBeans=new ArrayList<>();
        for(Review r:reviews){
            reviewBeans.add(new ReviewBean(r));
        }

        request.put("reviews",reviewBeans);

        String isfavor="no";
        User u=(User)session.get("user");
        if(u!=null) {
            List<Product> favor = getUserService().getFavorite(u);
            for (Product p : favor) {
                System.out.println(p.getId());
                if (p.getId() == pid) {
                    isfavor = "yes";
                }
            }

        }
        request.put("isfavor",isfavor);

        System.out.println(bean.getSmaimgpath());
        System.out.println(bean.getInfimgpath());
        System.out.println(bean.getDetimgpath());

        return SUCCESS;
    }

    @Override
    public void setRequest(Map<String, Object> map) {
        request=map;
    }

    @Override
    public void setSession(Map<String, Object> map) {
        session=map;
    }
}
