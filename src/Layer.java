public class Layer implements Function<Sequence>{
    //input is permuted
    private int[][] inputPermLinks;
    private int[] outputLinks;
    private int[] paramLinks;
    private BasicFunction<Sequence>[] inter;
    private int paramsCount;
    private int inputLinksCount;
    private int outputLinksCount;

    public Layer(BasicFunction<Sequence>[] inter, int[] inputPerm){
        inputPermLinks = new int[inter.length][];
        outputLinks = new int[inter.length];
        paramLinks = new int[inter.length];
        this.inter = inter;
        for(int i=0; i< inter.length; i++){
            for(int j=0; j<inter[i].getInputLinksCount(); j++) {
                inputPermLinks[i][j] = inputPerm[inputLinksCount+j];
                inputLinksCount ++;
            }
            outputLinks[i] = outputLinksCount;
            outputLinksCount += inter[i].getOutputLinksCount();
            paramLinks[i] = paramsCount;
            paramsCount += inter[i].getParamsCount();
        }
    }

    @Override
    public int getParamsCount() {
        return paramsCount;
    }

    @Override
    public int getInputLinksCount() {
        return inputLinksCount;
    }

    @Override
    public int getOutputLinksCount() {
        return outputLinksCount;
    }

    @Override
    public int[] getDifficulty(int[] prior) {
        int[] output = new int[outputLinksCount];
        for(int i=0; i< inter.length; i++){
            int[] input = new int[inter[i].getInputLinksCount()];
            for(int j=0; j<inter[i].getInputLinksCount(); j++){
                input[j] = prior[inputPermLinks[i][j]];
            }
            System.arraycopy(inter[i].getDifficulty(input),0,output,outputLinks[i],inter[i].getOutputLinksCount());
        }
        return output;
    }

    @Override
    public int[] getMinDifficulty() {
        int[] output = new int[outputLinksCount];
        for(int i=0; i< inter.length; i++){
            System.arraycopy(inter[i].getMinDifficulty(),0,output,outputLinks[i],inter[i].getOutputLinksCount());
        }
        return output;
    }

    @Override
    public Sequence[] forward(Sequence[] prior) {
        Sequence[] output = new Sequence[outputLinksCount];
        for(int i=0; i< inter.length; i++){
            Sequence[] input = new Sequence[inter[i].getInputLinksCount()];
            for(int j=0; j<inter[i].getInputLinksCount(); j++){
                input[j] = prior[inputPermLinks[i][j]];
            }
            System.arraycopy(inter[i].forward(input),0,output,outputLinks[i],inter[i].getOutputLinksCount());
        }
        return output;
    }

    @Override
    public Sequence[] backward(Sequence[] later) throws InverseException{
        Sequence[] output = new Sequence[inputLinksCount];
        for(int i=0; i< inter.length; i++){
            Sequence[] input = new Sequence[inter[i].getOutputLinksCount()];
            System.arraycopy(later,outputLinks[i],input,0,inter[i].getOutputLinksCount());
            Sequence[] o = inter[i].backward(input);
            for(int j=0; j<inter[i].getInputLinksCount(); j++){
                output[inputPermLinks[i][j]] = o[j];
            }
        }
        return output;
    }

    public static Layer[] getAllLayers(BasicFunctionContext<Sequence> ctx, int maxDifficulty, int outputLinksCount){
        
    }
}
