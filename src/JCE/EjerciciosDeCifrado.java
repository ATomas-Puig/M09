package JCE;

import sun.awt.image.BadDepthException;

import javax.crypto.BadPaddingException;
import javax.crypto.SecretKey;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class EjerciciosDeCifrado {

    public static void main(String[] args) {

        SecretKey key1 = Cifrar.keygenKeyGeneration(128);
        SecretKey key2 = Cifrar.passwordKeyGeneration("Ácido desoxirribonucleico", 128);
        SecretKey key3 = Cifrar.passwordKeyGeneration("Hola Jordi", 128);

        {

            try {
                //Ejercicio 1.5
                //Cifrar archivo con una clave de 128 bits con keygenKeyGeneration:
                Path pathCifrar = Paths.get("C:\\Users\\Antonio\\resultado.csv");
                byte[] primerArchivo = Files.readAllBytes(pathCifrar);
                byte[] primerArchivoCifrado = Cifrar.encryptData(key1, primerArchivo);
                FileOutputStream archivoCifrado = new FileOutputStream("C:\\Users\\Antonio\\archivocifrado.bin");
                archivoCifrado.write(primerArchivoCifrado);
                archivoCifrado.close();

                //Descifrar archivo con una clave de 128 bits con decryptData:
                Path pathDescifrar = Paths.get("C:\\Users\\Antonio\\archivocifrado.bin");
                byte[] segundoArchivo = Files.readAllBytes(pathDescifrar);
                byte[] segundoArchivoDescifrado = Cifrar.decryptData(key1, segundoArchivo);
                String stringDescifrado = new String(segundoArchivoDescifrado);
                FileWriter archivoDescifrado = new FileWriter("C:\\Users\\Antonio\\archivodescifrado.csv");
                archivoDescifrado.write(stringDescifrado);
                archivoDescifrado.close();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (BadPaddingException e) {
                e.printStackTrace();
            }

            try {
                //Ejercicio 1.6
                //Cifrar un archivo con una clave generada a partir de un password:
                Path pathCifrarContrasenya = Paths.get("C:\\Users\\Antonio\\resultado.csv");
                byte[] tercerArchivo = Files.readAllBytes(pathCifrarContrasenya);
                byte[] tercerArchivoCifrado = Cifrar.encryptData(key2, tercerArchivo);
                FileOutputStream archivoCifradoContrasenya = new FileOutputStream("C:\\Users\\Antonio\\archivocifradoconcontrasenya.bin");
                archivoCifradoContrasenya.write(tercerArchivoCifrado);
                archivoCifradoContrasenya.close();

                //Descifrar un archivo con una clave generada a partir de un password:
                Path pathDescifrarContrasenya = Paths.get("C:\\Users\\Antonio\\archivocifradoconcontrasenya.bin");
                byte[] cuartoArchivo = Files.readAllBytes(pathDescifrarContrasenya);
                byte[] cuartoArchivoDescifrado = Cifrar.decryptData(key2, cuartoArchivo);
                String stringDescifradoContrasenya = new String(cuartoArchivoDescifrado);
                FileWriter archivoDescifradoContrasenya = new FileWriter("C:\\Users\\Antonio\\archivodescifradoconcontrasenya.csv");
                archivoDescifradoContrasenya.write(stringDescifradoContrasenya);
                archivoDescifradoContrasenya.close();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (BadPaddingException e) {
                e.printStackTrace();
            }

            //Ejercicio 1.7
            //Probar algunos de los métodos de la clase SecretKey:
            System.out.println(key1.getAlgorithm());
            System.out.println(key2.getEncoded());
            System.out.println(key3.getFormat());
            System.out.println(key1.getClass());
            System.out.println(key2.hashCode());
            System.out.println(key3.toString());

            try {
                //Ejercicio 1.8
                //Descifrar el archivo del ejercicio 1.6 con una palabra clave incorrecta:
                Path pathDescifrarContrasenyaIncorrecta = Paths.get("C:\\Users\\Antonio\\archivocifradoconcontrasenya.bin");
                byte[] quintoArchivo = Files.readAllBytes(pathDescifrarContrasenyaIncorrecta);
                byte[] quintoArchivoDescifrado = Cifrar.decryptData(key3, quintoArchivo);
                String stringDescifradoContrasenyaIncorrecta = new String(quintoArchivoDescifrado);
                FileWriter archivoDescifradoContrasenyaIncorrecta = new FileWriter("C:\\Users\\Antonio\\archivodescifradoconcontrasenya.csv");
                archivoDescifradoContrasenyaIncorrecta.write(stringDescifradoContrasenyaIncorrecta);
                archivoDescifradoContrasenyaIncorrecta.close();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (BadPaddingException e) {
                System.out.println("Contraseña incorrecta.");
            }

            //Ejercicio 2
            //Descifrar un texto escondido a partir de una contraseña contenida en un archivo:*/

            try {
                Path textAmagat = Paths.get("C:\\Users\\Antonio\\textamagat");
                byte[] textAmagatCifrado = Files.readAllBytes(textAmagat);
                FileReader reader = new FileReader("C:\\Users\\Antonio\\clausA4.txt");
                BufferedReader bfReader = new BufferedReader(reader);
                String line = bfReader.readLine();

                while (line != null) {

                    try {
                        SecretKey key = Cifrar.passwordKeyGeneration(line, 128);
                        byte[] textAmagatDescifrado = Cifrar.decryptData(key, textAmagatCifrado);
                        String texto = new String(textAmagatDescifrado);
                        System.out.println(texto);
                        System.out.println(line);
                        line = null;
                    } catch (BadPaddingException e) {
                        line = bfReader.readLine();
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }


            // } catch (Exception e) {
            //   e.printStackTrace();
            // }
        }

    }
}
