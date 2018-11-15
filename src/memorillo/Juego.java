/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package memorillo;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 *
 * @author littl
 */
public class Juego extends JPanel {

    private final int EASY = 6;
    private final int MEDIUM = 8;
    private final int HARD = 10;

    private Cronometro cronometro;
    private ContadorJugadas contadorJugadas;

    private JPanel pCartas;
    private JPanel pCartasEncontrar;
    private JPanel pDatosJugador;
    private Image imagenFondo;

    private ArrayList<JFlip> jfCartas;

    public Juego(String nivel, String ImagenesSeleccionadas) {

        this.setLayout(null);
        this.setBounds(0, 0, 1500, 1000);
        this.setBackground(Color.yellow);

        asignarImagenFondo(ImagenesSeleccionadas);
        cpCartas(nivel, ImagenesSeleccionadas);
        cpCartasEncontrar();
        cpDatosJugador();
        crearContadorJugadas();
        crearCronometro();

    }

    
    public void paint(Graphics g) {
        g.drawImage(imagenFondo, 0, 0, getWidth(), getHeight(), this);  //Con la imagen, la posicion respecto del panel (0,0 porque quiero que ocupe todo), y que coja el ancho y el alto
        setOpaque(false);       //le ponemos la opacidad a false para que se muestre
        super.paint(g); //pintamos
    }
    
    
    
    public void asignarImagenFondo(String imagenesSeleccionadas){
        switch(imagenesSeleccionadas){
            case "Infantil":
                this.imagenFondo = new ImageIcon(getClass().getResource("/img/fondoInfantilJuego.png")).getImage();
                break;
                
            case "Salamanca":
                this.imagenFondo= new ImageIcon(getClass().getResource("/img/fondoInfantilJuego.png")).getImage();
        }
    }
    
    
    public void cpCartas(String nivel, String ImagenesSeleccionadas) {

        switch (nivel) {
            case "easy":
                pCartas = new JPanel();
                pCartas.setLayout(new GridLayout(3, 6, 10, 10));
                pCartas.setOpaque(false);
                pCartas.setBounds(30, 30, 950, 750);

                crearCartas(nivel, ImagenesSeleccionadas);
                this.add(pCartas);

                break;

            case "medium":
                pCartas = new JPanel();
                pCartas.setLayout(new GridLayout(4, 6, 10, 10));
                pCartas.setBounds(30, 30, 950, 750);

                crearCartas(nivel, ImagenesSeleccionadas);
                this.add(pCartas);

                break;

            case "hard":
                pCartas = new JPanel();
                pCartas.setLayout(new GridLayout(4, 5, 10, 10));
                pCartas.setBounds(30, 30, 950, 750);

                crearCartas(nivel, ImagenesSeleccionadas);
                this.add(pCartas);

                break;
        }

    }

    
    /**
     * Metodo crearCartas()
     *
     * @param nivel
     * @param ImagenesSeleccionadas
     */
    public void crearCartas(String nivel, String ImagenesSeleccionadas) {
        jfCartas = new ArrayList();
        switch (nivel) {
            case "easy":

                if (ImagenesSeleccionadas.equals("Infantil")) {
                    for (int i = 0; i < EASY; i++) {
                        System.out.println("/img/ImagenesSalamanca/" + i + ".png");
                        ImageIcon iconBack = new ImageIcon(this.getClass().getResource("/img/ImagenesInfantiles/" + i + ".png"));
                        jfCartas.add(new JFlip("Salamanca", iconBack));
                        jfCartas.add(new JFlip("Salamanca", iconBack));

                    }

                    Collections.shuffle(jfCartas);
                    for (int i = 0; i < jfCartas.size(); i++) {
                        pCartas.add(jfCartas.get(i));
                    }
                } else {
                    for (int i = 0; i < EASY; i++) {
                        System.out.println("/img/ImagenesSalamanca/" + i + ".png");
                        ImageIcon iconBack = new ImageIcon(this.getClass().getResource("/img/ImagenesSalamanca/" + i + ".png"));
                        jfCartas.add(new JFlip("Salamanca", iconBack));
                        jfCartas.add(new JFlip("Salamanca", iconBack));

                    }

                    Collections.shuffle(jfCartas);
                    for (int i = 0; i < jfCartas.size(); i++) {
                        pCartas.add(jfCartas.get(i));
                    }
                }

                break;
            case "medium":

                if (ImagenesSeleccionadas.equals("Infantil")) {
                    for (int i = 0; i < MEDIUM; i++) {
                        System.out.println("/img/ImagenesSalamanca/" + i + ".png");
                        ImageIcon iconBack = new ImageIcon(this.getClass().getResource("/img/ImagenesInfantiles/" + i + ".png"));
                        jfCartas.add(new JFlip("Salamanca", iconBack));
                        jfCartas.add(new JFlip("Salamanca", iconBack));

                    }

                    Collections.shuffle(jfCartas);
                    for (int i = 0; i < jfCartas.size(); i++) {
                        pCartas.add(jfCartas.get(i));
                    }
                } else {

                    System.out.println("carga de imagenes");

                    for (int i = 0; i < MEDIUM; i++) {
                        System.out.println("/img/ImagenesSalamanca/" + i + ".png");
                        ImageIcon iconBack = new ImageIcon(this.getClass().getResource("/img/ImagenesSalamanca/" + i + ".png"));
                        jfCartas.add(new JFlip("Salamanca", iconBack));
                        jfCartas.add(new JFlip("Salamanca", iconBack));

                    }

                    Collections.shuffle(jfCartas);
                    for (int i = 0; i < jfCartas.size(); i++) {
                        pCartas.add(jfCartas.get(i));
                    }

                }
                break;

            case "hard":
                if (ImagenesSeleccionadas.equals("Infantil")) {
                    for (int i = 0; i < HARD; i++) {
                        System.out.println("/img/ImagenesSalamanca/" + i + ".png");
                        ImageIcon iconBack = new ImageIcon(this.getClass().getResource("/img/ImagenesInfantiles/" + i + ".png"));
                        jfCartas.add(new JFlip("Salamanca", iconBack));
                        jfCartas.add(new JFlip("Salamanca", iconBack));

                    }

                    Collections.shuffle(jfCartas);
                    for (int i = 0; i < jfCartas.size(); i++) {
                        pCartas.add(jfCartas.get(i));
                    }
                } else {
                    for (int i = 0; i < HARD; i++) {
                        System.out.println("/img/ImagenesSalamanca/" + i + ".png");
                        ImageIcon iconBack = new ImageIcon(this.getClass().getResource("/img/ImagenesSalamanca/" + i + ".png"));
                        jfCartas.add(new JFlip("Salamanca", iconBack));
                        jfCartas.add(new JFlip("Salamanca", iconBack));

                    }

                    Collections.shuffle(jfCartas);
                    for (int i = 0; i < jfCartas.size(); i++) {
                        pCartas.add(jfCartas.get(i));
                    }
                }
                break;
        }

    }

    public void cpCartasEncontrar() {

        pCartasEncontrar = new JPanel();
        pCartasEncontrar.setLayout(new FlowLayout());
        pCartasEncontrar.setBackground(Color.BLUE);
        pCartasEncontrar.setBounds(30, 790, 1200, 125);

        this.add(pCartasEncontrar);

    }

    public void cpDatosJugador() {

        pDatosJugador = new JPanel();
        pDatosJugador.setLayout(null);
        pDatosJugador.setBackground(Color.WHITE);
        pDatosJugador.setBounds(1000, 30, 450, 350);

        this.add(pDatosJugador);

    }

    public void crearContadorJugadas() {

        contadorJugadas = new ContadorJugadas();
        this.add(contadorJugadas);

    }

    public void crearCronometro() {
        cronometro = new Cronometro();
        this.add(cronometro);
    }

}
