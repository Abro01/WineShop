package utilities;

import java.io.Serializable;

public class Request implements Serializable {
    private static final long serialVersionUID = 1L;
    private final  int value;

    //the request payload type
    private final Class<?> payloadType;

    //the request payload
    private final Object payload;

    public Request(int value, Object payload) {
        this.value = value;
        this.payloadType = payload.getClass();
        this.payload = payload;
    }

    public int getValue() {
        return this.value;
    }

    public Class<?> getPayloadType() {
        return payloadType;
    }

    public Object getPayload() {
        return payload;
    }
}
