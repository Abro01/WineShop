package utilities.models;

import utilities.Insertable;
import utilities.Removable;

import java.io.Serializable;
public class Offerta implements Insertable, Serializable, Removable {
    private static final long serialVersionUID = 1L;
    private int ID, CODVino, Sconto;
    private String Descrizione;

    public Offerta(int ID, int CODVino, int sconto, String descrizione) {
        this.ID = ID;
        this.CODVino = CODVino;
        this.Sconto = sconto;
        this.Descrizione = descrizione;
    }
    public Offerta(int ID) {
        this.ID = ID;
    }

    public Offerta(double CODVino) {
        this.CODVino = (int) CODVino;
    }

    public Offerta(int CODVino, int sconto) {
        this.CODVino = CODVino;
        this.Sconto = sconto;
    }

    public Offerta(int ID, int CODVino, String descrizione) {
        this.ID = ID;
        this.CODVino = CODVino;
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
        this.Sconto = sconto;
    }

    public String getDescrizione() {
        return Descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.Descrizione = descrizione;
    }

    @Override
    public String toString() {
        return "Offerta numero " + this.ID + ", con sconto del " + this.Sconto + "%, che consiste in uno " + this.Descrizione;
    }

    @Override
    public String[] getAttributes() {
        return new String[]{"ID", "Descrizione", "Sconto", "CODVino"};
    }

    @Override
    public String getInstanceName() {
        return "offerte";
    }

    @Override
    public String[] getValues() {
        return new String[]{"'" + this.ID + "'", "'" + this.Descrizione + "'", "'" + this.Sconto + "'", "'" + this.CODVino + "'"};
    }

    @Override
    public String getPk() {
        return "ID";
    }

    @Override
    public String getPkValue() {
        return "'" + this.getID() + "'";
    }
}
