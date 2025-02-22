package com.contador;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class ListaCronometros {
    private JFrame frame = new JFrame("Lista de cronometros") ;
    private JPanel panelCronometros ;
    private JButton btnAgregarCronometro = new JButton("+ (0)") ; ;
    ArrayList<Cronometro> listaCronometros = new ArrayList<>() ;
    public ListaCronometros(){
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        panelCronometros = new JPanel() ;
        panelCronometros.setLayout(new BoxLayout(panelCronometros, BoxLayout.Y_AXIS));

        btnAgregarCronometro.addActionListener(e -> agregarCronometro()) ;

        frame.add(btnAgregarCronometro, BorderLayout.NORTH);
        frame.add(new JScrollPane(panelCronometros), BorderLayout.CENTER) ;
        frame.setVisible(true);

    }
    private void agregarCronometro(){
        Cronometro cronometro = new Cronometro() ;
        listaCronometros.add(cronometro) ;
        panelCronometros.add(cronometro.getPanelCronometro()) ;
        panelCronometros.revalidate();
        btnAgregarCronometro.setText("+ (" + (Integer.toString(Cronometro.NUMERO_CONTADORES)) + ')');
    }
}
