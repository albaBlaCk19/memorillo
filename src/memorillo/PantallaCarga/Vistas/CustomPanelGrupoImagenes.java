/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package memorillo.PantallaCarga.Vistas;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import memorillo.PantallaCarga.Controladores.ControladorConfiguracionJuego;

/**
 *
 * @author littl
 */
public class CustomPanelGrupoImagenes extends JPanel {

    private ControladorConfiguracionJuego controladorConfiguracion;
    private Image imagenFondo;
    private JLabel lTitulo;
    private JButton botonAceptar;

    private JLabel lInfantiles, lSalamanca;
    private ImageIcon iconoInfantil, iconoSalamanca;

    private Image iconoBoton = new ImageIcon(getClass().getResource("/img/cursorNormal.png")).getImage();
    private Cursor cursorBoton = Toolkit.getDefaultToolkit().createCustomCursor(iconoBoton, new Point(0, 0), "cursorBoton");
    private Image iconoBotonPulsado = new ImageIcon(getClass().getResource("/img/cursorPulsado.png")).getImage();
    private Cursor cursorBotonPulsado = Toolkit.getDefaultToolkit().createCustomCursor(iconoBotonPulsado, new Point(0, 0), "cursorBoton");

    private Font fTitulo = new Font("Autery", Font.BOLD, 65);
    private Border borde = BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLACK, 3), BorderFactory.createLoweredSoftBevelBorder());

    private boolean seleccionada;

    public CustomPanelGrupoImagenes(ControladorConfiguracionJuego controladorConfig) {
        this.controladorConfiguracion = controladorConfig;
        this.setLayout(null);
        this.setBounds(370, 200, 700, 500);

        this.seleccionada = false;

        imagenFondo = new ImageIcon(getClass().getResource("/img/stickynote.png")).getImage();   //la imagenFondo sera la imagen correspondiente a la ruta recibida

        crearTitulo();
        crearEleccionesImagen();
        crearBoton();

        this.setVisible(true);
    }

    /*
    Se utiliza el metodo paint para pintar nuestro fondo con la imagen que hemos recibido
     */
    @Override
    public void paint(Graphics g) {
        if (imagenFondo != null) {      //si la imagen existe, la pintara
            g.drawImage(imagenFondo, 0, 0, getWidth(), getHeight(), this);  //Con la imagen, la posicion respecto del panel (0,0 porque quiero que ocupe todo), y que coja el ancho y el alto

            setOpaque(false);       //le ponemos la opacidad a false para que se muestre
        }

        super.paint(g); //pintamos
    }

    public void crearTitulo() {
        lTitulo = new JLabel("Select Game Style");
        lTitulo.setBounds(105, 15, 600, 200);
        lTitulo.setFont(fTitulo);   //le cambiamos la fuente (declarada al principio)
        lTitulo.setForeground(new Color(225, 174, 33));   //le asignamos un color de letra

        this.add(lTitulo);  //añadimos el titulo a nuestro panel
    }

    public void crearBoton() {
        /*
        Creamos la imagen que queremos mostrar como boton
         */

        ImageIcon iconoBoton = new ImageIcon(new ImageIcon(this.getClass().getResource("/img/botones/botonAmarillo.png")).getImage().getScaledInstance(175, 65, Image.SCALE_SMOOTH));
        ImageIcon iconoBotonSeleccionado = new ImageIcon(new ImageIcon(this.getClass().getResource("/img/botones/botonAmarilloPulsado.png")).getImage().getScaledInstance(175, 65, Image.SCALE_SMOOTH));

        botonAceptar = new JButton("Images",iconoBoton);     //creamos nuestro boton y le mandamos directamente el icono como parametro
        botonAceptar.setPressedIcon(iconoBotonSeleccionado);

        botonAceptar.setFont(new Font("Autery", Font.BOLD, 40));
        botonAceptar.setForeground(Color.black);
        botonAceptar.setHorizontalTextPosition(JLabel.CENTER);

        botonAceptar.setActionCommand("aceptarImagenes");
        botonAceptar.setName("aceptarImagenes");

        botonAceptar.setBorder(BorderFactory.createEmptyBorder());      //le borramos el borde al boton para que no se vea
        botonAceptar.setContentAreaFilled(false);   //le quitamos el "relleno" al boton

        botonAceptar.setCursor(cursorBoton);

        botonAceptar.setBounds(250, 370, 175, 65);      //lo colocamos
        botonAceptar.addActionListener(controladorConfiguracion);
        botonAceptar.addMouseListener(controladorConfiguracion);
        this.add(botonAceptar);
    }

    public void crearEleccionesImagen() {
        /*
        creo los label que contendran las imagenes
         */
        lInfantiles = new JLabel();
        lInfantiles.setBounds(100, 180, 200, 150);
        lInfantiles.addMouseListener(controladorConfiguracion);

        lSalamanca = new JLabel();
        lSalamanca.setBounds(380, 180, 200, 150);
        lSalamanca.addMouseListener(controladorConfiguracion);

        Image img;

        /*
        Creo las imagenes y las añado a los JLabel
         */
        img = new ImageIcon(this.getClass().getResource("/img/traseraInfantil.png")).getImage();
        iconoInfantil = new ImageIcon(img.getScaledInstance(200, 150, Image.SCALE_SMOOTH));
        lInfantiles.setIcon(iconoInfantil);
        lInfantiles.setCursor(cursorBoton);

        lInfantiles.setName("imgInfantil");
        this.add(lInfantiles);

        img = new ImageIcon(this.getClass().getResource("/img/traseraSalamanca.png")).getImage();
        iconoSalamanca = new ImageIcon(img.getScaledInstance(200, 150, Image.SCALE_SMOOTH));
        lSalamanca.setIcon(iconoSalamanca);
        lSalamanca.setCursor(cursorBoton);

        lSalamanca.setName("imgSalamanca");
        this.add(lSalamanca);

    }

    public void seleccionarImagen(int numeroFoto) {

        switch (numeroFoto) {
            case 1:
                if (!isSeleccionada(lInfantiles) && !isSeleccionada(lSalamanca)) {
                    lInfantiles.setBorder(borde);
                } else if (!isSeleccionada(lInfantiles) && isSeleccionada(lSalamanca)) {
                    lInfantiles.setBorder(borde);
                    lSalamanca.setBorder(null);
                } else {
                    lInfantiles.setBorder(null);
                }

                break;

            case 2:
                if (!isSeleccionada(lSalamanca) && !isSeleccionada(lInfantiles)) {
                    lSalamanca.setBorder(borde);
                } else if (!isSeleccionada(lSalamanca) && isSeleccionada(lInfantiles)) {
                    lSalamanca.setBorder(borde);
                    lInfantiles.setBorder(null);
                } else {
                    lSalamanca.setBorder(null);
                }
                break;
        }
    }

    public boolean isSeleccionada(JLabel label) {
        if (label.getBorder() != null) {
            return true;
        } else {
            return false;
        }

    }
    
    public String recogerSeleccionada(){
        if (isSeleccionada(lSalamanca)){
            return "Salamanca";
        } else {
            return "Infantil";
        }
    }

    public void botonPulsado() {
        botonAceptar.setCursor(cursorBotonPulsado);
        lInfantiles.setCursor(cursorBotonPulsado);
        lSalamanca.setCursor(cursorBotonPulsado);
    }

    public void botonLevantado() {
        botonAceptar.setCursor(cursorBoton);
        lInfantiles.setCursor(cursorBoton);
        lSalamanca.setCursor(cursorBoton);
    }
}
