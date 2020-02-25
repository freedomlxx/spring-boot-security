package com.lee.security.test;

import org.jasypt.util.text.BasicTextEncryptor;

public class JasyptTest {

    public static void main(String[] args) {
        BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
        textEncryptor.setPassword("shiro@jasypt");
        encrypt(textEncryptor);
        decrypt(textEncryptor,"zWBvbRCRbqg1NzD3c10vpg==");
        decrypt(textEncryptor,"AWP+QIn5FHNszYV/ZFYV/g==");
    }
    private static void encrypt(BasicTextEncryptor textEncryptor) {

        String userName = textEncryptor.encrypt("root");
        System.out.println(userName);
        String password = textEncryptor.encrypt("123456");
        System.out.println(password);
    }

    private static void decrypt(BasicTextEncryptor textEncryptor, String obj) {
        String oldValue = textEncryptor.decrypt(obj);
        System.out.println(oldValue);
    }
}
