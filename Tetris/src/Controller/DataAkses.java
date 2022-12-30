/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import database.ConnectionManager;
import model.Score;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author asus
 */
public class DataAkses {
    public static List <Score> showScore() throws SQLException{
        List <Score> listScore = new ArrayList<>();
        
        String query = "select * from score;";
        try{
            PreparedStatement st = ConnectionManager.getConnection().prepareStatement(query);
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                Score s = new Score();
                s.setNama(rs.getString("nama"));
                s.setScore(rs.getInt("score"));
                listScore.add(s);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return listScore;
    }
}
