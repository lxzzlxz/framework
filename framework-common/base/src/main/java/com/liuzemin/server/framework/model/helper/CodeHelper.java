package com.liuzemin.server.framework.model.helper;

import java.util.Random;

public class CodeHelper {

    private static final int[] numberSeed = new int[]{0,1,2,3,4,5,6,7,8,9};

    private static final char[] charSeed = new char[]{'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z','A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z','0','1','2','3','4','5','6','7','8','9','0'};

    private static Random random = new Random();

    /**
     * 包含重复
     * @param count
     * @return
     */
    public static String getRandomNumberCode(int count){
        if(count <=0){
            return "";
        }
        String s = "";
        for(int i=0;i<count;i++){
            int ran = random.nextInt(numberSeed.length);
            s += numberSeed[ran];
        }
        return s;
    }

    /**
     * 不包含重复
     * @param count
     * @return
     */
    public static String getRandomNumberCode1(int count){
        if(count <=0 || count > numberSeed.length){
            return "";
        }
        String s = "";
        int[] seeds = numberSeed.clone();
        for(int i=count;i>0;i--){
            int ran = random.nextInt(i);

            int b = seeds[ran];
            seeds[ran] = seeds[i];
            seeds[i] = b;
            s += b;
        }
        return s;
    }

    /**
     * 包含重复
     * @param count
     * @return
     */
    public static String getRandomCharCode(int count){
        if(count <=0){
            return "";
        }
        String s = "";
        for(int i=0;i<count;i++){
            int ran = random.nextInt(charSeed.length);
            s += charSeed[ran];
        }
        return s;
    }

    /**
     * 不包含重复
     * @param count
     * @return
     */
    public static String getRandomCharCode1(int count){
        if(count <=0 || count > charSeed.length){
            return "";
        }
        String s = "";
        char[] seeds = charSeed.clone();
        for(int i=count;i>0;i--){
            int ran = random.nextInt(i);
            char b = seeds[ran];
            seeds[ran] = seeds[i];
            seeds[i] = b;
            s += b;
        }
        return s;
    }
}
