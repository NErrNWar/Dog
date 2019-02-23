package org.dog.dao;

import org.dog.Idao.IOrderItemDao;
import org.dog.entity.Order;
import org.dog.entity.OrderItem;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("orderItemDao")
public class OrderItemDao extends BaseDao<OrderItem> implements IOrderItemDao {
    @Override
    public List<OrderItem> getOrderItemByOrder(Order order) {
        return find("from OrderItem oi where oi.order.id=?0",order.getId());
    }
}
