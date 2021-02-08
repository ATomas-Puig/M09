package Threads;

public class MainBanc {

    public static void main(String[] args) throws InterruptedException {
        CompteEstalvi compteEstalvi = new CompteEstalvi();
        Thread op1 = null;
        Thread op2 = null;
        //Threads
        for(int i= 0; i < 1000; i++) {
            op1 = new Thread(() -> compteEstalvi.ingres(100));
            op2 = new Thread(() -> compteEstalvi.treu(50));
            op1.start();
            op2.start();
        }
        op1.join();
        op2.join();

        System.out.println("Total saldo: " + compteEstalvi.getSaldo());
    }
}