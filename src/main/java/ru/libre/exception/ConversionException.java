package ru.libre.exception;

public class ConversionException extends RuntimeException {
    public ConversionException(Throwable cause) {
        super(String.format("Conversion error [%s]", cause.getMessage()), cause);
    }
}
