package JCE;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.security.*;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.util.Arrays;

public class Cifrar {

    //Ejercicio 1.1
    public static SecretKey keygenKeyGeneration(int keySize) {
        SecretKey sKey = null;
        if ((keySize == 128) || (keySize == 192) || (keySize == 256)) {
            try {
                KeyGenerator kgen = KeyGenerator.getInstance("AES");
                kgen.init(keySize);
                sKey = kgen.generateKey();
            } catch (NoSuchAlgorithmException ex) {
                System.err.println("Generador no disponible.");
            }
        }
        return sKey;
    }

    //Ejercicio 1.2
    public static SecretKey passwordKeyGeneration(String text, int keySize) {
        SecretKey sKey = null;
        if ((keySize == 128) || (keySize == 192) || (keySize == 256)) {
            try {
                byte[] data = text.getBytes("UTF-8");
                MessageDigest md = MessageDigest.getInstance("SHA-256");
                byte[] hash = md.digest(data);
                byte[] key = Arrays.copyOf(hash, keySize / 8);
                sKey = new SecretKeySpec(key, "AES");
            } catch (Exception ex) {
                System.err.println("Error generant la clau:" + ex);
            }
        }
        return sKey;
    }

    //Ejercicio 1.3
    public static byte[] encryptData(SecretKey sKey, byte[] data) {
        byte[] encryptedData = null;
        try {
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, sKey);
            encryptedData = cipher.doFinal(data);
        } catch (Exception ex) {
            System.err.println("Error xifrant les dades: " + ex);
        }
        return encryptedData;
    }

    //Ejercicio 1.4
    public static byte[] decryptData(SecretKey sKey, byte[] data) throws BadPaddingException {
        byte[] decryptedData = null;
        try {
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, sKey);
            decryptedData = cipher.doFinal(data);
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }
        return decryptedData;
    }

    //--------------Práctica 5----------------
    //Ejercicio 1.1
    public static KeyPair randomGenerate(int len) {
        KeyPair keys = null;
        try {
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
            keyGen.initialize(len);
            keys = keyGen.genKeyPair();
        } catch (Exception ex) {
            System.err.println("Generador no disponible.");
        }
        return keys;
    }

    public static byte[] encryptData(byte[] data, PublicKey pub) {
        byte[] encryptedData = null;
        try {
            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding", "SunJCE");
            cipher.init(Cipher.ENCRYPT_MODE, pub);
            encryptedData = cipher.doFinal(data);
        } catch (Exception ex) {
            System.err.println("Error xifrant: " + ex);
        }
        return encryptedData;
    }

    public static byte[] decryptData(byte[] data, PrivateKey priv) {
        byte[] decryptedData = null;
        try {
            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding", "SunJCE");
            cipher.init(Cipher.DECRYPT_MODE, priv);
            decryptedData = cipher.doFinal(data);
        } catch (Exception ex) {
            System.err.println("Error desxifrant: " + ex);
        }
        return decryptedData;
    }

    //Ejercicio 1.2
    public static KeyStore loadKeyStore(String ksFile, String ksPwd) throws Exception {
        KeyStore ks = KeyStore.getInstance("PKCS12");
        File f = new File(ksFile);
        if (f.isFile()) {
            FileInputStream in = new FileInputStream(f);
            ks.load(in, ksPwd.toCharArray());
        }
        return ks;
    }

    //Ejercicio 1.3
    public static PublicKey getPublicKey(String fitxer) throws Exception {
        FileInputStream fis = new FileInputStream(fitxer);
        BufferedInputStream bis = new BufferedInputStream(fis);
        CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
        PublicKey publicKey = null;

        while (bis.available() > 0) {
            Certificate certificate = certificateFactory.generateCertificate(bis);
            publicKey = certificate.getPublicKey();
        }
        return publicKey;
    }

    //Ejercicio 1.4
    public static PublicKey getPublicKey(KeyStore ks, String alias, String pwMyKey) throws Exception {
        PublicKey publicKey = null;
        Key key = ks.getKey(alias, pwMyKey.toCharArray());
        if (key instanceof PrivateKey) {
            Certificate certificate = ks.getCertificate(alias);
            publicKey = certificate.getPublicKey();
        }
        return publicKey;
    }

    //Ejercicio 1.5
    public static byte[] signData(byte[] data, PrivateKey priv) {
        byte[] signature = null;

        try {
            Signature signer = Signature.getInstance("SHA1withRSA");
            signer.initSign(priv);
            signer.update(data);
            signature = signer.sign();
        } catch (Exception ex) {
            System.err.println("Error signant les dades: " + ex);
        }
        return signature;
    }

    //Ejercicio 1.6
    public static boolean validateSignature(byte[] data, byte[] signature, PublicKey pub) {
        boolean isValid = false;
        try {
            Signature signer = Signature.getInstance("SHA1withRSA");
            signer.initVerify(pub);
            signer.update(data);
            isValid = signer.verify(signature);
        } catch (Exception ex) {
            System.err.println("Error validant les dades: " + ex);
        }
        return isValid;
    }

    //Ejercicio 2.1
    public static byte[][] encryptWrappedData(byte[] data, PublicKey pub) {
        byte[][] encWrappedData = new byte[2][];
        try {
            //Generación de clave
            KeyGenerator kgen = KeyGenerator.getInstance("AES");
            kgen.init(128);
            //Clave simétrica
            SecretKey sKey = kgen.generateKey();

            //Encriptación de datos con la clave simétrica
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, sKey);
            byte[] encMsg = cipher.doFinal(data);

            //Envoltura de clave privada con la clave pública
            cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            cipher.init(Cipher.WRAP_MODE, pub);
            byte[] encKey = cipher.wrap(sKey);

            //Inserción de mensaje y clave cifrados en array bidimensional de bytes
            encWrappedData[0] = encMsg;
            encWrappedData[1] = encKey;
        } catch (Exception ex) {
            System.err.println("Ha succeït un error xifrant: " + ex);
        }
        //Retorno del mensaje y la clave cifrados
        return encWrappedData;
    }

    public static byte[] decryptWrappedData(byte[][] data, PrivateKey priv) {
        byte[] decWrappedData = null;
        try {
            //Desenvoltura de clave simétrica con la clave privada
            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            cipher.init(Cipher.UNWRAP_MODE, priv);
            Key decKey = cipher.unwrap(data[1], "AES", Cipher.SECRET_KEY);

            //Descrifrado de datos con la clave simétrica desenvuelta
            cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, decKey);
            byte[] decMsg = cipher.doFinal(data[0]);

            //Inserción de datos desencriptados en un array de bytes
            decWrappedData = decMsg;
        } catch (Exception ex) {
            System.err.println("Ha succeït un error desxifrant: " + ex);
        }
        //Retorno del mensaje descifrado
        return decWrappedData;
    }
}

