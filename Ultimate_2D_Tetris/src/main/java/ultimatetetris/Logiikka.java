package ultimatetetris;


import java.util.Random;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import ultimatetetris.ui.Ui;

public class Logiikka {
    
    static Random r = new Random();
    static Color[] varit = {Color.YELLOW, Color.TEAL, Color.GREEN, Color.RED, Color.ORANGE, Color.BLUE, Color.PURPLE};
    
    public static void uusiPalikka() {
        palikka = new Palikka(3, 0, varit, kentta, r.nextInt(7));
        Ui.tetrisAdd(palikka);
    }
    
    public static void uusiKentta() {
        kentta = new Kentta(20, 10);
    }
    
    private static Kentta kentta;
    private static Palikka palikka;
    private static int odotus = 0; 
    
    private static boolean palikkaalhaalla = true;
    
    public static void update() {
        if (palikka == null) {
            uusiPalikka();
        }
        odotus += 30;
        
        if (palikka.tormaaAlas()) {
            if (!(palikka.getLiikutettu())) {
                palikka.liikkumattomuus();
                Ui.tetrisRemove(palikka);
                uusiPalikka();
                onkoTaysiRivi();
            }
        }
        if (palikka.liikutettualas()) {
            palikka.liikkumattomuus();
            Ui.tetrisRemove(palikka);
            uusiPalikka();
            onkoTaysiRivi();
        }
        if (odotus % 900 == 0) {
            if (!palikka.tormaaAlas()) {
                palikka.liikuAlas();
            }
            if (palikka.tormaaAlas() && palikkaalhaalla) {
                odotus = 30;
                palikkaalhaalla = false;
            }
            if (odotus % 900 == 0) {
                if (palikka.tormaaAlas()) {
                    palikka.liikutettuSetFalse();
                    palikkaalhaalla = true;
                }
                odotus = 0;
            }
        }
        for (int q = 0; q < 20; q++) {
            for (int w = 0; w < 10; w++) {
                if (Logiikka.getKentta().getKohta(q, w) == 1) {
                    Rectangle r = new Rectangle(30, 30, varit[Logiikka.getKentta().getVari(q, w)]);
                    r.setX(w * 30);
                    r.setY(q * 30);
                    Ui.piirettyAdd(q, r);
                    Logiikka.getKentta().setKohta(q, w, 2);
                }
            }
        }
    }
    
    public static void onkoTaysiRivi() {
        boolean[] kerrokset = new boolean[20];
        for (int q = 0; q < 20; q++) {
            boolean kerros = true;
            for (int w = 0; w < 10; w++) {
                if (kentta.getKohta(q, w) == 0) {
                    kerros = false;
                }
            }
            if (kerros) {
                kerrokset[q] = true;
            }
        }
        for (int m = 0; m < kerrokset.length; m++) {
            if (kerrokset[m]) {
                for (int p = m; p > 0; p--) {
                    Ui.piirrettyRemoveRivi(p);
                }
                kentta.kerrosVaihto(m);
                
            }
        }
    }

    public static Palikka getPalikka() {
        return palikka;
    }
    public static Kentta getKentta() {
        return kentta;
    }
}
