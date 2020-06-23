
//Exception class for if a key is not existing 
public class InexistentKeyException extends RuntimeException {
    public InexistentKeyException() {
        super("Key not in tree");
    }
}