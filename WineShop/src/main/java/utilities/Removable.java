package utilities;

/*
    Questa interfaccia consente a qualsiasi oggetto che la implementa di essere analizzato e
    rimosso dal database.
*/
public interface Removable extends Insertable {
    /*
      ritorna il valore della chiave primaria(ID)
    */
    String getPkValue();
}
