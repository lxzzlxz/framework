package com.liuzemin.server.framework.zuul;
import java.io.File;

// 寻找exe文件
public class ExeFinder {
    public static void searchForExe(File directory, String exeName) {
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    searchForExe(file, exeName);
                } else {
                    if (file.getName().toLowerCase().endsWith("WeChat.exe")) {
                        System.out.println("Found .exe file: " + file.getAbsolutePath());
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        File startDirectory = new File("C:\\"); // 更改为你想要开始搜索的目录
        String exeName = "WeChat.exe"; // 更改为你想要搜索的.exe文件名
        searchForExe(startDirectory, exeName);
    }
}
