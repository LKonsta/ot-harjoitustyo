
package ultimatetetris.ui;

import java.util.ArrayList;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import ultimatetetris.Logiikka;
import ultimatetetris.Palikka;

public class Ui extends Application {

//    private void jonoupdate() {
////        Palikka p = Logiikka.getJono();
////        for (int e = 0; e < p.getKuutiot().size(); e++) {
////            int x = p.getKuutiot().get(e).getKohtaX();
////            int y = p.getKuutiot().get(e).getKohtaY();
////            pane2.getChildren().add(new Rectangle((y * 20), (x * 20), 20, 20));
////        }
//    }

//    private void muistiupdate() {
//        Palikka p = Logiikka.getMuisti();
//        int[][] m = p.getMuoto();
//        for (int e = 0; e < m.length; e++) {
//            for (int t = 0; t < m[e].length; t++) {
//                if (m[e][t] == 1) {
//                    pane.getChildren().add(new Rectangle((e * 20), (t * 20), 20, 20));
//                }
//            }
//        }
//    }

    Scene scene;
    Pane pane;
    Pane pane2;
    Pane tetrisPane;
    Logiikka l;
    
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
                    l.getPalikka().liikuVasen();
                    break;
                case RIGHT:
                    l.getPalikka().liikuOikea();
                    break;
                case DOWN:
                    l.getPalikka().liikuAlas();
                    break;
                case X:
                    palikkaRemove(l.getPalikka());
                    l.getPalikka().pyorita(0);
                    palikkaAdd(l.getPalikka());
                    break;
                case Z:
                    palikkaRemove(l.getPalikka());
                    l.getPalikka().pyorita(1);
                    palikkaAdd(l.getPalikka());
                    break;
                case C:
                    l.swapMuisti();
                    break;
//                case SPACE:
//                    kuutio.liikuPohja();
//                    break;
            }
        });
        l = new Logiikka();
        stage.setScene(scene);
        stage.show();
    }
    
    private ArrayList<Rectangle>[] piirrettyKentta = new ArrayList[20];
    
    public Pane addTetrisPane() {
        for (int p = 0; p < piirrettyKentta.length; p++) {
            piirrettyKentta[p] = new ArrayList<>();
        }
        tetrisPane = new Pane();
        tetrisPane.setPrefSize(300, 600);
        tetrisPane.setStyle("-fx-background-color: black;");
        
        
        return tetrisPane;
    }
    
    public void piirettyAdd(int q, Rectangle r) {
        piirrettyKentta[q].add(r);
        tetrisPane.getChildren().add(r);
    }
    
    public void piirrettyRemoveRivi(int q) {
        tetrisPane.getChildren().removeAll(piirrettyKentta[q]);
        piirrettyKentta[q] = piirrettyKentta[q - 1];
    }
    
    public void palikkaRemove(Palikka palikka) {
        tetrisPane.getChildren().removeAll(palikka.getKuutiot());
    }
    public void palikkaAdd(Palikka palikka) {
        if (tetrisPane != null) {
            tetrisPane.getChildren().addAll(palikka.getKuutiot());
        }
    }
    
}
