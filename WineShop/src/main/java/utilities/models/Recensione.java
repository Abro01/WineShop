package utilities.models;

import utilities.Insertable;
import utilities.Removable;

import java.io.Serializable;
public class Recensione implements Serializable, Removable, Insertable {
    private static final long serialVersionUID = 1L;
    private int ID, voto, CODVino;
    private String CODCliente, commento;

    public Recensione(int ID, int voto, int CODVino, String CODCliente, String commento) {
        this.ID = ID;
        this.voto = voto;
        this.CODVino = CODVino;
        this.CODCliente = CODCliente;
        this.commento = commento;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getVoto() {
        return voto;
    }

    public void setVoto(int voto) {
        this.voto = voto;
    }

    public int getCODVino() {
        return CODVino;
    }

    public void setCODVino(int CODVino) {
        this.CODVino = CODVino;
    }

    public String getCODCliente() {
        return CODCliente;
    }

    public void setCODCliente(String CODCliente) {
        this.CODCliente = CODCliente;
    }

    public String getCommento() {
        return commento;
    }

    public void setCommento(String commento) {
        this.commento = commento;
    }

    //ritorna gli attributi delle recensioni
    @Override
    public String[] getAttributes() {
        return new String[]{"ID", "Voto", "Commento", "CODVino", "CODCliente"};
    }

    //Ottiene il nome della tabella
    @Override
    public String getInstanceName() {
        return "recensioni";
    }

    //ritorna il valore delle recensioni
    @Override
    public String[] getValues() {
        return new String[]{"'" + this.ID + "'", "'" + this.voto + "'", "'" + this.commento + "'", "'" + this.CODVino + "'", "'" + this.CODCliente + "'"};
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
        return "Recensione: " + this.commento + ", voto: " + this.voto + ", codice vino: " + this.CODVino + ", codice cliente: " + this.CODCliente;
    }
}
