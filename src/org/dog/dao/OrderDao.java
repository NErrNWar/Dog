package org.dog.dao;

import org.dog.Idao.IOrderDao;
import org.dog.entity.Order;
import org.dog.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("orderDao")
public class OrderDao extends BaseDao<Order> implements IOrderDao {
    @Override
    public List<Order> getOrderByType(User user, String type) {
        return find("from Order o where o.user.id=?0 and o.type=?1 order by o.buyTime",user.getId(),type);
    }
}
