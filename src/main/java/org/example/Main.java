package org.example;

import com.example.imssregistrocomedor.conection.Servidor;
import com.example.imssregistrocomedor.controller.Registro;
import com.example.imssregistrocomedor.dao.RegistroDAO;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {




        public static void main(String[] args) {

            try {

                Servidor.main(args);

            } catch (Exception e) {

                e.printStackTrace();

            }

        }



}