package com.wineshop.client;

import utilities.Costanti;
import utilities.Response;
import utilities.models.Utente;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class LoginController {
    private RequestController requestController;
    private Utente UtenteLoggato;

    @FXML
    private Button Login_BtnLogin;

    @FXML
    private Button Login_BtnReg;

    @FXML
    private Hyperlink Login_Link;

    @FXML
    private CheckBox Login_Mostra;

    @FXML
    private TextField Login_MostraPassword;

    @FXML
    private PasswordField Login_Password;

    @FXML
    private TextField Login_Username;

    @FXML
    private Label lblLogin;

    public void setRequestController(RequestController controller) {
        this.requestController = controller;
    }

    public void setUtenteLoggato(Utente utente) {
        this.UtenteLoggato = utente;
    }

    @FXML
    void OnLogin_Login_BtnLogin(ActionEvent event) throws Exception {
        String username = this.Login_Username.getText(); //ok
        String password = this.Login_Password.getText();//ok
        this.lblLogin.setText("");

        Response r = this.requestController.makeRequest(Costanti.Login, new Utente(username, password));

        System.out.println("Valore r: " + r);

        if (r.getStatusCode() == Costanti.Successo)
        {
            System.out.println("Valore status code: " + r.getStatusCode());
            System.out.println("Login avvenuto con successo!");
            UtenteLoggato = (Utente) r.getPayload();                //assegna le credenziali agli utenti
        } else {
            System.out.println("Login fallito");
            this.lblLogin.setText("Login fallito");
            Login_Username.setText("");
            Login_Password.setText("");
        }

        //controllo della tipologia di utente così da indirizzarlo nella sezione corretta
        if (r.getStatusCode() == Costanti.Successo) {
            String gui = "";
            UtenteLoggato = (Utente) r.getPayload();

            // check which gui to show
            if (Objects.equals(UtenteLoggato.getTipo(), "Cliente"))
            {
                gui = "HomeCliente.fxml";
            } else if (Objects.equals(UtenteLoggato.getTipo(), "Amministratore"))
            {
                gui = "HomeAmministratore.fxml";
            } else if (Objects.equals(UtenteLoggato.getTipo(), "Impiegato"))
            {
                gui = "HomeImpiegato.fxml";
            } else if (Objects.equals(UtenteLoggato.getTipo(), "Produttore"))
            {
                gui = "HomeProduttore.fxml";
            }

            FXMLLoader loader = new FXMLLoader(getClass().getResource(gui));
            Parent guiToShow = loader.load();
            Object controller = loader.getController();
            Scene scene = new Scene(guiToShow, 850, 500);
            // Collega il file CSS alla scena
            //scene.getStylesheets().add(getClass().getResource("C:/Users/andre/OneDrive/Documenti/GitHub/WineShop/WineShop/src/main/resources/Design/Design.css").toExternalForm());
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // set the request controller and the logged user
            if (Objects.equals(UtenteLoggato.getTipo(), "Cliente"))
            {
                // caso cliente
                ((HomeClienteController) controller).setRequestController(this.requestController);
                ((HomeClienteController) controller).setLoggedUser(UtenteLoggato);
            } else if (Objects.equals(UtenteLoggato.getTipo(), "Amministratore")) {
                // caso amministratore
                ((HomeAmministratoreController) controller).setRequestController(this.requestController);
                ((HomeAmministratoreController) controller).setLoggedUser(UtenteLoggato);
            } else if (Objects.equals(UtenteLoggato.getTipo(), "Impiegato")) {
                // caso impiegato
                ((HomeImpiegatoController) controller).setRequestController(this.requestController);
                ((HomeImpiegatoController) controller).setLoggedUser(UtenteLoggato);
            } else if (Objects.equals(UtenteLoggato.getTipo(), "Produttore")) {
                // caso produttore
                ((HomeProduttoreController) controller).setRequestController(this.requestController);
                ((HomeProduttoreController) controller).setLoggedUser(UtenteLoggato);
            }
            stage.setTitle(UtenteLoggato.getUsername().toUpperCase() + " - " + UtenteLoggato.getTipo().toUpperCase() + " - WineDemo");
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.show();

            if (UtenteLoggato.getTipo().equals("Cliente"))
            {
                ((HomeClienteController) controller).initialize();
            } else if (UtenteLoggato.getTipo().equals("Amministratore"))
            {
                ((HomeAmministratoreController) controller).initialize();
            }
            else if (UtenteLoggato.getTipo().equals("Impiegato"))
            {
                ((HomeImpiegatoController) controller).initialize();
            }
            else if (UtenteLoggato.getTipo().equals("Produttore"))
            {
                ((HomeProduttoreController) controller).initialize();
            }
        } else if (r.getStatusCode() == Costanti.Bad_Request) {
            this.lblLogin.setText("Username o password errati!");
        } else if (r.getStatusCode() == Costanti.Internal_Server_Error) {
            this.lblLogin.setText("Errore del server!");
        }
    }

    @FXML
    void OnLogin_MostraClick(ActionEvent event) {
        if (Login_Mostra.isSelected()) {
            Login_MostraPassword.setText(Login_Password.getText());
            Login_MostraPassword.setVisible(true);
            Login_Password.setVisible(false);
        } else {
            Login_MostraPassword.setText(Login_Password.getText());
            Login_MostraPassword.setVisible(false);
            Login_Password.setVisible(true);
        }
    }

    @FXML
    void OnLogin_BtnRegClick(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Registrazione.fxml"));
        Parent RegGui = loader.load();
        Object controller = loader.getController();
        Scene scene = new Scene(RegGui, 346, 600);
        ((RegistrazioneController) controller).setRequestController(this.requestController);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }

    @FXML
    void OnLogin_LinkClick(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AggiornaPassword.fxml"));
        Parent AggPassGui = loader.load();
        Object controller = loader.getController();
        Scene scene = new Scene(AggPassGui, 346, 600);
        ((AggiornaController) controller).setRequestController(this.requestController);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }
}