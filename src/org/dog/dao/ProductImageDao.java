package org.dog.dao;

import org.dog.Idao.IProductImageDao;
import org.dog.entity.Product;
import org.dog.entity.ProductImage;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("productImageDao")
public class ProductImageDao extends BaseDao<ProductImage> implements IProductImageDao {
    @Override
    public List<ProductImage> getProductImagesByProduct(Product product) {
        return find("from ProductImage pi where pi.product.id=?0 order by pi.ipath",product.getId());
    }

    @Override
    public ProductImage getFirstProductImageByProduct(Product product) {
        List<ProductImage> productImages=getProductImagesByProduct(product);
        if(productImages!=null&&productImages.size()>0){
            return productImages.get(0);
        }
        return null;
    }
}
