package MultiProcesoRecursiveTask;

import java.util.List;
import java.util.concurrent.Callable;

public class Caixa implements Callable<Integer> {

    private int preuTotal;
    private List<Integer> carret;

    public Caixa(Client client) {
        this.carret = client.getCarret();
    }

    @Override
    public Integer call() throws Exception {

        for (int i = 0 ; i < carret.size(); i++){
         preuTotal = preuTotal + carret.get(i);
        }
        return preuTotal;
    }
}
