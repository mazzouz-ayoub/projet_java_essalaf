package ma.fstt.trackingl;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class AccueilController {

    @FXML
    private Button button;
    @FXML
    void AfficherLivreur(ActionEvent event) {

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 672, 600);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Gestion Des Livreurs");
            stage.show();



            }catch (Exception e) {
               // TODO: handle exception e.printStackTrace();
                }
            }
    @FXML
    private Button buttonp;

    @FXML
    void AfficherProduit(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("produit-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 672, 600);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Gestion Des produits");
            stage.show();



        }catch (Exception e) {
            // TODO: handle exception e.printStackTrace();
        }
    }

    @FXML
    private Button buttonc;

    @FXML
    void AfficherCommande(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("commande-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 672, 600);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Gestion Des produits");
            stage.show();



        }catch (Exception e) {
            // TODO: handle exception e.printStackTrace();
        }
    }

}