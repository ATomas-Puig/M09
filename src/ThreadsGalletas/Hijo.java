package ThreadsGalletas;

public class Hijo extends Thread {

    Bote bote;

    public Hijo(Bote bote, String nombre) {
        super(nombre);
        this.bote = bote;
    }

    @Override
    public void run() {
        for (; ; ) {
            if (!bote.isEmpty()) {
                bote.cogerBote();
                cogerGalleta();
                try {
                    System.out.println(getName() + " está cogiendo una galleta.");
                    Thread.sleep((long) Math.random() * 1000);
                    bote.dejarBote();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void cogerGalleta() {
        bote.setGalletas(bote.getGalletas() - 1);
    }
}
