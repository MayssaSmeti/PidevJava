package entities;

public class Contrat {
    private int id;
    private String validitedu;
    private String validiteau;
    private int id_vehicule;
    private int id_client;
    private String photo_cin;

    public static int actionTest;

    private static int IdContrat;

    public static int getIdContrat() {
        return IdContrat;
    }

    public static void setIdContrat(int idContrat) {
        IdContrat = idContrat;
    }

    public Contrat(int id, String validitedu, String validiteau, int id_vehicule, int id_client, String photo_cin) {
        this.id = id;
        this.validitedu = validitedu;
        this.validiteau = validiteau;
        this.id_vehicule = id_vehicule;
        this.id_client = id_client;
        this.photo_cin = photo_cin;
    }

    public Contrat() {
    }

    public Contrat(String validitedu, String validiteau, int id_vehicule, int id_client, String photo_cin) {
        this.validitedu = validitedu;
        this.validiteau = validiteau;
        this.id_vehicule = id_vehicule;
        this.id_client = id_client;
        this.photo_cin = photo_cin;
    }

    public Contrat(String validitedu, String validiteau, String photo_cin) {
        this.validitedu = validitedu;
        this.validiteau = validiteau;
        this.photo_cin = photo_cin;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getValiditedu() {
        return validitedu;
    }

    public void setValiditedu(String validitedu) {
        this.validitedu = validitedu;
    }

    public String getValiditeau() {
        return validiteau;
    }

    public void setValiditeau(String validiteau) {
        this.validiteau = validiteau;
    }

    public int getId_vehicule() {
        return id_vehicule;
    }

    public void setId_vehicule(int id_vehicule) {
        this.id_vehicule = id_vehicule;
    }

    public int getId_client() {
        return id_client;
    }

    public void setId_client(int id_client) {
        this.id_client = id_client;
    }

    public String getPhoto_cin() {
        return photo_cin;
    }

    public void setPhoto_cin(String photo_cin) {
        this.photo_cin = photo_cin;
    }

   
    

    @Override
    public String toString() {
        return "Contrat{" + "id=" + id + ", validitedu=" + validitedu + ", validiteau=" + validiteau + ", id_vehicule=" + id_vehicule + ", id_client=" + id_client + ", photo_cin=" + photo_cin + '}';
    }
}
