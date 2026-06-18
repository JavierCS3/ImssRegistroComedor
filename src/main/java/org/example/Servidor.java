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
                System.getenv().getOrDefault("PORT","8080")
        );


        HttpServer server =
                HttpServer.create(
                        new InetSocketAddress(puerto),
                        0
                );


        // INDEX

        server.createContext("/", exchange -> {


            InputStream is =
                    Servidor.class
                            .getResourceAsStream("/templates/index.html");


            byte[] response =
                    is.readAllBytes();



            exchange.getResponseHeaders()
                    .set(
                            "Content-Type",
                            "text/html"
                    );


            exchange.sendResponseHeaders(
                    200,
                    response.length
            );


            exchange.getResponseBody()
                    .write(response);


            exchange.close();

        });



        // CSS

        server.createContext("/css/style.css", exchange -> {


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


            exchange.close();

        });



        // JS

        server.createContext("/js/registro.js", exchange -> {


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


            exchange.close();

        });



        // REGISTRAR


        server.createContext("/registrar", exchange -> {


            try {


                if(!exchange.getRequestMethod()
                        .equalsIgnoreCase("POST")){


                    exchange.sendResponseHeaders(405,0);
                    exchange.close();
                    return;

                }



                String body =
                        new String(
                                exchange.getRequestBody()
                                        .readAllBytes()
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



                try {

                    dao.guardar(registro);

                    String respuesta = "OK";

                    exchange.sendResponseHeaders(
                            200,
                            respuesta.length()
                    );

                    exchange.getResponseBody()
                            .write(respuesta.getBytes());

                } catch (Exception e) {

                    e.printStackTrace();

                    String error = "ERROR";

                    exchange.sendResponseHeaders(
                            500,
                            error.length()
                    );

                    exchange.getResponseBody()
                            .write(error.getBytes());

                }



                String respuesta =
                        "Guardado";



                exchange.sendResponseHeaders(
                        200,
                        respuesta.length()
                );


                exchange.getResponseBody()
                        .write(
                                respuesta.getBytes()
                        );



            }catch(Exception e){


                System.out.println(
                        "ERROR EN REGISTRO"
                );


                e.printStackTrace();



                String error =
                        e.toString();



                exchange.sendResponseHeaders(
                        500,
                        error.length()
                );


                exchange.getResponseBody()
                        .write(
                                error.getBytes()
                        );

            }



            exchange.close();


        });



        server.start();



        System.out.println(
                "Servidor iniciado puerto: " + puerto
        );


    }

}