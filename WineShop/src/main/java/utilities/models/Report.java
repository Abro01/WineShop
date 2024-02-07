package utilities.models;

import utilities.Insertable;
import utilities.Removable;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Report implements Insertable, Serializable, Removable {
    private static final long serialVersionUID = 1L;
    private int ID, CODAmministratore;
    private String Descrizione;
    private String Data;

    public Report(int ID, int CODAmministratore, String Descrizione, String Data) {
        this.ID = ID;
        this.CODAmministratore = CODAmministratore;
        this.Descrizione = Descrizione;
        this.Data = Data;
    }
    public Report(int CODAmministratore, String descrizione, String data) {
        this.CODAmministratore = CODAmministratore;
        Descrizione = descrizione;
        Data = data;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getCODAmministratore() {
        return CODAmministratore;
    }

    public void setCODAmministratore(int CODAmministratore) {
        this.CODAmministratore = CODAmministratore;
    }

    public String getDescrizione() {
        return Descrizione;
    }

    public void setDescrizione(String descrizione) {
        Descrizione = descrizione;
    }

    public String getData() {
        return Data;
    }

    public void setData(String data) {
        Data = data;
    }

    @Override
    public String[] getAttributes() {
        return new String[]{"ID", "Descrizione", "Data", "CODAmministratore"};
    }

    @Override
    public String getInstanceName() {
        return "report";
    }

    @Override
    public String[] getValues() {
        return new String[]{"'" + this.ID + "'", "'" + this.Descrizione + "'", "'" + this.Data + "'", "'" + this.CODAmministratore + "'"};
    }

    @Override
    public String getPk() {
        return "ID";
    }

    @Override
    public String getPkValue() {
        return "'" + this.getID() + "'";
    }

    public String toString() {
        return "Report numero: " + this.ID + ", descrizione: " + this.Descrizione;
    }
}
