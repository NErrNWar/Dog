package org.dog.dao;

import org.dog.Idao.IPropertyDao;
import org.dog.entity.Property;
import org.springframework.stereotype.Repository;

@Repository("propertyDao")
public class PropertyDao extends BaseDao<Property> implements IPropertyDao {
}
