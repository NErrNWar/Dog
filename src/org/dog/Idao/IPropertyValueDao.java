package org.dog.Idao;

import org.dog.entity.Product;
import org.dog.entity.PropertyValue;

import java.util.List;

public interface IPropertyValueDao extends IBaseDao<PropertyValue> {
    List<PropertyValue> getPropertyValueByProduct(Product product);
}
