package ultimatetetris;

import java.util.ArrayList;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;


public class Kentta {
    
    private final ArrayList<ImageView>[] piirrettyKentta = new ArrayList[20];
    int[][] kentta;
    int[][] varikentta;
    
    public Kentta(int h, int w) {
        kentta = new int[h][w];
        varikentta = new int[h][w];
        for (int p = 0; p < piirrettyKentta.length; p++) {
            piirrettyKentta[p] = new ArrayList<>();
        }
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

    public void addPiirrettyKentta(int q, ImageView r) {
        piirrettyKentta[q].add(r);
    }

    void removePiirrettRivi(int k, Pane pane) {
        pane.getChildren().removeAll(piirrettyKentta[k]);
    }
   
}
