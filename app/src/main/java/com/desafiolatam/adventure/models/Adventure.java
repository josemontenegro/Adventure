package com.desafiolatam.adventure.models;

import java.io.Serializable;

public class Adventure implements Serializable {

    private String name, description, category, date, uid, owner;

    public Adventure() {
    }

    public Adventure(String name, String description, String category, String date, String uid, String owner) {
        this.name = name;
        this.description = description;
        this.category = category;
        this.date = date;
        this.uid = uid;
        this.owner = owner;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }
}