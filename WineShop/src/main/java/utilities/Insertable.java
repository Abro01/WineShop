package utilities;

/*
    Questa interfaccia consente a qualsiasi oggetto che la implementa di essere analizzato e
    inserito nel database
*/
public interface Insertable {
    /*
      ritorna gli attributi dell'oggetto
    */
    String[] getAttributes();

    /*
      ritorna il  ome della relazione con il db
    */
    String getInstanceName();

    /*
     ritorna il valore degli attributi
    */
    String[] getValues();

    /*
      ritorna l'id dell'oggetto
    */
    String getPk();
}
