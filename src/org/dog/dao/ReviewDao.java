package org.dog.dao;

import org.dog.Idao.IReviewDao;
import org.dog.entity.Product;
import org.dog.entity.Review;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("reviewDao")
public class ReviewDao extends BaseDao<Review> implements IReviewDao {
    @Override
    public List<Review> getReviewByProduct(Product product) {
        return find("select r from Review r where r.product.id=?0 order by r.createTime",product.getId());
    }
}
