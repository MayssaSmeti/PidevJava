/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pidev.Services;

import java.sql.SQLException;
import Pidev.Entities.DevisItem;
import Pidev.Entities.Locale;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Mayssa
 */
public interface IService <T> {
     public void ajouterDevis(T p) throws SQLException;
    public void modifierDevis(T p) throws SQLException;
    public void supprimeDevis(int id) throws SQLException;
    public List<T> getAll() throws SQLException;
    public List<DevisItem> getDevisItems(int id) throws SQLException;
    public void ajouterLocale(Locale p) throws SQLException;
    public void modifierLocale(Locale p) throws SQLException;
    public void supprimeLocale(int id) throws SQLException;
    public List<Locale> getLocale() throws SQLException;
     public List<T> searchProduit(String query)throws SQLException;
    
}
