package com.tco.requests;

import com.tco.misc.RequestException;

public abstract class Request {

    protected String requestType;

    public String getRequestType() {
        return requestType;
    }

    // Overrideable Methods
    public abstract void buildResponse() throws RequestException;
}