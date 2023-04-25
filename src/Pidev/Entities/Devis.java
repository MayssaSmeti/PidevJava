/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pidev.Entities;


import Pidev.Services.ServiceDevis;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author SCORPIO-12
 */
public class Devis {

  
    private static int id;
    private float total_ht;
    private Date date;
    private Expert expert;
    private Mecanicien mecanicien;
    
    private String nomM;
     private String nomE;
    private List<DevisItem> devisItems;

    public Devis(int id, float total_ht, Date date, Expert expert, Mecanicien mecanicien) {
        this.id = id;
        this.total_ht = total_ht;
        this.date = date;
        this.expert = expert;
        this.mecanicien = mecanicien;
        this.total_ht = total_ht;
    }

    public Devis(Date date, Mecanicien mecanicien, Expert expert, List<DevisItem> devisItems) {
        this.date = date;
        this.mecanicien = mecanicien;
        this.expert = expert;
        this.devisItems = devisItems;
        this.total_ht = calculateTotalHT();
    }

    public Devis(float total_ht, Date date, Expert expert, Mecanicien mecanicien) {
        this.date = date;
        this.expert = expert;
        this.mecanicien = mecanicien;
    }

    public Devis(float total_ht, Expert expert, Mecanicien mecanicien) {
        this.total_ht = total_ht;
        this.expert = expert;
        this.mecanicien = mecanicien;
    }

    public Devis(int id, float total_ht, Expert expert, Mecanicien mecanicien) {
        this.id = id;
        this.total_ht = total_ht;
        this.expert = expert;
        this.mecanicien = mecanicien;
    }

    public Devis() {
        this.devisItems = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
       this.id = id;
    }
     public static void setIdDevis(int i) {
        Devis.id=i;
    }



    public Expert getExpert() {
        return expert;
    }

    public Mecanicien getMecanicien() {
        return mecanicien;
    }

    public void setExpert(Expert expert) {
        this.expert = expert;
    }

    public void setMecanicien(Mecanicien mecanicien) {
        this.mecanicien = mecanicien;
    }

    public List<DevisItem> getDevisItems() {
        return devisItems;
    }

    public void addDevisItem(DevisItem devisItem) {
        this.devisItems.add(devisItem);
    }

    public void setDevisItems(List<DevisItem> devisItems) {
        this.devisItems = devisItems;
    }
    

    public float getTotal_ht() {
        return total_ht;
    }

    public void setTotal_ht(float total_ht) {
        this.total_ht = total_ht;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Devis{" + "id=" + id + ", total_ht=" + total_ht + ", date=" + date + ", expert=" + expert + ", mecanicien=" + mecanicien + ", devisItems=" + devisItems + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + this.id;
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
        final Devis other = (Devis) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    public float calculateTotalHT() {
        float total = 0;
        for (DevisItem item : devisItems) {
            total += item.getTotalprice();
        }
        return total;
    }

    
     public List<DevisItem> getDevisItems(int id) throws SQLException{
         ServiceDevis sd =new ServiceDevis();
        return  sd.getDevisItems(id);
     }

    public String getNomM() {
        return nomM;
    }

    public void setNomM(String nomM) {
        this.nomM = nomM;
    }

    public String getNomE() {
        return nomE;
    }

    public void setNomE(String nomE) {
        this.nomE = nomE;
    }

 
    
    

}
