package org.dog.Idao;

import org.dog.entity.Product;
import org.dog.entity.Review;

import java.util.List;

public interface IReviewDao extends IBaseDao<Review> {
    List<Review> getReviewByProduct(Product product);
}
