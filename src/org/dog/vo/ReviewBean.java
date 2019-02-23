package org.dog.vo;

import org.dog.entity.Review;

import java.util.Calendar;

public class ReviewBean {
    private int id;
    private String content;
    private String type;
    private String createTime;
    private String username;

    public ReviewBean(){}

    public ReviewBean(Review review){
        this.id=review.getId();
        this.content=review.getContent();
        this.type=review.getType();

        Calendar c=review.getCreateTime();
        if(c!=null){
            StringBuilder builder=new StringBuilder();
            builder.append(c.get(Calendar.YEAR)+"-");
            builder.append((c.get(Calendar.MONTH)+1)+"-");
            builder.append(c.get(Calendar.DAY_OF_MONTH));
            createTime=builder.toString();
        }

        if(review.getUser()!=null){
            username=review.getUser().getUsername();
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
