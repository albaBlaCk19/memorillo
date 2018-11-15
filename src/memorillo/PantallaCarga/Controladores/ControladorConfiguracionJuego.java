/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package memorillo.PantallaCarga.Controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import memorillo.Juego;
import memorillo.PantallaCarga.Vistas.PantallaCargaJuego;
import memorillo.PantallaCarga.Vistas.Vista;

/**
 *
 * @author littl
 */
public class ControladorConfiguracionJuego implements ActionListener, MouseListener {

    private PantallaCargaJuego panelCarga;
    private Juego juego;
    private Vista ventana;

    public ControladorConfiguracionJuego(PantallaCargaJuego panelCarga, Vista ventana) {
        this.panelCarga = panelCarga;
        this.ventana = ventana;

    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        System.out.println(ae.getActionCommand());
        switch (ae.getActionCommand()) {
            case "newGame":
                ventana.crearJuego();

                break;

            case "choosePlayer":
                panelCarga.cambiarEstadoPaneles("cpJugador", true);
                panelCarga.cambiarEstadoBotones(false);
                break;

            case "aceptarPlayer":
                panelCarga.cambiarEstadoPaneles("cpJugador", false);
                panelCarga.cambiarEstadoBotones(true);
                break;

            case "chooseImages":
                panelCarga.cambiarEstadoPaneles("cpImages", true);
                panelCarga.cambiarEstadoBotones(false);
                break;

            case "aceptarImagenes":
                panelCarga.cambiarEstadoPaneles("cpImages", false);
                panelCarga.cambiarEstadoBotones(true);
                break;

            case "chooseLevel":
                panelCarga.cambiarEstadoPaneles("cpLevel", true);
                panelCarga.cambiarEstadoBotones(false);
                break;

            case "aceptarNivel":
                panelCarga.cambiarEstadoPaneles("cpLevel", false);
                panelCarga.cambiarEstadoBotones(true);
                break;

        }
    }

    @Override
    public void mouseClicked(MouseEvent me) {
        switch(me.getComponent().getName()){
            case "easy":
                panelCarga.setNivelSeleccionado("easy");
                System.out.println("Seleccionaste facil");
                break;
                
            case "medium":
                 panelCarga.setNivelSeleccionado("medium");
                 System.out.println("Seleccionaste medio");
                break;
                
            case "hard":
                 panelCarga.setNivelSeleccionado("hard");
                 System.out.println("Seleccionaste dificil");
                break;
        }
    }

    @Override
    public void mousePressed(MouseEvent me) {
        panelCarga.botonPulsado(me.getComponent().getName());

    }

    @Override
    public void mouseReleased(MouseEvent me) {
        
        System.out.println(me.getComponent().getName());
        panelCarga.botonLevantado(me.getComponent().getName());

        switch (me.getComponent().getName()) {
            case "imgInfantil":
                System.out.println("Seleccionaste infantil");
                panelCarga.seleccinarImagen(1);
                panelCarga.setImagenesSeleccionadas("Infantil");
                break;

            case "imgSalamanca":
                panelCarga.seleccinarImagen(2);
                panelCarga.setImagenesSeleccionadas("Salamanca");
                System.out.println("Seleccionaste salamanca");
                break;
                
            
        }
    }

    @Override
    public void mouseEntered(MouseEvent me) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(MouseEvent me) {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
