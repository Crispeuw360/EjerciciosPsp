/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio2;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Igor
 */
public class Servidor {

    private final int PUERTOENTRADA = 5000;
    private final int PUERTOSALIDA = 5001;

    public void iniciar() {
        String mensajeRecibido = "";
        String mensajeEnviado = "";
        ServerSocket servidorEntrada = null;
        ServerSocket servidorSalida = null;
        Socket clienteEntrada = null;
        Socket clienteSalida = null;
        ObjectInputStream entrada = null;
        ObjectOutputStream salida = null;
        try {
            servidorEntrada = new ServerSocket(PUERTOENTRADA);
            servidorSalida = new ServerSocket(PUERTOSALIDA);
            while (true) {
                System.out.println("Esperando conexiones del cliente...");
                
                clienteEntrada = servidorEntrada.accept();
                System.out.println("Cliente conectado para enviar Peticiones.");
                
                clienteSalida = servidorSalida.accept();
                System.out.println("Cliente conectado para recibir Peticiones.");
                
                salida = new ObjectOutputStream(clienteEntrada.getOutputStream());
                entrada = new ObjectInputStream(clienteSalida.getInputStream());

                mensajeRecibido = (String) entrada.readObject();

                if (mensajeRecibido.equalsIgnoreCase("motor")) {
                    mensajeEnviado = "MOTOR=ACTIVO";
                } else if (mensajeRecibido.equalsIgnoreCase("Termometro")) {
                    mensajeEnviado = "TEMPERATURA=23.5º";
                } else {
                    System.out.println("Mensaje invalido");
                    mensajeEnviado = "ERROR";

                }

                salida.writeObject(mensajeEnviado);
                System.out.println("respuesta enviada: " + mensajeEnviado);
                
                entrada.close();
                salida.close();
                clienteEntrada.close();
                clienteSalida.close();
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            try {
                
                if (servidorEntrada != null) {
                    servidorEntrada.close();
                }
                if (servidorSalida != null) {
                    servidorSalida.close();
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        Servidor s = new Servidor();
        s.iniciar();
    }
}
