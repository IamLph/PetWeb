package com.pet.pojo;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Table(name = "tb_pet_user")
public class PetUser implements Serializable {

    private String petId;
    private String userId;
    private String startTime;
    private String endTime;
    private String idCard;

    public PetUser() {
    }

    public PetUser(String petId, String userId, String startTime, String endTime, String idCard) {
        this.petId = petId;
        this.userId = userId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.idCard = idCard;
    }

    public String getPetId() {
        return petId;
    }

    public void setPetId(String petId) {
        this.petId = petId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    @Override
    public String toString() {
        return "PetUser{" +
                "petId='" + petId + '\'' +
                ", userId='" + userId + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", idCard='" + idCard + '\'' +
                '}';
    }
}
