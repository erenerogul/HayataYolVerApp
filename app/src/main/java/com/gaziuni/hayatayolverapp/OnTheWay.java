package com.gaziuni.hayatayolverapp;

public class OnTheWay {
    String driverPhone,driverName,driverPlate,userName,userPhone;
    Double driverLatitude,driverLongitude,userLatitude,userLongitude;

    public OnTheWay(String driverPhone, String driverName, String driverPlate, Double driverLatitude, Double driverLongitude,
                    String userName, String userPhone, Double userLatitude, Double userLongitude) {
        this.driverPhone = driverPhone;
        this.driverName = driverName;
        this.driverPlate = driverPlate;
        this.driverLatitude = driverLatitude;
        this.driverLongitude = driverLongitude;
        this.userName = userName;
        this.userPhone = userPhone;
        this.userLatitude = userLatitude;
        this.userLongitude = userLongitude;
    }
    public  OnTheWay(){

    }

    public String getDriverPhone() {
        return driverPhone;
    }

    public void setDriverPhone(String driverPhone) {
        this.driverPhone = driverPhone;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getDriverPlate() {
        return driverPlate;
    }

    public void setDriverPlate(String driverPlate) {
        this.driverPlate = driverPlate;
    }

    public Double getDriverLatitude() {
        return driverLatitude;
    }

    public void setDriverLatitude(Double driverLatitude) {
        this.driverLatitude = driverLatitude;
    }

    public Double getDriverLongitude() {
        return driverLongitude;
    }

    public void setDriverLongitude(Double driverLongitude) {
        this.driverLongitude = driverLongitude;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public Double getUserLatitude() {
        return userLatitude;
    }

    public void setUserLatitude(Double userLatitude) {
        this.userLatitude = userLatitude;
    }

    public Double getUserLongitude() {
        return userLongitude;
    }

    public void setUserLongitude(Double userLongitude) {
        this.userLongitude = userLongitude;
    }
}
