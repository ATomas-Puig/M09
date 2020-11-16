package JCE;

import javax.crypto.SecretKey;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class EjerciciosDeCifrado {

    public static void main(String[] args) {

        SecretKey key1 = Cifrar.keygenKeyGeneration(128);
        SecretKey key2 = Cifrar.passwordKeyGeneration("Ácido desoxirribonucleico", 128);

        {
            try {
                //Cifrar archivo con una clave de 128 bits con keygenKeyGeneration:
                /*Path pathCifrar = Paths.get("C:\\Users\\Antonio\\resultado.csv");
                byte[] primerArchivo = Files.readAllBytes(pathCifrar);
                FileWriter archivoCifrado = new FileWriter("C:\\Users\\Antonio\\archivocifrado.csv");
                archivoCifrado.write(new String(Cifrar.encryptData(key1, primerArchivo)));
                archivoCifrado.close();

                //Descifrar archivo con una clave de 128 bits con decryptData:
                Path pathDescifrar = Paths.get("C:\\Users\\Antonio\\archivocifrado.csv");
                byte[] segundoArchivo = Files.readAllBytes(pathDescifrar);
                //String segundo = new String(segundoArchivo);
                //System.out.println(segundo);
                FileWriter archivoDescifrado = new FileWriter("C:\\Users\\Antonio\\archivodescifrado.csv");
                archivoDescifrado.write(new String(Cifrar.decryptData(key1, segundoArchivo)));
                archivoDescifrado.close();*/

                String frase = "Hola qué tal.";
                byte[] fraseBytes = frase.getBytes();
                String fraseEncriptada = new String(Cifrar.encryptData(key1, fraseBytes), "UTF-8");

                byte[] frase2Bytes = fraseEncriptada.getBytes("UTF-8");
                String fraseDesencriptada = new String(Cifrar.decryptData(key1, frase2Bytes));
                System.out.println(fraseDesencriptada);



            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
