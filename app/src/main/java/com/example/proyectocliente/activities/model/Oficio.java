package com.example.proyectocliente.activities.model;

import java.sql.Blob;

public class Oficio {
    private int id;
    private String description;
    private Blob image;

    public Oficio(int id, String description, Blob image) {
        this.id = id;
        this.description = description;
        this.image = image;
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

    public Blob getImage() {
        return image;
    }

    public void setImage(Blob image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Oficio{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", image=" + image +
                '}';
    }
}
