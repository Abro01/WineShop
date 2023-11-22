package com.wineshop.client;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import utilities.Costanti;
import utilities.Response;
import utilities.models.Utente;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.IOException;

public class RegistrazioneController implements Initializable {
    private RequestController requestController;
    @FXML
    private Button Reg_BtnLogin;
    @FXML
    private Button Reg_BtnReg;
    @FXML
    private TextField Reg_CF;
    @FXML
    private TextField Reg_Cog;
    @FXML
    private PasswordField Reg_ConfPass;
    @FXML
    private TextField Reg_Email;
    @FXML
    private TextField Reg_Ind;
    @FXML
    private CheckBox Reg_Mostra;
    @FXML
    private TextField Reg_MostraConfPass;
    @FXML
    private TextField Reg_MostraPass;
    @FXML
    private TextField Reg_Nome;
    @FXML
    private PasswordField Reg_Password;
    @FXML
    private ComboBox<String> Reg_SceltaTipo = new ComboBox<>();
    @FXML
    private TextField Reg_Tel;
    @FXML
    private TextField Reg_Username;

    public void setRequestController(RequestController controller) {
        this.requestController = controller;
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
    }

    //funzione che verifica la corretta forma della mail
    private void Errore_Email() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Errore email");
        alert.setHeaderText("Si è verificato un'errore.");
        alert.setContentText("La mail deve contenere una @ e non può essere vuota.");
        alert.showAndWait();
    }

    //fuzione che controlla la password
    private void Errore_Password() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Errore password");
        alert.setHeaderText("Si è verificato un'errore.");
        alert.setContentText("Le password non coincidono");
        alert.showAndWait();
    }

    @FXML
    void OnReg_BtnLoginClick(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
        Parent LogGui = loader.load();
        Object controller = loader.getController();
        Scene scene = new Scene(LogGui, 346, 600);
        ((LoginController) controller).setRequestController(this.requestController);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }

    @FXML
    void OnReg_BtnRegClick(ActionEvent event) throws Exception {
        String nome = this.Reg_Nome.getText();
        String cognome = this.Reg_Cog.getText();
        String indirizzo = this.Reg_Ind.getText();
        String telefono = this.Reg_Tel.getText();
        String cf = this.Reg_CF.getText();
        String email = this.Reg_Email.getText();
        String tipo = this.Reg_SceltaTipo.getSelectionModel().getSelectedItem().toString();
        String username = this.Reg_Username.getText();
        String password = this.Reg_Password.getText();
        String cof_password = this.Reg_ConfPass.getText();

        if(username.isEmpty() || nome.isEmpty() || cognome.isEmpty() || indirizzo.isEmpty() || telefono.isEmpty() || cf.isEmpty() || email.isEmpty() || tipo.isEmpty() || password.isEmpty() || cof_password.isEmpty())
        {
            String s = "Il campo non può essere vuoto";
            Errore_Campi_Vuoti(s);
            Reg_Nome.clear();
            Reg_Cog.clear();
            Reg_Ind.clear();
            Reg_Tel.clear();
            Reg_CF.clear();
            Reg_Email.clear();
            Reg_Username.clear();
            Reg_Password.clear();
            Reg_ConfPass.clear();
            return;
        }

        if(!Reg_Email.getText().contains("@") || email.isEmpty())
        {
            Errore_Email();
            Reg_Email.clear();
            return;
        }

        if(!CF_Check(cf))
        {
            String s = "Reinserire il codice fiscale perche' non sono stati rispettati i criteri necessari";
            Errore_Campi_Vuoti(s);
            Reg_CF.clear();
            return;
        }

        if(!password.equals(cof_password))
        {
            Errore_Password();
            Reg_Password.clear();
            Reg_ConfPass.clear();
            return;
        }

        try {
            Utente u = new Utente(nome, cognome, cf, email, telefono, indirizzo, password, tipo, username);
            Response r = this.requestController.makeRequest(Costanti.Registrazione, u);

            if(r.getStatusCode() == Costanti.Successo)
            {
                System.out.println("Registrazione avvenuta con successo");
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Registrazione eseguita");
                alert.setHeaderText("Registrazione avvenuta con successo");
                alert.setContentText("Registrazione avvenuta con successo");
                alert.showAndWait();

                FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
                Parent LogGui = loader.load();
                Object controller = loader.getController();
                Scene scene = new Scene(LogGui, 346, 600);
                ((LoginController) controller).setRequestController(this.requestController);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.centerOnScreen();
                stage.show();
            } else {
                System.out.println("Registrazione fallita");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Registrazione fallita");
                alert.setHeaderText("Si è verificato un'errore.");
                alert.setContentText("La registrazione non è andata a buon fine");
                alert.showAndWait();
            }
        } catch (Exception e)
        {
            System.out.println(e);
            throw new RuntimeException(e);
        }
    }

    @FXML
    void OnReg_MostraClick(ActionEvent event) {
        if (Reg_Mostra.isSelected()) {
            Reg_MostraPass.setText(Reg_Password.getText());
            Reg_MostraPass.setVisible(true);
            Reg_Password.setVisible(false);

            Reg_MostraConfPass.setText(Reg_ConfPass.getText());
            Reg_MostraConfPass.setVisible(true);
            Reg_ConfPass.setVisible(false);
        } else {
            Reg_MostraPass.setText(Reg_Password.getText());
            Reg_MostraPass.setVisible(false);
            Reg_Password.setVisible(true);

            Reg_MostraConfPass.setText(Reg_ConfPass.getText());
            Reg_MostraConfPass.setVisible(false);
            Reg_ConfPass.setVisible(true);
        }
    }

    //posizione utilizzata per risolvere percorsi relativi per l'oggetto radice.
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> options = FXCollections.observableArrayList("Amministratore", "Cliente", "Impiegato", "Produttore");
        Reg_SceltaTipo.setItems(options);
    }

}
