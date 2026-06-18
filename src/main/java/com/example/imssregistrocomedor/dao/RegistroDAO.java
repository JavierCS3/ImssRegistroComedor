package com.example.imssregistrocomedor.dao;

import com.example.imssregistrocomedor.conection.Conexion;
import com.example.imssregistrocomedor.controller.Registro;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import org.bson.Document;


public class RegistroDAO {


    public void guardar(Registro r){

        try{


            MongoDatabase db =
                    Conexion.conectar();


            MongoCollection<Document> coleccion =
                    db.getCollection("asistencias");


            Document doc =
                    new Document()
                            .append("nombre", r.getNombre())
                            .append("empleadoId", r.getEmpleadoId())
                            .append("fecha", r.getFecha().toString());


            coleccion.insertOne(doc);


            System.out.println("Guardado");


        }catch(Exception e){

            e.printStackTrace();

            throw e;

        }

    }

}