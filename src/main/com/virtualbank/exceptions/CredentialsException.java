package main.com.virtualbank.exceptions;

import main.com.virtualbank.enums.CredentialErrorCodes;

public class CredentialsException extends RuntimeException {
    public CredentialsException(CredentialErrorCodes credentialErrorCode) {
        super(decodeCredentialErrorCode(credentialErrorCode));
    }

    private static String decodeCredentialErrorCode(CredentialErrorCodes credentialErrorCode) {
        return switch (credentialErrorCode) {
            case ACCOUNT_EXISTS -> "Account already Exists! Please login instead. ";
            case ACCOUNT_DOES_NOT_EXIST -> "Account doesn't exist! ";
            case INVALID_PASSWORD -> "Invalid Password! ";
        };
    }

}
