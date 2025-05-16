package com.liuzemin.server.framework.model.utils;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;

/**
 * 记得把lib目录中的opencv-4100.jar添加到项目的外部jar
 */
public class Test {
    /*static {
        System.load("D:/Program Files (x86)/opencv/build/java/x64/opencv_java4100.dll");
    }*/
    static {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }


    public static void main(String[] args) {
		Mat mat = Imgcodecs.imread("C:\\Users\\微信图片_20240723150509.png");
        if (mat.empty()) {
            System.out.println("load image faild");
            return;
        }
        System.out.println("image size:"+mat.size());
    }

}
