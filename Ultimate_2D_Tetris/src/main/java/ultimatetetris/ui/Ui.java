package ultimatetetris.ui;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import ultimatetetris.Kentta;
import ultimatetetris.Logiikka;

public class Ui extends Application {

    Scene scene;
    Pane pane;
    Pane tetrisPane;
    Logiikka l;
    Kentta k;
    
    public static void main(String[] args) {
        launch(Ui.class);
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Ultimate 2D Tetris");
        
        Pane aPane = new Pane();
        aPane.setPrefSize(600, 600);
        aPane.setStyle("-fx-background-color: black;");
        
        Button aloitaNappi = new Button("Start Game");
        
        aloitaNappi.setOnAction((ActionEvent e) -> {
            stage.setScene(peliNakyma(stage)); 
        });
        
        aPane.getChildren().add(aloitaNappi);
        
        scene = new Scene(aPane);

        stage.setScene(scene);
        
        stage.show();
        
    }
    
    private Scene peliNakyma(Stage stage) {
        BorderPane ruutu = new BorderPane();

        Image rVasen = new Image("reunukset_vasen.png");
        Image rOikea = new Image("reunukset_oikea.png");
        Image kentta = new Image("vari_null.png");
        
        pane = new Pane();
        pane.setPrefSize(300, 600);
        pane.setStyle("-fx-background-color: black;");
        ImageView kenttaTausta = new ImageView(kentta);
        pane.getChildren().add(kenttaTausta);
        

        Pane vasen = new Pane();
        vasen.setPrefSize(150, 600);
        vasen.setStyle("-fx-background-color: black;");
        ImageView reunusVasen = new ImageView(rVasen);
        vasen.getChildren().add(reunusVasen);

        Pane oikea = new Pane();
        oikea.setPrefSize(150, 600);
        oikea.setStyle("-fx-background-color: black;");
        ImageView reunusOikea = new ImageView(rOikea);
        oikea.getChildren().add(reunusOikea);

        ruutu.setCenter(pane);
        ruutu.setLeft(vasen);
        ruutu.setRight(oikea);

        scene = new Scene(ruutu);

        l = new Logiikka(scene, pane, vasen, oikea);

        return (scene);
    }
    
}
