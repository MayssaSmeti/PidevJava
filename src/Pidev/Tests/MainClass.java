/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pidev.Tests;

import Pidev.Entities.Rapport;
import Pidev.Entities.Rendezvous;
import Pidev.Services.ServiceRapport;
import Pidev.Services.ServiceRendezvous;
import Pidev.Utilis.DataSource;
import java.sql.Date;
import java.util.List;

/**
 *
 * @author abdelazizmezri
 */
public class MainClass {
    
    public static void main(String[] args) {
        
       /* Rendezvous p1 = new Rendezvous(3,"Abdelaziz", "M");
        Rendezvous p2 = new Rendezvous("Tarak", "Ayari");
        Rendezvous p3 = new Rendezvous("Samir", "Sankou7");
        Rendezvous p4 = new Rendezvous("Sinda", "Hamdi");
        
        ServiceRendezvous sp = new ServiceRendezvous();
        
        /*sp.ajouter(p1);
        sp.ajouter(p2);
        sp.ajouter2(p3);
        sp.ajouter2(p4);*/
        
       // sp.supprimer(3);
       //Rapport r1=new Rapport("benothman","ben","aa","aaaaz");
       //ServiceRapport s1=new ServiceRapport();
       //s1.ajouter(r1);
        //s1.supprimer(11);
       // s1.supprimer(6);
      //s1.getAll().forEach(rapport -> System.out.println(rapport));

       // Rendezvous r = new Rendezvous(,Date.valueOf("2023-06-15"), "hammamet", new Rapport(10));
        ServiceRendezvous s2=new ServiceRendezvous();
        //s2.modifier(r);
       
       /* List <Rendezvous> listRendezvous = s2.getAll();
        for (Rendezvous rendezvous: listRendezvous) {
            System.out.println(r);
       s2.getAll();
    }
       */
        s2.getAll().forEach(rendezvous -> System.out.println(rendezvous));

    
}
}
