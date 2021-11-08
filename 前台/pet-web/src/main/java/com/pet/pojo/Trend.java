package com.pet.pojo;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Table(name = "tb_trend")
public class Trend implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private String title;
    private String img;
    private String content;
    private Integer star;
    private Integer unstar;
    private Integer commentNum;
    private Date createTime;

    public Trend(String title, String img, String content) {
        this.title = title;
        this.img = img;
        this.content = content;
    }

    public Trend(String id, String title, String img, String content, Integer star, Integer unstar, Integer commentNum, Date createTime) {
        this.id = id;
        this.title = title;
        this.img = img;
        this.content = content;
        this.star = star;
        this.unstar = unstar;
        this.commentNum = commentNum;
        this.createTime = createTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getStar() {
        return star;
    }

    public void setStar(Integer star) {
        this.star = star;
    }

    public Integer getUnstar() {
        return unstar;
    }

    public void setUnstar(Integer unstar) {
        this.unstar = unstar;
    }

    public Integer getCommentNum() {
        return commentNum;
    }

    public void setCommentNum(Integer commentNum) {
        this.commentNum = commentNum;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Trend{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", img='" + img + '\'' +
                ", content='" + content + '\'' +
                ", star=" + star +
                ", unstar=" + unstar +
                ", commentNum=" + commentNum +
                ", createTime=" + createTime +
                '}';
    }
}
