package org.dog.service;

import org.dog.Idao.*;
import org.dog.Iservice.IProductService;
import org.dog.dao.OrderDao;
import org.dog.entity.*;
import org.dog.vo.OrderItemBean;
import org.dog.vo.ProductBean;
import org.dog.vo.ShopCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service("productService")
public class ProductService implements IProductService {
    @Autowired
    private IUserDao userDao;
    @Autowired
    private ICategoryDao categoryDao;
    @Autowired
    private IFeedBackDao feedBackDao;
    @Autowired
    private IOrderDao orderDao;
    @Autowired
    private IOrderItemDao orderItemDao;
    @Autowired
    private IPropertyDao propertyDao;
    @Autowired
    private IPropertyValueDao propertyValueDao;
    @Autowired
    private IProductDao productDao;
    @Autowired
    private IProductImageDao productImageDao;
    @Autowired
    private IReviewDao reviewDao;

    public IUserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(IUserDao userDao) {
        this.userDao = userDao;
    }

    public ICategoryDao getCategoryDao() {
        return categoryDao;
    }

    public void setCategoryDao(ICategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    public IFeedBackDao getFeedBackDao() {
        return feedBackDao;
    }

    public void setFeedBackDao(IFeedBackDao feedBackDao) {
        this.feedBackDao = feedBackDao;
    }

    public IOrderDao getOrderDao() {
        return orderDao;
    }

    public void setOrderDao(IOrderDao orderDao) {
        this.orderDao = orderDao;
    }

    public IOrderItemDao getOrderItemDao() {
        return orderItemDao;
    }

    public void setOrderItemDao(IOrderItemDao orderItemDao) {
        this.orderItemDao = orderItemDao;
    }

    public IPropertyDao getPropertyDao() {
        return propertyDao;
    }

    public void setPropertyDao(IPropertyDao propertyDao) {
        this.propertyDao = propertyDao;
    }

    public IPropertyValueDao getPropertyValueDao() {
        return propertyValueDao;
    }

    public void setPropertyValueDao(IPropertyValueDao propertyValueDao) {
        this.propertyValueDao = propertyValueDao;
    }

    public IProductDao getProductDao() {
        return productDao;
    }

    public void setProductDao(IProductDao productDao) {
        this.productDao = productDao;
    }

    public IProductImageDao getProductImageDao() {
        return productImageDao;
    }

    public void setProductImageDao(IProductImageDao productImageDao) {
        this.productImageDao = productImageDao;
    }

    public IReviewDao getReviewDao() {
        return reviewDao;
    }

    public void setReviewDao(IReviewDao reviewDao) {
        this.reviewDao = reviewDao;
    }

    @Override
    public void addProduct(Product product) {
        getProductDao().save(product);
    }

    @Override
    public void deleteProduct(Product product) {
        getProductDao().delete(product);
    }

    @Override
    public void updateProduct(Product product) {
        getProductDao().update(product);
    }

    @Override
    public List<Product> getAllProduct() {
        return getProductDao().findAll(Product.class);
    }

    @Override
    public List<Product> getProductByCategory(Category category) {
        return getProductDao().getProductByCategory(category);
    }

    @Override
    public List<Product> getProductByCategoryByPage(Category category, int page, int num) {
        return getProductDao().findByPage("from Product p where p.category.name=?0 order by p.outnum desc",page,num,category.getName());
    }

    @Override
    public List<Product> getProductByName(String name) {
        return getProductDao().getProductByName(name);
    }

    @Override
    public List<Product> searchByPage(String keyword, int pageno, int pagesize) {
        /*List<Product> productList=getProductDao().find("from Product p where p.category.name like?0 order by p.outnum desc",keyword);
        List<Product> productList1=getProductDao().find("from Product p where p.name like?0 order by p.outnum",keyword);
        List<Product> productList2=new ArrayList<>();

        List<PropertyValue> propertyValueList=getPropertyValueDao().find("from PropertyValue pv where pv.property.name='品牌' and pv.value like ?0",keyword);

        Set<Product> ps=new TreeSet<>(productList);
        return new ArrayList<Product>(ps);*/

        System.out.println("pageno------------------------"+pageno);
        System.out.println("pagesize++++++++++++++++"+pagesize);

        return getProductDao().findByPage("select distinct p from Product p join p.propertyValues pv where p.name like?0 or p.category.name like?1 or pv.property.name='品牌' and pv.value like ?2 order by p.outnum desc",pageno,pagesize,keyword,keyword,keyword);
    }

    @Override
    public List<Product> getProductByPropertyValue(Category category,PropertyValue value) {
        Property p=new Property();
        p.setName("品牌");
        List<PropertyValue> propertyValueList=getPropertyValue(category,p);

        List<Product> target=new ArrayList<>();
        for(PropertyValue propertyValue: propertyValueList){
            if(propertyValue.getValue().equals(value.getValue())){
                target.add(propertyValue.getProduct());
            }
        }
        return target;
    }

    @Override
    public Product getProductById(int id) {
        return getProductDao().get(Product.class,id);
    }

    @Override
    public List<Review> getReViewByProduct(Product p) {
        return getReviewDao().getReviewByProduct(p);
    }

    @Override
    public List<Category> getAllCategory() {
        return getCategoryDao().find("select c from Category c order by c.id");
    }

    @Override
    public List<PropertyValue> getPropertyValue(Category category, Property property) {
        List<Property> properties=getPropertyDao().find("select pro from Property pro where pro.category.name=?0 and pro.name=?1",category.getName(),property.getName());
        Set<PropertyValue> pvalue=new HashSet<PropertyValue>();
        for(Property p:properties){
            pvalue.addAll(p.getPropertyValues());
        }
        return new ArrayList<PropertyValue>(pvalue);
    }

    @Override
    public int getReviewnum(Product product) {
        List<Review> reviews=getReviewDao().getReviewByProduct(product);
        return (reviews==null)?0:reviews.size();
    }

    @Override
    public List<ProductImage> getProductImage(Product product) {
        return getProductImageDao().getProductImagesByProduct(product);
    }

    @Override
    public ProductImage getFirstProductImage(Product product) {
        return getProductImageDao().getFirstProductImageByProduct(product);
    }

    @Override
    public void saveOrder(User user, ShopCart shopCart) {
        if(shopCart.getId()<=0) {
            int uid = user.getId();
            User u = getUserDao().get(User.class, uid);
            Order order = new Order();
            order.setUser(u);
            order.setType("shopCart");
            for (OrderItemBean itemBean : shopCart.getItems()) {
                OrderItem item = new OrderItem();
                int pid = itemBean.getProductBean().getProid();
                Product product = getProductById(pid);
                item.setProduct(product);
                item.setNum(itemBean.getNum());
                item.setPrice(itemBean.getPrice());
                item.setOrder(order);
                order.getOrderItems().add(item);
            }
            order.setPrice(shopCart.getPrice());
            getOrderDao().save(order);
            shopCart.setId(order.getId());
        }
        else{
            Order order= getOrderDao().get(Order.class,shopCart.getId());
            boolean flag=false;
            Iterator<OrderItem> itemIterator=order.getOrderItems().iterator();
            while(itemIterator.hasNext()){
                flag=false;
                OrderItem item=itemIterator.next();
                for(OrderItemBean itemBean:shopCart.getItems()){
                    if(item.getProduct().getId()==itemBean.getProductBean().getProid()){
                        item.setNum(itemBean.getNum());
                        item.setPrice(itemBean.getPrice());
                        flag=true;
                    }
                }
                if(!flag){
                    getOrderItemDao().delete(item);
                    itemIterator.remove();
                }
            }

            boolean f2=false;
            for(OrderItemBean oibean:shopCart.getItems()){
                f2=false;
                for(OrderItem oi:order.getOrderItems()){
                    if(oibean.getProductBean().getProid()==oi.getProduct().getId()){
                        f2=true;
                    }
                }

                if(!f2){
                    OrderItem i=new OrderItem();
                    Product p=getProductDao().get(Product.class,oibean.getProductBean().getProid());
                    i.setProduct(p);
                    i.setNum(oibean.getNum());
                    i.setPrice(oibean.getNum()*p.getPromotePrice());
                    order.getOrderItems().add(i);
                    i.setOrder(order);
                }
            }

            double count=0;
            for(OrderItem i:order.getOrderItems()){
                System.out.println("==============="+i.getProduct().getName());
                count+=i.getPrice();
            }

            order.setPrice(count);
            getOrderDao().update(order);
        }
    }

    @Override
    public void saveOrder(Order o) {
        getOrderDao().save(o);
    }

    @Override
    public ShopCart getShopCart(User u) {
        List<Order> orders=getOrderDao().getOrderByType(u,"shopCart");
        if(orders==null||orders.size()==0){
            return null;
        }
        Order order=orders.get(0);
        if(order==null){
            return null;
        }

        ShopCart shopCart=new ShopCart();
        shopCart.setId(order.getId());
        List<OrderItem> orderItems=getOrderItemDao().getOrderItemByOrder(order);

        for(OrderItem item:orderItems){
            OrderItemBean bean=new OrderItemBean();
            bean.setNum(item.getNum());
            bean.setPrice(item.getPrice());
            bean.setId(item.getId());
            ProductBean b=new ProductBean(item.getProduct());
            b.setFirstimg(getFirstProductImage(item.getProduct()).getIpath());
            bean.setProductBean(b);
            shopCart.getItems().add(bean);
        }

        return shopCart;
    }

    @Override
    public Map<String, String> getPropertyValue(Product product) {
        List<PropertyValue> propertyValues=getPropertyValueDao().getPropertyValueByProduct(product);
        Map<String,String> pv=new HashMap<String,String>();
        for(PropertyValue value:propertyValues){
            pv.put(value.getProperty().getName(),value.getValue());
        }
        return pv;
    }

    @Override
    public long getProductCount() {
        return getProductDao().findCount(Product.class);
    }

    @Override
    public List<Product> getHotProduct() {
        return getProductDao().findByPage("from Product p order by p.outnum desc",1,8);
    }

    @Override
    public List<Product> getNewProduct() {
        return getProductDao().findByPage("from Product p order by p.createTime desc",1,8);
    }

    @Override
    public List<Product> getLifeProduct() {
        return getProductDao().findByPage("from Product p where p.category.name='家具建材' order by p.outnum desc",1,8);
    }

    @Override
    public List<Product> getElecProduct() {
        return getProductDao().findByPage("from Product p where p.category.name='数码电子' order by p.outnum desc",1,8);
    }

    @Override
    public List<Product> getFruitProduct() {
        return getProductDao().findByPage("from Product p where p.category.name='生鲜水果' order by p.outnum desc",1,8);
    }

    @Override
    public long getSearchCount(String param) {
        return getProductDao().getCount(param);
    }


}
