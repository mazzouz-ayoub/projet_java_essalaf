package ma.fstt.trackingl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import ma.fstt.model.Client;
import ma.fstt.model.ClientDAO;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class HelloController implements Initializable {


    @FXML
    private TextField nom ;


    @FXML
    private TextField tele ;

    @FXML
    private TextField nomm ;


    @FXML
    private TableView<Client> mytable ;


    @FXML
    private TableColumn<Client ,Long> col_id ;

    @FXML
    private TableColumn <Client ,String> col_nom ;

    @FXML
    private TableColumn <Client ,String> col_tele ;


    @FXML
    protected void onSaveButtonClick() {

        // accees a la bdd

        try {
            ClientDAO clientDAO = new ClientDAO();

            Client cli = new Client(0l , nom.getText() , tele.getText());
            clientDAO.save(cli);

            UpdateTable();




        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    @FXML
    private Button search;

    @FXML
    void onSearchButtonClick(ActionEvent event) {
        onSearchButtonClick();
    }

    @FXML
    protected void onSearchButtonClick() {
        String searchName = nomm.getText();

        try {
            ClientDAO clientDAO = new ClientDAO();
            ObservableList<Client> livreurList = FXCollections.observableArrayList();
            for (Client ettemp : clientDAO.getAll()) {
                if (ettemp.getNom().equals(searchName)) {
                    livreurList.add(ettemp);

                }
            }
            mytable.setItems(livreurList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



    @FXML
    private Button button;
    @FXML
    void AfficherAccueil(ActionEvent event) {

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("accueil-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 672, 600);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Welcome to Tracking Des Livreurs");
            stage.show();


        }catch (Exception e) {
            // TODO: handle exception e.printStackTrace();
        }
    }

    @FXML
    private void onDeleteButtonClick() {
        Client selectedLivreur = mytable.getSelectionModel().getSelectedItem();
        if (selectedLivreur == null) {
            // No item selected, display error message
            System.out.println("Error: no item selected for deletion");
            return;
        }
        try {
            ClientDAO clientDAO = new ClientDAO();
            clientDAO.delete(selectedLivreur);
            UpdateTable();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    protected void onUpdateButtonClick() {

        Client selecteClient = mytable.getSelectionModel().getSelectedItem();

        if (selecteClient == null) {
            // No item selected, display error message
            System.out.println("Error: no item selected for update");
            return;
        }

        try {
            ClientDAO clientDAO = new ClientDAO();

            // Update the client  object with the new data
            selecteClient.setNom(nom.getText());
            selecteClient.setTelephone(tele.getText());

            clientDAO.update(selecteClient);

            UpdateTable();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void UpdateTable(){
        col_id.setCellValueFactory(new PropertyValueFactory<Client,Long>("id_client"));
        col_nom.setCellValueFactory(new PropertyValueFactory<Client,String>("nom"));

        col_tele.setCellValueFactory(new PropertyValueFactory<Client,String>("telephone"));

        mytable.setItems(this.getDataClients());
    }

    public static ObservableList<Client> getDataClients(){

        ClientDAO clientDAO = null;

        ObservableList<Client> listfx = FXCollections.observableArrayList();

        try {
            clientDAO = new ClientDAO();
            for (Client ettemp : clientDAO.getAll())
                listfx.add(ettemp);

        }catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return listfx ;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        UpdateTable();

    }
}