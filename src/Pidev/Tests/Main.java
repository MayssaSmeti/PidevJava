/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pidev.Tests;

import API.Mail;
import API.SMS;

import Pidev.Entities.User;
import Pidev.Services.UserCrud;
import Pidev.Utilis.MyConnection;
import javax.mail.MessagingException;



/**
 *
 * @author Mayssa
 */
//fxml est un lg de structure de données baseé sur le xml 
public class Main {
    public static void main (String[] args ) throws MessagingException 
    {
        MyConnection mc = MyConnection.getInstance(); 
        MyConnection mc2 = MyConnection.getInstance() ; 
        System.out.println(mc.hashCode()+"-"+mc2.hashCode());
        UserCrud pcd = new UserCrud () ;
        //User u2 = new User("rrrr@gmail.com", "hh","hhhhh","tttttttttt", 0, "dddd", 0, "expert"); 
       // pcd.ajouterUtilisateur2(u2);
        /// pcd.modifierUtilisateur(u2);
         //pcd.supprimerUtilisateur(u2);
      // User u3 = new User(86,"loul@gmail.com", "loulgggo", "edii",22222, "hjbbfb", 0, "hdfjhj") ; 

        //pcd.modifierUtilisateur(u3);
      // pcd.selectEmail("mayssa.smeeti@gmail.com");

       // System.out.println(pcd.afficherUtilisateurs());
      // Mail test = new  Mail(); 
        //test.sendEmail();
        //sendSMS test= new sendSMS();
        //test.sendSms(); 
        
    }
    
    
    
}
