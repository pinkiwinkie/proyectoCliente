package com.example.proyectocliente.activities.model;

import java.io.Serializable;

public class Root implements Serializable {
    public class Usuario implements Serializable{
        private int id;
        private String name,
                lastName;
        private int idOficio;
    }
    public class Oficio implements Serializable{
        private int id;
        private String description,
                imageUrl;
    }
}
