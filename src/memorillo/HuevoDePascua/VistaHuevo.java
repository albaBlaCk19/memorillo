/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package memorillo.HuevoDePascua;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

/**
 *
 * @author littl
 */
public class VistaHuevo {

    
    private JFrame ventanaHuevo;
    private TicTacToe ttt;

    public VistaHuevo() {

        ventanaHuevo = new JFrame("Tic Tac Toe");
        ventanaHuevo.setLayout(null);
        ventanaHuevo.setBounds(200, 20, 1000, 1000);

        ttt = new TicTacToe();
        ventanaHuevo.add(ttt);
        ventanaHuevo.setVisible(true);

    }

    
}
