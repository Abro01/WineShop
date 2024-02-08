package com.wineshop.client;

import javafx.beans.binding.Bindings;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import utilities.EmptyPayload;
import utilities.models.*;
import utilities.Costanti;
import utilities.Response;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.ArrayList;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class HomeClienteController {
    private RequestController requestController;
    private Utente UtenteLoggato;
    @FXML
    private AnchorPane AnchorPane_Carrello;
    @FXML
    private AnchorPane AnchorPane_Email;
    @FXML
    private AnchorPane AnchorPane_Assistenza;
    @FXML
    private AnchorPane AnchorPane_DatiOrdine;
    @FXML
    private AnchorPane AnchorPane_DatiVino;
    @FXML
    private AnchorPane AnchorPane_DettagliOrdine;
    @FXML
    private AnchorPane AnchorPane_DatiOfferta;
    @FXML
    private AnchorPane AnchorPane_DatiRecensioni;
    @FXML
    private AnchorPane AnchorPane_Home;
    @FXML
    private AnchorPane AnchorPane_Offerte;
    @FXML
    private AnchorPane AnchorPane_Ordini;
    @FXML
    private AnchorPane AnchorPane_Profilo;
    @FXML
    private AnchorPane AnchorPane_Recensione;
    @FXML
    private AnchorPane AnchorPane_Ricerca;
    @FXML
    private AnchorPane AnchorPane_Preferiti;
    @FXML
    private ComboBox<String> CBSceltaMP_Carrello;
    @FXML
    private ComboBox<String> CBVotoVino_Recensione;
    @FXML
    private ComboBox<String> CBSceltaStato_Ricerca;
    @FXML
    private ComboBox<String> CBVotoVino_DatiRecensioni;
    @FXML
    private TableColumn<Offerta, Void> ColonnaApplica_Offerta;
    @FXML
    private TableColumn<Vino, Void> ColonnaBtnCarrello_Vino;
    @FXML
    private TableColumn<Offerta, String> ColonnaDescrizione_Offerta;
    @FXML
    private TableColumn<Vino, Integer> ColonnaID_Vino;
    @FXML
    private TableColumn<Offerta, Integer> ColonnaID_Offerta;
    @FXML
    private TableColumn<Vino, String> ColonnaNome_Vino;
    @FXML
    private TableColumn<Vino, Double> ColonnaPrezzo_Vino;
    @FXML
    private TableColumn<Vino, String> ColonnaProvenienza_Vino;
    @FXML
    private TableColumn<Vino, Void> ColonnaRecensione_Vino;
    @FXML
    private TableColumn<Offerta, Integer> ColonnaSconto_Offerta;
    @FXML
    private TableColumn<Offerta, Integer> ColonnaVino_Offerta;
    @FXML
    private TableColumn<DettagliOrdine, Integer> IDVino_Carrello;
    @FXML
    private TableColumn<DettagliOrdine, Integer> ID_DettagliOrdine;
    @FXML
    private TableColumn<Ordine, Integer> ID_Ordini;
    @FXML
    private TableColumn<Ordine, String> Indirizzo_Ordini;
    @FXML
    private TableColumn<Ordine, Void> Info_Ordini;
    @FXML
    private TableColumn<Ordine, String> MP_Ordini;
    @FXML
    private TableColumn<DettagliOrdine, Integer> Quantita_Carrello;
    @FXML
    private TableColumn<DettagliOrdine, Integer> Quantita_DettagliOrdine;
    @FXML
    private TableColumn<Ordine, String> Stato_Ordini;
    @FXML
    private TableView<DettagliOrdine> TV_Carrello;
    @FXML
    private TableView<DettagliOrdine> TV_DettagliOrdine;
    @FXML
    private TableView<Ordine> TV_Ordini;
    @FXML
    private TableView<Vino> TV_Ricerca;
    @FXML
    private TableView<Offerta> TV_RicercaOfferte;
    @FXML
    private TableColumn<Ordine, Double> Totale_Ordini;
    @FXML
    private TableColumn<DettagliOrdine, Integer> Vino_Carrello;
    @FXML
    private TableColumn<DettagliOrdine, Integer> Ordine_Carrello;
    @FXML
    private TableColumn<DettagliOrdine, Integer> Vino_DettagliOrdine;
    @FXML
    private TableColumn<Preferito, Void> ColonnaElimina_Preferiti;
    @FXML
    private TableColumn<Preferito, Integer> ColonnaID_Preferiti;
    @FXML
    private TableColumn<Preferito, Integer> ColonnaVino_Preferiti;
    @FXML
    private TableView<Preferito> TV_Preferiti;
    @FXML
    private TableColumn<Recensione, String> ColonnaCommento_DatiRecensioni;
    @FXML
    private TableColumn<Recensione, Void> ColonnaElimina_DatiRecensioni;
    @FXML
    private TableColumn<Recensione, Integer> ColonnaID_DatiRecensioni;
    @FXML
    private TableColumn<Recensione, Integer> ColonnaVino_DatiRecensioni;
    @FXML
    private TableColumn<Recensione, String> ColonnaVoto_DatiRecensioni;
    @FXML
    private TableView<Recensione> TV_DatiRecensioni;
    @FXML
    private TableColumn<Utente, Void> ColonnaContatta_Assistenza;
    @FXML
    private TableColumn<Utente, String> ColonnaEmailImpiegato_Assistenza;
    @FXML
    private TableColumn<Utente, String> ColonnaNomeImpiegato_Assistenza;
    @FXML
    private TableColumn<Utente, Integer> ColonnaIDImpiegati_Assistenza;
    @FXML
    private TableView<Utente> TV_Assistenza;
    @FXML
    private Button btnAcquista_Carrello;
    @FXML
    private Button btnAddRecensione_Recensione;
    @FXML
    private Button btnAggiungiCarrello_Vino;
    @FXML
    private Button btnRicerca_Preferiti;
    @FXML
    private Button btnPreferiti_Cliente;
    @FXML
    private Button btnCarrello_Cliente;
    @FXML
    private Button btnHome_Cliente;
    @FXML
    private Button btnLogout_Cliente;
    @FXML
    private Button btnOrdini_Cliente;
    @FXML
    private Button btnPreferito_Vino;
    @FXML
    private Button btnProfilo_Cliente;
    @FXML
    private Button btnPromozioni_Cliente;
    @FXML
    private Button btnRecensione_Cliente;
    @FXML
    private Button btnRicerca_Cliente;
    @FXML
    private Button btnRicerca_Offerte;
    @FXML
    private Button btnRicerca_Ordini;
    @FXML
    private Button btnRicerca_Ricerca;
    @FXML
    private Button btnVisualizzaDettagliOrdine;
    @FXML
    private Button btnRicerca_DatiRecensioni;
    @FXML
    private Button btnAssistenza_Cliente;
    @FXML
    private Button btnInviaEmail;
    @FXML
    private ImageView imgBottglia_Vino;
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
    private Label lblID_Ordine;
    @FXML
    private Label lblIndirizzo_Ordine;
    @FXML
    private Label lblIndirizzo_Profilo;
    @FXML
    private Label lblMP_Ordine;
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
    private Label lblStato_Ordine;
    @FXML
    private Label lblTelefono_Profilo;
    @FXML
    private Label lblTotale_Carrello;
    @FXML
    private Label lblTotale_Ordine;
    @FXML
    private Label lblUsername_Profilo;
    @FXML
    private Label lblVitigno_Vino;
    @FXML
    private Label lblDescrizione_DatiOfferta;
    @FXML
    private Label lblID_DatiOfferta;
    @FXML
    private Label lblSconto_DatiOfferta;
    @FXML
    private Label lblVino_DatiOfferta;
    @FXML
    private Label lblDestinatario_Email;
    @FXML
    private TextField txtIndirizzo_Carrello;
    @FXML
    private TextField txtQuantita_Vino;
    @FXML
    private TextArea txtRecensione_Recensione;
    @FXML
    private TextArea txtEmail;
    @FXML
    private TextField txtRicerca_Offerte;
    @FXML
    private TextField txtRicerca_Preferiti;
    @FXML
    private TextField txtOggetto_Email;
    @FXML
    private TextField txtRicerca_Ricerca;
    private ArrayList<DettagliOrdine> dettagli_ordine;
    private ArrayList<Vino> vino;
    private ArrayList<Ordine> ordine;
    private ArrayList<Offerta> offerta, offerta_popup;
    private ArrayList<Preferito> preferito;
    private ArrayList<Recensione> recensione;
    private ArrayList<DettagliOrdine> dettaglio_carrello;
    private ArrayList<Ordine> codice_ordine;
    private ArrayList<Utente> utente;
    private Vino VinoSelezionato;
    private Offerta OffertaSelezionata;
    private Ordine OrdineSelezionato;
    private Preferito PreferitoSelezionato;
    private Recensione RecensioneSelezionata;
    private Utente UtenteSelezionato;
    private int id_ordine = 0, id_vino = 0, id_offerta = 0, id_vino_offerta = 0, id_preferito = 0, id_recensione = 0, id_dettagli_carrello = 0, soglia_vino = 0, id_impiegato = 0;
    private Image immagine;
    private String tipo_dato, email_contatto;
    private double totale = 0, prezzo_vino = 0, prezzo_scontato = 0, prezzo_scontato_definitivo = 0, quantita_vino_aggiornata = 0;

    public void setRequestController(RequestController controller) {
        this.requestController = controller;
    }

    public void setLoggedUser(Utente utente) {
        this.UtenteLoggato = utente;
    }

    //home cliente
    @FXML
    void OnBtnHomeCliente_Click(ActionEvent event) {
        AnchorPane_Home.setVisible(true);
        AnchorPane_Ricerca.setVisible(false);
        AnchorPane_Carrello.setVisible(false);
        AnchorPane_Offerte.setVisible(false);
        AnchorPane_Ordini.setVisible(false);
        AnchorPane_Preferiti.setVisible(false);
        AnchorPane_Recensione.setVisible(false);
        AnchorPane_Profilo.setVisible(false);
        AnchorPane_DettagliOrdine.setVisible(false);
        AnchorPane_DatiVino.setVisible(false);
        AnchorPane_DatiOrdine.setVisible(false);
        AnchorPane_DatiOfferta.setVisible(false);
        AnchorPane_DatiRecensioni.setVisible(false);
        AnchorPane_Assistenza.setVisible(false);
        AnchorPane_Email.setVisible(false);
    }

    @FXML
    void OnBtnRicercaCliente_Click(ActionEvent event) {
        txtRicerca_Ricerca.clear();

        TV_Ricerca.refresh();

        AnchorPane_Home.setVisible(false);
        AnchorPane_Ricerca.setVisible(true);
        AnchorPane_Carrello.setVisible(false);
        AnchorPane_Offerte.setVisible(false);
        AnchorPane_Ordini.setVisible(false);
        AnchorPane_Preferiti.setVisible(false);
        AnchorPane_Recensione.setVisible(false);
        AnchorPane_Profilo.setVisible(false);
        AnchorPane_DettagliOrdine.setVisible(false);
        AnchorPane_DatiVino.setVisible(false);
        AnchorPane_DatiOrdine.setVisible(false);
        AnchorPane_DatiOfferta.setVisible(false);
        AnchorPane_DatiRecensioni.setVisible(false);
        AnchorPane_Assistenza.setVisible(false);
        AnchorPane_Email.setVisible(false);

        try {
            Response r = this.requestController.makeRequest(Costanti.Mostra_Vini, new EmptyPayload());

            if (r.getStatusCode() == Costanti.Successo) {
                vino = (ArrayList<Vino>) r.getPayload();
                ObservableList<Vino> vini = FXCollections.observableArrayList(vino);

                ColonnaID_Vino.setCellValueFactory(cellData -> {
                    int ID_vino = cellData.getValue().getID();
                    IntegerProperty proprieta_id_vino = new SimpleIntegerProperty(ID_vino);
                    return Bindings.createObjectBinding(proprieta_id_vino::get, proprieta_id_vino);
                });

                ColonnaNome_Vino.setCellValueFactory(cellData -> {
                    String nome_vino = cellData.getValue().getNome();
                    StringProperty proprieta_nome_vino = new SimpleStringProperty(nome_vino);
                    return Bindings.createObjectBinding(proprieta_nome_vino::get, proprieta_nome_vino);
                });

                ColonnaProvenienza_Vino.setCellValueFactory(cellData -> {
                    String provenienza_vino = cellData.getValue().getProvenienza();
                    StringProperty proprieta_provenienza_vino = new SimpleStringProperty(provenienza_vino);
                    return Bindings.createObjectBinding(proprieta_provenienza_vino::get, proprieta_provenienza_vino);
                });

                ColonnaPrezzo_Vino.setCellValueFactory(cellData -> {
                    double prezzo_vino = cellData.getValue().getPrezzo();
                    DoubleProperty proprieta_prezzo_vino = new SimpleDoubleProperty(prezzo_vino);
                    return Bindings.createObjectBinding(proprieta_prezzo_vino::get, proprieta_prezzo_vino);
                });

                ColonnaBtnCarrello_Vino.setCellFactory(param -> new TableCell<>() {
                    final Button btnInfo_vino = new Button("Add Cart");

                    {
                        setAlignment(Pos.CENTER);
                    }

                    @Override
                    protected void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);

                        if (empty) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            btnInfo_vino.getStyleClass().add("btn");
                            btnInfo_vino.setOnAction(event -> {
                                VinoSelezionato = TV_Ricerca.getSelectionModel().getSelectedItem();

                                if (VinoSelezionato != null) {
                                    AnchorPane_Home.setVisible(false);
                                    AnchorPane_Ricerca.setVisible(false);
                                    AnchorPane_Carrello.setVisible(false);
                                    AnchorPane_Offerte.setVisible(false);
                                    AnchorPane_Ordini.setVisible(false);
                                    AnchorPane_Preferiti.setVisible(false);
                                    AnchorPane_Recensione.setVisible(false);
                                    AnchorPane_Profilo.setVisible(false);
                                    AnchorPane_DettagliOrdine.setVisible(false);
                                    AnchorPane_DatiVino.setVisible(true);
                                    AnchorPane_DatiOrdine.setVisible(false);
                                    AnchorPane_DatiOfferta.setVisible(false);
                                    AnchorPane_DatiRecensioni.setVisible(false);
                                    AnchorPane_Assistenza.setVisible(false);
                                    AnchorPane_Email.setVisible(false);

                                    id_vino = ColonnaID_Vino.getCellData(VinoSelezionato);
                                    System.out.println("ID: " + id_vino);

                                    prezzo_vino = ColonnaPrezzo_Vino.getCellData(VinoSelezionato);
                                    System.out.println("Il prezzo del vino " + id_vino + " e': " + prezzo_vino);

                                    quantita_vino_aggiornata = VinoSelezionato.getQuantita();
                                    System.out.println("La quantita del vino " + id_vino + " e': " + quantita_vino_aggiornata);

                                    soglia_vino = VinoSelezionato.getSoglia();
                                    System.out.println("La soglia massima del vino " + id_vino + " e': " + soglia_vino);

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

                                    try {
                                        Response rs = requestController.makeRequest(Costanti.Verifica_Offerte_Vino, new Offerta(Double.parseDouble(String.valueOf(id_vino))));

                                        if(rs.getStatusCode() == Costanti.Successo)
                                        {
                                            offerta_popup = (ArrayList<Offerta>) rs.getPayload();

                                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                            alert.setTitle("OFFERTA");
                                            alert.setHeaderText("Se acquisti questo vini usufruirai di un'offerta del " + offerta_popup.get(0).getSconto() + "% di sconto");
                                            alert.setContentText("Se acquisti questo vini usufruirai di un'offerta del " + offerta_popup.get(0).getSconto() + "% di sconto");
                                            alert.showAndWait();
                                        }
                                    } catch (Exception e) {
                                        System.out.println(e);
                                        throw new RuntimeException(e);
                                    }
                                }
                            });
                            setGraphic(btnInfo_vino);
                            setText(null);
                        }
                    }
                });

                ColonnaRecensione_Vino.setCellFactory(param -> new TableCell<>() {
                    final Button btnInfo_vino = new Button("Recensisci");

                    {
                        setAlignment(Pos.CENTER);
                    }

                    @Override
                    protected void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);

                        if (empty) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            btnInfo_vino.getStyleClass().add("btn");
                            btnInfo_vino.setOnAction(event -> {
                                VinoSelezionato = TV_Ricerca.getSelectionModel().getSelectedItem();

                                if (VinoSelezionato != null) {
                                    AnchorPane_Home.setVisible(false);
                                    AnchorPane_Ricerca.setVisible(false);
                                    AnchorPane_Carrello.setVisible(false);
                                    AnchorPane_Offerte.setVisible(false);
                                    AnchorPane_Ordini.setVisible(false);
                                    AnchorPane_Preferiti.setVisible(false);
                                    AnchorPane_Recensione.setVisible(true);
                                    AnchorPane_Profilo.setVisible(false);
                                    AnchorPane_DettagliOrdine.setVisible(false);
                                    AnchorPane_DatiVino.setVisible(false);
                                    AnchorPane_DatiOrdine.setVisible(false);
                                    AnchorPane_DatiOfferta.setVisible(false);
                                    AnchorPane_DatiRecensioni.setVisible(false);
                                    AnchorPane_Assistenza.setVisible(false);
                                    AnchorPane_Email.setVisible(false);

                                    id_vino = ColonnaID_Vino.getCellData(VinoSelezionato);
                                    System.out.println("ID: " + id_vino);
                                }
                            });
                            setGraphic(btnInfo_vino);
                            setText(null);
                        }
                    }
                });
                TV_Ricerca.setItems(vini);
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("No vini alert");
                alert.setHeaderText("Non sono presenti vini.");
                alert.setContentText("Non sono presenti vini.");
                alert.showAndWait();
            }
        } catch (Exception e) {
            System.out.println(e);
            throw new RuntimeException(e);
        }
    }

    //ricerca i vini acquistabili
    @FXML
    void OnBtnRicercaViniCliente_Click(ActionEvent event) {
        TV_Ricerca.refresh();

        AnchorPane_Home.setVisible(false);
        AnchorPane_Ricerca.setVisible(true);
        AnchorPane_Carrello.setVisible(false);
        AnchorPane_Offerte.setVisible(false);
        AnchorPane_Ordini.setVisible(false);
        AnchorPane_Preferiti.setVisible(false);
        AnchorPane_Recensione.setVisible(false);
        AnchorPane_Profilo.setVisible(false);
        AnchorPane_DettagliOrdine.setVisible(false);
        AnchorPane_DatiVino.setVisible(false);
        AnchorPane_DatiOrdine.setVisible(false);
        AnchorPane_DatiOfferta.setVisible(false);
        AnchorPane_DatiRecensioni.setVisible(false);
        AnchorPane_Assistenza.setVisible(false);
        AnchorPane_Email.setVisible(false);

        String dato_cercato = this.txtRicerca_Ricerca.getText();

        try {
            Response r = this.requestController.makeRequest(Costanti.Ricerca_Vini, new Vino(dato_cercato));

            if (r.getStatusCode() == Costanti.Successo) {
                vino = (ArrayList<Vino>) r.getPayload();
                ObservableList<Vino> vini = FXCollections.observableArrayList(vino);

                ColonnaID_Vino.setCellValueFactory(cellData -> {
                    int ID_vino = cellData.getValue().getID();
                    IntegerProperty proprieta_id_vino = new SimpleIntegerProperty(ID_vino);
                    return Bindings.createObjectBinding(proprieta_id_vino::get, proprieta_id_vino);
                });

                ColonnaNome_Vino.setCellValueFactory(cellData -> {
                    String nome_vino = cellData.getValue().getNome();
                    StringProperty proprieta_nome_vino = new SimpleStringProperty(nome_vino);
                    return Bindings.createObjectBinding(proprieta_nome_vino::get, proprieta_nome_vino);
                });

                ColonnaProvenienza_Vino.setCellValueFactory(cellData -> {
                    String provenienza_vino = cellData.getValue().getProvenienza();
                    StringProperty proprieta_provenienza_vino = new SimpleStringProperty(provenienza_vino);
                    return Bindings.createObjectBinding(proprieta_provenienza_vino::get, proprieta_provenienza_vino);
                });

                ColonnaPrezzo_Vino.setCellValueFactory(cellData -> {
                    double prezzo_vino = cellData.getValue().getPrezzo();
                    DoubleProperty proprieta_prezzo_vino = new SimpleDoubleProperty(prezzo_vino);
                    return Bindings.createObjectBinding(proprieta_prezzo_vino::get, proprieta_prezzo_vino);
                });

                //button add cart
                ColonnaBtnCarrello_Vino.setCellFactory(param -> new TableCell<>() {
                    final Button btnInfo_vino = new Button("Add Cart");

                    {
                        setAlignment(Pos.CENTER);
                    }

                    @Override
                    protected void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);

                        if (empty) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            btnInfo_vino.getStyleClass().add("btn");
                            btnInfo_vino.setOnAction(event -> {
                                VinoSelezionato = TV_Ricerca.getSelectionModel().getSelectedItem();

                                if (VinoSelezionato != null) {
                                    AnchorPane_Home.setVisible(false);
                                    AnchorPane_Ricerca.setVisible(false);
                                    AnchorPane_Carrello.setVisible(false);
                                    AnchorPane_Offerte.setVisible(false);
                                    AnchorPane_Ordini.setVisible(false);
                                    AnchorPane_Preferiti.setVisible(false);
                                    AnchorPane_Recensione.setVisible(false);
                                    AnchorPane_Profilo.setVisible(false);
                                    AnchorPane_DettagliOrdine.setVisible(false);
                                    AnchorPane_DatiVino.setVisible(true);
                                    AnchorPane_DatiOrdine.setVisible(false);
                                    AnchorPane_DatiOfferta.setVisible(false);
                                    AnchorPane_DatiRecensioni.setVisible(false);
                                    AnchorPane_Assistenza.setVisible(false);
                                    AnchorPane_Email.setVisible(false);

                                    id_vino = ColonnaID_Vino.getCellData(VinoSelezionato);
                                    System.out.println("ID: " + id_vino);

                                    prezzo_vino = ColonnaPrezzo_Vino.getCellData(VinoSelezionato);
                                    System.out.println("Il prezzo del vino " + id_vino + " e': " + prezzo_vino);

                                    quantita_vino_aggiornata = VinoSelezionato.getQuantita();
                                    System.out.println("La quantita del vino " + id_vino + " e': " + quantita_vino_aggiornata);

                                    soglia_vino = VinoSelezionato.getSoglia();
                                    System.out.println("La soglia massima del vino " + id_vino + " e': " + soglia_vino);

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

                                    try {
                                        Response rs = requestController.makeRequest(Costanti.Verifica_Offerte_Vino, new Offerta(Double.parseDouble(String.valueOf(id_vino))));

                                        if(rs.getStatusCode() == Costanti.Successo)
                                        {
                                            offerta_popup = (ArrayList<Offerta>) rs.getPayload();

                                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                            alert.setTitle("OFFERTA");
                                            alert.setHeaderText("Se acquisti questo vini usufruirai di un'offerta del " + offerta_popup.get(0).getSconto() + "% di sconto");
                                            alert.setContentText("Se acquisti questo vini usufruirai di un'offerta del " + offerta_popup.get(0).getSconto() + "% di sconto");
                                            alert.showAndWait();
                                        }
                                    } catch (Exception e) {
                                        System.out.println(e);
                                        throw new RuntimeException(e);
                                    }
                                }
                            });
                            setGraphic(btnInfo_vino);
                            setText(null);
                        }
                    }
                });

                //button recensione
                ColonnaRecensione_Vino.setCellFactory(param -> new TableCell<>() {
                    final Button btnInfo_vino = new Button("Recensisci");

                    {
                        setAlignment(Pos.CENTER);
                    }

                    @Override
                    protected void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);

                        if (empty) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            btnInfo_vino.getStyleClass().add("btn");
                            btnInfo_vino.setOnAction(event -> {
                                VinoSelezionato = TV_Ricerca.getSelectionModel().getSelectedItem();

                                if (VinoSelezionato != null) {
                                    AnchorPane_Home.setVisible(false);
                                    AnchorPane_Ricerca.setVisible(false);
                                    AnchorPane_Carrello.setVisible(false);
                                    AnchorPane_Offerte.setVisible(false);
                                    AnchorPane_Ordini.setVisible(false);
                                    AnchorPane_Preferiti.setVisible(false);
                                    AnchorPane_Recensione.setVisible(true);
                                    AnchorPane_Profilo.setVisible(false);
                                    AnchorPane_DettagliOrdine.setVisible(false);
                                    AnchorPane_DatiVino.setVisible(false);
                                    AnchorPane_DatiOrdine.setVisible(false);
                                    AnchorPane_DatiOfferta.setVisible(false);
                                    AnchorPane_DatiRecensioni.setVisible(false);
                                    AnchorPane_Assistenza.setVisible(false);
                                    AnchorPane_Email.setVisible(false);

                                    id_vino = ColonnaID_Vino.getCellData(VinoSelezionato);
                                    System.out.println("ID: " + id_vino);
                                }
                            });
                            setGraphic(btnInfo_vino);
                            setText(null);
                        }
                    }
                });
                TV_Ricerca.setItems(vini);
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("No vini alert");
                alert.setHeaderText("Non sono presenti vini.");
                alert.setContentText("Non sono presenti vini.");
                alert.showAndWait();
            }
        } catch (Exception e) {
            System.out.println(e);
            throw new RuntimeException(e);
        }
    }

    //aggiunge i vini al carrello
    @FXML
    void OnBtnAggiungiCarrelloVino_Click(ActionEvent event) {
        String quantita_vino = this.txtQuantita_Vino.getText();

        if(quantita_vino.isEmpty())
        {
            Errore_Campi_Vuoti();
            return;
        } else {
            if (quantita_vino.matches("[a-zA-Z\\s]+")) {            //controlla se la quantita inserita è composta da numeri o lettere
                System.out.println("Errore quantita vino");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Quantita vino");
                alert.setHeaderText("Si è verificato un'errore.");
                alert.setContentText("La quantita del vino può essere solo un numero intero.\nPrego reiserire.");
                alert.showAndWait();
                txtQuantita_Vino.clear();
                return;
            } else {
                double quantita_vino_int = Double.parseDouble(quantita_vino);

                if(quantita_vino_int > 0)       //controllo se la quantità è maggiore di 0
                {
                    if((quantita_vino_aggiornata - quantita_vino_int) >= soglia_vino)       //controllo se eventualmente la quantita rimanente sarebbe > della soglia
                    {
                        quantita_vino_aggiornata = quantita_vino_aggiornata - quantita_vino_int;
                        try {
                            Response response = this.requestController.makeRequest(Costanti.Verifica_Offerte_Vino, new Offerta(Double.parseDouble(String.valueOf(id_vino))));

                            if(response.getStatusCode() == Costanti.Successo)                   //nel caso in cui sia applicata un'offerta a un vino, ne calcola il prezzo scontato
                            {
                                offerta_popup = (ArrayList<Offerta>) response.getPayload();

                                int sconto = offerta_popup.get(0).getSconto();
                                prezzo_scontato = prezzo_vino - (prezzo_vino * (sconto * 0.01));
                                System.out.println(prezzo_scontato);
                            } else {
                                prezzo_scontato = prezzo_vino;
                            }
                        } catch (Exception e)
                        {
                            System.out.println(e);
                            throw new RuntimeException(e);
                        }
                        try {
                            Response r = this.requestController.makeRequest(Costanti.Add_Carrello, new DettagliOrdine(quantita_vino_int, id_vino, UtenteLoggato.getId()));
                            Response rs = this.requestController.makeRequest(Costanti.Update_Quantita_Vino, new Vino(id_vino, quantita_vino_aggiornata));

                            if (r.getStatusCode() == Costanti.Successo && rs.getStatusCode() == Costanti.Successo)
                            {
                                System.out.println("Vino aggiunto al carrello con successo e quantita aggiornata");
                                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                alert.setTitle("Vino aggiunto al carrello");
                                alert.setHeaderText("Vino aggiunto al carrello con successo e quantita aggiornata");
                                alert.setContentText("Vino aggiunto al carrello con successo e quantita aggiornata");
                                alert.showAndWait();

                                Calcolo_Sconto(quantita_vino_int, prezzo_scontato);
                                System.out.println(prezzo_scontato);

                                AnchorPane_Home.setVisible(false);
                                AnchorPane_Ricerca.setVisible(true);
                                AnchorPane_Carrello.setVisible(false);
                                AnchorPane_Offerte.setVisible(false);
                                AnchorPane_Ordini.setVisible(false);
                                AnchorPane_Preferiti.setVisible(false);
                                AnchorPane_Recensione.setVisible(false);
                                AnchorPane_Profilo.setVisible(false);
                                AnchorPane_DettagliOrdine.setVisible(false);
                                AnchorPane_DatiVino.setVisible(false);
                                AnchorPane_DatiOrdine.setVisible(false);
                                AnchorPane_DatiOfferta.setVisible(false);
                                AnchorPane_DatiRecensioni.setVisible(false);
                                AnchorPane_Assistenza.setVisible(false);
                                AnchorPane_Email.setVisible(false);
                            } else {
                                System.out.println("Errore");
                                Alert alert = new Alert(Alert.AlertType.ERROR);
                                alert.setTitle("Aggiunta carrello");
                                alert.setHeaderText("Impossibile aggiungere il vino nel carrello");
                                alert.setContentText("Impossibile aggiungere il vino nel carrello");
                                alert.showAndWait();
                            }
                        } catch (Exception e)
                        {
                            System.out.println(e);
                            throw new RuntimeException(e);
                        }
                    } else {
                        double quantita_massima = quantita_vino_aggiornata - soglia_vino;
                        System.out.println("Errore");
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Quantita vino");
                        alert.setHeaderText("La quantita del vino sotto una certa soglia");
                        alert.setContentText("La quantita del vino " + id_vino + " nei nostri magazzini non può scendere al di sotto della soglia " + soglia_vino + ".\nNe si può acquistare al massimo " + quantita_massima + ".\nOppure richieda assistenza ad uno dei nostri impiegati, per redigere una proposta d acquisto");
                        alert.showAndWait();
                    }
                } else {
                    System.out.println("Errore");
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Quantita vino");
                    alert.setHeaderText("La quantita del vino deve essere per maggiore di zero");
                    alert.setContentText("La quantita del vino deve essere per maggiore di zero");
                    alert.showAndWait();
                }
                txtQuantita_Vino.clear();
            }
        }
    }

    //mostra i vini inseriti nel carrello
    @FXML
    void OnBtnCarrelloCliente_Click(ActionEvent event) {
        TV_Carrello.refresh();

        AnchorPane_Home.setVisible(false);
        AnchorPane_Ricerca.setVisible(false);
        AnchorPane_Carrello.setVisible(true);
        AnchorPane_Offerte.setVisible(false);
        AnchorPane_Ordini.setVisible(false);
        AnchorPane_Preferiti.setVisible(false);
        AnchorPane_Recensione.setVisible(false);
        AnchorPane_Profilo.setVisible(false);
        AnchorPane_DettagliOrdine.setVisible(false);
        AnchorPane_DatiVino.setVisible(false);
        AnchorPane_DatiOrdine.setVisible(false);
        AnchorPane_DatiOfferta.setVisible(false);
        AnchorPane_DatiRecensioni.setVisible(false);
        AnchorPane_Assistenza.setVisible(false);
        AnchorPane_Email.setVisible(false);

        lblTotale_Carrello.setText(String.valueOf(totale));

        try {
            Response r = this.requestController.makeRequest(Costanti.Mostra_Vini_Carrello, new DettagliOrdine(id_vino, UtenteLoggato.getId()));

            if(r.getStatusCode() == Costanti.Successo)
            {
                dettaglio_carrello = (ArrayList<DettagliOrdine>) r.getPayload();
                ObservableList<DettagliOrdine> dettagli_carrello = FXCollections.observableArrayList(dettaglio_carrello);

                IDVino_Carrello.setCellValueFactory(cellData ->{
                    int ID_vino_carrello = cellData.getValue().getID();
                    IntegerProperty proprieta_ID_vino_carrello = new SimpleIntegerProperty(ID_vino_carrello);
                    return Bindings.createObjectBinding(proprieta_ID_vino_carrello::get, proprieta_ID_vino_carrello);
                });

                Vino_Carrello.setCellValueFactory(cellData ->{
                    int vino_carrello = cellData.getValue().getCODVino();
                    IntegerProperty proprieta_vino_carrello = new SimpleIntegerProperty(vino_carrello);
                    return Bindings.createObjectBinding(proprieta_vino_carrello::get, proprieta_vino_carrello);
                });

                Quantita_Carrello.setCellValueFactory(cellData ->{
                    int quantita_carrello = cellData.getValue().getQuantita();
                    IntegerProperty proprieta_quantita_carrello = new SimpleIntegerProperty(quantita_carrello);
                    return Bindings.createObjectBinding(proprieta_quantita_carrello::get, proprieta_quantita_carrello);
                });

                Ordine_Carrello.setCellValueFactory(cellData ->{
                    int ordine_carrello = cellData.getValue().getCODOrdine();
                    IntegerProperty proprieta_quantita_carrello = new SimpleIntegerProperty(ordine_carrello);
                    return Bindings.createObjectBinding(proprieta_quantita_carrello::get, proprieta_quantita_carrello);
                });

                TV_Carrello.setItems(dettagli_carrello);
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

    //effettua l'acquisto dell'ordine
    @FXML
    void OnBtnAcquistaCarrello_Click(ActionEvent event) {
        String indirizzo_ordine = this.txtIndirizzo_Carrello.getText();
        String MP_ordine = this.CBSceltaMP_Carrello.getSelectionModel().getSelectedItem().toString();

        if(indirizzo_ordine.isEmpty() || MP_ordine.isEmpty())
        {
            Errore_Campi_Vuoti();
            return;
        } else {
            try {
                Response r = this.requestController.makeRequest(Costanti.Add_Ordine, new Ordine(UtenteLoggato.getId(), totale, MP_ordine, indirizzo_ordine));       //richista di aggiunta ordine

                if(r.getStatusCode() == Costanti.Successo)
                {
                    Response rs = this.requestController.makeRequest(Costanti.Mostra_Ordini_Effettuati, new Ordine(Double.parseDouble(String.valueOf(UtenteLoggato.getId())), indirizzo_ordine));       //richiesta di restituire gli ordini in modo tale da estrarre l'id

                    if(rs.getStatusCode() == Costanti.Successo)
                    {
                        codice_ordine = (ArrayList<Ordine>) rs.getPayload();
                        id_dettagli_carrello = codice_ordine.get(0).getID();

                        Response response = this.requestController.makeRequest(Costanti.Update_Dettagli_ordine, new DettagliOrdine(id_dettagli_carrello));          //richiesta di aggiornamento dei dettigli dell'ordine, in modo da associare il CODOrdine della tabella dettagli_ordini con l'ID dell'ordine corrispondente

                        if(response.getStatusCode() == Costanti.Successo)
                        {
                            System.out.println("Acquisto completato");
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("Acquisto ordine");
                            alert.setHeaderText("Acquisto ordine avvenuto con successo");
                            alert.setContentText("Acquisto ordine avvenuto con successo");
                            alert.showAndWait();
                        } else {
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("Errore acquisto");
                            alert.setHeaderText("Acquisto non andato a buon fine");
                            alert.setContentText("Acquisto non andato a buon fine");
                            alert.showAndWait();
                        }
                    }
                }
            } catch (Exception e)
            {
                System.out.println(e);
                throw new RuntimeException(e);
            }
        }
    }

    @FXML
    void OnBtnPromozioniCliente_Click(ActionEvent event) {
        TV_RicercaOfferte.refresh();

        AnchorPane_Home.setVisible(false);
        AnchorPane_Ricerca.setVisible(false);
        AnchorPane_Carrello.setVisible(false);
        AnchorPane_Offerte.setVisible(true);
        AnchorPane_Ordini.setVisible(false);
        AnchorPane_Preferiti.setVisible(false);
        AnchorPane_Recensione.setVisible(false);
        AnchorPane_Profilo.setVisible(false);
        AnchorPane_DettagliOrdine.setVisible(false);
        AnchorPane_DatiVino.setVisible(false);
        AnchorPane_DatiOrdine.setVisible(false);
        AnchorPane_DatiOfferta.setVisible(false);
        AnchorPane_DatiRecensioni.setVisible(false);
        AnchorPane_Assistenza.setVisible(false);
        AnchorPane_Email.setVisible(false);

        try {
            Response r = this.requestController.makeRequest(Costanti.Mostra_Offerte, new EmptyPayload());

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
                    int vino_offerta = cellData.getValue().getCODVino();
                    IntegerProperty proprieta_vino_offerta = new SimpleIntegerProperty(vino_offerta);
                    return Bindings.createObjectBinding(proprieta_vino_offerta::get, proprieta_vino_offerta);
                });

                ColonnaApplica_Offerta.setCellFactory(param -> new TableCell<>() {
                    final Button btnGestione_Offerta = new Button("Visualizza");

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
                                    AnchorPane_Ricerca.setVisible(false);
                                    AnchorPane_Carrello.setVisible(false);
                                    AnchorPane_Offerte.setVisible(false);
                                    AnchorPane_Ordini.setVisible(false);
                                    AnchorPane_Preferiti.setVisible(false);
                                    AnchorPane_Recensione.setVisible(false);
                                    AnchorPane_Profilo.setVisible(false);
                                    AnchorPane_DettagliOrdine.setVisible(false);
                                    AnchorPane_DatiVino.setVisible(false);
                                    AnchorPane_DatiOrdine.setVisible(false);
                                    AnchorPane_DatiOfferta.setVisible(true);
                                    AnchorPane_DatiRecensioni.setVisible(false);
                                    AnchorPane_Assistenza.setVisible(false);
                                    AnchorPane_Email.setVisible(false);

                                    id_offerta = ColonnaID_Offerta.getCellData(OffertaSelezionata);
                                    System.out.println("ID: " + id_offerta);

                                    id_vino_offerta = ColonnaVino_Offerta.getCellData(OffertaSelezionata);
                                    System.out.println("ID vino: " + id_vino_offerta);

                                    lblID_DatiOfferta.setText(String.valueOf(OffertaSelezionata.getID()));
                                    lblSconto_DatiOfferta.setText(String.valueOf(OffertaSelezionata.getSconto()));
                                    lblVino_DatiOfferta.setText(String.valueOf(OffertaSelezionata.getCODVino()));
                                    lblDescrizione_DatiOfferta.setText(OffertaSelezionata.getDescrizione());
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

    @FXML
    void OnBtnCercaOfferte_Click(ActionEvent event) {
        TV_RicercaOfferte.refresh();
        String dato_cercato;
        int dato_cercato_int;
        System.out.println("Dato : " + tipo_dato);
        Response r;

        dato_cercato = this.txtRicerca_Offerte.getText();

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
                        int vino_offerta = cellData.getValue().getCODVino();
                        IntegerProperty proprieta_vino_offerta = new SimpleIntegerProperty(vino_offerta);
                        return Bindings.createObjectBinding(proprieta_vino_offerta::get, proprieta_vino_offerta);
                    });

                    ColonnaApplica_Offerta.setCellFactory(param -> new TableCell<>() {
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
                                        AnchorPane_Ricerca.setVisible(false);
                                        AnchorPane_Carrello.setVisible(false);
                                        AnchorPane_Offerte.setVisible(false);
                                        AnchorPane_Ordini.setVisible(false);
                                        AnchorPane_Preferiti.setVisible(false);
                                        AnchorPane_Recensione.setVisible(false);
                                        AnchorPane_Profilo.setVisible(false);
                                        AnchorPane_DettagliOrdine.setVisible(false);
                                        AnchorPane_DatiVino.setVisible(false);
                                        AnchorPane_DatiOrdine.setVisible(false);
                                        AnchorPane_DatiOfferta.setVisible(true);
                                        AnchorPane_DatiRecensioni.setVisible(false);
                                        AnchorPane_Assistenza.setVisible(false);
                                        AnchorPane_Email.setVisible(false);

                                        id_offerta = ColonnaID_Offerta.getCellData(OffertaSelezionata);
                                        System.out.println("ID: " + id_offerta);

                                        id_vino_offerta = ColonnaVino_Offerta.getCellData(OffertaSelezionata);
                                        System.out.println("ID vino: " + id_vino_offerta);

                                        lblID_DatiOfferta.setText(String.valueOf(OffertaSelezionata.getID()));
                                        lblSconto_DatiOfferta.setText(String.valueOf(OffertaSelezionata.getSconto()));
                                        lblVino_DatiOfferta.setText(String.valueOf(OffertaSelezionata.getCODVino()));
                                        lblDescrizione_DatiOfferta.setText(OffertaSelezionata.getDescrizione());
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
        txtRicerca_Offerte.clear();
    }

    @FXML
    void OnBtnOrdiniCliente_Click(ActionEvent event) {
        CBSceltaStato_Ricerca.setPromptText("Selezionare stato ordine");
        CBSceltaStato_Ricerca.setValue("Selezionare stato ordine");
        TV_Ordini.refresh();

        AnchorPane_Home.setVisible(false);
        AnchorPane_Ricerca.setVisible(false);
        AnchorPane_Carrello.setVisible(false);
        AnchorPane_Offerte.setVisible(false);
        AnchorPane_Ordini.setVisible(true);
        AnchorPane_Preferiti.setVisible(false);
        AnchorPane_Recensione.setVisible(false);
        AnchorPane_Profilo.setVisible(false);
        AnchorPane_DettagliOrdine.setVisible(false);
        AnchorPane_DatiVino.setVisible(false);
        AnchorPane_DatiOrdine.setVisible(false);
        AnchorPane_DatiOfferta.setVisible(false);
        AnchorPane_DatiRecensioni.setVisible(false);
        AnchorPane_Assistenza.setVisible(false);
        AnchorPane_Email.setVisible(false);

        try {
            Response r = this.requestController.makeRequest(Costanti.Mostra_Ordini_Cliente, new Ordine(Double.parseDouble(String.valueOf(UtenteLoggato.getId()))));

            if(r.getStatusCode() == Costanti.Successo)
            {
                ordine = (ArrayList<Ordine>) r.getPayload();
                ObservableList<Ordine> ordini = FXCollections.observableArrayList(ordine);

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

                Info_Ordini.setCellFactory(param -> new TableCell<>() {
                    final Button btnInfo = new Button("Info");

                    {setAlignment(Pos.CENTER);}
                    @Override
                    protected void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);

                        if (empty)
                        {
                            setGraphic(null);
                            setText(null);
                        } else {
                            btnInfo.getStyleClass().add("btn");
                            btnInfo.setOnAction(event -> {
                                OrdineSelezionato = TV_Ordini.getSelectionModel().getSelectedItem();

                                if(OrdineSelezionato != null)
                                {
                                    AnchorPane_Home.setVisible(false);
                                    AnchorPane_Ricerca.setVisible(false);
                                    AnchorPane_Carrello.setVisible(false);
                                    AnchorPane_Offerte.setVisible(false);
                                    AnchorPane_Ordini.setVisible(false);
                                    AnchorPane_Preferiti.setVisible(false);
                                    AnchorPane_Recensione.setVisible(false);
                                    AnchorPane_Profilo.setVisible(false);
                                    AnchorPane_DettagliOrdine.setVisible(false);
                                    AnchorPane_DatiVino.setVisible(false);
                                    AnchorPane_DatiOrdine.setVisible(true);
                                    AnchorPane_DatiOfferta.setVisible(false);
                                    AnchorPane_DatiRecensioni.setVisible(false);
                                    AnchorPane_Assistenza.setVisible(false);
                                    AnchorPane_Email.setVisible(false);

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
                            setGraphic(btnInfo);
                            setText(null);
                        }
                    }
                });
                TV_Ordini.setItems(ordini);
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

    @FXML
    void OnBtnCercaOrdini_Click(ActionEvent event) {
        TV_Ordini.refresh();
        String ordine_cercato = this.CBSceltaStato_Ricerca.getSelectionModel().getSelectedItem().toString();

        try {
            Response r = this.requestController.makeRequest(Costanti.Ricerca_Ordini_Cliente, new Ordine(ordine_cercato, UtenteLoggato.getId()));

            if(r.getStatusCode() == Costanti.Successo)
            {
                ordine = (ArrayList<Ordine>) r.getPayload();
                ObservableList<Ordine> ordini = FXCollections.observableArrayList(ordine);

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

                Info_Ordini.setCellFactory(param -> new TableCell<>() {
                    final Button btnInfo = new Button("Info");

                    {setAlignment(Pos.CENTER);}
                    @Override
                    protected void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);

                        if (empty)
                        {
                            setGraphic(null);
                            setText(null);
                        } else {
                            btnInfo.getStyleClass().add("btn");
                            btnInfo.setOnAction(event -> {
                                OrdineSelezionato = TV_Ordini.getSelectionModel().getSelectedItem();

                                if(OrdineSelezionato != null)
                                {
                                    AnchorPane_Home.setVisible(false);
                                    AnchorPane_Ricerca.setVisible(false);
                                    AnchorPane_Carrello.setVisible(false);
                                    AnchorPane_Offerte.setVisible(false);
                                    AnchorPane_Ordini.setVisible(false);
                                    AnchorPane_Preferiti.setVisible(false);
                                    AnchorPane_Recensione.setVisible(false);
                                    AnchorPane_Profilo.setVisible(false);
                                    AnchorPane_DettagliOrdine.setVisible(false);
                                    AnchorPane_DatiVino.setVisible(false);
                                    AnchorPane_DatiOrdine.setVisible(true);
                                    AnchorPane_DatiOfferta.setVisible(false);
                                    AnchorPane_DatiRecensioni.setVisible(false);
                                    AnchorPane_Assistenza.setVisible(false);
                                    AnchorPane_Email.setVisible(false);

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
                            setGraphic(btnInfo);
                            setText(null);
                        }
                    }
                });
                TV_Ordini.setItems(ordini);
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

    @FXML
    void OnBtnPreferitiCliente_Click(ActionEvent event) {
        TV_Preferiti.refresh();
        txtRicerca_Preferiti.clear();

        AnchorPane_Home.setVisible(false);
        AnchorPane_Ricerca.setVisible(false);
        AnchorPane_Carrello.setVisible(false);
        AnchorPane_Offerte.setVisible(false);
        AnchorPane_Ordini.setVisible(false);
        AnchorPane_Preferiti.setVisible(true);
        AnchorPane_Recensione.setVisible(false);
        AnchorPane_Profilo.setVisible(false);
        AnchorPane_DettagliOrdine.setVisible(false);
        AnchorPane_DatiVino.setVisible(false);
        AnchorPane_DatiOrdine.setVisible(false);
        AnchorPane_DatiOfferta.setVisible(false);
        AnchorPane_DatiRecensioni.setVisible(false);
        AnchorPane_Assistenza.setVisible(false);
        AnchorPane_Email.setVisible(false);

        try {
            Response r = this.requestController.makeRequest(Costanti.Mostra_Preferiti, new Preferito(UtenteLoggato.getId()));

            if(r.getStatusCode() == Costanti.Successo)
            {
                preferito = (ArrayList<Preferito>) r.getPayload();
                ObservableList<Preferito> preferiti = FXCollections.observableArrayList(preferito);

                ColonnaID_Preferiti.setCellValueFactory(cellData -> {
                    int ID_preferito = cellData.getValue().getID();
                    IntegerProperty proprieta_id_preferito = new SimpleIntegerProperty(ID_preferito);
                    return Bindings.createObjectBinding(proprieta_id_preferito::get, proprieta_id_preferito);
                });

                ColonnaVino_Preferiti.setCellValueFactory(cellData -> {
                    int vino_preferito = cellData.getValue().getCODVino();
                    IntegerProperty proprieta_vino_preferito = new SimpleIntegerProperty(vino_preferito);
                    return Bindings.createObjectBinding(proprieta_vino_preferito::get, proprieta_vino_preferito);
                });

                ColonnaElimina_Preferiti.setCellFactory(param -> new TableCell<>() {
                    final Button btnElimina = new Button("Elimina");

                    {setAlignment(Pos.CENTER);}
                    @Override
                    protected void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);

                        if (empty)
                        {
                            setGraphic(null);
                            setText(null);
                        } else {
                            btnElimina.getStyleClass().add("btn");
                            btnElimina.setOnAction(event -> {
                                PreferitoSelezionato = TV_Preferiti.getSelectionModel().getSelectedItem();

                                if(PreferitoSelezionato != null)
                                {
                                    AnchorPane_Home.setVisible(true);
                                    AnchorPane_Ricerca.setVisible(false);
                                    AnchorPane_Carrello.setVisible(false);
                                    AnchorPane_Offerte.setVisible(false);
                                    AnchorPane_Ordini.setVisible(false);
                                    AnchorPane_Preferiti.setVisible(false);
                                    AnchorPane_Recensione.setVisible(false);
                                    AnchorPane_Profilo.setVisible(false);
                                    AnchorPane_DettagliOrdine.setVisible(false);
                                    AnchorPane_DatiVino.setVisible(false);
                                    AnchorPane_DatiOrdine.setVisible(false);
                                    AnchorPane_DatiOfferta.setVisible(false);
                                    AnchorPane_DatiRecensioni.setVisible(false);
                                    AnchorPane_Assistenza.setVisible(false);
                                    AnchorPane_Email.setVisible(false);

                                    id_preferito = ColonnaID_Preferiti.getCellData(PreferitoSelezionato);
                                    System.out.println("ID: " + id_preferito);

                                    try {
                                        Response rs = requestController.makeRequest(Costanti.Elimina_Preferiti, new Preferito(id_preferito, ColonnaVino_Preferiti.getCellData(PreferitoSelezionato), UtenteLoggato.getId()));

                                        if(rs.getStatusCode() == Costanti.Successo)
                                        {
                                            System.out.println("Vino eliminato dai preferiti");
                                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                            alert.setTitle("Eliminato dai preferiti");
                                            alert.setHeaderText("Vino eliminato dai preferiti con successo");
                                            alert.setContentText("Vino eliminato dai preferiti con successo");
                                            alert.showAndWait();
                                        } else {
                                            Alert alert = new Alert(Alert.AlertType.ERROR);
                                            alert.setTitle("Preferito alert");
                                            alert.setHeaderText("Non è stato possibile eliminare il vino ai preferiti");
                                            alert.setContentText("Non è stato possibile eliminare il vino ai preferiti");
                                            alert.showAndWait();
                                        }
                                    } catch (Exception e)
                                    {
                                        System.out.println(e);
                                        throw new RuntimeException(e);
                                    }
                                }
                            });
                            setGraphic(btnElimina);
                            setText(null);
                        }
                    }
                });
                TV_Preferiti.setItems(preferiti);
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("No preferiti alert");
                alert.setHeaderText("Non sono presenti preferiti");
                alert.setContentText("Non sono presenti preferiti");
                alert.showAndWait();
            }
        } catch (Exception e)
        {
            System.out.println(e);
            throw new RuntimeException(e);
        }
    }

    @FXML
    void OnBtnRicercaPreferiti_Click(ActionEvent event) {
        TV_Preferiti.refresh();
        String preferito_cercato = this.txtRicerca_Preferiti.getText();

        if (preferito_cercato.matches("[a-zA-Z\\s]+")) {
            System.out.println("Errore preferiti");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Errore preferiti");
            alert.setHeaderText("Si è verificato un'errore");
            alert.setContentText("Il codice di un vino può essere solo un numero intero.\nPrego reiserire.");
            alert.showAndWait();
            txtRicerca_Preferiti.clear();
            return;
        } else {
            int preferito_cercato_int = Integer.parseInt(preferito_cercato);

            try {
                Response r = this.requestController.makeRequest(Costanti.Ricerca_Preferiti, new Preferito(preferito_cercato_int, UtenteLoggato.getId()));

                if(r.getStatusCode() == Costanti.Successo)
                {
                    preferito = (ArrayList<Preferito>) r.getPayload();
                    ObservableList<Preferito> preferiti = FXCollections.observableArrayList(preferito);

                    ColonnaID_Preferiti.setCellValueFactory(cellData -> {
                        int ID_preferito = cellData.getValue().getID();
                        IntegerProperty proprieta_id_preferito = new SimpleIntegerProperty(ID_preferito);
                        return Bindings.createObjectBinding(proprieta_id_preferito::get, proprieta_id_preferito);
                    });

                    ColonnaVino_Preferiti.setCellValueFactory(cellData -> {
                        int vino_preferito = cellData.getValue().getCODVino();
                        IntegerProperty proprieta_vino_preferito = new SimpleIntegerProperty(vino_preferito);
                        return Bindings.createObjectBinding(proprieta_vino_preferito::get, proprieta_vino_preferito);
                    });

                    ColonnaElimina_Preferiti.setCellFactory(param -> new TableCell<>() {
                        final Button btnElimina = new Button("Elimina");

                        {setAlignment(Pos.CENTER);}
                        @Override
                        protected void updateItem(Void item, boolean empty) {
                            super.updateItem(item, empty);

                            if (empty)
                            {
                                setGraphic(null);
                                setText(null);
                            } else {
                                btnElimina.getStyleClass().add("btn");
                                btnElimina.setOnAction(event -> {
                                    PreferitoSelezionato = TV_Preferiti.getSelectionModel().getSelectedItem();

                                    if(PreferitoSelezionato != null)
                                    {
                                        AnchorPane_Home.setVisible(false);
                                        AnchorPane_Ricerca.setVisible(false);
                                        AnchorPane_Carrello.setVisible(false);
                                        AnchorPane_Offerte.setVisible(false);
                                        AnchorPane_Ordini.setVisible(false);
                                        AnchorPane_Preferiti.setVisible(false);
                                        AnchorPane_Recensione.setVisible(false);
                                        AnchorPane_Profilo.setVisible(false);
                                        AnchorPane_DettagliOrdine.setVisible(false);
                                        AnchorPane_DatiVino.setVisible(false);
                                        AnchorPane_DatiOrdine.setVisible(true);
                                        AnchorPane_DatiOfferta.setVisible(false);
                                        AnchorPane_DatiRecensioni.setVisible(false);
                                        AnchorPane_Assistenza.setVisible(false);
                                        AnchorPane_Email.setVisible(false);

                                        id_preferito = ColonnaID_Preferiti.getCellData(PreferitoSelezionato);
                                        System.out.println("ID: " + id_preferito);

                                        try {
                                            Response rs = requestController.makeRequest(Costanti.Elimina_Preferiti, new Preferito(id_preferito, ColonnaVino_Preferiti.getCellData(PreferitoSelezionato), UtenteLoggato.getId()));

                                            if(rs.getStatusCode() == Costanti.Successo)
                                            {
                                                System.out.println("Vino eliminato dai preferiti");
                                                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                                alert.setTitle("Eliminato dai preferiti");
                                                alert.setHeaderText("Vino eliminato dai preferiti con successo");
                                                alert.setContentText("Vino eliminato dai preferiti con successo");
                                                alert.showAndWait();
                                            } else {
                                                Alert alert = new Alert(Alert.AlertType.ERROR);
                                                alert.setTitle("Preferito alert");
                                                alert.setHeaderText("Non è stato possibile eliminare il vino ai preferiti");
                                                alert.setContentText("Non è stato possibile eliminare il vino ai preferiti");
                                                alert.showAndWait();
                                            }
                                        } catch (Exception e)
                                        {
                                            System.out.println(e);
                                            throw new RuntimeException(e);
                                        }
                                    }
                                });
                                setGraphic(btnElimina);
                                setText(null);
                            }
                        }
                    });
                    TV_Preferiti.setItems(preferiti);
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("No preferiti alert");
                    alert.setHeaderText("Non sono presenti vini preferiti");
                    alert.setContentText("Non sono presenti vini preferiti");
                    alert.showAndWait();
                }
            } catch (Exception e)
            {
                System.out.println(e);
                throw new RuntimeException(e);
            }
            txtRicerca_Preferiti.clear();
        }
    }

    @FXML
    void OnBtnRecensioneCliente_Click(ActionEvent event) {
        CBVotoVino_DatiRecensioni.setValue("Selezionare vino");
        CBVotoVino_DatiRecensioni.setPromptText("Selezionare vino");
        TV_DatiRecensioni.refresh();

        AnchorPane_Home.setVisible(false);
        AnchorPane_Ricerca.setVisible(false);
        AnchorPane_Carrello.setVisible(false);
        AnchorPane_Offerte.setVisible(false);
        AnchorPane_Ordini.setVisible(false);
        AnchorPane_Preferiti.setVisible(false);
        AnchorPane_Recensione.setVisible(false);
        AnchorPane_Profilo.setVisible(false);
        AnchorPane_DettagliOrdine.setVisible(false);
        AnchorPane_DatiVino.setVisible(false);
        AnchorPane_DatiOrdine.setVisible(false);
        AnchorPane_DatiOfferta.setVisible(false);
        AnchorPane_DatiRecensioni.setVisible(true);
        AnchorPane_Assistenza.setVisible(false);
        AnchorPane_Email.setVisible(false);

        try {
            Response r = this.requestController.makeRequest(Costanti.Mostra_Recensioni, new Recensione(UtenteLoggato.getId()));

            if(r.getStatusCode() == Costanti.Successo)
            {
                recensione = (ArrayList<Recensione>) r.getPayload();
                ObservableList<Recensione> recensioni = FXCollections.observableArrayList(recensione);

                ColonnaID_DatiRecensioni.setCellValueFactory(cellData -> {
                    int ID_recensione = cellData.getValue().getID();
                    IntegerProperty proprieta_id_recensione = new SimpleIntegerProperty(ID_recensione);
                    return Bindings.createObjectBinding(proprieta_id_recensione::get, proprieta_id_recensione);
                });

                ColonnaVoto_DatiRecensioni.setCellValueFactory(cellData -> {
                    String voto_recensione = cellData.getValue().getVoto();
                    StringProperty proprieta_voto_recensione = new SimpleStringProperty(voto_recensione);
                    return Bindings.createObjectBinding(proprieta_voto_recensione::get, proprieta_voto_recensione);
                });

                ColonnaCommento_DatiRecensioni.setCellValueFactory(cellData -> {
                    String commento_recensione = cellData.getValue().getCommento();
                    StringProperty proprieta_commento_recensione = new SimpleStringProperty(commento_recensione);
                    return Bindings.createObjectBinding(proprieta_commento_recensione::get, proprieta_commento_recensione);
                });

                ColonnaVino_DatiRecensioni.setCellValueFactory(cellData -> {
                    int vino_recensione = cellData.getValue().getCODVino();
                    IntegerProperty proprieta_vino_recensione = new SimpleIntegerProperty(vino_recensione);
                    return Bindings.createObjectBinding(proprieta_vino_recensione::get, proprieta_vino_recensione);
                });

                ColonnaElimina_DatiRecensioni.setCellFactory(param -> new TableCell<>() {
                    final Button btnElimina = new Button("Elimina");

                    {setAlignment(Pos.CENTER);}
                    @Override
                    protected void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);

                        if (empty)
                        {
                            setGraphic(null);
                            setText(null);
                        } else {
                            btnElimina.getStyleClass().add("btn");
                            btnElimina.setOnAction(event -> {
                                RecensioneSelezionata = TV_DatiRecensioni.getSelectionModel().getSelectedItem();

                                if(RecensioneSelezionata != null)
                                {
                                    AnchorPane_Home.setVisible(true);
                                    AnchorPane_Ricerca.setVisible(false);
                                    AnchorPane_Carrello.setVisible(false);
                                    AnchorPane_Offerte.setVisible(false);
                                    AnchorPane_Ordini.setVisible(false);
                                    AnchorPane_Preferiti.setVisible(false);
                                    AnchorPane_Recensione.setVisible(false);
                                    AnchorPane_Profilo.setVisible(false);
                                    AnchorPane_DettagliOrdine.setVisible(false);
                                    AnchorPane_DatiVino.setVisible(false);
                                    AnchorPane_DatiOrdine.setVisible(false);
                                    AnchorPane_DatiOfferta.setVisible(false);
                                    AnchorPane_DatiRecensioni.setVisible(false);
                                    AnchorPane_Assistenza.setVisible(false);
                                    AnchorPane_Email.setVisible(false);

                                    id_recensione = ColonnaID_DatiRecensioni.getCellData(RecensioneSelezionata);
                                    System.out.println("ID: " + id_recensione);

                                    try {
                                        Response rs = requestController.makeRequest(Costanti.Elimina_Recensioni, new Recensione(id_recensione, ColonnaVino_DatiRecensioni.getCellData(RecensioneSelezionata)));

                                        if(rs.getStatusCode() == Costanti.Successo)
                                        {
                                            System.out.println("Recensione eliminata");
                                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                            alert.setTitle("Recensione eliminata");
                                            alert.setHeaderText("Recensione eliminata con successo");
                                            alert.setContentText("Recensione eliminata con successo");
                                            alert.showAndWait();
                                        } else {
                                            Alert alert = new Alert(Alert.AlertType.ERROR);
                                            alert.setTitle("Recensione alert");
                                            alert.setHeaderText("Non è stato possibile eliminare la recensione");
                                            alert.setContentText("Non è stato possibile eliminare la recensione");
                                            alert.showAndWait();
                                        }
                                    } catch (Exception e)
                                    {
                                        System.out.println(e);
                                        throw new RuntimeException(e);
                                    }
                                }
                            });
                            setGraphic(btnElimina);
                            setText(null);
                        }
                    }
                });
                TV_DatiRecensioni.setItems(recensioni);
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("No recensioni alert");
                alert.setHeaderText("Non sono presenti recensioni");
                alert.setContentText("Non sono presenti recensioni");
                alert.showAndWait();
            }
        } catch (Exception e)
        {
            System.out.println(e);
            throw new RuntimeException(e);
        }
    }

    @FXML
    void OnBtnRicercaRecensioni_Click(ActionEvent event) {
        TV_DatiRecensioni.refresh();
        String voto_cercato = this.CBVotoVino_DatiRecensioni.getSelectionModel().getSelectedItem().toString();

        try {
            Response r = this.requestController.makeRequest(Costanti.Ricerca_Recensioni, new Recensione(UtenteLoggato.getId(), voto_cercato));

            if(r.getStatusCode() == Costanti.Successo)
            {
                recensione = (ArrayList<Recensione>) r.getPayload();
                ObservableList<Recensione> recensioni = FXCollections.observableArrayList(recensione);

                ColonnaID_DatiRecensioni.setCellValueFactory(cellData -> {
                    int ID_recensione = cellData.getValue().getID();
                    IntegerProperty proprieta_id_recensione = new SimpleIntegerProperty(ID_recensione);
                    return Bindings.createObjectBinding(proprieta_id_recensione::get, proprieta_id_recensione);
                });

                ColonnaVoto_DatiRecensioni.setCellValueFactory(cellData -> {
                    String voto_recensione = cellData.getValue().getVoto();
                    StringProperty proprieta_voto_recensione = new SimpleStringProperty(voto_recensione);
                    return Bindings.createObjectBinding(proprieta_voto_recensione::get, proprieta_voto_recensione);
                });

                ColonnaCommento_DatiRecensioni.setCellValueFactory(cellData -> {
                    String commento_recensione = cellData.getValue().getCommento();
                    StringProperty proprieta_commento_recensione = new SimpleStringProperty(commento_recensione);
                    return Bindings.createObjectBinding(proprieta_commento_recensione::get, proprieta_commento_recensione);
                });

                ColonnaVino_DatiRecensioni.setCellValueFactory(cellData -> {
                    int vino_recensione = cellData.getValue().getCODVino();
                    IntegerProperty proprieta_vino_recensione = new SimpleIntegerProperty(vino_recensione);
                    return Bindings.createObjectBinding(proprieta_vino_recensione::get, proprieta_vino_recensione);
                });

                ColonnaElimina_DatiRecensioni.setCellFactory(param -> new TableCell<>() {
                    final Button btnElimina = new Button("Elimina");

                    {setAlignment(Pos.CENTER);}
                    @Override
                    protected void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);

                        if (empty)
                        {
                            setGraphic(null);
                            setText(null);
                        } else {
                            btnElimina.getStyleClass().add("btn");
                            btnElimina.setOnAction(event -> {
                                RecensioneSelezionata = TV_DatiRecensioni.getSelectionModel().getSelectedItem();

                                if(RecensioneSelezionata != null)
                                {
                                    AnchorPane_Home.setVisible(true);
                                    AnchorPane_Ricerca.setVisible(false);
                                    AnchorPane_Carrello.setVisible(false);
                                    AnchorPane_Offerte.setVisible(false);
                                    AnchorPane_Ordini.setVisible(false);
                                    AnchorPane_Preferiti.setVisible(false);
                                    AnchorPane_Recensione.setVisible(false);
                                    AnchorPane_Profilo.setVisible(false);
                                    AnchorPane_DettagliOrdine.setVisible(false);
                                    AnchorPane_DatiVino.setVisible(false);
                                    AnchorPane_DatiOrdine.setVisible(false);
                                    AnchorPane_DatiOfferta.setVisible(false);
                                    AnchorPane_DatiRecensioni.setVisible(false);
                                    AnchorPane_Assistenza.setVisible(false);
                                    AnchorPane_Email.setVisible(false);

                                    id_recensione = ColonnaID_DatiRecensioni.getCellData(RecensioneSelezionata);
                                    System.out.println("ID: " + id_recensione);

                                    try {
                                        Response rs = requestController.makeRequest(Costanti.Elimina_Recensioni, new Recensione(id_recensione, ColonnaVino_DatiRecensioni.getCellData(RecensioneSelezionata)));

                                        if(rs.getStatusCode() == Costanti.Successo)
                                        {
                                            System.out.println("Recensione eliminata");
                                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                            alert.setTitle("Recensione eliminata");
                                            alert.setHeaderText("Recensione eliminata con successo");
                                            alert.setContentText("Recensione eliminata con successo");
                                            alert.showAndWait();
                                        } else {
                                            Alert alert = new Alert(Alert.AlertType.ERROR);
                                            alert.setTitle("Recensione alert");
                                            alert.setHeaderText("Non è stato possibile eliminare la recensione");
                                            alert.setContentText("Non è stato possibile eliminare la recensione");
                                            alert.showAndWait();
                                        }
                                    } catch (Exception e)
                                    {
                                        System.out.println(e);
                                        throw new RuntimeException(e);
                                    }
                                }
                            });
                            setGraphic(btnElimina);
                            setText(null);
                        }
                    }
                });
                TV_DatiRecensioni.setItems(recensioni);
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("No recensioni alert");
                alert.setHeaderText("Non sono presenti recensioni");
                alert.setContentText("Non sono presenti recensioni");
                alert.showAndWait();
            }
        } catch (Exception e)
        {
            System.out.println(e);
            throw new RuntimeException(e);
        }
    }

    @FXML
    void OnBtnAddRecensione_Click(ActionEvent event) {
        String voto_vino = this.CBVotoVino_Recensione.getSelectionModel().getSelectedItem().toString();
        String commento_vino = this.txtRecensione_Recensione.getText();

        if(voto_vino.isEmpty() || commento_vino.isEmpty())
        {
            Errore_Campi_Vuoti();
            return;
        } else {
            try {
                Response r = this.requestController.makeRequest(Costanti.Add_Recensione, new Recensione(id_vino, UtenteLoggato.getId(), commento_vino, voto_vino));

                if(r.getStatusCode() == Costanti.Successo)
                {
                    System.out.println("Recensione aggiunta");
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Recensione aggiunta");
                    alert.setHeaderText("Recensione aggiunta con successo");
                    alert.setContentText("Recensione aggiunta con successo");
                    alert.showAndWait();

                    AnchorPane_Home.setVisible(true);
                    AnchorPane_Ricerca.setVisible(false);
                    AnchorPane_Carrello.setVisible(false);
                    AnchorPane_Offerte.setVisible(false);
                    AnchorPane_Ordini.setVisible(false);
                    AnchorPane_Preferiti.setVisible(false);
                    AnchorPane_Recensione.setVisible(false);
                    AnchorPane_Profilo.setVisible(false);
                    AnchorPane_DettagliOrdine.setVisible(false);
                    AnchorPane_DatiVino.setVisible(false);
                    AnchorPane_DatiOrdine.setVisible(false);
                    AnchorPane_DatiOfferta.setVisible(false);
                    AnchorPane_DatiRecensioni.setVisible(false);
                    AnchorPane_Assistenza.setVisible(false);
                    AnchorPane_Email.setVisible(false);
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Recensione alert");
                    alert.setHeaderText("Non è stato possibile aggiungere la recensione");
                    alert.setContentText("Non è stato possibile aggiungere la recensione");
                    alert.showAndWait();
                }
            } catch (Exception e)
            {
                System.out.println(e);
                throw new RuntimeException(e);
            }
        }
        txtRecensione_Recensione.clear();
        TV_DatiRecensioni.refresh();
    }

    @FXML
    void OnBtnPreferitiVino_Click(ActionEvent event) {
        try {
            Response r = this.requestController.makeRequest(Costanti.Add_Preferiti, new Preferito(id_vino, UtenteLoggato.getId()));

            if(r.getStatusCode() == Costanti.Successo)
            {
                System.out.println("Vino aggiunto ai preferiti");
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Aggiunto ai preferiti");
                alert.setHeaderText("Vino aggiunto ai preferiti con successo");
                alert.setContentText("Vino aggiunto ai preferiti con successo");
                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Preferito alert");
                alert.setHeaderText("Non è stato possibile aggiungere il vino ai preferiti");
                alert.setContentText("Non è stato possibile aggiungere il vino ai preferiti");
                alert.showAndWait();
            }
        } catch (Exception e)
        {
            System.out.println(e);
            throw new RuntimeException(e);
        }
    }

    //visualizza degli del proprio ordine
    @FXML
    void OnBtnVisualizzaDettagliOrdine_Click(ActionEvent event) {
        TV_DettagliOrdine.refresh();

        AnchorPane_Home.setVisible(false);
        AnchorPane_Ricerca.setVisible(false);
        AnchorPane_Carrello.setVisible(false);
        AnchorPane_Offerte.setVisible(false);
        AnchorPane_Ordini.setVisible(false);
        AnchorPane_Preferiti.setVisible(false);
        AnchorPane_Recensione.setVisible(false);
        AnchorPane_Profilo.setVisible(false);
        AnchorPane_DettagliOrdine.setVisible(true);
        AnchorPane_DatiVino.setVisible(false);
        AnchorPane_DatiOrdine.setVisible(false);
        AnchorPane_DatiOfferta.setVisible(false);
        AnchorPane_DatiRecensioni.setVisible(false);
        AnchorPane_Assistenza.setVisible(false);
        AnchorPane_Email.setVisible(false);

        try {
            Response r = this.requestController.makeRequest(Costanti.Mostra_Dettagli_Ordine, new DettagliOrdine(id_ordine));

            if(r.getStatusCode() == Costanti.Successo)
            {
                dettagli_ordine = (ArrayList<DettagliOrdine>) r.getPayload();
                ObservableList<DettagliOrdine> dettagli_ordini = FXCollections.observableArrayList(dettagli_ordine);

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
                    int Vino_DettagliOrdine = cellData.getValue().getCODVino();
                    IntegerProperty proprieta_vino_dettagli_ordine = new SimpleIntegerProperty(Vino_DettagliOrdine);
                    return Bindings.createObjectBinding(proprieta_vino_dettagli_ordine::get, proprieta_vino_dettagli_ordine);
                });
                TV_DettagliOrdine.setItems(dettagli_ordini);
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

    @FXML
    void OnBtnAssistenzaCliente_Click(ActionEvent event) {
        TV_Assistenza.refresh();

        AnchorPane_Home.setVisible(false);
        AnchorPane_Ricerca.setVisible(false);
        AnchorPane_Carrello.setVisible(false);
        AnchorPane_Offerte.setVisible(false);
        AnchorPane_Ordini.setVisible(false);
        AnchorPane_Preferiti.setVisible(false);
        AnchorPane_Recensione.setVisible(false);
        AnchorPane_Profilo.setVisible(false);
        AnchorPane_DettagliOrdine.setVisible(false);
        AnchorPane_DatiVino.setVisible(false);
        AnchorPane_DatiOrdine.setVisible(false);
        AnchorPane_DatiOfferta.setVisible(false);
        AnchorPane_DatiRecensioni.setVisible(false);
        AnchorPane_Assistenza.setVisible(true);
        AnchorPane_Email.setVisible(false);

        try {
            Response r = this.requestController.makeRequest(Costanti.Mostra_Impiegati, new EmptyPayload());     //svolge richiesta per mostrare gli impiegati

            if(r.getStatusCode() == Costanti.Successo)
            {
                utente = (ArrayList<Utente>) r.getPayload();            //ArrayList di tipo utente
                ObservableList<Utente> utenti = FXCollections.observableArrayList(utente);

                ColonnaIDImpiegati_Assistenza.setCellValueFactory(cellData -> {
                    int ID_impiegato = cellData.getValue().getId();
                    IntegerProperty proprieta_id_impiegato = new SimpleIntegerProperty(ID_impiegato);
                    return Bindings.createObjectBinding(proprieta_id_impiegato::get, proprieta_id_impiegato);
                });

                ColonnaNomeImpiegato_Assistenza.setCellValueFactory(cellData -> {
                    String nome_impiegato = cellData.getValue().getNome();
                    StringProperty proprieta_nome_impiegato = new SimpleStringProperty(nome_impiegato);
                    return Bindings.createObjectBinding(proprieta_nome_impiegato::get, proprieta_nome_impiegato);
                });

                ColonnaEmailImpiegato_Assistenza.setCellValueFactory(cellData -> {
                    String email_impiegato = cellData.getValue().getEmail();
                    StringProperty proprieta_email_impiegato = new SimpleStringProperty(email_impiegato);
                    return Bindings.createObjectBinding(proprieta_email_impiegato::get, proprieta_email_impiegato);
                });

                ColonnaContatta_Assistenza.setCellFactory(param -> new TableCell<>() {
                    final Button btnContatta = new Button("Contatta");

                    {setAlignment(Pos.CENTER);}
                    @Override
                    protected void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);

                        if (empty)
                        {
                            setGraphic(null);
                            setText(null);
                        } else {
                            btnContatta.getStyleClass().add("btn");     //definisce lo stile del bottone
                            btnContatta.setOnAction(event -> {
                                UtenteSelezionato = TV_Assistenza.getSelectionModel().getSelectedItem();

                                if(UtenteSelezionato != null)
                                {
                                    AnchorPane_Home.setVisible(true);
                                    AnchorPane_Ricerca.setVisible(false);
                                    AnchorPane_Carrello.setVisible(false);
                                    AnchorPane_Offerte.setVisible(false);
                                    AnchorPane_Ordini.setVisible(false);
                                    AnchorPane_Preferiti.setVisible(false);
                                    AnchorPane_Recensione.setVisible(false);
                                    AnchorPane_Profilo.setVisible(false);
                                    AnchorPane_DettagliOrdine.setVisible(false);
                                    AnchorPane_DatiVino.setVisible(false);
                                    AnchorPane_DatiOrdine.setVisible(false);
                                    AnchorPane_DatiOfferta.setVisible(false);
                                    AnchorPane_DatiRecensioni.setVisible(false);
                                    AnchorPane_Assistenza.setVisible(false);
                                    AnchorPane_Email.setVisible(true);

                                    id_impiegato = ColonnaIDImpiegati_Assistenza.getCellData(UtenteSelezionato);
                                    System.out.println("ID: " + id_impiegato);

                                    email_contatto = ColonnaEmailImpiegato_Assistenza.getCellData(UtenteSelezionato);
                                    System.out.println("Email: " + email_contatto);

                                    lblDestinatario_Email.setText(email_contatto);
                                }
                            });
                            setGraphic(btnContatta);
                            setText(null);
                        }
                    }
                });
                TV_Assistenza.setItems(utenti);
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("No impiegati alert");
                alert.setHeaderText("Non sono presenti impiegati");
                alert.setContentText("Non sono presenti impiegati");
                alert.showAndWait();
            }
        } catch (Exception e)
        {
            System.out.println(e);
            throw new RuntimeException(e);
        }
    }

    //invio mail e aggiunta assistenza nel db
    @FXML
    void OnBtnInviaEmail_Click(ActionEvent event) {
        String oggetto = this.txtOggetto_Email.getText();
        String messaggio = this.txtEmail.getText();

        try {
            Response r = this.requestController.makeRequest(Costanti.Add_Assistenza, new Assistenza(id_impiegato, UtenteLoggato.getId(), messaggio));

            if(r.getStatusCode() == Costanti.Successo)
            {
                sendEmail(email_contatto, oggetto, messaggio);
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Errore");
                alert.setHeaderText("Assistenza non inserita con successo");
                alert.setContentText("Assistenza non inserita con successo");
                alert.showAndWait();
            }
        } catch (Exception e)
        {
            System.out.println(e);
            throw new RuntimeException(e);
        }
        txtOggetto_Email.clear();
        txtEmail.clear();
    }

    //dati profilo utente loggato
    @FXML
    void OnBtnProfiloCliente_Click(ActionEvent event) {
        AnchorPane_Home.setVisible(false);
        AnchorPane_Ricerca.setVisible(false);
        AnchorPane_Carrello.setVisible(false);
        AnchorPane_Offerte.setVisible(false);
        AnchorPane_Ordini.setVisible(false);
        AnchorPane_Preferiti.setVisible(false);
        AnchorPane_Recensione.setVisible(false);
        AnchorPane_Profilo.setVisible(true);
        AnchorPane_DettagliOrdine.setVisible(false);
        AnchorPane_DatiVino.setVisible(false);
        AnchorPane_DatiOrdine.setVisible(false);
        AnchorPane_DatiOfferta.setVisible(false);
        AnchorPane_DatiRecensioni.setVisible(false);
        AnchorPane_Assistenza.setVisible(false);
        AnchorPane_Email.setVisible(false);

        lblNome_Profilo.setText(UtenteLoggato.getNome());
        lblCognome_Profilo.setText(UtenteLoggato.getCognome());
        lblUsername_Profilo.setText(UtenteLoggato.getUsername());
        lblCF_Profilo.setText(UtenteLoggato.getCf());
        lblEmail_Profilo.setText(UtenteLoggato.getEmail());
        lblTelefono_Profilo.setText(UtenteLoggato.getTelefono());
        lblIndirizzo_Profilo.setText(UtenteLoggato.getIndirizzo());
    }

    //funzione logout
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

    //funzione che controlla che tutti i campi siano stati compilati
    private void Errore_Campi_Vuoti() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Errore campi vuoti");
        alert.setHeaderText("Si è verificato un'errore.");
        alert.setContentText("Tutti i campi devono essere compilati! ");
        alert.showAndWait();
    }

    //calcolo sconto
    private double Calcolo_Sconto(double quantita_vino_int, double prezzo_scontato)
    {
        if(quantita_vino_int == 6 || quantita_vino_int == 7)
        {
            prezzo_scontato_definitivo = (prezzo_scontato - (prezzo_scontato * 0.05)) * quantita_vino_int;
        } else if (quantita_vino_int == 8) {
            prezzo_scontato_definitivo = (prezzo_scontato - (prezzo_scontato * 0.06)) * quantita_vino_int;
        } else if (quantita_vino_int == 9) {
            prezzo_scontato_definitivo = (prezzo_scontato - (prezzo_scontato * 0.07)) * quantita_vino_int;
        } else if (quantita_vino_int == 10) {
            prezzo_scontato_definitivo = (prezzo_scontato - (prezzo_scontato * 0.08)) * quantita_vino_int;
        } else if (quantita_vino_int == 11) {
            prezzo_scontato_definitivo = (prezzo_scontato - (prezzo_scontato * 0.09)) * quantita_vino_int;
        } else if (quantita_vino_int == 12) {
            prezzo_scontato_definitivo = (prezzo_scontato - (prezzo_scontato * 0.10)) * quantita_vino_int;
        } else if (quantita_vino_int > 12) {
            prezzo_scontato_definitivo = (prezzo_scontato - (prezzo_scontato * 0.13)) * quantita_vino_int;
        } else if (quantita_vino_int < 6) {
            prezzo_scontato_definitivo = prezzo_scontato * quantita_vino_int;
        }

        totale = totale + prezzo_scontato_definitivo;

        return totale;
    }

    private void sendEmail(String destinatario, String oggetto, String messaggio)
    {
        Properties properties = new Properties();

        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");  // Sostituisci con il tuo host SMTP
        properties.put("mail.smtp.port", "587");  // Sostituisci con la porta SMTP appropriata

        // Crea un oggetto Session con le proprietà e un autenticatore
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(UtenteLoggato.getEmail(), "08052001");
            }
        });

        try {
            // Crea un oggetto MimeMessage
            Message emailMessage = new MimeMessage(session);

            emailMessage.setFrom(new InternetAddress(UtenteLoggato.getEmail()));
            emailMessage.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destinatario));
            emailMessage.setSubject(oggetto);
            emailMessage.setText(messaggio);

            //invia la mail
            Transport.send(emailMessage);

            //notifica l'utente che la mail è stata inviata
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Email inviata");
            alert.setHeaderText("Richiesta inviata");
            alert.setContentText("La tua richiesta d'assistenza e' stata inviata con successo.");
            alert.showAndWait();
        } catch (Exception e) {
            // Gestisci eventuali errori nell'invio dell'email
            e.printStackTrace();

            // Notifica l'utente in caso di errore
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Errore");
            alert.setHeaderText("Errore invio");
            alert.setContentText("Errore riscontrato durante l'invio della mail.\nPrego controllare i dati della vostra mail");
            alert.showAndWait();
        }
    }

    public void initialize() {
        ObservableList<String> opzioni_stato_ordine = FXCollections.observableArrayList("Inviato", "Preparazione", "Spedito", "Consegnato");        //ObservableList per definire i vari stati di un ordine
        CBSceltaStato_Ricerca.setItems(opzioni_stato_ordine);           //impostare nella ComboBox i dati presenti nell'ObservableList precedente

        ObservableList<String> opzioni_voto = FXCollections.observableArrayList("1", "2", "3", "4", "5");           //ObservableList per definire i vari voti da dare a un vino
        CBVotoVino_Recensione.setItems(opzioni_voto);           //impostare nella ComboBox i dati presenti nell'ObservableList precedente
        CBVotoVino_DatiRecensioni.setItems(opzioni_voto);

        ObservableList<String> opzioni_MP = FXCollections.observableArrayList("Bonifico bancario", "Carta di credito");         //ObservableList per definire i vari metodi di pagamento
        CBSceltaMP_Carrello.setItems(opzioni_MP);               //impostare nella ComboBox i dati presenti nell'ObservableList precedente
    }
}
