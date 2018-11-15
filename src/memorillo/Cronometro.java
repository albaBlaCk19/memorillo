/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package memorillo;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author Agus-PC
 */
public class Cronometro extends JPanel {

    private Image imagenCronometro;     //sera la foto del reloj
    private JLabel jLabelCronometro;     //sera el label que muestre el tiempo

    private boolean cronometroActivo;

    private static int minutos = 0;
    private static int segundos = 0;

    private String min = "";
    private String seg = "";

    private Timer timerCronometro;

    public Cronometro() {
        this.setLayout(null);
     this.setBounds(1230, 745, 220, 180);


        imagenCronometro = new ImageIcon(getClass().getResource("/img/cronometro.png")).getImage();   //la imagenFondo sera la imagen correspondiente a la ruta recibida
        this.setBackground(Color.cyan);

        this.cronometroActivo = true;

        crearLabelCronometro();
        iniciarCronometro();

    }

    public void crearLabelCronometro() {
        jLabelCronometro = new JLabel("00:00");
        jLabelCronometro.setFont(new Font("Consolas", Font.BOLD, 40));
        jLabelCronometro.setForeground(Color.white);
        jLabelCronometro.setBounds(57, 60, 150, 100);

        this.add(jLabelCronometro);
    }

    /*
    Se utiliza el metodo paint para pintar nuestro fondo con la imagen que hemos recibido
     */
    @Override
    public void paint(Graphics g) {
        if (imagenCronometro != null) {      //si la imagen existe, la pintara
            g.drawImage(imagenCronometro, 0, 0, getWidth(), getHeight(), this);  //Con la imagen, la posicion respecto del panel (0,0 porque quiero que ocupe todo), y que coja el ancho y el alto

            setOpaque(false);       //le ponemos la opacidad a false para que se muestre
        }

        super.paint(g); //pintamos
    }

    /**
     * Metodo iniciarCronometro()
     *
     * En este metodo lo que hacemos es iniciar el cronometro, cambiamos el
     * estado de cronometroActivo a true (por si estaba pausado) creamos un
     * Timer que cada segundo nos sume 1 a la variable segundos si segundos
     * alcanza el valor 59 ponemos la variable segundos a 0 y sumamos 1 a la
     * variable minutos
     *
     *
     */
    public void iniciarCronometro() {
        cronometroActivo = true;
        timerCronometro = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {

                if (cronometroActivo == true) {
                    segundos += 1;

                    if (segundos == 59) {
                        segundos = 0;
                        minutos++;
                    }
                    cambiarCronometro(minutos, segundos);

                }

            }
        });
        timerCronometro.start();
        timerCronometro.setRepeats(true);

    }

    /**
     * Metodo pararCronometro()
     *
     * Este metodo lo hacemos para parar el contador, cambiamos el estado de
     * cronometroActivo a false y paramos el Timer
     */
    public void pararCronometro() {
        cronometroActivo = false;
        timerCronometro.stop();

    }

    /**
     * Metodo cambiarCronometro()
     *
     * @param ae
     */
    public void cambiarCronometro(int minutos, int segundos) {

        if (minutos < 10) {
            min = "0" + minutos;
        } else {
            min = minutos + "";
        }
        if (segundos < 10) {
            seg = "0" + segundos;
        } else {
            seg = segundos + "";
        }

        jLabelCronometro.setText(min + ":" + seg);
    }

}
