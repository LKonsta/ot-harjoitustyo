
package ultimatetetris.ui;

import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import ultimatetetris.Kentta;
import ultimatetetris.Kuutio;
import ultimatetetris.Logiikka;
import ultimatetetris.Main;
import ultimatetetris.Palikka;

public class Ui extends Application {

    Scene scene;
    Pane pane;
    Pane pane2;
    static Pane tetrisPane;
    
    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Ultimate 2d Tetris");
        BorderPane rootPane = new BorderPane();
        pane = new Pane();
        pane2 = new Pane();
        Pane pane3 = new Pane();
        Pane pane4 = new Pane();
        pane.setPrefSize(150, 600);
        pane2.setPrefSize(150, 600);
        pane3.setPrefSize(600, 10);
        pane4.setPrefSize(600, 10);
        
        Text text1 = new Text("tänne \n tulee \n muistissa \n olvea \n palikka");
        Text text2 = new Text("Tänne \n tulee \n tieto \n seuraavasta \n palikasta \n ja \n pisteistä");
        pane.getChildren().add(text1);
        pane2.getChildren().add(text2);
        
        rootPane.setLeft(pane);
        rootPane.setRight(pane2);
        rootPane.setTop(pane3);
        rootPane.setBottom(pane4);
        rootPane.setCenter(addTetrisPane());
        
        scene = new Scene(rootPane);
        
        scene.setOnKeyPressed(e -> {
            switch (e.getCode()) {
                case LEFT:
                    Logiikka.getPalikka().liikuVasen();
                    break;
                case RIGHT:
                    Logiikka.getPalikka().liikuOikea();
                    break;
                case DOWN:
                    Logiikka.getPalikka().liikuAlas();
                    break;
                case X:
                    tetrisRemove(Logiikka.getPalikka());
                    Logiikka.getPalikka().pyorita(0);
                    tetrisAdd(Logiikka.getPalikka());
                    break;
                case Z:
                    tetrisRemove(Logiikka.getPalikka());
                    Logiikka.getPalikka().pyorita(1);
                    tetrisAdd(Logiikka.getPalikka());
                    break;
//                case SPACE:
//                    kuutio.liikuPohja();
//                    break;
            }
        });

        stage.setScene(scene);
        stage.show();
    }
    
    private static ArrayList<Rectangle>[] piirrettyKentta = new ArrayList[20];
    public static Pane addTetrisPane() {
        Random r = new Random();
        Color[] varit = {Color.YELLOW, Color.TEAL, Color.GREEN, Color.RED, Color.ORANGE, Color.BLUE, Color.PURPLE};
        
        tetrisPane = new Pane();
        Logiikka.uusiKentta();
        Logiikka.uusiPalikka();
        tetrisPane.setPrefSize(300, 600);
        tetrisPane.setStyle("-fx-background-color: black;");
        
        for (int p = 0; p < piirrettyKentta.length; p++) {
            piirrettyKentta[p] = new ArrayList<>();
        }
        AnimationTimer ajastin = new AnimationTimer() {
            @Override
            public void handle(long now) {
                Logiikka.update();
                try {
                    Thread.sleep(10);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };
        ajastin.start();
        return tetrisPane;
    }
    
    public static void piirettyAdd(int q, Rectangle r) {
        piirrettyKentta[q].add(r);
        tetrisPane.getChildren().add(r);
    }
    
    public static void piirrettyRemoveRivi(int q) {
        tetrisPane.getChildren().removeAll(piirrettyKentta[q]);
        piirrettyKentta[q] = piirrettyKentta[q - 1];
    }
    
    public static void tetrisRemove(Palikka palikka) {
        tetrisPane.getChildren().removeAll(palikka.getKuutiot());
    }
    public static void tetrisAdd(Palikka palikka) {
        tetrisPane.getChildren().addAll(palikka.getKuutiot());
    }
    
}
