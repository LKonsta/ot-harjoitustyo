/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;
import javafx.application.Application;
import static javafx.application.ConditionalFeature.FXML;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.stage.WindowEvent;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;
import ultimatetetris.logiikka.Kentta;
import ultimatetetris.logiikka.Kuutio;
import ultimatetetris.logiikka.Logiikka;
import ultimatetetris.logiikka.Main;
import ultimatetetris.logiikka.Palikka;
import ultimatetetris.ui.Ui;

/**
 *
 * @author khlehto
 */
public class MainTest extends Application {
    
    static Kentta kentta;

    private static Stage getPrimaryStage() {
        return stage;
    }


    boolean kaynnissa;
    static Stage stage;
    Logiikka log;
    
    
    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        
        BorderPane p0 = new BorderPane();
        Pane p1 = new Pane();
        p1.setPrefSize(300, 600);

        p0.setCenter(p1);

        Scene s = new Scene(p0);
        log = new Logiikka(s, p1, p1, p1);
        stage.setScene(s);
        stage.show();
    }
    
    @BeforeClass
    public static void setUp() {
        kentta = new Kentta(20,10);
        launch(MainTest.class);
    }
    
    @Test
    public void Palikkatesti1() {

        
        Palikka p = new Palikka(kentta, 1);
        for (int l = 0;l<20;l++) {
            p.liikuAlas();
        }
        assertEquals(3, p.x);
        assertEquals(18, p.y);
    }
    @Test
    public void Palikkatesti2() {
        Palikka p = new Palikka(kentta, 0);
        for (int l = 0; l < 20; l++) {
            p.liikuAlas();
            p.liikuVasen();
        }
        assertEquals(0, p.x);
        assertEquals(18, p.y);
    }
    @Test
    public void Palikkatesti3() {
        Palikka p = new Palikka(kentta, 0);
        for (int l = 0; l < 20; l++) {
            p.liikuAlas();
            p.liikuOikea();
        }
        assertEquals(6, p.x);
        assertEquals(18, p.y);
    }
    
    @Test
    public void PalikkaLuomistesti1() {
        Palikka p1 = new Palikka(kentta, 0);
        ArrayList<Kuutio> k1 =  p1.getKuutiot();
        
        assertEquals(3, k1.get(0).getKohtaX());
        assertEquals(1, k1.get(0).getKohtaY());
        
        assertEquals(4, k1.get(1).getKohtaX());
        assertEquals(1, k1.get(1).getKohtaY());
        
        assertEquals(5, k1.get(2).getKohtaX());
        assertEquals(1, k1.get(2).getKohtaY());
        
        assertEquals(6, k1.get(3).getKohtaX());
        assertEquals(1, k1.get(3).getKohtaY());
        
        Palikka p2 = new Palikka(kentta, 1);
        ArrayList<Kuutio> k2 = p2.getKuutiot();

        assertEquals(3, k2.get(0).getKohtaX());
        assertEquals(0, k2.get(0).getKohtaY());

        assertEquals(3, k2.get(1).getKohtaX());
        assertEquals(1, k2.get(1).getKohtaY());

        assertEquals(4, k2.get(2).getKohtaX());
        assertEquals(1, k2.get(2).getKohtaY());

        assertEquals(5, k2.get(3).getKohtaX());
        assertEquals(1, k2.get(3).getKohtaY());
        
        Palikka p3 = new Palikka(kentta, 2);
        ArrayList<Kuutio> k3 = p3.getKuutiot();

        assertEquals(5, k3.get(0).getKohtaX());
        assertEquals(0, k3.get(0).getKohtaY());

        assertEquals(3, k3.get(1).getKohtaX());
        assertEquals(1, k3.get(1).getKohtaY());

        assertEquals(4, k3.get(2).getKohtaX());
        assertEquals(1, k3.get(2).getKohtaY());

        assertEquals(5, k3.get(3).getKohtaX());
        assertEquals(1, k3.get(3).getKohtaY());
        
        Palikka p4 = new Palikka(kentta, 3);
        ArrayList<Kuutio> k4 = p4.getKuutiot();

        assertEquals(4, k4.get(0).getKohtaX());
        assertEquals(0, k4.get(0).getKohtaY());

        assertEquals(5, k4.get(1).getKohtaX());
        assertEquals(0, k4.get(1).getKohtaY());

        assertEquals(4, k4.get(2).getKohtaX());
        assertEquals(1, k4.get(2).getKohtaY());

        assertEquals(5, k4.get(3).getKohtaX());
        assertEquals(1, k4.get(3).getKohtaY());
        
        Palikka p5 = new Palikka(kentta, 4);
        ArrayList<Kuutio> k5 = p5.getKuutiot();

        assertEquals(4, k5.get(0).getKohtaX());
        assertEquals(0, k5.get(0).getKohtaY());

        assertEquals(5, k5.get(1).getKohtaX());
        assertEquals(0, k5.get(1).getKohtaY());

        assertEquals(3, k5.get(2).getKohtaX());
        assertEquals(1, k5.get(2).getKohtaY());

        assertEquals(4, k5.get(3).getKohtaX());
        assertEquals(1, k5.get(3).getKohtaY());
        
        Palikka p6 = new Palikka(kentta, 5);
        ArrayList<Kuutio> k6 = p6.getKuutiot();

        assertEquals(4, k6.get(0).getKohtaX());
        assertEquals(0, k6.get(0).getKohtaY());

        assertEquals(3, k6.get(1).getKohtaX());
        assertEquals(1, k6.get(1).getKohtaY());

        assertEquals(4, k6.get(2).getKohtaX());
        assertEquals(1, k6.get(2).getKohtaY());

        assertEquals(5, k6.get(3).getKohtaX());
        assertEquals(1, k6.get(3).getKohtaY());
    }
    
    @Test
    public void PalikkaPyoritystest1() {
        int[][][] muodot = {{{1,0,0},{1,1,1},{0,0,0}},
                            {{0,1,0},{0,1,0},{1,1,0}},
                            {{0,0,0},{1,1,1},{0,0,1}},
                            {{0,1,1},{0,1,0},{0,1,0}}
        };
        Palikka p = new Palikka(kentta, 1);
        for (int m = 0;m<muodot.length;m++) {
            assertArrayEquals(muodot[m], p.nykypalikka);
            p.pyorita(1);
        }
        for (int m = 3; m >= 0; m--) {
            p.pyorita(0);
            assertArrayEquals(muodot[m], p.nykypalikka);
        }
    }
    
    @Test
    public void rivinpuhdistusTest() {
        Pane p = new Pane();
        Kentta ke = new Kentta(20,10);
        ke.setKohta(10, 5, 2);
        ke.setKohta(8, 5, 2);
        ke.setVari(10, 5, 4);
        ke.setVari(8, 5, 3);
        
        ke.kerrosVaihto(10, p);
        assertEquals(0, ke.getKohta(10,5));
        assertEquals(1, ke.getKohta(9,5));
        assertEquals(0, ke.getVari(10,5));
        assertEquals(3, ke.getVari(9,5));
    }
    
//    @Test
//    public void onkotaysiriviTest() {
//        kentta = new Kentta(20, 10);
//        for (int e = 0;e<10;e++) {
//            kentta.setKohta(19, e, 1);
//        }
//        log.onkoTaysiRivi();
//        for (int e = 0; e < 10; e++) {
//            assertEquals(0, kentta.getKohta(19, e));
//        }
//    }
//    
//    @Test
//    public void updateTest1() {
//        Kentta k = log.getKentta();
//        for (int e = 0; e < 10; e++) {
//            log.kenttaSetKohta(19, e, 1);
//        }
//        log.updateKentta();
//        for (int e = 0; e < 10; e++) {
//            k = log.getKentta();
//            assertEquals(2, log.kenttaGetKohta(19, e));
//        }
//    }
//    
//    @Test
//    public void updateTest2() {
//        kentta = new Kentta(20, 10);
//        for (int e = 0; e < 1000; e++) {
//            log.update();
//        }
//        boolean totta = true;
//        for (int e = 0; e < 10; e++) {
//            if (log.getKentta().getKohta(19, e) != 0) {
//                totta = false;
//            }
//        }
//        assertTrue(totta);
//    }

}
