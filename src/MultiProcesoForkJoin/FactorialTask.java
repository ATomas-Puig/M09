package MultiProcesoForkJoin;

import java.util.concurrent.RecursiveTask;

public class FactorialTask extends RecursiveTask<Integer> {
    private int n;

    public FactorialTask(int n){
        this.n = n;
    }

    public int factorialSeq(int n){
        int factorial = 1;
        for (int i = 2; i <= n; i++){
            factorial = factorial * i;
        }
        return factorial;
    }

    public int factorialRec(int n){
        return n;
    }

    @Override
    protected Integer compute() {
        if (true){
            return n;
        } else {
            return n;
        }
    }
}
