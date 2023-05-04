/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pidev.Entities;

/**
 *
 * @author USER
 */
public class Constat {
    
    private int id;
    private User expert;
    private Vehicule vehicule;
    private String nomclient_e,prenomclient_e,typevehicule_e,marquevehicule_e,assuranceclient_e,adresseclient_e,emplacementaccid,photoaccid,descriptiondegat,observations,numcontrat_e,mail,date_creation;

    public Constat() {
    }

    public Constat(int id) {
        this.id = id;
    }

    
    public Constat(int id,String nomclient_e, String prenomclient_e, String typevehicule_e, String marquevehicule_e, String assuranceclient_e, String adresseclient_e, String emplacementaccid, String photoaccid, String descriptiondegat, String observations, String numcontrat_e, String mail, String date_creation,User expert,Vehicule vehicule) {
        this.id = id;
        this.nomclient_e = nomclient_e;
        this.prenomclient_e = prenomclient_e;
        this.typevehicule_e = typevehicule_e;
        this.marquevehicule_e = marquevehicule_e;
        this.assuranceclient_e = assuranceclient_e;
        this.adresseclient_e = adresseclient_e;
        this.emplacementaccid = emplacementaccid;
        this.photoaccid = photoaccid;
        this.descriptiondegat = descriptiondegat;
        this.observations = observations;
        this.numcontrat_e = numcontrat_e;
        this.mail = mail;
        this.date_creation = date_creation;
        this.expert = expert;
        this.vehicule = vehicule;
    }

    public Constat(String nomclient_e, String prenomclient_e, String typevehicule_e, String marquevehicule_e, String assuranceclient_e, String adresseclient_e, String emplacementaccid, String photoaccid, String descriptiondegat, String observations, String numcontrat_e, String mail, String date_creation,User expert,Vehicule vehicule) {
        this.nomclient_e = nomclient_e;
        this.prenomclient_e = prenomclient_e;
        this.typevehicule_e = typevehicule_e;
        this.marquevehicule_e = marquevehicule_e;
        this.assuranceclient_e = assuranceclient_e;
        this.adresseclient_e = adresseclient_e;
        this.emplacementaccid = emplacementaccid;
        this.photoaccid = photoaccid;
        this.descriptiondegat = descriptiondegat;
        this.observations = observations;
        this.numcontrat_e = numcontrat_e;
        this.mail = mail;
        this.date_creation = date_creation;
        this.expert = expert;
        this.vehicule = vehicule;
    }

     public Constat(String nomclient_e, String prenomclient_e, String typevehicule_e, String marquevehicule_e, String assuranceclient_e, String adresseclient_e, String emplacementaccid, String photoaccid, String descriptiondegat, String observations, String numcontrat_e, String mail, String date_creation) {
        this.nomclient_e = nomclient_e;
        this.prenomclient_e = prenomclient_e;
        this.typevehicule_e = typevehicule_e;
        this.marquevehicule_e = marquevehicule_e;
        this.assuranceclient_e = assuranceclient_e;
        this.adresseclient_e = adresseclient_e;
        this.emplacementaccid = emplacementaccid;
        this.photoaccid = photoaccid;
        this.descriptiondegat = descriptiondegat;
        this.observations = observations;
        this.numcontrat_e = numcontrat_e;
        this.mail = mail;
        this.date_creation = date_creation;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomclient_e() {
        return nomclient_e;
    }

    public void setNomclient_e(String nomclient_e) {
        this.nomclient_e = nomclient_e;
    }

    public String getPrenomclient_e() {
        return prenomclient_e;
    }

    public void setPrenomclient_e(String prenomclient_e) {
        this.prenomclient_e = prenomclient_e;
    }

    public String getTypevehicule_e() {
        return typevehicule_e;
    }

    public void setTypevehicule_e(String typevehicule_e) {
        this.typevehicule_e = typevehicule_e;
    }

    public String getMarquevehicule_e() {
        return marquevehicule_e;
    }

    public void setMarquevehicule_e(String marquevehicule_e) {
        this.marquevehicule_e = marquevehicule_e;
    }

    public String getAssuranceclient_e() {
        return assuranceclient_e;
    }

    public void setAssuranceclient_e(String assuranceclient_e) {
        this.assuranceclient_e = assuranceclient_e;
    }

    public String getAdresseclient_e() {
        return adresseclient_e;
    }

    public void setAdresseclient_e(String adresseclient_e) {
        this.adresseclient_e = adresseclient_e;
    }

    public String getEmplacementaccid() {
        return emplacementaccid;
    }

    public void setEmplacementaccid(String emplacementaccid) {
        this.emplacementaccid = emplacementaccid;
    }

    public String getPhotoaccid() {
        return photoaccid;
    }

    public void setPhotoaccid(String photoaccid) {
        this.photoaccid = photoaccid;
    }

    public String getDescriptiondegat() {
        return descriptiondegat;
    }

    public void setDescriptiondegat(String descriptiondegat) {
        this.descriptiondegat = descriptiondegat;
    }

    public String getObservations() {
        return observations;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }

    public String getNumcontrat_e() {
        return numcontrat_e;
    }

    public void setNumcontrat_e(String numcontrat_e) {
        this.numcontrat_e = numcontrat_e;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getDate_creation() {
        return date_creation;
    }

    public void setDate_creation(String date_creation) {
        this.date_creation = date_creation;
    }

    public User getExpert() {
        return expert;
    }

    public void setExpert(User expert) {
        this.expert = expert;
    }

    public Vehicule getVehicule() {
        return vehicule;
    }

    public void setVehicule(Vehicule vehicule) {
        this.vehicule = vehicule;
    }

    @Override
    public String toString() {
        return "Constat{" + "id=" + id + ", expert=" + expert + ", vehicule=" + vehicule + ", nomclient_e=" + nomclient_e + ", prenomclient_e=" + prenomclient_e + ", typevehicule_e=" + typevehicule_e + ", marquevehicule_e=" + marquevehicule_e + ", assuranceclient_e=" + assuranceclient_e + ", adresseclient_e=" + adresseclient_e + ", emplacementaccid=" + emplacementaccid + ", photoaccid=" + photoaccid + ", descriptiondegat=" + descriptiondegat + ", observations=" + observations + ", numcontrat_e=" + numcontrat_e + ", mail=" + mail + ", date_creation=" + date_creation + '}';
    }
    
}
