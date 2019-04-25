package ultimatetetris;

import java.util.ArrayList;
import java.util.Random;
import javafx.scene.image.Image;

public class Palikka {
    
    Random r = new Random();
    boolean liikutettu;
    int liikutettuarvo = 0;
    
    int muoto;
    public int y;
    public int x;
    int suunta;
    Image[] vari;
    Kentta kentta;
    int[][][] muodot = {{{0, 0, 0, 0}, {1, 1, 1, 1}, {0, 0, 0, 0}, {0, 0, 0, 0}},
                        {{1, 0, 0}, {1, 1, 1}, {0, 0, 0}},
                        {{0, 0, 1}, {1, 1, 1}, {0, 0, 0}},
                        {{1, 1}, {1, 1}},
                        {{0, 1, 1}, {1, 1, 0}, {0, 0, 0}},
                        {{0, 1, 0}, {1, 1, 1}, {0, 0, 0}},
                        {{1, 1, 0}, {0, 1, 1}, {0, 0, 0}}
    };
    
    public int[][] nykypalikka;
    public ArrayList<Kuutio> kuutiot = new ArrayList<>();
    boolean painettu = false;
    /**
     * Luodaan uusi palikka. Palikan luonnissa käydään muodon antaman arvon mukaisesti läpi mihin kohtiin luodaan 4 Kuutiota jotka liikkuvat symmettrisesti.
     * 
     * @param vari annetaan array mahdollisia kuvia joita käytetään palikan luomisessa.
     * @param kentta kerrotaan mihin kenttään palikka luodaan.
     * @param muoto arvottu arvo jonka muotoinen palikka on.
     */
    public Palikka(Image[] vari, Kentta kentta, int muoto) {
        
        this.liikutettuarvo = 0;
        this.liikutettu = true;
        this.muoto = muoto;
        this.y = 0;
        if (muoto == 3) {
            this.x = 4;
        } else {
            this.x = 3;
        }
        this.vari = vari;
        this.kentta = kentta;
        for (int h = 0; h < muodot[muoto].length; h++) {
            for (int w = 0; w < muodot[muoto][h].length; w++) {
                if (muodot[muoto][h][w] == 1) {
                    kuutiot.add(new Kuutio((x + w) * 30, (y + h) * 30, vari[muoto], this.kentta));
                }
            }
        }
        this.nykypalikka = muodot[muoto];
    }
    /**
     * Liikutetaan tämän hetksitä palikaa vasemmalle. Liikkumisessa käydään läpi palikan kaikki kuutiot ja liikutetaan niitä yksitellen. 
     */
    public void liikuVasen() {
        if (!tormaaVasen()) {
            x--;
            for (int k = 0; k < kuutiot.size(); k++) {
                kuutiot.get(k).liikuVasen();
            }
            liikutettu = true;
        }
    }
    /**
     * Liikutetaan tämän hetkistä palikaa oikealle. Liikkumisessa käydään läpi palikan kaikki kuutiot ja liikutetaan niitä yksitellen.
     */
    public void liikuOikea() {
        if (!tormaaOikea()) {
            x++;
            for (int k = 0; k < kuutiot.size(); k++) {
                kuutiot.get(k).liikuOikea();
            }
            liikutettu = true;
        }
    }
    /**
     * Liikutetaan tämän hetkistä palikkaa alas päin. Liikkumisessa käydään läpi palikan kaikki kuutiot ja liikutetaan niitä yksitellen.
     */
    public void liikuAlas() {
        if (!tormaaAlas()) {
            y++;
            for (int k = 0; k < kuutiot.size(); k++) {
                kuutiot.get(k).liikuAlas();
            }
            liikutettu = true;
        } else {
            painettu = true;
            
        }
    }
    
    void liikuPohja() {
        while (!tormaaAlas()) {
            liikuAlas();
        }
    }
    /**
     * Pyörittää tällähetkellä kentällä olevaa palikkaa.
     * jos kutsutaan arvolla 0 pyoritetään oikeaan, ja jos 1 pyritetään vasempaan.
     * @param s tieto suunnasta mihin pyöritetään.
     */
    public void pyorita(int s) {
        int[][] kopio = new int[nykypalikka.length][nykypalikka[0].length];
        for (int w = 0; w < nykypalikka.length; w++) {
            for (int h = 0; h < nykypalikka[0].length; h++) {
                if (s == 0) {
                    kopio[w][h] = nykypalikka[(nykypalikka.length - 1) - h][w];
                } else {
                    kopio[w][h] = nykypalikka[h][(nykypalikka.length - 1) - w];
                }
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
                    if ((y + h) >= 19) {
                        return false;
                    }
                    if ((x + w) > 9 || (x + w) < 0) {
                        return false;
                    }
                    if (kentta.getKohta(y + h, x + w) != 0) {
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
                    kuutiot.add(new Kuutio((x + w) * 30, (y + h) * 30, vari[muoto], kentta));
                }
            }
        }
    }
    
    void liikkumattomuus() {
        for (int k = 0; k < kuutiot.size(); k++) {
            int finalX = kuutiot.get(k).getKohtaX();
            int finalY = kuutiot.get(k).getKohtaY();
            kentta.setKohta(finalY, finalX, 1);
            kentta.setVari(finalY, finalX, muoto);
        }
    }

    private boolean tormaaOikea() {
        for (int k = 0; k < kuutiot.size(); k++) {
            if (kuutiot.get(k).tormaaOikea()) {
                return true;
            }
        }
        return false;
    }

    private boolean tormaaVasen() {
        for (int k = 0; k < kuutiot.size(); k++) {
            if (kuutiot.get(k).tormaaVasen()) {
                return true;
            }
        }
        return false;
    }

    boolean tormaaAlas() {
        for (int k = 0; k < kuutiot.size(); k++) {
            if (kuutiot.get(k).tormaaAlas()) {
                return true;
            }
        }
        return false;
    }
    
    boolean liikutettualas() {
        boolean nyt = painettu;
        painettu = false;
        return (nyt);
    }

    int getMuotoN() {
        return muoto;
    }
    
    int[][] tiettyMuoto(int m) {
        return muodot[m];
    }
   
    public ArrayList<Kuutio> getKuutiot() {
        return kuutiot;
    }

    public int[][] getMuoto() {
        return muodot[muoto];
    }
}
