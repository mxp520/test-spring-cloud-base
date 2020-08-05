package com.objcat.servicea.utils;


import com.lowagie.text.*;
import com.lowagie.text.rtf.RtfWriter2;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

public class CreateWord {


    /**
     *
     * @param imageList 图片字节流集合
     * @param response 返回word
     * @throws DocumentException 文档错误抛出
     * @throws IOException 输入输出错误抛出
     */
    public static void createDocContext(List<Image> imageList,HttpServletResponse response) throws DocumentException, IOException {
        File file1 = new File("TwoCodeImage.doc");
        Document document = new Document();

        // 建立一个书写器(Writer)与document对象关联，通过书写器(Writer)可以将文档写入到磁盘中
        RtfWriter2.getInstance(document, new FileOutputStream(file1));
        document.open();
        Paragraph title = new Paragraph("图书索书号二维码");
        //设置标题格式对齐方式
        title.setAlignment(Element.ALIGN_CENTER);
        document.add(title);
        // 设置 Table 表格
        //设置表格，将图片加到表格中进行方便定位
        Table aTable = new Table(4);
        // 设置每列所占比例
        // 占页面宽度 90%
        aTable.setWidth(100);
        // 自动填满
        // aTable.setAutoFillEmptyCells(true);
        //这里是imagelist集合，就是图片字节流的集合，图片从流中去获取放到word中
        for (int i = 0; i < imageList.size(); i++) {
            //设置图片等比例缩小
            imageList.get(i).scalePercent(55);
            aTable.addCell(new Cell(imageList.get(i)));
        }
        document.add(aTable);
        document.add(new Paragraph("\n"));
        System.out.println("word----success");
        document.close();

        //响应浏览器 返回下载
        response.setContentType("applicaiton/x-download");
        response.addHeader("Content-Disposition", "attachment;filename=" + "TwoCodeImage.doc");
        InputStream is = null;
        OutputStream os = null;
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        is = new FileInputStream(new File("TwoCodeImage.doc"));
        bis = new BufferedInputStream(is);
        os = response.getOutputStream();
        bos = new BufferedOutputStream(os);
        byte[] b = new byte[1024];
        int len = 0;
        while ((len = bis.read(b)) != -1) {
            bos.write(b, 0, len);
        }
        bis.close();
        is.close();
        bos.close();
        os.close();
        //删除本地临时文件
        file1.delete();
    }


    public static void createPDFContext(List<Image> imageList,HttpServletResponse response) throws Exception {
        File file1 = new File("TwoCodeImage.pdf");
        com.lowagie.text.Document document = new com.lowagie.text.Document();// 构建文档对象

        // 建立一个书写器(Writer)与document对象关联，通过书写器(Writer)可以将文档写入到磁盘中
        com.lowagie.text.pdf.PdfWriter.getInstance(document, new FileOutputStream(file1));
        document.open();

        com.lowagie.text.Paragraph paragraph  = new com.lowagie.text.Paragraph();
        paragraph.add("标题");
        //设置标题格式对齐方式
        paragraph.setAlignment(Element.ALIGN_CENTER);
        document.add(paragraph);
        // 设置 Table 表格
        //设置表格，将图片加到表格中进行方便定位
        Table aTable = new Table(4);


        // 设置每列所占比例
        // 占页面宽度 90%
        aTable.setWidth(90);
        // 自动填满
        // aTable.setAutoFillEmptyCells(true);
        //这里是imagelist集合，就是图片字节流的集合，图片从流中去获取放到word中
        for (int i = 0; i < imageList.size(); i++) {
            //设置图片等比例缩小
            imageList.get(i).scalePercent(45);
            Cell cell = new Cell(imageList.get(i));
            cell.setHorizontalAlignment(1);//设备内容居中
            aTable.addCell(cell);
        }
        document.add( aTable);
        document.add(new com.lowagie.text.Paragraph("\n"));
        System.out.println("word----success");
        document.close();

        //响应浏览器 返回下载
        response.setContentType("applicaiton/x-download");
        response.addHeader("Content-Disposition", "attachment;filename=" + "TwoCodeImage.pdf");
        InputStream is = null;
        OutputStream os = null;
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        is = new FileInputStream(new File("TwoCodeImage.pdf"));
        bis = new BufferedInputStream(is);
        os = response.getOutputStream();
        bos = new BufferedOutputStream(os);
        byte[] b = new byte[1024];
        int len = 0;
        while ((len = bis.read(b)) != -1) {
            bos.write(b, 0, len);
        }
        bis.close();
        is.close();
        bos.close();
        os.close();
        //删除本地临时文件
        file1.delete();
    }
}
