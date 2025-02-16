package portalgen.userservice.exception;

public class ResponseException extends RuntimeException {
    private final transient ResponseError error;
    public ResponseException(ResponseError error) {
        super(error.getMessage());
        this.error = error;
    }

    public ResponseException(ResponseError error, String message) {
        super(message);
        this.error = error;
    }

    public ResponseError getError() {
        return this.error;
    }
}