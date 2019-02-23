package org.dog.action;

import com.opensymphony.xwork2.ActionSupport;
import org.dog.Iservice.IUserService;
import org.dog.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.util.HashMap;
import java.util.Map;

@Controller("forgetPasswordAction")
@Scope("prototype")
public class ForgetPasswordAction extends ActionSupport {
    @Autowired
    private IUserService userService;
    private String npassword;
    private String account;

    private Map<String,String> result;

    public IUserService getUserService() {
        return userService;
    }

    public void setUserService(IUserService userService) {
        this.userService = userService;
    }

    public String getNpassword() {
        return npassword;
    }

    public void setNpassword(String npassword) {
        this.npassword = npassword;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public Map<String, String> getResult() {
        return result;
    }

    public void setResult(Map<String, String> result) {
        this.result = result;
    }

    public String execute(){
        result=new HashMap<>();
        try {
            User u = getUserService().getUserByAccount(getAccount());

            u.setPassword(getNpassword());
            userService.updateUser(u);

            result.put("result","success");
            result.put("message","");

        }catch(Exception e){
            result.put("result","error");
            result.put("message",e.getMessage());
        }

        return SUCCESS;
    }

}
