package lk.ijse.controller;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;
import java.util.Base64;

public class Main {

    public static void main(String[] args) {
        try {
            String secretKey = "1234567891234567";
            String originalText = "Kavindu";

            // Encryption
            String encryptedText = encrypt(originalText, secretKey);
            System.out.println("Encrypted Text: " + encryptedText);

            // Decryption
            String decryptedText = decrypt(encryptedText, secretKey);
            System.out.println("Decrypted Text: " + decryptedText);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String encrypt(String plainText, String secretKey) throws Exception {
        SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getBytes(), "AES");
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");

        // Generate a random IV
        byte[] ivBytes = new byte[16];
        SecureRandom random = new SecureRandom();
        random.nextBytes(ivBytes);
        IvParameterSpec iv = new IvParameterSpec(ivBytes);

        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, iv);
        byte[] encryptedBytes = cipher.doFinal(plainText.getBytes());

        // Combine IV and encrypted data
        byte[] combinedBytes = new byte[ivBytes.length + encryptedBytes.length];
        System.arraycopy(ivBytes, 0, combinedBytes, 0, ivBytes.length);
        System.arraycopy(encryptedBytes, 0, combinedBytes, ivBytes.length, encryptedBytes.length);

        return Base64.getEncoder().encodeToString(combinedBytes);
    }

    public static String decrypt(String encryptedText, String secretKey) throws Exception {
        SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getBytes(), "AES");
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");

        // Extract IV from the combined data
        byte[] combinedBytes = Base64.getDecoder().decode(encryptedText);
        byte[] ivBytes = new byte[16];
        byte[] encryptedBytes = new byte[combinedBytes.length - 16];

        System.arraycopy(combinedBytes, 0, ivBytes, 0, 16);
        System.arraycopy(combinedBytes, 16, encryptedBytes, 0, encryptedBytes.length);

        IvParameterSpec iv = new IvParameterSpec(ivBytes);
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, iv);
        byte[] decryptedBytes = cipher.doFinal(encryptedBytes);

        return new String(decryptedBytes);
    }
}
