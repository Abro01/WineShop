package server.managedb;

import java.io.Serial;
public class SQLToResponseException extends Exception {
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * constructor from superclass
     */
    public SQLToResponseException() { super(); }

    /**
     * constructor with custom message
     * @param message the message to display
     */
    public SQLToResponseException(String message) { super(message); }
}
