package utilities.models;

import utilities.Insertable;
import utilities.Removable;

import java.io.Serializable;

public class Assistenza implements Serializable, Removable, Insertable {
    private static final long serialVersionUID = 1L;
    private int ID, CODImpiegato, CODCliente;
    private String Proposta_Acquisto;

    public Assistenza(int ID, int CODImpiegato, int CODCliente, String proposta_Acquisto) {
        this.ID = ID;
        this.CODImpiegato = CODImpiegato;
        this.CODCliente = CODCliente;
        this.Proposta_Acquisto = proposta_Acquisto;
    }

    public Assistenza(int CODImpiegato, int CODCliente, String proposta_Acquisto) {
        this.CODImpiegato = CODImpiegato;
        this.CODCliente = CODCliente;
        this.Proposta_Acquisto = proposta_Acquisto;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getCODImpiegato() {
        return CODImpiegato;
    }

    public void setCODImpiegato(int CODImpiegato) {
        this.CODImpiegato = CODImpiegato;
    }

    public int getCODCliente() {
        return CODCliente;
    }

    public void setCODCliente(int CODCliente) {
        this.CODCliente = CODCliente;
    }

    public String getProposta_Acquisto() {
        return Proposta_Acquisto;
    }

    public void setProposta_Acquisto(String proposta_Acquisto) {
        this.Proposta_Acquisto = proposta_Acquisto;
    }

    //ritorna gli attributi dei dettagli di un ordine
    @Override
    public String[] getAttributes() {
        return new String[]{"ID", "Proposta_Acquisto", "CODVino", "CODImpiegato", "CODCliente"};
    }

    //Ottiene il nome della tabella
    @Override
    public String getInstanceName() {
        return "assistenze";
    }

    //ritorna il valore dei dettagli di un ordine
    @Override
    public String[] getValues() {
        return new String[]{"'" + this.ID + "'", "'" + this.Proposta_Acquisto + "'", "'" + this.CODImpiegato + "'", "'" + this.CODCliente + "'"};
    }

    //Ottiene la chiave unica della tabella
    @Override
    public String getPk() {
        return "ID";
    }

    //Ottiene il valore della chiave unica
    @Override
    public String getPkValue() {
        return "'" + this.getID() + "'";
    }

    public String toString() {
        return "Assistenza.\nProposta d acquisto: " + this.Proposta_Acquisto + ", codice impiegato: " + this.CODImpiegato + ", codice cliente: " + this.CODCliente;
    }
}
