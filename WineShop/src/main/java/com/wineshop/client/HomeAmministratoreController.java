package com.wineshop.client;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import utilities.models.Utente;
import utilities.Costanti;
import utilities.Response;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
public class HomeAmministratoreController {
    private RequestController requestController;
    private Utente UtenteLoggato;

    @FXML
    private AnchorPane AnchorPane_Home;

    @FXML
    private AnchorPane AnchorPane_Ordini;

    @FXML
    private AnchorPane AnchorPane_Profilo;

    @FXML
    private AnchorPane AnchorPane_Ricerca;

    @FXML
    private ComboBox<?> CBQuantita_Vino;

    @FXML
    private TableColumn<?, ?> ID_Ordini;

    @FXML
    private TableColumn<?, ?> Indirizzo_Ordini;

    @FXML
    private TableColumn<?, ?> MP_Ordini;

    @FXML
    private TableView<?> TV_Ordini;

    @FXML
    private TableView<?> TV_Ricerca;

    @FXML
    private TableColumn<?, ?> Totale_Ordini;

    @FXML
    private Button btnAddCart_Vino;

    @FXML
    private Button btnAddPreferiti_Vino;

    @FXML
    private Button btnHome_Amministratore;

    @FXML
    private Button btnLogout_Amministratore;

    @FXML
    private Button btnOrdini_Cliente;

    @FXML
    private Button btnOrdini_Cliente1;

    @FXML
    private Button btnProfilo_Amministratore;

    @FXML
    private Button btnRicerca_Amministratore;

    @FXML
    private Button btnRicerca_Ricerca;

    @FXML
    private Label lblAnno_Vino;

    @FXML
    private Label lblCF_Profilo;

    @FXML
    private Label lblCognome_Profilo;

    @FXML
    private Label lblDescrizione_Vino;

    @FXML
    private Label lblEmail_Profilo;

    @FXML
    private Label lblIndirizzo_Profilo;

    @FXML
    private Label lblNome_Profilo;

    @FXML
    private Label lblNome_Vino;

    @FXML
    private Label lblPrezzo_Vino;

    @FXML
    private Label lblProvenienza_Vino;

    @FXML
    private Label lblQuantita_Vino;

    @FXML
    private Label lblSoglia_Vino;

    @FXML
    private Label lblTelefono_Profilo;

    @FXML
    private Label lblUsername_Profilo;

    @FXML
    private Label lblVitigno_Vino;

    @FXML
    private TextField txtRicerca_Ricerca;

    @FXML
    void OnBtnHomeAmministratore_Click(ActionEvent event) {
        AnchorPane_Home.setVisible(true);
        AnchorPane_Ricerca.setVisible(false);
        AnchorPane_Ordini.setVisible(false);
        AnchorPane_Profilo.setVisible(false);
    }

    @FXML
    void OnBtnLogoutAmministratore_Click(ActionEvent event) throws Exception {
        System.out.println("Effettuando il Logout");

        Response response = this.requestController.makeRequest(Costanti.Logout, UtenteLoggato);

        if(response.getStatusCode() != Costanti.Successo)
        {
            System.out.println("Logout errato!");
            return;
        } else if (response.getStatusCode() == Costanti.Successo){
            System.out.println("Logout avvenuto con successo!");
            setLoggedUser(null);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
            Parent LogGui = loader.load();
            Object controller = loader.getController();
            Scene scene = new Scene(LogGui, 346, 600);
            ((LoginController) controller).setRequestController(this.requestController);
            ((LoginController) controller).setUtenteLoggato(UtenteLoggato);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.show();

        }
    }

    @FXML
    void OnBtnOrdiniAmministratore_Click(ActionEvent event) {
        AnchorPane_Home.setVisible(false);
        AnchorPane_Ricerca.setVisible(false);
        AnchorPane_Ordini.setVisible(true);
        AnchorPane_Profilo.setVisible(false);
    }

    @FXML
    void OnBtnProfiloAmministratore_Click(ActionEvent event) {
        AnchorPane_Home.setVisible(false);
        AnchorPane_Ricerca.setVisible(false);
        AnchorPane_Ordini.setVisible(false);
        AnchorPane_Profilo.setVisible(true);

        lblNome_Profilo.setText(UtenteLoggato.getNome());
        lblCognome_Profilo.setText(UtenteLoggato.getCognome());
        lblUsername_Profilo.setText(UtenteLoggato.getUsername());
        lblCF_Profilo.setText(UtenteLoggato.getCf());
        lblEmail_Profilo.setText(UtenteLoggato.getEmail());
        lblTelefono_Profilo.setText(UtenteLoggato.getTelefono());
        lblIndirizzo_Profilo.setText(UtenteLoggato.getIndirizzo());
    }

    @FXML
    void OnBtnRicercaAmministratore_Click(ActionEvent event) {
        AnchorPane_Home.setVisible(false);
        AnchorPane_Ricerca.setVisible(true);
        AnchorPane_Ordini.setVisible(false);
        AnchorPane_Profilo.setVisible(false);
    }

    public void setRequestController(RequestController controller) {
        this.requestController = controller;
    }

    public void setLoggedUser(Utente utente) {
        this.UtenteLoggato = utente;
    }

    public void initialize() {
    }
}
