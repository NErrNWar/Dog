package org.dog.service;

import org.dog.Idao.*;
import org.dog.Iservice.IUserService;
import org.dog.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service("userService")
public class UserService implements IUserService {
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
    private IPropertyDao propertyDao;
    @Autowired
    private IPropertyValueDao propertyValueDao;
    @Autowired
    private IProductDao productDao;
    @Autowired
    private IProductImageDao productImageDao;
    @Autowired
    private IReviewDao reviewDao;


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

    public IProductDao getProductDao() {
        return productDao;
    }

    public void setProductDao(IProductDao productDao) {
        this.productDao = productDao;
    }

    public IProductImageDao getProductImageDao() {
        return productImageDao;
    }

    public void setProductImageDao(IProductImageDao productImageDao) {
        this.productImageDao = productImageDao;
    }

    public IReviewDao getReviewDao() {
        return reviewDao;
    }

    public void setReviewDao(IReviewDao reviewDao) {
        this.reviewDao = reviewDao;
    }

    @Override
    public User checkUser(String name, String password) {
        User u=getUserDao().getUserByName(name);
        if(u==null){
            return null;
        }
        else if(u.getPassword().equals(password)){
            return u;
        }
        return null;
    }

    @Override
    public boolean registUser(User u) {
        int c=getUserDao().save(u);
        if(c>0) {
            return true;
        }
        return false;
    }

    @Override
    public void updateUser(User u) {
        getUserDao().update(u);
    }

    @Override
    public void deleteUser(User u) {
        getUserDao().delete(u);
    }

    @Override
    public boolean checkUserIfExitByUsername(String username) {
        User u=userDao.getUserByName(username);
        if(u==null){
            return false;
        }
        return true;
    }

    @Override
    public boolean checkUserIfExitByAccount(String account) {
        System.out.println("account==+=+==+=+=="+account);
        User u=userDao.getUserByAccount(account);
        System.out.println(u);
        if(u==null){
            return false;
        }
        return true;
    }

    @Override
    public void addReview(Review review,User user) {
        User ru=userDao.get(User.class,user.getId());
        review.setUser(ru);
        getReviewDao().save(review);
    }

    @Override
    public void addFeedBack(FeedBack feedBack,User user) {
        getFeedBackDao().save(feedBack);
    }

    @Override
    public void addFavorite(User u, Product product) {
        User ru=getUserById(u.getId());
        Product p=getProductDao().get(Product.class,product.getId());
        //ru.setFavorite();
        //getProductDao().find("select p from Product p where p.id in (select pro_id from favorite_tab tab join User u on tab.user_id=u.id where u.id=?0)",u.getId());

        ru.getFavorite().add(p);

        getUserDao().update(ru);
    }

    @Override
    public void removeFavorite(User u,Product product) {
        User ru=getUserById(u.getId());
        /*
        for(Product p:ru.getFavorite()){
            if (p.getId()==product.getId()){
                ru.getFavorite().remove(p);
            }
        }*/

        Iterator<Product> productIterator=ru.getFavorite().iterator();
        while(productIterator.hasNext()){
            if(productIterator.next().getId()==product.getId()){
                productIterator.remove();
            }
        }

        getUserDao().update(ru);
    }

    @Override
    public List<Product> getFavorite(User u) {
        String username=u.getUsername();
        User user=getUserDao().getUserByName(username);
        List<Product> products=new ArrayList<Product>(user.getFavorite());
        return products;
    }

    @Override
    public void addOrder(User u, Order order) {
        String username=u.getUsername();
        User user=getUserDao().getUserByName(username);
        order.setUser(user);
        getOrderDao().save(order);
    }

    @Override
    public void removeOrder(Order order) {
        getOrderDao().delete(order);
    }

    @Override
    public void updateOrder(Order order) {
        getOrderDao().update(order);
    }

    @Override
    public List<Order> getAllOrder(User u) {
        return getOrderDao().find("from Order o where o.user.username=?0 order by o.buyTime",u.getUsername());
    }

    @Override
    public List<Order> getOrderByType(User u, String type) {
        List<Order> os=getAllOrder(u);
        List<Order> r=new ArrayList<Order>();
        for(Order o:os){
            if(o.getType().equals(type)){
                r.add(o);
            }
        }
        return r;
    }

    @Override
    public List<Product> getAllProductHaveBought(User u) {
        List<OrderItem> ois=getOrderItemDao().find("from OrderItem oi where oi.order.user.username=?0",u.getUsername());
        Set<Product> products=new HashSet<Product>();
        for(OrderItem oi:ois){
            products.add(oi.getProduct());
        }
        return new ArrayList<Product>(products);
    }

    @Override
    public User getUserById(int id) {
        return getUserDao().get(User.class,id);
    }

    @Override
    public User getUserByName(String username) {
        List<User> users=getUserDao().find("from User u where u.username=?0",username);
        if(users!=null){
            return users.get(0);
        }
        return null;
    }

    @Override
    public User getUserByAccount(String account) {
        List<User> users=getUserDao().find("from User u where u.account=?0",account);
        if(users!=null){
            return users.get(0);
        }
        return null;
    }
}
