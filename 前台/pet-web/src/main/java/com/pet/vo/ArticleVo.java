package com.pet.vo;

public class ArticleVo {

    private String id;
    private String imgUrl;
    private String pName;
    private Double money;
    private String guiGe;
    private String yongFa;
    private String zhuYi;
    private String shopUrl;

    public ArticleVo() {
    }

    public ArticleVo(String id, String imgUrl, String pName, Double money, String guiGe, String yongFa, String zhuYi, String shopUrl) {
        this.id = id;
        this.imgUrl = imgUrl;
        this.pName = pName;
        this.money = money;
        this.guiGe = guiGe;
        this.yongFa = yongFa;
        this.zhuYi = zhuYi;
        this.shopUrl = shopUrl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public String getGuiGe() {
        return guiGe;
    }

    public void setGuiGe(String guiGe) {
        this.guiGe = guiGe;
    }

    public String getYongFa() {
        return yongFa;
    }

    public void setYongFa(String yongFa) {
        this.yongFa = yongFa;
    }

    public String getZhuYi() {
        return zhuYi;
    }

    public void setZhuYi(String zhuYi) {
        this.zhuYi = zhuYi;
    }

    public String getShopUrl() {
        return shopUrl;
    }

    public void setShopUrl(String shopUrl) {
        this.shopUrl = shopUrl;
    }
}
