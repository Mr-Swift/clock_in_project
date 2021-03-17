package com.njusc.npm.utils.util;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * excel工具类，基于apache poi
 *
 * @author wuxb
 */
public class PoiUtil {
    private static Logger log = Logger.getLogger(PoiUtil.class);

    /**
     * 文件后缀
     */
    private static final String SURFIX = ".xlsx";
    /**
     * Excel Sheet名称
     */
    private static final String SHEET_NAME = "Sheet";

    private PoiUtil() {
    }

    /**
     * 导出Excel
     *
     * @param response HttpServletResponse
     * @param fileName 不含后缀的文件名
     * @param list     导出内容
     */
    public static void exportExcel(final HttpServletResponse response,
                                   final String fileName,
                                   final List<String[]> list) {

        export(response, createExcel(list), fileName);
    }

    /**
     * 导出Excel
     *
     * @param response HttpServletResponse
     * @param fileName 不含后缀的文件名
     * @param list     导出内容
     * @param cell     夸行夸列对象-数组
     */
    public static void exportExcel(final HttpServletResponse response,
                                   final String fileName,
                                   final List<String[]> list,
                                   final CellRangeAddress... cell) {
        Workbook book = createExcel(list);

        if (Util.n(cell)) {
            export(response, book, fileName);
            return;
        }

        Sheet sheet = book.getSheet(SHEET_NAME);
        for (CellRangeAddress c : cell) {
            // sheet.addMergedRegion(c); // 此方法线程安全，效率低
            sheet.addMergedRegionUnsafe(c); // 此方法非线程安全，效率高
        }
        // Stream.of(cell).forEach(i -> sheet.addMergedRegion(i));
        export(response, book, fileName);
    }

    /**
     * HSSFWorkbook:是操作Excel2003以前（包括2003）的版本,扩展名是.xls；
     * <p>
     * XSSFWorkbook:是操作Excel2007后的版本,扩展名是.xlsx,其对应的是excel2007(1048576行，16384列)，容易引发OOM
     * <p>
     * SXSSFWorkbook:是操作Excel2007后的版本,扩展名是.xlsx,无限制,采用写入硬盘机制避免OOM
     *
     * @return
     */
    public static Workbook workbook() {
        return workbook("");
    }

    public static Workbook workbook(final String fileName) {
        if (Util.n(fileName) || SURFIX.equalsIgnoreCase(fileName.substring(fileName.lastIndexOf(".")))) {
            return new SXSSFWorkbook();
        }

        return new HSSFWorkbook();
    }

    /**
     * 创建Excel文件
     *
     * @param list Excel内容
     * @return
     */
    private static Workbook createExcel(final List<String[]> list) {

        // 创建文档对象
        Workbook book = workbook();
        // 创建excel中sheet对象
        Sheet sheet = book.createSheet(SHEET_NAME);

        // 设置单元格格式，左右-上下-居中
        CellStyle style = book.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);

        int size = list.size();
        for (int i = 0; i < size; i++) {
            Row row = sheet.createRow(i);

            String[] arr = list.get(i);
            int len = arr.length;

            for (int j = 0; j < len; j++) {
                Cell cel = row.createCell(j);
                cel.setCellStyle(style);
                cel.setCellValue(arr[j]);
            }
        }

        return book;
    }

    /**
     * 导出文件
     *
     * @param response HttpServletResponse
     * @param book     excel文档对象
     * @param fileName 不含后缀的文件名
     */
    public static void export(final HttpServletResponse response,
                              final Workbook book,
                              final String fileName) {

        response.setContentType("application/x-download");
        response.setCharacterEncoding("utf-8");

        String newFileName = Util.n(fileName) ? System.currentTimeMillis() + SURFIX : fileName + SURFIX;

        try (OutputStream os = response.getOutputStream()) {
            response.setHeader("Content-disposition", "attachment;filename=" + new String(newFileName.getBytes("gbk"), "iso8859-1"));
            book.write(os);
        } catch (Exception e) {
            log.info(e.getMessage(), e);
        } finally {
            try {
                if (!Util.n(book)) {
                    book.close();
                }
            } catch (Exception e) {
                log.info(e.getMessage(), e);
            }
        }
    }

    /**
     * 校验excel行是否是空行
     */
    public static boolean isEmptyRow(Row row) {
        for (int i = row.getFirstCellNum(); i < row.getLastCellNum(); i++) {
            if (!Util.n(cellValue(row.getCell(i)))) {
                return false;
            }
        }
        return true;
    }

    /**
     * poi import(read) excel get row values
     *
     * @param row  行对象
     * @param last 总共读取多少列
     * @return String[]
     */
    public static String[] rowValue(Row row, int last) {
        if (Util.n(row)) {
            return new String[]{};
        }

        String[] values = new String[last];
        for (int i = 0; i < last; i++) {
            values[i] = cellValue(row.getCell(i));
        }

        return values;
    }

    /**
     * 获取单元格数据
     *
     * @param cell 单元格对象
     * @return String
     */
    public static String cellValue(Cell cell) {

        if (Util.n(cell)) {
            return "";
        }

        // 判断数据的类型
        switch (cell.getCellType()) {
            case NUMERIC: // 数字、日期
                return numeric(cell);
            case STRING: // 字符串
                return cell.getStringCellValue();
            case BOOLEAN: // Boolean
                return String.valueOf(cell.getBooleanCellValue());
            case FORMULA: // 公式
                return formula(cell);
            case BLANK: // 空值
                return "";
            case ERROR: // 故障
                return "";
            default:
                break;
        }
        return "";
    }

    /**
     * 单元格数字处理（含日期）
     *
     * @param cell
     * @return
     */
    private static String numeric(Cell cell) {
        if (DateUtil.isCellDateFormatted(cell)) {
            return new SimpleDateFormat("yyyy-MM-dd").format(cell.getDateCellValue()); // 日期型
        }
        // 把数字当成String来读，避免出现1读成1.0的情况
        if (CellType.NUMERIC == cell.getCellType()) {
            cell.setCellType(CellType.STRING);
        }

        String value = new BigDecimal(cell.getStringCellValue()).toString();

        if (value.indexOf(".") > 0 && value.substring(value.indexOf(".")).length() > 3) {
            BigDecimal b = new BigDecimal(value);
            value = b.setScale(2, BigDecimal.ROUND_HALF_UP).toString();

        }
        return value;
    }

    /**
     * 单元格公式处理
     *
     * @param cell
     * @return
     */
    private static String formula(Cell cell) {
        try {
            return cell.getStringCellValue();
        } catch (IllegalStateException e) {
            try {
                return String.valueOf(cell.getNumericCellValue());
            } catch (Exception e2) {
                log.info(e.getMessage(), e2);
            }
        }
        return "";
    }

}
