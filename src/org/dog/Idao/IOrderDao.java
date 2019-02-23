package org.dog.Idao;

import org.dog.entity.Order;
import org.dog.entity.User;

import java.util.List;

public interface IOrderDao extends IBaseDao<Order>{
    List<Order> getOrderByType(User user,String type);
}
