package com.liuzemin.server.framework.model.utils.opencv;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.highgui.HighGui;
import org.opencv.imgcodecs.Imgcodecs;

import java.io.File;

// 图形展示类
public class ImageDisplay {
    static {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }

    public static void main(String[] args) {
        String basePath = "D:\\Users";
        String fileName = "111.png";
        File file = new File(basePath, fileName); // 安全拼接路径

        // 验证文件是否存在
        if (!file.exists()) {
            System.out.println("错误：文件路径不存在！");
            return;
        }

        // 读取图像
        Mat image = Imgcodecs.imread(file.getAbsolutePath());
        if (image.empty()) {
            System.out.println("Could not open or find the image!");
            return;
        }

        // 显示图像
        HighGui.imshow("local image", image);
        // 等待按键
        HighGui.waitKey(0);
    }
}