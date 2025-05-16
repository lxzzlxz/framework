package com.liuzemin.server.framework.model.utils;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import java.io.File;

public class OCRProcessor {
    static {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME); // 加载OpenCV本地库
        System.load("D:/Program Files (x86)/opencv/build/java/x64/opencv_java4100.dll");

    }

    public static String recognizeText(String imagePath) {
        ITesseract tesseract = new Tesseract();
        tesseract.setDatapath("C:/Program Files/Tesseract-OCR/tessdata");
        tesseract.setLanguage("chi_sim"); // 设置语言包

        try {
            // 1. 读取并预处理图片
            Mat src = Imgcodecs.imread(imagePath);
            Mat processed = preprocess(src);

            // 2. 保存预处理后的图片（可选，用于调试）
            Imgcodecs.imwrite("processed.jpg", processed);

            // 3. 执行OCR识别
            return tesseract.doOCR(new File("processed.jpg"));
        } catch (TesseractException e) {
            System.err.println("OCR错误: " + e.getMessage());
        }
        return null;
    }

    private static Mat preprocess(Mat src) {
        // 灰度化 → 二值化 → 降噪
        Mat gray = new Mat(), binary = new Mat(), denoised = new Mat();
        Imgproc.cvtColor(src, gray, Imgproc.COLOR_BGR2GRAY);
        Imgproc.threshold(gray, binary, 0, 255, Imgproc.THRESH_BINARY | Imgproc.THRESH_OTSU);
        Imgproc.GaussianBlur(binary, denoised, new Size(3, 3), 0);
        return denoised;
    }

    public static void main(String[] args) {
        String result = recognizeText("C:\\Users\\刘泽民\\Pictures\\Saved Pictures\\微信图片_20240723150509.png");
        if (result != null) {
            System.out.println("识别结果:\n" + result);
        }
    }
}
