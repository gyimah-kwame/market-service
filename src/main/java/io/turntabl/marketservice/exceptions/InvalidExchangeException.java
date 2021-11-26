package io.turntabl.marketservice.exceptions;

public class InvalidExchangeException extends RuntimeException{

    public InvalidExchangeException(Long id) {
        super("Exchange with id "+id +" does not exists");
    }
}
