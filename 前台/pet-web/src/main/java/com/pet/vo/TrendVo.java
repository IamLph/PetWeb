package com.pet.vo;

import java.util.Date;

public class TrendVo {

    private String dynamicId;
    private String message;
    private String content;
    private Integer zan;
    private Integer cai;
    private Integer comment;
    private Date time;
    private String pId;
    private String pName;
    private String url;
    private String turl;

    public TrendVo() {
    }

    public TrendVo(String dynamicId, String message, String content, Integer zan, Integer cai, Integer comment, Date time, String pId, String pName, String url, String turl) {
        this.dynamicId = dynamicId;
        this.message = message;
        this.content = content;
        this.zan = zan;
        this.cai = cai;
        this.comment = comment;
        this.time = time;
        this.pId = pId;
        this.pName = pName;
        this.url = url;
        this.turl = turl;
    }

    public String getDynamicId() {
        return dynamicId;
    }

    public void setDynamicId(String dynamicId) {
        this.dynamicId = dynamicId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getZan() {
        return zan;
    }

    public void setZan(Integer zan) {
        this.zan = zan;
    }

    public Integer getCai() {
        return cai;
    }

    public void setCai(Integer cai) {
        this.cai = cai;
    }

    public Integer getComment() {
        return comment;
    }

    public void setComment(Integer comment) {
        this.comment = comment;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getpId() {
        return pId;
    }

    public void setpId(String pId) {
        this.pId = pId;
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTurl() {
        return turl;
    }

    public void setTurl(String turl) {
        this.turl = turl;
    }
}
