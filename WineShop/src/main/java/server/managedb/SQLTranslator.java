package server.managedb;

import utilities.*;
import utilities.models.*;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SQLTranslator {
    private int lastRequest;
    private final DateTimeFormatter dateFormatter;
    private final DateTimeFormatter dateTimeFormatter;
    private Utente UtenteLoggato;

    public SQLTranslator() {
        this.lastRequest = -1;
        this.dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    }

    //consente la traduzione da una richiesta del client a un'istruzione SQL
    public  String requestToSQL(Request request) throws RequestToSQLException, IOException {
        String query = "";
        Object model = request.getPayload();

        this.lastRequest = request.getValue();

        switch (request.getValue())
        {
            case Costanti.Login ->
            {
                query += "SELECT * " +
                         "FROM utenti " +
                         "WHERE Username = '" + ((Utente) model).getUsername() + "' AND Password = '" + ((Utente) model).getPassword() + "'; " +
                         "UPDATE utenti SET Online = 1 WHERE Username = '" + ((Utente) model).getUsername() + "';";
            }
            case Costanti.Registrazione ->
            {
                Insertable Nuovo_Utente = (Insertable) model;
                query += "INSERT INTO utenti (ID,CF,Nome,Cognome,Username,Password,Email,Telefono,Indirizzo,Tipo) " +
                         "VALUES (" + ((Utente) model).getId() + ",'" + ((Utente) model).getCf()+ "','" + ((Utente) model).getNome() + "','" + ((Utente) model).getCognome() + "','" + ((Utente) model).getUsername() + "','" + ((Utente) model).getPassword() + "','" + ((Utente) model).getEmail() + "','" + ((Utente) model).getTelefono() + "','" + ((Utente) model).getIndirizzo() + "','" + ((Utente) model).getTipo() + "');";
            }
            case Costanti.Cambio_Pswd ->
            {
                query += "UPDATE utenti " +
                         "SET Password = '" + ((Utente) model).getPassword() + "' " +
                         "WHERE Username = '" + ((Utente) model).getUsername() + "';";
            }
            case Costanti.Logout ->
            {
                query += "UPDATE utenti " +
                         "SET LastLogin = '" + LocalDateTime.now().format(dateTimeFormatter) + "' " +
                         "WHERE Username = '" + ((Utente) model).getUsername() + "'; " +
                         "UPDATE utenti " +
                         "SET Online = 0 " +
                         "WHERE Username = '" + ((Utente) model).getUsername() + "'; ";
            }
            default -> throw new RequestToSQLException();
        }
        return query;
    }

    //consente la traduzione da un risultato SQL a una {@code Response}
    public Response sqlToResponse(List<Map<String, String>> queryResult) throws SQLToResponseException {
        Response response = null;

        switch (this.lastRequest){
            case Costanti.Login:
                if(queryResult.isEmpty())
                {
                    response = new Response(Costanti.Bad_Request, new EmptyPayload("Login errato!"));
                    break;
                }
                Map<String, String> uLog = queryResult.get(0);
                Utente LogUtente = new Utente(uLog.get("Nome"), uLog.get("Cognome"), uLog.get("CF"), uLog.get("Email"), uLog.get("Telefono"), uLog.get("Indirizzo"), uLog.get("Password"), uLog.get("Tipo"), uLog.get("Username"), Integer.parseInt(uLog.get("ID")));
                //System.out.println("id: " + utente.getId());
                response = new Response(Costanti.Successo, LogUtente);
                this.UtenteLoggato = LogUtente;
                break;
            case Costanti.Registrazione, Costanti.Cambio_Pswd:
                response = new Response(Costanti.Successo, new EmptyPayload());
                break;
            case Costanti.Logout:
                response = new Response(Costanti.Successo, new EmptyPayload("Logout effettuato con successo"));
                this.UtenteLoggato = null;
                break;
            default:
                throw new SQLToResponseException();
        }
        return response;
    }
}
