/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ultimate_2d_tetris.ot.harjoitustyo;

import java.util.Arrays;


public class Kentta {
    int[][] kentta;
    int[][] varikentta;
    public Kentta(int h, int w) {
        kentta = new int[h][w];
        varikentta = new int[h][w];
    }
    public int getKohta(int h, int w) {
        return kentta[h][w];
    }
    public void setKohta(int h, int w, int arvo) {
        kentta[h][w] = arvo;
    }
    public int getVari(int h, int w) {
        return varikentta[h][w];
    }
    public void setVari(int h, int w, int arvo) {
        varikentta[h][w] = arvo;
    }
    
    void kerrosVaihto(int m) {
        for (int alku = m; alku > 1; alku--) {
            kentta[alku] = kentta[alku - 1];
            varikentta[alku] = varikentta[alku-1];
            for (int e = 0; e < kentta[alku].length; e++) {
                if (kentta[alku][e] == 2) {
                    kentta[alku][e] = 1;
                }
            }
        }

    }
}
