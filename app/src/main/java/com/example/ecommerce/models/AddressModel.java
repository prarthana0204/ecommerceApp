package com.example.ecommerce.models;

public class AddressModel {
    String userAddress;
    boolean isSeleceted;

    public AddressModel() {
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public boolean isSeleceted() {
        return isSeleceted;
    }

    public void setSeleceted(boolean seleceted) {
        isSeleceted = seleceted;
    }
}
