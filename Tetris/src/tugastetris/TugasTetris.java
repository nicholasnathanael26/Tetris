/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tugastetris;

import Element.Board;
import database.ConnectionManager;
import View.ViewMenu;
import java.awt.BorderLayout;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;

/**
 *
 * @author asus
 */
public class TugasTetris extends JFrame {

    /**
     * @param args the command line arguments
     */
    public TugasTetris() {

        Board board = new Board();
        add(board);
        board.start();

        setSize(300, 600);
        setTitle("Tetris");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
   }
    
    public static void main(String[] args) {
        // TODO code application logic here
        ViewMenu v = new ViewMenu();
        v.setLocationRelativeTo(null);
        v.setVisible(true);
//        TugasTetris t = new TugasTetris();
//        t.setVisible(true);
    }

}
