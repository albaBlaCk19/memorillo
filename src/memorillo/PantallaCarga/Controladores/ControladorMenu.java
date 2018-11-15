/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package memorillo.PantallaCarga.Controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import memorillo.PantallaCarga.Vistas.PantallaCargaJuego;
import memorillo.PantallaCarga.Vistas.Vista;

/**
 *
 * @author Agus-PC
 */
public class ControladorMenu implements ActionListener {

    private Vista ventana;
    private PantallaCargaJuego pantallaCarga;
    private String desarrolladores = "Agustin De Los Dolores \nAlba Duran";

    public ControladorMenu(PantallaCargaJuego pantallaCarga,Vista ventana) {
        this.pantallaCarga = pantallaCarga;
        this.ventana=ventana;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {

        System.out.println(ae.getActionCommand());

        switch (ae.getActionCommand()) {
            case "New Game":
                ventana.crearJuego();
                break;
            case "Save Game":
                // lo que sea

                break;
            case "Load Game":
                // lo que sea

                break;

            case "Exit":

                System.exit(0);

                break;
            case "Highscore":
                
                break;

            case  "How To Play":
                break;
            case "About us":

                JOptionPane.showMessageDialog(pantallaCarga, desarrolladores, "Desarrollado por:", JOptionPane.INFORMATION_MESSAGE);

                break;

        }

    }

}
