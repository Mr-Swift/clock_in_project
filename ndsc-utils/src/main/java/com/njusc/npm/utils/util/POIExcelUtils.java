//package com.njusc.wqlwc.utils.util;
//
//import java.io.IOException;
//import java.lang.reflect.Field;
//import java.lang.reflect.InvocationTargetException;
//import java.lang.reflect.Method;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.List;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//
//import org.apache.poi.hssf.usermodel.HSSFCell;
//import org.apache.poi.hssf.usermodel.HSSFCellStyle;
//import org.apache.poi.hssf.usermodel.HSSFDataFormat;
//import org.apache.poi.hssf.usermodel.HSSFRichTextString;
//import org.apache.poi.hssf.usermodel.HSSFRow;
//import org.apache.poi.hssf.usermodel.HSSFSheet;
//import org.apache.poi.hssf.usermodel.HSSFWorkbook;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
///**
// * @author jinzf
// * @date Jul 29, 2015
// * @description TODO
// * @version 1.0
// */
//public class POIExcelUtils {
//
//    private final static Logger log = LoggerFactory
//            .getLogger(POIExcelUtils.class);
//
//    public static <T> HSSFWorkbook exportExcel(String title, String[] headers,
//            List<T> dataset, String pattern) throws IOException {
//        // 声明一个工作薄
//        HSSFWorkbook workbook = new HSSFWorkbook();
//        // 生成一个表格
//        HSSFSheet sheet = workbook.createSheet(title);
//        // // 设置表格默认列宽度为15个字节
//        // sheet.setDefaultColumnWidth(15);
//        // // 生成一个样式
//        // HSSFCellStyle style = workbook.createCellStyle();
//        // // 设置这些样式
//        // style.setFillForegroundColor(HSSFColor.SKY_BLUE.index);
//        // style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
//        // style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
//        // style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
//        // style.setBorderRight(HSSFCellStyle.BORDER_THIN);
//        // style.setBorderTop(HSSFCellStyle.BORDER_THIN);
//        // style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
//        // // 生成一个字体
//        // HSSFFont font = workbook.createFont();
//        // font.setColor(HSSFColor.VIOLET.index);
//        // font.setFontHeightInPoints((short) 12);
//        // font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
//        // // 把字体应用到当前的样式
//        // style.setFont(font);
//        // // 生成并设置另一个样式
//        // HSSFCellStyle style2 = workbook.createCellStyle();
//        // style2.setFillForegroundColor(HSSFColor.LIGHT_YELLOW.index);
//        // style2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
//        // style2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
//        // style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
//        // style2.setBorderRight(HSSFCellStyle.BORDER_THIN);
//        // style2.setBorderTop(HSSFCellStyle.BORDER_THIN);
//        // style2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
//        // style2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
//        // // 生成另一个字体
//        // HSSFFont font2 = workbook.createFont();
//        // font2.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
//        // // 把字体应用到当前的样式
//        // style2.setFont(font2);
//        //
//        // // 声明一个画图的顶级管理器
//        // HSSFPatriarch patriarch = sheet.createDrawingPatriarch();
//        // // 定义注释的大小和位置,详见文档
//        // HSSFComment comment = patriarch.createComment(new HSSFClientAnchor(0,
//        // 0, 0, 0, (short) 4, 2, (short) 6, 5));
//        // // 设置注释内容
//        // comment.setString(new HSSFRichTextString("可以在POI中添加注释！"));
//        // // 设置注释作者，当鼠标移动到单元格上是可以在状态栏中看到该内容.
//        // comment.setAuthor("leno");
//
//        // 产生表格标题行
//        HSSFRow row = sheet.createRow(0);
//        for (int i = 0; i < headers.length; i++) {
//            HSSFCell cell = row.createCell(i);
//            // cell.setCellStyle(style);
//            HSSFRichTextString text = new HSSFRichTextString(headers[i]);
//            cell.setCellValue(text);
//        }
//
//        HSSFCellStyle textStyle = workbook.createCellStyle();
//        HSSFDataFormat format = workbook.createDataFormat();
//        textStyle.setDataFormat(format.getFormat("@"));
//
//        // 遍历集合数据，产生数据行
//        int index = 0;
//        for(T t : dataset){
//            index++;
//            row = sheet.createRow(index);
//            // 利用反射，根据javabean属性的先后顺序，动态调用getXxx()方法得到属性值
//            Field[] fields = t.getClass().getDeclaredFields();
//            for (int i = 0; i < fields.length; i++) {
//                HSSFCell cell = row.createCell(i);
//
//                cell.setCellStyle(textStyle);//设置单元格格式为"文本"
//                cell.setCellType(HSSFCell.CELL_TYPE_STRING);
//
//                Field field = fields[i];
//                String fieldName = field.getName();
//                String getMethodName = "get"
//                        + fieldName.substring(0, 1).toUpperCase()
//                        + fieldName.substring(1);
//                try {
//                    Class<?> tCls = t.getClass();
//                    Method getMethod = tCls.getMethod(getMethodName,
//                            new Class[] {});
//                    Object value = getMethod.invoke(t, new Object[] {});
//                    // 判断值的类型后进行强制类型转换
//                    String textValue = null;
//                    if (value == null) {
//                        textValue = "";
//                    } else if (value instanceof Date) {
//                        Date date = (Date) value;
//                        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
//                        textValue = sdf.format(date);
//                    } else {
//                        // 其它数据类型都当作字符串简单处理
//                        textValue = value.toString();
//                    }
//                    // 如果不是图片数据，就利用正则表达式判断textValue是否全部由数字组成
//                    if (textValue != null) {
//                        Pattern p = Pattern.compile("^//d+(//.//d+)?$");
//                        Matcher matcher = p.matcher(textValue);
//                        if (matcher.matches()) {
//                            // 是数字当作double处理
//                            cell.setCellValue(Double.parseDouble(textValue));
//                        } else {
//                            HSSFRichTextString richString = new HSSFRichTextString(
//                                    textValue);
//                            cell.setCellValue(richString);
//                        }
//                    }
//                } catch (SecurityException e) {
//                    log.error(e.getMessage(), e);
//                } catch (NoSuchMethodException e) {
//                    log.error(e.getMessage(), e);
//                } catch (IllegalArgumentException e) {
//                    log.error(e.getMessage(), e);
//                } catch (IllegalAccessException e) {
//                    log.error(e.getMessage(), e);
//                } catch (InvocationTargetException e) {
//                    log.error(e.getMessage(), e);
//                } finally {
//                }
//            }
//        }
//
//        return workbook;
//    }
//}
