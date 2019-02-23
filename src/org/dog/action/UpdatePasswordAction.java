package org.dog.action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.SessionAware;
import org.dog.Iservice.IUserService;
import org.dog.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.util.HashMap;
import java.util.Map;

@Controller("updatePasswordAction")
@Scope("prototype")
public class UpdatePasswordAction extends ActionSupport implements SessionAware {
    @Autowired
    private IUserService userService;
    private String ypassword;
    private String npassword;

    private Map<String,Object> session;

    private Map<String,String> result;

    public IUserService getUserService() {
        return userService;
    }

    public void setUserService(IUserService userService) {
        this.userService = userService;
    }

    public String getYpassword() {
        return ypassword;
    }

    public void setYpassword(String ypassword) {
        this.ypassword = ypassword;
    }

    public String getNpassword() {
        return npassword;
    }

    public void setNpassword(String npassword) {
        this.npassword = npassword;
    }

    public Map<String, String> getResult() {
        return result;
    }

    public void setResult(Map<String, String> result) {
        this.result = result;
    }

    public String execute(){

        System.out.println("-=-=-=-=-==-=-=ypassword"+getYpassword());
        System.out.println("=*=*==*=*=*=*=*=*=*npassword"+getNpassword());

        result=new HashMap<String,String>();
        User u=(User)session.get("user");
        User ru=getUserService().getUserById(u.getId());

        System.out.println(ru);
        System.out.println(ru.getPassword());

        try{
            if(ru.getPassword().equals(getYpassword())){
                ru.setPassword(getNpassword());
                getUserService().updateUser(ru);
                session.put("user",ru);

                ru.setPassword("");
                result.put("result","success");
                result.put("message","");
            }
            else{
                result.put("result","error");
                result.put("message","密码不正确！");
            }

        }
        catch(Exception e){
            e.printStackTrace();
            result.put("result","error");
            result.put("message",e.getMessage());
        }



        return SUCCESS;
    }

    @Override
    public void setSession(Map<String, Object> map) {
        session=map;
    }
}
