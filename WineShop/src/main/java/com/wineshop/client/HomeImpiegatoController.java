package com.wineshop.client;

import javafx.beans.binding.Bindings;
import javafx.beans.property.*;
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
import utilities.Costanti;
import utilities.EmptyPayload;
import utilities.Response;
import utilities.models.*;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

import java.util.ArrayList;
import java.util.List;

public class HomeImpiegatoController {
    private RequestController requestController;
    private Utente UtenteLoggato;
    @FXML
    private AnchorPane AnchorPane_DatiOrdine;
    @FXML
    private AnchorPane AnchorPane_DatiUtente;
    @FXML
    private AnchorPane AnchorPane_DatiVino;
    @FXML
    private AnchorPane AnchorPane_DettagliOrdine;
    @FXML
    private AnchorPane AnchorPane_Home;
    @FXML
    private AnchorPane AnchorPane_InserisciOfferta;
    @FXML
    private AnchorPane AnchorPane_ModificaOfferta;
    @FXML
    private AnchorPane AnchorPane_Offerte;
    @FXML
    private AnchorPane AnchorPane_Ordini;
    @FXML
    private AnchorPane AnchorPane_Profilo;
    @FXML
    private AnchorPane AnchorPane_Ricerca;
    @FXML
    private ComboBox<String> CBSceltaDati_Ricerca;
    @FXML
    private ComboBox<String> CBSceltaStato_Ordine;
    @FXML
    private ComboBox<String> CBSceltaStato_Ricerca;
    @FXML
    private TableColumn<Vino, Integer> ColonnaAnno_Vino;
    @FXML
    private TableColumn<Utente, String> ColonnaCognome_Cliente;
    @FXML
    private TableColumn<Offerta, String> ColonnaDescrizione_Offerta;
    @FXML
    private TableColumn<Utente, String> ColonnaEmail_Cliente;
    @FXML
    private TableColumn<Offerta, Void> ColonnaGestione_Offerta;
    @FXML
    private TableColumn<Utente, Integer> ColonnaID_Cliente;
    @FXML
    private TableColumn<Offerta, Integer> ColonnaID_Offerta;
    @FXML
    private TableColumn<Vino, Integer> ColonnaID_Vino;
    @FXML
    private TableColumn<Utente, Void> ColonnaInfo_Cliente;
    @FXML
    private TableColumn<Vino, Void> ColonnaInfo_Vino;
    @FXML
    private TableColumn<Utente, String> ColonnaNome_Cliente;
    @FXML
    private TableColumn<Vino, String> ColonnaNome_Vino;
    @FXML
    private TableColumn<Vino, Double> ColonnaPrezzo_Vino;
    @FXML
    private TableColumn<Offerta, Integer> ColonnaSconto_Offerta;
    @FXML
    private TableColumn<Utente, String> ColonnaUsername_Cliente;
    @FXML
    private TableColumn<Offerta, Integer> ColonnaVino_Offerta;
    @FXML
    private TableColumn<Vino, String> ColonnaVitigno_Vino;
    @FXML
    private TableColumn<Ordine, Void> Gestione_Ordini;
    @FXML
    private TableColumn<DettagliOrdine, Integer> ID_DettagliOrdine;
    @FXML
    private TableColumn<Ordine, Integer> ID_Ordini;
    @FXML
    private TableColumn<Ordine, String> Indirizzo_Ordini;
    @FXML
    private TableColumn<Ordine, String> MP_Ordini;
    @FXML
    private TableColumn<DettagliOrdine, Integer> Quantita_DettagliOrdine;
    @FXML
    private TableColumn<Ordine, String> Stato_Ordini;
    @FXML
    private TableView<DettagliOrdine> TV_DettagliOrdine;
    @FXML
    private TableView<Ordine> TV_Ordini;
    @FXML
    private TableView<Utente> TV_RicercaClienti;
    @FXML
    private TableView<Offerta> TV_RicercaOfferte;
    @FXML
    private TableView<Vino> TV_RicercaVini;
    @FXML
    private TableColumn<Ordine, Double> Totale_Ordini;
    @FXML
    private TableColumn<DettagliOrdine, Integer> Vino_DettagliOrdine;
    @FXML
    private Button btnApplicaOfferta_Vino;
    @FXML
    private Button btnEliminaOfferta;
    @FXML
    private Button btnHome_Impiegato;
    @FXML
    private Button btnInserisciOfferta;
    @FXML
    private Button btnLogout_Impiegato;
    @FXML
    private Button btnModificaOfferta;
    @FXML
    private Button btnModificaStatoOrdine;
    @FXML
    private Button btnOfferte_Impiegato;
    @FXML
    private Button btnOrdini_Impiegato;
    @FXML
    private Button btnProfilo_Impiegato;
    @FXML
    private Button btnRicerca_Impiegato;
    @FXML
    private Button btnRicerca_Offerte;
    @FXML
    private Button btnRicerca_Ordini;
    @FXML
    private Button btnRicerca_Ricerca;
    @FXML
    private Button btnVisualizzaDettagliOrdine;
    @FXML
    private ImageView imgBottglia_Vino;
    @FXML
    private Label lblAnno_Vino;
    @FXML
    private Label lblCF_Profilo;
    @FXML
    private Label lblCF_Utente;
    @FXML
    private Label lblCognome_Profilo;
    @FXML
    private Label lblCognome_Utente;
    @FXML
    private Label lblDescrizione_Vino;
    @FXML
    private Label lblEmail_Profilo;
    @FXML
    private Label lblEmail_Utente;
    @FXML
    private Label lblIDCliente_Ordine;
    @FXML
    private Label lblID_Ordine;
    @FXML
    private Label lblID_Utente;
    @FXML
    private Label lblIndirizzo_Ordine;
    @FXML
    private Label lblIndirizzo_Profilo;
    @FXML
    private Label lblIndirizzo_Utente;
    @FXML
    private Label lblMP_Ordine;
    @FXML
    private Label lblNome_Profilo;
    @FXML
    private Label lblNome_Utente;
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
    private Label lblTelefono_Utente;
    @FXML
    private Label lblTotale_Ordine;
    @FXML
    private Label lblUsername_Profilo;
    @FXML
    private Label lblUsername_Utente;
    @FXML
    private Label lblVitigno_Vino;
    @FXML
    private Label lblStatoModificato_Ordine;
    @FXML
    private TextArea txtDescrizione_InserisciOfferta;
    @FXML
    private TextArea txtDescrizione_ModificaOfferta;
    @FXML
    private TextField txtRicerca_Offerte;
    @FXML
    private TextField txtRicerca_Ricerca;
    @FXML
    private TextField txtSconto_InserisciOfferta;
    @FXML
    private TextField txtSconto_ModificaOfferta;
    private String tipo_dato, sconto_offerta, descrizione_offerta;
    private ArrayList<Vino> vino;
    private ArrayList<Utente> cliente;
    private ArrayList<Offerta> offerta;
    private ArrayList<Ordine> ordine;
    private ArrayList<DettagliOrdine> dettagli_ordine;
    private Vino VinoSelezionato;
    private Utente ClienteSelezionato;
    private Offerta OffertaSelezionata;
    private Ordine OrdineSelezionato;
    private int id_vino = 0, id_cliente = 0, id_offerta = 0, id_ordine = 0, sconto_int_offerta = 0, id_vino_offerta = 0, id_nuova_offerta = 0;
    private Image immagine;

    @FXML
    void OnBtnHomeImpiegato_Click(ActionEvent event) {
        AnchorPane_Home.setVisible(true);
        AnchorPane_Profilo.setVisible(false);
        AnchorPane_Ordini.setVisible(false);
        AnchorPane_DatiVino.setVisible(false);
        AnchorPane_DatiUtente.setVisible(false);
        AnchorPane_ModificaOfferta.setVisible(false);
        AnchorPane_InserisciOfferta.setVisible(false);
        AnchorPane_DatiOrdine.setVisible(false);
        AnchorPane_DettagliOrdine.setVisible(false);
        AnchorPane_Ricerca.setVisible(false);
        AnchorPane_Offerte.setVisible(false);
    }

    @FXML
    void OnBtnLogoutImpiegato_Click(ActionEvent event) throws Exception {
        System.out.println("Effettuando il Logout");

        Response response = this.requestController.makeRequest(Costanti.Logout, UtenteLoggato);

        if (response.getStatusCode() != Costanti.Successo) {
            System.out.println("Logout errato!");
            return;
        } else if (response.getStatusCode() == Costanti.Successo) {
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
    void OnBtnProfiloImpiegato_Click(ActionEvent event) {
        AnchorPane_Home.setVisible(false);
        AnchorPane_Profilo.setVisible(true);
        AnchorPane_Ordini.setVisible(false);
        AnchorPane_DatiVino.setVisible(false);
        AnchorPane_DatiUtente.setVisible(false);
        AnchorPane_ModificaOfferta.setVisible(false);
        AnchorPane_InserisciOfferta.setVisible(false);
        AnchorPane_DatiOrdine.setVisible(false);
        AnchorPane_DettagliOrdine.setVisible(false);
        AnchorPane_Ricerca.setVisible(false);
        AnchorPane_Offerte.setVisible(false);

        lblNome_Profilo.setText(UtenteLoggato.getNome());
        lblCognome_Profilo.setText(UtenteLoggato.getCognome());
        lblUsername_Profilo.setText(UtenteLoggato.getUsername());
        lblCF_Profilo.setText(UtenteLoggato.getCf());
        lblEmail_Profilo.setText(UtenteLoggato.getEmail());
        lblTelefono_Profilo.setText(UtenteLoggato.getTelefono());
        lblIndirizzo_Profilo.setText(UtenteLoggato.getIndirizzo());
    }

    @FXML
    void OnBtnRicercaImpiegato_Click(ActionEvent event) {
        txtRicerca_Ricerca.clear();
        txtRicerca_Ricerca.setVisible(false);
        btnRicerca_Ricerca.setVisible(false);

        TV_RicercaClienti.refresh();
        TV_RicercaVini.refresh();
        TV_RicercaClienti.setVisible(false);
        TV_RicercaVini.setVisible(false);

        CBSceltaDati_Ricerca.setPromptText("Seleziona la tipologia di dato da cercare");
        CBSceltaDati_Ricerca.setValue("Seleziona la tipologia di dato da cercare");

        AnchorPane_Home.setVisible(false);
        AnchorPane_Profilo.setVisible(false);
        AnchorPane_Ordini.setVisible(false);
        AnchorPane_DatiVino.setVisible(false);
        AnchorPane_DatiUtente.setVisible(false);
        AnchorPane_ModificaOfferta.setVisible(false);
        AnchorPane_InserisciOfferta.setVisible(false);
        AnchorPane_DatiOrdine.setVisible(false);
        AnchorPane_DettagliOrdine.setVisible(false);
        AnchorPane_Ricerca.setVisible(true);
        AnchorPane_Offerte.setVisible(false);

        CBSceltaDati_Ricerca.setOnAction(event1 -> {
            tipo_dato = this.CBSceltaDati_Ricerca.getValue();
            System.out.println("Dato selezionato: " + tipo_dato);

            Response r;

            if (tipo_dato.equals("Vini")) {
                txtRicerca_Ricerca.setVisible(true);
                btnRicerca_Ricerca.setVisible(true);
                txtRicerca_Ricerca.setPromptText("Inserire il nome del vino o il suo anno di produzione");
                TV_RicercaVini.setVisible(true);
                TV_RicercaClienti.setVisible(false);

                try {
                    r = this.requestController.makeRequest(Costanti.Mostra_Vini, new EmptyPayload());

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

                        ColonnaAnno_Vino.setCellValueFactory(cellData -> {
                            int anno_vino = cellData.getValue().getAnno();
                            IntegerProperty proprieta_anno_vino = new SimpleIntegerProperty(anno_vino);
                            return Bindings.createObjectBinding(proprieta_anno_vino::get, proprieta_anno_vino);
                        });

                        ColonnaVitigno_Vino.setCellValueFactory(cellData -> {
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
                                        VinoSelezionato = TV_RicercaVini.getSelectionModel().getSelectedItem();

                                        if (VinoSelezionato != null) {
                                            AnchorPane_Home.setVisible(false);
                                            AnchorPane_Profilo.setVisible(false);
                                            AnchorPane_Ordini.setVisible(false);
                                            AnchorPane_DatiVino.setVisible(true);
                                            AnchorPane_DatiUtente.setVisible(false);
                                            AnchorPane_ModificaOfferta.setVisible(false);
                                            AnchorPane_InserisciOfferta.setVisible(false);
                                            AnchorPane_DatiOrdine.setVisible(false);
                                            AnchorPane_DettagliOrdine.setVisible(false);
                                            AnchorPane_Ricerca.setVisible(false);
                                            AnchorPane_Offerte.setVisible(false);

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
            } else if (tipo_dato.equals("Clienti")) {
                txtRicerca_Ricerca.setVisible(true);
                btnRicerca_Ricerca.setVisible(true);
                txtRicerca_Ricerca.setPromptText("Inserire il cognome del cliente");
                TV_RicercaVini.setVisible(false);
                TV_RicercaClienti.setVisible(true);

                try {
                    r = this.requestController.makeRequest(Costanti.Mostra_Clienti, new EmptyPayload());

                    if (r.getStatusCode() == Costanti.Successo) {
                        cliente = (ArrayList<Utente>) r.getPayload();
                        ObservableList<Utente> clienti = FXCollections.observableArrayList(cliente);

                        ColonnaID_Cliente.setCellValueFactory(cellData -> {
                            int ID_cliente = cellData.getValue().getId();
                            id_cliente = ID_cliente;
                            IntegerProperty proprieta_id_cliente = new SimpleIntegerProperty(ID_cliente);
                            return Bindings.createObjectBinding(proprieta_id_cliente::get, proprieta_id_cliente);
                        });

                        ColonnaNome_Cliente.setCellValueFactory(cellData -> {
                            String nome_cliente = cellData.getValue().getNome();
                            StringProperty proprieta_nome_cliente = new SimpleStringProperty(nome_cliente);
                            return Bindings.createObjectBinding(proprieta_nome_cliente::get, proprieta_nome_cliente);
                        });

                        ColonnaCognome_Cliente.setCellValueFactory(cellData -> {
                            String cognome_cliente = cellData.getValue().getCognome();
                            StringProperty proprieta_cognome_cliente = new SimpleStringProperty(cognome_cliente);
                            return Bindings.createObjectBinding(proprieta_cognome_cliente::get, proprieta_cognome_cliente);
                        });

                        ColonnaUsername_Cliente.setCellValueFactory(cellData -> {
                            String username_cliente = cellData.getValue().getUsername();
                            StringProperty proprieta_username_cliente = new SimpleStringProperty(username_cliente);
                            return Bindings.createObjectBinding(proprieta_username_cliente::get, proprieta_username_cliente);
                        });

                        ColonnaEmail_Cliente.setCellValueFactory(cellData -> {
                            String email_cliente = cellData.getValue().getEmail();
                            StringProperty proprieta_email_cliente = new SimpleStringProperty(email_cliente);
                            return Bindings.createObjectBinding(proprieta_email_cliente::get, proprieta_email_cliente);
                        });

                        ColonnaInfo_Cliente.setCellFactory(param -> new TableCell<>() {
                            final Button btnGestione_Cliente = new Button("Info");

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
                                    btnGestione_Cliente.getStyleClass().add("btn");
                                    btnGestione_Cliente.setOnAction(event -> {
                                        ClienteSelezionato = TV_RicercaClienti.getSelectionModel().getSelectedItem();

                                        if (ClienteSelezionato != null) {
                                            AnchorPane_Home.setVisible(false);
                                            AnchorPane_Profilo.setVisible(false);
                                            AnchorPane_Ordini.setVisible(false);
                                            AnchorPane_DatiVino.setVisible(false);
                                            AnchorPane_DatiUtente.setVisible(true);
                                            AnchorPane_ModificaOfferta.setVisible(false);
                                            AnchorPane_InserisciOfferta.setVisible(false);
                                            AnchorPane_DatiOrdine.setVisible(false);
                                            AnchorPane_DettagliOrdine.setVisible(false);
                                            AnchorPane_Ricerca.setVisible(false);
                                            AnchorPane_Offerte.setVisible(false);

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
                } catch (Exception e) {
                    System.out.println(e);
                    throw new RuntimeException(e);
                }
            }
        });
    }

    @FXML
    void OnBtnRicerca_Click(ActionEvent event) {
        String dato_cercato;
        System.out.println("Dato : " + tipo_dato);
        Response r;

        if (tipo_dato.equals("Vini")) {
            txtRicerca_Ricerca.setVisible(true);
            dato_cercato = this.txtRicerca_Ricerca.getText();
            try {
                r = this.requestController.makeRequest(Costanti.Ricerca_Vini, new Vino(dato_cercato));

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

                    ColonnaAnno_Vino.setCellValueFactory(cellData -> {
                        int anno_vino = cellData.getValue().getAnno();
                        IntegerProperty proprieta_anno_vino = new SimpleIntegerProperty(anno_vino);
                        return Bindings.createObjectBinding(proprieta_anno_vino::get, proprieta_anno_vino);
                    });

                    ColonnaVitigno_Vino.setCellValueFactory(cellData -> {
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
                                    VinoSelezionato = TV_RicercaVini.getSelectionModel().getSelectedItem();

                                    if (VinoSelezionato != null) {
                                        AnchorPane_Home.setVisible(false);
                                        AnchorPane_Profilo.setVisible(false);
                                        AnchorPane_Ordini.setVisible(false);
                                        AnchorPane_DatiVino.setVisible(true);
                                        AnchorPane_DatiUtente.setVisible(false);
                                        AnchorPane_ModificaOfferta.setVisible(false);
                                        AnchorPane_InserisciOfferta.setVisible(false);
                                        AnchorPane_DatiOrdine.setVisible(false);
                                        AnchorPane_DettagliOrdine.setVisible(false);
                                        AnchorPane_Ricerca.setVisible(false);
                                        AnchorPane_Offerte.setVisible(false);

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
            } catch (Exception e) {
                System.out.println(e);
                throw new RuntimeException(e);
            }

        } else if (tipo_dato.equals("Clienti")) {
            txtRicerca_Ricerca.setVisible(true);
            dato_cercato = this.txtRicerca_Ricerca.getText();

            try {
                r = this.requestController.makeRequest(Costanti.Ricerca_Clienti, new Utente(dato_cercato, true));

                if (r.getStatusCode() == Costanti.Successo) {
                    cliente = (ArrayList<Utente>) r.getPayload();
                    ObservableList<Utente> clienti = FXCollections.observableArrayList(cliente);

                    ColonnaID_Cliente.setCellValueFactory(cellData -> {
                        int ID_cliente = cellData.getValue().getId();
                        id_cliente = ID_cliente;
                        IntegerProperty proprieta_id_cliente = new SimpleIntegerProperty(ID_cliente);
                        return Bindings.createObjectBinding(proprieta_id_cliente::get, proprieta_id_cliente);
                    });

                    ColonnaNome_Cliente.setCellValueFactory(cellData -> {
                        String nome_cliente = cellData.getValue().getNome();
                        StringProperty proprieta_nome_cliente = new SimpleStringProperty(nome_cliente);
                        return Bindings.createObjectBinding(proprieta_nome_cliente::get, proprieta_nome_cliente);
                    });

                    ColonnaCognome_Cliente.setCellValueFactory(cellData -> {
                        String cognome_cliente = cellData.getValue().getCognome();
                        StringProperty proprieta_cognome_cliente = new SimpleStringProperty(cognome_cliente);
                        return Bindings.createObjectBinding(proprieta_cognome_cliente::get, proprieta_cognome_cliente);
                    });

                    ColonnaUsername_Cliente.setCellValueFactory(cellData -> {
                        String username_cliente = cellData.getValue().getUsername();
                        StringProperty proprieta_username_cliente = new SimpleStringProperty(username_cliente);
                        return Bindings.createObjectBinding(proprieta_username_cliente::get, proprieta_username_cliente);
                    });

                    ColonnaEmail_Cliente.setCellValueFactory(cellData -> {
                        String email_cliente = cellData.getValue().getEmail();
                        StringProperty proprieta_email_cliente = new SimpleStringProperty(email_cliente);
                        return Bindings.createObjectBinding(proprieta_email_cliente::get, proprieta_email_cliente);
                    });

                    ColonnaInfo_Cliente.setCellFactory(param -> new TableCell<>() {
                        final Button btnGestione_Cliente = new Button("Info");

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
                                btnGestione_Cliente.getStyleClass().add("btn");
                                btnGestione_Cliente.setOnAction(event -> {
                                    ClienteSelezionato = TV_RicercaClienti.getSelectionModel().getSelectedItem();

                                    if (ClienteSelezionato != null) {
                                        AnchorPane_Home.setVisible(false);
                                        AnchorPane_Profilo.setVisible(false);
                                        AnchorPane_Ordini.setVisible(false);
                                        AnchorPane_DatiVino.setVisible(false);
                                        AnchorPane_DatiUtente.setVisible(true);
                                        AnchorPane_ModificaOfferta.setVisible(false);
                                        AnchorPane_InserisciOfferta.setVisible(false);
                                        AnchorPane_DatiOrdine.setVisible(false);
                                        AnchorPane_DettagliOrdine.setVisible(false);
                                        AnchorPane_Ricerca.setVisible(false);
                                        AnchorPane_Offerte.setVisible(false);

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
            } catch (Exception e) {
                System.out.println(e);
                throw new RuntimeException(e);
            }
        }
        txtRicerca_Ricerca.clear();
    }

    @FXML
    void OnBtnApplicaOffertaVino_Click(ActionEvent event) {
        AnchorPane_Home.setVisible(false);
        AnchorPane_Profilo.setVisible(false);
        AnchorPane_Ordini.setVisible(false);
        AnchorPane_DatiVino.setVisible(false);
        AnchorPane_DatiUtente.setVisible(false);
        AnchorPane_ModificaOfferta.setVisible(false);
        AnchorPane_InserisciOfferta.setVisible(true);
        AnchorPane_DatiOrdine.setVisible(false);
        AnchorPane_DettagliOrdine.setVisible(false);
        AnchorPane_Ricerca.setVisible(false);
        AnchorPane_Offerte.setVisible(false);
    }

    @FXML
    void OnBtnOfferteImpiegato_Click(ActionEvent event) {
        AnchorPane_Home.setVisible(false);
        AnchorPane_Profilo.setVisible(false);
        AnchorPane_Ordini.setVisible(false);
        AnchorPane_DatiVino.setVisible(false);
        AnchorPane_DatiUtente.setVisible(false);
        AnchorPane_ModificaOfferta.setVisible(false);
        AnchorPane_InserisciOfferta.setVisible(false);
        AnchorPane_DatiOrdine.setVisible(false);
        AnchorPane_DettagliOrdine.setVisible(false);
        AnchorPane_Ricerca.setVisible(false);
        AnchorPane_Offerte.setVisible(true);

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
                                    AnchorPane_Ordini.setVisible(false);
                                    AnchorPane_DatiVino.setVisible(false);
                                    AnchorPane_DatiUtente.setVisible(false);
                                    AnchorPane_ModificaOfferta.setVisible(true);
                                    AnchorPane_InserisciOfferta.setVisible(false);
                                    AnchorPane_DatiOrdine.setVisible(false);
                                    AnchorPane_DettagliOrdine.setVisible(false);
                                    AnchorPane_Ricerca.setVisible(false);
                                    AnchorPane_Offerte.setVisible(false);

                                    id_offerta = ColonnaID_Offerta.getCellData(OffertaSelezionata);
                                    System.out.println("ID: " + id_offerta);

                                    id_vino_offerta = ColonnaVino_Offerta.getCellData(OffertaSelezionata);
                                    System.out.println("ID vino: " + id_vino_offerta);

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
                                        AnchorPane_Ordini.setVisible(false);
                                        AnchorPane_DatiVino.setVisible(false);
                                        AnchorPane_DatiUtente.setVisible(false);
                                        AnchorPane_ModificaOfferta.setVisible(true);
                                        AnchorPane_InserisciOfferta.setVisible(false);
                                        AnchorPane_DatiOrdine.setVisible(false);
                                        AnchorPane_DettagliOrdine.setVisible(false);
                                        AnchorPane_Ricerca.setVisible(false);
                                        AnchorPane_Offerte.setVisible(false);

                                        id_offerta = ColonnaID_Offerta.getCellData(OffertaSelezionata);
                                        System.out.println("ID: " + id_offerta);

                                        id_vino_offerta = ColonnaVino_Offerta.getCellData(OffertaSelezionata);
                                        System.out.println("ID vino: " + id_vino_offerta);
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
    void OnBtnInserisciOfferta_Click(ActionEvent event) {
        sconto_offerta = this.txtSconto_InserisciOfferta.getText();
        descrizione_offerta = this.txtDescrizione_InserisciOfferta.getText();
        System.out.println("ID vino: " + id_vino);

        if(sconto_offerta.isEmpty() || descrizione_offerta.isEmpty())
        {
            String s = "Il campo non può essere vuoto";
            Errore_Campi_Vuoti(s);
            return;
        }

        if (sconto_offerta.matches("[a-zA-Z\\s]+")) {
            System.out.println("Errore ricerca offerta");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Sconto offerta");
            alert.setHeaderText("Si è verificato un'errore.");
            alert.setContentText("Lo sconto di un'offerta può essere solo un numero intero.\nPrego reiserire.");
            alert.showAndWait();
        } else {
            sconto_int_offerta = Integer.parseInt(sconto_offerta);

            if(sconto_int_offerta > 0 || sconto_int_offerta <= 100)
            {
                try {
                    Offerta o = new Offerta(id_nuova_offerta, id_vino, sconto_int_offerta, descrizione_offerta);

                    Response r = this.requestController.makeRequest(Costanti.Add_Offerta, o);

                    if (r.getStatusCode() == Costanti.Successo) {
                        System.out.println("Offerta aggiunta con successo");
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Offerta aggiunta");
                        alert.setHeaderText("Offerta aggiunta con successo");
                        alert.setContentText("Offerta aggiunta con successo");
                        alert.showAndWait();
                        txtSconto_InserisciOfferta.clear();
                    } else {
                        System.out.println("L'offerta non è stato inserita");
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("L'offerta non è stato inserita");
                        alert.setHeaderText("Si è verificato un'errore.");
                        alert.setContentText("L'aggiunta dell'offerta non è andata a buon fine");
                        alert.showAndWait();
                    }
                } catch (Exception e) {
                    System.out.println(e);
                    throw new RuntimeException(e);
                }
            } else {
                Errore_Sconto();
                return;
            }

            txtDescrizione_InserisciOfferta.clear();
            txtSconto_InserisciOfferta.clear();
        }
    }

    @FXML
    void OnBtnEliminaOfferta_Click(ActionEvent event) {
        try {
            Response r = this.requestController.makeRequest(Costanti.Elimina_Offerta, new Offerta(id_offerta));

            if (r.getStatusCode() == Costanti.Successo)
            {
                System.out.println("Offerta eliminata con successo");
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Offerta eliminata");
                alert.setHeaderText("Offerta eliminata con successo");
                alert.setContentText("Offerta eliminata con successo");
                alert.showAndWait();

                AnchorPane_Home.setVisible(true);
                AnchorPane_Profilo.setVisible(false);
                AnchorPane_Ordini.setVisible(false);
                AnchorPane_DatiVino.setVisible(false);
                AnchorPane_DatiUtente.setVisible(false);
                AnchorPane_ModificaOfferta.setVisible(false);
                AnchorPane_InserisciOfferta.setVisible(false);
                AnchorPane_DatiOrdine.setVisible(false);
                AnchorPane_DettagliOrdine.setVisible(false);
                AnchorPane_Ricerca.setVisible(false);
                AnchorPane_Offerte.setVisible(false);
                TV_RicercaOfferte.refresh();
            } else {
                System.out.println("Errore");
                Alert alert = new Alert(Alert.AlertType.ERROR);
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

    @FXML
    void OnBtnModificaOfferta_Click(ActionEvent event) {
        Response r;

        String sconto_nuovo = this.txtSconto_ModificaOfferta.getText();
        String descrizione_nuovo = this.txtDescrizione_ModificaOfferta.getText();

        if (sconto_nuovo.matches("[a-zA-Z\\s]+")) {
            System.out.println("Errore sconto offerta");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Sconto offerta");
            alert.setHeaderText("Si è verificato un'errore.");
            alert.setContentText("Lo sconto di un'offerta può essere solo un numero intero.\nPrego reiserire.");
            alert.showAndWait();
            txtSconto_ModificaOfferta.clear();
            return;
        } else {
            if(sconto_nuovo.isEmpty())
            {
                try {
                    Offerta offerta_modificata_sconto = new Offerta(id_offerta, id_vino_offerta, descrizione_nuovo);
                    r = this.requestController.makeRequest(Costanti.Modifica_Dati_Offerta_Sconto, offerta_modificata_sconto);

                    if(r.getStatusCode() == Costanti.Successo)
                    {
                        System.out.println("Offerta modificata con successo");
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Offerta modificata");
                        alert.setHeaderText("Offerta modificata con successo");
                        alert.setContentText("Offerta modificata con successo");
                        alert.showAndWait();

                    } else {
                        System.out.println("L'offerta non è stato modificato");
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("L'offerta non è stato modificato");
                        alert.setHeaderText("Si è verificato un'errore.");
                        alert.setContentText("La modifica dell'offerta non è andata a buon fine");
                        alert.showAndWait();
                    }
                } catch (Exception e)
                {
                    System.out.println(e);
                    throw new RuntimeException(e);
                }
            } else {
                int sconto_nuovo_int = Integer.parseInt(sconto_nuovo);

                if(sconto_nuovo_int < 1 || sconto_nuovo_int > 100)
                {
                    Errore_Sconto();
                    return;
                } else {
                    try {
                        Offerta offerta_modificata = new Offerta(id_offerta, id_vino_offerta, sconto_nuovo_int, descrizione_nuovo);
                        r = this.requestController.makeRequest(Costanti.Modifica_Dati_Offerta, offerta_modificata);

                        if(r.getStatusCode() == Costanti.Successo)
                        {
                            System.out.println("Offerta modificata con successo");
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("Offerta modificata");
                            alert.setHeaderText("Offerta modificata con successo");
                            alert.setContentText("Offerta modificata con successo");
                            alert.showAndWait();
                        } else {
                            System.out.println("L'offerta non è stato modificato");
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("L'offerta non è stato modificato");
                            alert.setHeaderText("Si è verificato un'errore.");
                            alert.setContentText("La modifica dell'offerta non è andata a buon fine");
                            alert.showAndWait();
                        }
                    } catch (Exception e)
                    {
                        System.out.println(e);
                        throw new RuntimeException(e);
                    }
                }
            }
            txtSconto_ModificaOfferta.clear();
            txtDescrizione_ModificaOfferta.clear();
        }
    }
    @FXML
    void OnBtnOrdiniImpiegato_Click(ActionEvent event) {
        CBSceltaStato_Ricerca.setPromptText("Selezionare stato ordine");
        CBSceltaStato_Ricerca.setValue("Selezionare stato ordine");

        AnchorPane_Home.setVisible(false);
        AnchorPane_Profilo.setVisible(false);
        AnchorPane_Ordini.setVisible(true);
        AnchorPane_DatiVino.setVisible(false);
        AnchorPane_DatiUtente.setVisible(false);
        AnchorPane_ModificaOfferta.setVisible(false);
        AnchorPane_InserisciOfferta.setVisible(false);
        AnchorPane_DatiOrdine.setVisible(false);
        AnchorPane_DettagliOrdine.setVisible(false);
        AnchorPane_Ricerca.setVisible(false);
        AnchorPane_Offerte.setVisible(false);

        try {
            Response r = this.requestController.makeRequest(Costanti.Mostra_Ordini, new EmptyPayload());

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
                                    AnchorPane_Ordini.setVisible(false);
                                    AnchorPane_DatiVino.setVisible(false);
                                    AnchorPane_DatiUtente.setVisible(false);
                                    AnchorPane_ModificaOfferta.setVisible(false);
                                    AnchorPane_InserisciOfferta.setVisible(false);
                                    AnchorPane_DatiOrdine.setVisible(true);
                                    AnchorPane_DettagliOrdine.setVisible(false);
                                    AnchorPane_Ricerca.setVisible(false);
                                    AnchorPane_Offerte.setVisible(false);

                                    if(Stato_Ordini.getCellData(OrdineSelezionato).equals("Consegnato"))
                                    {
                                        lblStatoModificato_Ordine.setVisible(false);
                                        CBSceltaStato_Ordine.setVisible(false);
                                    } else {
                                        lblStatoModificato_Ordine.setVisible(true);
                                        CBSceltaStato_Ordine.setVisible(true);
                                    }

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
        String ordine_cercato = this.CBSceltaStato_Ricerca.getSelectionModel().getSelectedItem().toString();

        try {
            Response r = this.requestController.makeRequest(Costanti.Ricerca_Ordini, new Ordine(ordine_cercato));

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
                                    AnchorPane_Ordini.setVisible(false);
                                    AnchorPane_DatiVino.setVisible(false);
                                    AnchorPane_DatiUtente.setVisible(false);
                                    AnchorPane_ModificaOfferta.setVisible(false);
                                    AnchorPane_InserisciOfferta.setVisible(false);
                                    AnchorPane_DatiOrdine.setVisible(true);
                                    AnchorPane_DettagliOrdine.setVisible(false);
                                    AnchorPane_Ricerca.setVisible(false);
                                    AnchorPane_Offerte.setVisible(false);

                                    if(Stato_Ordini.getCellData(OrdineSelezionato).equals("Consegnato"))
                                    {
                                        lblStatoModificato_Ordine.setVisible(false);
                                        CBSceltaStato_Ordine.setVisible(false);
                                    } else {
                                        lblStatoModificato_Ordine.setVisible(true);
                                        CBSceltaStato_Ordine.setVisible(true);
                                    }

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
    void OnBtnModificaStatoOrdine_Click(ActionEvent event) {
        String stato_nuovo = this.CBSceltaStato_Ordine.getSelectionModel().getSelectedItem().toString();

        try {
            Ordine ordine_aggiornato = new Ordine(id_ordine, stato_nuovo);
            Response r = this.requestController.makeRequest(Costanti.Modifica_Stato_Ordine, ordine_aggiornato);

            if(r.getStatusCode() == Costanti.Successo)
            {
                System.out.println("Stato aggiornato con successo");
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Stato aggiornato");
                alert.setHeaderText("Stato aggiornato con successo");
                alert.setContentText("Stato aggiornato con successo");
                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Stato alert");
                alert.setHeaderText("Non è stato possibile aggiornare lo stato dell'ordine");
                alert.setContentText("Non è stato possibile aggiornare lo stato dell'ordine");
                alert.showAndWait();
            }

        } catch (Exception e)
        {
            System.out.println(e);
            throw new RuntimeException(e);
        }
    }
    @FXML
    void OnBtnVisualizzaDettagliOrdine_Click(ActionEvent event) {
        AnchorPane_Home.setVisible(false);
        AnchorPane_Profilo.setVisible(false);
        AnchorPane_Ordini.setVisible(false);
        AnchorPane_DatiVino.setVisible(false);
        AnchorPane_DatiUtente.setVisible(false);
        AnchorPane_ModificaOfferta.setVisible(false);
        AnchorPane_InserisciOfferta.setVisible(false);
        AnchorPane_DatiOrdine.setVisible(false);
        AnchorPane_DettagliOrdine.setVisible(true);
        AnchorPane_Ricerca.setVisible(false);
        AnchorPane_Offerte.setVisible(false);

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
                    int Vino_DettagliOrdine = cellData.getValue().getID();
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

    public void setRequestController(RequestController controller) {
        this.requestController = controller;
    }

    //funzione che controlla che tutti i campi siano stati compilati
    private void Errore_Campi_Vuoti(String s) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Errore campi vuoti");
        alert.setHeaderText("Si è verificato un'errore.");
        alert.setContentText("Tutti i campi devono essere compilati! " + s);
        alert.showAndWait();
    }

    //funzione che controlla che lo sconto applicato sia tra 1 a 100
    private void Errore_Sconto() {
        System.out.println("Errore sconto offerta");
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Sconto offerta");
        alert.setHeaderText("Si è verificato un'errore.");
        alert.setContentText("Lo sconto può essere compreso solo tra 1 e 100.\nPrego reiserire.");
        alert.showAndWait();
    }
    public void setLoggedUser(Utente utente) {
        this.UtenteLoggato = utente;
    }

    public void initialize() {
        ObservableList<String> opzioni_ricerca = FXCollections.observableArrayList("Vini", "Clienti");
        CBSceltaDati_Ricerca.setItems(opzioni_ricerca);

        ObservableList<String> opzioni_stato_ordine = FXCollections.observableArrayList("Spedito", "Consegnato", "Preparazione");
        CBSceltaStato_Ordine.setItems(opzioni_stato_ordine);
        CBSceltaStato_Ricerca.setItems(opzioni_stato_ordine);
    }
}