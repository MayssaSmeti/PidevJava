/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pidev.Entities;

/**
 *
 * @author SCORPIO-12
 */
public class DevisItem {
    private int id,qte;
    private String description;
    private double unitprice,totalprice;    
    private Devis devis;

    public DevisItem(int id, int qte, String description, float unitprice, float totalprice, Devis devis) {
        this.id = id;
        this.qte = qte;
        this.description = description;
        this.unitprice = unitprice;
        this.totalprice = totalprice;
        this.devis = devis;
    }

    public DevisItem(String description,int qte,  double unitprice, double totalprice) {
        this.qte = qte;
        this.description = description;
        this.unitprice = unitprice;
        this.totalprice = totalprice;
    }

    public DevisItem() {
    }

    public DevisItem(String newItem, double newPrice, int newQuantity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQte() {
        return qte;
    }

    public void setQte(int qte) {
        this.qte = qte;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getUnitprice() {
        return unitprice;
    }

    public void setUnitprice(float unitprice) {
        this.unitprice = unitprice;
    }

    public double getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(float totalprice) {
        this.totalprice = totalprice;
    }

    public Devis getDevis() {
        return devis;
    }

    public void setDevis(Devis devis) {
        this.devis = devis;
    }

    @Override
    public String toString() {
        return "DevisItem{" + "id=" + id + ", qte=" + qte + ", description=" + description + ", unitprice=" + unitprice + ", totalprice=" + totalprice + ", devis=" + devis + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + this.id;
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
        final DevisItem other = (DevisItem) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
    
    
    
}
