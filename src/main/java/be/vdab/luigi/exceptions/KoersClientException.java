package be.vdab.luigi.exceptions;

public class KoersClientException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public KoersClientException(String message) {
        super(message);
    }

    public KoersClientException(String message, Exception oorzaak) {
        super(message, oorzaak);
    }
}
