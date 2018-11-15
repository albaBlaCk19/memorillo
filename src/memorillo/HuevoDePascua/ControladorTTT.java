/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package memorillo.HuevoDePascua;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URL;

/**
 *
 * @author littl
 */
public class ControladorTTT implements ActionListener, MouseListener {

    private TicTacToe ttt;
    private static int turno=0;
    
    public ControladorTTT(TicTacToe ttt) {
        this.ttt = ttt;
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        System.out.println(ae.getActionCommand());
        
        if (ae.getActionCommand() == "" && turno%2==0) {
            System.out.println("Turno par");
            ttt.colocarFicha("jugador1", ae.getActionCommand());
            turno++;
        } else {
            System.out.println("Turno impar");
            ttt.colocarFicha ("jugador2", ae.getActionCommand());
            turno++;
        }
        
    }
    
    @Override
    public void mouseClicked(MouseEvent me) {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void mousePressed(MouseEvent me) {
        ttt.botonPulsado();
    }
    
    @Override
    public void mouseReleased(MouseEvent me) {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void mouseEntered(MouseEvent me) {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void mouseExited(MouseEvent me) {
        //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
