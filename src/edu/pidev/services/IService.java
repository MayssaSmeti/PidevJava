/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pidev.services;

import java.util.List;

/**
 *
 * @author abdelazizmezri
 */
public interface IService <T>{
    public void ajouter(T r);
    public void supprimer(int id);
    public void modifier(T r);
    public List<T> getAll();
    
}
