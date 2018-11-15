/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package memorillo.PantallaCarga.Vistas;

import memorillo.PantallaCarga.Controladores.ControladorConfiguracionJuego;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author littl
 */
public class PantallaCargaJuego extends JPanel {

    private Vista ventana;
    private ControladorConfiguracionJuego controladorConfiguracion;
    private CustomPanelJugador cpJugador;
    private CustomPanelGrupoImagenes cpGrupoImagenes;
    private CustomPanelNiveles cpLevel;
    private Image imagenFondo;
    private JButton botonNewGame;
    private JButton botonPlayer;
    private JButton botonLevel;
    private JButton botonImages;

    private Image iconoBoton = new ImageIcon(getClass().getResource("/img/cursorNormal.png")).getImage();
    private Cursor cursorBoton = Toolkit.getDefaultToolkit().createCustomCursor(iconoBoton, new Point(0, 0), "cursorBoton");
    private Image iconoBotonPulsado = new ImageIcon(getClass().getResource("/img/cursorPulsado.png")).getImage();
    private Cursor cursorBotonPulsado = Toolkit.getDefaultToolkit().createCustomCursor(iconoBotonPulsado, new Point(0, 0), "cursorBoton");

    private int contadorBotones = 0;
    private Timer creadorBotones;

    private String ImagenesSeleccionadas = "";        //aqui guardamos la imagen seleccionada para mandarsela al constructor del juego
    private String nivelSeleccionado = "";            //aqui guardamos el nivel seleccionado
    
    public PantallaCargaJuego(Vista ventana) {

        this.setLayout(null);
        this.setBounds(0, 0, 1500, 1000);

        controladorConfiguracion = new ControladorConfiguracionJuego(this, ventana);

        imagenFondo = new ImageIcon(getClass().getResource("/img/fondoInfantil.png")).getImage();   //la imagenFondo sera la imagen correspondiente a la ruta recibida

        Image loadd = new ImageIcon(getClass().getResource("/img/loading.gif")).getImage();
        ImageIcon loading = new ImageIcon(loadd);
        JLabel load = new JLabel(loading);
        load.setHorizontalTextPosition(JLabel.CENTER);

        load.setFont(new Font("Autery", Font.BOLD, 40));
        load.setForeground(Color.black);
        load.setBounds(580, 400, 260, 260);

        creadorBotones = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {

                if (contadorBotones == 0) {
                    load.setText("Loading");
                    add(load);
                    contadorBotones++;
                    repaint();
                } else if (contadorBotones == 1) {

                    botonNewGame();
                    contadorBotones++;
                    repaint();
                } else if (contadorBotones == 2) {

                    botonPlayer();
                    contadorBotones++;
                    repaint();
                } else if (contadorBotones == 3) {
                    load.setText("Almost ready");
                    botonLevels();
                    contadorBotones++;
                    repaint();
                } else if (contadorBotones == 4) {
                    botonImages();
                    contadorBotones++;
                    repaint();

                } else if (contadorBotones == 5) {
                    remove(load);
                    repaint();
                    creadorBotones.stop();
                }

            }
        });

        creadorBotones.start();
        creadorBotones.setRepeats(true);

        cpJugador = new CustomPanelJugador(controladorConfiguracion);
        cpJugador.setVisible(false);
        this.add(cpJugador);

        cpGrupoImagenes = new CustomPanelGrupoImagenes(controladorConfiguracion);
        cpGrupoImagenes.setVisible(false);
        this.add(cpGrupoImagenes);

        cpLevel = new CustomPanelNiveles(controladorConfiguracion);
        cpLevel.setVisible(false);
        this.add(cpLevel);

    }

    /*
    Se utiliza el metodo paint para pintar nuestro fondo con la imagen que hemos recibido
     */
    @Override
    public void paint(Graphics g) {
        g.drawImage(imagenFondo, 0, 0, getWidth(), getHeight(), this);  //Con la imagen, la posicion respecto del panel (0,0 porque quiero que ocupe todo), y que coja el ancho y el alto

        setOpaque(false);       //le ponemos la opacidad a false para que se muestre

        super.paint(g); //pintamos
    }

    public void botonNewGame() {
        ImageIcon iconoNewGame = new ImageIcon(new ImageIcon(this.getClass().getResource("/img/botones/botonVerde.png")).getImage().getScaledInstance(220, 110, Image.SCALE_SMOOTH));
        ImageIcon iconoNewGameSeleccionado = new ImageIcon(new ImageIcon(this.getClass().getResource("/img/botones/botonVerdePulsado.png")).getImage().getScaledInstance(220, 110, Image.SCALE_SMOOTH));

        botonNewGame = new JButton("New Game", iconoNewGame);     //creamos nuestro boton y le mandamos directamente el icono como parametro
        botonNewGame.setFont(new Font("Autery", Font.BOLD, 40));
        botonNewGame.setForeground(Color.black);
        botonNewGame.setHorizontalTextPosition(JLabel.CENTER);

        botonNewGame.setPressedIcon(iconoNewGameSeleccionado);
        botonNewGame.setActionCommand("newGame");

        botonNewGame.setName("newGame");
        botonNewGame.setCursor(cursorBoton);

        botonNewGame.addActionListener(controladorConfiguracion);
        botonNewGame.addMouseListener(controladorConfiguracion);

        botonNewGame.setBorder(BorderFactory.createEmptyBorder());      //le borramos el borde al boton para que no se vea
        botonNewGame.setContentAreaFilled(false);   //le quitamos el "relleno" al boton

        botonNewGame.setBounds(100, 450, 220, 110);      //lo colocamos

        this.add(botonNewGame);
    }

    public void botonPlayer() {
        ImageIcon iconoPlayer = new ImageIcon(new ImageIcon(this.getClass().getResource("/img/botones/botonRojo.png")).getImage().getScaledInstance(220, 110, Image.SCALE_SMOOTH));
        ImageIcon iconoPlayerSeleccionado = new ImageIcon(new ImageIcon(this.getClass().getResource("/img/botones/botonRojoPulsado.png")).getImage().getScaledInstance(220, 110, Image.SCALE_SMOOTH));

        botonPlayer = new JButton("Player", iconoPlayer);     //creamos nuestro boton y le mandamos directamente el icono como parametro
        botonPlayer.setFont(new Font("Autery", Font.BOLD, 40));
        botonPlayer.setForeground(Color.black);
        botonPlayer.setHorizontalTextPosition(JLabel.CENTER);

        botonPlayer.setCursor(cursorBoton);

        botonPlayer.setPressedIcon(iconoPlayerSeleccionado);
        botonPlayer.setActionCommand("choosePlayer");
        botonPlayer.setName("choosePlayer");

        botonPlayer.setCursor(cursorBoton);

        botonPlayer.setBorder(BorderFactory.createEmptyBorder());      //le borramos el borde al boton para que no se vea
        botonPlayer.setContentAreaFilled(false);   //le quitamos el "relleno" al boton
        botonPlayer.addActionListener(controladorConfiguracion);
        botonPlayer.addMouseListener(controladorConfiguracion);

        botonPlayer.setBounds(350, 700, 220, 110);      //lo colocamos

        this.add(botonPlayer);
    }

    public void botonLevels() {
        ImageIcon iconoLevel = new ImageIcon(new ImageIcon(this.getClass().getResource("/img/botones/botonAzul.png")).getImage().getScaledInstance(220, 110, Image.SCALE_SMOOTH));
        ImageIcon iconoLevelSeleccionado = new ImageIcon(new ImageIcon(this.getClass().getResource("/img/botones/botonAzulPulsado.png")).getImage().getScaledInstance(220, 110, Image.SCALE_SMOOTH));

        botonLevel = new JButton("Level", iconoLevel);     //creamos nuestro boton y le mandamos directamente el icono como parametro
        botonLevel.setFont(new Font("Autery", Font.BOLD, 40));
        botonLevel.setForeground(Color.black);
        botonLevel.setHorizontalTextPosition(JLabel.CENTER);

        botonLevel.setPressedIcon(iconoLevelSeleccionado);
        botonLevel.setActionCommand("chooseLevel");

        botonLevel.setName("chooseLevel");

        botonLevel.setCursor(cursorBoton);

        botonLevel.setBorder(BorderFactory.createEmptyBorder());      //le borramos el borde al boton para que no se vea
        botonLevel.setContentAreaFilled(false);   //le quitamos el "relleno" al boton
        botonLevel.addActionListener(controladorConfiguracion);
        botonLevel.addMouseListener(controladorConfiguracion);
        botonLevel.setBounds(850, 700, 220, 110);      //lo colocamos

        this.add(botonLevel);
    }

    public void botonImages() {
        ImageIcon iconoImagenes = new ImageIcon(new ImageIcon(this.getClass().getResource("/img/botones/botonAmarillo.png")).getImage().getScaledInstance(220, 110, Image.SCALE_SMOOTH));
        ImageIcon iconoImagenesSeleccionado = new ImageIcon(new ImageIcon(this.getClass().getResource("/img/botones/botonAmarilloPulsado.png")).getImage().getScaledInstance(220, 110, Image.SCALE_SMOOTH));

        botonImages = new JButton("Images", iconoImagenes);     //creamos nuestro boton y le mandamos directamente el icono como parametro
        botonImages.setFont(new Font("Autery", Font.BOLD, 40));
        botonImages.setForeground(Color.black);
        botonImages.setHorizontalTextPosition(JLabel.CENTER);

        botonImages.setPressedIcon(iconoImagenesSeleccionado);
        botonImages.setActionCommand("chooseImages");

        botonImages.setName("chooseImages");

        botonImages.setCursor(cursorBoton);

        botonImages.setBorder(BorderFactory.createEmptyBorder());      //le borramos el borde al boton para que no se vea
        botonImages.setContentAreaFilled(false);   //le quitamos el "relleno" al boton
        botonImages.addActionListener(controladorConfiguracion);
        botonImages.addMouseListener(controladorConfiguracion);

        botonImages.setBounds(1150, 450, 220, 110);      //lo colocamos

        this.add(botonImages);
    }

    public void cambiarEstadoPaneles(String nombrePanel, boolean estado) {
        switch (nombrePanel) {
            case "cpJugador":
                cpJugador.setVisible(estado);
                break;
            case "cpImages":
                cpGrupoImagenes.setVisible(estado);
                break;
            case "cpLevel":
                cpLevel.setVisible(estado);
                break;
        }
    }

    public void cambiarEstadoBotones(boolean estado) {
        botonImages.setVisible(estado);
        botonLevel.setVisible(estado);
        botonNewGame.setVisible(estado);
        botonPlayer.setVisible(estado);

    }

    public void botonPulsado(String boton) {
        switch (boton) {
            case "newGame":
                botonNewGame.setCursor(cursorBotonPulsado);
                break;

            case "choosePlayer":
                botonPlayer.setCursor(cursorBotonPulsado);
                break;

            case "chooseLevel":
                botonLevel.setCursor(cursorBotonPulsado);
                break;

            case "chooseImages":
                botonImages.setCursor(cursorBotonPulsado);
                break;

            case "aceptarPlayer":
                cpJugador.botonPulsado();
                break;

            case "aceptarImagenes":
                cpGrupoImagenes.botonPulsado();
                break;

            case "aceptarNivel":
                cpLevel.botonPulsado();
                break;

            case "imgInfantil":
                cpGrupoImagenes.botonPulsado();
                break;

            case "imgSalamanca":
                cpGrupoImagenes.botonPulsado();
                break;
        }

    }

    public void botonLevantado(String boton) {
        switch (boton) {
            case "newGame":
                botonNewGame.setCursor(cursorBoton);
                break;

            case "choosePlayer":
                botonPlayer.setCursor(cursorBoton);
                break;

            case "chooseLevel":
                botonLevel.setCursor(cursorBoton);
                break;

            case "chooseImages":
                botonImages.setCursor(cursorBoton);
                break;

            case "aceptarPlayer":
                cpJugador.botonLevantado();
                break;

            case "aceptarImagenes":
                cpGrupoImagenes.botonLevantado();
                break;

            case "aceptarNivel":
                cpLevel.botonLevantado();
                break;

            case "imgInfantil":
                cpGrupoImagenes.botonLevantado();
                break;

            case "imgSalamanca":
                cpGrupoImagenes.botonLevantado();
                break;
        }
    }

    public void seleccinarImagen(int numeroImagen) {

        cpGrupoImagenes.seleccionarImagen(numeroImagen);

    }

    public void setImagenesSeleccionadas(String ImagenesSeleccionadas) {
        
        this.ImagenesSeleccionadas = ImagenesSeleccionadas;
        System.out.println("Set imgSelecc Pantalla  " + ImagenesSeleccionadas);

    }

    public String getImagenesSeleccionadas() {
        if (ImagenesSeleccionadas==""){     //el jugador no ha escogido imagenes, le ponemos por defecto infantil
            return ("Infantil");
        }
        return ImagenesSeleccionadas;
    }
    
    public void setNivelSeleccionado(String nivelSeleccionado){
        this.nivelSeleccionado=nivelSeleccionado;
        System.out.println("Set nivelSelecc Pantalla  " + nivelSeleccionado);
    }
    
    public String getNivelSeleccionado(){
        
        if (nivelSeleccionado == ""){       //el jugador no ha escogido nivel, por defecto sera Facil.
            System.out.println("no nivel elegido 1");
            return ("easy");
        }
        return nivelSeleccionado;
    }
    
    
}
