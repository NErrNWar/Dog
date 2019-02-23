package org.dog.dao;

import org.dog.Idao.IProductDao;
import org.dog.entity.Category;
import org.dog.entity.Product;
import org.dog.entity.Property;
import org.dog.entity.PropertyValue;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("productDao")
public class ProductDao extends BaseDao<Product> implements IProductDao {
    @Override
    public List<Product> getProductByPropertyValue(Property property, PropertyValue value) {
        return null;
    }

    @Override
    public List<Product> getProductByCategory(Category category) {
        List<Product> list=find("select p from Product p where p.category.id=?0",category.getId());
        return list;
    }

    @Override
    public List<Product> getProductByName(String name) {
        String rname="%"+name+"%";
        return find("select p from Product p where p.name like ?0",rname);
    }

    @Override
    public long getCount(String param) {

        List<?> list=null;
        Query q=getSessionFactory().getCurrentSession().createQuery("select count(distinct p) from Product p join p.propertyValues pv where p.name like ?1 or p.category.name like?2 or pv.property.name='品牌' and pv.value like ?3")
                .setParameter(1,param).setParameter(2,param).setParameter(3,param);

        list= q.list();
        if(list!=null){
            Object o=list.get(0);
            return (long)o;
        }
        return 0;
    }
}
