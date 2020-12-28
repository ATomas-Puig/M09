package MultiProceso;

import java.util.concurrent.Callable;

public class Multiplicacio implements Callable<Integer> {
        private int operador1;
        private int operador2;
        public Multiplicacio(int operador1, int operador2) {
            this.operador1 = operador1;
            this.operador2 = operador2;
        }
        @Override
        public Integer call() throws Exception {
            return operador1 * operador2;
        }
}
