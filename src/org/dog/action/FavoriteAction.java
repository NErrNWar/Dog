package org.dog.action;

import com.opensymphony.xwork2.ActionSupport;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;


@Controller("favoriteAction")
@Scope("prototype")
public class FavoriteAction extends ActionSupport {

    public String execute(){

        return SUCCESS;
    }
}
