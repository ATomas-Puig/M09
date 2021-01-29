package MultiProcesoRecursiveTask;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

public class MainCaixa {

    public static void main(String[] args) {

        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(2);
        Client c1 = new Client("Marianico", new ArrayList<Integer>(Arrays.asList(1,10,40,13,50)));
        Client c2 = new Client("Teodoro", new ArrayList<>(Arrays.asList(6,13,6,9,12)));

        Caixa caixa1 = new Caixa(c1);
        Caixa caixa2 = new Caixa(c2);

        try {
        List<Future<Integer>> resultados = executor.invokeAll(new ArrayList<Caixa>(Arrays.asList(caixa1,caixa2)));
        executor.shutdown();

        for (int i = 0; i < resultados.size(); i++){
            System.out.println(resultados.get(i).get());
        }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
