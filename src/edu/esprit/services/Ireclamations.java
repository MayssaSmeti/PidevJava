/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.services;

import edu.esprit.entities.Reclamation;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author msi
 */
public interface Ireclamations {
   
    public void ajouterReclamation2(Reclamation r) throws SQLException;
    public void modifier(Reclamation r) throws SQLException;
    public void supprimer(int id) throws SQLException;
    public List<Reclamation> afficherReclamations();
    
}

