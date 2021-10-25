public class InverseException extends Exception{
    private String[] input;
    private String function;

    public InverseException(String[] input, String function) {
        this.input = input;
        this.function = function;
    }
}
