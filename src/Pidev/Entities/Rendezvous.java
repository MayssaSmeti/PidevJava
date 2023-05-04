/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pidev.Entities;

import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

/**
 *
 * @author abdelazizmezri
 */
public class Rendezvous {

    public static void setId(Rendezvous rendezvous, int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    private int id;
    private String lieu;
    private Date date;
    private Rapport type_id;
    
    
     public static int actionTest;
    
    
    public Rendezvous() {
    }

    public Rendezvous(Date date, String lieu, Rapport type_id) {
        this.date = date;
        this.lieu = lieu;
        this.type_id = type_id;
    }

    public Rendezvous(int id,Date date, String lieu, Rapport type_id) {
        this.id = id;
        this.date=date;
        this.lieu = lieu;
        this.type_id = type_id;
    }

   

    public Rendezvous(String lieu, Date date) {
       
    }

    public Rendezvous(Date date, String lieu) {
     
        this.date = date;
            this.lieu = lieu;
    }

    public Rendezvous(int id, String lieu, Date date) {
        this.id = id;
        this.lieu = lieu;
        this.date = date;
    }


    public Rapport getType_id() {
        return type_id;
    }

    public void setType_id(Rapport type_id) {
        this.type_id = type_id;
    }
    
  

  
    public int getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

  

    public String getLieu() {
        return lieu;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Rendezvous{" + "id=" + id + ", lieu=" + lieu + ", date=" + date + ", type_id=" + type_id + '}';
    }

   

   
    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Rendezvous other = (Rendezvous) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    public void setDate(String strVal) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
