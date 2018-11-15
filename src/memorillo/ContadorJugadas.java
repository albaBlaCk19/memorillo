/*
 * Clase ContadorJugadas
 * Esta clase la utilizaremos a la hora de querer saber los movimientos que ha hecho el jugador 
 * durante la partida hasta que haya finalizado esta.
 */
package memorillo;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Agus-PC
 */
public class ContadorJugadas extends JPanel {

    private Image imagenContador;       // falta meter la imagen de este contador
    private JLabel jLabelContador;

    private int contadorJugadas;

    public ContadorJugadas() {

        this.contadorJugadas = 0;
        this.setLayout(null);
        imagenContador = new ImageIcon(getClass().getResource("/img/contadorJugadas.png")).getImage();
        this.setBackground(Color.PINK);
        this.setBounds(1000, 390, 400, 350);

        crearLabelContador();
    }

    /**
     * Metodo crearLabelContador()
     *
     *
     */
    public void crearLabelContador() {
        jLabelContador = new JLabel("100");
        jLabelContador.setFont(new Font("Consolas", Font.BOLD, 40));
        jLabelContador.setForeground(Color.BLACK);
        jLabelContador.setBounds(215, 60, 150, 100);

        this.add(jLabelContador);
    }

    @Override
    public void paint(Graphics g) {

        if (imagenContador != null) {
            g.drawImage(imagenContador, 0, 0, getWidth(), getHeight(), this);
            setOpaque(false);
        }
        super.paint(g);
    }

    /**
     * Metodo sumarJugada() Este sera el metodo que llamaremos en la logica
     * cuando queramos sumar una jugada (movimiento)
     *
     * @return contadorJugadas++;
     */
    public int sumarJugada() {
        return contadorJugadas++;
    }

    /**
     * Metodo resetearContadorJugadas() Este metodo lo utilizaremos para cuando
     * nos haga falta volvera poner el contadorJugadas a 0
     *
     * @return contadorJugadas=0;
     */
    public int resetearContadorJugadas() {

        return contadorJugadas = 0;
    }

    public int getContadorJugadas() {
        return contadorJugadas;
    }

    /**
     * Metodo setText() lo utilizaremos para cambiar el texto del contador de
     * las jugadas, es decir, cada vez que haga un movimiento
     *
     * @param contadorJugadas
     */
    public void setText(int contadorJugadas) {

        jLabelContador.setText("" + contadorJugadas);
    }

}
