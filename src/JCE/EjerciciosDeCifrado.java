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

        Path pathCifrar = Paths.get("C:\\Users\\Antonio\\resultado.csv");
        byte[] primerArchivo;

        {
            try {
                //Cifrar archivo con una clave de 128 bits con keygenKeyGeneration:
                primerArchivo = Files.readAllBytes(pathCifrar);
                FileWriter archivoCifrado = new FileWriter("C:\\Users\\Antonio\\archivocifrado.csv");
                archivoCifrado.write(new String(Cifrar.encryptData(key1, primerArchivo)));

                //Descifrar archivo con una clave de 128 bits con decryptData:
                Path pathDescifrar = Paths.get("C:\\Users\\Antonio\\archivocifrado.csv");
                byte[] segundoArchivo = pathCifrar.getBytes();
                FileWriter archivoDescifrado = new FileWriter("C:\\Users\\Antonio\\archivodescifrado.csv");
                archivoDescifrado.write(segundoArchivo);


            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
