package com.example.imssregistrocomedor.conection;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;


public class Conexion {

    private static final String URL =
            "mongodb+srv://javierefrencs_db_user:J123456@imssregistro.kau1t1i.mongodb.net/?appName=ImssRegistro";

    private static MongoClient cliente;


    public static MongoDatabase conectar(){

        cliente = MongoClients.create(URL);

        return cliente.getDatabase("comedor_imss");
    }

}