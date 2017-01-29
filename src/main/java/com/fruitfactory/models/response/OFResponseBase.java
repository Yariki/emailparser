package com.fruitfactory.models.response;

/**
 * Created by Yariki on 1/29/2017.
 */
public class OFResponseBase {
    private boolean status;

    private String message;

    public OFResponseBase(boolean status, String message) {
        this.status = status;
        this.message = message;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
