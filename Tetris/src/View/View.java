/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

/**
 *
 * @author cynthia
 */
import Element.Board;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.Icon;
import javax.swing.ImageIcon;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class View extends JFrame {

    JFrame frameNewGame = new JFrame("New Game");

    public void actionPerformed(ActionEvent event) {
        if (event.getActionCommand() == "New Game") {
            Board board = new Board();
            add(board);
            board.start();
            setSize(200, 400);
            setTitle("TETRIS GAME");
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            board.requestFocus();
        } else if (event.getActionCommand() == "High Score") {

            JLabel judul = new JLabel("High Score");
            //buat button (delete)
            JButton delete = new JButton("Delete");
            delete.setBackground(java.awt.Color.BLACK);
            delete.setForeground(java.awt.Color.WHITE);
            delete.setBounds(230, 250, 150, 20);

            //buat button (back)
            Icon iconBack = new ImageIcon("D:\\ITHB\\Semester 3\\Prak PBO\\TUBES\\back.png");
            JButton back = new JButton(iconBack);
            back.setBackground(java.awt.Color.BLACK);
            back.setForeground(java.awt.Color.WHITE);
            back.setBounds(50, 320, 28, 25);

            //bikin jpanel buat judul
            JPanel panel1 = new JPanel();
            panel1.setSize(600, 150);
            panel1.add(judul);
            panel1.setBackground(java.awt.Color.BLACK);

            //bikin JTable buat data scorenya
            JPanel panel2 = new JPanel();
            String data[][] = {{"1.", "Dave", "670"},
            {"2.", "Cyn", "780"},
            {"3.", "Nathan", "700"},
            {"4.", "Brein", "500"}};
            String column[] = {"NUMBER", "NAME", "SCORE"};
            JTable jt = new JTable(data, column);

            jt.setBounds(200, 180, 200, 300);
            jt.setBackground(java.awt.Color.BLACK);
            jt.setForeground(java.awt.Color.WHITE);
            JScrollPane sp = new JScrollPane(jt);
            panel2.add(sp);

            panel2.setSize(600, 150);
            panel2.setBackground(java.awt.Color.BLACK);

            //bikin jpanel buat menunya
            JPanel panel3 = new JPanel();
            panel3.setSize(600, 150);
            panel3.setLayout(null);
            panel3.add(delete, BorderLayout.SOUTH);
            panel3.add(back, BorderLayout.SOUTH);
            panel3.setBackground(java.awt.Color.BLACK);
            //add button to jframe
            add(panel1);
            add(panel2);
            add(panel3);

            //ActionListener Tombol delete
            delete.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    DefaultTableModel model = new DefaultTableModel();
                    int rowCount = model.getRowCount();

                    for (int i = rowCount - 1; i >= 0; i--) {
                        model.removeRow(i);
                    }
                }
            });
            //ActionListener Tombol back
            back.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {

                    ViewMenu VM = new ViewMenu();
                    VM.setVisible(true);
                }
            });

            //setting some attributes for frame
            setSize(600, 400);
            setLocationRelativeTo(null);
            getContentPane().setBackground(java.awt.Color.BLACK);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setVisible(true);

        }
    }

    public View() {
        frameNewGame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                ImageIcon icon = new ImageIcon("D:\\ITHB\\Semester 3\\Prak PBO\\TUBES\\exit.png");
                int result = JOptionPane.showConfirmDialog(frameNewGame,
                        "Do you want to Exit ?", "Exit Game..?",
                        JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION) {
                    frameNewGame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                } else if (result == JOptionPane.NO_OPTION) {
                    frameNewGame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                }
            }
        });
    }

}
