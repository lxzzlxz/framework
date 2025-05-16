package com.liuzemin.server.framework.model.utils.opencv;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.highgui.HighGui;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

// 边缘检测
public class EdgeDetection {
    static {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }

    public static void main(String[] args) {
        // 读取图像
        Mat image = Imgcodecs.imread("D:\\Users\\111.png");
        Mat edges = new Mat();

        // 转换为灰度图像
        Mat grayImage = new Mat();
        Imgproc.cvtColor(image, grayImage, Imgproc.COLOR_BGR2GRAY);

        // 使用Canny算法进行边缘检测
        Imgproc.Canny(grayImage, edges, 100, 200);

        // 保存边缘检测结果
        Imgcodecs.imwrite("output/edges.jpg", edges);

        // 显示图像
        HighGui.imshow("image", edges);
        HighGui.waitKey(0);
    }
}
