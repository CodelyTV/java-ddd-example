package tv.codely.shared.domain;

public abstract class DomainError extends Throwable {
    private final String errorCode;
    private final String errorMessage;

    public DomainError(String errorCode, String errorMessage) {
        super(errorMessage);

        this.errorCode    = errorCode;
        this.errorMessage = errorMessage;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
