
package Pidev.Utilis;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author zaghd
 */
public class MyConnection {

   
    
    public String url="jdbc:mysql://localhost:3306/pidev";
    public String login="root";
    public String pwd="";
    Connection cnx; 
    public static MyConnection instance;
    
    private MyConnection(){
        try {
           cnx =  DriverManager.getConnection(url, login, pwd);
           System.out.println("connexion est etablie avec succee");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    public Connection getCnx() {
        return cnx;
    }
    public static MyConnection getInstance() {
        if (instance == null) {
            instance = new MyConnection();
        }
        return instance;
    }
}
