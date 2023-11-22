package utilities.models;

import utilities.Insertable;
import utilities.Removable;

import java.io.Serializable;
public class Ordine implements Serializable, Removable, Insertable {
    private static final long serialVersionUID = 1L;
    private int ID;
    private double totale;
    private String CODCliente, Metodo_Pagamento, Indirizzo;
    private boolean pagato;
    private boolean spedito;

    public Ordine(int ID, double totale, String CODCliente, String metodo_Pagamento, boolean pagato, boolean spedito, String indirizzo) {
        this.ID = ID;
        this.totale = totale;
        this.CODCliente = CODCliente;
        this.Metodo_Pagamento = metodo_Pagamento;
        this.pagato = pagato;
        this.spedito = spedito;
        this.Indirizzo = indirizzo;
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

    public String getCODCliente() {
        return CODCliente;
    }

    public void setCODCliente(String CODCliente) {
        this.CODCliente = CODCliente;
    }

    public String getMetodo_Pagamento() {
        return Metodo_Pagamento;
    }

    public void setMetodo_Pagamento(String metodo_Pagamento) {
        this.Metodo_Pagamento = metodo_Pagamento;
    }

    public boolean isPagato() {
        return pagato;
    }

    public void setPagato(boolean pagato) {
        this.pagato = pagato;
    }

    public boolean isSpedito() {
        return spedito;
    }

    public void setSpedito(boolean spedito) {
        this.spedito = spedito;
    }

    public String getIndirizzo() {
        return Indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        Indirizzo = indirizzo;
    }

    //ritorna gli attributi degli ordini
    @Override
    public String[] getAttributes() {
        return new String[]{"ID", "Totale", "Indirizzo", "Metodo_Pagamento", "CODCliente"};
    }

    //Ottiene il nome della tabella
    @Override
    public String getInstanceName() {
        return "ordini";
    }

    //ritorna il valore dei produttori
    @Override
    public String[] getValues() {
        int ValorePagato = this.pagato ? 1 : 0;
        return new String[]{"'" + this.ID + "'", "'" + this.totale + "'", "'" + this.Indirizzo + "'", "'" + this.Metodo_Pagamento + "'", "'" + this.CODCliente + "'", "'" + ValorePagato + "'"};
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
        return "Ordine.\nCodice" + this.ID + ", totale: " + this.totale + ", tipo di pagamento: " + this.Metodo_Pagamento + ", codice utente: " + this.CODCliente;
    }
}
