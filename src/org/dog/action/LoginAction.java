package org.dog.action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.SessionAware;
import org.dog.Iservice.IUserService;
import org.dog.entity.User;
import org.dog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.util.Map;

@Controller("loginAction")
@Scope("prototype")
public class LoginAction extends ActionSupport implements SessionAware {
    private String username;
    private String password;
    private Map<String,Object> session;
    @Autowired
    private IUserService userService;

    private String error;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public IUserService getUserService() {
        return userService;
    }

    public void setUserService(IUserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute() throws Exception {
        User u=getUserService().checkUser(username,password);
        if(u==null){
            error="用户名或密码错误";
            return ERROR;
        }
        session.put("user",u);
        return SUCCESS;
    }

    @Override
    public void setSession(Map<String, Object> map) {
        this.session=map;
    }
}
