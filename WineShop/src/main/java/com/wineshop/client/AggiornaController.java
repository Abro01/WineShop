package com.wineshop.client;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import utilities.models.Utente;
import utilities.Costanti;
import utilities.Response;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class AggiornaController {
    private RequestController requestController;
    private Utente UtenteLoggato;
    @FXML
    private Button CambioPass_Btn;

    @FXML
    private Button CambioPass_BtnIndietro;

    @FXML
    private PasswordField CambioPass_ConfPass;

    @FXML
    private CheckBox CambioPass_Mostra;

    @FXML
    private TextField CambioPass_MostraConfPass;

    @FXML
    private TextField CambioPass_MostraPassword;

    @FXML
    private PasswordField CambioPass_Password;

    @FXML
    private TextField CambioPass_Username;

    public void setRequestController(RequestController controller) {
        this.requestController = controller;
    }

    public void setLoggedUser(Utente utente) {
        this.UtenteLoggato = utente;
    }

    //button che permette di tornare al form di Login
    @FXML
    void OnCambioPass_BtnIndietroClick(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
        Parent RegGui = loader.load();
        Object controller = loader.getController();
        Scene scene = new Scene(RegGui, 346, 600);
        ((LoginController) controller).setRequestController(this.requestController);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }

    //button confronto password
    @FXML
    void OnCambioPass_MostraClick(ActionEvent event) {
        if (CambioPass_Mostra.isSelected()) {
            CambioPass_MostraPassword.setText(CambioPass_Password.getText());
            CambioPass_MostraPassword.setVisible(true);
            CambioPass_Password.setVisible(false);

            CambioPass_MostraConfPass.setText(CambioPass_ConfPass.getText());
            CambioPass_MostraConfPass.setVisible(true);
            CambioPass_ConfPass.setVisible(false);
        } else {
            CambioPass_MostraPassword.setText(CambioPass_Password.getText());
            CambioPass_MostraPassword.setVisible(false);
            CambioPass_Password.setVisible(true);

            CambioPass_MostraConfPass.setText(CambioPass_ConfPass.getText());
            CambioPass_MostraConfPass.setVisible(false);
            CambioPass_ConfPass.setVisible(true);
        }
    }

    //fuzione che controlla la password
    private void Errore_Password() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Errore password");
        alert.setHeaderText("Si è verificato un'errore.");
        alert.setContentText("Le password non coincidono");
        alert.showAndWait();
    }

    //funzione che controlla che tutti i campi siano stati compilati
    private void Errore_Campi_Vuoti(String s) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Errore campi vuoti");
        alert.setHeaderText("Si è verificato un'errore.");
        alert.setContentText("Tutti i campi devono essere compilati! " + s);
        alert.showAndWait();
    }

    //button cambio password
    @FXML
    void OnCambioPass_Click(ActionEvent event) throws Exception {
        String username = this.CambioPass_Username.getText();
        String password = this.CambioPass_Password.getText();
        String conf_password = this.CambioPass_ConfPass.getText();

        if(username.isEmpty() || password.isEmpty() || conf_password.isEmpty())
        {
            String s = "Il campo non può essere vuoto";
            Errore_Campi_Vuoti(s);
            CambioPass_Username.clear();
            CambioPass_Password.clear();
            CambioPass_ConfPass.clear();
            return;
        }

        if(!password.equals(conf_password))
        {
            Errore_Password();
            CambioPass_Password.clear();
            CambioPass_ConfPass.clear();
            return;
        }

        try {
            Utente u = new Utente(username, password);
            Response r = this.requestController.makeRequest(Costanti.Cambio_Pswd, u);

            if(r.getStatusCode() == Costanti.Successo)
            {
                System.out.println("Cambio password avvenuto con successo");
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Campio password eseguito");
                alert.setHeaderText("Cambio password avvenuto con successo");
                alert.setContentText("Cambio password avvenuto con successo");
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
                System.out.println("Cambio password fallito");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Cambio password fallito");
                alert.setHeaderText("Si è verificato un'errore.");
                alert.setContentText("Il cambio password non è andato a buon fine");
                alert.showAndWait();
            }
        } catch (Exception e)
        {
            System.out.println(e);
            throw new RuntimeException(e);
        }
    }

    public void initialize() {
    }
}
