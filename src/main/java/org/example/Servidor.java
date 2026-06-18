package org.example;

import com.example.imssregistrocomedor.controller.Registro;
import com.example.imssregistrocomedor.dao.RegistroDAO;
import com.sun.net.httpserver.*;

import java.io.*;
import java.net.InetSocketAddress;
import java.nio.file.Files;


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


            File file =
                    new File(
                            "src/main/resources/templates/index.html"
                    );


            byte[] response =
                    Files.readAllBytes(file.toPath());


            exchange.getResponseHeaders()
                    .set("Content-Type","text/html");


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

            File file =
                    new File(
                            "src/main/resources/static/css/style.css"
                    );


            byte[] response =
                    Files.readAllBytes(file.toPath());


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


            File file =
                    new File(
                            "src/main/resources/static/js/registro.js"
                    );


            byte[] response =
                    Files.readAllBytes(file.toPath());


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



        // GUARDAR REGISTRO

        server.createContext("/registrar", exchange -> {


            if(exchange.getRequestMethod()
                    .equalsIgnoreCase("POST")){


                InputStream is =
                        exchange.getRequestBody();


                String body =
                        new String(
                                is.readAllBytes()
                        );


                System.out.println(body);



                // ejemplo:
                // {"nombre":"Javier","empleadoId":"123"}

                String nombre =
                        body.split("\"nombre\":\"")[1]
                                .split("\"")[0];


                String id =
                        body.split("\"empleadoId\":\"")[1]
                                .split("\"")[0];



                Registro registro =
                        new Registro(
                                nombre,
                                id
                        );


                RegistroDAO dao =
                        new RegistroDAO();


                dao.guardar(registro);



                String respuesta =
                        "OK";


                exchange.sendResponseHeaders(
                        200,
                        respuesta.length()
                );


                exchange.getResponseBody()
                        .write(
                                respuesta.getBytes()
                        );


            }


            exchange.close();

        });



        server.start();


        System.out.println(
                "Servidor iniciado en http://localhost:8080"
        );


    }

}