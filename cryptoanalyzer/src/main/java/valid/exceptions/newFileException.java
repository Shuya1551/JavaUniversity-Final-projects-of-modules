package valid.exceptions;

public class newFileException extends RuntimeException {
    String message;

    public String getMessage() {
        return this.message;
    }

    public newFileException(String message){
        this.message = message;
    }
    public newFileException(String message, Throwable cause){
        super(cause);
        this.message = message;
    }
}
