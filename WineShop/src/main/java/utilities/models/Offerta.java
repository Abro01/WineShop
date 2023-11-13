package utilities.models;

import java.io.Serializable;
public class Offerta implements Serializable {
    private int ID, CODVino, Sconto;
    private final String Descrizione;

    public Offerta(int ID, int CODVino, int sconto, String descrizione) {
        this.ID = ID;
        this.CODVino = CODVino;
        this.Sconto = sconto;
        this.Descrizione = descrizione;
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

    public int getSconto() {
        return Sconto;
    }

    public void setSconto(int sconto) {
        Sconto = sconto;
    }

    public String getDescrizione() {
        return Descrizione;
    }

    @Override
    public String toString() {
        return "Offerta numero " + ID + ", con sconto del " + Sconto + ", che consiste in '" + Descrizione;
    }
}
