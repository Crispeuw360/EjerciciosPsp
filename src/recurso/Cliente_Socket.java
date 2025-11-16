package recurso;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Cliente_Socket {
    private final int PUERTO = 5000;
    private final String IP = "127.0.0.1";
    public void iniciar() {
        Socket cliente = null;
        ObjectInputStream entrada = null;
        ObjectOutputStream salida = null;
        try {
            cliente = new Socket(IP, PUERTO);

            salida = new ObjectOutputStream (cliente.getOutputStream());
            entrada = new ObjectInputStream(cliente.getInputStream());

            Persona p = (Persona) entrada.readObject();
            p.getDatos();

            p.setNombre("Carmen");
            p.setFechaNacimiento(1983, 1, 1);

            salida.writeObject(p);

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            try {
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
        Cliente2 c = new Cliente2();
        c.iniciar();
    }
}
