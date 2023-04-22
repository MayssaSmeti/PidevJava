package service;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import utils.MyConnection;


import java.sql.Date;
//import Projet.entities.User;
import entities.Contrat;
import entities.User;
import utils.MyConnection;
import java.sql.Statement;

public class UserService implements IUserService{
    Connection cnx = MyConnection.getInstance().getCnx();
    

    
        
    public List<User> getAll()  {
        List<User> myList = new ArrayList<>();  
        try {
                String req3="SELECT * FROM user";
                Statement st = cnx.createStatement(); 
                ResultSet rs = st.executeQuery(req3);
                while(rs.next()){
                    User o =new User();
                    o.setId(rs.getInt("id"));
                    o.setCin(rs.getInt("cin"));
                    myList.add(o);
                }
            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
            }
            return myList;
    }

}
