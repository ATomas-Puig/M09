package ThreadsAtletas;

public class Atleta extends Thread {

    Testigo testigo;

    public Atleta(Testigo testigo, String nombre) {
        super(nombre);
        this.testigo = testigo;
    }

    @Override
    public void run() {
        testigo.Coger();
        int metros = (int) Math.random() * 1000;
        try {
            Thread.sleep(metros);
            System.out.println("El atleta " + getName() + " ha recorrido " + metros + " metros.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        testigo.Dejar();
    }
}
