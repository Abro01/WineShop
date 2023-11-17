package server.managedb;

import utilities.*;
import utilities.models.*;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
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
                Map<String, String> uRes = queryResult.get(0);
                Utente utente = new Utente(Integer.parseInt(uRes.get("ID")), uRes.get("Username"), uRes.get("Password"), uRes.get("Tipo"));
                System.out.println("id: " + utente.getId());
                response = new Response(Costanti.Successo, utente);
                this.UtenteLoggato = utente;
                break;
            default:
                throw new SQLToResponseException();
        }
        return response;
    }
}
