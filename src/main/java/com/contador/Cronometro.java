package com.contador;
import javax.swing.*;
import java.awt.*;

public class Cronometro extends Thread{
    static int NUMERO_CONTADORES = 0;
    static int TIEMPO_ESPERA = 10 ;

    private JPanel panelCronometro = new JPanel() ;
    private JLabel labelTiempo = new JLabel("<html><h1>00:00:00:00</h1></html>");
    private JButton btnActivarCronometro = new JButton("Iniciar");
    private JButton btnPausarCronometro = new JButton("Detener");
    private JButton btnReiniciarCronometro = new JButton("Reiniciar");

    private long tiempoGuardado, tiempoInicio, tiempoActual ;
    private int milisegundos, segundos, minutos, horas ;
    private boolean estaPausado = true ;

    public Cronometro() {
        NUMERO_CONTADORES ++ ;

        panelCronometro.setLayout(new BorderLayout());
        labelTiempo.setHorizontalAlignment(SwingConstants.CENTER);
        panelCronometro.add(labelTiempo, BorderLayout.NORTH);


        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelBotones.add(btnActivarCronometro);
        panelBotones.add(btnPausarCronometro);
        panelBotones.add(btnReiniciarCronometro);

        panelCronometro.add(panelBotones, BorderLayout.CENTER);
        panelCronometro.setVisible(true);

        btnActivarCronometro.addActionListener(e -> inicializarCronometro());
        btnPausarCronometro.addActionListener(e -> detenerCronometro());
        btnReiniciarCronometro.addActionListener(e -> reiniciarCronometro());
        this.start();

    }

    @Override
    public void run(){
        while(true){
            try{

                if(!estaPausado){
                    tiempoActual = System.nanoTime() ;
                    long tiempoPasado = (tiempoGuardado + (tiempoActual - tiempoInicio)) ;
                    actualizarTiempo(tiempoPasado);
                }

                Thread.sleep(TIEMPO_ESPERA);
            }catch (InterruptedException e) {
                System.out.println("Cronómetro interrumpido");
            }
        }
    }

    public void inicializarCronometro(){
        if(estaPausado) {
            tiempoInicio = System.nanoTime() ;
            estaPausado = false ;
        }
    }
    private void detenerCronometro(){

        if(!estaPausado) {
            estaPausado = true ;
            tiempoGuardado += tiempoActual - tiempoInicio ; // chance usar el System.nanoTime() ;
        }
    }
    private void reiniciarCronometro(){
        tiempoGuardado = 0 ;
        estaPausado = true ;
        milisegundos = segundos = minutos = horas = 0;
        actualizarEtiqueta();
    }

    private void actualizarTiempo(long tiempoPasado){
        milisegundos = (int) (tiempoPasado / 1_000_000) % 1000;
        segundos = (int) (tiempoPasado / 1_000_000_000) % 60;
        minutos = (int) (tiempoPasado / 60_000_000_000L) % 60;
        horas = (int) (tiempoPasado / 3_600_000_000_000L);
        actualizarEtiqueta() ;
    }

    private void actualizarEtiqueta(){
        labelTiempo.setText(String.format("<html><h1>%02d:%02d:%02d:%02d</h1></html>", horas, minutos, segundos, milisegundos / 10));

    }

    public JPanel getPanelCronometro(){
        return panelCronometro ;
    }
}
