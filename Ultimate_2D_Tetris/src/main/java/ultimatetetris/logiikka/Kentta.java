package ultimatetetris.logiikka;

import java.util.ArrayList;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;


public class Kentta {
    
    private final ArrayList<ImageView>[] piirrettyKentta = new ArrayList[20];
    int[][] kentta;
    int[][] varikentta;
   /**
    * Alustaa peliä varten kentan, värikentän, ja piirrettykentänä.
    * Kentässä tieto onko palikoiden sijainneista.
    * Värikentässä tieto näiden palikoiden väreistä.
    * Piirrettykentässä tieto piirretyistä kuvista joita asetetaan näytölle, jotta tiettyjen rivien piirtämisiä olisi helpompi muokata
     * @param h luotavan kentän korkeus.
     * @param w luotavan kentän leveys.
    */
    public Kentta(int h, int w) {
        kentta = new int[h][w];
        varikentta = new int[h][w];
        for (int p = 0; p < piirrettyKentta.length; p++) {
            piirrettyKentta[p] = new ArrayList<>();
        }
    }
    
    /**
     * Palauttaa kentän kohdassa olevan palikan tiedon jos sitä on.
     * @param h kohta-y kentässä.
     * @param w kohta-x kentässä.
     * @return palauttaa arvon sijainnissa h, w.
     */
    public int getKohta(int h, int w) {
        return kentta[h][w];
    }
    /**
    * Asettaa kentälle kohtaan arvon. Käytetään kun palikka on tippunut alas ja halutaan tallettaa se taulukkoon. 
    * @param h kohta-y kentässä.
    * @param w kohta-x kentässä.
    * @param arvo asetettava arvo, usein 1.
    */
    public void setKohta(int h, int w, int arvo) {
        kentta[h][w] = arvo;
    }
    /**
    * Palauttaa värikentän kohdassa olevan palikan varin jolloin piirrossa tiedetään minkä värinen kuva siihen kohtaa asetetaan. 
    * @param h kohta-y kentässä.
    * @param w kohta-x kentässä.
     * @return palauttaa varin kohdassa h, w.
    */
    public int getVari(int h, int w) {
        return varikentta[h][w];
    }
    /**
     * Asettaa värikentälle kohtaan tiedon sen väristä. Käytetään samaa aikaa kuin setKohta jolloin palikalle saadaan tieto sen väristä.
     * @param h kohta-y kentässä. 
     * @param w kohta-x kentässä.
     * @param arvo asetettava väri.
     */
    public void setVari(int h, int w, int arvo) {
        varikentta[h][w] = arvo;
    }
    /**
     * Kerroksen poistaminen kun peli tunnistaa täyden rivin. 
     * @param m kertoo mikä rivi poistetaan.
     * @param pane tieto mistä paneelista kyse kun poistetaan piirrettykentässä olevan rivin tieto siinnä olevista piirretyistä kuvista. 
     */
    public void kerrosVaihto(int m, Pane pane) {
        for (int alku = m; alku >= 0; alku--) {
            if (alku == 0) {
                int[] rivi = new int[10];
                kentta[alku] = rivi;
                varikentta[alku] = rivi;
                piirrettyKentta[alku] = new ArrayList<>();
                continue;
            }
            for (int i = 0; i < 10; i++) {
                kentta[alku][i] = 0;
                varikentta[alku][i] = 0;
                if (kentta[alku - 1][i] == 2 || kentta[alku - 1][i] == 1) {
                    kentta[alku][i] = 1;
                    varikentta[alku][i] = getVari(alku - 1, i);
                }
            }
            pane.getChildren().removeAll(piirrettyKentta[alku]);
            piirrettyKentta[alku] = new ArrayList<>();
        }
    }
    /**
     * Lisätään piirrettykenttään tieto uudesta kuvasta. 
     * @param q tieto mille riville uusi kuva laitetaa.
     * @param r tieto kuvasta, joka asetetaan piirrettykentään.
     */
    public void addPiirrettyKentta(int q, ImageView r) {
        piirrettyKentta[q].add(r);
    }
    /**
     * Poistetaan yhdeltä riviltä kaikki siihen piirretyt kuvat.
     * @param k Tieto rivistä
     * @param pane Tieto paneelista josta kuvat poistetaan.
     */
    public void removePiirrettRivi(int k, Pane pane) {
        pane.getChildren().removeAll(piirrettyKentta[k]);
    }
   
}
