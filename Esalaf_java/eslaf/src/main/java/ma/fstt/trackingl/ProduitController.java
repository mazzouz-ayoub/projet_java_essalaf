package ma.fstt.trackingl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import ma.fstt.model.Produit;
import ma.fstt.model.ProduitDAO;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ProduitController implements Initializable {


    @FXML
    private TextField nom ;


    @FXML
    private TextField prix ;

    @FXML
    private TextField description ;


    @FXML
    private TableView<Produit> mytable ;


    @FXML
    private TableColumn<Produit ,Long> col_id ;

    @FXML
    private TableColumn <Produit ,String> col_nom ;

    @FXML
    private TableColumn <Produit ,String> col_prix ;

    @FXML
    private TableColumn <Produit ,String> col_description ;


    @FXML
    protected void onSaveButtonClick() {

        // accees a la bdd

        try {
            ProduitDAO produitDAO = new ProduitDAO();

            Produit prod = new Produit(0l , nom.getText() , prix.getText() , description.getText());

            produitDAO.save(prod);


            UpdateTable();




        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }


    @FXML
    private void onDeleteButtonClick() {

        Produit selectedProduit = mytable.getSelectionModel().getSelectedItem();
        if (selectedProduit == null) {
            // No item selected, display error message
            System.out.println("Error: no item selected for deletion");
            return;
        }
        try {
            ProduitDAO produitDAO = new ProduitDAO();
            produitDAO.delete(selectedProduit);
            UpdateTable();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    protected void onUpdateButtonClick() {

        Produit selecteProduit = mytable.getSelectionModel().getSelectedItem();

        if (selecteProduit == null) {
            // No item selected, display error message
            System.out.println("Error: no item selected for update");
            return;
        }

        try {
            ProduitDAO produitDAO = new ProduitDAO();

            // Update the client  object with the new data
            selecteProduit.setNom_produit(nom.getText());
            selecteProduit.setPrix(prix.getText());
            selecteProduit.setDescription(description.getText());

            produitDAO.update(selecteProduit);

            UpdateTable();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void UpdateTable(){
        col_id.setCellValueFactory(new PropertyValueFactory<Produit,Long>("id_produit"));
        col_nom.setCellValueFactory(new PropertyValueFactory<Produit,String>("nom_produit"));

        col_prix.setCellValueFactory(new PropertyValueFactory<Produit,String>("prix"));
        col_description.setCellValueFactory(new PropertyValueFactory<Produit,String>("description"));

        mytable.setItems(this.getDataProduits());
    }

    public static ObservableList<Produit> getDataProduits(){

        ProduitDAO produitDAO = null;

        ObservableList<Produit> listfx = FXCollections.observableArrayList();

        try {
            produitDAO = new ProduitDAO();
            for (Produit ettemp : produitDAO.getAll())
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