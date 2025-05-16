package com.liuzemin.server.framework.model.utils.opencv;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.highgui.HighGui;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

// 图形灰度类
public class ImageConversion {
    static {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }
    public static void main(String[] args) {
        Mat image = Imgcodecs.imread("D:\\Users\\111.png");
        Mat grayImage = new Mat();
        // 转换头像为灰头像
        Imgproc.cvtColor(image, grayImage, Imgproc.COLOR_BGR2GRAY);

        // 保留灰度头像
        Imgcodecs.imwrite("output/gray_image.jpg", grayImage);

        // 展示灰度头像
        HighGui.imshow("grayImage", grayImage);
        HighGui.waitKey(0);
    }
}
