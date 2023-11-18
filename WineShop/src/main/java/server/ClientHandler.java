package server;

import utilities.*;

import server.managedb.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import java.util.Map;
//questa classe gestisce le richieste client
public class ClientHandler implements Runnable {
    //è l'intestazione di ogni client
    private final Socket socket;
    private final SQLTranslator translator;
    private final DataBaseManager dbManager;

    public ClientHandler(Socket socket, ServerConfiguration conf) {
        this.socket = socket;
        this.translator = new SQLTranslator();
        this.dbManager = new DataBaseManager(conf.getDbUser(), conf.getDbPassword());
    }

    //Esegue run durante la gestione dei client
    public void  run() {
        ObjectInputStream in;
        ObjectOutputStream out;

        try {
            in = new ObjectInputStream(this.socket.getInputStream());
            out = new ObjectOutputStream(this.socket.getOutputStream());
        } catch (IOException e)
        {
            System.err.println("Errore riscontrato aprendo lo stream!");
            try {
                this.socket.close();
            } catch (IOException ie)
            {
                System.err.println("Errore riscontrato chiudendo lo stream!");
            }
            return;
        }
        System.out.println("4 - Client " + this.socket.getInetAddress().getHostAddress() + " " + this.socket.getPort() + " connesso!");

        while (true)
        {
            try {
                utilities.Response response;

                try {
                    Request request = (Request) in.readObject();        //legge la richiesta in arrivo

                    if(request.getValue() == Costanti.Close_Connection)
                    {
                        break;          //se la richiesta è di chiudere la connessione
                    }

                    String query = this.translator.requestToSQL(request);

                    System.out.println("Prova ad eseguire:\n" + query);

                    List<Map<String, String>> queryResult = this.dbManager.executeSQLStatement(query);       //esegue la query (sembra giusto)

                    /*for (Map<String, String> mappa : queryResult) {
                        // Stampa tutte le chiavi e i valori della mappa
                        System.out.println(mappa);
                    }*/
                    response = this.translator.sqlToResponse(queryResult);              //traduce il risultato della query in un oggetto di tipo response

                } catch (RequestToSQLException rtse)
                {
                    rtse.printStackTrace();
                    response = new Response(Costanti.Bad_Request, new EmptyPayload("Richiesta errata!"));
                } catch (SQLToResponseException stre) {
                    stre.printStackTrace();
                    response = new Response(Costanti.Internal_Server_Error, new EmptyPayload("Errore server!"));
                }
                out.writeObject(response);
            } catch (Exception e)
            {
                e.printStackTrace();
                System.err.println("Errore riscontrato nel socket dello stream");
                break;
            }
        }

        try {
            socket.close();
        } catch (IOException ie)
        {
            System.err.println("Errore riscontrato durante la chiusura del socket!");
        }
        System.out.println("Client " + socket.getInetAddress().getHostAddress() + " " + socket.getPort() + " disconnesso!");
    }
}
