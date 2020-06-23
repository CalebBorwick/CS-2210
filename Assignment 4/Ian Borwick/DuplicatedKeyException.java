//exception class for if a key is a duplicate
public class DuplicatedKeyException extends RuntimeException {
    public DuplicatedKeyException() {
        super("Cannot insert key because it already exists in tree");
    }
 
}