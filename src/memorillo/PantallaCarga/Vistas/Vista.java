/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package memorillo.PantallaCarga.Vistas;

import java.awt.Image;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.MenuShortcut;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;
import memorillo.Cronometro;
import memorillo.HuevoDePascua.VistaHuevo;
import memorillo.Juego;
import memorillo.PantallaCarga.Controladores.ControladorMenu;
import memorillo.PantallaCarga.Vistas.PantallaCargaJuego;
import memorillo.PantallaCarga.Vistas.CustomPanelGrupoImagenes;
import memorillo.PantallaCarga.Vistas.CustomPanelJugador;
import memorillo.PantallaCarga.Vistas.CustomPanelNiveles;

/**
 *
 * @author littl
 */
public class Vista {

    private JFrame ventana;
    private ControladorMenu controladorMenu;
    private CustomPanelJugador pDatosJugador;
    private Cronometro cronometro;
    private CustomPanelNiveles pNiveles;
    private CustomPanelGrupoImagenes pElegirImagen;
    private PantallaCargaJuego pantallaCarga;
    private Juego juego;

    /*
    cosas que uso para el menu
     */
    private MenuBar barraMenu;
    private Menu mMenu;
    private Menu mGame;
    private MenuItem miNewGame;
    private MenuItem miSaveGame;
    private MenuItem miLoadGame;
    private MenuItem miExit;
    private MenuItem miPause;
    private MenuItem miResume;
    private MenuItem miHighscore;
    private MenuItem miHowToPlay;
    private MenuItem miAboutUs;

    // estas variables estaticas las utilizaremos para el menu
    protected static final String NEW_GAME = "New Game";
    protected static final String SAVE_GAME = "Save Game";
    protected static final String LOAD_GAME = "Load Game";
    protected static final String EXIT = "Exit";

    protected static final String PAUSE = "Pause";
    protected static final String RESUME = "Resume";

    protected static final String HIGHSCORE = "Highscore";

    protected static final String HOW_TO_PLAY = "How To Play";
    protected static final String ABOUT_US = "About us";

    private String nivel = "medium";

    public Vista() {
        controladorMenu = new ControladorMenu(pantallaCarga, this);
        ventana = new JFrame("MemoRillo");

        ventana.setLayout(null);
        ventana.setBounds(200, 15, 1500, 1000);
        crearMenu();
        crearPantallaCarga();

        //VistaHuevo vh = new VistaHuevo();

        ventana.setIconImage(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/img/iconoApp.png")).getScaledInstance(500, 500, Image.SCALE_SMOOTH));
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setVisible(true);
    }

    public void crearPantallaCarga() {
        pantallaCarga = new PantallaCargaJuego(this);
        pantallaCarga.setVisible(true);
        ventana.add(pantallaCarga);
    }

    public void crearJuego() {
        juego = new Juego(pantallaCarga.getNivelSeleccionado(), pantallaCarga.getImagenesSeleccionadas());
        juego.setVisible(true);
        ventana.add(juego);
        pantallaCarga.setVisible(false);
    }

    /**
     * Metodo donde creo el menu (falta pasar a alba)
     */
    public void crearMenu() {

        // creo la barra de menu
        barraMenu = new MenuBar();

        // creo los elementos del menu
        mMenu = new Menu("Menu");
        mGame = new Menu("Game");
        Menu mStats = new Menu("Stats");
        Menu mHelp = new Menu("Help");

        /*creo los "atajos de teclado" */
        // estos tres son los de MENU
        MenuShortcut mscNewGame = new MenuShortcut(KeyEvent.VK_N, false);    //ctrl+N
        MenuShortcut mscSaveGame = new MenuShortcut(KeyEvent.VK_S, false);  //ctrl+S
        MenuShortcut mscLoadGame = new MenuShortcut(KeyEvent.VK_L, false); // ctrl+L

        // estos dos del GAME
        MenuShortcut mscPause = new MenuShortcut(KeyEvent.VK_P, false); //ctrl+P
        MenuShortcut mscResume = new MenuShortcut(KeyEvent.VK_R, false); //ctrl+R

        // este es el de Help
        MenuShortcut mscHowToPlay = new MenuShortcut(KeyEvent.VK_H, false); //ctrl+H

        /* creo los elementos menuItem */
        miNewGame = new MenuItem(NEW_GAME, mscNewGame);
        miSaveGame = new MenuItem(SAVE_GAME, mscSaveGame);
        miLoadGame = new MenuItem(LOAD_GAME, mscLoadGame);
        miExit = new MenuItem(EXIT);

        miPause = new MenuItem(PAUSE, mscPause);
        miResume = new MenuItem(RESUME, mscResume);

        miHighscore = new MenuItem(HIGHSCORE);
        miHowToPlay = new MenuItem(HOW_TO_PLAY, mscHowToPlay);
        miAboutUs = new MenuItem(ABOUT_US);

        // añado los elementos al menu
        mMenu.add(miNewGame);
        mMenu.add(miSaveGame);
        mMenu.add(miLoadGame);
        mMenu.add(miExit);

        mGame.add(miPause);
        mGame.add(miResume);

        mStats.add(miHighscore);

        mHelp.add(miHowToPlay);
        mHelp.add(miAboutUs);
        escuchadores();

        // añado los elementos Menu a la barra de menu
        barraMenu.add(mMenu);
        barraMenu.add(mGame);
        barraMenu.add(mStats);
        barraMenu.add(mHelp);

        // añado la barra de menu a la ventana
        ventana.setMenuBar(barraMenu);

    }

    /**
     * Metodo eliminarMGame()
     *
     * este metodo lo utilizaremos en alguna pantalla para que esta opcion no
     * nos aparezca en el menu
     */
    public void eliminarMGame() {
        barraMenu.remove(mGame);
    }

    public void escuchadores() {
        miAboutUs.addActionListener(controladorMenu);
        miNewGame.addActionListener(controladorMenu);
        miExit.addActionListener(controladorMenu);
    }
}
