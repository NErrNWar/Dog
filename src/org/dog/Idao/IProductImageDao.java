package org.dog.Idao;

import org.dog.entity.Product;
import org.dog.entity.ProductImage;

import java.util.List;

public interface IProductImageDao extends IBaseDao<ProductImage> {
    List<ProductImage> getProductImagesByProduct(Product product);
    ProductImage getFirstProductImageByProduct(Product product);
}
