package org.dog.service;

import org.dog.Idao.*;
import org.dog.Iservice.IOrderService;
import org.dog.dao.ReviewDao;
import org.dog.entity.Order;
import org.dog.entity.OrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("orderService")
public class OrderService implements IOrderService{
    @Autowired
    private IUserDao userDao;
    @Autowired
    private ICategoryDao categoryDao;
    @Autowired
    private IFeedBackDao feedBackDao;
    @Autowired
    private IOrderDao orderDao;
    @Autowired
    private IOrderItemDao orderItemDao;
    @Autowired
    private IProductImageDao productImageDao;
    @Autowired
    private IProductDao productDao;
    @Autowired
    private IPropertyDao propertyDao;
    @Autowired
    private IPropertyValueDao propertyValueDao;
    @Autowired
    private ReviewDao reviewDao;

    public IUserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(IUserDao userDao) {
        this.userDao = userDao;
    }

    public ICategoryDao getCategoryDao() {
        return categoryDao;
    }

    public void setCategoryDao(ICategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    public IFeedBackDao getFeedBackDao() {
        return feedBackDao;
    }

    public void setFeedBackDao(IFeedBackDao feedBackDao) {
        this.feedBackDao = feedBackDao;
    }

    public IOrderDao getOrderDao() {
        return orderDao;
    }

    public void setOrderDao(IOrderDao orderDao) {
        this.orderDao = orderDao;
    }

    public IOrderItemDao getOrderItemDao() {
        return orderItemDao;
    }

    public void setOrderItemDao(IOrderItemDao orderItemDao) {
        this.orderItemDao = orderItemDao;
    }

    public IProductImageDao getProductImageDao() {
        return productImageDao;
    }

    public void setProductImageDao(IProductImageDao productImageDao) {
        this.productImageDao = productImageDao;
    }

    public IProductDao getProductDao() {
        return productDao;
    }

    public void setProductDao(IProductDao productDao) {
        this.productDao = productDao;
    }

    public IPropertyDao getPropertyDao() {
        return propertyDao;
    }

    public void setPropertyDao(IPropertyDao propertyDao) {
        this.propertyDao = propertyDao;
    }

    public IPropertyValueDao getPropertyValueDao() {
        return propertyValueDao;
    }

    public void setPropertyValueDao(IPropertyValueDao propertyValueDao) {
        this.propertyValueDao = propertyValueDao;
    }

    public ReviewDao getReviewDao() {
        return reviewDao;
    }

    public void setReviewDao(ReviewDao reviewDao) {
        this.reviewDao = reviewDao;
    }

    @Override
    public List<OrderItem> getOrderItem(Order o) {
        return getOrderItemDao().getOrderItemByOrder(o);
    }

    @Override
    public void deleteOrder(int oid) {

        getOrderDao().delete(Order.class,oid);
    }

    @Override
    public void deleteOrderItems(int oid) {
        Order o=new Order();
        o.setId(oid);
        List<OrderItem> orderItems=getOrderItemDao().getOrderItemByOrder(o);

        for(OrderItem orderItem:orderItems){
            getOrderItemDao().delete(orderItem);
            System.out.println(orderItem.getProduct().getName());
        }
    }

    @Override
    public Order getOrderById(int oid) {
        return getOrderDao().get(Order.class,oid);
    }

    @Override
    public void saveOrder(Order order) {
        getOrderDao().save(order);
    }

    @Override
    public void updateOrder(Order order) {
        getOrderDao().update(order);
    }

    @Override
    public OrderItem getOrderItemById(int oiid) {
        return getOrderItemDao().get(OrderItem.class,oiid);
    }

    @Override
    public void updateOrderItem(OrderItem orderItem) {
        getOrderItemDao().update(orderItem);
    }
}
