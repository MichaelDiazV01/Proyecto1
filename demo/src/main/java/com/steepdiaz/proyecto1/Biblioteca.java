package com.steepdiaz.proyecto1;

import java.util.Scanner;

public class Biblioteca {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String eleccion = "";
        while (!eleccion.equals("9")) {
            System.out.println(
                    "1. Registrar usuario\n2. Registrar Libro\n7. Salir\nElige:");
            eleccion = sc.nextLine();
            if (eleccion.equals("1")) {
                ControladorUsuarios.solicitarDatosParaRegistrar();
            }
            if (eleccion.equals("2")) {
                ControladorLibros.solicitarDatosParaRegistrar();
            }
            if (eleccion.equals("7")) {
                System.exit(0);
            }

        }
    }
}
