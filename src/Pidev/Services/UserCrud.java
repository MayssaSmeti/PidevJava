/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pidev.Services;

import Pidev.Entities.User;
import Pidev.Utilis.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Mayssa
 */
public class UserCrud implements IService {
Connection cnx = MyConnection.getInstance().getCnx();
    
    
    public List<User> getAll()  {
        List<User> myList = new ArrayList<>();  
        Connection cnx = MyConnection.getInstance().getCnx();
        try {
                String req3="SELECT * FROM user";
                Statement st = cnx.createStatement(); 
                ResultSet rs = st.executeQuery(req3);
                while(rs.next()){
                    User o =new User();
                    o.setId(rs.getInt("id"));
                    o.setEmail(rs.getString("email"));
                    o.setNum_tel(rs.getInt("num_tel"));
                    o.setCin(rs.getInt("cin"));
                    myList.add(o);
                }
            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
            }
            return myList;
    }
   
    
    
}