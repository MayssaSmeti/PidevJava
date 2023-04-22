package service;

import java.sql.SQLException;
import java.util.List;

import entities.Contrat;

public interface IContratService {
    public void ajouter(Contrat o);
    public void modifier(Contrat o) throws SQLException;
    public void supprimer(int id) throws SQLException;
    public List<Contrat> getAll() ;
    public Contrat getOneContrat(int idc) throws SQLException;
}
