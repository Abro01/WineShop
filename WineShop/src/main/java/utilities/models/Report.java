package utilities.models;

import utilities.Insertable;
import utilities.Removable;

import java.io.Serializable;
public class Report implements Insertable, Serializable, Removable {
    private static final long serialVersionUID = 1L;
    private int ID, CODAmministratore;
    private String Descrizione;

    public Report(int ID, int CODAmministratore, String descrizione) {
        this.ID = ID;
        this.CODAmministratore = CODAmministratore;
        Descrizione = descrizione;
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

    @Override
    public String[] getAttributes() {
        return new String[]{"ID", "Descrizione", "CODAmministratore"};
    }

    @Override
    public String getInstanceName() {
        return "report";
    }

    @Override
    public String[] getValues() {
        return new String[]{"'" + this.ID + "'", "'" + this.Descrizione + "'", "'" + this.CODAmministratore + "'"};
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
