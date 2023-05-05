/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pidev.Services;

import Pidev.Entities.Constat;
import Pidev.Entities.DevisItem;
import Pidev.Entities.Locale;
import Pidev.Entities.Rapport;
import Pidev.Entities.Reclamation;
import Pidev.Entities.Rendezvous;
import Pidev.Entities.Reponse;
import Pidev.Entities.User;
import Pidev.Entities.Vehicule;
import java.sql.SQLException;
import java.util.List;
import javafx.collections.ObservableList;

/**
 *
 * @author Mayssa
 */
public interface IService<U> {

    public void modifier(U p) throws SQLException;

    public void supprimer(int id) throws SQLException;

    public List<U> getAll() throws SQLException;

    public void ajouterReclamation2(Reclamation r) throws SQLException;

    public void modifier(Reclamation r) throws SQLException;

    public void supprimerRec(int id) throws SQLException;

    public List<Reclamation> afficherReclamations();

    public void ajouterReponse2(Reponse re) throws SQLException;

    public void modifier(Reponse re) throws SQLException;

    public void supprimerRep(int id) throws SQLException;

    public List<Reponse> afficherReponses();

    public void ajouterDevis(U p) throws SQLException;

    public void modifierDevis(U p) throws SQLException;

    public void supprimeDevis(int id) throws SQLException;

    public List<U> getAllDevis() throws SQLException;
     public List<U> getAllDevisMec() throws SQLException ;

    public List<DevisItem> getDevisItems(int id) throws SQLException;

    public void ajouterLocale(Locale p) throws SQLException;

    public void modifierLocale(Locale p) throws SQLException;

    public void supprimeLocale(int id) throws SQLException;

    public List<Locale> getLocale() throws SQLException;

    public List<U> searchProduit(String query) throws SQLException;
    
    public void ajouterVehicule();
    
    public void ajouterVehicule2(Vehicule v);
    
    public List<Vehicule> afficherVehicules();
    
    public void modifier(Vehicule v) throws SQLException;
    
    public void supprimerVehicule(int id) throws SQLException;
    
    public ObservableList<Vehicule> getvehiculeList() throws SQLException;
    
    public void ajouterConstat();
    
    public void ajouterConstat2(Constat c);
    
    public List<Constat> afficherConstats();
    
    public void modifier(Constat c) throws SQLException;
    
    public void supprimerConstat(int id) throws SQLException;
    
    public ObservableList<Constat> getconstatList() throws SQLException;
    
    public Constat getOneConstat(int idc) throws SQLException ;
    
    public List<User> getAllContrat() ;
    
    public void ajouter(U r);
    public void supprimer1(int id);
    public void modifier1(U r);
    public List<U> getAll1();
    public Rapport getOneOffre(int idOffre) throws SQLException;
    public Rendezvous getOneContrat(int idc) throws SQLException;

}

