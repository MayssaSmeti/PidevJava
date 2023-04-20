/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pidev.Tests;

import Pidev.Entities.User;
import Pidev.Services.UserCrud;
import Pidev.Utilis.MyConnection;



/**
 *
 * @author Mayssa
 */
//fxml est un lg de structure de données baseé sur le xml 
public class Main {
    public static void main (String[] args )
    {
        MyConnection mc = MyConnection.getInstance(); 
        MyConnection mc2 = MyConnection.getInstance() ; 
        System.out.println(mc.hashCode()+"-"+mc2.hashCode());
        UserCrud pcd = new UserCrud () ;
        User u2 = new User("maissaup@gmail.com", "lloul", "edi", "kkkkk",22222, "hjbbfb", 0, "hdfjhj") ; 
        pcd.ajouterUtilisateur2(u2);
        /// pcd.modifierUtilisateur(u2);
         //pcd.supprimerUtilisateur(u2);
       //User u3 = new User(65,"loul@gmail.com", "loulo", "edii",22222, "hjbbfb", 0, "hdfjhj") ; 

       // pcd.modifierUtilisateur(u3);


        System.out.println(pcd.afficherUtilisateurs());
        
    }
}
