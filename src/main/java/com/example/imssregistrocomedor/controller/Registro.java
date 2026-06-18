package com.example.imssregistrocomedor.controller;

import java.time.LocalDateTime;


public class Registro {

    private String nombre;
    private String empleadoId;
    private LocalDateTime fecha;


    public Registro(String nombre,String empleadoId){

        this.nombre = nombre;
        this.empleadoId = empleadoId;
        this.fecha = LocalDateTime.now();

    }


    public String getNombre(){
        return nombre;
    }


    public String getEmpleadoId(){
        return empleadoId;
    }


    public LocalDateTime getFecha(){
        return fecha;
    }

}