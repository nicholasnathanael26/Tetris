/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Element;

import Element.Shape.Komponen;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author asus
 */
public class Board extends JPanel implements ActionListener {

    final int lebar = 10;
    final int tinggi = 22;

    private Timer timer;
    private boolean isFallingFinished = false;
    private boolean isStarted = false;
    private boolean isPaused = false;
    private int numLinesRemoved = 0;
    private int currentX = 0;
    private int currentY = 0;
    private JLabel statusBar;
    private JLabel namaBar;
    private Shape currentPiece;
    private Komponen[] board;

    public Board() {
        setFocusable(true);
        currentPiece = new Shape();
        timer = new Timer(400, this);
        timer.start();
        board = new Komponen[lebar * tinggi];
        addKeyListener(new Tombol());
        hapus();
        namaBar = new JLabel();
        statusBar = new JLabel(" 0");
        add(statusBar, BorderLayout.SOUTH);
    }

    public JLabel getStatusBar() {
        return statusBar;
    }

    public void actionPerformed(ActionEvent e) {
        if (isFallingFinished) {
            isFallingFinished = false;
            potonganBaru();
        } else {
            oneLineDown();
        }
    }

    private int lebarKotak() {
        return (int) getSize().getWidth() / lebar;
    }

    private int tinggiKotak() {
        return (int) getSize().getHeight() / tinggi;
    }

    private Komponen shapeAt(int x, int y) {
        return board[(y * lebar) + x];
    }

    public void start() {
        if (isPaused) {
            return;
        }

        isStarted = true;
        isFallingFinished = false;
        numLinesRemoved = 0;
        hapus();

        potonganBaru();
        timer.start();
    }

    private void pause() {
        if (!isStarted) {
            return;
        }
        isPaused = !isPaused;
        if (isPaused) {
            timer.stop();
            statusBar.setText("pause");
        } else {
            timer.start();
            statusBar.setText(String.valueOf(numLinesRemoved));
        }
        repaint();
    }

    public void paint(Graphics g) {
        super.paint(g);

        Dimension size = getSize();
        int papanTop = (int) size.getHeight() - tinggi * tinggiKotak();

        for (int i = 0; i < tinggi; ++i) {
            for (int j = 0; j < lebar; ++j) {
                Komponen komponen = shapeAt(j, tinggi - i - 1);
                if (komponen != Komponen.Kosong) {
                    membuatKomponen(g, 0 + j * lebarKotak(), papanTop + i * tinggiKotak(), komponen);
                }
            }
        }

        if (currentPiece.getShape() != Komponen.Kosong) {
            for (int i = 0; i < 4; ++i) {
                int x = currentX + currentPiece.x(i);
                int y = currentY - currentPiece.y(i);
                membuatKomponen(g, 0 + x * lebarKotak(), papanTop + (tinggi - y - 1) * tinggiKotak(), currentPiece.getShape());
            }
        }
    }

    private void turunkanPenuh() {
        int newY = currentY;
        while (newY > 0) {
            if (!tryMove(currentPiece, currentX, newY - 1)) {
                break;
            }
            --newY;
        }
        menurunkanKomponen();
    }

    private void oneLineDown() {
        if (!tryMove(currentPiece, currentX, currentY - 1)) {
            menurunkanKomponen();
        }
    }

    private void hapus() {
        for (int i = 0; i < tinggi * lebar; ++i) {
            board[i] = Komponen.Kosong;
        }
    }

    private void menurunkanKomponen() {
        for (int i = 0; i < 4; ++i) {
            int x = currentX + currentPiece.x(i);
            int y = currentY - currentPiece.y(i);
            board[(y * lebar) + x] = currentPiece.getShape();
        }
        hapusBarisPenuh();
        if (!isFallingFinished) {
            potonganBaru();
        }
    }

    private void potonganBaru() {
        currentPiece.setRandom();
        currentX = lebar / 2 + 1;
        currentY = tinggi - 1 + currentPiece.minY();

        if (!tryMove(currentPiece, currentX, currentY)) {
            currentPiece.setKomponen(Komponen.Kosong);
            timer.stop();
            isStarted = false;
            statusBar.setText("GAME OVER");
        }
    }

    private boolean tryMove(Shape potonganBaru, int newX, int newY) {
        for (int i = 0; i < 4; ++i) {
            int x = newX + potonganBaru.x(i);
            int y = newY - potonganBaru.y(i);
            if (x < 0 || x >= lebar || y < 0 || y >= tinggi) {
                return false;
            }
            if (shapeAt(x, y) != Komponen.Kosong) {
                return false;
            }
        }

        currentPiece = potonganBaru;
        currentX = newX;
        currentY = newY;
        repaint();
        return true;
    }

    private void hapusBarisPenuh() {
        int jumlah = 0;

        for (int i = tinggi - 1; i >= 0; --i) {
            boolean barisPenuh = true;

            for (int j = 0; j < lebar; ++j) {
                if (shapeAt(j, i) == Komponen.Kosong) {
                    barisPenuh = false;
                    break;
                }
            }

            if (barisPenuh) {
                ++jumlah;
                for (int k = i; k < tinggi - 1; ++k) {
                    for (int j = 0; j < lebar; ++j) {
                        board[(k * lebar) + j] = shapeAt(j, k + 1);
                    }
                }
            }
        }
        
        
        //Hitung Score
        if (jumlah > 0) {
            numLinesRemoved += jumlah*100;
            statusBar.setText(String.valueOf(numLinesRemoved));
            isFallingFinished = true;
            currentPiece.setKomponen(Komponen.Kosong);
            repaint();
        }
    }

    private void membuatKomponen(Graphics g, int x, int y, Komponen komponen) {
        Color colors[] = {new Color(0, 0, 0), new Color(204, 0, 0),
                         new Color(100, 0, 0),new Color(0, 204, 0),
                         new Color(204, 100, 0), new Color(204, 0, 100),
                         new Color(204, 170, 100), new Color(218, 170, 0)
        };
        Color color = colors[komponen.ordinal()];
        g.setColor(color);
        g.fillRect(x + 1, y + 1, lebarKotak() - 2, tinggiKotak() - 2);

        g.drawLine(x, y + tinggiKotak() - 1, x, y);
        g.drawLine(x, y, x + lebarKotak() - 1, y);

    }

    class Tombol extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {

            if (!isStarted || currentPiece.getShape() == Komponen.Kosong) {
                return;
            }

            if (e.getKeyCode() == 'p' || e.getKeyCode() == 'P') {
                pause();
                return;
            }
            if (isPaused) {
                return;
            }

            switch (e.getKeyCode()) {
                case KeyEvent.VK_LEFT:
                    tryMove(currentPiece, currentX - 1, currentY);
                    break;
                case KeyEvent.VK_RIGHT:
                    tryMove(currentPiece, currentX + 1, currentY);
                    break;
                case KeyEvent.VK_DOWN:
                    tryMove(currentPiece.putarKanan(), currentX, currentY);
                    break;
                case KeyEvent.VK_UP:
                    tryMove(currentPiece.putarKiri(), currentX, currentY);
                    break;
                case KeyEvent.VK_SPACE:
                    turunkanPenuh();
                    break;
            }
        }
    }
}
