package br.pucrs.ages.mase.authservice.exception;

public class UnauthorizedException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public UnauthorizedException(Throwable exception) {
        super(exception);
    }

    public UnauthorizedException(String exception) {
        super(exception);
    }
}