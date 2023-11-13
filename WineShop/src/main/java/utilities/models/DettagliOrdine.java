package utilities.models;

import utilities.Insertable;
import utilities.Removable;

import java.io.Serializable;
public class DettagliOrdine implements Serializable, Removable, Insertable {
    private int ID, quantita, CODOrdine, CODVino;

    public DettagliOrdine(int ID, int quantita, int CODOrdine, int CODVino) {
        this.ID = ID;
        this.quantita = quantita;
        this.CODOrdine = CODOrdine;
        this.CODVino = CODVino;
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

    //ritorna gli attributi dei dettagli di un ordine
    @Override
    public String[] getAttributes() {
        return new String[]{"ID", "quantita", "CODOrdine", "CODVino"};
    }

    //Ottiene il nome della tabella
    @Override
    public String getInstanceName() {
        return "dettagli_ordini";
    }

    //ritorna il valore dei dettagli di un ordine
    @Override
    public String[] getValues() {
        return new String[]{"'" + this.ID + "'", "'" + this.quantita + "'", "'" + this.CODOrdine + "'", "'" + this.CODVino + "'"};
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
        return "Dettagli ordine.\nQuantita: " + this.quantita + ", codice ordine: " + this.CODOrdine + ", codice vino: " + this.CODVino;
    }
}
