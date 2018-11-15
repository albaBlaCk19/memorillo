/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package memorillo.HuevoDePascua;

import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author littl
 */
public class TicTacToe extends JPanel {

    private final int FILAS = 3;
    private final int COLUMNAS = 3;

    private int pos_x, pos_y;

    private Image imagenFondo;
    private JButton[][] casillas = new JButton[3][3];

    private ImageIcon jugador1 = new ImageIcon(new ImageIcon(this.getClass().getResource("/img/huevoPascua/jugadorDefecto.png")).getImage().getScaledInstance(180, 150, Image.SCALE_SMOOTH));
    private ImageIcon jugador2 = new ImageIcon(new ImageIcon(this.getClass().getResource("/img/huevoPascua/jugador2.png")).getImage().getScaledInstance(180, 150, Image.SCALE_SMOOTH));

    private Image iconoBoton = new ImageIcon(getClass().getResource("/img/cursorNormal.png")).getImage();
    private Cursor cursorBoton = Toolkit.getDefaultToolkit().createCustomCursor(iconoBoton, new Point(0, 0), "cursorBoton");
    private Image iconoBotonPulsado = new ImageIcon(getClass().getResource("/img/cursorPulsado.png")).getImage();
    private Cursor cursorBotonPulsado = Toolkit.getDefaultToolkit().createCustomCursor(iconoBotonPulsado, new Point(0, 0), "cursorBoton");

    private ControladorTTT controladorTTT;

    public TicTacToe() {
        this.setLayout(null);
        this.setBounds(0, 0, 1000, 950);

        this.controladorTTT = new ControladorTTT(this);
        crearCssillas();
        imagenFondo = (new ImageIcon(this.getClass().getResource("/img/huevoPascua/ttt.png")).getImage().getScaledInstance(1000, 950, Image.SCALE_SMOOTH));
        this.setVisible(true);
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(imagenFondo, 0, 0, getWidth(), getHeight(), this);  //Con la imagen, la posicion respecto del panel (0,0 porque quiero que ocupe todo), y que coja el ancho y el alto

        setOpaque(false);       //le ponemos la opacidad a false para que se muestre

        super.paint(g); //pintamos
    }

    public void crearCssillas() {

        JPanel pCasillas = new JPanel();
        pCasillas.setLayout(new GridLayout(3, 3));
        pCasillas.setBorder(null);
        pCasillas.setOpaque(false);
        pCasillas.setBounds(200, 230, 600, 550);

        int idBoton=0;
        for (int i = 0; i < FILAS; i++) {
            for (int j = 0; j < COLUMNAS; j++) {
                casillas[i][j] = new JButton(""+idBoton);
                casillas[i][j].setContentAreaFilled(false);
                casillas[i][j].setBorder(null);

                casillas[i][j].setCursor(cursorBoton);
                casillas[i][j].addMouseListener(controladorTTT);
                casillas[i][j].addActionListener(controladorTTT);

                pCasillas.add(casillas[i][j]);
                idBoton++;
            }
            
        }

        this.add(pCasillas);

    }

    public void conocerPulsado(String valorBoton) {

        switch (valorBoton) {
            case "0":
                pos_x = 0;
                pos_y = 0;

                break;

            case "1":
                pos_x = 0;
                pos_y = 1;
                break;

            case "2":
                pos_x = 0;
                pos_y = 2;
                break;

            case "3":
                pos_x = 1;
                pos_y = 0;
                break;

            case "4":
                pos_x = 1;
                pos_y = 1;
                break;

            case "5":
                pos_x = 1;
                pos_y = 2;
                break;

            case "6":
                pos_x = 2;
                pos_y = 0;
                break;

            case "7":
                pos_x = 2;
                pos_y = 1;
                break;

            case "8":
                pos_x = 1;
                pos_y = 2;
                break;
        }

    }

    public boolean comprobarEspacioDisponible(String boton) {

        conocerPulsado(boton);
        boolean disponible = true;

        if (casillas[pos_x][pos_y].getIcon() != null || casillas[pos_x][pos_y] != null) {
            return false;       //ya esta ocupada
        } else {
            return true;    //si no, puede pulsar
        }

    }

    public void colocarFicha(String icono, String valor) {

        conocerPulsado(valor);
        switch (icono) {
            case "jugador1":
                casillas[pos_x][pos_y].setIcon(jugador1);

                break;

            case "jugador2":
                casillas[pos_x][pos_y].setIcon(jugador2);

                break;
        }
    }

    public boolean gana(ImageIcon icono) {
        for (int i = 0; i < casillas.length; i++)//horizontales
        {
            int cont = 0; //contador para saber cuantas fichas iguales hay
            for (int j = 0; j < casillas.length; j++) {
                if (casillas[i][j].getIcon().equals(icono)) {
                    cont++;
                }
            }
            if (cont == 3) {
                return true;
            }
        }

        for (int j = 0; j < casillas.length; j++)//verticales
        {
            int cont = 0; //contador para saber cuantas fichas iguales hay
            for (int i = 0; i < casillas.length; i++) {
                if (casillas[i][j].getIcon().equals(icono)) {
                    cont++;
                }
            }
            if (cont == 3) {
                return true;
            }
        }

        //diagonal
        if ((casillas[0][0].getIcon().equals(icono)) && (casillas[1][1].getIcon().equals(icono)) && (casillas[2][2].getIcon().equals(icono))) {
            return true;
        }
        if ((casillas[0][2].getIcon().equals(icono)) && (casillas[1][1].getIcon().equals(icono)) && (casillas[2][0].getIcon().equals(icono))) {
            return true;
        }

        return false;

    }

    public void botonPulsado() {
        for (int i = 0; i < casillas.length; i++) {
            for (int j = 0; j < casillas.length; j++) {
                casillas[i][j].setCursor(cursorBotonPulsado);

            }

        }
    }

}
