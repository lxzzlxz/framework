package com.liuzemin.server.framework.model.utils.opencv;

import org.opencv.core.*;
import org.opencv.highgui.HighGui;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import java.util.ArrayList;
import java.util.List;

// 留下黑底绿轮廓
public class ContourDetection {
    static {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }

    public static void main(String[] args) {
        // 读取图像
        Mat image = Imgcodecs.imread("D:\\Users\\111.png");
        Mat grayImage = new Mat();
        Mat edges = new Mat();

        // 转换为灰度图像
        Imgproc.cvtColor(image, grayImage, Imgproc.COLOR_BGR2GRAY);
        // 使用Canny算法进行边缘检测
        Imgproc.Canny(grayImage, edges, 100, 200);

        // 检测轮廓
        List<MatOfPoint> contours = new ArrayList<>();
        Mat hierarchy = new Mat();
        Imgproc.findContours(edges, contours, hierarchy, Imgproc.RETR_TREE, Imgproc.CHAIN_APPROX_SIMPLE);

        // 绘制轮廓
        Mat contourImage = Mat.zeros(image.size(), image.type());
        for (MatOfPoint contour : contours) {
            Imgproc.drawContours(contourImage, contours, -1, new Scalar(0, 255, 0), 2);
        }

        // 保存轮廓图像
        Imgcodecs.imwrite("output/contours.jpg", contourImage);

        // 展示图像
        HighGui.imshow("contourImage", contourImage);
        HighGui.waitKey(0);
    }
}
