package com.pet.vo;

public class CommentVo {

    private String id;
    private String dynamicId;
    private String userId;
    private String img;
    private String userName;
    private String content;
    private String time;

    public CommentVo() {
    }

    public CommentVo(String id, String dynamicId, String userId, String img, String userName, String content, String time) {
        this.id = id;
        this.dynamicId = dynamicId;
        this.userId = userId;
        this.img = img;
        this.userName = userName;
        this.content = content;
        this.time = time;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDynamicId() {
        return dynamicId;
    }

    public void setDynamicId(String dynamicId) {
        this.dynamicId = dynamicId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
