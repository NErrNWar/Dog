package org.dog.dao;

import org.dog.Idao.IBaseDao;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;

@Repository("baseDao")
public class BaseDao <T>  implements IBaseDao <T> {
    @Autowired
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public T get(Class<T> entityClass, Integer id) {
        return getSessionFactory().getCurrentSession().get(entityClass,id);
    }

    @Override
    public Integer save(T entity) {
        return (Integer) getSessionFactory().getCurrentSession().save(entity);
    }

    @Override
    public void update(T entity) {
        getSessionFactory().getCurrentSession().update(entity);
    }

    @Override
    public void delete(T entity) {
        getSessionFactory().getCurrentSession().delete(entity);
    }

    @Override
    public void delete(Class<T> entityClass, Integer id) {
        getSessionFactory().getCurrentSession().createQuery("delete "+entityClass.getSimpleName()+" en where en.id=?1")
                .setParameter("1",id).executeUpdate();
    }

    @Override
    public List<T> findAll(Class<T> entityClass) {
        return find("select en from "+entityClass.getSimpleName()+" en");
    }

    @Override
    public long findCount(Class<T> entityClass) {
        List<T> lists=findAll(entityClass);
        return lists.size();
    }

    @Override
    public List<T> find(String hql, Object... params) {
        Query query=getSessionFactory().getCurrentSession().createQuery(hql);
        for(int i=0;i<params.length;i++){
            query.setParameter(i,params[i]);
        }
        return query.getResultList();
    }

    @Override
    public List<T> findByPage(String hql, int pageNo, int pageSize, Object... params) {
        Query query=getSessionFactory().getCurrentSession().createQuery(hql);
        for(int i=0;i<params.length;i++){
            query.setParameter(i,params[i]);
        }

        return query.setMaxResults(pageSize).setFirstResult((pageNo-1)*pageSize).getResultList();
    }
}
