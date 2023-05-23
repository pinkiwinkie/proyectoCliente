package com.example.proyectocliente.activities.model;
//getters y setters
public class Usuario {
    private int id;
    private String name,
            lastName;
    private int idOficio;

    public Usuario(int id, String name, String lastName, int idOficio) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.idOficio = idOficio;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public int getIdOficio() {
        return idOficio;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setIdOficio(int idOficio) {
        this.idOficio = idOficio;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", idOficio=" + idOficio +
                '}';
    }
}
