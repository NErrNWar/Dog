package org.dog.Iservice;

import org.dog.entity.*;

import java.util.List;

public interface IUserService {

    User checkUser(String name,String password);
    boolean registUser(User u);
    void updateUser(User u);
    void deleteUser(User u);
    boolean checkUserIfExitByUsername(String username);
    boolean checkUserIfExitByAccount(String account);

    void addReview(Review review,User user);

    void addFeedBack(FeedBack feedBack,User user);

    void addFavorite(User u,Product product);
    void removeFavorite(User u,Product product);
    List<Product> getFavorite(User u);

    void addOrder(User u,Order order);
    void removeOrder(Order order);
    void updateOrder(Order order);
    List<Order> getAllOrder(User u);
    List<Order> getOrderByType(User u,String type);

    List<Product> getAllProductHaveBought(User u);

    User getUserById(int id);
    User getUserByName(String username);
    User getUserByAccount(String account);
}
