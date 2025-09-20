package com.github.com.ronaldocutrim.Expenses_Control.contracts;

public class NotFound extends RuntimeException {
    public NotFound(String message) {
        super(message);
    }
}