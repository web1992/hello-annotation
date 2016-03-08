package com.web.clazz.ibm.demo;

import java.io.*;
import java.security.*;
import java.lang.reflect.*;
import javax.crypto.*;
import javax.crypto.spec.*;

public class DecryptStart extends ClassLoader {
    // ��Щ�����ڹ��캯�������ã�
    // �Ժ�loadClass()�������������ǽ�����
    private SecretKey key;
    private Cipher cipher;

    // ���캯�������ý�������Ҫ�Ķ���
    public DecryptStart(SecretKey key) throws GeneralSecurityException,
            IOException {
        this.key = key;

        String algorithm = "DES";
        SecureRandom sr = new SecureRandom();
        System.err.println("[DecryptStart: creating cipher]");
        cipher = Cipher.getInstance(algorithm);
        cipher.init(Cipher.DECRYPT_MODE, key, sr);
    }

    // main���̣�����Ҫ����������ܳף�����DecryptStart��
    // ʵ�������������ǵĶ���ClassLoader��
    // ���ú�ClassLoader�Ժ���������װ��Ӧ��ʵ����
    // �������ͨ��Java Reflection API����Ӧ��ʵ����main����
    static public void main(String args[]) throws Exception {
        String keyFilename = args[0];
        String appName = args[1];

        // ��Щ�Ǵ��ݸ�Ӧ�ñ���Ĳ���
        String realArgs[] = new String[args.length - 2];
        System.arraycopy(args, 2, realArgs, 0, args.length - 2);

        // ��ȡ�ܳ�
        System.err.println("[DecryptStart: reading key]");
        byte rawKey[] = Util.readFile(keyFilename);
        DESKeySpec dks = new DESKeySpec(rawKey);
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        SecretKey key = keyFactory.generateSecret(dks);

        // �������ܵ�ClassLoader
        DecryptStart dr = new DecryptStart(key);

        // ����Ӧ�������һ��ʵ��
        // ͨ��ClassLoaderװ����
        System.err.println("[DecryptStart: loading " + appName + "]");
        Class clasz = dr.loadClass(appName);

        // ���ͨ��Reflection API����Ӧ��ʵ��
        // ��main()����

        // ��ȡһ����main()������
        String proto[] = new String[1];
        Class mainArgs[] = {(new String[1]).getClass()};
        Method main = clasz.getMethod("main", mainArgs);

        // ����һ������main()��������������
        Object argsArray[] = {realArgs};
        System.err.println("[DecryptStart: running " + appName + ".main()]");

        // ����main()
        main.invoke(null, argsArray);
    }

    public Class loadClass(String name, boolean resolve)
            throws ClassNotFoundException {
        try {
            // ����Ҫ������Class����
            Class clasz = null;

            // ����Ĳ���1��������Ѿ���ϵͳ����֮��
            // ���ǲ����ٴ�װ����
            clasz = findLoadedClass(name);

            if (clasz != null)
                return clasz;

            // �����Ƕ��Ʋ���
            try {
                // ��ȡ�������ܵ����ļ�
                byte classData[] = Util.readFile(name + ".class");

                if (classData != null) {
                    // ����...
                    byte decryptedClassData[] = cipher.doFinal(classData);

                    // ... �ٰ���ת����һ����
                    clasz = defineClass(name, decryptedClassData,
                            0, decryptedClassData.length);
                    System.err.println("[DecryptStart: decrypting class " + name + "]");
                }
            } catch (FileNotFoundException fnfe) {
            }

            // ����Ĳ���2���������û�гɹ�
            // ���ǳ�����Ĭ�ϵ�ClassLoaderװ����
            if (clasz == null)
                clasz = findSystemClass(name);

            // ����Ĳ���3�����б�Ҫ����װ����ص���
            if (resolve && clasz != null)
                resolveClass(clasz);

            // ���෵�ظ�������
            return clasz;
        } catch (IOException ie) {
            throw new ClassNotFoundException(ie.toString()
            );
        } catch (GeneralSecurityException gse) {
            throw new ClassNotFoundException(gse.toString()
            );
        }
    }
}
