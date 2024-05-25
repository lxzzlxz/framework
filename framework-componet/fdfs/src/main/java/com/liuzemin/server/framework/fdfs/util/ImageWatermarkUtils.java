package com.liuzemin.server.framework.fdfs.util;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

/**
 * 图片添加水印工具类
 * 文字水印 图片水印 利用jdk ,不依赖第三方
 */
public class ImageWatermarkUtils {
    private static final Logger log = LoggerFactory.getLogger(ImageWatermarkUtils.class);

    // 水印透明度
    private static float alpha = 0.5F;
    private static String waterMark = "版权所有，不得转载";
    //旋转角度
    private static Integer degree = -30;
    private static Integer fontSize = 30;
    private static Font font = new Font("宋体", Font.BOLD, fontSize);
    private static Color color = new Color(210, 210, 210);

    public static void main(String[] args) {
        //文件输出流 即目标文件保存地址
        FileOutputStream outputStream = null;
        //    try {
        //     outputStream = new FileOutputStream("C:\\Users\\Administrator\\Desktop\\23333.jpg");
        //   } catch (FileNotFoundException e) {
        //       e.printStackTrace();
        //     }
        //源文件
        String srcImgPath = "C:\\Users\\fy\\Desktop\\22222.jpg";
        //   markImageByText(srcImgPath, outputStream);
    }

    //  public static byte[] markImageByText(String srcImgPath, FileOutputStream outputStream) {
    public static byte[] markImageByText(byte[] imgData) {
        InputStream is = new ByteArrayInputStream(imgData);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();

        try {
            // 1、源图片
            //   Image srcImg = ImageIO.read(new File(srcImgPath));
            Image srcImg = ImageIO.read(is);
            if(null ==srcImg){
                return null;
            }
            // 原图宽度
            int srcImgWidth = srcImg.getWidth(null);
            // 原图高度
            int srcImgHeight = srcImg.getHeight(null);
            BufferedImage buffImg = new BufferedImage(srcImg.getWidth(null), srcImg.getHeight(null), BufferedImage.TYPE_INT_RGB);
            // 2、得到画笔对象
            Graphics2D g = buffImg.createGraphics();
            // 3、设置对线段的锯齿状边缘处理
            g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            g.drawImage(srcImg.getScaledInstance(srcImg.getWidth(null), srcImg.getHeight(null), Image.SCALE_SMOOTH), 0, 0, null);
            // 4、设置水印旋转
            if (null != degree) {
                g.rotate(Math.toRadians(degree),
                        (double) buffImg.getWidth() / 2,
                        (double) buffImg.getHeight() / 2);
            }
            // 5、设置水印文字颜色
            g.setColor(color);
            // 6、设置水印文字Font
            g.setFont(font);
            // 7、设置水印文字透明度
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, alpha));
            // 间隔
            int split = fontSize * 5;
            // 文字占用的宽度
            int xWidth = getStrWidth(waterMark, fontSize);
            // x,y可以绘制的数量,多加一个补充空白
            int xCanNum = srcImgWidth / xWidth + 1;
            int yCanNum = srcImgHeight / fontSize + 1;
            // 8、第一参数->设置的内容，后面两个参数->文字在图片上的坐标位置(x,y) i是y轴, 文字默认在y坐标上面,所以这里初始化1
            for (int i = 1; i <= yCanNum; i++) {
                int y = fontSize * i + split * i;
                for (int j = 0; j < xCanNum; j++) {
                    int x = xWidth * j + split * j;
                    g.drawString(waterMark, x, y - (fontSize + split) * j);
                }
            }
            // 9、释放资源
            g.dispose();
            // 10、生成图片
            ImageIO.write(buffImg, "jpg", bos);
            byte[] bytes = bos.toByteArray();
            return bytes;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != is) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    /**
     * 获取字符串占用的宽度
     * <br>
     *
     * @param str      字符串
     * @param fontSize 文字大小
     * @return 字符串占用的宽度
     * @author
     */
    public static int getStrWidth(String str, int fontSize) {
        char[] chars = str.toCharArray();
        int fontSize2 = fontSize / 2;

        int width = 0;

        for (char c : chars) {
            int len = String.valueOf(c).getBytes().length;
            // 汉字为3,其余1
            // 可能还有一些特殊字符占用2等等,统统计为汉字
            if (len != 1) {
                width += fontSize;
            } else {
                width += fontSize2;
            }
        }

        return width;
    }

}
