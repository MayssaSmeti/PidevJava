package service;

import java.sql.SQLException;
import java.util.List;

import entities.User;

public interface IUserService {
   
    public List<User> getAll() ;
   
}
