package entities;

public class User {
  private int id;
  private int cin ;


  public User(int id, int cin) {
    this.id = id;
    this.cin = cin;
  }

  public User() {
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getCin() {
    return cin;
  }
  
  public void setCin(int cin) {
    this.cin = cin;
  }

  @Override
  public String toString() {
    return "User [id=" + id + ", cin=" + cin + "]";
  } 
  
  
}
