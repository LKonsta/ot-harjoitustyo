package ultimatetetris.ui;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import ultimatetetris.logiikka.Kentta;
import ultimatetetris.logiikka.Logiikka;
import ultimatetetris.logiikka.Main;
import ultimatetetris.dao.HighscoreDAO;

public class Ui extends Application {

    Stage stage;
    Scene scene;
    Pane pane;
    Pane tetrisPane;
    Pane aPane;
    Logiikka l;
    Kentta k;
    Logiikka log;
    AnimationTimer ajastin;
    BorderPane ruutu;
    
    HighscoreDAO highscoreDAO;
    
    public static void main(String[] args) {
        launch(Ui.class);
    }

    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        stage.setTitle("Ultimate 2D Tetris");
        
        
        highscoreDAO = new HighscoreDAO("scores");
        aPane = getAPane();
        
        scene = new Scene(aPane);
        scene.getStylesheets().add("stylesheet.css");
        stage.setScene(scene);
        
        stage.show();
    }
    
    private void peliNakyma(Stage stage) {
        ruutu = new BorderPane();

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
        scene.getStylesheets().add("stylesheet.css");
        
        log = new Logiikka(scene, pane, vasen, oikea);
        
        ajastin = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (log.peliKaynnissa) {
                    log.update();
                } else if (log.peliJatkuu) {
                    log.startCountdown();
                } else if (log.peliPaattyi) {
                    log.peliPaattyi();
                } else if (log.lopetaPeli) {
                    ajastin.stop();
                    ruutu.setCenter(getScorePane());
                }
                try {
                    Thread.sleep(10);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };
        
        ajastin.start();
        stage.setScene(scene);
    }
    
    public Pane getAPane() {
        Pane a = new Pane();
        a.setPrefSize(600, 600);
        a.setStyle("-fx-background-color: black;");
        
        Image aloitaimage = new Image("Start_game_button.png");
        Button aloitaNappi = new Button();
        aloitaNappi.setGraphic(new ImageView(aloitaimage));
        aloitaNappi.setLayoutX(200);
        aloitaNappi.setLayoutY(130);
        
        Image scoreimage = new Image("Highscore_button.png");
        Button highscorenappi = new Button();
        highscorenappi.setGraphic(new ImageView(scoreimage));
        highscorenappi.setLayoutX(200);
        highscorenappi.setLayoutY(250);
        
        aloitaNappi.setOnAction((ActionEvent e) -> {
            peliNakyma(stage); 
        });
        
        highscorenappi.setOnAction((ActionEvent e) -> {
            highscoreNakyma(stage);
        });
        
        
        a.getChildren().addAll(aloitaNappi, highscorenappi);
        return a;
    }
    
    private Text error;
    
    private Pane getScorePane() {
        int newscore = log.getScore();
        Label lScore = new Label(Integer.toString(newscore));
        
        Image tausta = new Image("highscore_tausta.png");
        ImageView taustaH = new ImageView(tausta);
        lScore.setLayoutX(80);
        lScore.setLayoutY(90);
        lScore.setFont(Font.font("uroob", 37));
        lScore.setTextFill(Color.WHITE);
        
        TextField tName = new TextField();
        tName.setLayoutX(72);
        tName.setLayoutY(165);
        tName.setPrefHeight(28);
        tName.setPrefWidth(156);
        
        Pane p = new Pane();
        
        p.setPrefSize(600, 600);
        p.setStyle("-fx-background-color: black;");
        p.getChildren().add(taustaH);
        p.getChildren().addAll(lScore, tName);

        Image x = new Image("X_button.png");
        Image save = new Image("Save_button.png");
        
        ImageView xKuva = new ImageView(x);
        ImageView saveKuva = new ImageView(save);
        
        Button tallennanappi = new Button();
        tallennanappi.setGraphic(saveKuva);
        tallennanappi.setLayoutX(115);
        tallennanappi.setLayoutY(235);
        
        Button takaisinnappi = new Button();
        takaisinnappi.setGraphic(xKuva);
        takaisinnappi.setLayoutX(55);
        takaisinnappi.setLayoutY(235);
        
        tallennanappi.setOnAction((ActionEvent e) -> {
            String inputname = tName.getText();
            if (inputname.matches("[A-Za-z]+") && inputname.length() <= 10) {
                highscoreDAO.newHighscore(inputname, newscore);
                scene = new Scene(getAPane());
                scene.getStylesheets().add("stylesheet.css");
                stage.setScene(scene);
            } else {
                if (error != null) {
                    p.getChildren().remove(error);
                }
                if (inputname.length() > 10) {
                    error = new Text("Name must be less than 10 letters");
                } else {
                    System.out.println(inputname.length());
                    error = new Text("Name has to only contain letters");
                }
                error.setFont(Font.font("uroob", 15));
                error.setFill(Color.WHITE);
                error.setLayoutX(65);
                error.setLayoutY(225);
                p.getChildren().add(error);
            }
            
        });
        
        takaisinnappi.setOnAction((ActionEvent e) -> {
            scene = new Scene(getAPane());
            scene.getStylesheets().add("stylesheet.css");
            stage.setScene(scene);   
        });
        
        p.getChildren().addAll(takaisinnappi, tallennanappi);
        return p;
    }

    private void highscoreNakyma(Stage stage) {
        Pane a = new Pane();
        Image tausta = new Image("highscore_listaus_tausta.png");
        ImageView taustaKuva = new ImageView(tausta);
        
        a.setPrefSize(600, 600);
        a.setStyle("-fx-background-color: black;");
        a.getChildren().add(taustaKuva);
        
        List<String> scoresdata = highscoreDAO.getHighscores();
        List<String> addable = new ArrayList<>();
        for (int i = 0; i < scoresdata.size(); i++) {
            String[] set = scoresdata.get(i).split(":");
            String newset = (set[1] + " | " + set[0]);    
            addable.add(newset);
        }
        
        ListView<String> list = new ListView<>();
        ObservableList<String> scores = FXCollections.observableArrayList(addable);
        list.setItems(scores);
        
        list.setPrefWidth(320);
        list.setPrefHeight(395);
        list.setLayoutX(139);
        list.setLayoutY(52);
        
        a.getChildren().add(list);
        
        Image back = new Image("Back_button.png");
        ImageView backKuva = new ImageView(back);
        
        Button takasnappi = new Button();
        takasnappi.setGraphic(backKuva);
        takasnappi.setLayoutX(240);
        takasnappi.setLayoutY(500);
        
        takasnappi.setOnAction((ActionEvent e) -> {
            scene = new Scene(getAPane());
            scene.getStylesheets().add("stylesheet.css");
            stage.setScene(scene);
        });
        
        a.getChildren().add(takasnappi);
        
        scene = new Scene(a);
        scene.getStylesheets().add("stylesheet.css");
        stage.setScene(scene);
    }
}
