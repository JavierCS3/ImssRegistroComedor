package com.example.imssregistrocomedor.conection;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class Conexion {


    private static final String URL =
            System.getenv().getOrDefault(
                    "MONGO_URI",
                    "mongodb+srv://javierefrencs_db_user:J123456@imssregistro.kau1t1i.mongodb.net/?appName=ImssRegistro"
            );


    private static final MongoClient cliente =
            MongoClients.create(URL);



    public static MongoDatabase conectar(){


        return cliente.getDatabase(
                "comedor_imss"
        );

    }

}