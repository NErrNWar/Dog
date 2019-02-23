package org.dog.vo;

import org.dog.entity.Product;
import org.dog.entity.Property;
import org.dog.entity.PropertyValue;

public class PropertyValueBean {
    private int id;
    private String value;
    private Product product;
    private Property property;

    public PropertyValueBean(){}

    public PropertyValueBean(PropertyValue propertyValue){
        this.id=propertyValue.getId();
        this.value=propertyValue.getValue();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Property getProperty() {
        return property;
    }

    public void setProperty(Property property) {
        this.property = property;
    }
}
