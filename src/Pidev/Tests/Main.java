/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pidev.Tests;

import Pidev.Entities.User;
import Pidev.Services.ServiceDevis;
import Pidev.Utilis.MyConnection;
import java.sql.SQLException;

/**
 *
 * @author Mayssa
 */
public class Main {
        public static void main(String[] args) {
        MyConnection mc = MyConnection.getInstance();

        try {
            ServiceDevis sd = new ServiceDevis();
            User u=new User(1);
            //Mecanicien m = new Mecanicien(2);
            //Expert e=new Expert(1);
            //Devis p1 = new Devis(77,200,e,m);
           // sd.ajouterDevis(p1);
            System.out.println(sd.getAll());
            //sd.supprimeDevis(76);
           // sd.modifierDevis(p1);
     
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
