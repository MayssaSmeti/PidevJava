/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.tests;

import edu.esprit.entities.Client;
import edu.esprit.entities.Constat;
import edu.esprit.entities.Expert;
import edu.esprit.entities.User;
import edu.esprit.entities.Vehicule;
import edu.esprit.services.ConstatCRUD;
import edu.esprit.services.VehiculeCRUD;
import edu.esprit.utils.MyConnection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author USER
 */
public class ConnectionDB {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        try {
        MyConnection mc = MyConnection.getInstance();
        System.out.println(mc.hashCode());
        ConstatCRUD ccd = new ConstatCRUD();
        User u = new User(1);
        Expert e = new Expert(1);
        Vehicule v = new Vehicule(1);
        //ccd.ajouterConstat();
        Constat c2 = new Constat("eyat","bkh","voiture","kia","gat","nabeul","tunis","eya.png","no description","no observations","123456789","aya@esprit.tn","2013-05-02 20:05:11",e,v);
        ccd.ajouterConstat2(c2);
        //System.out.println(ccd.afficherConstats());
        //Constat c3 = new Constat(9,"eyat","bkh","voiture","kia","gat","nabeul","tunis","eya.png","no description","no observations","123456789","aya@esprit.tn","2013-05-02 20:05:11",e,v);
        //ccd.modifier(c3);
        ccd.supprimer(16);
        } 
        catch (SQLException ex) {
               System.err.println(ex.getMessage());
        }
       
        try {
        VehiculeCRUD vcd = new VehiculeCRUD();
        Client c = new Client (1);
        //vcd.ajouterVehicule();
        Vehicule v2 = new Vehicule("123456789","voiture","kia","6",c);
        vcd.ajouterVehicule2(v2);
        //System.out.println(vcd.afficherVehicules());
        //Vehicule v3 = new Vehicule(7,"123456789","voiture","kia","6",c);
        //vcd.modifier(v3);
        vcd.supprimer(10);
        } 
        catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        
        
    }
    
}
