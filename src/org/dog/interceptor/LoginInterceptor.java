package org.dog.interceptor;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.opensymphony.xwork2.util.ValueStack;
import org.dog.entity.User;

import java.util.Map;

public class LoginInterceptor extends AbstractInterceptor {

    @Override
    public String intercept(ActionInvocation actionInvocation) throws Exception {
        ActionContext context=ActionContext.getContext();
        ValueStack stack=context.getValueStack();
        Map<String,Object> session=context.getSession();
        User u=(User)session.get("user");
        if(u==null){
            stack.set("error","请先登录");
            return "login";
        }
        return actionInvocation.invoke();
    }
}
