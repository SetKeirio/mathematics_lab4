package algorithm.equations;

public class CannotBeDiagonalException extends RuntimeException{

    public CannotBeDiagonalException(String s){
        super(s);
    }
    public CannotBeDiagonalException(){
        super();
    }
}
