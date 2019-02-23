package org.dog.Idao;

import java.util.List;

public interface IBaseDao<T> {
    T get(Class<T> entityClass,Integer id);
    Integer save(T entity);
    void update(T entity);
    void delete(T entity);
    void delete(Class<T> entityClass,Integer id);
    List<T> findAll(Class<T> entityClass);
    long findCount(Class<T> entityClass);
    List<T> find(String hql,Object...params);
    List<T> findByPage(String hql,int pageNo,int pageSize,Object...params);
}
