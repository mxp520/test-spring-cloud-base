package com.objcat.servicea.utils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.objcat.servicea.entity.StudentEntity;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ZXingCode {

    /**
     * 颜色
     */
    private static final int QRCOLOR = 0xFF000000;
    /**
     * 背景颜色
     */
    private static final int BGWHITE = 0xFFFFFFFF;


    /**
     * 生成二维码方法
     *
     * @param dataList 扫描二维码所获取的信息
     * @return
     */
    public static List<com.lowagie.text.Image> getQRCode(List<StudentEntity> dataList) {
        List<com.lowagie.text.Image> resList = new ArrayList<>();
        com.lowagie.text.Image imageByte =null;
        try {
            for(StudentEntity data:dataList){
                ZXingCode zp = new ZXingCode();
                BufferedImage bim = zp.generateQRCodeBufferedImage(data.toString(), BarcodeFormat.QR_CODE, 230, 230, zp.getDecodeHintType());
                //字节数组
                imageByte = zp.addTextForQRCode(bim, data.getBelowTextList());
                resList.add(imageByte);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return resList;
    }

    /**
     * @param bim       放在内存中图片
     * @param belowText 二维码下方显示文字
     * @return
     */
    public com.lowagie.text.Image addTextForQRCode(BufferedImage bim, List<String> belowText) {
        try {
            BufferedImage image = bim;
            if (belowText != null && !belowText.isEmpty()) {
                for(int i=0;i<belowText.size();i++){
                    BufferedImage outImage = new BufferedImage(230, 255+i*35, BufferedImage.TYPE_4BYTE_ABGR);
                    Graphics2D outg = outImage.createGraphics();
                    outg.drawImage(image, 0, 0, image.getWidth(), image.getHeight(), null);
                    outg.setColor(Color.BLACK);
                    //获得本地字体列表
//                String[] fonts = this.getFontName();
                    outg.setFont(new Font("SansSerif", Font.PLAIN, 18));
                    int strWidth = outg.getFontMetrics().stringWidth(belowText.get(i));
                    outg.drawString(belowText.get(i), 100 - strWidth / 2 + 8, image.getHeight() + (outImage.getHeight() - image.getHeight()) / 2 + 5);
                    outg.dispose();
                    outImage.flush();
                    image = outImage;
                }

            }
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            baos.flush();
            ImageIO.write(image, "png", baos);
            BufferedImage newBufferedImage = new BufferedImage(
                    image.getWidth(), image.getHeight(),
                    BufferedImage.TYPE_INT_RGB);
            newBufferedImage.createGraphics().drawImage(image, 0, 0,
                    Color.WHITE, null);
//            存放本地
//            ImageIO.write(newBufferedImage, "png", new File(CODEPATH + "SZ-" + System.currentTimeMillis() + "code.png"));
            com.lowagie.text.Image imageByte = com.lowagie.text.Image.getInstance(baos.toByteArray());
            baos.close();
            image.flush();
            return imageByte;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 绘制二维码，不带文字
     *
     * @param content       扫描内容
     * @param barcodeFormat 格式
     * @param width
     * @param height
     * @param hints         二维码属性设置
     * @return 放到内存中，后续再二维码下方添加文字
     */
    public BufferedImage generateQRCodeBufferedImage(String content, BarcodeFormat barcodeFormat, int width, int height, Map<EncodeHintType, ?> hints) {
        MultiFormatWriter multiFormatWriter = null;
        BitMatrix bm = null;
        BufferedImage image = null;
        try {
            multiFormatWriter = new MultiFormatWriter();
            bm = multiFormatWriter.encode(content, barcodeFormat, width, height, hints);
            int w = bm.getWidth();
            int h = bm.getHeight();
            image = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
            for (int x = 0; x < w; x++) {
                for (int y = 0; y < h; y++) {
                    image.setRGB(x, y, bm.get(x, y) ? QRCOLOR : BGWHITE);
                }
            }
        } catch (WriterException e) {
            e.printStackTrace();
        }
        return image;
    }

    /**
     * 设置二维码属性
     *
     * @return
     */
    public Map<EncodeHintType, Object> getDecodeHintType() {
        Map<EncodeHintType, Object> hints = new HashMap<EncodeHintType, Object>();
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
        hints.put(EncodeHintType.MARGIN, 0);
        hints.put(EncodeHintType.MAX_SIZE, 350);
        hints.put(EncodeHintType.MIN_SIZE, 100);
        return hints;
    }

    /**
     * 获得本地字体列表
     * @return 字体数组
     */
    public String[] getFontName(){
        GraphicsEnvironment e = GraphicsEnvironment.getLocalGraphicsEnvironment();
        String[] fontName = e.getAvailableFontFamilyNames();
        return fontName;
    }
}
