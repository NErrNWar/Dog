package org.dog.Idao;

import org.dog.entity.Category;
import org.dog.entity.Product;
import org.dog.entity.PropertyValue;
import org.dog.entity.Property;

import java.util.List;

public interface IProductDao extends IBaseDao<Product> {
    List<Product> getProductByPropertyValue(Property property, PropertyValue value);
    List<Product> getProductByCategory(Category category);
    List<Product> getProductByName(String name);
    long getCount(String param);
}
