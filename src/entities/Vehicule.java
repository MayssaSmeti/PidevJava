package entities;

public class Vehicule {
  private int id;
  private String matricule ;
  public Vehicule(int id, String matricule) {
    this.id = id;
    this.matricule = matricule;
  }
  public Vehicule() {
  }
  public int getId() {
    return id;
  }
  public void setId(int id) {
    this.id = id;
  }
  public String getMatricule() {
    return matricule;
  }
  public void setMatricule(String matricule) {
    this.matricule = matricule;
  }


  
  
}
