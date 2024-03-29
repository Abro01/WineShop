package utilities.models;

import utilities.Insertable;
import utilities.Removable;

import java.io.Serializable;
public class Preferito implements Serializable, Removable, Insertable {
    private static final long serialVersionUID = 1L;
    private int ID, CODVino, CODCliente;
    private String Nome;

    public Preferito(int ID, int CODVino, int CODCliente) {
        this.ID = ID;
        this.CODVino = CODVino;
        this.CODCliente = CODCliente;
    }

    public Preferito(int CODVino, int CODCliente) {
        this.CODVino = CODVino;
        this.CODCliente = CODCliente;
    }

    public Preferito(int CODCliente) {
        this.CODCliente = CODCliente;
    }

    public Preferito(int ID, int CODVino, int CODCliente, String nome) {
        this.ID = ID;
        this.CODVino = CODVino;
        this.CODCliente = CODCliente;
        this.Nome = nome;
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

    public int getCODCliente() {
        return CODCliente;
    }

    public void setCODCliente(int CODCliente) {
        this.CODCliente = CODCliente;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        this.Nome = nome;
    }

    //ritorna gli attributi dei preferiti
    @Override
    public String[] getAttributes() {
        return new String[]{"ID", "CODVino", "CODCliente"};
    }

    //Ottiene il nome della tabella
    @Override
    public String getInstanceName() {
        return "preferiti";
    }

    //ritorna il valore dei produttori
    @Override
    public String[] getValues() {
        return new String[]{"'" + this.ID + "'", "'" + this.CODVino + "'", "'" + this.CODCliente + "'"};
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
        return "Preferiti.\nId preferito" + this.ID + ", codice vino: " + this.CODVino + ", codice cliente: " + this.CODCliente;
    }
}
