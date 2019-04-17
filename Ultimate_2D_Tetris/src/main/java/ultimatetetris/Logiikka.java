package ultimatetetris;


import java.util.ArrayDeque;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.AnimationTimer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import ultimatetetris.ui.Ui;

public class Logiikka {
    Ui ui;
    public Logiikka() {
        ui = new Ui();
        kentta = new Kentta(20, 10);
        palikka = new Palikka(3, 0, varit, kentta, r.nextInt(7));
        ui.palikkaAdd(palikka);
//        uusiPalikka();
//        uusiKentta();
        AnimationTimer ajastin = new AnimationTimer() {
            @Override
            public void handle(long now) {
                update();
//                muistiupdate();
                try {
                    Thread.sleep(10);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };
        ajastin.start();
    }
    
    
    Random r = new Random();
    Color[] varit = {Color.YELLOW, Color.TEAL, Color.LIGHTGREEN, Color.RED, Color.ORANGE, Color.BLUE, Color.PURPLE};
    ArrayDeque<Palikka> palikkaJono = new ArrayDeque<>();
    
    public Palikka getJono() {
        return palikkaJono.peek();
    }
    public Palikka getMuisti() {
        return muisti.peek();
    }
    
    public void uusiPalikka() {
        Palikka p = new Palikka(3, 0, varit, kentta, r.nextInt(7));
        while (palikkaJono.contains(p) || palikkaJono.size() < 3) {
            if (!palikkaJono.contains(p)) {
                palikkaJono.add(p);
                System.out.println(p);
            }
            p = new Palikka(3, 0, varit, kentta, r.nextInt(7));
        }
        System.out.println(p);
        palikkaJono.add(p);
        palikka = palikkaJono.poll(); 
        ui.palikkaAdd(palikka);
    }
    
    public void uusiKentta() {
        kentta = new Kentta(20, 10);
    }
    
    private Kentta kentta;
    private Palikka palikka;
    public int odotus = 0; 
    
    private boolean palikkaalhaalla = true;
    
    public void update() {
        
        odotus += 30;
        
//        if (palikka.tormaaAlas()) {
//            if (!(palikka.getLiikutettu())) {
//                palikka.liikkumattomuus();
//                ui.palikkaRemove(palikka);
//                uusiPalikka();
//                onkoTaysiRivi();
//            }
//        }
//        if (palikka.liikutettualas()) {
//            palikka.liikkumattomuus();
//            ui.palikkaRemove(palikka);
//            uusiPalikka();
//            onkoTaysiRivi();
//        }
//        if (odotus % 900 == 0) {
//            if (!palikka.tormaaAlas()) {
//                palikka.liikuAlas();
//            }
//            if (palikka.tormaaAlas() && palikkaalhaalla) {
//                odotus = 30;
//                palikkaalhaalla = false;
//            }
//            if (odotus % 900 == 0) {
//                if (palikka.tormaaAlas()) {
//                    palikka.liikutettuSetFalse();
//                    palikkaalhaalla = true;
//                }
//                odotus = 0;
//            }
//        }
        for (int q = 0; q < 20; q++) {
            for (int w = 0; w < 10; w++) {
                if (kentta.getKohta(q, w) == 1) {
                    Rectangle r = new Rectangle(30, 30, varit[kentta.getVari(q, w)]);
                    r.setX(w * 30);
                    r.setY(q * 30);
                    ui.piirettyAdd(q, r);
                    kentta.setKohta(q, w, 2);
                }
            }
        }
    }
    
    public void onkoTaysiRivi() {
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
                    ui.piirrettyRemoveRivi(p);
                }
                kentta.kerrosVaihto(m);
                
            }
        }
    }

    public Palikka getPalikka() {
        return palikka;
    }
    public Kentta getKentta() {
        return kentta;
    }

    ArrayDeque<Palikka> muisti = new ArrayDeque<>();
    public void swapMuisti() {
        ui.palikkaRemove(palikka);
        palikka.resetPalikka();
        if (muisti == null) {
            muisti.add(palikka);
            uusiPalikka();
        } else {
            muisti.add(palikka);
            palikka = muisti.poll();
        }
        ui.palikkaAdd(palikka);
    }

}
