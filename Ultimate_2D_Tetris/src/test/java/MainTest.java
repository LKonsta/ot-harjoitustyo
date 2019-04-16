/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.Rectangle;
import java.util.ArrayList;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import ultimatetetris.Main;
import ultimatetetris.Kentta;
import ultimatetetris.Kuutio;
import ultimatetetris.Palikka;

/**
 *
 * @author khlehto
 */
public class MainTest{
    
    public MainTest() {
    }
   
    Kentta kentta;
    
    @Before
    public void setUp() {
        this.kentta = new Kentta(20,10);
        
    }
    
    @Test
    public void Palikkatesti1() {
        Color[] vari = {Color.YELLOW, Color.TEAL, Color.GREEN, Color.RED, Color.ORANGE, Color.BLUE, Color.PURPLE
        };
        Palikka p = new Palikka(3, 0, vari, kentta, 0);
        for (int l = 0;l<20;l++) {
            p.liikuAlas();
        }
        assertEquals(3, p.x);
        assertEquals(18, p.y);
    }
    @Test
    public void Palikkatesti2() {
        Color[] vari = {Color.YELLOW, Color.TEAL, Color.GREEN, Color.RED, Color.ORANGE, Color.BLUE, Color.PURPLE
        };
        Palikka p = new Palikka(3, 0, vari, kentta, 0);
        for (int l = 0; l < 20; l++) {
            p.liikuAlas();
            p.liikuVasen();
        }
        assertEquals(0, p.x);
        assertEquals(18, p.y);
    }
    @Test
    public void Palikkatesti3() {
        Color[] vari = {Color.YELLOW, Color.TEAL, Color.GREEN, Color.RED, Color.ORANGE, Color.BLUE, Color.PURPLE
        };
        Palikka p = new Palikka(3, 0, vari, kentta, 0);
        for (int l = 0; l < 20; l++) {
            p.liikuAlas();
            p.liikuOikea();
        }
        assertEquals(8, p.x);
        assertEquals(18, p.y);
    }
    @Test
    public void PalikkaLuomistesti1() {
        Color[] vari = {Color.YELLOW, Color.TEAL, Color.GREEN, Color.RED, Color.ORANGE, Color.BLUE, Color.PURPLE
        };
        Palikka p1 = new Palikka(3, 3, vari, kentta, 1);
        ArrayList<Kuutio> k1 =  p1.getKuutiot();
        
        assertEquals(5, k1.get(0).getKohtaX());
        assertEquals(3, k1.get(0).getKohtaY());
        
        assertEquals(5, k1.get(1).getKohtaX());
        assertEquals(4, k1.get(1).getKohtaY());
        
        assertEquals(5, k1.get(2).getKohtaX());
        assertEquals(5, k1.get(2).getKohtaY());
        
        assertEquals(5, k1.get(3).getKohtaX());
        assertEquals(6, k1.get(3).getKohtaY());
        
        Palikka p2 = new Palikka(3, 3, vari, kentta, 2);
        ArrayList<Kuutio> k2 = p2.getKuutiot();

        assertEquals(4, k2.get(0).getKohtaX());
        assertEquals(3, k2.get(0).getKohtaY());

        assertEquals(4, k2.get(1).getKohtaX());
        assertEquals(4, k2.get(1).getKohtaY());

        assertEquals(5, k2.get(2).getKohtaX());
        assertEquals(4, k2.get(2).getKohtaY());

        assertEquals(5, k2.get(3).getKohtaX());
        assertEquals(5, k2.get(3).getKohtaY());
        
        Palikka p3 = new Palikka(3, 3, vari, kentta, 3);
        ArrayList<Kuutio> k3 = p3.getKuutiot();

        assertEquals(4, k3.get(0).getKohtaX());
        assertEquals(3, k3.get(0).getKohtaY());

        assertEquals(3, k3.get(1).getKohtaX());
        assertEquals(4, k3.get(1).getKohtaY());

        assertEquals(4, k3.get(2).getKohtaX());
        assertEquals(4, k3.get(2).getKohtaY());

        assertEquals(3, k3.get(3).getKohtaX());
        assertEquals(5, k3.get(3).getKohtaY());
        
        Palikka p4 = new Palikka(3, 3, vari, kentta, 4);
        ArrayList<Kuutio> k4 = p4.getKuutiot();

        assertEquals(3, k4.get(0).getKohtaX());
        assertEquals(3, k4.get(0).getKohtaY());

        assertEquals(3, k4.get(1).getKohtaX());
        assertEquals(4, k4.get(1).getKohtaY());

        assertEquals(3, k4.get(2).getKohtaX());
        assertEquals(5, k4.get(2).getKohtaY());

        assertEquals(4, k4.get(3).getKohtaX());
        assertEquals(5, k4.get(3).getKohtaY());
        
        Palikka p5 = new Palikka(3, 3, vari, kentta, 5);
        ArrayList<Kuutio> k5 = p5.getKuutiot();

        assertEquals(5, k5.get(0).getKohtaX());
        assertEquals(3, k5.get(0).getKohtaY());

        assertEquals(5, k5.get(1).getKohtaX());
        assertEquals(4, k5.get(1).getKohtaY());

        assertEquals(4, k5.get(2).getKohtaX());
        assertEquals(5, k5.get(2).getKohtaY());

        assertEquals(5, k5.get(3).getKohtaX());
        assertEquals(5, k5.get(3).getKohtaY());
        
        Palikka p6 = new Palikka(3, 3, vari, kentta, 6);
        ArrayList<Kuutio> k6 = p6.getKuutiot();

        assertEquals(4, k6.get(0).getKohtaX());
        assertEquals(3, k6.get(0).getKohtaY());

        assertEquals(3, k6.get(1).getKohtaX());
        assertEquals(4, k6.get(1).getKohtaY());

        assertEquals(4, k6.get(2).getKohtaX());
        assertEquals(4, k6.get(2).getKohtaY());

        assertEquals(5, k6.get(3).getKohtaX());
        assertEquals(4, k6.get(3).getKohtaY());
    }
    
    @Test
    public void PalikkaPyoritystest1() {
        Color[] vari = {Color.YELLOW, Color.TEAL, Color.GREEN, Color.RED, Color.ORANGE, Color.BLUE, Color.PURPLE
        };
        int[][][] muodot = {{{0,0,1},{0,0,1},{0,1,1}},
                            {{1,1,1},{0,0,1},{0,0,0}},
                            {{1,1,0},{1,0,0},{1,0,0}},
                            {{0,0,0},{1,0,0},{1,1,1}}
        };
        Palikka p = new Palikka(3, 3, vari, kentta, 5);
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
        Kentta ke = new Kentta(20,10);
        Kuutio k = new Kuutio(10, 5, 30, 30, Color.BLACK, ke);
        
        ke.kerrosVaihto(10);
    }
    

}
