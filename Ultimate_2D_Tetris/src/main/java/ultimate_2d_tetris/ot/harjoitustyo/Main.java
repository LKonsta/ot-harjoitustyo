package ultimate_2d_tetris.ot.harjoitustyo;

import java.util.Arrays;
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

    public Scene getScene() {
        return scene;
    }
   
    public class Kuutio extends Rectangle {
        
        int x;
        int y;
        
        Kuutio(int x, int y, int w, int h, Color vari) {
            super(w, h, vari);
            
            this.x = x;
            this.y = y;

            setTranslateX(x);
            setTranslateY(y);
        }

        void liikuAlas() {
            if (getTranslateY() + 40 < 800 && !(TormaaAlas())) {
                setTranslateY(getTranslateY() + 40);
                y+=40;
            } else {
                paneeli.getChildren().remove(Kuutio.this);
                kentta[(int) getTranslateY() / 40][(int) getTranslateX() / 40] = 1;
//                kentta[(int) getTranslateY() / 40 -1][(int) getTranslateX() / 40] = 1;
                uusiKuuti();
            }
        }

        void liikuVasen() {
            if (getTranslateX()-40 >= 0) {
                if (!(TormaaVasen())) {
                    setTranslateX(getTranslateX() - 40);
                    x-=40;
                }
            }
        }

        void liikuOikea() {
            if (getTranslateX()+40 < 400 ) {
                if (!(TormaaOikea())) {
                    setTranslateX(getTranslateX() + 40);
                    x+=40;
                }
            }
        }
        
//        private void liikuPohja() {
//            for (int m = (int) kuutio.getTranslateY()/40;m>0;m--) {
//                if (kentta[m][(int) kuutio.getTranslateX()/40] != 0) {
//                    setTranslateY(m*40);
//                    y=m*40;
//                }
//            }
//        }

        private boolean TormaaAlas() {
            return kentta[y / 40 + 1][x / 40] != 0;
        }
        
        private boolean TormaaVasen() {
            if (x == 0) {
                return true;
            }
            
            return kentta[y/40][x/40 -1] != 0;
        }
        
        private boolean TormaaOikea() {
            
            if (x == 360) {
                return true;
            }
            return kentta[y / 40][x / 40 + 1] != 0;
        }

        

    }
    
    private void uusiKuuti() {
        kuutio = new Kuutio(200, 0, 40, 40, Color.BLACK);
        paneeli.getChildren().add(kuutio);
    }
    
    Scene scene;
    
    @Override
    public void start(Stage naytto) throws Exception {
        naytto.setTitle("Ultimate 2D Tetris");
       scene = new Scene(Piirto());
        
        scene.setOnKeyPressed(e -> {
            switch (e.getCode()) {
                case LEFT:
                    kuutio.liikuVasen();
                    break;
                case RIGHT:
                    kuutio.liikuOikea();
                    break;
                case DOWN:
                    kuutio.liikuAlas();
                    break;
//                case SPACE:
//                    kuutio.liikuPohja();
//                    break;
            }
        });
        
        naytto.setScene(scene);
        naytto.show();
        
    }
    private int[][] kentta;
    public Kuutio kuutio;
    private int odotus = 1; 
    
    private Parent Piirto() {
        kentta = new int[20][10];
        kuutio = new Kuutio(200, 0, 40, 40, Color.BLACK);
        paneeli.setPrefSize(400, 800);
        paneeli.getChildren().add(kuutio);
        
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
    
    private void update() {
        odotus+= 30;
        if (odotus >= 1000) {
            kuutio.liikuAlas();
            odotus = 0;
        }
        boolean[] kerrokset = new boolean[20];
        for (int q = 0;q<20;q++) {
            boolean kerros = true;
            for (int w = 0;w<10;w++) {
                Rectangle r = new Rectangle(w*40,q*40,40,40);
                if (kentta[q][w] == 1) { 
                    paneeli.getChildren().add(r);
                    kentta[q][w] = 2;
                } else if (kentta[q][w] == 0) {
                    kerros = false;
                }
                
            }
            if (kerros) {
                kerrokset[q] = true;
            }
        }
        for (int m = 0;m<kerrokset.length;m++) {
            if (kerrokset[m]) {
                kerrosVaihto(m);
            }
        }
    }
    
    private void kerrosVaihto(int m) {
        paneeli.getChildren().clear();
        
        for (int alku = m;alku>1;alku--) {
            kentta[alku] = kentta[alku-1];
            
            for (int e = 0;e<kentta[alku].length;e++) {
                if (kentta[alku][e] == 2) {
                    kentta[alku][e] = 1;
                }
            }
        }
        uusiKuuti();
       
    }
    
    private Pane paneeli = new Pane();
    
    public static void main(String[] args) {
        launch(args);
        
    }
}
