package com.example.imssregistrocomedor.conection;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class Conexion {


    private static final String URL =
            "mongodb+srv://javierefrencs_db_user:J123456@imssregistro.kau1t1i.mongodb.net/?appName=ImssRegistro";


    private static MongoClient cliente;



    public static MongoDatabase conectar(){


        try{


            System.out.println("Creando cliente Mongo");


            cliente = MongoClients.create(URL);



            System.out.println("Cliente creado");


            MongoDatabase db =
                    cliente.getDatabase("comedor_imss");



            System.out.println("Base obtenida");


            return db;



        }catch(Exception e){


            System.out.println("ERROR MONGO");


            e.printStackTrace();


            throw e;

        }


    }

}