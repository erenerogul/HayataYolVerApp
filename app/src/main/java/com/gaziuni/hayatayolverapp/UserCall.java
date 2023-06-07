package com.gaziuni.hayatayolverapp;

public class UserCall {
    String namesurname,phone,adress;
    Double ulongitude,ulatitude;

    public UserCall(String phone, String namesurname, String adress, Double ulongitude, Double ulatitude) {
        this.phone = phone;
        this.namesurname = namesurname;
        this.adress = adress;
        this.ulongitude = ulongitude;
        this.ulatitude = ulatitude;
    }
    public UserCall(){

    }

    public String getNamesurname() {
        return namesurname;
    }

    public void setNamesurname(String namesurname) {
        this.namesurname = namesurname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public Double getUlongitude() {
        return ulongitude;
    }

    public void setUlongitude(Double ulongitude) {
        this.ulongitude = ulongitude;
    }

    public Double getUlatitude() {
        return ulatitude;
    }

    public void setUlatitude(Double ulatitude) {
        this.ulatitude = ulatitude;
    }
}
