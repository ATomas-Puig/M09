package ThreadsGalletas;

public class CookieBattle {
    public static void main(String[] args) {


        Bote bote = new Bote();

        Padre padre = new Padre(bote, "Juanito el padre");
        Padre madre = new Padre(bote, "María la madre");

        Hijo hijo1 = new Hijo(bote, "Joselito el hijo listo");
        Hijo hijo2 = new Hijo(bote, "Teodoro el hijo tonto");
        Hijo hija1 = new Hijo(bote, "Gumersinda la hija lista");
        Hijo hija2 = new Hijo(bote, "Eufrasia la hija tonta");

        padre.start();
        madre.start();
        hijo1.start();
        hijo2.start();
        hija1.start();
        hija2.start();

    }
}
