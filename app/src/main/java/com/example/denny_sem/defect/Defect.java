package com.example.denny_sem.defect;

public class Defect {

    private String city, address, sender, createdAt;

    public Defect(String city, String address, String sender, String createdAt) {
        this.city = city;
        this.address = address;
        this.sender = sender;
        this.createdAt = createdAt;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}
