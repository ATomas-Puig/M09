package ThreadsGalletas;

import ThreadsAtletas.Testigo;

public class Padre extends Thread {

    Bote bote;

    public Padre(Bote bote, String nombre) {
        super(nombre);
        this.bote = bote;
    }

    @Override
    public void run() {
        for (; ; ) {
            if (bote.isEmpty() && !bote.isFull()) {
                bote.cogerBote();
                rellenarBote();
                try {
                    System.out.println(getName() + " está rellenando el bote...");
                    Thread.sleep((long) Math.random() * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                bote.dejarBote();
            }
        }
    }

    public void rellenarBote() {
        bote.setGalletas(Bote.getLimite());
    }
}
