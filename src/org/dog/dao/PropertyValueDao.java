package org.dog.dao;

import org.dog.Idao.IPropertyValueDao;
import org.dog.entity.Product;
import org.dog.entity.PropertyValue;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("propertyVlaueDao")
public class PropertyValueDao extends BaseDao<PropertyValue> implements IPropertyValueDao {
    @Override
    public List<PropertyValue> getPropertyValueByProduct(Product product) {
        return find("from PropertyValue pv where pv.product.id=?0",product.getId());
    }
}
