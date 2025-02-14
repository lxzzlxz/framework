package com.liuzemin.server.framework.jump;

import java.io.IOException;

public class CommandJumpExample {
    public static void main(String[] args) throws IOException {
        String command = "start chrome";
        Runtime.getRuntime().exec(command);
    }
}