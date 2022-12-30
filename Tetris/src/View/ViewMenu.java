/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Element.Board;
import Element.Suara;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import tugastetris.TugasTetris;

/**
 *
 * @author asus
 */
public class ViewMenu extends JFrame {

    JLabel statusbar;

    public ViewMenu() {
        //add suara
        Suara s = new Suara();
        s.SuaraTema();

        //buat gambar
        JLabel judul = new JLabel();
        judul.setIcon(new ImageIcon(resizeImage("tetris.png")));

        //buat button (new game)
        JButton newGame = new JButton("New Game");
        newGame.setBackground(Color.BLACK);
        newGame.setForeground(Color.WHITE);
        newGame.setBounds(200, 150, 200, 20);

        //buat button (high score)
        JButton highScore = new JButton("High Score");
        highScore.setBackground(Color.BLACK);
        highScore.setForeground(Color.WHITE);
        highScore.setBounds(200, 180, 200, 20);

        //bikin jpanel buat judul
        JPanel panel1 = new JPanel();
        panel1.setSize(600, 150);
        panel1.add(judul);
        panel1.setBackground(Color.BLACK);

        //bikin jpanel buat menunya
        JPanel panel2 = new JPanel();
        panel2.setSize(600, 150);
        panel2.setLayout(null);
        panel2.add(newGame, BorderLayout.SOUTH);
        panel2.add(highScore, BorderLayout.SOUTH);
        panel2.setBackground(Color.BLACK);
        //add button to jframe
        add(panel1);
        add(panel2);

        //ActionListener Tombol newGame
        newGame.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                //Bikin jframe
                JFrame identitas = new JFrame();
                identitas.setSize(400, 300);
                identitas.setVisible(true);
                identitas.setLocationRelativeTo(null);
                identitas.setDefaultCloseOperation(EXIT_ON_CLOSE);
                identitas.getContentPane().setLayout(null);
                
                //bikin panel1 buat judul
                JPanel panel1 = new JPanel();
                panel1.setLayout(null);
                panel1.setBounds(0, 0, 400, 40);
                panel1.setBackground(Color.blue);

                //judul
                JLabel judul = new JLabel("IDENTITAS");
                judul.setFont(new Font("Times New Roman", Font.BOLD, 14));
                judul.setBounds(150, 10, 400, 20);
                judul.setForeground(Color.WHITE);
                panel1.add(judul);

                //panel2 buat isinya
                JPanel panel2 = new JPanel();
                panel2.setLayout(null);
                panel2.setBounds(0, 30, 400, 280);
                panel2.setBackground(Color.BLACK);

                //Nama
                JLabel nama = new JLabel("Nama:");
                nama.setBounds(5, 5, 100, 25);
                nama.setForeground(Color.WHITE);
                panel2.add(nama);
                JTextField txtNama = new JTextField();
                txtNama.setBounds(5, 25, 100, 25);
                panel2.add(txtNama);

                JButton submit = new JButton("SUBMIT");
                submit.setBounds(145, 150, 100, 25);
                panel2.add(submit);

                identitas.add(panel1);
                identitas.add(panel2);
                
                submit.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        TugasTetris t = new TugasTetris();
                        t.setVisible(true);
                        t.setLocationRelativeTo(null);
                        setVisible(false);
                    }

                });
            }

        });

        //ActionListener Tombol Highscore
        highScore.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFrame HS = new JFrame();
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
                HS.add(panel1);
                HS.add(panel2);
                HS.add(panel3);

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
                HS.setSize(600, 400);
                HS.setLocationRelativeTo(null);
                HS.getContentPane().setBackground(java.awt.Color.BLACK);
                HS.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                HS.setVisible(true);
            }
        });

        //setting some attributes for frame
        setSize(600, 400);
        setLocationRelativeTo(null);
        getContentPane().setBackground(Color.BLACK);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

    }

    private Image resizeImage(String url) {
        Image dimg = null;
        try {
            BufferedImage img = ImageIO.read(new File(url));
            dimg = img.getScaledInstance(500, 150, Image.SCALE_SMOOTH);
        } catch (IOException ex) {
            ex.printStackTrace(System.err);
        }
        return dimg;
    }

}
