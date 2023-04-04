package ma.fstt.model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClientDAO extends BaseDAO<Client>{
    public ClientDAO() throws SQLException {

        super();
    }

    @Override
    public void save(Client object) throws SQLException {

        String request = "insert into client (nom , telephone) values (? , ?)";

        // mapping objet table

        this.preparedStatement = this.connection.prepareStatement(request);
        // mapping
        this.preparedStatement.setString(1 , object.getNom());

        this.preparedStatement.setString(2 , object.getTelephone());


        this.preparedStatement.execute();
    }

    @Override
    public void update(Client object) throws SQLException {
        String request = "UPDATE client SET nom = ?, telephone = ? WHERE id_client = ?";

        this.preparedStatement = this.connection.prepareStatement(request);

        this.preparedStatement.setString(1 , object.getNom());

        this.preparedStatement.setString(2 , object.getTelephone());

        this.preparedStatement.setLong(3 , object.getId_client());

        this.preparedStatement.executeUpdate();

        this.preparedStatement.close();

    }

    @Override
    public void delete(Client object) throws SQLException {
        String req = "DELETE FROM client WHERE id_client = ?;";

        this.preparedStatement = this.connection.prepareStatement(req);
        this.preparedStatement.setLong(1, object.getId_client());
        this.preparedStatement.execute();
    }

    @Override
    public List<Client> getAll()  throws SQLException {

        List<Client> mylist = new ArrayList<Client>();

        String request = "select * from client ";

        this.statement = this.connection.createStatement();

        this.resultSet =   this.statement.executeQuery(request);

// parcours de la table
        while ( this.resultSet.next()){

// mapping table objet
            mylist.add(new Client(this.resultSet.getLong(1) ,
                    this.resultSet.getString(2) , this.resultSet.getString(3)));
        }

        return mylist;
    }

    @Override
    public Client getOne(Long id_client) throws SQLException {

        return null;
    }

}
