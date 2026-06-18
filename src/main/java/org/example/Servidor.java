package org.example;


import com.example.imssregistrocomedor.controller.Registro;
import com.example.imssregistrocomedor.dao.RegistroDAO;
import com.sun.net.httpserver.*;

import java.io.*;
import java.net.InetSocketAddress;

import com.sun.net.httpserver.*;

import java.io.*;
import java.nio.charset.StandardCharsets;


public class Servidor {


    public static void main(String[] args) throws Exception {


        int puerto = Integer.parseInt(
                System.getenv().getOrDefault("PORT", "8080")
        );


        HttpServer server =
                HttpServer.create(
                        new InetSocketAddress(puerto),
                        0
                );


        // INDEX

        server.createContext("/", exchange -> {


            try {

                InputStream is =
                        Servidor.class
                                .getResourceAsStream("/templates/index.html");


                byte[] response =
                        is.readAllBytes();


                exchange.getResponseHeaders()
                        .set(
                                "Content-Type",
                                "text/html; charset=UTF-8"
                        );


                exchange.sendResponseHeaders(
                        200,
                        response.length
                );


                exchange.getResponseBody()
                        .write(response);


            } finally {

                exchange.close();

            }

        });



        // CSS

        server.createContext("/css/style.css", exchange -> {


            try {

                InputStream is =
                        Servidor.class
                                .getResourceAsStream("/static/css/style.css");


                byte[] response =
                        is.readAllBytes();



                exchange.getResponseHeaders()
                        .set(
                                "Content-Type",
                                "text/css"
                        );


                exchange.sendResponseHeaders(
                        200,
                        response.length
                );


                exchange.getResponseBody()
                        .write(response);



            } finally {

                exchange.close();

            }


        });




        // JS

        server.createContext("/js/registro.js", exchange -> {


            try {


                InputStream is =
                        Servidor.class
                                .getResourceAsStream("/static/js/registro.js");


                byte[] response =
                        is.readAllBytes();



                exchange.getResponseHeaders()
                        .set(
                                "Content-Type",
                                "application/javascript"
                        );



                exchange.sendResponseHeaders(
                        200,
                        response.length
                );


                exchange.getResponseBody()
                        .write(response);



            } finally {

                exchange.close();

            }


        });





        // REGISTRAR


        server.createContext("/registrar", exchange -> {


            try {


                if(!exchange.getRequestMethod()
                        .equalsIgnoreCase("POST")){


                    exchange.sendResponseHeaders(
                            405,
                            0
                    );

                    return;

                }




                String body =
                        new String(
                                exchange.getRequestBody()
                                        .readAllBytes(),
                                "UTF-8"
                        );



                System.out.println("JSON recibido:");
                System.out.println(body);



                String nombre =
                        body.replaceAll(
                                ".*\"nombre\":\"([^\"]+)\".*",
                                "$1"
                        );



                String empleadoId =
                        body.replaceAll(
                                ".*\"empleadoId\":\"([^\"]+)\".*",
                                "$1"
                        );



                System.out.println(
                        "Nombre: " + nombre
                );


                System.out.println(
                        "Empleado: " + empleadoId
                );




                Registro registro =
                        new Registro(
                                nombre,
                                empleadoId
                        );




                RegistroDAO dao =
                        new RegistroDAO();



                dao.guardar(registro);



                String respuesta =
                        "OK";



                exchange.getResponseHeaders()
                        .set(
                                "Content-Type",
                                "text/plain; charset=UTF-8"
                        );



                exchange.sendResponseHeaders(
                        200,
                        respuesta.length()
                );



                exchange.getResponseBody()
                        .write(
                                respuesta.getBytes()
                        );



                System.out.println("Respuesta enviada");




            }catch(Exception e){



                System.out.println(
                        "ERROR EN REGISTRO"
                );


                e.printStackTrace();



                String error =
                        "ERROR";



                exchange.sendResponseHeaders(
                        500,
                        error.length()
                );



                exchange.getResponseBody()
                        .write(
                                error.getBytes()
                        );



            }finally{


                exchange.close();


            }



        });




        server.start();



        System.out.println(
                "Servidor iniciado puerto: " + puerto
        );


    }

}