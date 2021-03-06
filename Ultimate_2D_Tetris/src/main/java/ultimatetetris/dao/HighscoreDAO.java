/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ultimatetetris.dao;

import java.sql.*;
import java.util.*;

/**
 *
 * @author khlehto
 */
public class HighscoreDAO {
    
    String db;
     /**
      * tietokanna luonti os sitä ei ole.
      * @param db tietokannalle nimi
      */
    public HighscoreDAO(String db) {
        this.db = db;
        try {
            Connection connection = DriverManager.getConnection("jdbc:sqlite:./" + db + ".db", "sa", "");
            PreparedStatement statement = connection.prepareStatement("CREATE TABLE IF NOT EXISTS scores (\n"
                    + "id INTEGER PRIMARY KEY,\n"
                    + "name varchar(144),\n"
                    + "points INTEGER\n"
                    + ");");
            statement.execute();

            statement.close();
            connection.close();
        } catch (Exception e)  {
            
        }
        
    }
    /**
     * luodaan tietokantaan uusi tulos.
     * tuloksessa on tiedossa nimi a pistemäärä.
     * @param name tieto minkä niminen henkil
     * @param score tieto henkilön saadusta pisteistä
     */
    public void newHighscore(String name, int score) {
        try {
            Connection con = openConnection();
            String create = "INSERT INTO scores (name, points) VALUES (?, ?)";
            PreparedStatement check = con.prepareStatement(create);
            check.setString(1, name);
            check.setInt(2, score);
            check.execute();
            check.close();
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        
    }
    /**
     * Haetaan kaikki muistissa olevat tulokset.
     * lista palautetaan muodossa "nimi:tulos"
     * 
     * @return palauttaa listan tuloksista.
     */
    public List getHighscores() {
        List<String> scores = new ArrayList<>();
        try {
            Connection con = openConnection();
            String getD = "SELECT name, points FROM scores ORDER BY points DESC";
            PreparedStatement namescore = con.prepareStatement(getD);
            ResultSet data = namescore.executeQuery();
            while (data.next()) {
                String name = data.getString("name");
                int score = data.getInt("points");
                scores.add(name + ":" + score);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return scores;
    }
    /**
     * yrittää avata tien tietokantaan.
     * @return palauttaa tien.
     * @throws Exception 
     */
    public Connection openConnection() throws Exception {
        Connection con = DriverManager.getConnection("jdbc:sqlite:./" + db + ".db", "sa", "");
        return con;
    }
    
}
