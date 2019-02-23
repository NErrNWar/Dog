package org.dog.entity;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name="FeedBack_inf")
public class FeedBack {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="fed_id")
    private Integer id;
    private String content;

    @Temporal(TemporalType.TIMESTAMP)
    private Calendar createTime;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name="user_id",referencedColumnName = "user_id")
    private User user;

    @Override
    public int hashCode() {
        int result=(getId()==null)?0:getId().intValue();
        result=result*11+((getUser()==null)?0:((getUser().getId()==null)?0:getUser().getId()));
        result=result*11+((getCreateTime()==null)?0:getCreateTime().hashCode());
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
        FeedBack feedBack=(FeedBack)obj;
        if(feedBack.getId()!=getId()){
            return false;
        }
        if(feedBack.getUser()==null){
            if(getUser()!=null){
                return false;
            }
        }
        else if(!feedBack.getUser().equals(getUser())){
            return false;
        }
        if(feedBack.getCreateTime()==null){
            if(getCreateTime()!=null){
                return false;
            }
        }
        else if(!feedBack.getCreateTime().equals(getCreateTime())){
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Calendar getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Calendar createTime) {
        this.createTime = createTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
