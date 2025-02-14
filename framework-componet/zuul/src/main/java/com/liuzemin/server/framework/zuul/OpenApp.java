package com.liuzemin.server.framework.zuul;

import java.io.IOException;

public class OpenApp {
    public static void main(String[] args) {
        String directoryPath = "C:\\Program Files (x86)\\Tencent\\WeChat";
        String softwareName = "WeChat.exe";
        try{
            //Runtime.getRuntime().exec("notepad.exe");
            Runtime.getRuntime().exec("cmd.exe /c start " + "\"\"" + " " + '\"' +directoryPath + "\\" + softwareName+ '\"');
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
