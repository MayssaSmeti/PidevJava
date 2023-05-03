
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pidev.Entities;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;
import java.util.UUID;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 *
 * @author Mayssa
 */
public class User {

    private int id;
    private String email;
    private String nom;
    private String prenom;
    private String password;
    private int cin;
    private String adresse;
    private int num_tel;
    private String roles;
    private String activation_token;
    private String status;

    public User() {

    }

    public User(String email) {
        this.email = email;
    }
    

    public User(String email, String nom, String prenom, String password, Integer cin, String adresse, Integer num_tel, String roles) {
        this.email = email;
        this.nom = nom;
        this.prenom = prenom;
        this.password = password;
        this.cin = cin;
        this.adresse = adresse;
        this.num_tel = num_tel;
        this.roles = roles; //To change body of generated methods, choose Tools | Templates.
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }
    

    public User(int id, String email, String roles, String nom, String prenom, int cin, int num_tel, String adresse) {
        //To change body of generated methods, choose Tools | Templates.
        this.id = id;
        this.email = email;
        this.nom = nom;
        this.prenom = prenom;
        this.status = status;
        this.cin = cin;
        this.adresse = adresse;
        this.num_tel = num_tel;
        this.roles = roles;

    }

    public User(int id, TextField fxnom, TextField fxprenom, TextField fxemail, Integer cin, Integer num_tel, TextField fxadresse) {
        this.id = id;
        this.email = fxemail.getText();
        this.nom = fxnom.getText();
        this.prenom = fxprenom.getText();
       
        this.cin = cin ;
        this.adresse = fxadresse.getText();
        this.num_tel = num_tel;
      
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public User(int id, String email, String nom, String prenom, int cin, String adresse, int num_tel, String roles, String status) {
        this.id = id;
        this.email = email;
        this.nom = nom;
        this.prenom = prenom;
        this.cin = cin;
        this.adresse=adresse;
        this.num_tel=num_tel; 
        this.roles = roles;
        this.status = status;
    }

    public String getActivation_token() {
        return activation_token;
    }

    public void setActivation_token(String activation_token) {
        this.activation_token = activation_token;
    }

    public static User Current_User;

    public User(int id, String email, String nom, String prenom, String password, String roles, String status) {
        this.id = id;
        this.email = email;
        this.nom = nom;
        this.prenom = prenom;
        this.password = password;

        this.roles = roles;

        this.status = status;
    }

    public User(String email, String nom, String prenom, String password, int cin, String adresse, int num_tel, String roles, String activation_token, String status) {

        this.email = email;
        this.nom = nom;
        this.prenom = prenom;
        this.password = (hashMotDePasse(password));
        this.cin = cin;
        this.adresse = adresse;
        this.num_tel = num_tel;
        this.roles = roles;
        this.activation_token = generateCode();
        this.status = status;
    }

    public User(int id, String email, String nom, String prenom, int cin, String adresse, int num_tel, String roles) {
        this.id = id;
        this.email = email;
        this.nom = nom;
        this.prenom = prenom;
        this.cin = cin;
        this.adresse = adresse;
        this.num_tel = num_tel;
        this.roles = roles;
    }

    public User(String email, String nom, String prenom, String password, int cin, String adresse, int num_tel) {
        this.email = email;
        this.nom = nom;
        this.prenom = prenom;
        this.password = (hashMotDePasse(password));
        this.cin = cin;
        this.adresse = adresse;
        this.num_tel = num_tel;
    }

    public User(String email, String nom, String prenom, int cin, String adresse, int num_tel, String roles) {
        this.email = email;
        this.nom = nom;
        this.prenom = prenom;
        this.cin = cin;
        this.adresse = adresse;
        this.num_tel = num_tel;
        this.roles = roles;
    }

    public User(TextField fxemail, TextField fxnom, TextField fxprenom, PasswordField fxpassword, Integer cin, TextField fxadresse, Integer num_tel, TextField fxroles) {
        this.email = fxemail.getText();
        this.cin = cin;
        this.num_tel = num_tel;
        this.password = (hashMotDePasse(fxpassword.getText()));
        this.roles = fxroles.getText();
        this.nom = fxnom.getText();
        this.prenom = fxprenom.getText();
        this.adresse = fxadresse.getText();

    }

    public User(TextField email, int cin, int num_tel, TextField role, TextField nom, TextField prenom, TextField adresse) {

        this.email = email.getText();
        this.cin = cin;
        this.num_tel = num_tel;
        this.roles = role.getText();
        this.nom = nom.getText();
        this.prenom = prenom.getText();
        this.adresse = adresse.getText();

    }

    public static User getCurrent_User() {
        return Current_User;
    }

    public static void setCurrent_User(User Current_User) {
        User.Current_User = Current_User;
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

    public int getNum_tel() {
        return num_tel;
    }

    public void setNum_tel(int num_tel) {
        this.num_tel = num_tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String edresse) {
        this.adresse = edresse;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", email=" + email + ", nom=" + nom + ", prenom=" + prenom + ", password=" + password + ", cin=" + cin + ", adresse=" + adresse + ", num_tel=" + num_tel + ", roles=" + roles + ", activation_token=" + activation_token + '}';
    }

    public String generateCode() {

        Random random = new Random();
        int codeInt = random.nextInt(1000000);
        String activation_token = String.format("%06d", codeInt);

        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder sb = new StringBuilder(activation_token);
        for (int i = 0; i < 2; i++) {
            int index = random.nextInt(alphabet.length());
            sb.insert(random.nextInt(sb.length() + 1), alphabet.charAt(index));
        }
        activation_token = sb.toString();

        return activation_token;
    }

    public String hashMotDePasse(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : hashBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

}
