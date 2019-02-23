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

@Controller("updateUserAction")
@Scope("prototype")
public class UpdateUserAction extends ActionSupport implements SessionAware {
    @Autowired
    private IUserService userService;

    private String username;
    private String sex;
    private String birth;
    private String address;
    private String tel;

    private Map<String,Object> session;

    private Map<String,String> result;

    public Map<String, String> getResult() {
        return result;
    }

    public void setResult(Map<String, String> result) {
        this.result = result;
    }

    public IUserService getUserService() {
        return userService;
    }

    public void setUserService(IUserService userService) {
        this.userService = userService;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String execute(){
        System.out.println("/////////////"+getUsername());
        System.out.println("/////////////"+getAddress());
        System.out.println("//////////"+getBirth());
        System.out.println("///////////"+getSex());
        System.out.println("//////////"+getTel());

        result = new HashMap<>();
        try {
            try {
                int year = Integer.parseInt(birth.split("-")[0]);
                int mon = Integer.parseInt(birth.split("-")[1]);
                int day = Integer.parseInt(birth.split("-")[2]);
            }catch(Exception e){
                result.put("result","error");
                result.put("message","请正确填写年份");
            }
            User u = (User) session.get("user");

            User ru = getUserService().getUserById(u.getId());

            ru.setUsername(getUsername());
            ru.setTel(getTel());
            ru.setAdress(getAddress());
            ru.setSex("男".equals(sex) ? "man" : "woman");


            getUserService().updateUser(ru);

            session.put("user", ru);

            ru.setPassword("");

            result.put("result", "success");
            result.put("message", "");
        }catch(Exception e){
            result.put("result", "error");
            result.put("message", e.getMessage());
        }

        return SUCCESS;
    }

    @Override
    public void setSession(Map<String, Object> map) {
        session=map;
    }
}
