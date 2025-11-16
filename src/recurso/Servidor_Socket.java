package recurso;

import java.net.ServerSocket;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Servidor_Socket {
    private final int PUERTO = 5000;
    public void iniciar() {
        ServerSocket servidor = null;
        Socket cliente = null;
        ObjectInputStream entrada = null;
        ObjectOutputStream salida = null;
        try {
            servidor = new ServerSocket(PUERTO);
            System.out.println("Esperando conexiones del cliente...");
            cliente = servidor.accept();
            System.out.println("Cliente conectado.");
 	    salida = new ObjectOutputStream (cliente.getOutputStream());
            entrada = new ObjectInputStream(cliente.getInputStream());
            Persona p = new Persona();
            p.setNif("16078123X");
            p.setNombre("Pepito");
            p.setApellido("Grillo");
            p.setFechaNacimiento(1990, 12, 12);
            p.setGenero('M');

            salida.writeObject(p);

            p = (Persona) entrada.readObject();
            p.getDatos();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            try {
                if (servidor != null)
                    servidor.close();
                if (cliente != null)
                    cliente.close();
                if (entrada != null)
                    entrada.close();
                if (salida != null)
                    salida.close();
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
    public static void main(String[] args) {
        Servidor2 s = new Servidor2();
        s.iniciar();
    }
}
