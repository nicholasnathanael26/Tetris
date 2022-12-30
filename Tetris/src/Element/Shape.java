/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Element;

import java.util.Random;

/**
 *
 * @author asus
 */
public class Shape {
    protected enum Komponen{
        Kosong, BentukZ, BentukZ2, BentukBatang,
        BentukT, BentukKotak, BentukL, BentukL2
    }
    
    private Komponen potonganKomponen;
    private int kordinat[][];
    
    public Shape(){
        kordinat = new int[4][2];
        setKomponen(Komponen.Kosong);
    }
    
    public void setKomponen(Komponen komponen){
        int [][][] tableKordinat = new int[][][] {
            { { 0, 0 },   { 0, 0 },   { 0, 0 },   { 0, 0 } },
            { { 0, -1 },  { 0, 0 },   { -1, 0 },  { -1, 1 } },
            { { 0, -1 },  { 0, 0 },   { 1, 0 },   { 1, 1 } },
            { { 0, -1 },  { 0, 0 },   { 0, 1 },   { 0, 2 } },
            { { -1, 0 },  { 0, 0 },   { 1, 0 },   { 0, 1 } },
            { { 0, 0 },   { 1, 0 },   { 0, 1 },   { 1, 1 } },
            { { -1, -1 }, { 0, -1 },  { 0, 0 },   { 0, 1 } },
            { { 1, -1 },  { 0, -1 },  { 0, 0 },   { 0, 1 } }
        };
        for (int i = 0; i < 4 ; i++){
            for (int j = 0; j < 2; ++j){
                kordinat[i][j] = tableKordinat[komponen.ordinal()][i][j];
            }
        }
        potonganKomponen = komponen;
    }
    
    
    private void setX(int index, int x){
        kordinat[index][0] = x;
    }
    private void setY(int index, int y){
        kordinat[index][1] = y;
    }
    public int x(int index){
        return kordinat[index][0];
    }
    public int y(int index){
        return kordinat[index][1];
    }
    public Komponen getShape(){
        return potonganKomponen;
    }
    
    public void setRandom(){
        Random r = new Random();
        int x = Math.abs(r.nextInt()) % 7 + 1;
        Komponen[] values = Komponen.values(); 
        setKomponen(values[x]);
    }
    
    
    public int minY(){
        int m = kordinat[0][1];
        for (int i=0; i < 4; i++) {
          m = Math.min(m, kordinat[i][1]);
        }
        return m;
    }
    
    public Shape putarKiri() {
        if (potonganKomponen == Komponen.BentukKotak){
            return this;
        }

        Shape result = new Shape();
        result.potonganKomponen = potonganKomponen;

        for (int i = 0; i < 4; ++i){
            result.setX(i, y(i));
            result.setY(i, -x(i));
        }
        return result;
    }
    
    public Shape putarKanan(){
        if (potonganKomponen == Komponen.BentukKotak){
            return this;
        }
        Shape result = new Shape();
        result.potonganKomponen = potonganKomponen;
        for (int i = 0; i < 4; ++i) {
            result.setX(i, -y(i));
            result.setY(i, x(i));
        }
        return result;
    }
}
