/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.tests;

import edu.esprit.entities.Assureur;
import edu.esprit.entities.Client;
import edu.esprit.entities.Reclamation;
import edu.esprit.entities.Reponse;
import edu.esprit.entities.User;
import edu.esprit.services.ReclamationCRUD;
import edu.esprit.services.ReponseCRUD;
import edu.esprit.utils.MyConnection;

/**
 *
 * @author msi
 */
public class MainClass {
    public static void main(String[] args){
        MyConnection mc = MyConnection.getInstance();
        MyConnection mc2 = MyConnection.getInstance();
        System.out.println(mc.hashCode()+ " - "+mc2.hashCode());
        ReclamationCRUD pcd = new ReclamationCRUD();
        User u=new User(1);
        Client c=new Client(1);
        Reclamation r2 = new Reclamation("descriptionB","objetB",c,5);
        pcd.ajouterReclamation2(r2);
       
       //System.out.println(pcd.afficherReclamations());
       pcd.supprimer(158);
       //Reclamation r3 = new Reclamation(98,"descp","obj",c,5);
       //pcd.modifier(r3);
       
       
        ReponseCRUD pc = new ReponseCRUD();
        Assureur a=new Assureur(1);
        Reclamation r=new Reclamation(5);
        Reponse re2 = new Reponse("descriptionA",r,a);
        //pc.ajouterReponse();
        //pc.ajouterReponse2(re2);
       // pc.ajouter(re2);
        //System.out.println(pc.afficherReponses());
        //pc.supprimer(46);
        //Reponse re3 = new Reponse(32,"descp",r,a);
        //pc.modifier(re3);
    }            
}
