package ultimatetetris;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public final class Logiikka {
    
    Pane tetris;
    Pane vasen;
    Pane oikea;
    private final Kentta kentta;
    private Palikka palikka;
    
    Random r = new Random();
    Color[] varit = {
        Color.CYAN, 
        Color.BLUE, 
        Color.ORANGE, 
        Color.GOLD,
        Color.LAWNGREEN, 
        Color.PURPLE, 
        Color.RED
    };
    
    Image[] kuvat = {
        new Image("" + 1 + "_teema/vari_cyan.png"),
        new Image("" + 1 + "_teema/vari_blue.png"),
        new Image("" + 1 + "_teema/vari_orange.png"),
        new Image("" + 1 + "_teema/vari_yellow.png"),
        new Image("" + 1 + "_teema/vari_green.png"),
        new Image("" + 1 + "_teema/vari_purple.png"),
        new Image("" + 1 + "_teema/vari_red.png"),
        new Image("" + 1 + "_teema/vari_grey.png")
    };
    
    Image[] numerot = {
        new Image("n_0.png"),
        new Image("n_1.png"),
        new Image("n_2.png"),
        new Image("n_3.png"),
        new Image("n_4.png"),
        new Image("n_5.png"),
        new Image("n_6.png"),
        new Image("n_7.png"),
        new Image("n_8.png"),
        new Image("n_9.png")
    };
    
    public int odotus = 0;
    public int kerroin = 10;
    public boolean palikkaVaihdettu = false;
    public boolean palikkaLiikutettu = false;
    public int palikkaLiikutettuKerrat = 0;
    int linjat;
    int taso;
    int pisteet;
    
    ArrayDeque<Integer> muisti = new ArrayDeque<>();
    ArrayDeque<Integer> palikkaJono = new ArrayDeque<>();
    ArrayList<ImageView> kuutioMuisti;
    ArrayList<ImageView> kuutioJono;
    ArrayList<ImageView> pisteetList;
    ArrayList<ImageView> levelList;
    ArrayList<ImageView> linjaList;
    
    boolean peliKaynnissa;
    boolean peliJatkuu;
    boolean peliPaattyi;
    ImageView countKuva;
    int count = 5;
    boolean uusiCount;
    int ppCounter = 0;
    int ppKerros = 19;
    
    public Logiikka(Scene scene, Pane tetris, Pane vasen, Pane oikea) {
        this.tetris = tetris;
        this.vasen = vasen;
        this.oikea = oikea;
        kentta = new Kentta(20, 10);
        
        updateScore();
        updateLinjat();
        updateLevel();
        updateKentta();
        
        uusiPalikka();
        peliJatkuu = true;
        
        scene.setOnKeyPressed((KeyEvent e) -> {
            if (peliKaynnissa) {
                switch (e.getCode()) {
                    case LEFT:
                        palikka.liikuVasen();
                        palikkaLiikutettu = true;
                        break;
                    case RIGHT:
                        palikka.liikuOikea();
                        palikkaLiikutettu = true;
                        break;
                    case DOWN:
                        palikka.liikuAlas();
                        tormaysTesti();
                        break;
                    case X:
                        tetris.getChildren().removeAll(palikka.getKuutiot());
                        palikka.pyorita(0);
                        tetris.getChildren().addAll(palikka.getKuutiot());
                        palikkaLiikutettu = true;
                        break;
                    case Z:
                        tetris.getChildren().removeAll(palikka.getKuutiot());
                        palikka.pyorita(1);
                        tetris.getChildren().addAll(palikka.getKuutiot());
                        palikkaLiikutettu = true;
                        break;
                    case C:
                        if (!palikkaVaihdettu) {
                            swapMuisti();
                        }
                        break;
                    case SPACE:
                        palikka.liikuPohja();
                        tormaysTesti();
                        break;
                    case P:
                        peliKaynnissa = false;
                        break;
                }
                
            } else {
                switch (e.getCode()) {
                    case P:
                        peliJatkuu = true;
                        break;
                }
            }
        });
        
        AnimationTimer ajastin = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (peliKaynnissa) {
                    update();
                } else if (peliJatkuu) {
                    startCountdown();
                } else if (peliPaattyi) {
                    peliPaattyi();
                }
                try {
                    Thread.sleep(10);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Peli.class.getName()).log(Level.SEVERE, null, ex);
                }
            } 
        };
        ajastin.start();
    }
    
    public void update() {
        odotus += kerroin;
        if (odotus > 1000) {
            if (!palikkaLiikutettu || palikkaLiikutettuKerrat > 5) {
                tormaysTesti();
            }
            palikka.liikuAlas();
            odotus = 0;
            if (palikka.tormaaAlas() && palikkaLiikutettu) {
                palikkaLiikutettuKerrat++;
                palikkaLiikutettu = false;
            }
        }
    }
    
    public void startCountdown() {
        if (uusiCount) {
            count = 5;
        }
        uusiCount = false;
        odotus += 30;
        if (countKuva == null) {
            countKuva = new ImageView(numerot[count]);
            countKuva.setX(120);
            countKuva.setY(250);
            countKuva.setFitWidth(60);
            countKuva.setFitHeight(84);
            tetris.getChildren().add(countKuva);
        }
        if (odotus > 1000) {
            updateCount();
        }
    }
    
    public void updateCount() {
        if (count == 0) {
            peliKaynnissa = true;
            tetris.getChildren().remove(countKuva);
            peliJatkuu = false;
            uusiCount = true;
            return;
        }
        count--;
        odotus = 0;
        tetris.getChildren().remove(countKuva);
        countKuva = new ImageView(numerot[count]);
        countKuva.setX(120);
        countKuva.setY(250);
        countKuva.setFitWidth(60);
        countKuva.setFitHeight(84);
        tetris.getChildren().add(countKuva);
    }
    
    public void tormaysTesti() {
        if (palikka.tormaaAlas()) {
            palikka.liikkumattomuus();
            tetris.getChildren().removeAll(palikka.getKuutiot());
            uusiPalikka();
            onkoTaysiRivi();
        }
    }
    
    public void uusiPalikka() {
        palikkaVaihdettu = false;
        Integer p = r.nextInt(7);
        while (palikkaJono.contains(p) || palikkaJono.size() < 3) {
            if (!palikkaJono.contains(p)) {
                palikkaJono.addLast(p);
            }
            p = r.nextInt(7);
        }
        palikkaJono.addLast(p);
        int palikanmuoto = palikkaJono.poll();
        palikka = new Palikka(kuvat, kentta, palikanmuoto);
        updateKentta();
        tetris.getChildren().addAll(palikka.getKuutiot());
        updateOikea();
        onkoTilaa();
    }
    
    private void onkoTilaa() {
        ArrayList<Kuutio> kuut = palikka.getKuutiot();
        for (int i = 0; i < kuut.size(); i++) {
            int kuutY = kuut.get(i).getKohtaY();
            int kuutX = kuut.get(i).getKohtaX();
            if (kentta.getKohta(kuutY, kuutX) != 0) {
                peliKaynnissa = false;
                peliPaattyi = true;
            }
        }
    }
    
    private void peliPaattyi() {
        if (kentta.getVari(0, 0) != 7) {
            if (ppCounter > 1000) {
                kentta.removePiirrettRivi(ppKerros, tetris);
                for (int w = 0; w < 10; w++) {
                    ImageView ruutu = new ImageView(kuvat[7]);
                    kentta.setVari(ppKerros, w, 7);
                    ruutu.setX(w * 30);
                    ruutu.setY(ppKerros * 30);
                    tetris.getChildren().add(ruutu);
                }
                ppKerros--;
                ppCounter = 0;
            }
            ppCounter += 100;
        } else {
            peliPaattyi = false;
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
        int tamankerran = 0;
        for (int m = 0; m < kerrokset.length; m++) {
            if (kerrokset[m]) {
                kentta.kerrosVaihto(m, tetris);
                tamankerran++;
            }
        }
        if (tamankerran > 0) {
            linjojenPisteet(tamankerran);
        }
        updateKentta();
    }
    
    public void updateKentta() {
        for (int q = 0; q < 20; q++) {
            for (int w = 0; w < 10; w++) {
                if (kentta.getKohta(q, w) == 1) {
                    ImageView ruutu = new ImageView(kuvat[kentta.getVari(q, w)]);
                    ruutu.setX(w * 30);
                    ruutu.setY(q * 30);
                    kentta.addPiirrettyKentta(q, ruutu);
                    tetris.getChildren().add(ruutu);
                    kentta.setKohta(q, w, 2);   
                }
            }
        }
    }
    
    public void linjojenPisteet(int n) {
        linjat += n;
        if (linjat >= (taso + 1) * 10) {
            taso++;
            kerroin += 10;
        }
        switch (n) {
            case 1:
                pisteet += (40 * (taso + 1));
                break;
            case 2:
                pisteet += (100 * (taso + 1));
                break;
            case 3:
                pisteet += (300 * (taso + 1));
                break;
            case 4:
                pisteet += (1200 * (taso + 1));
                break;
        }
        updateScore();
        updateLinjat();
        updateLevel();
    }
    
    public void swapMuisti() {
        palikkaVaihdettu = true;
        tetris.getChildren().removeAll(palikka.getKuutiot());
        int v = palikka.getMuotoN();
        int[][] m = palikka.getMuoto();
        if (muisti.isEmpty()) {
            muisti.add(palikka.getMuotoN());
            uusiPalikka();
            updateVasen(m, v);
            palikkaVaihdettu = true;
            return;
        } else {
            muisti.addLast(v);
            updateVasen(m, v);
            palikka = new Palikka(kuvat, kentta, muisti.poll());
        }
        tetris.getChildren().addAll(palikka.getKuutiot());
    }
    
    private void updateScore() {
        if (pisteetList != null) {
            oikea.getChildren().removeAll(pisteetList);
        }
        pisteetList = new ArrayList<>();
        String[] jaettu = Integer.toString(pisteet).split("");
        pisteetList.addAll(updateNumeroKuvat(7, jaettu, 32, 167));
        oikea.getChildren().addAll(pisteetList);
    }
    
    private void updateLevel() {
        if (levelList != null) {
            oikea.getChildren().removeAll(levelList);
        }
        levelList = new ArrayList<>();
        String[] jaettu = Integer.toString(taso).split("");
        levelList.addAll(updateNumeroKuvat(2, jaettu, 32, 230));
        oikea.getChildren().addAll(levelList);
    }
    
    private void updateLinjat() {
        if (linjaList != null) {
            oikea.getChildren().removeAll(linjaList);
        }
        linjaList = new ArrayList<>();
        String[] jaettu = Integer.toString(linjat).split("");
        linjaList.addAll(updateNumeroKuvat(3, jaettu, 32, 294));
        oikea.getChildren().addAll(linjaList);
    }
    
    private ArrayList<ImageView> updateNumeroKuvat(int n, String[] j, int h, int w) {
        ArrayList<ImageView> vali = new ArrayList<>();
        int ve = 0;
        for (int i = 0; i < n; i++) {
            if (((n - 1) - i) - j.length >= 0) {
                ImageView num = new ImageView(numerot[0]);
                num.setX(h + (i * 12));
                num.setY(w);
                vali.add(num);
            } else {
                ImageView num = new ImageView();
                num.setImage(numerot[Integer.valueOf(j[ve])]);
                num.setX(h + (i * 12));
                num.setY(w);
                vali.add(num);
                ve++;
            }
        }
        return vali;
    }
    
    private void updateOikea() {
        int v = palikkaJono.peek();
        int[][] m = palikka.tiettyMuoto(v);
        if (kuutioJono != null) {
            oikea.getChildren().removeAll(kuutioJono);
        }
        kuutioJono = new ArrayList<>();
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[i].length; j++) {
                if (m[i][j] == 1) {
                    int kohtay = (27 + (40 - (m.length * 10)) + (i * 25));
                    int kohtax = (23 + (40 - (m.length * 10)) + (j * 25));
                    ImageView k = new ImageView(kuvat[v]);
                    k.setX(kohtax);
                    k.setY(kohtay);
                    k.setFitHeight(25);
                    k.setFitWidth(25);
                    kuutioJono.add(k);
                }
            }
        }
        oikea.getChildren().addAll(kuutioJono);
    }
    
    private void updateVasen(int[][] m, int v) {
        if (kuutioMuisti != null) {
            vasen.getChildren().removeAll(kuutioMuisti);
        }
        kuutioMuisti = new ArrayList<>();
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[i].length; j++) {
                if (m[i][j] == 1) {
                    int kohtay = (30 + (40 - (m.length * 10)) + (i * 25));
                    int kohtax = (23 + (40 - (m.length * 10)) + (j * 25));
                    ImageView k = new ImageView(kuvat[v]);
                    k.setX(kohtax);
                    k.setY(kohtay);
                    k.setFitHeight(25);
                    k.setFitWidth(25);
                    kuutioMuisti.add(k);
                }
            }
        }
        vasen.getChildren().addAll(kuutioMuisti);
    }
    
    public Palikka getPalikka() {
        return palikka;
    }

    public Kentta getKentta() {
        return kentta;
    }


}
