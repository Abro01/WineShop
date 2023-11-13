package com.wineshop.client;

import utilities.*;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
public class RequestController {
    private final ObjectOutputStream out;
    private final ObjectInputStream in;

    public RequestController(ObjectOutputStream out, ObjectInputStream in) {
        this.out = out;
        this.in = in;
    }

    //utilizzato per fare richieste
    public <T> Response makeRequest(int header, T payload) {
        Response r = null;
        try {
            out.writeObject(new Request(header, payload));
            r = (Response) in.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return r;
    }

    /*
        chiude la connessione con il server utilizzando una speciale richiesta
        e chiudendo il socket
    */
    public void closeConnection() {
        try {
            out.writeObject(new Request(Costanti.Logout, new EmptyPayload()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
