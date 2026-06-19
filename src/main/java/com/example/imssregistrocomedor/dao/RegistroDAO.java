package com.example.imssregistrocomedor.dao;

import com.example.imssregistrocomedor.conection.Conexion;
import com.example.imssregistrocomedor.controller.Registro;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import org.bson.Document;

import java.time.LocalDateTime;


public class RegistroDAO {


    public void guardar(Registro r){

        MongoClient cliente =
                MongoClients.create(
                        "mongodb+srv://javierefrencs_db_user:J123456@imssregistro.kau1t1i.mongodb.net/comedor_imss?retryWrites=true&w=majority"
                );


        System.out.println("Mongo creado");


        MongoDatabase db =
                cliente.getDatabase("comedor_imss");


        System.out.println("DB lista");


        MongoCollection<Document> coleccion =
                db.getCollection("asistencias");


        Document doc =
                new Document()
                        .append("nombre", r.getNombre())
                        .append("empleadoId", r.getEmpleadoId())
                        .append("fecha", LocalDateTime.now().toString());


        coleccion.insertOne(doc);


        System.out.println("Guardado");

    }

}