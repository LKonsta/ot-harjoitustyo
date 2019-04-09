package ultimate_2d_tetris.ot.harjoitustyo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Main extends Application {
    Random r = new Random();
    Color[] varit = {Color.YELLOW, Color.TEAL, Color.GREEN, Color.RED, Color.ORANGE, Color.BLUE, Color.PURPLE};
    
    public Scene getScene() {
        return scene;
    }
   
    private void uusiPalikka() {
        palikka = new Palikka(3, 0, varit, kentta, r.nextInt(7));
        paneeli.getChildren().addAll(palikka.getKuutiot());
    }
    
    Scene scene;
    
    @Override
    public void start(Stage naytto) throws Exception {
        naytto.setTitle("Ultimate 2D Tetris");
        scene = new Scene(Piirto());
        
        scene.setOnKeyPressed(e -> {
            switch (e.getCode()) {
                case LEFT:
                    palikka.liikuVasen();
                    break;
                case RIGHT:
                    palikka.liikuOikea();
                    break;
                case DOWN:
                    palikka.liikuAlas();
                    break;
                case X:
                    paneeli.getChildren().removeAll(palikka.getKuutiot());
                    palikka.pyoritaOikea();
                    paneeli.getChildren().addAll(palikka.getKuutiot());
                    break;
                case Z:
                    paneeli.getChildren().removeAll(palikka.getKuutiot());
                    palikka.pyoritaVasen();
                    paneeli.getChildren().addAll(palikka.getKuutiot());
                    break;
//                case SPACE:
//                    kuutio.liikuPohja();
//                    break;
            }
        });
        
        naytto.setScene(scene);
        naytto.show();
        
    }
    
    private Kentta kentta;
    private Palikka palikka;
    private int odotus = 0; 
    private ArrayList<Rectangle>[] piirrettyKentta  = new ArrayList[20];
    
    private Parent Piirto() {
        kentta = new Kentta(20, 10);
        
        palikka = new Palikka(3, 0, varit, kentta, r.nextInt(7));
        paneeli.setPrefSize(400, 800);
        paneeli.setStyle("-fx-background-color: black;");
        paneeli.getChildren().addAll(palikka.getKuutiot());
        for (int p = 0;p<piirrettyKentta.length;p++) {
            piirrettyKentta[p] = new ArrayList<>();
        }
        
        AnimationTimer ajastin = new AnimationTimer() {
            @Override
            public void handle(long now) {
                update();
                try {
                    Thread.sleep(10);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };
        ajastin.start();
        return paneeli;
    }
    
    private boolean palikkaalhaalla = true;
    private void update() {
        odotus+= 30;
        
        if (palikka.tormaaAlas()) {
            if (!(palikka.getLiikutettu())) {
                palikka.liikkumattomuus();
                paneeli.getChildren().removeAll(palikka.getKuutiot());
                uusiPalikka();
            }
        }
        if (palikka.liikutettualas()) {
            palikka.liikkumattomuus();
            paneeli.getChildren().removeAll(palikka.getKuutiot());
            uusiPalikka();
        }
        if (odotus%900 == 0) {
            if (!palikka.tormaaAlas()) {
                palikka.liikuAlas();
            }
            if (palikka.tormaaAlas() && palikkaalhaalla) {
                odotus = 30;
                palikkaalhaalla = false;
            }
            if (odotus%900 == 0) {
                if (palikka.tormaaAlas()) {

                    palikka.liikutettuSetFalse();
                    palikkaalhaalla = true;
                }
                odotus = 0;
            }
        }
        
        boolean[] kerrokset = new boolean[20];
        for (int q = 0; q < 20; q++) {
            boolean kerros = true;
            for (int w = 0; w < 10; w++) {
                if (kentta.getKohta(q, w) == 1) {
                    Rectangle r = new Rectangle(40, 40, varit[kentta.getVari(q, w)]);
                    r.setX(w*40);
                    r.setY(q*40);
                    piirrettyKentta[q].add(r);
                    paneeli.getChildren().add(r);
                    kentta.setKohta(q, w, 2);
                } else if (kentta.getKohta(q, w) == 0) {
                    kerros = false;
                }
            }
            if (kerros) {
                kerrokset[q] = true;
            }
        }
        for (int m = 0; m < kerrokset.length; m++) {
            if (kerrokset[m]) {
                for (int s = m;s>1;s--) {
                    paneeli.getChildren().removeAll(piirrettyKentta[s]);
                }
                kentta.kerrosVaihto(m);
            }
        }
    }
    
    private Pane paneeli = new Pane();
    
    public static void main(String[] args) {
       
        launch(args);
    }
}
