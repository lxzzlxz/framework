package com.liuzemin.server.framework.fdfs.util;

import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

public class ResizeImageUtil {
    //每次压缩比率
    final static float reduceMultiple = 0.5f;


    /**
     * 通过BufferedImage图片流调整图片大小
     * 指定压缩后长宽
     */
    public BufferedImage resizeImage(BufferedImage originalImage, int targetWidth, int targetHeight) throws IOException {
        Image resultingImage = originalImage.getScaledInstance(targetWidth, targetHeight, Image.SCALE_AREA_AVERAGING);
        BufferedImage outputImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_RGB);
        outputImage.getGraphics().drawImage(resultingImage, 0, 0, null);
        return outputImage;
    }

    /**
     * 通过BufferedImage图片流调整图片大小
     *
     * @param originalImage
     * @param reduceMultiple 缩小倍数
     * @return
     * @throws IOException
     */
    public BufferedImage resizeImage(BufferedImage originalImage, float reduceMultiple) throws IOException {
        int width = (int) (originalImage.getWidth() * reduceMultiple);
        int height = (int) (originalImage.getHeight() * reduceMultiple);
        Image resultingImage = originalImage.getScaledInstance(width, height, Image.SCALE_AREA_AVERAGING);
        BufferedImage outputImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        outputImage.getGraphics().drawImage(resultingImage, 0, 0, null);
        return outputImage;
    }

    /**
     * 压缩图片到指定大小
     *
     * @param srcImgData
     * @return
     * @throws IOException
     */

    public static byte[] resizeImage(byte[] srcImgData) throws IOException {
        BufferedImage bi = ImageIO.read(new ByteArrayInputStream(srcImgData));
        int width = (int) (bi.getWidth() * reduceMultiple); // 源图宽度
        int height = (int) (bi.getHeight() * reduceMultiple); // 源图高度
        Image image = bi.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        BufferedImage tag = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics g = tag.getGraphics();
        g.setColor(Color.RED);
        g.drawImage(image, 0, 0, null); // 绘制处理后的图
        g.dispose();
        ByteArrayOutputStream bOut = new ByteArrayOutputStream();
        ImageIO.write(tag, "jpg", bOut);
        return bOut.toByteArray();
    }


    /**
     * BufferedImage图片流转byte[]数组
     */
    public byte[] imageToBytes(BufferedImage bImage) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            ImageIO.write(bImage, "jpg", out);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out.toByteArray();
    }


    /**
     * byte[]数组转BufferedImage图片流
     */
    private BufferedImage bytesToBufferedImage(byte[] ImageByte) {
        ByteArrayInputStream in = new ByteArrayInputStream(ImageByte);
        BufferedImage image = null;
        try {
            image = ImageIO.read(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }


    /**
     * 保存照片文件
     *
     * @param path 文件绝对路径
     * @param file 文件数据
     */
    public void saveFile(String path, MultipartFile file) {
        if (file != null && !file.isEmpty()) {
            //保存文件到对应位置
            File dir = new File(path);
            if (!dir.getParentFile().exists()) {
                dir.getParentFile().mkdirs();
            }
            // 压缩到小于指定文件大小200k
            double targetSize = 200 * 1024;
            try {
                //从MultipartFile 中获取 byte[]
                byte[] bytes = file.getBytes();
                System.out.println("头像图片压缩前大小：[{}]" + bytes.length);
                while (bytes.length > targetSize) {
                    bytes = resizeImage(bytes);
                }
                System.out.println("头像图片压缩后大小：[{}]" + bytes.length);
                BufferedImage newImage = bytesToBufferedImage(bytes);
                //图像缓冲区图片保存为图片文件(文件不存在会自动创建文件保存，文件存在会覆盖原文件保存)
                ImageIO.write(newImage, "jpg", new File(path));
            } catch (IOException e) {
                //抛出异常
            }
        }
    }

}
