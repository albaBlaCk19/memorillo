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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;
import memorillo.PantallaCarga.Controladores.ControladorConfiguracionJuego;

/**
 *
 * @author littl
 */
public class CustomPanelJugador extends JPanel {

    /*
    Se crean los elementos principales de este panel : JTextField, JButtons y el JFileChooser
     */
    private Vista vista;
    private JTextField tfNombre;
    private JButton botonFoto, botonAceptar;
    private JFileChooser chooserImagen;

    /*
    Se crea el label del titulo y el label que contendra la foto del jugador
     */
    private JLabel lTitulo;
    private JLabel lJugador;

    /*
    Se crean las variables necesarias para colocar las imagenes a nuestros elementos
     */
    private Image imagenFondo;
    private ImageIcon fotoJugador;
    private final ImageIcon JUGADOR_DEFECTO = new ImageIcon(this.getClass().getResource("/img/jugadorDefecto.png"));    //si no se selecciona una foto, la foto por defecto sera esta

    private String nombreJugador = "";
    private String NOMBRE_DEFECTO = "Player";
    
    
    private Image iconoBoton = new ImageIcon(getClass().getResource("/img/cursorNormal.png")).getImage();
    private Cursor cursorBoton = Toolkit.getDefaultToolkit().createCustomCursor(iconoBoton, new Point(0, 0), "cursorBoton");
    private Image iconoBotonPulsado = new ImageIcon(getClass().getResource("/img/cursorPulsado.png")).getImage();
    private Cursor cursorBotonPulsado = Toolkit.getDefaultToolkit().createCustomCursor(iconoBotonPulsado, new Point(0, 0), "cursorBoton");
    
    private ControladorConfiguracionJuego controladorConfiguracion;

    private Font fTitulo = new Font("Autery", Font.BOLD, 65);

    /**
     * Constructor parametrizado de CustomPanelJugador
     *
     * @param nombreImagen
     */
    public CustomPanelJugador(ControladorConfiguracionJuego controladorConfig) {
        this.controladorConfiguracion=controladorConfig;
        this.setLayout(null);
        this.setBounds(370, 200, 700, 500);

        lJugador = new JLabel();    //creamos el label que tendra la foto del jugador

        imagenFondo = new ImageIcon(getClass().getResource("/img/stickynote.png")).getImage();   //la imagenFondo sera la imagen correspondiente a la ruta recibida

        crearTitulo();  //creamos el titulo
        crearChooser(); //creamos el FileChooser para seleccionar la foto
        crearTextField();   //creamos el TextField para el nombre de jugador
        
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

        super.paint(g); 
    }

    /*
    Metodo para crear el JLabel del titulo del panel
     */
    public void crearTitulo() {
        lTitulo = new JLabel("Player");
        lTitulo.setBounds(250, 15, 300, 200);
        lTitulo.setFont(fTitulo);   //le cambiamos la fuente (declarada al principio)
        lTitulo.setForeground(new Color(199, 0, 57));   //le asignamos un color de letra

        this.add(lTitulo);  //añadimos el titulo a nuestro panel
    }

    /*
    Metodo para crear el JFileChooser
     */
    public void crearChooser() {

        botonFoto = new JButton("Select photo");    //Creamos un boton "seleccionar foto" que abrira el chooser
        botonFoto.setBounds(70, 262, 200, 30);  //lo colocamos
        
        this.add(botonFoto);    //lo añadimos

        /*
        Le añadimos un ActionListener a nuestro boton para que al pulsarlo, lance nuestro chooser
         */
        botonFoto.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {    //cuando pulsemos el boton

                chooserImagen = new JFileChooser();   //se crea el JFileChooser
                chooserImagen.setCurrentDirectory(new File(System.getProperty("user.home"))); //le indicamos en que directorio se abirira el chooser

                FileNameExtensionFilter filter = new FileNameExtensionFilter("*.Images", "jpg", "gif", "png");  //creamos un filtro de archivos (Aceptara los indicados en los parametros
                chooserImagen.addChoosableFileFilter(filter); //le asignamos al chooser el filtro

                int result = chooserImagen.showSaveDialog(null);      //con este metodo sabemos si el usuario ha pulsado en Guardar o en Cancelar 
                                                                      //--> Devuelve: JFileChooser.APPROVE_OPTION --> Guardar
                                                                      //-->JFileChooser.CANCEL_OPTION -->cancelar
                                                                      //-->JFileChooser.ERROR --> error  al elegir el archivo

                if (result == JFileChooser.APPROVE_OPTION) {    //si le dio a guardar
                    File fotoElegida = chooserImagen.getSelectedFile();  //guardamos en un objeto de tipo File, el archivo seleccionado
                    String ruta = fotoElegida.getAbsolutePath(); //obtenemos la ruta absoluta de dicho archivo

                    lJugador.setIcon(cogerFoto(ruta));   //añadimos la foto a nuestro label jugador
                    lJugador.setBounds(100, 120, 300, 300);   //damos un tamaño al label
                    botonFoto.setVisible(false);  //ocultaremos el boton para que solo se vea la imagen seleccionada

                } else if (result == JFileChooser.CANCEL_OPTION) {  //si ha pulsado en cancelar (opcion añadida por si decidimos mostrar un dialogo modal de ¿Esta seguro?
                    System.out.println("No photo selected");
                }
            }
        });
        this.add(lJugador);     //añadimos el label del jugador al panel
    }

    /**
     * Metodo que permite obtener un icono valido a partir de la ruta obtenida
     * con el chooser
     *
     * @param ruta
     * @return
     */
    public ImageIcon cogerFoto(String ruta) {  //recibe la ruta extraida con el getPath

        ImageIcon jugador = new ImageIcon(ruta);     //creamos el icono con la imagen de la ruta   
        Image imagenJugador = jugador.getImage().getScaledInstance(175, 175, Image.SCALE_SMOOTH);   //CREAMOS ESTE IAMGE SOLO PARA REDIMENSIONAR LA FOTO
        fotoJugador = new ImageIcon(imagenJugador); //volvemos a crear el icono con la foto redimensionada

        return fotoJugador;
    }

    /**
     * Metodo para crear el campo de texto donde el usuario introducira su nombre.
     * Se crea tambien el boton "Done" para validar la foto y el nombre.
     */
    public void crearTextField() {

        /*
        Creamos la imagen que queremos mostrar como boton
         */
        ImageIcon iconoBoton = new ImageIcon(new ImageIcon(this.getClass().getResource("/img/botones/botonRojo.png")).getImage().getScaledInstance(175, 65, Image.SCALE_SMOOTH));
        ImageIcon iconoBotonSeleccionado = new ImageIcon(new ImageIcon(this.getClass().getResource("/img/botones/botonRojoPulsado.png")).getImage().getScaledInstance(175, 65, Image.SCALE_SMOOTH));

        botonAceptar = new JButton("Done", iconoBoton);     //creamos nuestro boton y le mandamos directamente el icono como parametro
        botonAceptar.setPressedIcon(iconoBotonSeleccionado);
        
        botonAceptar.setFont(new Font ("Autery", Font.BOLD, 40));
        botonAceptar.setForeground(Color.white);
        botonAceptar.setHorizontalTextPosition(JLabel.CENTER);
        
        botonAceptar.setActionCommand("aceptarPlayer");
        botonAceptar.setName("aceptarPlayer");
        
        botonAceptar.setBorder(BorderFactory.createEmptyBorder());      //le borramos el borde al boton para que no se vea
        botonAceptar.setContentAreaFilled(false);   //le quitamos el "relleno" al boton
        
        botonAceptar.setCursor(cursorBoton);

        botonAceptar.setBounds(250, 370, 175, 65);      //lo colocamos
        
        botonAceptar.addActionListener(controladorConfiguracion);
        botonAceptar.addMouseListener(controladorConfiguracion);

        /*
        Creamos el textField para introducir el nombre
         */
        tfNombre = new JTextField("Player name");   //lo creamos ¡borrar player name?!
        tfNombre.setFont(new Font("Autery", Font.BOLD, 30));    //asignamos fuente que utilizara nuestro textfield
        tfNombre.setBounds(350, 250, 250, 50);  //lo colocamos

        this.add(botonAceptar); //añadimos boton
        this.add(tfNombre); //añadimos el textfield
    }

    /**
     * Metodo getter de la foto del jugador. Si no ha seleccionado ninguna,
     * devolvera la de Jugador por defecto.
     *
     * @return
     */
    public ImageIcon getPlayerPhoto() {
        if (fotoJugador != null) {
            return fotoJugador;
        } else {
            return JUGADOR_DEFECTO;
        }
    }

    /**
     * Metodo getter del nombre del jugador. Si no ha introducido nombre,
     * devolvera un nombre por defecto.
     * @return 
     */
    public String getPlayerName() {
        
        nombreJugador = tfNombre.getText();
        
        if (nombreJugador != null) {
            return nombreJugador;
        } else {
            return NOMBRE_DEFECTO;
        }
    }
    
    public void botonPulsado(){
        botonAceptar.setCursor(cursorBotonPulsado);
    }
    
    public void botonLevantado(){
        botonAceptar.setCursor(cursorBoton);
    }
}
