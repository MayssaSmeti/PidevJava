package Pidev.Services;

import java.sql.SQLException;
import java.util.List;

import Pidev.Entities.Offre;


public interface  IOffreService {
    public void ajouter(Offre o) throws SQLException;
    public void modifier(Offre o) throws SQLException;
    public void supprimer(int id) throws SQLException;
    public List<Offre> getAll();
    public Offre getOneOffre(int idOffre) throws SQLException;
}
