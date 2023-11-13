package utilities.models;

import utilities.Insertable;
import utilities.Removable;

import java.io.Serializable;
public class Preferito implements Serializable, Removable, Insertable {
    private int ID, CODVino;
    private String CODUtente;

    public Preferito(int ID, int CODVino, String CODUtente) {
        this.ID = ID;
        this.CODVino = CODVino;
        this.CODUtente = CODUtente;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getCODVino() {
        return CODVino;
    }

    public void setCODVino(int CODVino) {
        this.CODVino = CODVino;
    }

    public String getCODUtente() {
        return CODUtente;
    }

    public void setCODUtente(String CODUtente) {
        this.CODUtente = CODUtente;
    }

    //ritorna gli attributi dei preferiti
    @Override
    public String[] getAttributes() {
        return new String[]{"ID", "CODVino", "CODUtente"};
    }

    //Ottiene il nome della tabella
    @Override
    public String getInstanceName() {
        return "preferiti";
    }

    //ritorna il valore dei produttori
    @Override
    public String[] getValues() {
        return new String[]{"'" + this.ID + "'", "'" + this.CODVino + "'", "'" + this.CODUtente + "'"};
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
        return "Preferiti.\nId preferito" + this.ID + ", codice vino: " + this.CODVino + ", codice utente: " + this.CODUtente;
    }
}
