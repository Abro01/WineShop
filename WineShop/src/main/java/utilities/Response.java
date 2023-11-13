package utilities;

import java.io.Serializable;

public class Response implements Serializable {
    private static final long serialVersionUID = 1L;
    private final  int statusCode;

    //the request payload type
    private final Class<?> payloadType;

    //the request payload
    private final Object payload;

    public Response(int statusCode, Object payload) {
        this.statusCode = statusCode;
        this.payloadType = payload.getClass();
        this.payload = payload;
    }

    public int getStatusCode() {
        return this.statusCode;
    }

    public Class<?> getPayloadType() {
        return this.payloadType;
    }

    public Object getPayload() {
        return this.payload;
    }
}
