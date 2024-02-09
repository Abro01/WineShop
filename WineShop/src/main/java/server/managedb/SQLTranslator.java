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
                         "WHERE Username = '" + ((Utente) model).getUsername() + "' AND Password = MD5('" + ((Utente) model).getPassword() + "'); " +
                         "UPDATE utenti SET Online = 1 WHERE Username = '" + ((Utente) model).getUsername() + "';";
            }
            case Costanti.Registrazione ->
            {
                Insertable Nuovo_Utente = (Insertable) model;
                query += "INSERT INTO utenti (ID,CF,Nome,Cognome,Username,Password,Email,Telefono,Indirizzo,Tipo) " +
                         "VALUES (" + ((Utente) model).getId() + ",'" + ((Utente) model).getCf()+ "','" + ((Utente) model).getNome() + "','" + ((Utente) model).getCognome() + "','" + ((Utente) model).getUsername() + "',MD5('" + ((Utente) model).getPassword() + "'),'" + ((Utente) model).getEmail() + "','" + ((Utente) model).getTelefono() + "','" + ((Utente) model).getIndirizzo() + "','" + ((Utente) model).getTipo() + "');";
            }
            case Costanti.Cambio_Pswd ->
            {
                query += "UPDATE utenti " +
                         "SET Password = MD5('" + ((Utente) model).getPassword() + "') " +
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
            case Costanti.Add_Vino ->
            {
                Insertable Nuovo_Vino = (Insertable) model;
                query += "INSERT INTO vini (ID,Nome,Provenienza,Anno,Descrizione,Vitigno,Prezzo,Soglia,Quantita,Immagine,CODProduttore) " +
                         "VALUES (" + ((Vino) model).getID() + ",'" + ((Vino) model).getNome() + "','" + ((Vino) model).getProvenienza() + "'," + ((Vino) model).getAnno() + ",'" + ((Vino) model).getDescrizione() + "','" + ((Vino) model).getVitigno() + "'," + ((Vino) model).getPrezzo() + "," + ((Vino) model).getSoglia() + "," + ((Vino) model).getQuantita() + ",'" + ((Vino) model).getImmagine() + "'," + ((Vino) model).getCODProduttore() + ");";
            }
            case Costanti.Mostra_Vini_Produttore ->
            {
                query += "SELECT v.* " +
                         "FROM vini v, utenti u " +
                         "WHERE u.ID = v.CODProduttore " +
                         "AND u.ID = " + UtenteLoggato.getId() + ";";
            }
            case Costanti.Ricerca_Vini_Produttore ->
            {
                Insertable Vino_Cercato = (Insertable) model;
                query += "SELECT v.* " +
                         "FROM vini v, utenti u " +
                         "WHERE u.ID = v.CODProduttore " +
                         "AND u.ID = " + UtenteLoggato.getId() + " " +
                         "AND v.Nome = '" + ((Vino) model).getNome() + "';";
            }
            case Costanti.Modifica_Dati_Vini ->
            {
                Insertable Vino_Modificato = (Insertable) model;
                StringBuilder queryBuilder = new StringBuilder("UPDATE vini SET");

                //controllo se i textField sono compilati e modifica i dato solo dove sono stati compilati
                if(!((Vino) model).getNome().isEmpty())
                {
                    queryBuilder.append(" Nome = '").append(((Vino) model).getNome()).append("',");
                }
                if(!((Vino) model).getProvenienza().isEmpty())
                {
                    queryBuilder.append(" Provenienza = '").append(((Vino) model).getProvenienza()).append("',");
                }
                if(((Vino) model).getAnno() >= 1900)
                {
                    queryBuilder.append(" Anno = ").append(((Vino) model).getAnno()).append(",");
                }
                if(!((Vino) model).getDescrizione().isEmpty())
                {
                    queryBuilder.append(" Descrizione = '").append(((Vino) model).getDescrizione()).append("',");
                }
                if(!((Vino) model).getVitigno().isEmpty())
                {
                    queryBuilder.append(" Vitigno = '").append(((Vino) model).getVitigno()).append("',");
                }
                if(((Vino) model).getPrezzo() > 0)
                {
                    queryBuilder.append(" Prezzo = ").append(((Vino) model).getPrezzo()).append(",");
                }
                if(((Vino) model).getSoglia() > 0)
                {
                    queryBuilder.append(" Soglia = ").append(((Vino) model).getSoglia()).append(",");
                }
                if(((Vino) model).getQuantita() > 0)
                {
                    queryBuilder.append(" Quantita = ").append(((Vino) model).getQuantita()).append(",");
                }
                if(!((Vino) model).getImmagine().isEmpty())
                {
                    queryBuilder.append(" Immagine = '").append(((Vino) model).getImmagine()).append("',");
                }
                //Rimuove l'eventuale ultima virgola
                queryBuilder.deleteCharAt(queryBuilder.length() - 1);

                queryBuilder.append(" WHERE ID = ").append(((Vino) model).getID()).append(";");

                query += queryBuilder.toString();
            }
            case Costanti.Modifica_Dati_Vini_Anno ->
            {
                Insertable Vino_Modificato = (Insertable) model;
                StringBuilder queryBuilder = new StringBuilder("UPDATE vini SET");

                //controllo se i textField sono compilati e modifica i dato solo dove sono stati compilati
                if(!((Vino) model).getNome().isEmpty())
                {
                    queryBuilder.append(" Nome = '").append(((Vino) model).getNome()).append("',");
                }
                if(!((Vino) model).getProvenienza().isEmpty())
                {
                    queryBuilder.append(" Provenienza = '").append(((Vino) model).getProvenienza()).append("',");
                }
                if(!((Vino) model).getDescrizione().isEmpty())
                {
                    queryBuilder.append(" Descrizione = '").append(((Vino) model).getDescrizione()).append("',");
                }
                if(!((Vino) model).getVitigno().isEmpty())
                {
                    queryBuilder.append(" Vitigno = '").append(((Vino) model).getVitigno()).append("',");
                }
                if(((Vino) model).getPrezzo() > 0)
                {
                    queryBuilder.append(" Prezzo = ").append(((Vino) model).getPrezzo()).append(",");
                }
                if(((Vino) model).getSoglia() > 0)
                {
                    queryBuilder.append(" Soglia = ").append(((Vino) model).getSoglia()).append(",");
                }
                if(((Vino) model).getQuantita() > 0)
                {
                    queryBuilder.append(" Quantita = ").append(((Vino) model).getQuantita()).append(",");
                }
                if(!((Vino) model).getImmagine().isEmpty())
                {
                    queryBuilder.append(" Immagine = '").append(((Vino) model).getImmagine()).append("',");
                }
                //Rimuove l'eventuale ultima virgola
                queryBuilder.deleteCharAt(queryBuilder.length() - 1);

                queryBuilder.append(" WHERE ID = ").append(((Vino) model).getID()).append(";");

                query += queryBuilder.toString();
            }
            case Costanti.Modifica_Dati_Vini_Prezzo ->
            {
                Insertable Vino_Modificato = (Insertable) model;
                StringBuilder queryBuilder = new StringBuilder("UPDATE vini SET");

                //controllo se i textField sono compilati e modifica i dato solo dove sono stati compilati
                if(!((Vino) model).getNome().isEmpty())
                {
                    queryBuilder.append(" Nome = '").append(((Vino) model).getNome()).append("',");
                }
                if(!((Vino) model).getProvenienza().isEmpty())
                {
                    queryBuilder.append(" Provenienza = '").append(((Vino) model).getProvenienza()).append("',");
                }
                if(((Vino) model).getAnno() >= 1900)
                {
                    queryBuilder.append(" Anno = ").append(((Vino) model).getAnno()).append(",");
                }
                if(!((Vino) model).getDescrizione().isEmpty())
                {
                    queryBuilder.append(" Descrizione = '").append(((Vino) model).getDescrizione()).append("',");
                }
                if(!((Vino) model).getVitigno().isEmpty())
                {
                    queryBuilder.append(" Vitigno = '").append(((Vino) model).getVitigno()).append("',");
                }
                if(((Vino) model).getSoglia() > 0)
                {
                    queryBuilder.append(" Soglia = ").append(((Vino) model).getSoglia()).append(",");
                }
                if(((Vino) model).getQuantita() > 0)
                {
                    queryBuilder.append(" Quantita = ").append(((Vino) model).getQuantita()).append(",");
                }
                if(!((Vino) model).getImmagine().isEmpty())
                {
                    queryBuilder.append(" Immagine = '").append(((Vino) model).getImmagine()).append("',");
                }
                //Rimuove l'eventuale ultima virgola
                queryBuilder.deleteCharAt(queryBuilder.length() - 1);

                queryBuilder.append(" WHERE ID = ").append(((Vino) model).getID()).append(";");

                query += queryBuilder.toString();
            }
            case Costanti.Modifica_Dati_Vini_Quantita ->
            {
                Insertable Vino_Modificato = (Insertable) model;
                StringBuilder queryBuilder = new StringBuilder("UPDATE vini SET");

                //controllo se i textField sono compilati e modifica i dato solo dove sono stati compilati
                if(!((Vino) model).getNome().isEmpty())
                {
                    queryBuilder.append(" Nome = '").append(((Vino) model).getNome()).append("',");
                }
                if(!((Vino) model).getProvenienza().isEmpty())
                {
                    queryBuilder.append(" Provenienza = '").append(((Vino) model).getProvenienza()).append("',");
                }
                if(((Vino) model).getAnno() >= 1900)
                {
                    queryBuilder.append(" Anno = ").append(((Vino) model).getAnno()).append(",");
                }
                if(!((Vino) model).getDescrizione().isEmpty())
                {
                    queryBuilder.append(" Descrizione = '").append(((Vino) model).getDescrizione()).append("',");
                }
                if(!((Vino) model).getVitigno().isEmpty())
                {
                    queryBuilder.append(" Vitigno = '").append(((Vino) model).getVitigno()).append("',");
                }
                if(((Vino) model).getPrezzo() > 0)
                {
                    queryBuilder.append(" Prezzo = ").append(((Vino) model).getPrezzo()).append(",");
                }
                if(((Vino) model).getSoglia() > 0)
                {
                    queryBuilder.append(" Soglia = ").append(((Vino) model).getSoglia()).append(",");
                }
                if(!((Vino) model).getImmagine().isEmpty())
                {
                    queryBuilder.append(" Immagine = '").append(((Vino) model).getImmagine()).append("',");
                }
                //Rimuove l'eventuale ultima virgola
                queryBuilder.deleteCharAt(queryBuilder.length() - 1);

                queryBuilder.append(" WHERE ID = ").append(((Vino) model).getID()).append(";");

                query += queryBuilder.toString();
            }
            case Costanti.Modifica_Dati_Vini_Soglia ->
            {
                Insertable Vino_Modificato = (Insertable) model;
                StringBuilder queryBuilder = new StringBuilder("UPDATE vini SET");

                //controllo se i textField sono compilati e modifica i dato solo dove sono stati compilati
                if(!((Vino) model).getNome().isEmpty())
                {
                    queryBuilder.append(" Nome = '").append(((Vino) model).getNome()).append("',");
                }
                if(!((Vino) model).getProvenienza().isEmpty())
                {
                    queryBuilder.append(" Provenienza = '").append(((Vino) model).getProvenienza()).append("',");
                }
                if(((Vino) model).getAnno() >= 1900)
                {
                    queryBuilder.append(" Anno = ").append(((Vino) model).getAnno()).append(",");
                }
                if(!((Vino) model).getDescrizione().isEmpty())
                {
                    queryBuilder.append(" Descrizione = '").append(((Vino) model).getDescrizione()).append("',");
                }
                if(!((Vino) model).getVitigno().isEmpty())
                {
                    queryBuilder.append(" Vitigno = '").append(((Vino) model).getVitigno()).append("',");
                }
                if(((Vino) model).getPrezzo() > 0)
                {
                    queryBuilder.append(" Prezzo = ").append(((Vino) model).getPrezzo()).append(",");
                }
                if(((Vino) model).getQuantita() > 0)
                {
                    queryBuilder.append(" Quantita = ").append(((Vino) model).getQuantita()).append(",");
                }
                if(!((Vino) model).getImmagine().isEmpty())
                {
                    queryBuilder.append(" Immagine = '").append(((Vino) model).getImmagine()).append("',");
                }
                //Rimuove l'eventuale ultima virgola
                queryBuilder.deleteCharAt(queryBuilder.length() - 1);

                queryBuilder.append(" WHERE ID = ").append(((Vino) model).getID()).append(";");

                query += queryBuilder.toString();
            }
            case Costanti.Modifica_Dati_Vini_Tutti ->
            {
                Insertable Vino_Modificato = (Insertable) model;
                StringBuilder queryBuilder = new StringBuilder("UPDATE vini SET");

                //controllo se i textField sono compilati e modifica i dato solo dove sono stati compilati
                if(!((Vino) model).getNome().isEmpty())
                {
                    queryBuilder.append(" Nome = '").append(((Vino) model).getNome()).append("',");
                }
                if(!((Vino) model).getProvenienza().isEmpty())
                {
                    queryBuilder.append(" Provenienza = '").append(((Vino) model).getProvenienza()).append("',");
                }
                if(!((Vino) model).getDescrizione().isEmpty())
                {
                    queryBuilder.append(" Descrizione = '").append(((Vino) model).getDescrizione()).append("',");
                }
                if(!((Vino) model).getVitigno().isEmpty())
                {
                    queryBuilder.append(" Vitigno = '").append(((Vino) model).getVitigno()).append("',");
                }
                if(!((Vino) model).getImmagine().isEmpty())
                {
                    queryBuilder.append(" Immagine = '").append(((Vino) model).getImmagine()).append("',");
                }
                //Rimuove l'eventuale ultima virgola
                queryBuilder.deleteCharAt(queryBuilder.length() - 1);

                queryBuilder.append(" WHERE ID = ").append(((Vino) model).getID()).append(";");

                query += queryBuilder.toString();
            }
            case Costanti.Delete_Vino ->
            {
                query += "DELETE FROM vini " +
                         "WHERE ID = " + ((Vino) model).getID() + ";";
            }
            case Costanti.Registrazione_Impiegati ->
            {
                Insertable Nuovo_Utente = (Insertable) model;
                query += "INSERT INTO utenti (ID,CF,Nome,Cognome,Username,Password,Email,Telefono,Indirizzo,Tipo) " +
                         "VALUES (" + ((Utente) model).getId() + ",'" + ((Utente) model).getCf() + "','" + ((Utente) model).getNome() + "','" + ((Utente) model).getCognome() + "','" + ((Utente) model).getUsername() + "',MD5('" + ((Utente) model).getPassword() + "'),'" + ((Utente) model).getEmail() + "','" + ((Utente) model).getTelefono() + "','" + ((Utente) model).getIndirizzo() + "','Impiegato');";
            }
            case Costanti.Add_Report ->
            {
                Insertable Nuovo_Report = (Insertable) model;
                query += "INSERT INTO report (ID,Descrizione,Data,CODAmministratore) " +
                         "VALUES (" + ((Report) model).getID() + ",'" + ((Report) model).getDescrizione() + "','" + ((Report) model).getData() + "'," + ((Report) model).getCODAmministratore() + ");";
            }
            case Costanti.Mostra_Vini ->
            {
                query += "SELECT * " +
                         "FROM vini;";
            }
            case Costanti.Mostra_Clienti ->
            {
                query += "SELECT * " +
                         "FROM utenti " +
                         "WHERE Tipo = 'Cliente';";
            }
            case Costanti.Mostra_Offerte ->
            {
                query += "SELECT o.*, v.Nome " +
                         "FROM offerte o, vini v " +
                         "WHERE v.ID = o.CODVino;";
            }
            case Costanti.Mostra_Impiegati ->
            {
                query += "SELECT * " +
                         "FROM utenti " +
                         "WHERE Tipo = 'Impiegato';";
            }
            case Costanti.Mostra_Ordini ->
            {
                query += "SELECT o.* " +
                         "FROM ordini o, utenti u " +
                         "WHERE u.ID = o.CODCliente;";
            }
            case Costanti.Ricerca_Vini ->
            {
                Insertable Vino_Cercato = (Insertable) model;
                query += "SELECT v.* " +
                         "FROM vini v " +
                         "WHERE v.Nome = '" + ((Vino) model).getNome() + "' " +
                         "OR v.Anno = " + ((Vino) model).getAnno() + ";";
            }
            case Costanti.Ricerca_Clienti ->
            {
                Insertable Cliente_Cercato = (Insertable) model;
                query += "SELECT u.* " +
                         "FROM utenti u " +
                         "WHERE u.Cognome = '" + ((Utente) model).getCognome() + "' " +
                         "AND u.Tipo = 'Cliente';";
            }
            case Costanti.Ricerca_Impiegati ->
            {
                Insertable Impiegato_Cercato = (Insertable) model;
                query += "SELECT u.* " +
                         "FROM utenti u " +
                         "WHERE u.Username = '" + ((Utente) model).getUsername() + "' " +
                         "AND u.Tipo = 'Impiegato';";
            }
            case Costanti.Ricerca_Offerte ->
            {
                Insertable Offerta_Cercato = (Insertable) model;
                query += "SELECT o.* " +
                         "FROM offerte o " +
                         "WHERE o.ID = " + ((Offerta) model).getID() + ";";
            }
            case Costanti.Ricerca_Ordini ->
            {
                Insertable Ordini_Cercato = (Insertable) model;
                query += "SELECT o.* " +
                         "FROM ordini o " +
                         "WHERE o.Stato = '" + ((Ordine) model).getStato() + "';";
            }
            case Costanti.Elimina_Impiegato ->
            {
                query += "DELETE FROM utenti " +
                         "WHERE ID = " + ((Utente) model).getId() + ";";
            }
            case Costanti.Elimina_Offerta ->
            {
                query += "DELETE FROM offerte " +
                         "WHERE ID = " + ((Offerta) model).getID() + ";";
            }
            case Costanti.Mostra_Dettagli_Ordine ->
            {
                query += "SELECT d.*, v.Nome " +
                         "FROM dettagli_ordini d, vini v " +
                         "WHERE v.ID = d.CODVino " +
                         "AND CODOrdine = " + ((DettagliOrdine) model).getCODOrdine() + ";";
            }
            case Costanti.Add_Offerta ->
            {
                Insertable Nuova_Offerta = (Insertable) model;
                query += "INSERT INTO offerte (ID, Descrizione, Sconto, CODVino) " +
                         "VALUES (" + ((Offerta) model).getID() + ",'" + ((Offerta) model).getDescrizione() + "'," + ((Offerta) model).getSconto() + "," + ((Offerta) model).getCODVino() + ");";
            }
            case Costanti.Modifica_Dati_Offerta ->
            {
                Insertable Offerta_Modificata = (Insertable) model;
                StringBuilder queryBuilder = new StringBuilder("UPDATE offerte SET");

                //controllo se i textField sono compilati e modifica i dato solo dove sono stati compilati
                if(((Offerta) model).getSconto() > 0)
                {
                    queryBuilder.append(" Sconto = ").append(((Offerta) model).getSconto()).append(",");
                }
                if(!((Offerta) model).getDescrizione().isEmpty())
                {
                    queryBuilder.append(" Descrizione = '").append(((Offerta) model).getDescrizione()).append("',");
                }
                //Rimuove l'eventuale ultima virgola
                queryBuilder.deleteCharAt(queryBuilder.length() - 1);

                queryBuilder.append(" WHERE ID = ").append(((Offerta) model).getID());
                queryBuilder.append(" AND CODVino = ").append(((Offerta) model).getCODVino()).append(";");

                query += queryBuilder.toString();
            }
            case Costanti.Modifica_Dati_Offerta_Sconto ->
            {
                Insertable Offerta_Modificata = (Insertable) model;
                StringBuilder queryBuilder = new StringBuilder("UPDATE offerte SET");

                //controllo se i textField sono compilati e modifica i dato solo dove sono stati compilati
                if(!((Offerta) model).getDescrizione().isEmpty())
                {
                    queryBuilder.append(" Descrizione = '").append(((Offerta) model).getDescrizione()).append("',");
                }
                //Rimuove l'eventuale ultima virgola
                queryBuilder.deleteCharAt(queryBuilder.length() - 1);

                queryBuilder.append(" WHERE ID = ").append(((Offerta) model).getID());
                queryBuilder.append(" AND CODVino = ").append(((Offerta) model).getCODVino()).append(";");

                query += queryBuilder.toString();
            }
            case Costanti.Modifica_Stato_Ordine ->
            {
                {
                    Insertable Stato_Modificato = (Insertable) model;
                    StringBuilder queryBuilder = new StringBuilder("UPDATE ordini SET");

                    //controllo se i textField sono compilati e modifica i dato solo dove sono stati compilati
                    if(!((Ordine) model).getStato().isEmpty())
                    {
                        queryBuilder.append(" Stato = '").append(((Ordine) model).getStato()).append("',");
                    }
                    //Rimuove l'eventuale ultima virgola
                    queryBuilder.deleteCharAt(queryBuilder.length() - 1);

                    queryBuilder.append(" WHERE ID = ").append(((Ordine) model).getID()).append(";");

                    query += queryBuilder.toString();
                }
            }
            case Costanti.Mostra_Ordini_Cliente ->
            {
                query += "SELECT * " +
                         "FROM ordini " +
                         "WHERE CODCliente = " + ((Ordine) model).getCODCliente() + ";";
            }
            case Costanti.Ricerca_Ordini_Cliente ->
            {
                Insertable Ordine_Cercato = (Insertable) model;
                query += "SELECT * " +
                         "FROM ordini " +
                         "WHERE CODCliente = " + ((Ordine) model).getCODCliente() + " " +
                         "AND Stato = '" + ((Ordine) model).getStato() + "';";
            }
            case Costanti.Add_Preferiti ->
            {
                Insertable preferiti = (Insertable) model;
                query += "INSERT INTO preferiti (ID, CODVino, CODCliente) " +
                         "VALUES (" + ((Preferito) model).getID() + "," + ((Preferito) model).getCODVino() + "," + ((Preferito) model).getCODCliente() + ");";
            }
            case Costanti.Mostra_Preferiti ->
            {
                query += "SELECT p.*, v.Nome " +
                         "FROM preferiti p, vini v " +
                         "WHERE v.ID = p.CODVino " +
                         "AND CODCliente = " + ((Preferito) model).getCODCliente() + ";";
            }
            case Costanti.Ricerca_Preferiti ->
            {
                Insertable preferito_cercato = (Insertable) model;
                query += "SELECT * " +
                         "FROM preferiti " +
                         "WHERE CODCliente = " + ((Preferito) model).getCODCliente() + " " +
                         "AND CODVino = " + ((Preferito) model).getCODVino() + ";";
            }
            case Costanti.Elimina_Preferiti ->
            {
                query += "DELETE FROM preferiti " +
                         "WHERE ID = " + ((Preferito) model).getID() + " " +
                         "AND CODVino = " + ((Preferito) model).getCODVino() + " " +
                         "AND CODCliente = " + ((Preferito) model).getCODCliente() + ";";
            }
            case Costanti.Add_Recensione ->
            {
                Insertable recensione = (Insertable) model;
                query += "INSERT INTO recensioni (Voto, Commento, CODVino, CODCliente) " +
                         "VALUES ('" + ((Recensione) model).getVoto() + "','" + ((Recensione) model).getCommento() + "'," + ((Recensione) model).getCODVino() + "," + ((Recensione) model).getCODCliente() + ");";
            }
            case Costanti.Mostra_Recensioni ->
            {
                query += "SELECT * " +
                         "FROM recensioni " +
                         "WHERE CODCliente = " + ((Recensione) model).getCODCliente() + ";";
            }
            case Costanti.Ricerca_Recensioni ->
            {
                query += "SELECT * " +
                         "FROM recensioni " +
                         "WHERE CODCliente = " + ((Recensione) model).getCODCliente() + " " +
                         "AND Voto = '" + ((Recensione) model).getVoto() + "';";
            }
            case Costanti.Elimina_Recensioni ->
            {
                query += "DELETE FROM recensioni " +
                         "WHERE ID = " + ((Recensione) model).getID() + " " +
                         "AND CODVino = " + ((Recensione) model).getCODVino() + ";";
            }
            case Costanti.Add_Carrello ->
            {
                Insertable add_carrello = (Insertable) model;
                query += "INSERT INTO dettagli_ordini (Quantita, CODVino, CODOrdine, CODCliente) " +
                         "VALUES (" + ((DettagliOrdine) model).getQuantita() + "," + ((DettagliOrdine) model).getCODVino() + ",null," + ((DettagliOrdine) model).getCODCliente() + ");";
            }
            case Costanti.Mostra_Vini_Carrello ->
            {
                query += "SELECT d.*, v.Nome " +
                         "FROM dettagli_ordini d, vini v " +
                         "WHERE v.ID = d.CODVino " +
                         "AND d.CODCliente = " + ((DettagliOrdine) model).getCODCliente() + " " +
                         "AND d.CODOrdine IS null;";
            }
            case Costanti.Update_Quantita_Vino ->
            {
                Insertable Quantita_Modificata = (Insertable) model;
                query += "UPDATE vini " +
                         "SET Quantita = " + ((Vino) model).getQuantita() + " " +
                         "WHERE ID = " + ((Vino) model).getID() + ";";
            }
            case Costanti.Verifica_Offerte_Vino ->
            {
                query += "SELECT CODVino, Sconto " +
                         "FROM offerte " +
                         "WHERE CODVino = " + ((Offerta) model).getCODVino() + ";";
            }
            case Costanti.Add_Ordine ->
            {
                Insertable nuovo_ordine = (Insertable) model;
                query += "INSERT INTO ordini(Totale, Indirizzo, Metodo_Pagamento, Stato, CODCliente) " +
                         "VALUES(" + ((Ordine) model).getTotale() + ",'" + ((Ordine) model).getIndirizzo() + "','" + ((Ordine) model).getMetodo_Pagamento() + "','Inviato'," + ((Ordine) model).getCODCliente() + ");";
            }
            case Costanti.Update_Dettagli_ordine ->
            {
                Insertable dettaglio_ordine_modificato = (Insertable) model;
                query += "UPDATE dettagli_ordini " +
                         "SET CODOrdine = " + ((DettagliOrdine) model).getCODOrdine() + " " +
                         "WHERE CODOrdine IS null;";
            }
            case Costanti.Mostra_Ordini_Effettuati ->
            {
                query += "SELECT ID " +
                         "FROM ordini " +
                         "WHERE Indirizzo = '" + ((Ordine) model).getIndirizzo() + "' " +
                         "AND CODCliente = " + ((Ordine) model).getCODCliente() + ";";
            }
            case Costanti.Add_Assistenza ->
            {
                Insertable nuova_assistenza = (Insertable) model;
                query += "INSERT INTO assistenze(Proposta_Acquisto, CODImpiegato, CODCliente) " +
                         "VALUES ('" + ((Assistenza) model).getProposta_Acquisto() + "'," + ((Assistenza) model).getCODImpiegato() + "," + ((Assistenza) model).getCODCliente() +");";
            }
            case Costanti.Visualizza_Assistenze ->
            {
                query += "SELECT a.* " +
                         "FROM assistenze a , utenti u " +
                         "WHERE u.ID = a.CODCliente;";
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
            {
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
            }
            case Costanti.Registrazione, Costanti.Cambio_Pswd, Costanti.Add_Vino, Costanti.Modifica_Dati_Vini, Costanti.Modifica_Dati_Vini_Anno, Costanti.Modifica_Dati_Vini_Prezzo, Costanti.Modifica_Dati_Vini_Quantita, Costanti.Modifica_Dati_Vini_Soglia, Costanti.Modifica_Dati_Vini_Tutti, Costanti.Delete_Vino, Costanti.Registrazione_Impiegati, Costanti.Add_Report, Costanti.Elimina_Impiegato, Costanti.Elimina_Offerta, Costanti.Add_Offerta, Costanti.Modifica_Dati_Offerta, Costanti.Modifica_Dati_Offerta_Sconto, Costanti.Modifica_Stato_Ordine, Costanti.Add_Preferiti, Costanti.Elimina_Preferiti, Costanti.Add_Recensione, Costanti.Elimina_Recensioni, Costanti.Add_Carrello, Costanti.Update_Quantita_Vino, Costanti.Add_Ordine, Costanti.Update_Dettagli_ordine, Costanti.Add_Assistenza:
            {
                response = new Response(Costanti.Successo, new EmptyPayload());
                break;
            }
            case Costanti.Logout:
            {
                response = new Response(Costanti.Successo, new EmptyPayload("Logout effettuato con successo"));
                this.UtenteLoggato = null;
                break;
            }
            case Costanti.Mostra_Vini_Produttore, Costanti.Ricerca_Vini_Produttore, Costanti.Mostra_Vini, Costanti.Ricerca_Vini:
            {
                ArrayList<Vino> Vini= new ArrayList<>();

                for (Map<String, String> res : queryResult)
                {
                    Vino vino = new Vino(res.get("Nome"), res.get("Provenienza"), res.get("Descrizione"), res.get("Vitigno"), res.get("Immagine"), Double.parseDouble(res.get("Prezzo")), Integer.parseInt(res.get("ID")), Integer.parseInt(res.get("Soglia")), Integer.parseInt(res.get("Quantita")), Integer.parseInt(res.get("Anno")), Integer.parseInt(res.get("CODProduttore")));
                    System.out.println(vino);
                    Vini.add(vino);
                }
                if (Vini.isEmpty())
                {
                    response = new Response(Costanti.No_Righe, new EmptyPayload());
                } else {
                    response = new Response(Costanti.Successo, Vini);
                }
                break;
            }
            case Costanti.Mostra_Clienti, Costanti.Mostra_Impiegati, Costanti.Ricerca_Impiegati, Costanti.Ricerca_Clienti:
            {
                ArrayList<Utente> Utenti = new ArrayList<>();

                for(Map<String, String> res : queryResult)
                {
                    Utente utente = new Utente(res.get("Nome"), res.get("Cognome"), res.get("CF"), res.get("Email"), res.get("Telefono"), res.get("Indirizzo"), res.get("Password"), res.get("Tipo"), res.get("Username"), Integer.parseInt(res.get("ID")));
                    System.out.println(utente);
                    Utenti.add(utente);
                }
                if (Utenti.isEmpty())
                {
                    response = new Response(Costanti.No_Righe, new EmptyPayload());
                } else {
                    response = new Response(Costanti.Successo, Utenti);
                }
                break;
            }
            case Costanti.Mostra_Offerte, Costanti.Ricerca_Offerte:
            {
                ArrayList<Offerta> Offerte = new ArrayList<>();

                for(Map<String, String> res : queryResult)
                {
                    Offerta offerta = new Offerta(Integer.parseInt(res.get("ID")), Integer.parseInt(res.get("CODVino")), Integer.parseInt(res.get("Sconto")), res.get("Descrizione"), res.get("Nome"));
                    System.out.println(offerta);
                    Offerte.add(offerta);
                }
                if (Offerte.isEmpty())
                {
                    response = new Response(Costanti.No_Righe, new EmptyPayload());
                } else {
                    response = new Response(Costanti.Successo, Offerte);
                }
                break;
            }
            case Costanti.Mostra_Ordini, Costanti.Ricerca_Ordini, Costanti.Mostra_Ordini_Cliente, Costanti.Ricerca_Ordini_Cliente:
            {
                ArrayList<Ordine> Ordini = new ArrayList<>();

                for(Map<String, String> res : queryResult)
                {
                    Ordine ordine = new Ordine(Integer.parseInt(res.get("ID")), Double.parseDouble(res.get("Totale")), Integer.parseInt(res.get("CODCliente")), res.get("Metodo_Pagamento"), res.get("Stato"), res.get("Indirizzo"));
                    System.out.println(ordine);
                    Ordini.add(ordine);
                }
                if (Ordini.isEmpty())
                {
                    response = new Response(Costanti.No_Righe, new EmptyPayload());
                } else {
                    response = new Response(Costanti.Successo, Ordini);
                }
                break;
            }
            case Costanti.Mostra_Dettagli_Ordine:
            {
                ArrayList<DettagliOrdine> DettagliOrdini = new ArrayList<>();

                for(Map<String, String> res : queryResult)
                {
                    DettagliOrdine Dettagli_Ordine = new DettagliOrdine(Integer.parseInt(res.get("ID")), Integer.parseInt(res.get("Quantita")), Integer.parseInt(res.get("CODOrdine")), Integer.parseInt(res.get("CODVino")), res.get("Nome"));
                    System.out.println(Dettagli_Ordine);
                    DettagliOrdini.add(Dettagli_Ordine);
                }
                if (DettagliOrdini.isEmpty())
                {
                    response = new Response(Costanti.No_Righe, new EmptyPayload());
                } else {
                    response = new Response(Costanti.Successo, DettagliOrdini);
                }
                break;
            }
            case Costanti.Mostra_Preferiti, Costanti.Ricerca_Preferiti:
            {
                ArrayList<Preferito> Preferiti = new ArrayList<>();

                for(Map<String, String> res : queryResult)
                {
                    Preferito preferito = new Preferito(Integer.parseInt(res.get("ID")), Integer.parseInt(res.get("CODVino")), Integer.parseInt(res.get("CODCliente")), res.get("Nome"));
                    System.out.println(preferito);
                    Preferiti.add(preferito);
                }
                if (Preferiti.isEmpty())
                {
                    response = new Response(Costanti.No_Righe, new EmptyPayload());
                } else {
                    response = new Response(Costanti.Successo, Preferiti);
                }
                break;
            }
            case Costanti.Mostra_Recensioni, Costanti.Ricerca_Recensioni:
            {
                ArrayList<Recensione> Recensioni = new ArrayList<>();

                for(Map<String, String> res : queryResult)
                {
                    Recensione recensione = new Recensione(Integer.parseInt(res.get("CODVino")), res.get("Commento"), Integer.parseInt(res.get("ID")), res.get("Voto"));
                    System.out.println(recensione);
                    Recensioni.add(recensione);
                }
                if (Recensioni.isEmpty())
                {
                    response = new Response(Costanti.No_Righe, new EmptyPayload());
                } else {
                    response = new Response(Costanti.Successo, Recensioni);
                }
                break;
            }
            case Costanti.Mostra_Vini_Carrello:
            {
                ArrayList<DettagliOrdine> DettagliOrdini = new ArrayList<>();

                for(Map<String, String> res : queryResult)
                {
                    DettagliOrdine dettaglio_ordine = new DettagliOrdine(Integer.parseInt(res.get("ID")), Integer.parseInt(res.get("Quantita")), Integer.parseInt(res.get("CODVino")), res.get("Nome"));
                    System.out.println(dettaglio_ordine);
                    DettagliOrdini.add(dettaglio_ordine);
                }
                if (DettagliOrdini.isEmpty())
                {
                    response = new Response(Costanti.No_Righe, new EmptyPayload());
                } else {
                    response = new Response(Costanti.Successo, DettagliOrdini);
                }
                break;
            }
            case Costanti.Verifica_Offerte_Vino:
            {
                ArrayList<Offerta> Offerte = new ArrayList<>();

                for(Map<String, String> res : queryResult)
                {
                    Offerta offerta = new Offerta(Integer.parseInt(res.get("CODVino")), Integer.parseInt(res.get("Sconto")));
                    System.out.println(offerta);
                    Offerte.add(offerta);
                }
                if (Offerte.isEmpty())
                {
                    response = new Response(Costanti.No_Righe, new EmptyPayload());
                } else {
                    response = new Response(Costanti.Successo, Offerte);
                }
                break;
            }
            case Costanti.Mostra_Ordini_Effettuati:
            {
                ArrayList<Ordine> ordini = new ArrayList<>();

                for(Map<String, String> res : queryResult)
                {
                    Ordine ordine = new Ordine(Integer.parseInt(res.get("ID")));
                    System.out.println(ordine);
                    ordini.add(ordine);
                }
                if (ordini.isEmpty())
                {
                    response = new Response(Costanti.No_Righe, new EmptyPayload());
                } else {
                    response = new Response(Costanti.Successo, ordini);
                }
                break;
            }
            case Costanti.Visualizza_Assistenze:
            {
                ArrayList<Assistenza> assistenze = new ArrayList<>();

                for(Map<String, String> res : queryResult)
                {
                    Assistenza assistenza = new Assistenza(Integer.parseInt(res.get("ID")), Integer.parseInt(res.get("CODImpiegato")), Integer.parseInt(res.get("CODCliente")), res.get("Proposta_Acquisto"));
                    System.out.println(assistenza);
                    assistenze.add(assistenza);
                }
                if (assistenze.isEmpty())
                {
                    response = new Response(Costanti.No_Righe, new EmptyPayload());
                } else {
                    response = new Response(Costanti.Successo, assistenze);
                }
                break;
            }
            default:
                throw new SQLToResponseException();
        }
        return response;
    }
}
