package com.example.imssregistrocomedor.conection;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class Conexion {


    private static final String URL =
            "mongodb+srv://javierefrencs_db_user:J123456@imssregistro.kau1t1i.mongodb.net/comedor_imss?retryWrites=true&w=majority&serverSelectionTimeoutMS=10000&connectTimeoutMS=10000";


    private static MongoClient cliente;



    public static MongoDatabase conectar() {

        try {

            System.out.println("Creando cliente Mongo");

            MongoClient cliente = MongoClients.create(URL);

            System.out.println("Cliente creado");

            MongoDatabase db = cliente.getDatabase("comedor_imss");

            System.out.println("Probando conexión...");

            db.runCommand(new org.bson.Document("ping", 1));

            System.out.println("PING OK");

            return db;

        } catch (Exception e) {

            System.out.println("ERROR:");
            e.printStackTrace();

            throw e;
        }
    }

}