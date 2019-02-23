package org.dog.action;

import com.opensymphony.xwork2.ActionSupport;
import org.dog.Iservice.IUserService;
import org.dog.service.UserService;
import org.dog.util.MailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.util.*;

@Controller("securityCodeAction")
@Scope("prototype")
public class SecurityCodeAction extends ActionSupport {
    @Autowired
    private IUserService userService;
    private String account;
    private Map<String,String> result;

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

    public IUserService getUserService() {
        return userService;
    }

    public void setUserService(IUserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute() throws Exception {
        result=new HashMap<String,String>();

        if(userService.checkUserIfExitByAccount(getAccount())){
            result.put("result","error");
            result.put("message","用户已存在!");
            result.put("code","");
            return SUCCESS;
        }

        String securityCode=getSecurityCode();


        try {
            MailUtil.sendMailBySpring(getAccount(), securityCode);
        }
        catch(Exception e){
            result.put("result","error");
            result.put("message","请检查邮箱是否正确!");
            result.put("code","");
            return SUCCESS;
        }


        System.out.println("----------------"+securityCode);
        result.put("result","success");
        result.put("code",securityCode);
        return SUCCESS;
    }

    public String getSecurityCode(){
        Random random=new Random();
        int temp=0;
        while(temp<1000){
            temp=random.nextInt(10000);
        }
        return temp+"";
    }
}
