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
import ma.fstt.model.Credit;
import ma.fstt.model.CreditDAO;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class CreditController implements Initializable {


    @FXML
    private TextField nom_produit ;


    @FXML
    private TextField nom_client ;

    @FXML
    private TextField datedebut ;

    @FXML
    private TextField montant ;

    @FXML
    private TextField nomm ;


    @FXML
    private TableView<Credit> mytable ;


    @FXML
    private TableColumn<Credit ,Long> col_id ;

    @FXML
    private TableColumn <Credit ,Long> col_produit ;

    @FXML
    private TableColumn <Credit ,Long> col_client ;

    @FXML
    private TableColumn <Credit ,String> col_debut ;

    @FXML
    private TableColumn <Credit ,String> col_mon ;

    public CreditController() {
    }


    @FXML
    protected void onSaveButtonClick() {
        Credit newCredit = new Credit();
        newCredit.setNom_produit(String.valueOf(nom_produit.getText()));
        newCredit.setNom_client(String.valueOf(nom_client.getText()));
        newCredit.setDatedebut(datedebut.getText());
        newCredit.setMontant(montant.getText());

        try {
            CreditDAO commandeDAO = new CreditDAO();
            commandeDAO.save(newCredit);
            UpdateTable();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    private Button search;




    @FXML
    protected void onSearchButtonClick(ActionEvent event) {
        String searchID = nomm.getText();

        try {
            CreditDAO creditDAO = new CreditDAO();
            ObservableList<Credit> commandeList = FXCollections.observableArrayList();
            for (Credit ettemp : creditDAO.getAll()) {
                if (ettemp.getId_credit().equals(searchID)) {
                    commandeList.add(ettemp);
                }
            }
            mytable.setItems(commandeList);
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
        Credit selectedCredit = mytable.getSelectionModel().getSelectedItem();
        if (selectedCredit == null) {
            // No item selected, display error message
            System.out.println("Error: no item selected for deletion");
            return;
        }
        try {
            CreditDAO creditDAO = new CreditDAO();
            creditDAO.delete(selectedCredit);
            UpdateTable();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    protected void onUpdateButtonClick() {
        // Get the currently selected Commande from the table
        Credit selectedCommande = mytable.getSelectionModel().getSelectedItem();
        if (selectedCommande == null) {
            // No item selected, display error message
            System.out.println("Error: no item selected for update");
            return;
        }

        // Update the fields of the selected Commande with the values entered in the input fields
        selectedCommande.setNom_produit(String.valueOf(nom_produit.getText()));
        selectedCommande.setNom_client(String.valueOf(nom_client.getText()));
        selectedCommande.setDatedebut(datedebut.getText());
        selectedCommande.setMontant(montant.getText());

        try {
            CreditDAO commandeDAO = new CreditDAO();
            commandeDAO.update(selectedCommande);
            UpdateTable();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void UpdateTable(){
        col_id.setCellValueFactory(new PropertyValueFactory<>("id_commande"));
        col_produit.setCellValueFactory(new PropertyValueFactory<>("nom_produit"));
        col_client.setCellValueFactory(new PropertyValueFactory<>("nom_client"));
        col_debut.setCellValueFactory(new PropertyValueFactory<>("datedebut"));
        col_mon.setCellValueFactory(new PropertyValueFactory<>("montant"));

        mytable.setItems(getDataCredits());
    }

    public static ObservableList<Credit> getDataCredits(){

        CreditDAO creditDAO = null;

        ObservableList<Credit> listfx = FXCollections.observableArrayList();

        try {
            creditDAO = new CreditDAO();
            for (Credit ettemp : creditDAO.getAll())
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