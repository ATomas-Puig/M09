package EjerciciosMultiProceso;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class FerParetRunnable {

    public static void main(String[] args) {
        int numMaons = 10, ti, te;
        int numPaletes = 5;
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(5);

        //Instanciem els paletes
        Paleta[] P = new Paleta[numPaletes];

        //Comencem a contar el temps
        ti = (int) System.currentTimeMillis();

        //Donem nom als paletes i els posem a fer fer la paret
        for (int i = 0; i < numPaletes; i++) {
            P[i] = new Paleta("Paleta-" + i);
            P[i].setMaons(numMaons);
            executor.execute(P[i]);
        }

        //Terminem l'executor
        executor.shutdown();

        //Esperem fins que l'executor (els paletes) acabi les tasques per mesurar el temps
        while (!executor.isTerminated()) {
        }

        //Han acabat i agafem el temps final
        te = (int) System.currentTimeMillis();

        System.out.println("Han trigat: " + (te - ti) / 1000 + " segons");

    }
}