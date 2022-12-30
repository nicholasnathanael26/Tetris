/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author asus
 */
public class Score {
    private String nama;
    private int high_score;
    
    public Score(){
        
    }

    public Score(String nama, int high_score) {
        this.nama = nama;
        this.high_score = high_score;
    }
    public String getNama(){
        return nama;
    }
    public void setNama(String nama){
        this.nama = nama;
    }
    public int getScore(){
        return high_score;
    }
    public void setScore(int high_score){
        this.high_score = high_score;
    }
}
