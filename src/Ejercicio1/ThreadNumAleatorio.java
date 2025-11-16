/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ejercicio1;

import javax.swing.JLabel;
import javax.swing.SwingUtilities;

/**
 *
 * @author Igor
 */
public class ThreadNumAleatorio implements Runnable {

    private JLabel label1, label2, label3;
    private int numero1, numero2, numero3;
    private boolean activo1, activo2, activo3;
    private int pause = 1;
    public Thread hilo;

    public ThreadNumAleatorio(JLabel label1, JLabel label2, JLabel label3) {
        this.label1 = label1;
        this.label2 = label2;
        this.label3 = label3;
        this.activo1 = true;
        this.activo2 = true;
        this.activo3 = true;
        this.numero1 = 0;
        this.numero2 = 0;
        this.numero3 = 0;
    }

    public void start() {
        System.out.println("empezando numeros aleatorios");
        hilo = new Thread(this, "NumAleatorioThread");
        hilo.start();

    }

    public void run() {
        try {
            while ((activo1 || activo2 || activo3) && pause != 3) {
                if (pause == 1) {
                    Thread.sleep(1000);
                    if (activo1) {
                        numero1 = (int) (Math.random() * 34);
                        if (numero1 == 33) {
                            activo1 = false;
                        }
                    }
                    if (activo2) {
                        numero2 = (int) (Math.random() * 34);
                        if (numero2 == 33) {
                            activo2 = false;
                        }

                    }
                    if (activo3) {
                        numero3 = (int) (Math.random() * 34);
                        if (numero3 == 33) {
                            activo3 = false;
                        }
                    }
                        this.actualizarDisplay();
                    }

                }
            }catch(InterruptedException e){
        
        }
        }
    
     

    private void actualizarDisplay() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                label1.setText(Integer.toString(numero1));
                label2.setText(Integer.toString(numero2));
                label3.setText(Integer.toString(numero3));
            }
        });

    }

    public void stop() {
        this.pause = 3;
        this.activo1 = false;
        this.activo2 = false;
        this.activo3 = false;
    }

    public int getNumero1() {
        return numero1;
    }

    public void setNumero1(int numero1) {
        this.numero1 = numero1;
    }

    public int getNumero2() {
        return numero2;
    }

    public void setNumero2(int numero2) {
        this.numero2 = numero2;
    }

    public int getNumero3() {
        return numero3;
    }

    public void setNumero3(int numero3) {
        this.numero3 = numero3;
    }

    public boolean isActivo1() {
        return activo1;
    }

    public void setActivo1(boolean activo1) {
        this.activo1 = activo1;
    }

    public boolean isActivo2() {
        return activo2;
    }

    public void setActivo2(boolean activo2) {
        this.activo2 = activo2;
    }

    public boolean isActivo3() {
        return activo3;
    }

    public void setActivo3(boolean activo3) {
        this.activo3 = activo3;
    }

   

}
