package com.steepdiaz.proyecto1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
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

    public static ArrayList<Usuario> obtener() {
        ArrayList<Usuario> usuarios = new ArrayList<>();
        FileReader fileReader = null;
        BufferedReader bufferedReader = null;
        try {
            fileReader = new FileReader(NOMBRE_ARCHIVO);
            bufferedReader = new BufferedReader(fileReader);
            String linea;
            while ((linea = bufferedReader.readLine()) != null) {
                String[] usuariosComoArreglo = linea.split(SEPARADOR_CAMPO);
                usuarios.add(new Usuario(usuariosComoArreglo[0], usuariosComoArreglo[1]));
            }
        } catch (IOException e) {
            System.out.println("Excepción leyendo archivo: " + e.getMessage());
        } finally {
            try {
                if (fileReader != null) {
                    fileReader.close();
                }
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
            } catch (IOException e) {
                System.out.println("Excepción cerrando: " + e.getMessage());
            }
            return usuarios;
        }
    }

    public static void imprimirUsuarios(ArrayList<Usuario> usuario) {
        ArrayList<Prestamo> prestamos = ControladorPrestamos.obtener();
        System.out.println(
                "+-----+----------+----------------------------------------+---------------------+");
        System.out.printf("|%-5s|%-10s|%-40s|%-20s|\n", "#", "No. Usuario", "Nombre","Libros prestados");
        System.out.println(
                "+-----+----------+----------------------------------------+---------------------+");
        for (int x = 0; x < usuario.size(); x++) {
            Usuario usuarios = usuario.get(x);
            System.out.printf("|%-5s|%-10s|%-40s|%-20s|\n", x + 1, usuarios.getNumero(), usuarios.getNombre(),
                    ControladorPrestamos.cantidadLibrosPrestados(usuarios.getNumero(), prestamos));
            System.out.println(
                "+-----+----------+----------------------------------------+---------------------+");
        }
    }

    public static int buscarUsuarioPorNumero(String numero, ArrayList<Usuario> usuarios) {
        for (int x = 0; x < usuarios.size(); x++) {
            Usuario usuario = usuarios.get(x);
            if (usuario.getNumero().equals(numero)) {
                return x;
            }
        }
        return -1;
    }

    public static Usuario imprimirUsuarioYPedirSeleccion() {
        ArrayList<Usuario> usuarios = ControladorUsuarios.obtener();
        Scanner sc = new Scanner(System.in);
        while (true) {
            ControladorUsuarios.imprimirUsuarios(usuarios);
            System.out.println("Ingrese el numero de Usuario: ");
            String numero = sc.nextLine();
            int indice = ControladorUsuarios.buscarUsuarioPorNumero(numero, usuarios);
            if (indice == -1) {
                System.out.println("No existe socio con ese numero");
            } else {
                return usuarios.get(indice);
            }
        }
    }
}
