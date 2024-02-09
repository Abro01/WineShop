package utilities.models;

import utilities.Insertable;
import utilities.Removable;

import java.io.Serializable;

public class DettagliOrdine implements Serializable, Removable, Insertable {
    private static final long serialVersionUID = 1L;
    private int ID, quantita, CODOrdine, CODVino, CODCliente;
    private String Nome;

    public DettagliOrdine(int ID, int quantita, int CODOrdine, int CODVino) {
        this.ID = ID;
        this.quantita = quantita;
        this.CODOrdine = CODOrdine;
        this.CODVino = CODVino;
    }

    public DettagliOrdine(int CODOrdine) {
        this.CODOrdine = CODOrdine;
    }

    public DettagliOrdine(int quantita, int CODVino, int ID) {
        this.quantita = quantita;
        this.CODVino = CODVino;
        this.ID = ID;
    }

    public DettagliOrdine(double quantita_vino, int CODVino_vino, int CODCliente_vino) {
        this.quantita = (int) quantita_vino;
        this.CODVino = CODVino_vino;
        this.CODCliente = CODCliente_vino;
    }
    public DettagliOrdine(int CODVino, int CODCliente) {
        this.CODVino = CODVino;
        this.CODCliente = CODCliente;
    }

    public DettagliOrdine(int ID, int quantita, int CODOrdine, int CODVino, String nome) {
        this.ID = ID;
        this.quantita = quantita;
        this.CODOrdine = CODOrdine;
        this.CODVino = CODVino;
        this.Nome = nome;
    }

    public DettagliOrdine(int ID, int quantita, int CODVino, String nome) {
        this.ID = ID;
        this.quantita = quantita;
        this.CODVino = CODVino;
        this.Nome = nome;
    }

    public int getCODCliente() {
        return CODCliente;
    }

    public void setCODCliente(int CODCliente) {
        this.CODCliente = CODCliente;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getQuantita() {
        return quantita;
    }

    public void setQuantita(int quantita) {
        this.quantita = quantita;
    }

    public int getCODOrdine() {
        return CODOrdine;
    }

    public void setCODOrdine(int CODOrdine) {
        this.CODOrdine = CODOrdine;
    }

    public int getCODVino() {
        return CODVino;
    }

    public void setCODVino(int CODVino) {
        this.CODVino = CODVino;
    }

    public String getNome_vino() {
        return Nome;
    }

    public void setNome_vino(String Nome) {
        this.Nome = Nome;
    }

    //ritorna gli attributi dei dettagli di un ordine
    @Override
    public String[] getAttributes() {
        return new String[]{"ID", "Quantita", "CODVino", "CODOrdine", "CODCliente"};
    }

    //Ottiene il nome della tabella
    @Override
    public String getInstanceName() {
        return "dettagli_ordini";
    }

    //ritorna il valore dei dettagli di un ordine
    @Override
    public String[] getValues() {
        return new String[]{"'" + this.ID + "'", "'" + this.quantita + "'", "'" + this.CODOrdine + "'", "'" + this.CODVino + "'", "'" + this.CODCliente + "'"};
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
        return "Dettagli ordine.\nQuantita: " + this.quantita + ", codice ordine: " + this.CODOrdine + ", codice vino: " + this.CODVino + ", codice cliente: " + this.CODCliente;
    }
}
