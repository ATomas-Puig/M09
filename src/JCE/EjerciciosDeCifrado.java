package JCE;

import sun.awt.image.BadDepthException;

import javax.crypto.BadPaddingException;
import javax.crypto.SecretKey;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.KeyPair;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.CertificateException;
import java.util.Scanner;

public class EjerciciosDeCifrado {

    public static void main(String[] args) {

        SecretKey key1 = Cifrar.keygenKeyGeneration(128);
        SecretKey key2 = Cifrar.passwordKeyGeneration("Ácido desoxirribonucleico", 128);
        SecretKey key3 = Cifrar.passwordKeyGeneration("Hola Jordi", 128);

        {

            try {
                //Ejercicio 1.5
                //Cifrar archivo con una clave de 128 bits con keygenKeyGeneration:
                System.out.println("Práctica 4 - Ejercicio 1.5:");
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
                System.out.println();
                System.out.println("Práctica 4 - Ejercicio 1.6:");
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
            System.out.println();
            System.out.println("Práctica 4 - Ejercicio 1.7:");
            System.out.println(key1.getAlgorithm());
            System.out.println(key2.getEncoded());
            System.out.println(key3.getFormat());
            System.out.println(key1.getClass());
            System.out.println(key2.hashCode());
            System.out.println(key3.toString());

            try {
                //Ejercicio 1.8
                //Descifrar el archivo del ejercicio 1.6 con una palabra clave incorrecta:
                System.out.println();
                System.out.println("Práctica 4 - Ejercicio 1.8:");
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
            System.out.println();
            System.out.println("Práctica 4 - Ejercicio 2:");
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

        }

        //------------------Práctica 5 -------------------------
        //Ejercicio 1.1.i
        System.out.println();
        System.out.println("Práctica 5 - Ejercicio 1.1.i:");
        KeyPair keyPair = Cifrar.randomGenerate(1024);
        byte[] stringClear = "Ésta es una prueba de cifrado con par de claves".getBytes();
        byte[] stringCifrado = Cifrar.encryptData(stringClear, keyPair.getPublic());
        System.out.println("Frase encriptada: " + new String(stringCifrado));
        byte[] stringDescifrado = Cifrar.decryptData(stringCifrado, keyPair.getPrivate());
        System.out.println("Frase desencriptada: " + new String(stringDescifrado));

        //Ejercicio 1.1.ii
        System.out.println();
        System.out.println("Práctica 5 - Ejercicio 1.1.ii:");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Introduce la frase que quieras cifrar:");
        byte[] fraseClear = scanner.nextLine().getBytes();
        byte[] fraseCifrada = Cifrar.encryptData(fraseClear, keyPair.getPublic());
        System.out.println("Frase encriptada: " + new String(fraseCifrada));
        byte[] fraseDescifrada = Cifrar.decryptData(fraseCifrada, keyPair.getPrivate());
        System.out.println("Frase desencriptada: " + new String(fraseDescifrada));

        //Ejercicio 1.1.iii
        System.out.println();
        System.out.println("Práctica 5 - Ejercicio 1.1.iii:");
        System.out.println("Información de la clave pública:");
        System.out.println(keyPair.getPublic().getAlgorithm());
        System.out.println(keyPair.getPublic().getEncoded());
        System.out.println(keyPair.getPublic().getClass());
        System.out.println(keyPair.getPublic().getFormat());
        System.out.println("Información de la clave privada:");
        System.out.println(keyPair.getPrivate().getAlgorithm());
        System.out.println(keyPair.getPrivate().getEncoded());
        System.out.println(keyPair.getPrivate().getClass());
        System.out.println(keyPair.getPrivate().getFormat());

        //Ejercicio 1.2.i
        System.out.println();
        System.out.println("Práctica 5 - Ejercicio 1.2.i:");
        try {
            KeyStore keyStore = Cifrar.loadKeyStore("C:\\Users\\Antonio\\keystore_nickname.ks", "Hola87");
            //Ejercicio 1.2.i.1
            System.out.println("Práctica 5 - Ejercicio 1.2.i.1 - Tipo de Keystore:");
            System.out.println(keyStore.getType());
            //Ejercicio 1.2.i.2
            System.out.println("Práctica 5 - Ejercicio 1.2.i.2 - Medida del Keystore:");
            System.out.println(keyStore.size());
            //Ejercicio 1.2.i.1
            System.out.println("Práctica 5 - Ejercicio 1.2.i.3 - Alias dentro del Keystore:");
            System.out.println(keyStore.aliases());
            //Ejercicio 1.2.i.1
            System.out.println("Práctica 5 - Ejercicio 1.2.i.4 - Certificado de una de las claves:");
            System.out.println(keyStore.getCertificate("lamevaclaum9"));
            //Ejercicio 1.2.i.1
            System.out.println("Práctica 5 - Ejercicio 1.2.i.5 - Algoritmo de cifrado de una de las claves:");
            System.out.println(keyStore.getCertificate("lamevaclaum9").getPublicKey().getAlgorithm());
        } catch (Exception e) {
            e.printStackTrace();
        }

        //Ejercicio 1.2.ii
        System.out.println();
        System.out.println("Práctica 5 - Ejercicio 1.2.ii:");
        try {
            SecretKey key4 = Cifrar.keygenKeyGeneration(128);
            KeyStore keyStore = Cifrar.loadKeyStore("C:\\Users\\Antonio\\keystore_nickname.ks", "Hola87");
            KeyStore.ProtectionParameter protectionParameter = new KeyStore.PasswordProtection("Hola87".toCharArray());
            KeyStore.SecretKeyEntry secretKeyEntry = new KeyStore.SecretKeyEntry(key4);
            keyStore.setEntry("Ejercicio 1.2.ii",secretKeyEntry,protectionParameter);
            FileOutputStream fileOutputStream = new FileOutputStream("C:\\Users\\Antonio\\keystore_nickname.ks");
            keyStore.store(fileOutputStream, "Hola87".toCharArray());
        } catch (Exception e) {
            e.printStackTrace();
        }

        //Ejercicio 1.3
        System.out.println();
        System.out.println("Práctica 5 - Ejercicio 1.3:");
        try {
            System.out.println(Cifrar.getPublicKey("C:\\Users\\Antonio\\antonio.crt"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        //Ejercicio 1.4
        System.out.println();
        System.out.println("Práctica 5 - Ejercicio 1.4:");
        try {
            KeyStore keyStore = Cifrar.loadKeyStore("C:\\Users\\Antonio\\keystore_nickname.ks", "Hola87");
            System.out.println(Cifrar.getPublicKey(keyStore, "lamevaclaum9", "Hola87"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        //Ejercicio 1.5
        System.out.println();
        System.out.println("Práctica 5 - Ejercicio 1.5:");
        PrivateKey privateKey = keyPair.getPrivate();
        byte[] firma = null;
        try{
            firma = Cifrar.signData("Hola amigos".getBytes(), privateKey);
            System.out.println(new String(firma));
        }catch (Exception e){
            e.printStackTrace();
        }

        //Ejercicio 1.6
        System.out.println();
        System.out.println("Práctica 5 - Ejercicio 1.6:");
        PublicKey publicKey = keyPair.getPublic();
        try {
            System.out.println("¿Es válida la firma anterior? " + Cifrar.validateSignature("Hola amigos".getBytes(), firma, publicKey));
        }catch (Exception e){
            e.printStackTrace();
        }

        //Ejercicio 2.2
        System.out.println();
        System.out.println("Práctica 5 - Ejercicio 2.2:");
        String mensajeACifrar = "WARNING: Este es un mensaje cifrado.";
        byte[][] mensajeCifrado = Cifrar.encryptWrappedData(mensajeACifrar.getBytes(),keyPair.getPublic());
        String mensajeDescifrado = new String(Cifrar.decryptWrappedData(mensajeCifrado,keyPair.getPrivate()));
        System.out.println(mensajeDescifrado);
    }
}
