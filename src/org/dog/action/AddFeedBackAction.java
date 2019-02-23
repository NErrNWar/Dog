package org.dog.action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.SessionAware;
import org.dog.Iservice.IUserService;
import org.dog.entity.FeedBack;
import org.dog.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

@Controller("addFeedBackAction")
@Scope("prototype")
public class AddFeedBackAction extends ActionSupport implements SessionAware {
    private String content;
    @Autowired
    private IUserService userService;
    private Map<String,Object> session;

    private Map<String,String> result;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public IUserService getUserService() {
        return userService;
    }

    public void setUserService(IUserService userService) {
        this.userService = userService;
    }

    public Map<String, String> getResult() {
        return result;
    }

    public void setResult(Map<String, String> result) {
        this.result = result;
    }

    @Override
    public void setSession(Map<String, Object> map) {
        session=map;
    }

    public String execute(){

        result=new HashMap<>();
        try {
            User u = (User) session.get("user");

            User ru = null;
            if (u == null) {
                ru = userService.getUserByName("admin");
            } else {
                ru = userService.getUserById(u.getId());
            }

            FeedBack feedBack = new FeedBack();
            feedBack.setCreateTime(Calendar.getInstance());
            feedBack.setUser(ru);
            feedBack.setContent(content);

            userService.addFeedBack(feedBack, ru);

            result.put("result","success");
            result.put("message","");
        }catch (Exception e){
            result.put("result","error");
            result.put("message",e.getMessage());
        }

        return SUCCESS;
    }
}
