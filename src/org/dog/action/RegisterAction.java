package org.dog.action;

import com.opensymphony.xwork2.ActionSupport;
import org.dog.Iservice.IUserService;
import org.dog.entity.User;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

@Controller("registerAction")
@Scope("prototype")
public class RegisterAction extends ActionSupport {
    private IUserService userService;

    private Map<String,String> result;

    private String account;
    private String username;
    private String password;
    private String sex;
    private String year;
    private String mon;
    private String day;
    private String adress;

    @ResponseBody
    public String execute(){
        User user=new User();
        user.setAccount(getAccount());
        user.setUsername(getUsername());
        user.setPassword(getPassword());
        user.setAdress(getAdress());
        user.setSex(getSex());

        if(getAccount().matches("^1((3[0-9])|(4[5|7])|(5([0-3]|[5-9]))|(8[0,5-9]))\\d{8}$")){
            user.setTel(getAccount());
        }

        Calendar calendar=Calendar.getInstance();
        int y=Integer.valueOf(getYear());
        int m=Integer.valueOf(getMon());
        int d=Integer.valueOf(getDay());
        calendar.set(Calendar.YEAR,y);
        calendar.set(Calendar.MONTH,m-1);
        calendar.set(Calendar.DAY_OF_MONTH,d);
        user.setBirth(calendar);
        result=new HashMap<>();
        boolean flag=getUserService().registUser(user);
        if(flag){
            result.put("result","success");
            result.put("message","注册成功!");
        }else{
            result.put("result","error");
            result.put("message","注册失败，请检查各参数是否正确！");
        }

        System.out.println(getAccount());
        System.out.println(getUsername());
        System.out.println(getPassword());
        System.out.println(getSex());
        System.out.println(getAdress());
        System.out.println(getYear());
        System.out.println(getMon());
        System.out.println(getDay());
        return SUCCESS;
    }

    public IUserService getUserService() {
        return userService;
    }

    public void setUserService(IUserService userService) {
        this.userService = userService;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

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

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMon() {
        return mon;
    }

    public void setMon(String mon) {
        this.mon = mon;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public Map<String, String> getResult() {
        return result;
    }

    public void setResult(Map<String, String> result) {
        this.result = result;
    }
}
