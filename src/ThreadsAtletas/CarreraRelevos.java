package ThreadsAtletas;

public class CarreraRelevos {
    public static void main(String[] args) {
        Testigo testigo1 = new Testigo();
        Testigo testigo2 = new Testigo();
        Testigo testigo3 = new Testigo();

        Atleta atleta1 = new Atleta(testigo1, "Juanito");
        Atleta atleta2 = new Atleta(testigo1, "Pedrito");
        Atleta atleta3 = new Atleta(testigo1, "Fermín");

        Atleta atleta4 = new Atleta(testigo2, "Antoñito");
        Atleta atleta5 = new Atleta(testigo2, "Miguelito");
        Atleta atleta6 = new Atleta(testigo2, "Joselito");

        Atleta atleta7 = new Atleta(testigo3, "Felipito");
        Atleta atleta8 = new Atleta(testigo3, "Jesusito");
        Atleta atleta9 = new Atleta(testigo3, "Paquito");

        atleta1.start();
        atleta2.start();
        atleta3.start();
        atleta4.start();
        atleta5.start();
        atleta6.start();
        atleta7.start();
        atleta8.start();
        atleta9.start();

        try {
            atleta1.join();
            atleta2.join();
            atleta3.join();
            atleta4.join();
            atleta5.join();
            atleta6.join();
            atleta7.join();
            atleta8.join();
            atleta9.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int metrosE1 = atleta1.getTiempo() + atleta2.getTiempo() + atleta3.getTiempo();
        int metrosE2 = atleta4.getTiempo() + atleta5.getTiempo() + atleta6.getTiempo();
        int metrosE3 = atleta7.getTiempo() + atleta8.getTiempo() + atleta9.getTiempo();

        System.out.println("El equipo 1 ha tardado " + metrosE1 + " segundos.");
        System.out.println("El equipo 2 ha tardado " + metrosE2 + " segundos.");
        System.out.println("El equipo 3 ha tardado " + metrosE3 + " segundos.");
    }
}
