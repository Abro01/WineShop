package utilities.models;

import utilities.Insertable;
import utilities.Removable;

import java.io.Serializable;
public class Vino implements Serializable, Removable, Insertable {
    private static final long serialVersionUID = 1L;
    private String nome, provenienza, descrizione, vitigno, immagine;
    private double prezzo;
    private int ID, soglia, quantita, anno, CODProduttore;

    /*public Vino(String nome, String provenienza, String vitigno, double prezzo, int ID, int quantita) {
        setNome(nome);
        setProvenienza(provenienza);
        setVitigno(vitigno);
        setPrezzo(prezzo);
        setID(ID);
        setQuantita(quantita);
    }

    public Vino(int ID, int quantita) {
        setID(ID);
        setQuantita(quantita);
    }

    public Vino(String nome, int ID) {
        setNome(nome);
        setID(ID);
    }*/

    public Vino(String nome, int CODProduttore, String provenienza, String descrizione, String vitigno, String immagine, double prezzo, int ID, int soglia, int quantita, int anno) {
        this.nome = nome;
        this.CODProduttore = CODProduttore;
        this.provenienza = provenienza;
        this.descrizione = descrizione;
        this.vitigno = vitigno;
        this.immagine = immagine;
        this.prezzo = prezzo;
        this.ID = ID;
        this.soglia = soglia;
        this.quantita = quantita;
        this.anno = anno;
    }

    public Vino(String nome, int CODProduttore, String provenienza, String descrizione, String vitigno, String immagine, double prezzo, int soglia, int quantita, int anno) {
        this.nome = nome;
        this.CODProduttore = CODProduttore;
        this.provenienza = provenienza;
        this.descrizione = descrizione;
        this.vitigno = vitigno;
        this.immagine = immagine;
        this.prezzo = prezzo;
        this.soglia = soglia;
        this.quantita = quantita;
        this.anno = anno;
    }

    public Vino(String nome, String provenienza, double prezzo, int quantita, int anno) {
        this.nome = nome;
        this.provenienza = provenienza;
        this.prezzo = prezzo;
        this.quantita = quantita;
        this.anno = anno;
    }

    public Vino(int ID, int quantita) {
        this.ID = ID;
        this.quantita = quantita;
    }

    public Vino(String nome, int ID) {
        this.nome = nome;
        this.ID = ID;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getCODProduttore() {
        return CODProduttore;
    }

    public void setCODProduttore(int CODProduttore) {
        this.CODProduttore = CODProduttore;
    }

    public String getProvenienza() {
        return provenienza;
    }

    public void setProvenienza(String provenienza) {
        this.provenienza = provenienza;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public String getVitigno() {
        return vitigno;
    }

    public void setVitigno(String vitigno) {
        this.vitigno = vitigno;
    }

    public String getImmagine() {
        return null;
    }

    public void setImmagine(String immagine) {
        this.immagine = immagine;
    }

    public double getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(double prezzo) {
        this.prezzo = prezzo;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getSoglia() {
        return soglia;
    }

    public void setSoglia(int soglia) {
        this.soglia = soglia;
    }

    public int getQuantita() {
        return quantita;
    }

    public void setQuantita(int quantita) {
        this.quantita = quantita;
    }

    public int getAnno() {
        return anno;
    }

    public void setAnno(int anno) {
        this.anno = anno;
    }

    //ritorna gli attributi del vino
    @Override
    public String[] getAttributes() {
        return new String[]{"ID", "Nome", "Provenienza", "Anno", "Descrizione", "Vitigno", "Prezzo", "Soglia", "Quantita", "Immagine", "CODProduttore"};
    }

    //Ottiene il nome della tabella
    @Override
    public String getInstanceName() {
        return "vini";
    }

    //ritorna il valore del vino
    @Override
    public String[] getValues() {
        return new String[]{"'" + this.ID + "'", "'" + this.nome + "'", "'" + this.provenienza + "'", "'" + this.anno + "'", "'" + this.descrizione + "'", "'" + this.vitigno + "'", "'" + this.prezzo + "'", "'" + this.soglia + "'", "'" + this.quantita + "'", "'" + this.immagine + "'", "'" + this.CODProduttore + "'"};
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
        return "Vino: " + this.nome + ", provenienza: " + this.provenienza + ", anno: " + this.anno + ", descrizione: " + this.descrizione + ", vitigno: " + this.vitigno + ", prezzo: " + this.prezzo + ", soglia: " + this.soglia + ", quantita: " + this.quantita + ", immagine: " + this.immagine + ", prodotto da: " + this.CODProduttore;
    }
}
