package ThreadsAtletas;

public class Testigo {
    private boolean testigoLibre;

    public Testigo() {
        testigoLibre = true;
    }

    public synchronized void Coger() {
        try {
            while (!testigoLibre) wait();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        testigoLibre = false;

        notifyAll();
    }

    public synchronized void Dejar() {

        testigoLibre = true;
        notifyAll();
    }
}
