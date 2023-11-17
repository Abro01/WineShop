package utilities;

import java.io.Serial;
import java.io.Serializable;

/*
    Classe utilizzata per le richieste e le risposte che non hanno uno specifico payload
*/
public class EmptyPayload implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private final String messaggio;

    public EmptyPayload() {
        this.messaggio = "";
    }

    public EmptyPayload(String messaggio) {
        this.messaggio = messaggio;
    }

    @Override
    public String toString() { return "Messaggio del Server: " + messaggio; }
}
