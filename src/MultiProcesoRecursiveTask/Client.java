package MultiProcesoRecursiveTask;

import java.util.List;

public class Client {
    private String Nom;
    private List<Integer> carret;

    public Client(String nom, List<Integer> carret) {
        this.carret = carret;
        Nom = nom;
        this.carret = carret;
    }

    public String getNom() {
        return Nom;
    }

    public void setNom(String nom) {
        Nom = nom;
    }

    public List<Integer> getCarret() {
        return carret;
    }

    public void setCarret(List<Integer> carret) {
        this.carret = carret;
    }
}