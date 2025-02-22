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
                    actualizarTiempo();
                }
                
                Thread.sleep(10);
            }catch (InterruptedException e) {
                System.out.println("Cronómetro interrumpido");
            }
        }
    }

    public void inicializarCronometro(){
        if(estaPausado) {
            estaPausado = false ;
        }
    }
    private void detenerCronometro(){
        if(!estaPausado) {
            estaPausado = true ;
        }
    }
    private void reiniciarCronometro(){

        estaPausado = true ;
        milisegundos = segundos = minutos = horas = 0;
        actualizarEtiqueta();
    }

    private void actualizarTiempo(){
        milisegundos += 10 ;
        if(milisegundos == 1000){
            segundos ++ ;
            milisegundos = 0;
        }
        if(segundos == 60){
            segundos = 0;
            minutos ++ ;
        }
        if(minutos == 60){
            minutos = 0;
            horas ++;
        }
        actualizarEtiqueta() ;
    }

    private void actualizarEtiqueta(){
        labelTiempo.setText(String.format("<html><h1>%02d:%02d:%02d:%02d</h1></html>", horas, minutos, segundos, milisegundos / 10));

    }

    public JPanel getPanelCronometro(){
        return panelCronometro ;
    }
}
