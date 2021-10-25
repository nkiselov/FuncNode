public interface Function<T> {
    public int getParamsCount();
    public int getInputLinksCount();
    public int getOutputLinksCount();
    //-1 = undecided -> return min possible
    public int[] getDifficulty(int[] prior);
    public int[] getMinDifficulty();
    public T[] forward(T[] prior);
    public T[] backward(T[] later) throws InverseException;
}
