package org.dog.dao;

import org.dog.Idao.ICategoryDao;
import org.dog.entity.Category;
import org.springframework.stereotype.Repository;

@Repository("categoryDao")
public class CategoryDao extends BaseDao<Category> implements ICategoryDao {
}
