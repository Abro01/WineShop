package com.wineshop.client;

import javafx.beans.property.*;
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
    private TableColumn<Vino, Integer> ColonnaAnno = new TableColumn<>();

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
    private ImageView imgBottglia_InsVino;
    public ArrayList<Vino> vino_produttore;
    private String immagine;

    public void setRequestController(RequestController controller) {
        this.requestController = controller;
    }

    public void setLoggedUser(Utente utente) {
        this.UtenteLoggato = utente;
    }

    @FXML
    void OnBtnCaricaImg_Click(ActionEvent event) {
        FileChooser img = new FileChooser();
        img.setTitle("Seleziona immagine");

        FileChooser.ExtensionFilter filtro = new FileChooser.ExtensionFilter("Immagini (*.png)", "*.png");
        img.getExtensionFilters().add(filtro);

        File FileSelezionato = img.showOpenDialog(new Stage());

        if(FileSelezionato != null)
        {
            System.out.println("File selezionato: " + FileSelezionato.getAbsolutePath());
            lblNomeFile_InsVino.setText(FileSelezionato.getAbsolutePath());
            immagine = FileSelezionato.getAbsolutePath();

            Image bottiglia = new Image(FileSelezionato.getAbsolutePath());
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

            ColonnaAnno.setCellValueFactory(cellData -> {
                int anno_vino = cellData.getValue().getAnno();
                IntegerProperty proprieta_anno_vino = new SimpleIntegerProperty(anno_vino);
                return Bindings.createObjectBinding(proprieta_anno_vino::get, proprieta_anno_vino);
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
                            AnchorPane_Home.setVisible(false);
                            AnchorPane_Ricerca.setVisible(false);
                            AnchorPane_InsVino.setVisible(false);
                            AnchorPane_Profilo.setVisible(false);
                            AnchorPane_ModificaVino.setVisible(true);
                        });
                        setGraphic(btnModifica);
                        setText(null);
                    }
                }
            });


            TV_Ricerca.setItems(vini_produttore);
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
        String nome = this.txtNome_InsVino.getText();
        String provenienza = this.txtProvenienza_InsVino.getText();
        int anno = Integer.parseInt(this.txtAnno_InsVino.getText());
        String descrizione = this.txtDescrizione_InsVino.getText();
        String vitigno = this.txtVitigno_InsVino.getText();
        double prezzo = Double.parseDouble(this.txtPrezzo_InsVino.getText());
        int soglia = Integer.parseInt(this.txtSoglia_InsVino.getText());
        int quantita = Integer.parseInt(this.txtQuantita_InsVino.getText());
        int CODProduttore = UtenteLoggato.getId();

        try
        {
            Vino VinoNuovo = new Vino(nome, CODProduttore, provenienza, descrizione, vitigno, immagine, prezzo, soglia, quantita, anno);
            System.out.println(immagine);
            Response r = this.requestController.makeRequest(Costanti.Add_Vino, VinoNuovo);

            if(r.getStatusCode() == Costanti.Successo)
            {
                System.out.println("Vino aggiunto con successo");
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Vino aggiunto");
                alert.setHeaderText("Vino aggiunto con successo");
                alert.setContentText("Vino aggiunto con successo");
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

    public void initialize() {
    }
}
