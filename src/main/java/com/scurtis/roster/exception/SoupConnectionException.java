package com.scurtis.roster.exception;

/**
 * Author: Steve Curtis
 * Date: Feb 07, 2020
 **/

public class SoupConnectionException extends Exception {

    public SoupConnectionException(String errorMsg) {
        super(errorMsg);
    }

    public SoupConnectionException(String errorMsg, Throwable throwable) {
        super(errorMsg, throwable);
    }

}
