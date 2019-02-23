package org.dog.Idao;

import org.dog.entity.Order;
import org.dog.entity.OrderItem;

import java.util.List;

public interface IOrderItemDao extends IBaseDao<OrderItem> {
    List<OrderItem> getOrderItemByOrder(Order order);
}
