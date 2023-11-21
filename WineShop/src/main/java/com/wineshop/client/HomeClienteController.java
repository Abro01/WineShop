package com.wineshop.client;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import utilities.models.Utente;
import utilities.Costanti;
import utilities.Response;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class HomeClienteController {
    private RequestController requestController;
    private Utente UtenteLoggato;

    @FXML
    private AnchorPane AnchorPane_Carrello;

    @FXML
    private AnchorPane AnchorPane_Home;

    @FXML
    private AnchorPane AnchorPane_Ordini;

    @FXML
    private AnchorPane AnchorPane_Preferiti;

    @FXML
    private AnchorPane AnchorPane_Profilo;

    @FXML
    private AnchorPane AnchorPane_Recensioni;

    @FXML
    private AnchorPane AnchorPane_Ricerca;

    @FXML
    private AnchorPane AnchorPane_Vino;

    @FXML
    private TableColumn<?, ?> Anno_Preferiti;

    @FXML
    private ComboBox<?> CBQuantita_Vino;

    @FXML
    private ComboBox<?> CBVino_Recensioni;

    @FXML
    private ComboBox<?> CBVoto_Recensioni;

    @FXML
    private TableColumn<?, ?> ID_Ordini;

    @FXML
    private TableColumn<?, ?> ID_Preferiti;

    @FXML
    private TableColumn<?, ?> Indirizzo_Ordini;

    @FXML
    private TableColumn<?, ?> MP_Ordini;

    @FXML
    private TableColumn<?, ?> Nome_Preferiti;

    @FXML
    private TableColumn<?, ?> Prezzo_Carrello;

    @FXML
    private TableColumn<?, ?> Provenienza_Preferiti;

    @FXML
    private TableColumn<?, ?> Quantita_Carrello;

    @FXML
    private TableView<?> TV_Carrello;

    @FXML
    private TableView<?> TV_Ordini;

    @FXML
    private TableView<?> TV_Preferiti;

    @FXML
    private TableView<?> TV_Ricerca;

    @FXML
    private TableColumn<?, ?> Totale_Ordini;

    @FXML
    private TableColumn<?, ?> Vino_Carrello;

    @FXML
    private TableColumn<?, ?> Vitigno_Preferiti;

    @FXML
    private Button btnAcquisto_Carrello;

    @FXML
    private Button btnAddCart_Vino;

    @FXML
    private Button btnAddPreferiti_Vino;

    @FXML
    private Button btnAdd_Recensioni;

    @FXML
    private Button btnCarrello_Cliente;

    @FXML
    private Button btnHome_Cliente;

    @FXML
    private Button btnLogout_Cliente;

    @FXML
    private Button btnOrdini_Cliente;

    @FXML
    private Button btnPreferiti_Cliente;

    @FXML
    private Button btnProfilo_Cliente;

    @FXML
    private Button btnRecensioni_Cliente;

    @FXML
    private Button btnRicerca_Cliente;

    @FXML
    private Button btnRicerca_Ricerca;

    @FXML
    private ComboBox<?> cbMetodo_Carrello;

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
    private Label lblTotale_Carrello;

    @FXML
    private Label lblUsername_Profilo;

    @FXML
    private Label lblVitigno_Vino;

    @FXML
    private TextField txtIndirizzo_Carrello;

    @FXML
    private TextField txtRicerca_Ricerca;

    @FXML
    private TextArea txtTesto_Recensioni;


    public void setRequestController(RequestController controller) {
        this.requestController = controller;
    }

    public void setLoggedUser(Utente utente) {
        this.UtenteLoggato = utente;
    }

    @FXML
    public void OnBtnHomeCliente_Click(ActionEvent event){
        AnchorPane_Home.setVisible(true);
        AnchorPane_Ricerca.setVisible(false);
        AnchorPane_Carrello.setVisible(false);
        AnchorPane_Ordini.setVisible(false);
        AnchorPane_Preferiti.setVisible(false);
        AnchorPane_Recensioni.setVisible(false);
        AnchorPane_Profilo.setVisible(false);
    }

    @FXML
    public void OnBtnRicercaCliente_Click(ActionEvent event) {
        AnchorPane_Home.setVisible(false);
        AnchorPane_Ricerca.setVisible(true);
        AnchorPane_Carrello.setVisible(false);
        AnchorPane_Ordini.setVisible(false);
        AnchorPane_Preferiti.setVisible(false);
        AnchorPane_Recensioni.setVisible(false);
        AnchorPane_Profilo.setVisible(false);
    }

    @FXML
    public void OnBtnCarrelloCliente_Click(ActionEvent event) {
        AnchorPane_Home.setVisible(false);
        AnchorPane_Ricerca.setVisible(false);
        AnchorPane_Carrello.setVisible(true);
        AnchorPane_Ordini.setVisible(false);
        AnchorPane_Preferiti.setVisible(false);
        AnchorPane_Recensioni.setVisible(false);
        AnchorPane_Profilo.setVisible(false);
    }

    @FXML
    public void OnBtnOrdiniCliente_Click(ActionEvent event) {
        AnchorPane_Home.setVisible(false);
        AnchorPane_Ricerca.setVisible(false);
        AnchorPane_Carrello.setVisible(false);
        AnchorPane_Ordini.setVisible(true);
        AnchorPane_Preferiti.setVisible(false);
        AnchorPane_Recensioni.setVisible(false);
        AnchorPane_Profilo.setVisible(false);
    }

    @FXML
    public void OnBtnPreferitiCliente_Click(ActionEvent event) {
        AnchorPane_Home.setVisible(false);
        AnchorPane_Ricerca.setVisible(false);
        AnchorPane_Carrello.setVisible(false);
        AnchorPane_Ordini.setVisible(false);
        AnchorPane_Preferiti.setVisible(true);
        AnchorPane_Recensioni.setVisible(false);
        AnchorPane_Profilo.setVisible(false);
    }

    @FXML
    public void OnBtnRecensioniCliente_Click(ActionEvent event) {
        AnchorPane_Home.setVisible(false);
        AnchorPane_Ricerca.setVisible(false);
        AnchorPane_Carrello.setVisible(false);
        AnchorPane_Ordini.setVisible(false);
        AnchorPane_Preferiti.setVisible(false);
        AnchorPane_Recensioni.setVisible(true);
        AnchorPane_Profilo.setVisible(false);
    }

    @FXML
    public void OnBtnProfiloCliente_Click(ActionEvent event) {
        AnchorPane_Home.setVisible(false);
        AnchorPane_Ricerca.setVisible(false);
        AnchorPane_Carrello.setVisible(false);
        AnchorPane_Ordini.setVisible(false);
        AnchorPane_Preferiti.setVisible(false);
        AnchorPane_Recensioni.setVisible(false);
        AnchorPane_Profilo.setVisible(true);
    }

    @FXML
    public void OnBtnLogoutCliente_Click(ActionEvent event) throws Exception {
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
    public void initialize() {
    }

}
