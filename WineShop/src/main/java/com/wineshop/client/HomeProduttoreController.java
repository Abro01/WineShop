package com.wineshop.client;

import utilities.models.Utente;

public class HomeProduttoreController {
    private RequestController requestController;
    private Utente UtenteLoggato;

    public void setRequestController(RequestController controller) {
        this.requestController = controller;
    }

    public void setLoggedUser(Utente utente) {
        this.UtenteLoggato = utente;
    }

    public void initialize() {
    }
}
