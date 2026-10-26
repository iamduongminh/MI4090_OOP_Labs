package exceptions;

public class NoSuitableItemException extends Exception {
    public NoSuitableItemException() {
        super("Khong tim thay thiet bi phu hop.");
    }
}