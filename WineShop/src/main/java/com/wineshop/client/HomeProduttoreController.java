package com.wineshop.client;

import javafx.beans.property.*;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.scene.image.ImageView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.util.converter.IntegerStringConverter;
import utilities.EmptyPayload;
import utilities.models.Utente;
import utilities.models.Vino;
import utilities.Costanti;
import utilities.Response;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import java.io.File;

import java.io.File;
import java.io.IOException;
import java.net.IDN;
import java.util.ArrayList;

public class HomeProduttoreController {
    private RequestController requestController;
    private Utente UtenteLoggato;
    @FXML
    private AnchorPane AnchorPane_Home;
    @FXML
    private AnchorPane AnchorPane_InsVino;
    @FXML
    private AnchorPane AnchorPane_Profilo;
    @FXML
    private AnchorPane AnchorPane_Ricerca;
    @FXML
    private AnchorPane AnchorPane_ModificaVino;
    @FXML
    private TableColumn<Vino, Integer> ColonnaID = new TableColumn<>();
    @FXML
    private TableColumn<Vino, Void> ColonnaBtnModifica = new TableColumn<>();;
    @FXML
    private TableColumn<Vino, String> ColonnaNome = new TableColumn<>();;
    @FXML
    private TableColumn<Vino, Double> ColonnaPrezzo = new TableColumn<>();;
    @FXML
    private TableColumn<Vino, String> ColonnaProvenienza = new TableColumn<>();;
    @FXML
    private TableColumn<Vino, Integer> ColonnaQuantita = new TableColumn<>();;
    @FXML
    private TableView<Vino> TV_Ricerca = new TableView<>();
    @FXML
    private Button btnAddImg_InsVino;
    @FXML
    private Button btnGestioneVini_Produttore;
    @FXML
    private Button btnHome_Produttore;
    @FXML
    private Button btnInserisciVino_Produttore;
    @FXML
    private Button btnInserisci_InsVino;
    @FXML
    private Button btnLogout_Produttore;
    @FXML
    private Button btnProfilo_Produttore;
    @FXML
    private Button btnRicerca_Ricerca;
    @FXML
    private Button btnAddImg_Modifica;
    @FXML
    private Button btnModifica_Modifica;
    @FXML
    private Button btnDeleteVino_Modifica;
    @FXML
    private Label lblCF_Profilo;
    @FXML
    private Label lblCognome_Profilo;
    @FXML
    private Label lblEmail_Profilo;
    @FXML
    private Label lblIndirizzo_Profilo;
    @FXML
    private Label lblNomeFile_InsVino;
    @FXML
    private Label lblNome_Profilo;
    @FXML
    private Label lblTelefono_Profilo;
    @FXML
    private Label lblUsername_Profilo;
    @FXML
    private Label lblNomeFile_Modifica;
    @FXML
    private TextField txtAnno_InsVino;
    @FXML
    private TextArea txtDescrizione_InsVino;
    @FXML
    private TextField txtNome_InsVino;
    @FXML
    private TextField txtPrezzo_InsVino;
    @FXML
    private TextField txtProvenienza_InsVino;
    @FXML
    private TextField txtQuantita_InsVino;
    @FXML
    private TextField txtRicerca_Ricerca;
    @FXML
    private TextField txtSoglia_InsVino;
    @FXML
    private TextField txtVitigno_InsVino;
    @FXML
    private TextField txtAnno_Modifica;
    @FXML
    private TextArea txtDescrizione_Modifica;
    @FXML
    private TextField txtNome_Modifica;
    @FXML
    private TextField txtPrezzo_Modifica;
    @FXML
    private TextField txtProvenienza_Modifica;
    @FXML
    private TextField txtVitigno_Modifica;
    @FXML
    private TextField txtQuantita_Modifica;
    @FXML
    private TextField txtSoglia_Modifica;
    @FXML
    private ImageView imgBottglia_InsVino;
    @FXML
    private ImageView imgBottglia_Modifica;
    private ArrayList<Vino> vino_produttore;
    private String immagine;
    private Image bottiglia;
    private FileChooser img;
    private FileChooser.ExtensionFilter filtro;
    private File FileSelezionato;
    private Vino VinoSelezionato;
    private int id_vino;

    public void setRequestController(RequestController controller) {
        this.requestController = controller;
    }

    public void setLoggedUser(Utente utente) {
        this.UtenteLoggato = utente;
    }

    @FXML
    void OnBtnCaricaImg_Click(ActionEvent event) {
        img = new FileChooser();
        img.setTitle("Seleziona immagine");

        filtro = new FileChooser.ExtensionFilter("Immagini", "*.png", "*.jpg");
        img.getExtensionFilters().add(filtro);

        FileSelezionato = img.showOpenDialog(new Stage());

        if(FileSelezionato != null)
        {
            System.out.println("File selezionato: " + FileSelezionato.getAbsolutePath());
            lblNomeFile_InsVino.setText(FileSelezionato.getAbsolutePath());

            immagine = FileSelezionato.getAbsolutePath().replace("\\", "/");
            System.out.println("File per db: " + immagine);

            bottiglia = new Image(FileSelezionato.getAbsolutePath());
            imgBottglia_InsVino.setImage(bottiglia);
        }
    }

    @FXML
    void OnBtnGestioneViniProduttore_Click(ActionEvent event) {
        AnchorPane_Home.setVisible(false);
        AnchorPane_Ricerca.setVisible(true);
        AnchorPane_InsVino.setVisible(false);
        AnchorPane_Profilo.setVisible(false);
        AnchorPane_ModificaVino.setVisible(false);

        try {
            Response r = this.requestController.makeRequest(Costanti.Mostra_Vini_Produttore, new EmptyPayload());

            if(r.getStatusCode() != Costanti.Successo)
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("No vini alert");
                alert.setHeaderText("Si è verificato un'errore.");
                alert.setContentText("Si è verificato un'errore.");
                alert.showAndWait();
            } else {
                vino_produttore = (ArrayList<Vino>) r.getPayload();
                ObservableList<Vino> vini_produttore = FXCollections.observableArrayList(vino_produttore);

                ColonnaID.setCellValueFactory(cellData -> {
                    int ID_vino = cellData.getValue().getID();
                    IntegerProperty proprieta_id_vino = new SimpleIntegerProperty(ID_vino);
                    return Bindings.createObjectBinding(proprieta_id_vino::get, proprieta_id_vino);
                });

                ColonnaNome.setCellValueFactory(cellData -> {
                    String nome_vino = cellData.getValue().getNome();
                    StringProperty proprieta_nome_vino = new SimpleStringProperty(nome_vino);
                    return Bindings.createObjectBinding(proprieta_nome_vino::get, proprieta_nome_vino);
                });

                ColonnaProvenienza.setCellValueFactory(cellData -> {
                    String provenienza_vino = cellData.getValue().getProvenienza();
                    StringProperty proprieta_provenienza_vino = new SimpleStringProperty(provenienza_vino);
                    return Bindings.createObjectBinding(proprieta_provenienza_vino::get, proprieta_provenienza_vino);
                });

                ColonnaQuantita.setCellValueFactory(cellData -> {
                    int quantita_vino = cellData.getValue().getQuantita();
                    IntegerProperty proprieta_quantita_vino = new SimpleIntegerProperty(quantita_vino);
                    return Bindings.createObjectBinding(proprieta_quantita_vino::get, proprieta_quantita_vino);
                });

                ColonnaPrezzo.setCellValueFactory(cellData -> {
                    double prezzo_vino = cellData.getValue().getPrezzo();
                    DoubleProperty proprieta_prezzo_vino = new SimpleDoubleProperty(prezzo_vino);
                    return Bindings.createObjectBinding(proprieta_prezzo_vino::get, proprieta_prezzo_vino);
                });

                ColonnaBtnModifica.setCellFactory(param -> new TableCell<>() {
                    final Button btnModifica = new Button("Modifica");

                    {setAlignment(Pos.CENTER);}
                    @Override
                    protected void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);

                        if (empty)
                        {
                            setGraphic(null);
                            setText(null);
                        } else {
                            btnModifica.getStyleClass().add("btn");
                            btnModifica.setOnAction(event -> {
                                VinoSelezionato = TV_Ricerca.getSelectionModel().getSelectedItem();

                                if(VinoSelezionato != null)
                                {
                                    AnchorPane_Home.setVisible(false);
                                    AnchorPane_Ricerca.setVisible(false);
                                    AnchorPane_InsVino.setVisible(false);
                                    AnchorPane_Profilo.setVisible(false);
                                    AnchorPane_ModificaVino.setVisible(true);

                                    id_vino = ColonnaID.getCellData(VinoSelezionato);
                                    System.out.println("ID: " + id_vino);
                                }


                            });
                            setGraphic(btnModifica);
                            setText(null);
                        }
                    }
                });
                TV_Ricerca.setItems(vini_produttore);
            }
        } catch (Exception e)
        {
            System.out.println(e);
            throw new RuntimeException(e);
        }
    }

    @FXML
    void OnBtnHomeProduttore_Click(ActionEvent event) {
        AnchorPane_Home.setVisible(true);
        AnchorPane_Ricerca.setVisible(false);
        AnchorPane_InsVino.setVisible(false);
        AnchorPane_Profilo.setVisible(false);
        AnchorPane_ModificaVino.setVisible(false);
    }

    @FXML
    void OnBtnInsVinoProduttore_Click(ActionEvent event) {
        AnchorPane_Home.setVisible(false);
        AnchorPane_Ricerca.setVisible(false);
        AnchorPane_InsVino.setVisible(true);
        AnchorPane_Profilo.setVisible(false);
        AnchorPane_ModificaVino.setVisible(false);
    }

    @FXML
    void OnBtnInserisciVino_Click(ActionEvent event) {
        id_vino = 0;
        String nome = this.txtNome_InsVino.getText();
        String provenienza = this.txtProvenienza_InsVino.getText();
        String descrizione = this.txtDescrizione_InsVino.getText();
        String vitigno = this.txtVitigno_InsVino.getText();
        String anno_stringa = this.txtAnno_InsVino.getText();
        String prezzo_stringa = this.txtPrezzo_InsVino.getText();
        String soglia_stringa = this.txtSoglia_InsVino.getText();
        String quantita_stringa = this.txtQuantita_InsVino.getText();
        int CODProduttore = UtenteLoggato.getId();

        if((nome.isEmpty()) || (provenienza.isEmpty()) || (descrizione.isEmpty()) || (vitigno.isEmpty()) || (anno_stringa.isEmpty()) || (prezzo_stringa.isEmpty()) || (soglia_stringa.isEmpty()) || (quantita_stringa.isEmpty()))
        {
            String s = "Il campo non può essere vuoto";
            Errore_Campi_Vuoti(s);
            return;
        }

        int anno = Integer.parseInt(this.txtAnno_InsVino.getText());
        double prezzo = Double.parseDouble(this.txtPrezzo_InsVino.getText());
        int soglia = Integer.parseInt(this.txtSoglia_InsVino.getText());
        int quantita = Integer.parseInt(this.txtQuantita_InsVino.getText());

        if(anno < 1900 || anno > 2024)
        {
            Errore_Anno();
            txtNome_InsVino.clear();
            txtProvenienza_InsVino.clear();
            txtAnno_InsVino.clear();
            txtDescrizione_InsVino.clear();
            txtVitigno_InsVino.clear();
            txtPrezzo_InsVino.clear();
            txtSoglia_InsVino.clear();
            txtQuantita_InsVino.clear();
            lblNomeFile_InsVino.setText("");
            imgBottglia_InsVino.setImage(null);
            return;
        }

        if((prezzo <= 0) || (soglia <= 0) || (quantita <= 0))
        {
            Errore_Interi();
            txtNome_InsVino.clear();
            txtProvenienza_InsVino.clear();
            txtAnno_InsVino.clear();
            txtDescrizione_InsVino.clear();
            txtVitigno_InsVino.clear();
            txtPrezzo_InsVino.clear();
            txtSoglia_InsVino.clear();
            txtQuantita_InsVino.clear();
            lblNomeFile_InsVino.setText("");
            imgBottglia_InsVino.setImage(null);
            return;
        }

        try
        {
            Vino VinoNuovo = new Vino(nome, provenienza, descrizione, vitigno, immagine, prezzo, id_vino, soglia, quantita, anno, CODProduttore);
            //System.out.println(immagine);
            Response r = this.requestController.makeRequest(Costanti.Add_Vino, VinoNuovo);

            if(r.getStatusCode() == Costanti.Successo)
            {
                System.out.println("Vino aggiunto con successo");
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Vino aggiunto");
                alert.setHeaderText("Vino aggiunto con successo");
                alert.setContentText("Vino aggiunto con successo");
                alert.showAndWait();
            } else {
                System.out.println("Il vino non è stato inserito");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Il vino non è stato inserito");
                alert.setHeaderText("Si è verificato un'errore.");
                alert.setContentText("L'aggiunta del vino non è andata a buon fine");
                alert.showAndWait();
            }
        } catch (Exception e)
        {
            System.out.println(e);
            throw new RuntimeException(e);
        }
        txtNome_InsVino.clear();
        txtProvenienza_InsVino.clear();
        txtAnno_InsVino.clear();
        txtDescrizione_InsVino.clear();
        txtVitigno_InsVino.clear();
        txtPrezzo_InsVino.clear();
        txtSoglia_InsVino.clear();
        txtQuantita_InsVino.clear();
        imgBottglia_InsVino.setImage(null);
        lblNomeFile_InsVino.setText("");

        AnchorPane_Home.setVisible(true);
        AnchorPane_Ricerca.setVisible(false);
        AnchorPane_InsVino.setVisible(false);
        AnchorPane_Profilo.setVisible(false);
        AnchorPane_ModificaVino.setVisible(false);
    }

    @FXML
    void OnBtnLogoutProduttore_Click(ActionEvent event) throws Exception {
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
    void OnBtnProfiloProduttore_Click(ActionEvent event) {
        AnchorPane_Home.setVisible(false);
        AnchorPane_Ricerca.setVisible(false);
        AnchorPane_InsVino.setVisible(false);
        AnchorPane_Profilo.setVisible(true);
        AnchorPane_ModificaVino.setVisible(false);

        lblNome_Profilo.setText(UtenteLoggato.getNome());
        lblCognome_Profilo.setText(UtenteLoggato.getCognome());
        lblUsername_Profilo.setText(UtenteLoggato.getUsername());
        lblCF_Profilo.setText(UtenteLoggato.getCf());
        lblEmail_Profilo.setText(UtenteLoggato.getEmail());
        lblTelefono_Profilo.setText(UtenteLoggato.getTelefono());
        lblIndirizzo_Profilo.setText(UtenteLoggato.getIndirizzo());
    }

    @FXML
    void OnBtnRicercaViniProduttore_Click(ActionEvent event)   {
        TV_Ricerca.refresh();
        String vino_cercato = this.txtRicerca_Ricerca.getText();

        try {
            Response r = this.requestController.makeRequest(Costanti.Ricerca_Vini_Produttore, new Vino(vino_cercato));

            if(r.getStatusCode() != Costanti.Successo)
            {
                System.out.println("Il vino non è presente nel DB");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Ricerca vino");
                alert.setHeaderText("Si è verificato un'errore.");
                alert.setContentText("Il vino non è presente nel DB");
                alert.showAndWait();
            } else {
                vino_produttore = (ArrayList<Vino>) r.getPayload();
                ObservableList<Vino> vini_produttore = FXCollections.observableArrayList(vino_produttore);

                ColonnaID.setCellValueFactory(cellData -> {
                    int ID_vino = cellData.getValue().getID();
                    id_vino = ID_vino;
                    IntegerProperty proprieta_id_vino = new SimpleIntegerProperty(ID_vino);
                    return Bindings.createObjectBinding(proprieta_id_vino::get, proprieta_id_vino);
                });

                ColonnaNome.setCellValueFactory(cellData -> {
                    String nome_vino = cellData.getValue().getNome();
                    StringProperty proprieta_nome_vino = new SimpleStringProperty(nome_vino);
                    return Bindings.createObjectBinding(proprieta_nome_vino::get, proprieta_nome_vino);
                });

                ColonnaProvenienza.setCellValueFactory(cellData -> {
                    String provenienza_vino = cellData.getValue().getProvenienza();
                    StringProperty proprieta_provenienza_vino = new SimpleStringProperty(provenienza_vino);
                    return Bindings.createObjectBinding(proprieta_provenienza_vino::get, proprieta_provenienza_vino);
                });

                ColonnaQuantita.setCellValueFactory(cellData -> {
                    int quantita_vino = cellData.getValue().getQuantita();
                    IntegerProperty proprieta_quantita_vino = new SimpleIntegerProperty(quantita_vino);
                    return Bindings.createObjectBinding(proprieta_quantita_vino::get, proprieta_quantita_vino);
                });

                ColonnaPrezzo.setCellValueFactory(cellData -> {
                    double prezzo_vino = cellData.getValue().getPrezzo();
                    DoubleProperty proprieta_prezzo_vino = new SimpleDoubleProperty(prezzo_vino);
                    return Bindings.createObjectBinding(proprieta_prezzo_vino::get, proprieta_prezzo_vino);
                });

                ColonnaBtnModifica.setCellFactory(param -> new TableCell<>() {
                    final Button btnModifica = new Button("Modifica");

                    {setAlignment(Pos.CENTER);}

                    @Override
                    protected void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);

                        if (empty)
                        {
                            setGraphic(null);
                            setText(null);
                        } else {
                            btnModifica.getStyleClass().add("btn");
                            btnModifica.setOnAction(event -> {
                                VinoSelezionato = TV_Ricerca.getSelectionModel().getSelectedItem();
                                //id_vino = riga_vino.getID();

                                if(VinoSelezionato != null)
                                {
                                    AnchorPane_Home.setVisible(false);
                                    AnchorPane_Ricerca.setVisible(false);
                                    AnchorPane_InsVino.setVisible(false);
                                    AnchorPane_Profilo.setVisible(false);
                                    AnchorPane_ModificaVino.setVisible(true);

                                    id_vino = ColonnaID.getCellData(VinoSelezionato);
                                    System.out.println("ID: " + id_vino);
                                }
                            });
                            setGraphic(btnModifica);
                            setText(null);
                        }
                    }
                });
                TV_Ricerca.setItems(vini_produttore);
            }
        } catch (Exception e)
        {
            System.out.println(e);
            throw new RuntimeException(e);
        }
        txtRicerca_Ricerca.clear();
    }
    @FXML
    void OnBtnModificaImg_Click(ActionEvent event) {
        img = new FileChooser();
        img.setTitle("Seleziona immagine");

        filtro = new FileChooser.ExtensionFilter("Immagini", "*.png", "*.jpg");
        img.getExtensionFilters().add(filtro);

        FileSelezionato = img.showOpenDialog(new Stage());

        if(FileSelezionato != null)
        {
            System.out.println("File selezionato: " + FileSelezionato.getAbsolutePath());
            lblNomeFile_Modifica.setText(FileSelezionato.getAbsolutePath());

            immagine = FileSelezionato.getAbsolutePath().replace("\\", "/");
            System.out.println("File per db: " + immagine);

            bottiglia = new Image(FileSelezionato.getAbsolutePath());
            imgBottglia_Modifica.setImage(bottiglia);
        }
    }
    @FXML
    void OnBtnModificaVino_Click(ActionEvent event) {
        //System.out.println(immagine);
        Response r;

        String nome_nuovo = this.txtNome_Modifica.getText();
        String provenienza_nuovo = this.txtProvenienza_Modifica.getText();
        String descrizione_nuovo = this.txtDescrizione_Modifica.getText();
        String vitigno_nuovo = this.txtVitigno_Modifica.getText();

        String anno_nuovo_stringa = this.txtAnno_Modifica.getText();
        String prezzo_nuovo_stringa = this.txtPrezzo_Modifica.getText();
        String soglia_nuovo_stringa = this.txtSoglia_Modifica.getText();
        String quantita_nuovo_stringa = this.txtQuantita_Modifica.getText();

        int CODProduttore = UtenteLoggato.getId();

        if ((anno_nuovo_stringa.isEmpty()) && (prezzo_nuovo_stringa.isEmpty()) && (soglia_nuovo_stringa.isEmpty()) && (quantita_nuovo_stringa.isEmpty())) {
            try {
                Vino vino_modificato = new Vino(nome_nuovo, provenienza_nuovo, descrizione_nuovo, vitigno_nuovo, immagine, id_vino, CODProduttore);
                r = this.requestController.makeRequest(Costanti.Modifica_Dati_Vini_Tutti, vino_modificato);

                if(r.getStatusCode() == Costanti.Successo)
                {
                    System.out.println("Vino modificato con successo");
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Vino modificato");
                    alert.setHeaderText("Vino modificato con successo");
                    alert.setContentText("Vino modificato con successo");
                    alert.showAndWait();

                } else {
                    System.out.println("Il vino non è stato modificato");
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Il vino non è stato modificato");
                    alert.setHeaderText("Si è verificato un'errore.");
                    alert.setContentText("La modifica del vino non è andata a buon fine");
                    alert.showAndWait();
                }
            } catch (Exception e)
            {
                System.out.println(e);
                throw new RuntimeException(e);
            }
        } else if (prezzo_nuovo_stringa.isEmpty()) {
            int anno_nuovo = Integer.parseInt(anno_nuovo_stringa);
            int soglia_nuovo = Integer.parseInt(soglia_nuovo_stringa);
            int quantita_nuovo = Integer.parseInt(quantita_nuovo_stringa);

            if(anno_nuovo < 1900 || anno_nuovo > 2024)
            {
                Errore_Anno();
                return;
            }

            try {
                Vino vino_modificato = new Vino(nome_nuovo, provenienza_nuovo, descrizione_nuovo, vitigno_nuovo, immagine, id_vino, soglia_nuovo, quantita_nuovo, anno_nuovo, CODProduttore);
                r = this.requestController.makeRequest(Costanti.Modifica_Dati_Vini_Prezzo, vino_modificato);

                if(r.getStatusCode() == Costanti.Successo)
                {
                    System.out.println("Vino modificato con successo");
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Vino modificato");
                    alert.setHeaderText("Vino modificato con successo");
                    alert.setContentText("Vino modificato con successo");
                    alert.showAndWait();
                } else {
                    System.out.println("Il vino non è stato modificato");
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Il vino non è stato modificato");
                    alert.setHeaderText("Si è verificato un'errore.");
                    alert.setContentText("La modifica del vino non è andata a buon fine");
                    alert.showAndWait();
                }
            } catch (Exception e)
            {
                System.out.println(e);
                throw new RuntimeException(e);
            }
        } else if (quantita_nuovo_stringa.isEmpty()) {
            int anno_nuovo = Integer.parseInt(anno_nuovo_stringa);
            double prezzo_nuovo = Double.parseDouble(prezzo_nuovo_stringa);
            int soglia_nuovo = Integer.parseInt(soglia_nuovo_stringa);

            if(anno_nuovo < 1900 || anno_nuovo > 2024)
            {
                Errore_Anno();
                return;
            }

            try {
                Vino vino_modificato = new Vino(nome_nuovo, provenienza_nuovo, descrizione_nuovo, vitigno_nuovo, immagine, prezzo_nuovo, id_vino, soglia_nuovo, anno_nuovo, CODProduttore);
                r = this.requestController.makeRequest(Costanti.Modifica_Dati_Vini_Quantita, vino_modificato);

                if(r.getStatusCode() == Costanti.Successo)
                {
                    System.out.println("Vino modificato con successo");
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Vino modificato");
                    alert.setHeaderText("Vino modificato con successo");
                    alert.setContentText("Vino modificato con successo");
                    alert.showAndWait();
                } else {
                    System.out.println("Il vino non è stato modificato");
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Il vino non è stato modificato");
                    alert.setHeaderText("Si è verificato un'errore.");
                    alert.setContentText("La modifica del vino non è andata a buon fine");
                    alert.showAndWait();
                }
            } catch (Exception e)
            {
                System.out.println(e);
                throw new RuntimeException(e);
            }
        } else if (soglia_nuovo_stringa.isEmpty()) {
            int anno_nuovo = Integer.parseInt(anno_nuovo_stringa);
            double prezzo_nuovo = Double.parseDouble(prezzo_nuovo_stringa);
            int quantita_nuovo = Integer.parseInt(quantita_nuovo_stringa);

            if(anno_nuovo < 1900 || anno_nuovo > 2024)
            {
                Errore_Anno();
                return;
            }

            try {
                Vino vino_modificato = new Vino(nome_nuovo, provenienza_nuovo, descrizione_nuovo, vitigno_nuovo, immagine, prezzo_nuovo, id_vino, quantita_nuovo, anno_nuovo, CODProduttore);
                r = this.requestController.makeRequest(Costanti.Modifica_Dati_Vini_Soglia, vino_modificato);

                if(r.getStatusCode() == Costanti.Successo)
                {
                    System.out.println("Vino modificato con successo");
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Vino modificato");
                    alert.setHeaderText("Vino modificato con successo");
                    alert.setContentText("Vino modificato con successo");
                    alert.showAndWait();
                } else {
                    System.out.println("Il vino non è stato modificato");
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Il vino non è stato modificato");
                    alert.setHeaderText("Si è verificato un'errore.");
                    alert.setContentText("La modifica del vino non è andata a buon fine");
                    alert.showAndWait();
                }
            } catch (Exception e)
            {
                System.out.println(e);
                throw new RuntimeException(e);
            }
        } else if((anno_nuovo_stringa.isEmpty()))
        {
            double prezzo_nuovo = Double.parseDouble(prezzo_nuovo_stringa);
            int soglia_nuovo = Integer.parseInt(soglia_nuovo_stringa);
            int quantita_nuovo = Integer.parseInt(quantita_nuovo_stringa);


            try {
                Vino vino_modificato = new Vino(nome_nuovo, provenienza_nuovo, descrizione_nuovo, vitigno_nuovo, immagine, prezzo_nuovo, id_vino, soglia_nuovo, quantita_nuovo, CODProduttore);
                r = this.requestController.makeRequest(Costanti.Modifica_Dati_Vini_Anno, vino_modificato);

                if(r.getStatusCode() == Costanti.Successo)
                {
                    System.out.println("Vino modificato con successo");
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Vino modificato");
                    alert.setHeaderText("Vino modificato con successo");
                    alert.setContentText("Vino modificato con successo");
                    alert.showAndWait();
                } else {
                    System.out.println("Il vino non è stato modificato");
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Il vino non è stato modificato");
                    alert.setHeaderText("Si è verificato un'errore.");
                    alert.setContentText("La modifica del vino non è andata a buon fine");
                    alert.showAndWait();
                }
            } catch (Exception e)
            {
                System.out.println(e);
                throw new RuntimeException(e);
            }
        } else {
            int anno_nuovo = Integer.parseInt(anno_nuovo_stringa);
            double prezzo_nuovo = Double.parseDouble(prezzo_nuovo_stringa);
            int soglia_nuovo = Integer.parseInt(soglia_nuovo_stringa);
            int quantita_nuovo = Integer.parseInt(quantita_nuovo_stringa);

            if(anno_nuovo < 1900 || anno_nuovo > 2024)
            {
                Errore_Anno();
                return;
            }

            try {
                Vino vino_modificato = new Vino(nome_nuovo, provenienza_nuovo, descrizione_nuovo, vitigno_nuovo, immagine, prezzo_nuovo, id_vino, soglia_nuovo, quantita_nuovo, anno_nuovo, CODProduttore);
                r = this.requestController.makeRequest(Costanti.Modifica_Dati_Vini, vino_modificato);

                if(r.getStatusCode() == Costanti.Successo)
                {
                    System.out.println("Vino modificato con successo");
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Vino modificato");
                    alert.setHeaderText("Vino modificato con successo");
                    alert.setContentText("Vino modificato con successo");
                    alert.showAndWait();
                } else {
                    System.out.println("Il vino non è stato modificato");
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Il vino non è stato modificato");
                    alert.setHeaderText("Si è verificato un'errore.");
                    alert.setContentText("La modifica del vino non è andata a buon fine");
                    alert.showAndWait();
                }
            } catch (Exception e)
            {
                System.out.println(e);
                throw new RuntimeException(e);
            }
        }
        txtNome_Modifica.clear();
        txtProvenienza_Modifica.clear();
        txtAnno_Modifica.clear();
        txtDescrizione_Modifica.clear();
        txtVitigno_Modifica.clear();
        txtPrezzo_Modifica.clear();
        txtSoglia_Modifica.clear();
        txtQuantita_Modifica.clear();
        imgBottglia_Modifica.setImage(null);
        lblNomeFile_Modifica.setText("");

        AnchorPane_Home.setVisible(true);
        AnchorPane_Ricerca.setVisible(false);
        AnchorPane_InsVino.setVisible(false);
        AnchorPane_Profilo.setVisible(false);
        AnchorPane_ModificaVino.setVisible(false);
    }

    @FXML
    void OnBtnDeleteVino_Click(ActionEvent event) {
        try {
            Response r = this.requestController.makeRequest(Costanti.Delete_Vino, new Vino(id_vino));

            if(r.getStatusCode() == Costanti.Successo){
                System.out.println("Vino eliminato con successo");
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Vino eliminato");
                alert.setHeaderText("Vino eliminato con successo");
                alert.setContentText("Vino eliminato con successo");
                alert.showAndWait();

                AnchorPane_Home.setVisible(true);
                AnchorPane_Ricerca.setVisible(false);
                AnchorPane_InsVino.setVisible(false);
                AnchorPane_Profilo.setVisible(false);
                AnchorPane_ModificaVino.setVisible(false);
            } else {
                System.out.println("Il vino non è stato eliminato");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Il vino non è stato eliminato");
                alert.setHeaderText("Si è verificato un'errore.");
                alert.setContentText("L'eliminazione del vino non è andata a buon fine");
                alert.showAndWait();
            }
        } catch (Exception e)
        {
            System.out.println(e);
            throw new RuntimeException(e);
        }
    }

    private void Errore_Anno() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Errore anno");
        alert.setHeaderText("Si è verificato un'errore.");
        alert.setContentText("L'annata del vino deve essere compresa tra il 1900 e il 2024(compresi)");
        alert.showAndWait();
    }

    private void Errore_Campi_Vuoti(String s) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Errore campi vuoti");
        alert.setHeaderText("Si è verificato un'errore.");
        alert.setContentText("Tutti i campi devono essere compilati! " + s);
        alert.showAndWait();

        txtNome_InsVino.clear();
        txtProvenienza_InsVino.clear();
        txtAnno_InsVino.clear();
        txtDescrizione_InsVino.clear();
        txtVitigno_InsVino.clear();
        txtPrezzo_InsVino.clear();
        txtSoglia_InsVino.clear();
        txtQuantita_InsVino.clear();
        lblNomeFile_InsVino.setText("");
        imgBottglia_InsVino.setImage(null);
    }

    private  void Errore_Interi()
    {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Errore valori");
        alert.setHeaderText("Si è verificato un'errore.");
        alert.setContentText("Il valore di prezzo, soglia e quantita non può essere minore o uguale a 0");
        alert.showAndWait();
    }
    public void initialize() {
    }
}
