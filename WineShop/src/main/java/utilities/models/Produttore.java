package utilities.models;

import utilities.Insertable;
import utilities.Removable;

import java.io.Serializable;
public class Produttore implements Serializable, Removable, Insertable {
    private int id;
    private String nome, cognome, cf, email, telefono, indirizzo_azienda, tipo;

    public Produttore(int id, String nome, String cognome, String cf, String email, String telefono, String indirizzo_azienda, String tipo) {
        this.id = id;
        this.nome = nome;
        this.cognome = cognome;
        this.cf = cf;
        this.email = email;
        this.telefono = telefono;
        this.indirizzo_azienda = indirizzo_azienda;
        this.tipo = tipo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getCf() {
        return cf;
    }

    public void setCf(String cf) {
        this.cf = cf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getIndirizzo_azienda() {
        return indirizzo_azienda;
    }

    public void setIndirizzo_azienda(String indirizzo_azienda) {
        this.indirizzo_azienda = indirizzo_azienda;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    //ritorna gli attributi dei produttori
    @Override
    public String[] getAttributes() {
        return new String[]{"id", "cf", "nome", "cognome", "email", "telefono", "indirizzo_azienda", "tipo"};
    }

    //Ottiene il nome della tabella
    @Override
    public String getInstanceName() {
        return "produttori";
    }

    //ritorna il valore dei produttori
    @Override
    public String[] getValues() {
        return new String[]{"'" + this.id + "'", "'" + this.cf + "'", "'" + this.nome + "'", "'" + this.cognome + "'", "'" + this.email + "'", "'" + this.telefono + "'", "'" + this.indirizzo_azienda + "'", "'" + this.tipo + "'"};
    }

    //Ottiene la chiave unica della tabella
    @Override
    public String getPk() {
        return "ID";
    }

    //Ottiene il valore della chiave unica
    @Override
    public String getPkValue() {
        return "'" + this.getId() + "'";
    }

    public String toString() {
        return "Produttore.\nNome" + this.nome + ", cognome: " + this.cognome + ", tipo: " + this.tipo + ", azienda: " + this.indirizzo_azienda;
    }
}
