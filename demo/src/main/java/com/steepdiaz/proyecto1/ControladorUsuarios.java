package com.steepdiaz.proyecto1;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class ControladorUsuarios {

    private static final String NOMBRE_ARCHIVO = "usuarios.txt";
    private static final String SEPARADOR_CAMPO = ";";
    private static final String SEPARADOR_REGISTRO = "\n";

    public static void solicitarDatosParaRegistrar() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingrese numero de usuario: ");
        String numero = sc.nextLine();
        System.out.println("Ingrese nombre de usuario: ");
        String nombre = sc.nextLine();
        ControladorUsuarios.registrar(new Usuario(numero,nombre));
        System.out.println("Registrado exitosamente");
    }

    public static void registrar(Usuario usuario) {
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(NOMBRE_ARCHIVO, true));
            bufferedWriter.write(usuario.getNumero() + SEPARADOR_CAMPO + usuario.getNombre() + SEPARADOR_REGISTRO);
            bufferedWriter.close();
        } catch (IOException e) {
            System.out.println("Error escribiendo en archivo: " + e.getMessage());
        }
    }
}