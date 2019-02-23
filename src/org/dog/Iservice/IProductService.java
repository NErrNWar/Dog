package org.dog.Iservice;

import org.dog.entity.*;
import org.dog.vo.ShopCart;

import java.util.List;
import java.util.Map;

public interface IProductService {
    void addProduct(Product product);
    void deleteProduct(Product product);
    void updateProduct(Product product);
    List<Product> getAllProduct();
    List<Product> getProductByCategory(Category category);
    List<Product> getProductByCategoryByPage(Category category,int page,int num);
    List<Product> getProductByName(String name);
    List<Product> searchByPage(String keyword,int pageno,int pagesize);
    List<Product> getProductByPropertyValue(Category category,PropertyValue value);

    Product getProductById(int id);

    List<Review> getReViewByProduct(Product product);

    List<Category> getAllCategory();

    List<PropertyValue> getPropertyValue(Category category,Property property);

    int getReviewnum(Product product);

    List<ProductImage> getProductImage(Product product);

    ProductImage getFirstProductImage(Product product);

    void saveOrder(User user,ShopCart shopCart);

    void saveOrder(Order o);

    ShopCart getShopCart(User u);

    Map<String,String> getPropertyValue(Product product);
    long getProductCount();

    List<Product> getHotProduct();
    List<Product> getNewProduct();
    List<Product> getLifeProduct();
    List<Product> getElecProduct();
    List<Product> getFruitProduct();

    long getSearchCount(String param);
}
