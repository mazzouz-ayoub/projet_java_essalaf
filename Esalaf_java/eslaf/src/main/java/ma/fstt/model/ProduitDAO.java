package ma.fstt.model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProduitDAO extends BaseDAO<Produit>{
    public ProduitDAO() throws SQLException {

        super();
    }

    @Override
    public void save(Produit object) throws SQLException {

        String request = "insert into produit (nom_produit , prix , description) values (? , ? , ?)";

        // mapping objet table

        this.preparedStatement = this.connection.prepareStatement(request);
        // mapping
        this.preparedStatement.setString(1 , object.getNom_produit());

        this.preparedStatement.setString(2 , object.getPrix());

        this.preparedStatement.setString(3 , object.getDescription());



        this.preparedStatement.execute();
    }

    @Override
    public void update(Produit object) throws SQLException {
        String request = "UPDATE produit SET nom_produit = ?, prix = ? , description = ? WHERE id_produit = ?";

        this.preparedStatement = this.connection.prepareStatement(request);

        this.preparedStatement.setString(1 , object.getNom_produit());

        this.preparedStatement.setString(2 , object.getPrix());
        this.preparedStatement.setString(3 , object.getDescription());

        this.preparedStatement.setLong(4 , object.getId_produit());

        this.preparedStatement.executeUpdate();

        this.preparedStatement.close();

    }

    @Override
    public void delete(Produit object) throws SQLException {
        String req = "DELETE FROM produit WHERE id_produit = ?;";

        this.preparedStatement = this.connection.prepareStatement(req);
        this.preparedStatement.setLong(1, object.getId_produit());
        this.preparedStatement.execute();
    }

    @Override
    public List<Produit> getAll()  throws SQLException {

        List<Produit> mylist = new ArrayList<Produit>();

        String request = "select * from produit ";

        this.statement = this.connection.createStatement();

        this.resultSet =   this.statement.executeQuery(request);

// parcours de la table
        while ( this.resultSet.next()){

// mapping table objet
            mylist.add(new Produit(this.resultSet.getLong(1) ,
                    this.resultSet.getString(2) , this.resultSet.getString(3) , this.resultSet.getString(4)));
        }

        return mylist;
    }

    @Override
    public Produit getOne(Long id_produit) throws SQLException {

        return null;
    }

}
