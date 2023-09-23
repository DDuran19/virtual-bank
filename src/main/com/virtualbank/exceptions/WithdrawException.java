package main.com.virtualbank.exceptions;

public class WithdrawException extends RuntimeException {

    public WithdrawException(String s) {
        super(s);
    }

    public WithdrawException() {
        this("Insufficient Balance!");
    }
}
