module com.wineshop.wineshop {
    requires javafx.controls;
    requires javafx.fxml;

    requires mysql.connector.j;
    requires java.sql;
    requires com.google.gson;
    requires javafx.base;
    requires java.mail;


    opens com.wineshop.client to javafx.fxml;
    exports com.wineshop.client;
}