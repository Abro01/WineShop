package utilities.models;

import utilities.Insertable;
import utilities.Removable;

import java.io.Serializable;
public class Ordine implements Serializable, Removable, Insertable {
    private static final long serialVersionUID = 1L;
    private int ID, CODCliente;
    private double totale;
    private String Metodo_Pagamento, Indirizzo, Stato;


    public Ordine(int ID, double totale, int CODCliente, String metodo_Pagamento, String stato, String indirizzo) {
        this.ID = ID;
        this.totale = totale;
        this.CODCliente = CODCliente;
        this.Metodo_Pagamento = metodo_Pagamento;
        this.Stato = stato;
        this.Indirizzo = indirizzo;
    }

    public Ordine(int CODCliente, double totale, String metodo_Pagamento, String indirizzo) {
        this.CODCliente = CODCliente;
        this.totale = totale;
        this.Metodo_Pagamento = metodo_Pagamento;
        this.Indirizzo = indirizzo;
    }

    public Ordine(int CODCliente, String metodo_Pagamento, String indirizzo) {
        this.CODCliente = CODCliente;
        this.Metodo_Pagamento = metodo_Pagamento;
        this.Indirizzo = indirizzo;
    }


    public Ordine(String stato) {
        this.Stato = stato;
    }

    public Ordine(int ID, String stato) {
        this.ID = ID;
        this.Stato = stato;
    }

    public Ordine(int ID) {
        this.ID = ID;
    }

    public Ordine(double CODCliente) {
        this.CODCliente = (int) CODCliente;
    }

    public Ordine(String stato, int CODCliente){
        this.Stato = stato;
        this.CODCliente = CODCliente;
    }

    public Ordine(double CODCliente, String Indirizzo){
        this.Indirizzo = Indirizzo;
        this.CODCliente = (int) CODCliente;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public double getTotale() {
        return totale;
    }

    public void setTotale(double totale) {
        this.totale = totale;
    }

    public int getCODCliente() {
        return CODCliente;
    }

    public void setCODCliente(int CODCliente) {
        this.CODCliente = CODCliente;
    }

    public String getMetodo_Pagamento() {
        return Metodo_Pagamento;
    }

    public void setMetodo_Pagamento(String metodo_Pagamento) {
        this.Metodo_Pagamento = metodo_Pagamento;
    }

    public String getIndirizzo() {
        return Indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.Indirizzo = indirizzo;
    }

    public String getStato() {
        return Stato;
    }

    public void setStato(String stato) {
        this.Stato = stato;
    }

    //ritorna gli attributi degli ordini
    @Override
    public String[] getAttributes() {
        return new String[]{"ID", "Totale", "Indirizzo", "Metodo_Pagamento", "Stato", "CODCliente"};
    }

    //Ottiene il nome della tabella
    @Override
    public String getInstanceName() {
        return "ordini";
    }

    //ritorna il valore dei produttori
    @Override
    public String[] getValues() {
        return new String[]{"'" + this.ID + "'", "'" + this.totale + "'", "'" + this.Indirizzo + "'", "'" + this.Metodo_Pagamento + "'", "'" + this.Stato + "'", "'" + this.CODCliente + "'"};
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
        return "Ordine.\nCodice" + this.ID + ", totale: " + this.totale + ", tipo di pagamento: " + this.Metodo_Pagamento + ", stato dell'ordine: " + this.Stato + ", codice utente: " + this.CODCliente;
    }
}
