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
import entities.Vehicule;
import utils.MyConnection;
import java.sql.Statement;

public class VehiculeService implements IVehiculeService{
    Connection cnx = MyConnection.getInstance().getCnx();
    

    
        
    public List<Vehicule> getAll()  {
        List<Vehicule> myList = new ArrayList<>();  
        try {
                String req3="SELECT * FROM vehicule";
                Statement st = cnx.createStatement(); 
                ResultSet rs = st.executeQuery(req3);
                while(rs.next()){
                    Vehicule o =new Vehicule();
                    o.setId(rs.getInt("id"));
                    o.setMatricule(rs.getString("matricule"));
                    myList.add(o);
                }
            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
            }
            return myList;
    }

}
