package org.dog.Iservice;

import org.dog.entity.Order;
import org.dog.entity.OrderItem;

import java.util.List;

public interface IOrderService {
    List<OrderItem> getOrderItem(Order o);
    void deleteOrder(int oid);
    void deleteOrderItems(int oid);
    Order getOrderById(int oid);
    void saveOrder(Order order);
    void updateOrder(Order order);
    OrderItem getOrderItemById(int oiid);
    void updateOrderItem(OrderItem orderItem);
}
