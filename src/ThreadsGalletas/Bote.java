package ThreadsGalletas;

public class Bote {

    private int galletas;
    private boolean libre;
    private static final int limite = 10;

    public Bote() {
        this.galletas = 0;
        this.libre = true;
    }

    public synchronized void cogerBote() {

        try {
            while (!libre) {
                wait();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        libre = false;
        notifyAll();
    }

    public synchronized void dejarBote() {
        System.out.println("Quedan " + getGalletas() + " galletas.");
        libre = true;
        notifyAll();
    }

    public synchronized boolean isEmpty() {
        if (getGalletas() == 0) {
            return true;
        } else {
            return false;
        }
    }

    public synchronized boolean isFull() {
        if (getGalletas() == limite) {
            return true;
        } else {
            return false;
        }
    }

    public int getGalletas() {
        return galletas;
    }

    public void setGalletas(int galletas) {
        this.galletas = galletas;
    }

    public static int getLimite() {
        return limite;
    }
}
