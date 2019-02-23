package org.dog.action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.RequestAware;
import org.dog.Iservice.IOrderService;
import org.dog.vo.OrderBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Controller("deleteOrderAction")
@Scope("prototype")
public class DeleteOrderAction extends ActionSupport implements RequestAware {
    @Autowired
    private IOrderService orderService;
    private int oid;
    private String type;
    private Map<String,String> result;
    private Map<String,Object> request;

    public int getOid() {
        return oid;
    }

    public void setOid(int oid) {
        this.oid = oid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Map<String, String> getResult() {
        return result;
    }

    public void setResult(Map<String, String> result) {
        this.result = result;
    }

    public IOrderService getOrderService() {
        return orderService;
    }

    public void setOrderService(IOrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public void setRequest(Map<String, Object> map) {
        request=map;
    }

    public String execute(){
        result=new HashMap<>();
        System.out.println("=-=-=-=-=-=-==-=-="+getOid());
        System.out.println("-*-*--*-*-*-*-*-*-*-*-"+getType());

        try{
            /*
            List<OrderBean> all=(List<OrderBean>)request.get("allorder");
            List<OrderBean> tyorder=(List<OrderBean>) request.get(type);

            System.out.println(all);
            System.out.println(tyorder);

            Iterator<OrderBean> allitera=all.iterator();
            Iterator<OrderBean> tyitera=tyorder.iterator();

            while(allitera.hasNext()){
                if(allitera.next().getId()==oid){
                    allitera.remove();
                }
            }

            while (tyitera.hasNext()){
                if(tyitera.next().getId()==oid){
                    tyitera.remove();
                }
            }

            request.put("allorder",all);
            request.put(type,tyorder);*/

            getOrderService().deleteOrderItems(oid);
            getOrderService().deleteOrder(oid);

            result.put("result","success");
            result.put("message","");
        }catch(Exception e){
            e.printStackTrace();
            result.put("result","error");
            result.put("message",e.getMessage());
        }


        return SUCCESS;
    }
}
