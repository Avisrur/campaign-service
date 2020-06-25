package campaign.exceptions;

public class UnknownQueryParamException extends RuntimeException {
    public UnknownQueryParamException(String message) {
        super(message);
    }
}
