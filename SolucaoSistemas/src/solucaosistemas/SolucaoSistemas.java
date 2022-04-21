/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solucaosistemas;

import SolucaoSistemasVIEW.LogarVIEW;
import java.io.IOException;
import java.net.ServerSocket;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author Vinicius
 */
public class SolucaoSistemas {

     private static ServerSocket s;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
         try {
           
            try {
                //  UIManager.setLookAndFeel("com.jtattoo.plaf.mint.MintLookAndFeel");
                UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
                s = new ServerSocket(9581);
               // new AtendimentoVIEW(null, true).setVisible(true);
                 new LogarVIEW(null, true).setVisible(true);

            } catch (IOException | NullPointerException ex) {
                UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
                System.out.println(""+ex.getMessage());
                JOptionPane.showMessageDialog(null, "Sistema aberto!", "ATENÇÃO",
                        JOptionPane.WARNING_MESSAGE);

                try {

                    s.close();
                    System.exit(0);
                } catch (IOException ex1) {
                    System.out.println("" + ex1.getMessage());
                }

            }

        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException |
                UnsupportedLookAndFeelException ex) {
            System.out.println("AQUI " + ex.getMessage());
        }
        
    }
    
}
