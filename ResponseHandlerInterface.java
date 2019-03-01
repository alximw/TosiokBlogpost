package com.ljtq.res.ut;

public interface ResponseHandlerInterface {
    void handleException(Exception exception);

    void handleResponse(String response);
}

