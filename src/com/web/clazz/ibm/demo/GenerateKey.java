package com.web.clazz.ibm.demo;// GenerateKey.java

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.SecureRandom;

public class GenerateKey {
    static public void main(String args[]) throws Exception {
        String keyFilename = args[0];
        String algorithm = "DES";

        // �����ܳ�
        SecureRandom sr = new SecureRandom();
        KeyGenerator kg = KeyGenerator.getInstance(algorithm);
        kg.init(sr);
        SecretKey key = kg.generateKey();

        // ���ܳ����ݱ��浽�ļ�
        Util.writeFile(keyFilename, key.getEncoded());
    }
}

