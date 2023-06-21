package com.example.basesdedatos;

import java.io.*;
import java.util.PriorityQueue;

class Node {
    char caracter;

    int frecuencia;
    Node izquierdo, derecho;

    public Node(char caracter, int frecuencia) {
        this.caracter = caracter;
        this.frecuencia = frecuencia;
        izquierdo = derecho = null;
    }
}

class ArchivoCodificador {
    public static void main(String[] args) throws IOException {
        String rutaEntrada = "entrada.txt";
        String rutaCodificada = "archivo_codificado.txt";
        String rutaDecodificada = "archivo_decodificado.txt";

        // Codificar archivo
        codificarArchivo(rutaEntrada, rutaCodificada);

        // Decodificar archivo
        decodificarArchivo(rutaCodificada, rutaDecodificada);
    }

    public static void codificarArchivo(String rutaEntrada, String rutaCodificada) throws IOException {
        // Leer archivo de entrada
        String texto = leerArchivo(rutaEntrada);

        // Calcular frecuencia de caracteres
        int[] frecuencias = calcularFrecuencias(texto);

        // Construir árbol de codificación
        Node raiz = construirArbol(frecuencias);

        // Crear tabla de codificación
        String[] tablaCodificacion = new String[256];
        construirTablaCodificacion(raiz, "", tablaCodificacion);

        // Codificar texto
        StringBuilder textoCodificado = new StringBuilder();
        for (int i = 0; i < texto.length(); i++) {
            char c = texto.charAt(i);
            textoCodificado.append(tablaCodificacion[c]);
        }

        // Guardar texto codificado en archivo
        guardarArchivo(rutaCodificada, textoCodificado.toString());

        // Calcular tasa de compresión
        double tasaCompresion = (double) textoCodificado.length() / (double) (texto.length() * 8);
        System.out.println("El texto se ha codificado. Tasa de compresión: " + tasaCompresion);
    }

    public static void decodificarArchivo(String rutaCodificada, String rutaDecodificada) throws IOException {
        // Leer archivo codificado
        String textoCodificado = leerArchivo(rutaCodificada);

        // Leer árbol de codificación
        Node raiz = leerArbolCodificacion(textoCodificado);

        // Decodificar texto
        StringBuilder textoDecodificado = new StringBuilder();
        Node nodoActual = raiz;
        for (int i = 0; i < textoCodificado.length(); i++) {
            if (textoCodificado.charAt(i) == '0') {
                nodoActual = nodoActual.izquierdo;
            } else {
                nodoActual = nodoActual.derecho;
            }

            if (nodoActual.izquierdo == null && nodoActual.derecho == null) {
                textoDecodificado.append(nodoActual.caracter);
                nodoActual = raiz;
            }
        }

        // Guardar texto decodificado en archivo
        guardarArchivo(rutaDecodificada, textoDecodificado.toString());
    }

    public static String leerArchivo(String ruta) throws IOException {
        StringBuilder contenido = new StringBuilder();
        BufferedReader lector = new BufferedReader(new FileReader(ruta));
        String linea;
        while ((linea = lector.readLine()) != null) {
            contenido.append(linea).append("\n");
        }
        lector.close();
        return contenido.toString();
    }

    public static void guardarArchivo(String ruta, String contenido) throws IOException {
        BufferedWriter escritor = new BufferedWriter(new FileWriter(ruta));
        escritor.write(contenido);
        escritor.close();
    }

    public static int[] calcularFrecuencias(String texto) {
        int[] frecuencias = new int[256];
        for (int i = 0; i < texto.length(); i++) {
            char c = texto.charAt(i);
            frecuencias[c]++;
        }
        return frecuencias;
    }

    public static Node construirArbol(int[] frecuencias) {
        PriorityQueue<Node> colaPrioridad = new PriorityQueue<>((a, b) -> a.frecuencia - b.frecuencia);
        for (int i = 0; i < 256; i++) {
            if (frecuencias[i] > 0) {
                colaPrioridad.add(new Node((char) i, frecuencias[i]));
            }
        }

        while (colaPrioridad.size() > 1) {
            Node izquierdo = colaPrioridad.poll();
            Node derecho = colaPrioridad.poll();
            Node padre = new Node('\0', izquierdo.frecuencia + derecho.frecuencia);
            padre.izquierdo = izquierdo;
            padre.derecho = derecho;
            colaPrioridad.add(padre);
        }

        return colaPrioridad.poll();
    }

    public static void construirTablaCodificacion(Node nodo, String codigo, String[] tablaCodificacion) {
        if (nodo.izquierdo == null && nodo.derecho == null) {
            tablaCodificacion[nodo.caracter] = codigo;
            return;
        }

        construirTablaCodificacion(nodo.izquierdo, codigo + "0", tablaCodificacion);
        construirTablaCodificacion(nodo.derecho, codigo + "1", tablaCodificacion);
    }

    public static Node leerArbolCodificacion(String textoCodificado) {
        int indice = 0;
        return leerArbolCodificacionRecursivo(textoCodificado, indice);
    }

    public static Node leerArbolCodificacionRecursivo(String textoCodificado, int indice) {
        if (indice >= textoCodificado.length()) {
            return null;
        }

        char c = textoCodificado.charAt(indice);
        if (c == '1') {
            Node nodo = new Node('\0', 0);
            nodo.izquierdo = leerArbolCodificacionRecursivo(textoCodificado, indice + 1);
            nodo.derecho = leerArbolCodificacionRecursivo(textoCodificado, indice + 1);
            return nodo;
        } else {
            return new Node(textoCodificado.charAt(indice + 1), 0);
        }
    }
}
