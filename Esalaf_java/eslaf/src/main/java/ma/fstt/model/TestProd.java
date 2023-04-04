package ma.fstt.model;

import java.sql.SQLException;
import java.util.List;

public class TestProd {

    public static void main(String[] args) {
// trait bloc try catch
        try {

            ProduitDAO produitDAO = new ProduitDAO();
            Produit prod = new Produit(0l , "prod" , "200" , "telephone");
            produitDAO.save(prod);

            Produit produit = new Produit(12l, "prod1_modifié", "300", "pc");
            produitDAO.update(produit);

            Produit produit1 = new Produit(16l, "prod16_modifié", "900", "teele");
            produitDAO.update(produit1);

            Produit produit01 = new Produit(1l, "produit01", "065", "telephone");
            produitDAO.update(produit01);

            Produit produit11 = new Produit(1l, "produit1", "615" , "tv");

            produitDAO.delete(produit11);


           /*List<Produit> livlist =  produitDAO.getAll();

            for (Produit prod :livlist) {

                System.out.println(prod.toString());
            }*/

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

}
