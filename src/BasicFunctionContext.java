import java.util.HashMap;

public class BasicFunctionContext<T>{
    //Stored by output size
    HashMap<Integer,BasicFunction<T>[]> functions;
    int[] sortedSizes;
}
