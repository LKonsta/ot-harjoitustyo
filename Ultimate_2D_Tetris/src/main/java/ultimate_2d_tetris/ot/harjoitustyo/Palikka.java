/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ultimate_2d_tetris.ot.harjoitustyo;

import java.util.ArrayList;
import java.util.Arrays;
import javafx.scene.paint.Color;

/**
 *
 * @author khlehto
 */
public class Palikka {
    
    boolean liikutettu;
    int liikutettuarvo = 0;
    
    int muoto;
    public int y;
    public int x;
    int suunta;
    Kentta kentta;
    Color[] vari;
    int[][][] muodot = {{{1,1},{1,1}},
                        {{0,0,1,0},{0,0,1,0},{0,0,1,0},{0,0,1,0}},
                        {{0,1,0},{0,1,1},{0,0,1}},
                        {{0,1,0},{1,1,0},{1,0,0}},
                        {{0,1,0},{0,1,0},{0,1,1}},
                        {{0,1,0},{0,1,0},{1,1,0}},
                        {{0,1,0},{1,1,1},{0,0,0}}
    };
    
    public int[][] nykypalikka;
    public ArrayList<Kuutio> kuutiot = new ArrayList<>(); 
    

    
    public Palikka(int x, int y, Color[] vari, Kentta kentta, int muoto) {
        this.liikutettuarvo = 0;
        liikutettu = false;
        this.suunta = 1;
        this.muoto = muoto;
        this.y = y;
        this.x = x;
        this.vari = vari;
        this.kentta = kentta;
        for (int h = 0; h < muodot[muoto].length;h++) {
            for (int w = 0; w < muodot[muoto][h].length; w++) {
                if (muodot[muoto][h][w] == 1) {
                    kuutiot.add(new Kuutio((x + w) * 40, (y + h) * 40, 40, 40, vari[muoto], kentta));
                }
            }
        }
        this.nykypalikka = muodot[muoto];
    }
    
    public ArrayList<Kuutio> getKuutiot() {
        return kuutiot;
    }

    public void liikuVasen() {
        if (!tormaaVasen()) {
            x--;
            for (int k = 0;k<kuutiot.size();k++) {
                kuutiot.get(k).liikuVasen();
            }
            liikutettu = true;
        }
    }

    public void liikuOikea() {
        if (!tormaaOikea()) {
            x++;
            for (int k = 0;k<kuutiot.size();k++) {
                kuutiot.get(k).liikuOikea();
            }
            liikutettu = true;
        }
    }
    
    public void liikuAlas() {
        if (!tormaaAlas()) {
            y++;
            for (int k = 0;k<kuutiot.size();k++) {
                kuutiot.get(k).liikuAlas();
            }
            liikutettu = true;
        } else {
            painettu = true;
            
        }
    }
    boolean painettu = false;
    boolean liikutettualas() {
        boolean nyt = painettu;
        painettu = false;
        return (nyt);
    }

    public void pyoritaOikea() {
        int[][] kopio = new int[nykypalikka.length][nykypalikka[0].length];
        for (int w = 0;w<nykypalikka.length;w++) {
            for (int h = 0;h<nykypalikka[0].length;h++) {
                kopio[w][h] = nykypalikka[h][(nykypalikka.length-1)-w];
            }
        }
        if (kopioTormaa(kopio)) {
            nykypalikka = kopio;
            liikutettu = true;
            updatePalikka();
        }
    }

    void pyoritaVasen() {
        int[][] kopio = new int[nykypalikka.length][nykypalikka[0].length];
        for (int w = 0;w<nykypalikka.length;w++) {
            for (int h = 0;h<nykypalikka[0].length;h++) {
                kopio[w][h] = nykypalikka[(nykypalikka.length-1)-h][w];
            }
        }
        if (kopioTormaa(kopio)) {
            nykypalikka = kopio;
            liikutettu = true;
            updatePalikka();
        
        }
    }
    
    private boolean kopioTormaa(int[][] kopio) {
        for (int h = 0; h < kopio.length; h++) {
            for (int w = 0; w < kopio[0].length; w++) {
                if (kopio[h][w] == 1) {
                    if ((y+h)>=19) {
                        return false;
                    }
                    if ((x+w)>9 || (x+w)<0) {
                        return false;
                    }
                    if (kentta.getKohta(y+h,x+w) != 0) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
    
    

    private void updatePalikka() {
        kuutiot.clear();
        for (int h = 0; h < nykypalikka.length; h++) {
            for (int w = 0; w < nykypalikka[0].length; w++) {
                if (nykypalikka[h][w] == 1) {
                    kuutiot.add(new Kuutio((x + w) * 40, (y + h) * 40, 40, 40, vari[muoto], kentta));
                }
            }
        }
    }


    void liikkumattomuus() {
        for (int k = 0; k < kuutiot.size(); k++) {
            int x = kuutiot.get(k).getKohtaX();
            int y = kuutiot.get(k).getKohtaY();
            kentta.setKohta(y, x, 1);
            kentta.setVari(y, x, muoto);
        }
        
    }

    private boolean tormaaOikea() {
        for (int k = 0; k < kuutiot.size(); k++) {
            if (kuutiot.get(k).TormaaOikea()) {
                return true;
            }
        }
        return false;
    }

    private boolean tormaaVasen() {
        for (int k = 0; k < kuutiot.size(); k++) {
            if (kuutiot.get(k).TormaaVasen()) {
                return true;
            }
        }
        return false;
    }

    boolean tormaaAlas() {
        for (int k = 0; k < kuutiot.size(); k++) {
            if (kuutiot.get(k).TormaaAlas()) {
                return true;
            }
        }
        return false;
    }

    void liikutettuSetFalse() {
        liikutettu = false;
        liikutettuarvo++;
    }
    boolean getLiikutettu() {
        return liikutettu;
    }
    int getLiikutettuArvo() {
        return liikutettuarvo;
    }
}
