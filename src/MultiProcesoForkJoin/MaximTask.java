package MultiProcesoForkJoin;


import java.util.concurrent.RecursiveTask;

public class MaximTask extends RecursiveTask<Short> {

    private static final int LLINDAR = 100;
    private short[] arr;
    private int inici, fi;

    public MaximTask(short[] arr, int inici, int fi) {
        this.arr = arr;
        this.inici = inici;
        this.fi = fi;
    }

    private short getMaxSeq() {
        short max = arr[inici];
        for (int i = inici + 1; i < fi; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        return max;
    }

    private short getMaxReq() {
        MaximTask task1;
        MaximTask task2;
        MaximTask task3;
        int mig1 = (inici + fi) / 3 + 1;
        int mig2 = fi - mig1;
        task1 = new MaximTask(arr, inici, mig1);
        //task1.fork();
        task2 = new MaximTask(arr, mig1, mig2);
        //task2.fork();
        task3 = new MaximTask(arr, mig2, fi);
        //task3.fork();
        invokeAll(task1, task2, task3);
        short max = (short) Math.max(task1.join(), task2.join());
        max = (short) Math.max(max, task3.join());

        return max;
        //return  (short) Math.max(Math.max(task1.join(), task2.join()), task3.join());
        //return (short) Math.max(task1.join(), task2.join());
    }


    @Override
    protected Short compute() {
        if (fi - inici <= LLINDAR) {
            System.out.println("Dentro del if " + (fi - inici));
            return getMaxSeq();
        } else {
            return getMaxReq();
        }
    }
}

