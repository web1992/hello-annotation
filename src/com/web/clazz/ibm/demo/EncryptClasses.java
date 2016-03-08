package com.web.clazz.ibm.demo;
// EncryptClasses.java

import java.security.*;
import javax.crypto.*;
import javax.crypto.spec.*;

public class EncryptClasses {
    static public void main(String args[]) throws Exception {
        String keyFilename = args[0];
        String algorithm = "DES";

        // �����ܳ�
        SecureRandom sr = new SecureRandom();
        byte rawKey[] = Util.readFile(keyFilename);
        DESKeySpec dks = new DESKeySpec(rawKey);
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(algorithm);
        SecretKey key = keyFactory.generateSecret(dks);

        // ��������ʵ�ʼ��ܲ�����Cipher����
        Cipher ecipher = Cipher.getInstance(algorithm);
        ecipher.init(Cipher.ENCRYPT_MODE, key, sr);

        // ������������ָ����ÿһ����
        for (int i = 1; i < args.length; ++i) {
            String filename = args[i];

            // �������ļ�
            byte classData[] = Util.readFile(filename);

            // ����
            byte encryptedClassData[] = ecipher.doFinal(classData);

            // ������ܺ������
            Util.writeFile(filename, encryptedClassData);

            System.out.println("Encrypted " + filename);
        }
    }
}