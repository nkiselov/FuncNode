import java.util.ArrayList;

public class Procedure implements Function<Sequence>{
    private ArrayList<Layer> layers;
    private int paramsCount;

    @Override
    public int getParamsCount() {
        return 0;
    }

    @Override
    public int getInputLinksCount() {
        return 0;
    }

    @Override
    public int getOutputLinksCount() {
        return 1;
    }

    @Override
    public int[] getDifficulty(int[] prior) {
        return new int[0];
    }

    @Override
    public int[] getMinDifficulty() {
        return new int[0];
    }

    @Override
    public Sequence[] forward(Sequence[] prior) {
        return null;
    }

    @Override
    public Sequence[] backward(Sequence[] later) {
        return null;
    }
}
