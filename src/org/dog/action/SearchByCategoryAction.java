package org.dog.action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.RequestAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.util.Map;

@Controller("searchByCategoryAction")
@Scope("prototype")
public class SearchByCategoryAction extends ActionSupport implements RequestAware {
    private String keyword;

    private Map<String,Object> request;

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void setRequest(Map<String, Object> map) {
        request=map;
    }

    public String execute(){
        System.out.println("cattpe..............."+getKeyword());

        request.put("cattype",keyword);

        return SUCCESS;
    }
}
