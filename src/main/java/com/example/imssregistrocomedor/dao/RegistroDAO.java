package com.example.imssregistrocomedor.dao;

import com.example.imssregistrocomedor.conection.Conexion;
import com.example.imssregistrocomedor.controller.Registro;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import org.bson.Document;


public class RegistroDAO {


    public void guardar(Registro r){


        MongoDatabase db =
                Conexion.conectar();


        MongoCollection<Document> coleccion =
                db.getCollection("asistencias");


        Document doc = new Document();

        doc.append("nombre", r.getNombre());
        doc.append("empleadoId", r.getEmpleadoId());
        doc.append("fecha", r.getFecha().toString());


        coleccion.insertOne(doc);


        System.out.println("Guardado");

    }

}