package MultiProcesoRecursiveTask;

import java.util.concurrent.RecursiveTask;

public class Division extends RecursiveTask<Integer> {

    private int dividendo;
    private int divisor;
    public Division(int dividendo, int divisor) {
        this.dividendo = dividendo;
        this.divisor = divisor;
    }

    public int getDivisor() {
        return divisor;
    }

    public void setDivisor(int divisor) {
        this.divisor = divisor;
    }

    public int getDividendo() {
        return dividendo;
    }

    public void setDividendo(int dividendo) {
        this.dividendo = dividendo;
    }

    public int restaRecursiva (int dividendo, int divisor){
        int resto = 0;
        //if (resto > 0
        return resto;
    }

    @Override
    protected Integer compute() {
        return null;
    }
}
