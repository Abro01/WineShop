package server.managedb;

import java.io.Serial;
public class RequestToSQLException extends Exception {
    @Serial
    private static final long serialVersionUID = 1L;
    /**
     * constructor from superclass
     */
    public RequestToSQLException() { super(); }

    /**
     * constructor with custom message
     * @param message the message to display
     */
    public RequestToSQLException(String message) { super(message); }
}
