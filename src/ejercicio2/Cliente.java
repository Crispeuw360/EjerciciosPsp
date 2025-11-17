/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio2;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 *
 * @author Igor
 */
public class Cliente {
    private final int PUERTO1 = 5000;
    private final int PUERTO2 = 5001;
    private final String IP = "127.0.0.1";
    public void iniciar(String mensaje) {
        Socket clienteEntrada = null;
        Socket clienteSalida = null;
        ObjectInputStream entrada = null;
        ObjectOutputStream salida = null;
        String mensajeRecibido = "";
        try {
            clienteSalida = new Socket(IP, PUERTO2);
            clienteEntrada= new Socket(IP, PUERTO1);

            salida = new ObjectOutputStream (clienteSalida.getOutputStream());
            entrada = new ObjectInputStream(clienteEntrada.getInputStream());

            salida.writeObject(mensaje);
            System.out.println("enviando mensaje = " + mensaje);
            
            mensajeRecibido = (String) entrada.readObject();
            System.out.println("Mensaje recibido = " + mensajeRecibido);

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            try {
                if (clienteSalida != null)
                    clienteSalida.close();
                if (clienteEntrada != null)
                    clienteEntrada.close();
                if (salida != null)
                    salida.close();
                if(entrada != null){
                    entrada.close();
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
    
   
    public static void main(String[] args) {
        Cliente c1 = new Cliente();
        c1.iniciar("termometro");
        c1.iniciar("motor");
        
    }
}
