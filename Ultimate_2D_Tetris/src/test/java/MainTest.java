/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.Rectangle;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import ultimate_2d_tetris.ot.harjoitustyo.Main;

/**
 *
 * @author khlehto
 */
public class MainTest extends Application {
    
    public MainTest() {
    }
   
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
     @Test
     public void hello() {
         Parent rootnode = Main.getScene().getRoot();
         
     }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setScene(Main.getScene());
        stage.show();
        stage.toFront();
    }
}
