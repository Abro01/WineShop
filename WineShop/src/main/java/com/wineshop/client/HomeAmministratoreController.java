package com.wineshop.client;

import javafx.beans.property.*;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import utilities.EmptyPayload;
import utilities.models.*;
import utilities.Costanti;
import utilities.Response;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HomeAmministratoreController {
    private RequestController requestController;
    private Utente UtenteLoggato;
    @FXML
    private AnchorPane AnchorPane_Home;
    @FXML
    private AnchorPane AnchorPane_DatiOrdine;
    @FXML
    private AnchorPane AnchorPane_AddImpiegati;
    @FXML
    private AnchorPane AnchorPane_Ordini;
    @FXML
    private AnchorPane AnchorPane_Profilo;
    @FXML
    private AnchorPane AnchorPane_DettagliOrdine;
    @FXML
    private AnchorPane AnchorPane_Report;
    @FXML
    private AnchorPane AnchorPane_DatiUtente;
    @FXML
    private AnchorPane AnchorPane_Ricerca;
    @FXML
    private AnchorPane AnchorPane_DatiVino;
    @FXML
    private AnchorPane AnchorPane_GestioneOfferta;
    @FXML
    private ComboBox<String> CBSceltaDati_Ricerca;
    @FXML
    private TableColumn<Vino, Integer> ColonnaAnno_Vino = new TableColumn<>();
    @FXML
    private TableColumn<Utente, String> ColonnaCognome_Cliente = new TableColumn<>();
    @FXML
    private TableColumn<Utente, String> ColonnaCognome_Impiegati = new TableColumn<>();
    @FXML
    private TableColumn<Offerta, String> ColonnaDescrizione_Offerta = new TableColumn<>();
    @FXML
    private TableColumn<Utente, String> ColonnaEmail_Cliente = new TableColumn<>();
    @FXML
    private TableColumn<Utente, String> ColonnaEmail_Impiegati = new TableColumn<>();
    @FXML
    private TableColumn<Utente, Void> ColonnaGestione_Impiegati = new TableColumn<>();
    @FXML
    private TableColumn<Offerta, Void> ColonnaGestione_Offerta = new TableColumn<>();
    @FXML
    private TableColumn<Offerta, Integer> ColonnaID_Offerta = new TableColumn<>();
    @FXML
    private TableColumn<Utente, Integer> ColonnaID_Cliente = new TableColumn<>();
    @FXML
    private TableColumn<Utente, Integer> ColonnaID_Impiegati = new TableColumn<>();
    @FXML
    private TableColumn<Utente, Void> ColonnaInfo_Cliente = new TableColumn<>();
    @FXML
    private TableColumn<Vino, Void> ColonnaInfo_Vino = new TableColumn<>();
    @FXML
    private TableColumn<Utente, String> ColonnaNome_Cliente = new TableColumn<>();
    @FXML
    private TableColumn<Utente, String> ColonnaNome_Impiegati = new TableColumn<>();
    @FXML
    private TableColumn<Vino, Integer> ColonnaID_Vino = new TableColumn<>();
    @FXML
    private TableColumn<Vino, Double> ColonnaPrezzo_Vino = new TableColumn<>();
    @FXML
    private TableColumn<Vino, String> ColonnaNome_Vino = new TableColumn<>();
    @FXML
    private TableColumn<Offerta, Integer> ColonnaSconto_Offerta = new TableColumn<>();
    @FXML
    private TableColumn<Utente, String> ColonnaUsername_Cliente = new TableColumn<>();
    @FXML
    private TableColumn<Utente, String> ColonnaUsername_Impiegati = new TableColumn<>();
    @FXML
    private TableColumn<Offerta, String> ColonnaVino_Offerta = new TableColumn<>();
    @FXML
    private TableColumn<Vino, String> ColonnaVitigno_Vino = new TableColumn<>();
    @FXML
    private DatePicker Data_Report;
    @FXML
    private TableColumn<Ordine, Void> Gestione_Ordini = new TableColumn<>();
    @FXML
    private TableColumn<Ordine, Integer> ID_Ordini = new TableColumn<>();
    @FXML
    private TableColumn<Ordine, String> Indirizzo_Ordini = new TableColumn<>();
    @FXML
    private TableColumn<Ordine, String> MP_Ordini = new TableColumn<>();
    @FXML
    private TableColumn<Ordine, String> Stato_Ordini = new TableColumn<>();
    @FXML
    private TableColumn<DettagliOrdine, Integer> ID_DettagliOrdine = new TableColumn<>();
    @FXML
    private TableColumn<DettagliOrdine, Integer> Quantita_DettagliOrdine = new TableColumn<>();
    @FXML
    private TableColumn<DettagliOrdine, String> Vino_DettagliOrdine = new TableColumn<>();
    @FXML
    private TableView<Ordine> TV_Ordini = new TableView<>();
    @FXML
    private TableView<Utente> TV_RicercaImpiegati = new TableView<>();
    @FXML
    private TableView<Offerta> TV_RicercaOfferte = new TableView<>();
    @FXML
    private TableView<Utente> TV_RicercaClienti = new TableView<>();
    @FXML
    private TableView<Vino> TV_RicercaVini = new TableView<>();
    @FXML
    private TableView<DettagliOrdine> TV_DettagliOrdine = new TableView<>();
    @FXML
    private TableColumn<Ordine, Double> Totale_Ordini = new TableColumn<>();
    @FXML
    private Button btnAddImpiegati_Amministratore;
    @FXML
    private Button btnAddReport_Report;
    @FXML
    private Button btnGestioneOrdini_Amministratore;
    @FXML
    private Button btnHome_Amministratore;
    @FXML
    private Button btnLogout_Amministratore;
    @FXML
    private Button btnProfilo_Amministratore;
    @FXML
    private Button btnRegistra_AddImpiegati;
    @FXML
    private Button btnReport_Amministratore;
    @FXML
    private Button btnRicerca_Amministratore;
    @FXML
    private Button btnVisualizzaDettagliOrdine;
    @FXML
    private Button btnRicerca_Ricerca;
    @FXML
    private Button btnEliminaImpiegati;
    @FXML
    private Button btnEliminaOfferta;
    @FXML
    private Label lblCF_Profilo;
    @FXML
    private Label lblCognome_Profilo;
    @FXML
    private Label lblEmail_Profilo;
    @FXML
    private Label lblIndirizzo_Profilo;
    @FXML
    private Label lblNome_Profilo;
    @FXML
    private Label lblTelefono_Profilo;
    @FXML
    private Label lblUsername_Profilo;
    @FXML
    private TextField txtCF_AddImpiegati;
    @FXML
    private TextField txtCognome_AddImpiegati;
    @FXML
    private TextField txtEmail_AddImpiegati;
    @FXML
    private TextField txtIndirizzo_AddImpiegati;
    @FXML
    private TextField txtNome_AddImpiegati;
    @FXML
    private TextField txtPassword_AddImpiegati;
    @FXML
    private TextField txtConfPassword_AddImpiegati;
    @FXML
    private TextArea txtReport_Report;
    @FXML
    private TextField txtRicerca_Ricerca;
    @FXML
    private TextField txtTelefono_AddImpiegati;
    @FXML
    private TextField txtUsername_AddImpiegati;
    @FXML
    private TextField txtRicercaInt_Ricerca;
    @FXML
    private TextField txtRicerca_Ordini;
    @FXML
    private Label lblAnno_Vino;
    @FXML
    private Label lblDescrizione_Vino;
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
    private Label lblVitigno_Vino;
    @FXML
    private Label lblCF_Utente;
    @FXML
    private Label lblCognome_Utente;
    @FXML
    private Label lblEmail_Utente;
    @FXML
    private Label lblID_Utente;
    @FXML
    private Label lblNome_Utente;
    @FXML
    private Label lblTelefono_Utente;
    @FXML
    private Label lblUsername_Utente;
    @FXML
    private Label lblIndirizzo_Utente;
    @FXML
    private Label lblDescrizione_Offerta;
    @FXML
    private Label lblID_Offerta;
    @FXML
    private Label lblSconto_Offerta;
    @FXML
    private Label lblVino_Offerta;
    @FXML
    private Label lblIDCliente_Ordine;
    @FXML
    private Label lblID_Ordine;
    @FXML
    private Label lblIndirizzo_Ordine;
    @FXML
    private Label lblMP_Ordine;
    @FXML
    private Label lblStato_Ordine;
    @FXML
    private Label lblTotale_Ordine;
    @FXML
    private ImageView imgBottglia_Vino;
    private String DataReport, tipo_dato;
    private ArrayList<Vino> vino;
    private ArrayList<Utente> impiegato;
    private ArrayList<Utente> cliente;
    private ArrayList<Offerta> offerta;
    private ArrayList<Ordine> ordine;
    private ArrayList<DettagliOrdine> dettagli_ordine;
    private Vino VinoSelezionato;
    private Utente ImipegatoSelezionato, ClienteSelezionato;
    private Offerta OffertaSelezionata;
    private Ordine OrdineSelezionato;
    private int id_vino, id_impiegato, id_cliente, id_offerta, id_ordine;
    private Image immagine;

    //imposta il controller
    public void setRequestController(RequestController controller) {
        this.requestController = controller;
    }

    //memorizza l'utente loggato
    public void setLoggedUser(Utente utente) {
        this.UtenteLoggato = utente;
    }

    //caricamento AnchorPaneHome
    @FXML
    void OnBtnHomeAmministratore_Click(ActionEvent event) {
        AnchorPane_Home.setVisible(true);
        AnchorPane_Profilo.setVisible(false);
        AnchorPane_AddImpiegati.setVisible(false);
        AnchorPane_Ricerca.setVisible(false);
        AnchorPane_Ordini.setVisible(false);
        AnchorPane_Report.setVisible(false);
        AnchorPane_DatiVino.setVisible(false);
        AnchorPane_DatiUtente.setVisible(false);
        AnchorPane_GestioneOfferta.setVisible(false);
        AnchorPane_DatiOrdine.setVisible(false);
        AnchorPane_DettagliOrdine.setVisible(false);
    }

    //logout utente
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

    //caricamento AnchorPane Add Impiegati
    @FXML
    void OnBtnAddImpiegatiAmministratore_Click(ActionEvent event) {
        AnchorPane_Home.setVisible(false);
        AnchorPane_Profilo.setVisible(false);
        AnchorPane_AddImpiegati.setVisible(true);
        AnchorPane_Ricerca.setVisible(false);
        AnchorPane_Ordini.setVisible(false);
        AnchorPane_Report.setVisible(false);
        AnchorPane_DatiVino.setVisible(false);
        AnchorPane_DatiUtente.setVisible(false);
        AnchorPane_GestioneOfferta.setVisible(false);
        AnchorPane_DatiOrdine.setVisible(false);
        AnchorPane_DettagliOrdine.setVisible(false);
    }

    //inserire un impiegato
    @FXML
    void OnBtnAddImpiegati_Click(ActionEvent event) {
        String nome = this.txtNome_AddImpiegati.getText();
        String cognome = this.txtCognome_AddImpiegati.getText();
        String indirizzo = this.txtIndirizzo_AddImpiegati.getText();
        String telefono = this.txtTelefono_AddImpiegati.getText();
        String cf = this.txtCF_AddImpiegati.getText();
        String email = this.txtEmail_AddImpiegati.getText();
        String username = this.txtUsername_AddImpiegati.getText();
        String password = this.txtPassword_AddImpiegati.getText();
        String cof_password = this.txtConfPassword_AddImpiegati.getText();

        if(username.isEmpty() || nome.isEmpty() || cognome.isEmpty() || indirizzo.isEmpty() || telefono.isEmpty() || cf.isEmpty() || email.isEmpty() || password.isEmpty() || cof_password.isEmpty())
        {
            String s = "Il campo non può essere vuoto";
            Errore_Campi_Vuoti(s);
            return;
        }

        if(!txtEmail_AddImpiegati.getText().contains("@") || email.isEmpty())
        {
            Errore_Email();
            return;
        }

        if(!CF_Check(cf))
        {
            String s = "Reinserire il codice fiscale perche' non sono stati rispettati i criteri necessari";
            Errore_Campi_Vuoti(s);
            txtCF_AddImpiegati.clear();
            return;
        }

        if(!password.equals(cof_password))
        {
            Errore_Password();
            return;
        }

        try {
            Utente u = new Utente(nome, cognome, cf, email, telefono, indirizzo, password, username);
            Response r = this.requestController.makeRequest(Costanti.Registrazione_Impiegati, u);

            if(r.getStatusCode() == Costanti.Successo)
            {
                System.out.println("Registrazione impiegato avvenuta con successo");
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Registrazione impiegato eseguita");
                alert.setHeaderText("Registrazione impiegato avvenuta con successo");
                alert.setContentText("Registrazione impiegato avvenuta con successo");
                alert.showAndWait();
            } else {
                System.out.println("Registrazione impiegato fallita");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Registrazione impiegato fallita");
                alert.setHeaderText("Si è verificato un'errore.");
                alert.setContentText("La registrazione dell'impiegato non è andata a buon fine");
                alert.showAndWait();
            }
        } catch (Exception e)
        {
            System.out.println(e);
            throw new RuntimeException(e);
        }
        txtNome_AddImpiegati.clear();
        txtCognome_AddImpiegati.clear();
        txtIndirizzo_AddImpiegati.clear();
        txtTelefono_AddImpiegati.clear();
        txtCF_AddImpiegati.clear();
        txtEmail_AddImpiegati.clear();
        txtUsername_AddImpiegati.clear();
        txtPassword_AddImpiegati.clear();
        txtConfPassword_AddImpiegati.clear();
        txtPassword_AddImpiegati.clear();
        txtConfPassword_AddImpiegati.clear();
        txtEmail_AddImpiegati.clear();
    }

    //aggiunta report
    @FXML
    void OnBtnAddReport_Click(ActionEvent event) {
        LocalDate data_selezionata = Data_Report.getValue();
        DataReport = data_selezionata.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        System.out.println("Data selezionata: " + DataReport);
        String descrizione = this.txtReport_Report.getText();
        int CODAmministratore = UtenteLoggato.getId();

        try {
            Report report = new Report(CODAmministratore, descrizione, DataReport);         //definizione oggetto report
            Response r = this.requestController.makeRequest(Costanti.Add_Report, report);   //svolge la richiesta

            if(r.getStatusCode() == Costanti.Successo)          //se la risposta ha avuto esito positivo entra
            {
                System.out.println("Report aggiunto con successo");
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Report aggiunto");
                alert.setHeaderText("Report aggiunto con successo");
                alert.setContentText("Report aggiunto con successo");
                alert.showAndWait();
            } else {
                System.out.println("Il Report non è stato inserito");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Il report non è stato inserito");
                alert.setHeaderText("Si è verificato un'errore.");
                alert.setContentText("L'aggiunta del report non è andata a buon fine");
                alert.showAndWait();
            }
        } catch (Exception e)
        {
            System.out.println(e);
            throw new RuntimeException(e);
        }
        txtReport_Report.clear();
        Data_Report.setValue(null);
    }

    @FXML
    void OnBtnOrdiniAmministratore_Click(ActionEvent event) {
        txtRicerca_Ordini.clear();
        AnchorPane_Home.setVisible(false);
        AnchorPane_Profilo.setVisible(false);
        AnchorPane_AddImpiegati.setVisible(false);
        AnchorPane_Ricerca.setVisible(false);
        AnchorPane_Ordini.setVisible(true);
        AnchorPane_Report.setVisible(false);
        AnchorPane_DatiVino.setVisible(false);
        AnchorPane_DatiUtente.setVisible(false);
        AnchorPane_GestioneOfferta.setVisible(false);
        AnchorPane_DatiOrdine.setVisible(false);
        AnchorPane_DettagliOrdine.setVisible(false);

        try {
            Response r = this.requestController.makeRequest(Costanti.Mostra_Ordini, new EmptyPayload());

            if(r.getStatusCode() == Costanti.Successo)
            {
                ordine = (ArrayList<Ordine>) r.getPayload();            //definisce un array listi di tipo ordine
                ObservableList<Ordine> ordini = FXCollections.observableArrayList(ordine);

                //imposta i dati nelle varie colonne
                ID_Ordini.setCellValueFactory(cellData -> {
                    int ID_ordine = cellData.getValue().getID();
                    IntegerProperty proprieta_id_ordine = new SimpleIntegerProperty(ID_ordine);
                    return Bindings.createObjectBinding(proprieta_id_ordine::get, proprieta_id_ordine);
                });

                Totale_Ordini.setCellValueFactory(cellData -> {
                    Double totale_ordine = cellData.getValue().getTotale();
                    DoubleProperty proprieta_totale_ordine = new SimpleDoubleProperty(totale_ordine);
                    return Bindings.createObjectBinding(proprieta_totale_ordine::get, proprieta_totale_ordine);
                });

                Indirizzo_Ordini.setCellValueFactory(cellData -> {
                    String indirizzo_ordine = cellData.getValue().getIndirizzo();
                    StringProperty proprieta_indirizzo_ordine = new SimpleStringProperty(indirizzo_ordine);
                    return Bindings.createObjectBinding(proprieta_indirizzo_ordine::get, proprieta_indirizzo_ordine);
                });

                Stato_Ordini.setCellValueFactory(cellData -> {
                    String stato_ordine = cellData.getValue().getStato();
                    StringProperty proprieta_stato_ordine = new SimpleStringProperty(stato_ordine);
                    return Bindings.createObjectBinding(proprieta_stato_ordine::get, proprieta_stato_ordine);
                });

                MP_Ordini.setCellValueFactory(cellData -> {
                    String mp_ordine = cellData.getValue().getMetodo_Pagamento();
                    StringProperty proprieta_mp_ordine = new SimpleStringProperty(mp_ordine);
                    return Bindings.createObjectBinding(proprieta_mp_ordine::get, proprieta_mp_ordine);
                });

                //colonna composta da bottoni
                Gestione_Ordini.setCellFactory(param -> new TableCell<>() {
                    final Button btnGestione = new Button("Gestisci");

                    {setAlignment(Pos.CENTER);}
                    @Override
                    protected void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);

                        if (empty)
                        {
                            setGraphic(null);
                            setText(null);
                        } else {
                            btnGestione.getStyleClass().add("btn");
                            btnGestione.setOnAction(event -> {
                                OrdineSelezionato = TV_Ordini.getSelectionModel().getSelectedItem();

                                if(OrdineSelezionato != null)
                                {
                                    AnchorPane_Home.setVisible(false);
                                    AnchorPane_Profilo.setVisible(false);
                                    AnchorPane_AddImpiegati.setVisible(false);
                                    AnchorPane_Ricerca.setVisible(false);
                                    AnchorPane_Ordini.setVisible(false);
                                    AnchorPane_Report.setVisible(false);
                                    AnchorPane_DatiVino.setVisible(false);
                                    AnchorPane_DatiUtente.setVisible(false);
                                    AnchorPane_GestioneOfferta.setVisible(false);
                                    AnchorPane_DatiOrdine.setVisible(true);
                                    AnchorPane_DettagliOrdine.setVisible(false);

                                    id_ordine = ID_Ordini.getCellData(OrdineSelezionato);
                                    System.out.println("ID: " + id_ordine);

                                    lblID_Ordine.setText(String.valueOf(OrdineSelezionato.getID()));
                                    lblTotale_Ordine.setText(String.valueOf(OrdineSelezionato.getTotale()));
                                    lblIndirizzo_Ordine.setText(OrdineSelezionato.getIndirizzo());
                                    lblMP_Ordine.setText(OrdineSelezionato.getMetodo_Pagamento());
                                    lblID_Ordine.setText(String.valueOf(OrdineSelezionato.getCODCliente()));
                                    lblStato_Ordine.setText(OrdineSelezionato.getStato());
                                }
                            });
                            setGraphic(btnGestione);
                            setText(null);
                        }
                    }
                });
                TV_Ordini.setItems(ordini);     //inserisce nella TableView i vari ordini
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("No ordini alert");
                alert.setHeaderText("Non sono presenti ordini.");
                alert.setContentText("Non sono presenti ordini.");
                alert.showAndWait();
            }
        } catch (Exception e)
        {
            System.out.println(e);
            throw new RuntimeException(e);
        }
    }

    //ricerca ordini
    @FXML
    void OnBtnCercaOrdini_Click(ActionEvent event) {
        String ordine_cercato = txtRicerca_Ordini.getText();

        try {
            Response r = this.requestController.makeRequest(Costanti.Ricerca_Ordini, new Ordine(ordine_cercato));

            if(r.getStatusCode() == Costanti.Successo)
            {
                ordine = (ArrayList<Ordine>) r.getPayload();            //definisce un array listi di tipo ordine
                ObservableList<Ordine> ordini = FXCollections.observableArrayList(ordine);

                //imposta i dati nelle varie colonne
                ID_Ordini.setCellValueFactory(cellData -> {
                    int ID_ordine = cellData.getValue().getID();
                    IntegerProperty proprieta_id_ordine = new SimpleIntegerProperty(ID_ordine);
                    return Bindings.createObjectBinding(proprieta_id_ordine::get, proprieta_id_ordine);
                });

                Totale_Ordini.setCellValueFactory(cellData -> {
                    Double totale_ordine = cellData.getValue().getTotale();
                    DoubleProperty proprieta_totale_ordine = new SimpleDoubleProperty(totale_ordine);
                    return Bindings.createObjectBinding(proprieta_totale_ordine::get, proprieta_totale_ordine);
                });

                Indirizzo_Ordini.setCellValueFactory(cellData -> {
                    String indirizzo_ordine = cellData.getValue().getIndirizzo();
                    StringProperty proprieta_indirizzo_ordine = new SimpleStringProperty(indirizzo_ordine);
                    return Bindings.createObjectBinding(proprieta_indirizzo_ordine::get, proprieta_indirizzo_ordine);
                });

                Stato_Ordini.setCellValueFactory(cellData -> {
                    String stato_ordine = cellData.getValue().getStato();
                    StringProperty proprieta_stato_ordine = new SimpleStringProperty(stato_ordine);
                    return Bindings.createObjectBinding(proprieta_stato_ordine::get, proprieta_stato_ordine);
                });

                MP_Ordini.setCellValueFactory(cellData -> {
                    String mp_ordine = cellData.getValue().getMetodo_Pagamento();
                    StringProperty proprieta_mp_ordine = new SimpleStringProperty(mp_ordine);
                    return Bindings.createObjectBinding(proprieta_mp_ordine::get, proprieta_mp_ordine);
                });

                Gestione_Ordini.setCellFactory(param -> new TableCell<>() {
                    final Button btnGestione = new Button("Gestisci");

                    {setAlignment(Pos.CENTER);}
                    @Override
                    protected void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);

                        if (empty)
                        {
                            setGraphic(null);
                            setText(null);
                        } else {
                            btnGestione.getStyleClass().add("btn");
                            btnGestione.setOnAction(event -> {
                                OrdineSelezionato = TV_Ordini.getSelectionModel().getSelectedItem();

                                if(OrdineSelezionato != null)
                                {
                                    AnchorPane_Home.setVisible(false);
                                    AnchorPane_Profilo.setVisible(false);
                                    AnchorPane_AddImpiegati.setVisible(false);
                                    AnchorPane_Ricerca.setVisible(false);
                                    AnchorPane_Ordini.setVisible(false);
                                    AnchorPane_Report.setVisible(false);
                                    AnchorPane_DatiVino.setVisible(false);
                                    AnchorPane_DatiUtente.setVisible(false);
                                    AnchorPane_GestioneOfferta.setVisible(false);
                                    AnchorPane_DatiOrdine.setVisible(true);
                                    AnchorPane_DettagliOrdine.setVisible(false);

                                    id_ordine = ID_Ordini.getCellData(OrdineSelezionato);
                                    System.out.println("ID: " + id_ordine);

                                    lblID_Ordine.setText(String.valueOf(OrdineSelezionato.getID()));
                                    lblTotale_Ordine.setText(String.valueOf(OrdineSelezionato.getTotale()));
                                    lblIndirizzo_Ordine.setText(OrdineSelezionato.getIndirizzo());
                                    lblMP_Ordine.setText(OrdineSelezionato.getMetodo_Pagamento());
                                    lblID_Ordine.setText(String.valueOf(OrdineSelezionato.getCODCliente()));
                                    lblStato_Ordine.setText(OrdineSelezionato.getStato());
                                }
                            });
                            setGraphic(btnGestione);
                            setText(null);
                        }
                    }
                });
                TV_Ordini.setItems(ordini);     //inserisce i dati nella TableView
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("No ordini alert");
                alert.setHeaderText("Non sono presenti ordini.");
                alert.setContentText("Non sono presenti ordini.");
                alert.showAndWait();
            }
        } catch (Exception e)
        {
            System.out.println(e);
            throw new RuntimeException(e);
        }
        txtRicerca_Ordini.clear();
    }

    //visualizza dettagli ordine
    @FXML
    void OnBtnVisualizzaDettagliOrdine_Click(ActionEvent event) {
        AnchorPane_Home.setVisible(false);
        AnchorPane_Profilo.setVisible(false);
        AnchorPane_AddImpiegati.setVisible(false);
        AnchorPane_Ricerca.setVisible(false);
        AnchorPane_Ordini.setVisible(false);
        AnchorPane_Report.setVisible(false);
        AnchorPane_DatiVino.setVisible(false);
        AnchorPane_DatiUtente.setVisible(false);
        AnchorPane_GestioneOfferta.setVisible(false);
        AnchorPane_DatiOrdine.setVisible(false);
        AnchorPane_DettagliOrdine.setVisible(true);

        try {
            Response r = this.requestController.makeRequest(Costanti.Mostra_Dettagli_Ordine, new DettagliOrdine(id_ordine));

            if(r.getStatusCode() == Costanti.Successo)
            {
                dettagli_ordine = (ArrayList<DettagliOrdine>) r.getPayload();       //definisce un Array list di tipo Dettagli Ordine
                ObservableList<DettagliOrdine> dettagli_ordini = FXCollections.observableArrayList(dettagli_ordine);

                //inserisce i dati nelle colonne
                ID_DettagliOrdine.setCellValueFactory(cellData -> {
                    int ID_DettagliOrdine = cellData.getValue().getID();
                    IntegerProperty proprieta_id_dettagli_ordine = new SimpleIntegerProperty(ID_DettagliOrdine);
                    return Bindings.createObjectBinding(proprieta_id_dettagli_ordine::get, proprieta_id_dettagli_ordine);
                });

                Quantita_DettagliOrdine.setCellValueFactory(cellData -> {
                    int Quantita_DettagliOrdine = cellData.getValue().getQuantita();
                    IntegerProperty proprieta_quantita_dettagli_ordine = new SimpleIntegerProperty(Quantita_DettagliOrdine);
                    return Bindings.createObjectBinding(proprieta_quantita_dettagli_ordine::get, proprieta_quantita_dettagli_ordine);
                });

                Vino_DettagliOrdine.setCellValueFactory(cellData -> {
                    String Vino_DettagliOrdine = cellData.getValue().getNome_vino();
                    StringProperty proprieta_vino_dettagli_ordine = new SimpleStringProperty(Vino_DettagliOrdine);
                    return Bindings.createObjectBinding(proprieta_vino_dettagli_ordine::get, proprieta_vino_dettagli_ordine);
                });
                TV_DettagliOrdine.setItems(dettagli_ordini);        //inserisce i dati nella TableView
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("No dettagli ordini alert");
                alert.setHeaderText("Non sono presenti dettagli ordini per quest'ordine.");
                alert.setContentText("Non sono presenti dettagli ordini per quest'ordine.");
                alert.showAndWait();
            }
        } catch (Exception e)
        {
            System.out.println(e);
            throw new RuntimeException(e);
        }
    }

    //visualizza i dati del profilo loggato
    @FXML
    void OnBtnProfiloAmministratore_Click(ActionEvent event) {
        AnchorPane_Home.setVisible(false);
        AnchorPane_Profilo.setVisible(true);
        AnchorPane_AddImpiegati.setVisible(false);
        AnchorPane_Ricerca.setVisible(false);
        AnchorPane_Ordini.setVisible(false);
        AnchorPane_Report.setVisible(false);
        AnchorPane_DatiVino.setVisible(false);
        AnchorPane_DatiUtente.setVisible(false);
        AnchorPane_GestioneOfferta.setVisible(false);
        AnchorPane_DatiOrdine.setVisible(false);
        AnchorPane_DettagliOrdine.setVisible(false);

        lblNome_Profilo.setText(UtenteLoggato.getNome());
        lblCognome_Profilo.setText(UtenteLoggato.getCognome());
        lblUsername_Profilo.setText(UtenteLoggato.getUsername());
        lblCF_Profilo.setText(UtenteLoggato.getCf());
        lblEmail_Profilo.setText(UtenteLoggato.getEmail());
        lblTelefono_Profilo.setText(UtenteLoggato.getTelefono());
        lblIndirizzo_Profilo.setText(UtenteLoggato.getIndirizzo());
    }

    @FXML
    void OnBtnReportAmministratore_Click(ActionEvent event) {
        AnchorPane_Home.setVisible(false);
        AnchorPane_Profilo.setVisible(false);
        AnchorPane_AddImpiegati.setVisible(false);
        AnchorPane_Ricerca.setVisible(false);
        AnchorPane_Ordini.setVisible(false);
        AnchorPane_Report.setVisible(true);
        AnchorPane_DatiVino.setVisible(false);
        AnchorPane_DatiUtente.setVisible(false);
        AnchorPane_GestioneOfferta.setVisible(false);
        AnchorPane_DatiOrdine.setVisible(false);
        AnchorPane_DettagliOrdine.setVisible(false);
    }


    @FXML
    void OnBtnRicercaAmministratore_Click(ActionEvent event) {
        txtRicerca_Ricerca.clear();
        txtRicercaInt_Ricerca.clear();
        txtRicerca_Ricerca.setVisible(false);
        txtRicercaInt_Ricerca.setVisible(false);
        btnRicerca_Ricerca.setVisible(false);
        TV_RicercaImpiegati.refresh();
        TV_RicercaVini.refresh();
        TV_RicercaOfferte.refresh();
        TV_RicercaClienti.refresh();
        TV_RicercaImpiegati.setVisible(false);
        TV_RicercaVini.setVisible(false);
        TV_RicercaOfferte.setVisible(false);
        TV_RicercaClienti.setVisible(false);
        CBSceltaDati_Ricerca.setValue("Seleziona la tipologia di dato da cercare");
        CBSceltaDati_Ricerca.setPromptText("Scelgli la tipologia di dato da cercare");

        AnchorPane_Home.setVisible(false);
        AnchorPane_Profilo.setVisible(false);
        AnchorPane_AddImpiegati.setVisible(false);
        AnchorPane_Ricerca.setVisible(true);
        AnchorPane_Ordini.setVisible(false);
        AnchorPane_Report.setVisible(false);
        AnchorPane_DatiVino.setVisible(false);
        AnchorPane_DatiUtente.setVisible(false);
        AnchorPane_GestioneOfferta.setVisible(false);
        AnchorPane_DatiOrdine.setVisible(false);
        AnchorPane_DettagliOrdine.setVisible(false);

        //seleziona tipo di dato da ricercare tramite la ComboBox
        CBSceltaDati_Ricerca.setOnAction(event1 -> {
            tipo_dato = this.CBSceltaDati_Ricerca.getValue();
            System.out.println("Dato selezionato: " + tipo_dato);

            Response r;     //definizione variabile di tipo response per poterna riutillizzare in tutti i casi

            if(tipo_dato.equals("Vini"))
            {
                txtRicerca_Ricerca.setVisible(true);
                txtRicercaInt_Ricerca.setVisible(false);
                btnRicerca_Ricerca.setVisible(true);
                txtRicerca_Ricerca.setPromptText("Inserire il nome del vino o il suo anno di produzione");
                TV_RicercaVini.setVisible(true);
                TV_RicercaImpiegati.setVisible(false);
                TV_RicercaOfferte.setVisible(false);
                TV_RicercaClienti.setVisible(false);

                try {
                    r = this.requestController.makeRequest(Costanti.Mostra_Vini, new EmptyPayload());

                    if(r.getStatusCode() == Costanti.Successo)
                    {
                        vino = (ArrayList<Vino>) r.getPayload();            //definizione array list di tipo Vino
                        ObservableList<Vino> vini = FXCollections.observableArrayList(vino);

                        //inserisce i dati nelle colonne
                        ColonnaID_Vino.setCellValueFactory(cellData ->{
                            int ID_vino = cellData.getValue().getID();
                            IntegerProperty proprieta_id_vino = new SimpleIntegerProperty(ID_vino);
                            return Bindings.createObjectBinding(proprieta_id_vino::get, proprieta_id_vino);
                        });

                        ColonnaNome_Vino.setCellValueFactory(cellData ->{
                            String nome_vino = cellData.getValue().getNome();
                            StringProperty proprieta_nome_vino = new SimpleStringProperty(nome_vino);
                            return Bindings.createObjectBinding(proprieta_nome_vino::get, proprieta_nome_vino);
                        });

                        ColonnaAnno_Vino.setCellValueFactory(cellData ->{
                            int anno_vino = cellData.getValue().getAnno();
                            IntegerProperty proprieta_anno_vino = new SimpleIntegerProperty(anno_vino);
                            return Bindings.createObjectBinding(proprieta_anno_vino::get, proprieta_anno_vino);
                        });

                        ColonnaVitigno_Vino.setCellValueFactory(cellData ->{
                            String vitigno_vino = cellData.getValue().getVitigno();
                            StringProperty proprieta_vitigno_vino = new SimpleStringProperty(vitigno_vino);
                            return Bindings.createObjectBinding(proprieta_vitigno_vino::get, proprieta_vitigno_vino);
                        });

                        ColonnaPrezzo_Vino.setCellValueFactory(cellData -> {
                            double prezzo_vino = cellData.getValue().getPrezzo();
                            DoubleProperty proprieta_prezzo_vino = new SimpleDoubleProperty(prezzo_vino);
                            return Bindings.createObjectBinding(proprieta_prezzo_vino::get, proprieta_prezzo_vino);
                        });

                        ColonnaInfo_Vino.setCellFactory(param -> new TableCell<>() {
                            final Button btnInfo_vino = new Button("Info");

                            {setAlignment(Pos.CENTER);}

                            @Override
                            protected void updateItem(Void item, boolean empty) {
                                super.updateItem(item, empty);

                                if(empty)
                                {
                                    setGraphic(null);
                                    setText(null);
                                } else {
                                    btnInfo_vino.getStyleClass().add("btn");
                                    btnInfo_vino.setOnAction(event -> {
                                        VinoSelezionato = TV_RicercaVini.getSelectionModel().getSelectedItem();

                                        if(VinoSelezionato != null)
                                        {
                                            AnchorPane_Home.setVisible(false);
                                            AnchorPane_Profilo.setVisible(false);
                                            AnchorPane_AddImpiegati.setVisible(false);
                                            AnchorPane_Ricerca.setVisible(false);
                                            AnchorPane_Ordini.setVisible(false);
                                            AnchorPane_Report.setVisible(false);
                                            AnchorPane_DatiVino.setVisible(true);
                                            AnchorPane_DatiUtente.setVisible(false);
                                            AnchorPane_GestioneOfferta.setVisible(false);
                                            AnchorPane_DatiOrdine.setVisible(false);
                                            AnchorPane_DettagliOrdine.setVisible(false);

                                            id_vino = ColonnaID_Vino.getCellData(VinoSelezionato);
                                            System.out.println("ID: " + id_vino);

                                            lblNome_Vino.setText(VinoSelezionato.getNome());
                                            lblProvenienza_Vino.setText(VinoSelezionato.getProvenienza());
                                            lblAnno_Vino.setText(String.valueOf(VinoSelezionato.getAnno()));
                                            lblVitigno_Vino.setText(VinoSelezionato.getVitigno());
                                            lblPrezzo_Vino.setText(String.valueOf(VinoSelezionato.getPrezzo()));
                                            lblSoglia_Vino.setText(String.valueOf(VinoSelezionato.getSoglia()));
                                            lblQuantita_Vino.setText(String.valueOf(VinoSelezionato.getQuantita()));
                                            lblDescrizione_Vino.setText(VinoSelezionato.getDescrizione());

                                            immagine = new Image(VinoSelezionato.getImmagine());
                                            //System.out.println(VinoSelezionato.getImmagine());
                                            imgBottglia_Vino.setImage(immagine);
                                        }
                                    });
                                    setGraphic(btnInfo_vino);
                                    setText(null);
                                }
                            }
                        });
                        TV_RicercaVini.setItems(vini);      //inserisce i dati nella TableView
                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("No vini alert");
                        alert.setHeaderText("Non sono presenti vini.");
                        alert.setContentText("Non sono presenti vini.");
                        alert.showAndWait();
                    }
                } catch (Exception e)
                {
                    System.out.println(e);
                    throw new RuntimeException(e);
                }
            } else if (tipo_dato.equals("Impiegati"))       //stessa cosa impiegati
            {
                txtRicerca_Ricerca.setVisible(true);
                txtRicercaInt_Ricerca.setVisible(false);
                btnRicerca_Ricerca.setVisible(true);
                txtRicerca_Ricerca.setPromptText("Inserire l'username dell'impiegato");
                TV_RicercaVini.setVisible(false);
                TV_RicercaImpiegati.setVisible(true);
                TV_RicercaOfferte.setVisible(false);
                TV_RicercaClienti.setVisible(false);

                try {
                    r = this.requestController.makeRequest(Costanti.Mostra_Impiegati, new EmptyPayload());

                    if(r.getStatusCode() == Costanti.Successo)
                    {
                        impiegato = (ArrayList<Utente>) r.getPayload();
                        ObservableList<Utente> impiegati = FXCollections.observableArrayList(impiegato);

                        ColonnaID_Impiegati.setCellValueFactory(cellData ->{
                            int ID_impiegato = cellData.getValue().getId();
                            IntegerProperty proprieta_id_impiegato = new SimpleIntegerProperty(ID_impiegato);
                            return Bindings.createObjectBinding(proprieta_id_impiegato::get, proprieta_id_impiegato);
                        });

                        ColonnaNome_Impiegati.setCellValueFactory(cellData ->{
                            String nome_impiegato = cellData.getValue().getNome();
                            StringProperty proprieta_nome_impiegato = new SimpleStringProperty(nome_impiegato);
                            return Bindings.createObjectBinding(proprieta_nome_impiegato::get, proprieta_nome_impiegato);
                        });

                        ColonnaCognome_Impiegati.setCellValueFactory(cellData ->{
                            String cognome_impiegato = cellData.getValue().getCognome();
                            StringProperty proprieta_cognome_impiegato = new SimpleStringProperty(cognome_impiegato);
                            return Bindings.createObjectBinding(proprieta_cognome_impiegato::get, proprieta_cognome_impiegato);
                        });

                        ColonnaUsername_Impiegati.setCellValueFactory(cellData ->{
                            String username_impiegato = cellData.getValue().getUsername();
                            StringProperty proprieta_username_impiegato = new SimpleStringProperty(username_impiegato);
                            return Bindings.createObjectBinding(proprieta_username_impiegato::get, proprieta_username_impiegato);
                        });

                        ColonnaEmail_Impiegati.setCellValueFactory(cellData ->{
                            String email_impiegato = cellData.getValue().getEmail();
                            StringProperty proprieta_email_impiegato = new SimpleStringProperty(email_impiegato);
                            return Bindings.createObjectBinding(proprieta_email_impiegato::get, proprieta_email_impiegato);
                        });

                        ColonnaGestione_Impiegati.setCellFactory(param -> new TableCell<>() {
                            final Button btnGestione_Impiegato = new Button("Gestione");

                            {setAlignment(Pos.CENTER);}

                            @Override
                            protected void updateItem(Void item, boolean empty) {
                                super.updateItem(item, empty);

                                if(empty)
                                {
                                    setGraphic(null);
                                    setText(null);
                                } else {
                                    btnGestione_Impiegato.getStyleClass().add("btn");
                                    btnGestione_Impiegato.setOnAction(event -> {
                                        ImipegatoSelezionato = TV_RicercaImpiegati.getSelectionModel().getSelectedItem();

                                        if(ImipegatoSelezionato != null)
                                        {
                                            AnchorPane_Home.setVisible(false);
                                            AnchorPane_Profilo.setVisible(false);
                                            AnchorPane_AddImpiegati.setVisible(false);
                                            AnchorPane_Ricerca.setVisible(false);
                                            AnchorPane_Ordini.setVisible(false);
                                            AnchorPane_Report.setVisible(false);
                                            AnchorPane_DatiVino.setVisible(false);
                                            AnchorPane_DatiUtente.setVisible(true);
                                            btnEliminaImpiegati.setVisible(true);
                                            AnchorPane_GestioneOfferta.setVisible(false);
                                            AnchorPane_DettagliOrdine.setVisible(false);

                                            id_impiegato = ColonnaID_Impiegati.getCellData(ImipegatoSelezionato);
                                            System.out.println("ID: " + id_impiegato);

                                            lblID_Utente.setText(String.valueOf(ImipegatoSelezionato.getId()));
                                            lblNome_Utente.setText(ImipegatoSelezionato.getNome());
                                            lblCognome_Utente.setText(ImipegatoSelezionato.getCognome());
                                            lblEmail_Utente.setText(ImipegatoSelezionato.getEmail());
                                            lblTelefono_Utente.setText(ImipegatoSelezionato.getTelefono());
                                            lblIndirizzo_Utente.setText(ImipegatoSelezionato.getIndirizzo());
                                            lblUsername_Utente.setText(ImipegatoSelezionato.getUsername());
                                            lblCF_Utente.setText(ImipegatoSelezionato.getCf());
                                        }
                                    });
                                    setGraphic(btnGestione_Impiegato);
                                    setText(null);
                                }
                            }
                        });
                        TV_RicercaImpiegati.setItems(impiegati);
                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("No impiegati alert");
                        alert.setHeaderText("Non sono presenti impiegati.");
                        alert.setContentText("Non sono presenti impiegati.");
                        alert.showAndWait();
                    }
                } catch (Exception e)
                {
                    System.out.println(e);
                    throw new RuntimeException(e);
                }
            } else if (tipo_dato.equals("Clienti"))         //stessa cosa clienti
            {
                txtRicerca_Ricerca.setVisible(true);
                txtRicercaInt_Ricerca.setVisible(false);
                btnRicerca_Ricerca.setVisible(true);
                txtRicerca_Ricerca.setPromptText("Inserire il cognome del cliente");
                TV_RicercaVini.setVisible(false);
                TV_RicercaImpiegati.setVisible(false);
                TV_RicercaOfferte.setVisible(false);
                TV_RicercaClienti.setVisible(true);

                try {
                    r = this.requestController.makeRequest(Costanti.Mostra_Clienti, new EmptyPayload());

                    if(r.getStatusCode() == Costanti.Successo)
                    {
                        cliente = (ArrayList<Utente>) r.getPayload();
                        ObservableList<Utente> clienti = FXCollections.observableArrayList(cliente);

                        ColonnaID_Cliente.setCellValueFactory(cellData ->{
                            int ID_cliente = cellData.getValue().getId();
                            IntegerProperty proprieta_id_cliente = new SimpleIntegerProperty(ID_cliente);
                            return Bindings.createObjectBinding(proprieta_id_cliente::get, proprieta_id_cliente);
                        });

                        ColonnaNome_Cliente.setCellValueFactory(cellData ->{
                            String nome_cliente = cellData.getValue().getNome();
                            StringProperty proprieta_nome_cliente = new SimpleStringProperty(nome_cliente);
                            return Bindings.createObjectBinding(proprieta_nome_cliente::get, proprieta_nome_cliente);
                        });

                        ColonnaCognome_Cliente.setCellValueFactory(cellData ->{
                            String cognome_cliente = cellData.getValue().getCognome();
                            StringProperty proprieta_cognome_cliente = new SimpleStringProperty(cognome_cliente);
                            return Bindings.createObjectBinding(proprieta_cognome_cliente::get, proprieta_cognome_cliente);
                        });

                        ColonnaUsername_Cliente.setCellValueFactory(cellData ->{
                            String username_cliente = cellData.getValue().getUsername();
                            StringProperty proprieta_username_cliente = new SimpleStringProperty(username_cliente);
                            return Bindings.createObjectBinding(proprieta_username_cliente::get, proprieta_username_cliente);
                        });

                        ColonnaEmail_Cliente.setCellValueFactory(cellData ->{
                            String email_cliente = cellData.getValue().getEmail();
                            StringProperty proprieta_email_cliente = new SimpleStringProperty(email_cliente);
                            return Bindings.createObjectBinding(proprieta_email_cliente::get, proprieta_email_cliente);
                        });

                        ColonnaInfo_Cliente.setCellFactory(param -> new TableCell<>() {
                            final Button btnGestione_Cliente = new Button("Info");

                            {setAlignment(Pos.CENTER);}

                            @Override
                            protected void updateItem(Void item, boolean empty) {
                                super.updateItem(item, empty);

                                if(empty)
                                {
                                    setGraphic(null);
                                    setText(null);
                                } else {
                                    btnGestione_Cliente.getStyleClass().add("btn");
                                    btnGestione_Cliente.setOnAction(event -> {
                                        ClienteSelezionato = TV_RicercaClienti.getSelectionModel().getSelectedItem();

                                        if(ClienteSelezionato != null)
                                        {
                                            AnchorPane_Home.setVisible(false);
                                            AnchorPane_Profilo.setVisible(false);
                                            AnchorPane_AddImpiegati.setVisible(false);
                                            AnchorPane_Ricerca.setVisible(false);
                                            AnchorPane_Ordini.setVisible(false);
                                            AnchorPane_Report.setVisible(false);
                                            AnchorPane_DatiVino.setVisible(false);
                                            AnchorPane_DatiUtente.setVisible(true);
                                            btnEliminaImpiegati.setVisible(false);
                                            AnchorPane_GestioneOfferta.setVisible(false);
                                            AnchorPane_DettagliOrdine.setVisible(false);

                                            id_cliente = ColonnaID_Cliente.getCellData(ClienteSelezionato);
                                            System.out.println("ID: " + id_cliente);

                                            lblID_Utente.setText(String.valueOf(ClienteSelezionato.getId()));
                                            lblNome_Utente.setText(ClienteSelezionato.getNome());
                                            lblCognome_Utente.setText(ClienteSelezionato.getCognome());
                                            lblEmail_Utente.setText(ClienteSelezionato.getEmail());
                                            lblTelefono_Utente.setText(ClienteSelezionato.getTelefono());
                                            lblIndirizzo_Utente.setText(ClienteSelezionato.getIndirizzo());
                                            lblUsername_Utente.setText(ClienteSelezionato.getUsername());
                                            lblCF_Utente.setText(ClienteSelezionato.getCf());


                                        }
                                    });
                                    setGraphic(btnGestione_Cliente);
                                    setText(null);
                                }
                            }
                        });
                        TV_RicercaClienti.setItems(clienti);
                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("No clienti alert");
                        alert.setHeaderText("Non sono presenti clienti.");
                        alert.setContentText("Non sono presenti clienti.");
                        alert.showAndWait();
                    }
                } catch (Exception e)
                {
                    System.out.println(e);
                    throw new RuntimeException(e);
                }
            } else if (tipo_dato.equals("Offerte"))             //stessa cosa offerte
            {
                txtRicerca_Ricerca.setVisible(false);
                txtRicercaInt_Ricerca.setVisible(true);
                btnRicerca_Ricerca.setVisible(true);
                txtRicercaInt_Ricerca.setPromptText("Inserire codice dell'offerta");
                TV_RicercaVini.setVisible(false);
                TV_RicercaImpiegati.setVisible(false);
                TV_RicercaOfferte.setVisible(true);
                TV_RicercaClienti.setVisible(false);

                try {
                    r = this.requestController.makeRequest(Costanti.Mostra_Offerte, new EmptyPayload());

                    if(r.getStatusCode() == Costanti.Successo)
                    {
                        offerta = (ArrayList<Offerta>) r.getPayload();
                        ObservableList<Offerta> offerte = FXCollections.observableArrayList(offerta);

                        ColonnaID_Offerta.setCellValueFactory(cellData ->{
                            int ID_offerta = cellData.getValue().getID();
                            IntegerProperty proprieta_id_offerta = new SimpleIntegerProperty(ID_offerta);
                            return Bindings.createObjectBinding(proprieta_id_offerta::get, proprieta_id_offerta);
                        });

                        ColonnaDescrizione_Offerta.setCellValueFactory(cellData ->{
                            String descrizione_offerta = cellData.getValue().getDescrizione();
                            StringProperty proprieta_descrizione_offerta = new SimpleStringProperty(descrizione_offerta);
                            return Bindings.createObjectBinding(proprieta_descrizione_offerta::get, proprieta_descrizione_offerta);
                        });

                        ColonnaSconto_Offerta.setCellValueFactory(cellData ->{
                            int sconto_offerta = cellData.getValue().getSconto();
                            IntegerProperty proprieta_sconto_offerta = new SimpleIntegerProperty(sconto_offerta);
                            return Bindings.createObjectBinding(proprieta_sconto_offerta::get, proprieta_sconto_offerta);
                        });

                        ColonnaVino_Offerta.setCellValueFactory(cellData ->{
                            String vino_offerta = cellData.getValue().getNome();
                            StringProperty proprieta_vino_offerta = new SimpleStringProperty(vino_offerta);
                            return Bindings.createObjectBinding(proprieta_vino_offerta::get, proprieta_vino_offerta);
                        });

                        ColonnaGestione_Offerta.setCellFactory(param -> new TableCell<>() {
                            final Button btnGestione_Offerta = new Button("Gestione");

                            {setAlignment(Pos.CENTER);}

                            @Override
                            protected void updateItem(Void item, boolean empty) {
                                super.updateItem(item, empty);

                                if(empty)
                                {
                                    setGraphic(null);
                                    setText(null);
                                } else {
                                    btnGestione_Offerta.getStyleClass().add("btn");
                                    btnGestione_Offerta.setOnAction(event -> {
                                        OffertaSelezionata = TV_RicercaOfferte.getSelectionModel().getSelectedItem();

                                        if(OffertaSelezionata != null)
                                        {
                                            AnchorPane_Home.setVisible(false);
                                            AnchorPane_Profilo.setVisible(false);
                                            AnchorPane_AddImpiegati.setVisible(false);
                                            AnchorPane_Ricerca.setVisible(false);
                                            AnchorPane_Ordini.setVisible(false);
                                            AnchorPane_Report.setVisible(false);
                                            AnchorPane_DatiVino.setVisible(false);
                                            AnchorPane_DatiUtente.setVisible(false);
                                            btnEliminaImpiegati.setVisible(false);
                                            AnchorPane_GestioneOfferta.setVisible(true);
                                            AnchorPane_DettagliOrdine.setVisible(false);

                                            id_offerta = ColonnaID_Offerta.getCellData(OffertaSelezionata);
                                            System.out.println("ID: " + id_offerta);

                                            lblID_Offerta.setText(String.valueOf(OffertaSelezionata.getID()));
                                            lblSconto_Offerta.setText(String.valueOf(OffertaSelezionata.getSconto()));
                                            lblVino_Offerta.setText(OffertaSelezionata.getNome());
                                            lblDescrizione_Offerta.setText(OffertaSelezionata.getDescrizione());
                                        }
                                    });
                                    setGraphic(btnGestione_Offerta);
                                    setText(null);
                                }
                            }
                        });
                        TV_RicercaOfferte.setItems(offerte);
                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("No Offerte alert");
                        alert.setHeaderText("Non sono presenti offerte.");
                        alert.setContentText("Non sono presenti offerte.");
                        alert.showAndWait();
                    }
                } catch (Exception e)
                {
                    System.out.println(e);
                    throw new RuntimeException(e);
                }
            }
        });
    }

    //bottone ricerca dati
    @FXML
    void OnBtnRicerca_Click(ActionEvent event) {
        String dato_cercato;
        int dato_cercato_int;
        System.out.println("Dato : " + tipo_dato);
        Response r;

        if(tipo_dato.equals("Vini"))
        {
            txtRicerca_Ricerca.setVisible(true);
            txtRicercaInt_Ricerca.setVisible(false);
            dato_cercato = this.txtRicerca_Ricerca.getText();
            try {
                r = this.requestController.makeRequest(Costanti.Ricerca_Vini, new Vino(dato_cercato));

                if(r.getStatusCode() == Costanti.Successo)
                {
                    vino = (ArrayList<Vino>) r.getPayload();
                    ObservableList<Vino> vini = FXCollections.observableArrayList(vino);

                    ColonnaID_Vino.setCellValueFactory(cellData ->{
                        int ID_vino = cellData.getValue().getID();
                        IntegerProperty proprieta_id_vino = new SimpleIntegerProperty(ID_vino);
                        return Bindings.createObjectBinding(proprieta_id_vino::get, proprieta_id_vino);
                    });

                    ColonnaNome_Vino.setCellValueFactory(cellData ->{
                        String nome_vino = cellData.getValue().getNome();
                        StringProperty proprieta_nome_vino = new SimpleStringProperty(nome_vino);
                        return Bindings.createObjectBinding(proprieta_nome_vino::get, proprieta_nome_vino);
                    });

                    ColonnaAnno_Vino.setCellValueFactory(cellData ->{
                        int anno_vino = cellData.getValue().getAnno();
                        IntegerProperty proprieta_anno_vino = new SimpleIntegerProperty(anno_vino);
                        return Bindings.createObjectBinding(proprieta_anno_vino::get, proprieta_anno_vino);
                    });

                    ColonnaVitigno_Vino.setCellValueFactory(cellData ->{
                        String vitigno_vino = cellData.getValue().getVitigno();
                        StringProperty proprieta_vitigno_vino = new SimpleStringProperty(vitigno_vino);
                        return Bindings.createObjectBinding(proprieta_vitigno_vino::get, proprieta_vitigno_vino);
                    });

                    ColonnaPrezzo_Vino.setCellValueFactory(cellData -> {
                        double prezzo_vino = cellData.getValue().getPrezzo();
                        DoubleProperty proprieta_prezzo_vino = new SimpleDoubleProperty(prezzo_vino);
                        return Bindings.createObjectBinding(proprieta_prezzo_vino::get, proprieta_prezzo_vino);
                    });

                    ColonnaInfo_Vino.setCellFactory(param -> new TableCell<>() {
                        final Button btnInfo_vino = new Button("Info");

                        {setAlignment(Pos.CENTER);}

                        @Override
                        protected void updateItem(Void item, boolean empty) {
                            super.updateItem(item, empty);

                            if(empty)
                            {
                                setGraphic(null);
                                setText(null);
                            } else {
                                btnInfo_vino.getStyleClass().add("btn");
                                btnInfo_vino.setOnAction(event -> {
                                    VinoSelezionato = TV_RicercaVini.getSelectionModel().getSelectedItem();

                                    if(VinoSelezionato != null)
                                    {
                                        AnchorPane_Home.setVisible(false);
                                        AnchorPane_Profilo.setVisible(false);
                                        AnchorPane_AddImpiegati.setVisible(false);
                                        AnchorPane_Ricerca.setVisible(false);
                                        AnchorPane_Ordini.setVisible(false);
                                        AnchorPane_Report.setVisible(false);
                                        AnchorPane_DatiVino.setVisible(true);
                                        AnchorPane_DatiUtente.setVisible(false);
                                        AnchorPane_DettagliOrdine.setVisible(false);

                                        id_vino = ColonnaID_Vino.getCellData(VinoSelezionato);
                                        System.out.println("ID: " + id_vino);

                                        lblNome_Vino.setText(VinoSelezionato.getNome());
                                        lblProvenienza_Vino.setText(VinoSelezionato.getProvenienza());
                                        lblAnno_Vino.setText(String.valueOf(VinoSelezionato.getAnno()));
                                        lblVitigno_Vino.setText(VinoSelezionato.getVitigno());
                                        lblPrezzo_Vino.setText(String.valueOf(VinoSelezionato.getPrezzo()));
                                        lblSoglia_Vino.setText(String.valueOf(VinoSelezionato.getSoglia()));
                                        lblQuantita_Vino.setText(String.valueOf(VinoSelezionato.getQuantita()));
                                        lblDescrizione_Vino.setText(VinoSelezionato.getDescrizione());

                                        immagine = new Image(VinoSelezionato.getImmagine());
                                        //System.out.println(VinoSelezionato.getImmagine());
                                        imgBottglia_Vino.setImage(immagine);
                                    }
                                });
                                setGraphic(btnInfo_vino);
                                setText(null);
                            }
                        }
                    });
                    TV_RicercaVini.setItems(vini);

                } else {
                    System.out.println("Il vino non è presente nel DB");
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Ricerca vino");
                    alert.setHeaderText("Si è verificato un'errore.");
                    alert.setContentText("Il vino non è presente nel DB");
                    alert.showAndWait();
                }
            } catch (Exception e)
            {
                System.out.println(e);
                throw new RuntimeException(e);
            }
            txtRicerca_Ricerca.clear();
            txtRicercaInt_Ricerca.clear();
        } else if (tipo_dato.equals("Impiegati"))
        {
            txtRicerca_Ricerca.setVisible(true);
            txtRicercaInt_Ricerca.setVisible(false);
            dato_cercato = this.txtRicerca_Ricerca.getText();

            try {
                r = this.requestController.makeRequest(Costanti.Ricerca_Impiegati, new Utente(dato_cercato));

                if(r.getStatusCode() == Costanti.Successo)
                {
                    impiegato = (ArrayList<Utente>) r.getPayload();
                    ObservableList<Utente> impiegati = FXCollections.observableArrayList(impiegato);

                    ColonnaID_Impiegati.setCellValueFactory(cellData ->{
                        int ID_impiegato = cellData.getValue().getId();
                        IntegerProperty proprieta_id_impiegato = new SimpleIntegerProperty(ID_impiegato);
                        return Bindings.createObjectBinding(proprieta_id_impiegato::get, proprieta_id_impiegato);
                    });

                    ColonnaNome_Impiegati.setCellValueFactory(cellData ->{
                        String nome_impiegato = cellData.getValue().getNome();
                        StringProperty proprieta_nome_impiegato = new SimpleStringProperty(nome_impiegato);
                        return Bindings.createObjectBinding(proprieta_nome_impiegato::get, proprieta_nome_impiegato);
                    });

                    ColonnaCognome_Impiegati.setCellValueFactory(cellData ->{
                        String cognome_impiegato = cellData.getValue().getCognome();
                        StringProperty proprieta_cognome_impiegato = new SimpleStringProperty(cognome_impiegato);
                        return Bindings.createObjectBinding(proprieta_cognome_impiegato::get, proprieta_cognome_impiegato);
                    });

                    ColonnaUsername_Impiegati.setCellValueFactory(cellData ->{
                        String username_impiegato = cellData.getValue().getUsername();
                        StringProperty proprieta_username_impiegato = new SimpleStringProperty(username_impiegato);
                        return Bindings.createObjectBinding(proprieta_username_impiegato::get, proprieta_username_impiegato);
                    });

                    ColonnaEmail_Impiegati.setCellValueFactory(cellData ->{
                        String email_impiegato = cellData.getValue().getEmail();
                        StringProperty proprieta_email_impiegato = new SimpleStringProperty(email_impiegato);
                        return Bindings.createObjectBinding(proprieta_email_impiegato::get, proprieta_email_impiegato);
                    });

                    ColonnaGestione_Impiegati.setCellFactory(param -> new TableCell<>() {
                        final Button btnGestione_Impiegato = new Button("Gestione");

                        {setAlignment(Pos.CENTER);}

                        @Override
                        protected void updateItem(Void item, boolean empty) {
                            super.updateItem(item, empty);

                            if(empty)
                            {
                                setGraphic(null);
                                setText(null);
                            } else {
                                btnGestione_Impiegato.getStyleClass().add("btn");
                                btnGestione_Impiegato.setOnAction(event -> {
                                    ImipegatoSelezionato = TV_RicercaImpiegati.getSelectionModel().getSelectedItem();

                                    if(ImipegatoSelezionato != null)
                                    {
                                        AnchorPane_Home.setVisible(false);
                                        AnchorPane_Profilo.setVisible(false);
                                        AnchorPane_AddImpiegati.setVisible(false);
                                        AnchorPane_Ricerca.setVisible(false);
                                        AnchorPane_Ordini.setVisible(false);
                                        AnchorPane_Report.setVisible(false);
                                        AnchorPane_DatiVino.setVisible(false);
                                        AnchorPane_DatiUtente.setVisible(true);
                                        btnEliminaImpiegati.setVisible(true);
                                        AnchorPane_DettagliOrdine.setVisible(false);

                                        id_impiegato = ColonnaID_Impiegati.getCellData(ImipegatoSelezionato);
                                        System.out.println("ID: " + id_impiegato);

                                        lblID_Utente.setText(String.valueOf(ImipegatoSelezionato.getId()));
                                        lblNome_Utente.setText(ImipegatoSelezionato.getNome());
                                        lblCognome_Utente.setText(ImipegatoSelezionato.getCognome());
                                        lblEmail_Utente.setText(ImipegatoSelezionato.getEmail());
                                        lblTelefono_Utente.setText(ImipegatoSelezionato.getTelefono());
                                        lblIndirizzo_Utente.setText(ImipegatoSelezionato.getIndirizzo());
                                        lblUsername_Utente.setText(ImipegatoSelezionato.getUsername());
                                        lblCF_Utente.setText(ImipegatoSelezionato.getCf());
                                    }
                                });
                                setGraphic(btnGestione_Impiegato);
                                setText(null);
                            }
                        }
                    });
                    TV_RicercaImpiegati.setItems(impiegati);
                } else {
                    System.out.println("L'impiegato non è presente nel DB");
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Ricerca impiegato");
                    alert.setHeaderText("Si è verificato un'errore.");
                    alert.setContentText("L'impiegato non è presente nel DB");
                    alert.showAndWait();
                }
            } catch (Exception e)
            {
                System.out.println(e);
                throw new RuntimeException(e);
            }
            txtRicerca_Ricerca.clear();
            txtRicercaInt_Ricerca.clear();
        } else if (tipo_dato.equals("Clienti"))
        {
            txtRicerca_Ricerca.setVisible(true);
            txtRicercaInt_Ricerca.setVisible(false);
            dato_cercato = this.txtRicerca_Ricerca.getText();

            try {
                r = this.requestController.makeRequest(Costanti.Ricerca_Clienti, new Utente(dato_cercato, true));

                if(r.getStatusCode() == Costanti.Successo)
                {
                    cliente = (ArrayList<Utente>) r.getPayload();
                    ObservableList<Utente> clienti = FXCollections.observableArrayList(cliente);

                    ColonnaID_Cliente.setCellValueFactory(cellData ->{
                        int ID_cliente = cellData.getValue().getId();
                        IntegerProperty proprieta_id_cliente = new SimpleIntegerProperty(ID_cliente);
                        return Bindings.createObjectBinding(proprieta_id_cliente::get, proprieta_id_cliente);
                    });

                    ColonnaNome_Cliente.setCellValueFactory(cellData ->{
                        String nome_cliente = cellData.getValue().getNome();
                        StringProperty proprieta_nome_cliente = new SimpleStringProperty(nome_cliente);
                        return Bindings.createObjectBinding(proprieta_nome_cliente::get, proprieta_nome_cliente);
                    });

                    ColonnaCognome_Cliente.setCellValueFactory(cellData ->{
                        String cognome_cliente = cellData.getValue().getCognome();
                        StringProperty proprieta_cognome_cliente = new SimpleStringProperty(cognome_cliente);
                        return Bindings.createObjectBinding(proprieta_cognome_cliente::get, proprieta_cognome_cliente);
                    });

                    ColonnaUsername_Cliente.setCellValueFactory(cellData ->{
                        String username_cliente = cellData.getValue().getUsername();
                        StringProperty proprieta_username_cliente = new SimpleStringProperty(username_cliente);
                        return Bindings.createObjectBinding(proprieta_username_cliente::get, proprieta_username_cliente);
                    });

                    ColonnaEmail_Cliente.setCellValueFactory(cellData ->{
                        String email_cliente = cellData.getValue().getEmail();
                        StringProperty proprieta_email_cliente = new SimpleStringProperty(email_cliente);
                        return Bindings.createObjectBinding(proprieta_email_cliente::get, proprieta_email_cliente);
                    });

                    ColonnaInfo_Cliente.setCellFactory(param -> new TableCell<>() {
                        final Button btnGestione_Cliente = new Button("Info");

                        {setAlignment(Pos.CENTER);}

                        @Override
                        protected void updateItem(Void item, boolean empty) {
                            super.updateItem(item, empty);

                            if(empty)
                            {
                                setGraphic(null);
                                setText(null);
                            } else {
                                btnGestione_Cliente.getStyleClass().add("btn");
                                btnGestione_Cliente.setOnAction(event -> {
                                    ClienteSelezionato = TV_RicercaClienti.getSelectionModel().getSelectedItem();

                                    if(ClienteSelezionato != null)
                                    {
                                        AnchorPane_Home.setVisible(false);
                                        AnchorPane_Profilo.setVisible(false);
                                        AnchorPane_AddImpiegati.setVisible(false);
                                        AnchorPane_Ricerca.setVisible(false);
                                        AnchorPane_Ordini.setVisible(false);
                                        AnchorPane_Report.setVisible(false);
                                        AnchorPane_DatiVino.setVisible(false);
                                        AnchorPane_DatiUtente.setVisible(true);
                                        btnEliminaImpiegati.setVisible(false);
                                        AnchorPane_DettagliOrdine.setVisible(false);

                                        id_cliente = ColonnaID_Cliente.getCellData(ClienteSelezionato);
                                        System.out.println("ID: " + id_cliente);

                                        lblID_Utente.setText(String.valueOf(ClienteSelezionato.getId()));
                                        lblNome_Utente.setText(ClienteSelezionato.getNome());
                                        lblCognome_Utente.setText(ClienteSelezionato.getCognome());
                                        lblEmail_Utente.setText(ClienteSelezionato.getEmail());
                                        lblTelefono_Utente.setText(ClienteSelezionato.getTelefono());
                                        lblIndirizzo_Utente.setText(ClienteSelezionato.getIndirizzo());
                                        lblUsername_Utente.setText(ClienteSelezionato.getUsername());
                                        lblCF_Utente.setText(ClienteSelezionato.getCf());
                                    }
                                });
                                setGraphic(btnGestione_Cliente);
                                setText(null);
                            }
                        }
                    });
                    TV_RicercaClienti.setItems(clienti);
                } else {
                    System.out.println("Il cliente non è presente nel DB");
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Ricerca cliente");
                    alert.setHeaderText("Si è verificato un'errore.");
                    alert.setContentText("Il cliente non è presente nel DB");
                    alert.showAndWait();
                }
            } catch (Exception e)
            {
                System.out.println(e);
                throw new RuntimeException(e);
            }
            txtRicerca_Ricerca.clear();
            txtRicercaInt_Ricerca.clear();
        } else if (tipo_dato.equals("Offerte"))
        {
            txtRicerca_Ricerca.setVisible(false);
            txtRicercaInt_Ricerca.setVisible(true);
            dato_cercato = this.txtRicercaInt_Ricerca.getText();

            if(dato_cercato.matches("[a-zA-Z\\s]+"))
            {
                System.out.println("Errore ricerca offerta");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ID offerta");
                alert.setHeaderText("Si è verificato un'errore.");
                alert.setContentText("L'ID di un'offerta può essere solo un numero intero.\nPrego reiserire.");
                alert.showAndWait();
            } else {
                dato_cercato_int = Integer.parseInt(dato_cercato);

                try {
                    r = this.requestController.makeRequest(Costanti.Ricerca_Offerte, new Offerta(dato_cercato_int));

                    if(r.getStatusCode() == Costanti.Successo)
                    {
                        offerta = (ArrayList<Offerta>) r.getPayload();
                        ObservableList<Offerta> offerte = FXCollections.observableArrayList(offerta);

                        ColonnaID_Offerta.setCellValueFactory(cellData ->{
                            int ID_offerta = cellData.getValue().getID();
                            IntegerProperty proprieta_id_offerta = new SimpleIntegerProperty(ID_offerta);
                            return Bindings.createObjectBinding(proprieta_id_offerta::get, proprieta_id_offerta);
                        });

                        ColonnaDescrizione_Offerta.setCellValueFactory(cellData ->{
                            String descrizione_offerta = cellData.getValue().getDescrizione();
                            StringProperty proprieta_descrizione_offerta = new SimpleStringProperty(descrizione_offerta);
                            return Bindings.createObjectBinding(proprieta_descrizione_offerta::get, proprieta_descrizione_offerta);
                        });

                        ColonnaSconto_Offerta.setCellValueFactory(cellData ->{
                            int sconto_offerta = cellData.getValue().getSconto();
                            IntegerProperty proprieta_sconto_offerta = new SimpleIntegerProperty(sconto_offerta);
                            return Bindings.createObjectBinding(proprieta_sconto_offerta::get, proprieta_sconto_offerta);
                        });

                        ColonnaVino_Offerta.setCellValueFactory(cellData ->{
                            String vino_offerta = cellData.getValue().getNome();
                            StringProperty proprieta_vino_offerta = new SimpleStringProperty(vino_offerta);
                            return Bindings.createObjectBinding(proprieta_vino_offerta::get, proprieta_vino_offerta);
                        });

                        ColonnaGestione_Offerta.setCellFactory(param -> new TableCell<>() {
                            final Button btnGestione_Offerta = new Button("Gestione");

                            {setAlignment(Pos.CENTER);}

                            @Override
                            protected void updateItem(Void item, boolean empty) {
                                super.updateItem(item, empty);

                                if(empty)
                                {
                                    setGraphic(null);
                                    setText(null);
                                } else {
                                    btnGestione_Offerta.getStyleClass().add("btn");
                                    btnGestione_Offerta.setOnAction(event -> {
                                        OffertaSelezionata = TV_RicercaOfferte.getSelectionModel().getSelectedItem();

                                        if(OffertaSelezionata != null)
                                        {
                                            AnchorPane_Home.setVisible(false);
                                            AnchorPane_Profilo.setVisible(false);
                                            AnchorPane_AddImpiegati.setVisible(false);
                                            AnchorPane_Ricerca.setVisible(false);
                                            AnchorPane_Ordini.setVisible(false);
                                            AnchorPane_Report.setVisible(false);
                                            AnchorPane_DatiVino.setVisible(false);
                                            AnchorPane_DatiUtente.setVisible(false);
                                            btnEliminaImpiegati.setVisible(false);
                                            AnchorPane_GestioneOfferta.setVisible(true);
                                            AnchorPane_DettagliOrdine.setVisible(false);

                                            id_offerta = ColonnaID_Offerta.getCellData(OffertaSelezionata);
                                            System.out.println("ID: " + id_offerta);

                                            lblID_Offerta.setText(String.valueOf(OffertaSelezionata.getID()));
                                            lblSconto_Offerta.setText(String.valueOf(OffertaSelezionata.getSconto()));
                                            lblVino_Offerta.setText(OffertaSelezionata.getNome());
                                            lblDescrizione_Offerta.setText(OffertaSelezionata.getDescrizione());
                                        }
                                    });
                                    setGraphic(btnGestione_Offerta);
                                    setText(null);
                                }
                            }
                        });
                        TV_RicercaOfferte.setItems(offerte);
                    } else {
                        System.out.println("L'offerta non è presente nel DB");
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Ricerca offerta");
                        alert.setHeaderText("Si è verificato un'errore.");
                        alert.setContentText("L'offerta non è presente nel DB");
                        alert.showAndWait();
                    }
                } catch (Exception e)
                {
                    System.out.println(e);
                    throw new RuntimeException(e);
                }
            }
            txtRicerca_Ricerca.clear();
            txtRicercaInt_Ricerca.clear();
        }
    }
    @FXML
    void OnBtnEliminaImpiegato_Click(ActionEvent event) {
        try {
            Response r = this.requestController.makeRequest(Costanti.Elimina_Impiegato, new Utente(id_impiegato));

            if (r.getStatusCode() == Costanti.Successo)
            {
                System.out.println("Impiegato eliminato con successo");
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Impiegato eliminato");
                alert.setHeaderText("Impiegato eliminato con successo");
                alert.setContentText("Impiegato eliminato con successo");
                alert.showAndWait();

                AnchorPane_Home.setVisible(true);
                AnchorPane_Profilo.setVisible(false);
                AnchorPane_AddImpiegati.setVisible(false);
                AnchorPane_Ricerca.setVisible(false);
                AnchorPane_Ordini.setVisible(false);
                AnchorPane_Report.setVisible(false);
                AnchorPane_DatiVino.setVisible(false);
                AnchorPane_DatiUtente.setVisible(false);
                AnchorPane_GestioneOfferta.setVisible(false);
                AnchorPane_DettagliOrdine.setVisible(false);
                TV_RicercaImpiegati.refresh();
                TV_RicercaImpiegati.setVisible(false);
            } else {
                System.out.println("Errore");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Ricerca eliminazione impiegato");
                alert.setHeaderText("Impossibile eliminare l'impiegato.");
                alert.setContentText("Impossibile eliminare l'impiegato.");
                alert.showAndWait();
            }
        } catch (Exception e)
        {
            System.out.println(e);
            throw new RuntimeException(e);
        }
    }

    //button che elimina un'offerta
    @FXML
    void OnBtnEliminaOfferta_Click(ActionEvent event) {
        try {
            Response r = this.requestController.makeRequest(Costanti.Elimina_Offerta, new Offerta(id_offerta));

            if (r.getStatusCode() == Costanti.Successo)
            {
                System.out.println("Offerta eliminata con successo");
                Alert alert = new Alert(Alert.AlertType.INFORMATION);       //pop-up nel caso sia andato a buon fine
                alert.setTitle("Offerta eliminata");
                alert.setHeaderText("Offerta eliminata con successo");
                alert.setContentText("Offerta eliminata con successo");
                alert.showAndWait();

                AnchorPane_Home.setVisible(true);
                AnchorPane_Profilo.setVisible(false);
                AnchorPane_AddImpiegati.setVisible(false);
                AnchorPane_Ricerca.setVisible(false);
                AnchorPane_Ordini.setVisible(false);
                AnchorPane_Report.setVisible(false);
                AnchorPane_DatiVino.setVisible(false);
                AnchorPane_DatiUtente.setVisible(false);
                AnchorPane_GestioneOfferta.setVisible(false);
                AnchorPane_DettagliOrdine.setVisible(false);
                TV_RicercaOfferte.refresh();
                TV_RicercaOfferte.setVisible(false);
            } else {
                System.out.println("Errore");
                Alert alert = new Alert(Alert.AlertType.ERROR);                 //alert errore nel caso non sia andato a buon fine
                alert.setTitle("Ricerca eliminazione offerta");
                alert.setHeaderText("Impossibile eliminare l'offerta.");
                alert.setContentText("Impossibile eliminare l'offerta.");
                alert.showAndWait();
            }
        } catch (Exception e)
        {
            System.out.println(e);
            throw new RuntimeException(e);
        }
    }
    //controllo validità codice fiscale
    public static boolean CF_Check(String cf) {
        Pattern pattern = Pattern.compile("^[A-Z]{6}\\d{2}[A-Z]\\d{2}[A-Z]\\d{3}[A-Z]$");
        Matcher matcher = pattern.matcher(cf);

        return matcher.matches();
    }

    //funzione che controlla che tutti i campi siano stati compilati
    private void Errore_Campi_Vuoti(String s) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Errore campi vuoti");
        alert.setHeaderText("Si è verificato un'errore.");
        alert.setContentText("Tutti i campi devono essere compilati! " + s);
        alert.showAndWait();

        txtNome_AddImpiegati.clear();
        txtCognome_AddImpiegati.clear();
        txtIndirizzo_AddImpiegati.clear();
        txtTelefono_AddImpiegati.clear();
        txtCF_AddImpiegati.clear();
        txtEmail_AddImpiegati.clear();
        txtUsername_AddImpiegati.clear();
        txtPassword_AddImpiegati.clear();
        txtConfPassword_AddImpiegati.clear();
    }

    //funzione che verifica la corretta forma della mail
    private void Errore_Email() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Errore email");
        alert.setHeaderText("Si è verificato un'errore.");
        alert.setContentText("La mail deve contenere una @ e non può essere vuota.");
        alert.showAndWait();

        txtEmail_AddImpiegati.clear();
    }

    private void Errore_Password() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Errore password");
        alert.setHeaderText("Si è verificato un'errore.");
        alert.setContentText("Le password non coincidono");
        alert.showAndWait();

        txtPassword_AddImpiegati.clear();
        txtConfPassword_AddImpiegati.clear();
    }
    public void initialize() {
        ObservableList<String> options = FXCollections.observableArrayList("Vini", "Clienti", "Impiegati", "Offerte");
        CBSceltaDati_Ricerca.setItems(options);
    }
}
