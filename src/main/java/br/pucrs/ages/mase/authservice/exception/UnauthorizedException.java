package br.pucrs.ages.mase.authservice.exception;

public class UnauthorizedException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public UnauthorizedException(String error) {
        super(error);
    }
}