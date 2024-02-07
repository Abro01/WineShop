package utilities.models;

import utilities.Insertable;
import utilities.Removable;

import java.io.Serializable;
public class Recensione implements Serializable, Removable, Insertable {
    private static final long serialVersionUID = 1L;
    private int ID, CODVino, CODCliente;
    private String Commento , Voto;

    public Recensione(int ID, String Voto, int CODVino, int CODCliente, String Commento) {
        this.ID = ID;
        this.Voto = Voto;
        this.CODVino = CODVino;
        this.CODCliente = CODCliente;
        this.Commento = Commento;
    }

    public Recensione(int CODVino, String Commento, int ID, String Voto) {
        this.CODVino = CODVino;
        this.Commento = Commento;
        this.ID = ID;
        this.Voto = Voto;
    }

    public Recensione(int CODVino, int CODCliente, String commento, String voto) {
        this.CODVino = CODVino;
        this.CODCliente = CODCliente;
        this.Commento = commento;
        this.Voto = voto;
    }

    public Recensione(int CODCliente) {
        this.CODCliente = CODCliente;
    }

    public Recensione(int ID, int CODVino) {
        this.ID = ID;
        this.CODVino = CODVino;
    }

    public Recensione(int CODCliente, String Voto) {
        this.CODCliente = CODCliente;
        this.Voto = Voto;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getVoto() {
        return Voto;
    }

    public void setVoto(String voto) {
        this.Voto = voto;
    }

    public int getCODVino() {
        return CODVino;
    }

    public void setCODVino(int CODVino) {
        this.CODVino = CODVino;
    }

    public int getCODCliente() {
        return CODCliente;
    }

    public void setCODCliente(int CODCliente) {
        this.CODCliente = CODCliente;
    }

    public String getCommento() {
        return Commento;
    }

    public void setCommento(String commento) {
        this.Commento = commento;
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
        return new String[]{"'" + this.ID + "'", "'" + this.Voto + "'", "'" + this.Commento + "'", "'" + this.CODVino + "'", "'" + this.CODCliente + "'"};
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
        return "Recensione: " + this.Commento + ", voto: " + this.Voto + ", codice vino: " + this.CODVino + ", codice cliente: " + this.CODCliente;
    }
}
