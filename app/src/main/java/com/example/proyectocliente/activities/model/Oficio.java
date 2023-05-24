package com.example.proyectocliente.activities.model;

import java.sql.Blob;

public class Oficio {
    private int id;
    private String description;

    public Oficio(int id, String description, Blob image) {
        this.id = id;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return description;
    }
}
