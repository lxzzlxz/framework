package com.liuzemin.server.framework.model.utils.opencv;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.highgui.HighGui;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

// 图像平滑(模糊头像)
public class ImageSmoothing {
    static {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }

    public static void main(String[] args) {
        // 读取图像
        Mat img = Imgcodecs.imread("D:\\Users\\111.png");
        Mat smoothImage = new Mat();

        // 使用高斯模糊
        Imgproc.GaussianBlur(img, smoothImage, new org.opencv.core.Size(15, 15),0);

        // 保存平滑后的头像
        Imgcodecs.imwrite("output/smoothed_image.jpg", smoothImage);

        // 展示平滑图像
        HighGui.imshow("smoothimg", smoothImage);
        HighGui.waitKey(0);
    }
}
