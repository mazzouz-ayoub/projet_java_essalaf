package ma.fstt.model;

import javafx.scene.control.Alert;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



        import java.sql.SQLException;
        import java.util.ArrayList;
        import java.util.List;

public class CreditDAO extends BaseDAO<Credit>{
    public CreditDAO() throws SQLException {

        super();
    }

    @Override
    public void save(Credit object) throws SQLException {

        // Check if the product exists
        String checkProductQuery = "SELECT COUNT(*) FROM produit WHERE id_produit = ?";
        PreparedStatement checkProductStatement = this.connection.prepareStatement(checkProductQuery);
        checkProductStatement.setString(1, object.getNom_produit());
        ResultSet resultSet = checkProductStatement.executeQuery();
        resultSet.next();
        int productCount = resultSet.getInt(1);

        if (productCount == 0) {
            // Product does not exist, display an alert
            Alert alert = new Alert(Alert.AlertType.ERROR, "le produit est out solde ");
            alert.showAndWait();

        }

        // Product exists, insert the command into the database
        String insertCreditQuery = "INSERT INTO credit (nom_produit, nom_client, datedebut, montant) VALUES (?, ?, ?, ?)";
        this.preparedStatement = this.connection.prepareStatement(insertCreditQuery);
        // mapping
        this.preparedStatement.setString(1, object.getNom_produit());
        this.preparedStatement.setString(2, object.getNom_client());
        this.preparedStatement.setString(3, object.getDatedebut());
        this.preparedStatement.setString(4, object.getMontant());

        this.preparedStatement.execute();
    }

    @Override
    public void update(Credit object) throws SQLException {
        String request = "UPDATE credit SET nom_produit = ?, nom_client = ?, datedebut = ?, montant = ? WHERE id_credit = ?";

        this.preparedStatement = this.connection.prepareStatement(request);

        this.preparedStatement.setString(1, object.getNom_produit());
        this.preparedStatement.setString(2, object.getNom_client());
        this.preparedStatement.setString(3, object.getDatedebut());
        this.preparedStatement.setString(4, object.getMontant());
        this.preparedStatement.setLong(5, object.getId_credit());

        this.preparedStatement.executeUpdate();

        this.preparedStatement.close();
    }


    @Override
    public void delete(Credit object) throws SQLException {
        String req = "DELETE FROM credit WHERE id_credit = ?;";

        this.preparedStatement = this.connection.prepareStatement(req);
        this.preparedStatement.setLong(1, object.getId_credit());
        this.preparedStatement.execute();
    }

    @Override
    public  List<Credit> getAll()  throws SQLException {

        List<Credit> mylist = new ArrayList<Credit>();

        String request = "select c.id_credit, p.nom_produit, l.nom , c.datedebut, c.montant from credit c, produit p, client l where c.nom_produit = p.id_produit and c.nom_client = l.id_client";


        this.statement = this.connection.createStatement();

        this.resultSet =   this.statement.executeQuery(request);

// parcours de la table
        while ( this.resultSet.next()){

// mapping table objet
            mylist.add(new Credit(this.resultSet.getLong(1) ,
                    this.resultSet.getString(2) , this.resultSet.getString(3) , this.resultSet.getString(4) , this.resultSet.getString(5)));
        }

        return mylist;
    }

    @Override
    public Credit getOne(Long id_commande) throws SQLException {

        return null;
    }

}
