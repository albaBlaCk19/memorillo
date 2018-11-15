/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package memorillo;

/**
 *
 * @author littl
 */
import java.awt.BasicStroke;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.GeneralPath;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.Timer;

public class JFlip extends JComponent implements MouseListener {

    private Timer timer = null;
    private boolean moviendose = false;
    
    private boolean seleccionada=false;
    
    private int velocidad = 4;//velocidad de flip en milisegundos
    private int desplazamiento = 2;//desplazamiento en pixeles
    private boolean isFront = true;
    private boolean movimiento = true;      //sentido del desplazamiento (derecha o izquierda)

    //Iconos de nuestro componente, el trasero (iconBack), y el delantero (iconFront)
    private Icon iconBack;

    private Icon iconFront;
    private Image imagen;//= ((ImageIcon) iconFront).getImage();  //para poder pintarla, necesitamos una imagen, un icono no nos sirve

    //Variables de desplazamiento de nuestra imagen
    private int desplazamientoHorizontal = 0;
    private float desplazamientoIzquierda = 0;
    private float desplazamientoDerecha = 0;

    //tamaño por defecto de la imagen a mostrar 
    private Dimension dimension = new Dimension(160, 160);

    /**
     * Constructor por defecto de JFlip
     *
     * @param imagenes
     */
    public JFlip(String imagenes, ImageIcon iconBack) {

        super();        //llamamos al constructor del padre para obtener todas las caracteristicas de un JComponent
        setSize(dimension); //le asignamos un tamaño, en este caso, una dimension predefinida anteriormente
        setPreferredSize(dimension);    //le asignamos un tamaño, en este caso, una dimension predefinida anteriormente    
        setVisible(true);   //lo mostramos
        addMouseListener(JFlip.this);       //le añadimos el escuchador del raton
        this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));     //le cambiamos el cursor para que cuando entremos en el elemento aparezca una mano

        this.iconBack = iconBack;

        switch (imagenes) {
            case "Infantil":
                iconFront = new ImageIcon(getClass().getResource("/img/traseraInfantil.png"));
                imagen = ((ImageIcon) iconFront).getImage();
                break;

            case "Salamanca":
                iconFront = new ImageIcon(getClass().getResource("/img/traseraSalamanca.png"));
                imagen = ((ImageIcon) iconFront).getImage();
                break;
        }
    }

    /**
     * Metodo que pintara nuestro componente
     *
     * @param g
     */
    @Override
    public void paintComponent(Graphics g) {

        Graphics2D g2 = (Graphics2D) g;     //creamos un elemento del tipo Graphics2D, que necesitaremos para pintar nuestra imagen
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);        //el metodo RenderinHint se utiliza cuando queremos manipular una imagen, como
        //parametros le indicamos, primero que ajuste los bordes de la imagen (combina los colores de los pixeles existentes
        //con el color de relleno de la figura solicitada. El segundo parametro sera uno de los valores que acepta el KEY_ANTIALIASING, en este caso
        //VALUE_ANTIALIAS_ON.
        g2.setStroke(new BasicStroke(0f));

        //coordenas de imagen
        GeneralPath p = new GeneralPath();      //GeneralPath es un objeto que representa la ruta geometrica de una forma

        p.moveTo(desplazamientoHorizontal, desplazamientoIzquierda);     //esquina A  -- movemos el objeto, su coordinadas x e y
        p.lineTo(getWidth() - desplazamientoHorizontal - 1, desplazamientoDerecha);       //esquina B  -- dibuja una linea recta en la ruta de nuestra figura, desde las coordinadas actuales a las nuevas coordenadas, indicadas por parametro, x e y
        p.lineTo(getWidth() - desplazamientoHorizontal - 1, getHeight() - desplazamientoDerecha - 1);     //esquina D   -- dibuja una linea recta en la ruta de nuestra figura, desde las coordinadas actuales a las nuevas coordenadas, indicadas por parametro, x e y
        p.lineTo(desplazamientoHorizontal, getHeight() - desplazamientoIzquierda - 1);       //esquina C   -- movemos el objeto, su coordinadas x e y
        p.closePath();      // "cierra la ruta" pintando una linea de vuelta a las coordenadas del ultimo moveTo

        Shape shp = p;      //creamos una variable de tipo Shape que contendra el GeneralPath creado anteriormente
        g2.setClip(shp);    //
        g2.drawImage(imagen, 0, 0, getWidth() - desplazamientoHorizontal, getHeight(), null);
        g2.setClip(null);
        g2.draw(shp);
        g.dispose();
    }

    /**
     * Metodo que realiza los cambios en las variables de desplazamiento
     *
     * @return Void
     */
    private void flipAnimate() {
        moviendose = !moviendose;
        //declaramos un evento para modificar el desplazamiento vertical y horizontal
        ActionListener animation = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {

                if (movimiento) {
                    
                    desplazamientoHorizontal += desplazamiento;
                    if (desplazamientoDerecha >= getHeight() / 2) {
                        desplazamientoDerecha += 0;
                    } else {
                        desplazamientoDerecha += desplazamiento / (getSize().getWidth() / getSize().getHeight() + 1f);
                    }
                    //displacementRight += (displacementRight >= getHeight() / 2) ? 0 : desplazamiento / (getSize().getWidth() / getSize().getHeight() + 1f);
                } else {
                    desplazamientoHorizontal -= desplazamiento;
                    desplazamientoIzquierda -= desplazamiento / (getSize().getWidth() / getSize().getHeight() + 1f);
                }
                seleccionada=true;
                //repinta graficos
                repaint();

                //si se llego al medio del componente -> cambia de sentido
                if (desplazamientoHorizontal >= getWidth() / 2 ) {
                    movimiento = false;
                    desplazamientoIzquierda = desplazamientoDerecha;
                    desplazamientoDerecha = 0;
                    //
                    if (isFront) { //mostrar imagen de atras
                        isFront = false;
                        imagen = ((ImageIcon) iconBack).getImage();
                    } else {//mostrar imagen de adelante
                        isFront = true;
                        imagen = ((ImageIcon) iconFront).getImage();
                    }
                }
                //volvio a su posicion inicial -> detener animacion
                if (movimiento == false && desplazamientoHorizontal == 0) {
                    moviendose = false;
                    timer.stop();
                    desplazamientoIzquierda = 0;
                    desplazamientoDerecha = 0;
                    desplazamientoHorizontal = 0;
                    movimiento = false;
                   
                }
                
                
            }
        };
        //
        if (moviendose && !seleccionada) {
            if (timer != null) {
                
                movimiento=false;
                timer.stop();
            }
            timer = new Timer(velocidad, animation);
            timer.start(); //inicia animacion      
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        flipAnimate();
    }

    @Override
    public void mousePressed(MouseEvent e) {/*...*/
    }

    @Override
    public void mouseReleased(MouseEvent e) {/*...*/
    }

    @Override
    public void mouseEntered(MouseEvent e) {/*...*/
    }

    @Override
    public void mouseExited(MouseEvent e) {/*...*/
    }

    public Icon getIconBack() {
        return iconBack;
    }

    public void setIconBack(Icon iconBack) {
        this.iconBack = iconBack;
    }

    public Icon getIconFront() {
        return iconFront;
    }

    public void setIconFront(Icon iconFront) {
        this.iconFront = iconFront;
    }

    public int getDisplacement() {
        return desplazamiento;
    }

    public void setDisplacement(int displacement) {
        this.desplazamiento = displacement;
    }

    public int getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(int velocidad) {
        this.velocidad = velocidad;
    }

    void setCursor(int HAND_CURSOR) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean isSeleccionada() {
        return seleccionada;
    }

    
}
