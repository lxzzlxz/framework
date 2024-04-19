package com.liuzemin.server.framework.security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;

public class Test {
    private byte[] pwd;
    private byte[] salt;

    public boolean checkPassword(String password) {
        return Arrays.equals(hashPassword(salt, password), pwd);
    }

    // 输入密码之后，经过该方法对密码进行加密，然后通过checkPassword方法，进行比较
    private static byte[] hashPassword(byte[] salt, String password) {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("SHA-512");
            md.update(salt);
            return md.digest(password.getBytes());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException();
        }
    }
    // 系统初始化时，调用该方法，为密码赋值
    public byte[] resetPassword(String newPassword) {
        salt = generateSalt();
        pwd = hashPassword(salt, newPassword);
        return pwd;
    }
    private static byte[] generateSalt() {
        try {
            SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
            byte[] salt = new byte[64];
            sr.nextBytes(salt);
            return salt;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException();
        }
    }
}
