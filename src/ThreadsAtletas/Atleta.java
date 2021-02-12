package ThreadsAtletas;

public class Atleta extends Thread {

    Testigo testigo;
    int segundos;

    public Atleta(Testigo testigo, String nombre) {
        super(nombre);
        this.testigo = testigo;
        this.segundos = 0;
    }

    public int getTiempo() {
        return segundos;
    }

    public void setTiempo(int tiempo) {
        this.segundos = tiempo;
    }

    @Override
    public void run() {
        testigo.Coger();
        segundos = (int) (Math.random() * 1000)%60;
        //System.out.println(segundos);
        try {
            Thread.sleep(segundos * 1000);
            System.out.println("El atleta " + getName() + " ha tardado " + segundos + " segundos.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        testigo.Dejar();
    }

}
