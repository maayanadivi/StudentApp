package com.example.maayanalice.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Student {
    @PrimaryKey
    @NonNull
    String id = "";
    String name = "";
    boolean flag = false;
    String phone ="";
    String address="";

    public Student(){}
    public Student(String name, String id, boolean flag,String phone, String address) {
        this.name = name;
        this.id = id;
        this.flag = flag;
        this.phone=phone;
        this.address=address;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public boolean isFlag() {
        return flag;
    }
    public String getPhone(){
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getAddress(){
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }


}
