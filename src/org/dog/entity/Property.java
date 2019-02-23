package org.dog.entity;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name="Property_inf")
public class Property {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="pry_id")
    private Integer id;
    private String name;

    @ManyToOne(targetEntity = Category.class)
    @JoinColumn(name="cat_id",referencedColumnName = "cat_id")
    private Category category;

    @OneToMany(targetEntity = PropertyValue.class,mappedBy = "property",cascade = CascadeType.ALL)
    private Set<PropertyValue> propertyValues=new HashSet<PropertyValue>();

    @Override
    public int hashCode() {
        int result=(getId()==null)?0:getId().intValue();
        result=result*11+((getName()==null)?0:getName().hashCode());
        result=result*11+((getCategory()==null)?0:getCategory().hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj==this){
            return true;
        }
        if(obj==null){
            return false;
        }
        if(!obj.getClass().equals(getClass())){
            return false;
        }
        Property p=(Property)obj;
        if(p.getId()!=getId()){
            return false;
        }
        if(p.getName()==null){
            if(getName()!=null){
                return false;
            }
        }
        else if(!p.getName().equals(getName())){
            return false;
        }
        if(p.getCategory()==null){
            if(getCategory()!=null){
                return false;
            }
        }
        else if(!p.getCategory().equals(getCategory())){
            return false;
        }
        return true;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Set<PropertyValue> getPropertyValues() {
        return propertyValues;
    }

    public void setPropertyValues(Set<PropertyValue> propertyValues) {
        this.propertyValues = propertyValues;
    }
}
