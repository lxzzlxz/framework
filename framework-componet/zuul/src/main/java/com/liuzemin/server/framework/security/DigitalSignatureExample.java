package com.liuzemin.server.framework.security;

public class DigitalSignatureExample {
    public static void main(String[] args) throws Exception {
        /*String data = "Hello, world!";
        KeyPair keyPair = KeyPairGenerator.getInstance("DSA").generateKeyPair();
        Signature signature = Signature.getInstance("SHA1withDSA");
        signature.initSign(keyPair.getPrivate());
        signature.update(data.getBytes());
        byte[] signedData = signature.sign();
        signature.initVerify(keyPair.getPublic());
        signature.update(data.getBytes());
        boolean verified = signature.verify(signedData);
        System.out.println(verified);*/
        Test test = new Test();
        byte[] aa = test.resetPassword("Hello, world!");
        System.out.println(aa.toString());
    }
}
