package exceptions;

public class DuplicateIdException extends Exception {
    private final String duplicateId;

    public DuplicateIdException(String duplicateId) {
        super("ID da ton tai: " + duplicateId);
        this.duplicateId = duplicateId;
    }

    public String getDuplicateId() {
        return duplicateId;
    }
}