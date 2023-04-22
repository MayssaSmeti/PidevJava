package entities;

public class Offre {
    private int id;
    private String description;
    private int prix;
    private String titre;
    private String validite_offre;
    private String image_offre;

    public static int actionTest;

    private static int IdOffre;


    public static int getIdOffre() {
        return IdOffre;
    }


    public static void setIdOffre(int idOffre) {
        IdOffre = idOffre;
    }


    public Offre() {
    }

    
    public Offre(int id, String description, int prix, String titre, String validite_offre, String image_offre) {
        this.id = id;
        this.description = description;
        this.prix = prix;
        this.titre = titre;
        this.validite_offre = validite_offre;
        this.image_offre = image_offre;
    }

    public Offre(String description, int prix, String titre, String validite_offre, String image_offre) {
        this.description = description;
        this.prix = prix;
        this.titre = titre;
        this.validite_offre = validite_offre;
        this.image_offre = image_offre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getValidite_offre() {
        return validite_offre;
    }

    public void setValidite_offre(String validite_offre) {
        this.validite_offre = validite_offre;
    }

    public String getImage_offre() {
        return image_offre;
    }

    public void setImage_offre(String image_offre) {
        this.image_offre = image_offre;
    }

    @Override
    public String toString() {
        return "Offre{" + "id=" + id + ", description=" + description + ", prix=" + prix + ", titre=" + titre + ", validite_offre=" + validite_offre + ", image_offre=" + image_offre + '}';
    }
}
