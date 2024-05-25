package com.liuzemin.server.framework.model.utils;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.RegionUtil;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.net.URLEncoder;
import java.util.*;

public class ExportExcelUtil<T> {

    public static <T> void export(HttpServletResponse response, String title, String[] headers,
                                  Collection<T> dataList) throws Exception {

        // 设置请求
        response.setContentType("application/application/vnd.ms-excel");
        response.setHeader("Content-disposition", "attachment;filename=" + URLEncoder.encode(title + ".xlsx", "UTF-8"));
        // 创建一个Workbook，对应一个Excel文件
        SXSSFWorkbook wb = new SXSSFWorkbook();

        // 设置标题样式
        CellStyle titleStyle = wb.createCellStyle();

        // 设置单元格边框样式
        titleStyle.setBorderTop(BorderStyle.THIN);// 上边框 细边线
        titleStyle.setBorderBottom(BorderStyle.THIN);// 下边框 细边线
        titleStyle.setBorderLeft(BorderStyle.THIN);// 左边框 细边线
        titleStyle.setBorderRight(BorderStyle.THIN);// 右边框 细边线
        // 设置单元格对齐方式
        titleStyle.setAlignment(HorizontalAlignment.CENTER); //居中

        // 设置字体样式
        Font titleFont = wb.createFont();
        titleFont.setFontHeightInPoints((short) 12); // 字体高度
        titleFont.setFontName("黑体"); // 字体样式
        titleStyle.setFont(titleFont);


        // 在Workbook中添加一个sheet,对应Excel文件中的sheet
        Sheet sheet = wb.createSheet(title);
        // 标题数组
        String[] titleArray = new String[headers.length];
        // 字段名数组
        String[] fieldArray = new String[headers.length];
        for (int i = 0; i < headers.length; i++) {
            String[] tempArray = headers[i].split("#");// 临时数组 分割#
            titleArray[i] = tempArray[0];
            fieldArray[i] = tempArray[1];
        }
        // 在sheet中添加标题行
        Row row = sheet.createRow((int) 0);// 行数从0开始

        Cell titleCell = row.createCell(0);
        titleCell.setCellValue(title);
        titleCell.setCellStyle(titleStyle);
        CellRangeAddress region = new CellRangeAddress(0, 0, 0, headers.length);
        sheet.addMergedRegion(region);
        setBorderStyle(BorderStyle.THIN, region, sheet);


        // 数据样式 因为标题和数据样式不同 需要分开设置 不然会覆盖
        CellStyle dataStyle = wb.createCellStyle();
        // 设置单元格边框样式
        dataStyle.setBorderTop(BorderStyle.THIN);// 上边框 细边线
        dataStyle.setBorderBottom(BorderStyle.THIN);// 下边框 细边线
        dataStyle.setBorderLeft(BorderStyle.THIN);// 左边框 细边线
        dataStyle.setBorderRight(BorderStyle.THIN);// 右边框 细边线
        // 设置单元格对齐方式
        dataStyle.setAlignment(HorizontalAlignment.CENTER); //居中
        // 设置数据字体
        Font dataFont = wb.createFont();
        dataFont.setFontHeightInPoints((short) 12); // 字体高度
        dataFont.setFontName("宋体"); // 字体
        dataStyle.setFont(dataFont);
        // 在sheet中添加标题行
        Row row1 = sheet.createRow((int) 1);// 行数从0开始
        Cell sequenceCell1 = row1.createCell(0);// cell列 从0开始 第一列添加序号
        sequenceCell1.setCellValue("序号");
        sequenceCell1.setCellStyle(titleStyle);

        // 为标题行赋值
        for (int i = 0; i < titleArray.length; i++) {
            Cell titleCell1 = row1.createCell(i + 1);// 0号位被序号占用，所以需+1
            titleCell1.setCellValue(titleArray[i]);
            titleCell1.setCellStyle(titleStyle);
        }

        // 遍历集合数据，产生数据行
        Iterator<T> it = dataList.iterator();
        int index = 1;
        while (it.hasNext()) {
            index++;// 0号位被占用 所以+1
            row = sheet.createRow(index);
            // 为序号赋值
            Cell sequenceCellValue = row.createCell(0);// 序号值永远是第0列
            sequenceCellValue.setCellValue(index - 1);
            sequenceCellValue.setCellStyle(dataStyle);
            T t = (T) it.next();
            Class<? extends Object> tCls = t.getClass();// 泛型为Object以及所有Object的子类
            // 利用反射，根据传过来的字段名数组，动态调用对应的getXxx()方法得到属性值
            for (int i = 0; i < fieldArray.length; i++) {
                Cell dataCell = row.createCell(i + 1);
                dataCell.setCellStyle(dataStyle);
                String fieldName = fieldArray[i];
                if ("serialVersionUID".equals(fieldName)) {
                    continue;
                }
                String getMethodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);// 取得对应getXxx()方法
                Method getMethod = tCls.getMethod(getMethodName, new Class[]{});// 通过方法名得到对应的方法
                String typeName = getMethod.getReturnType().getTypeName();
                Object value = getMethod.invoke(t, new Object[]{});// 动态调用方,得到属性值
                if ("java.util.Date".equals(typeName)) {
                    if (null != value) {
                        value = DateUtils.timeToString((Date) value);
                    }
                }
                if (null != value) {
                    dataCell.setCellValue(value.toString());// 为当前列赋值
                }
            }
        }

        OutputStream outputStream = response.getOutputStream();// 打开流
        wb.write(outputStream);// HSSFWorkbook写入流
        wb.close();// HSSFWorkbook关闭
        outputStream.flush();// 刷新流
        outputStream.close();// 关闭流
    }

    private static void setBorderStyle(BorderStyle border, CellRangeAddress region, Sheet sheet) {
        RegionUtil.setBorderBottom(border, region, sheet);//下边框
        RegionUtil.setBorderLeft(border, region, sheet);     //左边框W
        RegionUtil.setBorderRight(border, region, sheet);    //右边框s
        RegionUtil.setBorderTop(border, region, sheet);      //上边框
    }
    public static void main(String[] args) {
        ArrayList<String> row1 = CollUtil.newArrayList("序号", "所属上级", "推荐单位", "入驻时间", "推荐供应商数量（家）", "", "", "年累缴费供应商数量", "", "", "", "", "", "", "", "开累缴费供应商数量",
                "", "", "", "", "", "", "", "");
        ArrayList<String> row2 = CollUtil.newArrayList("", "", "", "", "认证通过", "认证驳回", "小计", "1年期", "2年期", "续费1年期", "续费2年期", "缴费数量", "缴费金额（元）", "缴费供应商占比占认证通过）",
                "缴费供应商占总体缴费家数比例", "1年期", "2年期", "续费1年期", "续费2年期", "缴费数量", "缴费金额（元）", "缴费供应商占比占认证通过）", "缴费供应商占总体缴费家数比例");
        ArrayList<String> row3 = CollUtil.newArrayList("1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1");
        List<List<String>> rows = CollUtil.newArrayList(row1, row2, row3);

        // 通过工具类创建writer
        ExcelWriter writer = ExcelUtil.getWriter("d:/writeMapTest2.xlsx");
        writer.merge(0, 1, 0, 0, "", true);
        writer.merge(0, 1, 1, 1, "", true);
        writer.merge(0, 1, 2, 2, "", true);
        writer.merge(0, 1, 3, 3, "", true);
        writer.merge(0, 0, 4, 6, "", true);
        writer.merge(0, 0, 7, 14, "", true);
        writer.merge(0, 0, 15, 22, "", true);

        // 一次性写出内容，使用默认样式，强制输出标题
        writer.write(rows, true);
        // 关闭writer，释放内存
        writer.close();
    }
}
