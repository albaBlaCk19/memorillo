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
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import memorillo.PantallaCarga.Controladores.ControladorConfiguracionJuego;

/**
 *
 * @author littl
 */
public class CustomPanelNiveles extends JPanel {

    private ControladorConfiguracionJuego controladorConfiguracion;

    private JPanel pCheckBoxes;
    private Image imagenFondo;
    private JLabel lTitulo;
    private JButton botonAceptar;

    private ImageIcon iconoCheckSel = new ImageIcon(new ImageIcon(this.getClass().getResource("/img/checkSeleccionado.png")).getImage().getScaledInstance(70, 55, Image.SCALE_SMOOTH));
    private ImageIcon iconoCheck = new ImageIcon(new ImageIcon(this.getClass().getResource("/img/checkInicial.png")).getImage().getScaledInstance(70, 55, Image.SCALE_SMOOTH));

    private ButtonGroup chNiveles = new ButtonGroup();
    private JCheckBox chEasy = new JCheckBox(" Easy", iconoCheck);
    private JCheckBox chMedium = new JCheckBox(" Medium", iconoCheck);
    private JCheckBox chHard = new JCheckBox(" Hard", iconoCheck);

    private Image iconoBoton = new ImageIcon(getClass().getResource("/img/cursorNormal.png")).getImage();
    private Cursor cursorBoton = Toolkit.getDefaultToolkit().createCustomCursor(iconoBoton, new Point(0, 0), "cursorBoton");
    private Image iconoBotonPulsado = new ImageIcon(getClass().getResource("/img/cursorPulsado.png")).getImage();
    private Cursor cursorBotonPulsado = Toolkit.getDefaultToolkit().createCustomCursor(iconoBotonPulsado, new Point(0, 0), "cursorBoton");

    private Font fTitulo = new Font("Autery", Font.BOLD, 65);

    public CustomPanelNiveles(ControladorConfiguracionJuego controladorConfig) {
        this.controladorConfiguracion = controladorConfig;
        this.setLayout(null);
        this.setBounds(370, 200, 700, 500);

        imagenFondo = new ImageIcon(getClass().getResource("/img/stickynote.png")).getImage();   //la imagenFondo sera la imagen correspondiente a la ruta recibida

        crearTitulo();
        crearCheckBoxes();
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
        lTitulo = new JLabel("Select Level");
        lTitulo.setBounds(190, 15, 300, 200);
        lTitulo.setFont(fTitulo);   //le cambiamos la fuente (declarada al principio)
        lTitulo.setForeground(new Color(9, 106, 151));   //le asignamos un color de letra

        this.add(lTitulo);  //añadimos el titulo a nuestro panel
    }

    public void crearCheckBoxes() {

        Font fuenteChecks = new Font("Autery", Font.BOLD, 35);
        pCheckBoxes = new JPanel();
        pCheckBoxes.setLayout(new GridLayout(3, 1));
        pCheckBoxes.setBounds(100, 150, 400, 200);

        chEasy.setFont(fuenteChecks);
        chMedium.setFont(fuenteChecks);
        chHard.setFont(fuenteChecks);

        chEasy.setBackground(new Color(242, 242, 242));
        chEasy.setName("easy");
        chMedium.setBackground(new Color(242, 242, 242));
        chMedium.setName("medium");
        chHard.setBackground(new Color(242, 242, 242));
        chHard.setName("hard");

        chEasy.setSelectedIcon(iconoCheckSel);
        chMedium.setSelectedIcon(iconoCheckSel);
        chHard.setSelectedIcon(iconoCheckSel);
        
        chEasy.addMouseListener(controladorConfiguracion);
        chMedium.addMouseListener(controladorConfiguracion);
        chHard.addMouseListener(controladorConfiguracion);

        chNiveles.add(chEasy);
        chNiveles.add(chMedium);
        chNiveles.add(chHard);

        pCheckBoxes.add(chEasy);
        pCheckBoxes.add(chMedium);
        pCheckBoxes.add(chHard);

        this.add(pCheckBoxes);
    }

    public void crearBoton() {
        /*
        Creamos la imagen que queremos mostrar como boton
         */

        ImageIcon iconoBoton = new ImageIcon(new ImageIcon(this.getClass().getResource("/img/botones/botonAzul.png")).getImage().getScaledInstance(175, 65, Image.SCALE_SMOOTH));
        ImageIcon iconoBotonSeleccionado = new ImageIcon(new ImageIcon(this.getClass().getResource("/img/botones/botonAzulPulsado.png")).getImage().getScaledInstance(175, 65, Image.SCALE_SMOOTH));

        botonAceptar = new JButton("Done", iconoBoton);     //creamos nuestro boton y le mandamos directamente el icono como parametro
        botonAceptar.setPressedIcon(iconoBotonSeleccionado);

        botonAceptar.setFont(new Font("Autery", Font.BOLD, 40));
        botonAceptar.setForeground(Color.white);
        botonAceptar.setHorizontalTextPosition(JLabel.CENTER);

        botonAceptar.setActionCommand("aceptarNivel");
        botonAceptar.setName("aceptarNivel");

        botonAceptar.setCursor(cursorBoton);

        botonAceptar.setBorder(BorderFactory.createEmptyBorder());      //le borramos el borde al boton para que no se vea
        botonAceptar.setContentAreaFilled(false);   //le quitamos el "relleno" al boton

        botonAceptar.setBounds(250, 370, 175, 65);      //lo colocamos
        botonAceptar.addActionListener(controladorConfiguracion);
        botonAceptar.addMouseListener(controladorConfiguracion);

        this.add(botonAceptar);
    }

    public void botonPulsado() {
        botonAceptar.setCursor(cursorBotonPulsado);
    }

    public void botonLevantado() {
        botonAceptar.setCursor(cursorBoton);
    }
}
