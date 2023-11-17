package com.wineshop.client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Optional;

public class Client extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        try {
            Socket socket = null;
            int port = 8520;        //server port
            String ip = "localhost";

            if(getParameters().getRaw().size() == 2)
            {
                ip = getParameters().getNamed().get("ip");
                port = Integer.parseInt(getParameters().getNamed().get("port"));
            }

            System.out.println(ip + ":" + port);

            do {
                try {
                    System.out.print("\n1 - Cercando di connettersi al server...");
                    socket = new Socket(ip, port);
                } catch (Exception e) {
                    ButtonType btn = new ButtonType("Riprova");
                    Alert alert = new Alert(AlertType.ERROR, "Controlla la connessione su (" + ip + ":" + port + ") e riprova.\n" +
                            "Se il problema persiste probabilmente e' perche' il server e' spento!\nUtilizza un indirizzo IP e una porta personalizzati quando si chiama il programma con '--ip=yourip --port=yourport'", btn, ButtonType.CANCEL);
                    alert.setHeaderText("Indisponibilità di ricerca del server.");
                    alert.setTitle("Errore di connessione");
                    alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
                    Optional<ButtonType> result = alert.showAndWait();

                    if (result.get() == btn) {
                        System.out.println("\nRiprovando...");
                    } else {
                        System.out.print("Exit");
                        System.exit(0);
                    }
                }
            } while (socket == null);

            System.out.println("\n2 - Connesso!");

            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());

            FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));

            Parent root = loader.load();
            LoginController controller = loader.getController();
            RequestController requestController = new RequestController(out, in);
            controller.setRequestController(requestController);
            Scene scene = new Scene(root, 346, 600);

            String logoPath = "C:/Users/andre/OneDrive/Documenti/GitHub/WineShop/WineShop/src/main/resources/Design/Loghi/Logo_Calice.png";

            stage.getIcons().add(new Image(logoPath));

            final Socket fSock = socket;

            stage.setOnCloseRequest(event -> {          //gestisce la chiusura della finestra
                try {
                    requestController.closeConnection();
                    fSock.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

            stage.setTitle("Login");
            stage.setScene(scene);
            stage.setResizable(false);
            stage.centerOnScreen();
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}