package main.com.virtualbank.exceptions;

public class DepositException extends RuntimeException {
    public DepositException(String s) {
        super(s);
    }
}
