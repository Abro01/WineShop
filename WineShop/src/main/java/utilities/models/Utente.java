package utilities.models;

import utilities.Insertable;
import utilities.Removable;

import java.io.Serializable;

public class Utente implements Insertable, Serializable, Removable {
    private static final long serialVersionUID = 1L;
    private String nome, cognome, cf, email, telefono, indirizzo, password, tipo, username;
    private int id;

    public Utente(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Utente(int id, String username, String password, String tipo) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.tipo = tipo;
    }

    public Utente(String username, int id) {
        this.username = username;
        this.id = id;
    }

    public Utente(String nome, String cognome, String cf, String email, String telefono, String indirizzo, String password, String tipo, String username) {
        this.nome = nome;
        this.cognome = cognome;
        this.cf = cf;
        this.email = email;
        this.telefono = telefono;
        this.indirizzo = indirizzo;
        this.password = password;
        this.tipo = tipo;
        this.username = username;
    }

    public Utente(String username) {
        this.username = username;
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

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    //Ottiene gli attributi della classe
    @Override
    public String[] getAttributes() {
        return new String[]{"cf", "nome", "cognome", "username", "password", "email", "telefono", "indirizzo", "tipo"};
    }

    //Ottiene il nome della tabella
    @Override
    public String getInstanceName() {
        return "utenti";
    }

    //Ottiene un array di String che contiene il valore degli elementi della classe
    @Override
    public String[] getValues() {
        return new String[]{"'" + this.id + "'", "'" + this.cf + "'", "'" + this.nome + "'", "'" + this.cognome + "'", "'" + this.username + "'", "'" + this.password + "'", "'" + this.email + "'", "'" + this.telefono + "'", "'" + this.indirizzo + "'", "'" + this.tipo + "'"};
    }

    //Ottiene la chiave unica della tabella
    @Override
    public String getPk() {
        return "username";
    }

    //Ottiene il valore della chiave unica
    @Override
    public String getPkValue() {
        return "'" + this.getUsername() + "'";
    }

    public String toString() {
        return "Utente loggato:\n" + "Codice fiscale: " + this.cf + ", nome: " + this.nome + ", cognome: " + this.cognome + ", username: " + this.username + ", password: " + this.password + ", email: " + this.email + ", telefono: " + this.telefono + ", indirizzo: " + this.indirizzo + ", ruolo: " + this.tipo ;
    }
}
