/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pidev.Utilis;

/**
 *
 * @author Mayssa
 */
public class SessionManager {
   private static SessionManager instance;
 
    private static int id;
    private static int cin;
    private static String email;
    private static int num_tel;
    private static String nom;
    private static String adresse;
    private static String prenom ; 
    private static String roles;

     private SessionManager(int id , int cin , String email, int num_tel , String nom ,String adresse,String prenom,String role ) {
    SessionManager.id=id;
    SessionManager.cin=cin;
    SessionManager.email=email;
    SessionManager.num_tel=num_tel;
    SessionManager.nom=nom;
    SessionManager.adresse=adresse;
    SessionManager.prenom=prenom; 
    SessionManager.roles=role;
    }
 
      
    public static SessionManager getInstace(int id , int cin , String email, int num_tel , String nom ,String adresse,String prenom,String role) {
        if(instance == null) {
            instance = new SessionManager( id , cin ,  email ,  num_tel ,  nom,adresse,prenom, role);
        }
        return instance;
    }

    
    public static SessionManager getInstance() {
        return instance;
    }

    public static void setInstance(SessionManager instance) {
        SessionManager.instance = instance;
    }

    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        SessionManager.id = id;
    }

    public static int getCin() {
        return cin;
    }

    public static void setCin(int cin) {
        SessionManager.cin = cin;
    }

    public static String getEmail() {
        return email;
    }

    public static void setEmail(String email) {
        SessionManager.email = email;
    }

    public static int getNum_tel() {
        return num_tel;
    }

    public static void setNum_tel(int num_tel) {
        SessionManager.num_tel = num_tel;
    }

    public static String getNom() {
        return nom;
    }

    public static void setNom(String nom) {
        SessionManager.nom = nom;
    }

    public static String getAdresse() {
        return adresse;
    }

    public static void setAdresse(String adresse) {
        SessionManager.adresse = adresse;
    }

    public static String getPrenom() {
        return prenom;
    }

    public static void setPrenom(String prenom) {
        SessionManager.prenom = prenom;
    }

    public static String getRoles() {
        return roles;
    }

    public static void setRoles(String roles) {
        SessionManager.roles = roles;
    }

  
    
     public static void cleanUserSession() {
     id=0;
     cin=0;
     email="";
     num_tel=0;
     nom="";
     prenom="";
    adresse="" ; 
     roles="";
    }

    
 
 
    
    static class cleanUserSession {
 
        public cleanUserSession() {
          id=0;
         cin=0;
     email="";
     num_tel=0;
     nom="";
     prenom="";
     roles="";
     adresse=""; 
        }
    }
    
}
