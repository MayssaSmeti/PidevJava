/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pidev.Services;

import Pidev.Entities.User;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Mayssa
 */
public interface IService <U> {
    public List<User> getAll() ;
    
}