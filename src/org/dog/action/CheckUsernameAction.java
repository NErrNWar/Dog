package org.dog.action;

import com.opensymphony.xwork2.ActionSupport;
import org.dog.Iservice.IUserService;
import org.dog.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.util.*;

@Controller("checkUsernameAction")
@Scope("prototype")
public class CheckUsernameAction extends ActionSupport {
    private String username;
    @Autowired
    private IUserService userService;
    private Map<String,String> map;

    public String execute(){
        map=new HashMap<String,String>();
        boolean flag=userService.checkUserIfExitByUsername(getUsername());
        if(flag){
            map.put("result","error");
            map.put("message","用户名已存在");
        }
        else{
            map.put("result","success");
            map.put("message","成功");
        }
        return SUCCESS;
    }

    public Map<String, String> getMap() {
        return map;
    }

    public void setMap(Map<String, String> map) {
        this.map = map;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public IUserService getUserService() {
        return userService;
    }

    public void setUserService(IUserService userService) {
        this.userService = userService;
    }
}
