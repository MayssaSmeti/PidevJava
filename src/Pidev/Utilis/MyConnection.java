/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pidev.Utilis;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author Mayssa
 */
public class MyConnection {
    public String url="jdbc:mysql://localhost:3306/pidev3";
    public String login="root";
    public String pwd="";
    
    Connection cnx;
    Connection role ; 
    public static MyConnection instance;
    
   private  MyConnection(){
        try {
           cnx = DriverManager.getConnection(url ,login ,pwd);
           System.out.println("Connexion etablie!");
        } catch (SQLException ex) {
           System.err.print(ex.getMessage());
        }
    }
    
    public Connection getCnx() {
        return cnx;
    }
    public Connection getRole(){
        return role ;
    }
    
    public static MyConnection getInstance(){
        if(instance == null){
        instance = new MyConnection();
        }
        return instance;
    }
    
}
