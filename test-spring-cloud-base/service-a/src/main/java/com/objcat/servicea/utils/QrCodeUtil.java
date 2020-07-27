package com.objcat.servicea.utils;


import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QrCodeUtil {

    private static final int QRCOLOR = 0xFF000000; // 默认是黑色
    private static final int BGWHITE = 0xFFFFFFFF; // 背景颜色
    private static final int WIDTH = 400; // 二维码宽
    private static final int HEIGHT = 400; // 二维码高

    // 用于设置QR二维码参数
    private static Map<EncodeHintType, Object> hints = new HashMap<EncodeHintType, Object>() {
        private static final long serialVersionUID = 1L;
        {
            put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);// 设置QR二维码的纠错级别（H为最高级别）具体级别信息
            put(EncodeHintType.CHARACTER_SET, "utf-8");// 设置编码方式
        }
    };

    // 生成带logo的二维码图片
    public static void drawLogoQRCode(File codeFile, String qrUrl, List<String> noteList) {
        try {
            List<com.lowagie.text.Image> imageList = new ArrayList<>();
            MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
            // 参数顺序分别为：编码内容，编码类型，生成图片宽度，生成图片高度，设置参数
            BitMatrix bm = multiFormatWriter.encode(qrUrl, BarcodeFormat.QR_CODE, WIDTH, HEIGHT, hints);
            BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);

            // 开始利用二维码数据创建Bitmap图片，分别设为黑（0xFFFFFFFF）白（0xFF000000）两色
            for (int x = 0; x < WIDTH; x++) {
                for (int y = 0; y < HEIGHT; y++) {
                    image.setRGB(x, y, bm.get(x, y) ? QRCOLOR : BGWHITE);
                }
            }

//            int width = image.getWidth();
//            int height = image.getHeight();
//            if (logoFile !=null && logoFile.exists()) {
//                // 构建绘图对象
//                Graphics2D g = image.createGraphics();
//                // 读取Logo图片
//                BufferedImage logo = ImageIO.read(logoFile);
//                // 开始绘制logo图片
//                g.drawImage(logo, width * 2 / 5, height * 2 / 5, width * 2 / 10, height * 2 / 10, null);
//                g.dispose();
//                logo.flush();
//            }
            // 自定义文本描述
            if (noteList != null && !noteList.isEmpty()) {
                // 新的图片，把带logo的二维码下面加上文字
                BufferedImage outImage = new BufferedImage(400, 445, BufferedImage.TYPE_4BYTE_ABGR);
                Graphics2D outg = outImage.createGraphics();
                // 画二维码到新的面板
                outg.drawImage(image, 0, 0, image.getWidth(), image.getHeight(), null);
                // 画文字到新的面板
                outg.setColor(Color.BLACK);
                outg.setFont(new Font("楷体", Font.BOLD, 30)); // 字体、字型、字号
                for(int i =0 ;i<noteList.size();i++){
                    int strWidth = outg.getFontMetrics().stringWidth(noteList.get(i));
                    BufferedImage outImage2 = new BufferedImage(400, 400+i*45, BufferedImage.TYPE_4BYTE_ABGR);
                    Graphics2D outg2 = outImage2.createGraphics();
                    outg2.drawImage(outImage, 0, 0, outImage.getWidth(), outImage.getHeight(), null);
                    outg2.setColor(Color.BLACK);
                    outg2.setFont(new Font("宋体", Font.BOLD, 30)); // 字体、字型、字号
                    outg2.drawString(noteList.get(i), 200 - strWidth / 2,outImage.getHeight() + (outImage2.getHeight() - outImage.getHeight()) / 32);
                    outg2.dispose();
                    outImage2.flush();
                    outImage = outImage2;
                }

                outg.dispose();
                outImage.flush();
                image = outImage;
            }

            image.flush();
            ImageIO.write(image, "png", codeFile); // TODO
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        File QrCodeFile = new File("C:\\123.png"); //二维码生成地址以及名称格式
        String url = "45011106220023,高中二年级3班,吴思雨"; // 扫描二维码进入网址
        List noteList = new ArrayList<String>();
        noteList.add("吴思雨 , 男");noteList.add("45011106220023");noteList.add("高中二年级3班");
        noteList.add("□视力表 □电脑验光");
        QrCodeUtil.drawLogoQRCode(QrCodeFile, url, noteList);
    }
}
