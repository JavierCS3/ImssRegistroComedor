package com.example.imssregistrocomedor.dao;

import com.example.imssregistrocomedor.conection.Conexion;
import com.example.imssregistrocomedor.controller.Registro;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import org.bson.Document;


public class RegistroDAO {


    public void guardar(Registro r){


        System.out.println("Entrando DAO");


        MongoDatabase db = null;

        System.out.println("LLAMANDO CONEXION");

        db = Conexion.conectar();

        System.out.println("REGRESO DE CONEXION");


        System.out.println("Base conectada");


        MongoCollection<Document> coleccion =
                db.getCollection("asistencias");


        System.out.println("Coleccion obtenida");



        Document doc =
                new Document()
                        .append("nombre", r.getNombre())
                        .append("empleadoId", r.getEmpleadoId())
                        .append("fecha", r.getFecha().toString());



        System.out.println("Insertando...");


        coleccion.insertOne(doc);


        System.out.println("Guardado");

    }

}