package service;
import entities.Offre;
import utils.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class OffreService implements IOffreService{
    Connection cnx = MyConnection.getInstance().getCnx();
    
        public void ajouter(Offre o) {
        try {
            String requete = "INSERT INTO `offre` (`description`, `prix`, `titre`, `validite_offre`, `image_offre`) VALUES (?,?,?,?,?)";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setString(1, o.getDescription());
            pst.setInt(2, o.getPrix());
            pst.setString(3, o.getTitre());
            pst.setString(4, o.getValidite_offre());
            pst.setString(5, o.getImage_offre());
            pst.executeUpdate();
            System.out.println("Votre offre est ajouté");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
}
         public void supprimer(int id) {
        try {
            String req = "DELETE FROM `offre` WHERE id = " + id;
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Offre est supprimé !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void modifier(Offre o) {
        //o = new Offre(8031, "updated", 150, "titre", "valid" ,"sac.png");
        try {
            String req = "UPDATE `offre` SET `description` = '" +o.getDescription() + "', `prix` = '" + o.getPrix() + "', `titre` = '" + o.getTitre() + "', `validite_offre` = '" + o.getValidite_offre() + "', `image_offre` = '" + o.getImage_offre() + "' WHERE `offre`.`id` = " + o.getId();
            //pst.setInt(6, (int) o.getId());
             Statement pst = cnx.createStatement();
            pst.executeUpdate(req);
            System.out.println("Votre offre est  modifié avec succe");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
         
        public List<Offre> getAll(){
       List<Offre> myList = new ArrayList<>();  
       try {
             String req3="SELECT * FROM offre";
             Statement st = cnx.createStatement(); 
             ResultSet rs = st.executeQuery(req3);
             while(rs.next()){
                 Offre o =new Offre();
                 o.setId(rs.getInt(1));
                 o.setDescription(rs.getString("description"));
                 o.setPrix(rs.getInt("prix"));
                 o.setTitre(rs.getString("titre"));
                 o.setValidite_offre(rs.getString("validite_offre"));
                 o.setImage_offre(rs.getString("image_offre"));
                 myList.add(o);
             }
         } catch (SQLException ex) {
             System.err.println(ex.getMessage());
         }
         return myList;
}
    public Offre getOneOffre(int idOffre) throws SQLException {
        String req = "SELECT * FROM `offre` where id = ?";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setInt(1, idOffre);

        ResultSet rs = ps.executeQuery();
        Offre o =new Offre();
        
        while (rs.next()) {
        
                    o.setId(rs.getInt(1));
                    o.setDescription(rs.getString("description"));
                    o.setPrix(rs.getInt("prix"));
                    o.setTitre(rs.getString("titre"));
                    o.setValidite_offre(rs.getString("validite_offre"));
                    o.setImage_offre(rs.getString("image_offre"));
        }
        ps.close();
        return o;
    }

}
