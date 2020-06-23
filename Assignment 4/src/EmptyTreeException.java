//exception class for if the tree is empty
public class EmptyTreeException extends RuntimeException {
    public EmptyTreeException() {
        super("Empty Tree");
    }
}