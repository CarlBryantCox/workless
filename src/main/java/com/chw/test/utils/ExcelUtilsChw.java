package com.chw.test.utils;

import com.chw.test.config.BizException;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.net.URLEncoder;
import java.util.LinkedList;
import java.util.List;

@Slf4j
public class ExcelUtilsChw {

    /**
     * 根据Excel文件获取第一个工作表
     *
     * @param file Excel文件
     * @return 第一个工作表
     * @throws IOException IO异常 文件格式不正确异常
     */
    public static Sheet getFirstSheet(MultipartFile file) throws IOException {
        try{
            String fileName = file.getOriginalFilename();
            InputStream is = file.getInputStream();
            Workbook sheets;
            if (fileName != null && fileName.endsWith("xlsx")) {
                sheets = new XSSFWorkbook(is);
            } else if (fileName != null && fileName.endsWith("xls")) {
                sheets = new HSSFWorkbook(is);
            } else {
                throw new RuntimeException("传入的文件格式不正确！");
            }
            return sheets.getSheetAt(0);
        }catch (Exception e){
            throw new RuntimeException("传入的文件格式不正确！");
        }
    }

    /**
     * 根据Excel文件获取工作表
     * @param file Excel文件
     * @return 第一个工作表
     * @throws IOException IO异常 文件格式不正确异常
     */
    public static Sheet getSheet(MultipartFile file, Integer index) throws IOException {
        try{
            String fileName = file.getOriginalFilename();
            InputStream is = file.getInputStream();
            Workbook sheets;
            if (fileName != null && fileName.endsWith("xlsx")) {
                sheets = new XSSFWorkbook(is);
            } else if (fileName != null && fileName.endsWith("xls")) {
                sheets = new HSSFWorkbook(is);
            } else {
                throw new BizException("传入的文件格式不正确！");
            }
            return sheets.getSheetAt(index);
        }catch (Exception e){
            throw new BizException("传入的文件格式不正确！");
        }
    }

    /**
     * 获取Excel数据的最后一行的行号
     * @param sheet      工作表
     * @param startIndex 数据的开始行
     * @return 最后一行的行号
     */
    public static int getLastRowNum(Sheet sheet, int startIndex) {
        //总行数
        int lastRowNum = sheet.getLastRowNum() + 1;
        if (lastRowNum < startIndex) {
            throw new BizException("EXCEL不能为空！");
        }
        if (lastRowNum > 10000) {
            throw new BizException("EXCEL文件行数不能超过10000！");
        }
        return lastRowNum;
    }

    /**
     * 获取07以上版标题单元格样式
     * @param wb excel工作类
     * @return 07以上版普通单元格样式
     */
    public static XSSFCellStyle getTitleXSSFCellStyle(XSSFWorkbook wb) {
        XSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER);//居中
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        XSSFFont successFont = wb.createFont();
        successFont.setFontName("黑体");
        successFont.setBold(true);
        style.setFont(successFont);
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderTop(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
        return style;
    }

    /**
     * 获取07以上版普通单元格样式
     * @param wb excel工作类
     * @return 07以上版普通单元格样式
     */
    public static XSSFCellStyle getNormalXSSFCellStyle(XSSFWorkbook wb) {
        XSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER);//居中
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        XSSFFont successFont = wb.createFont();
        successFont.setFontName("微软雅黑");
        short colorIndex = 76;
        successFont.setColor(colorIndex);
        style.setFont(successFont);
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderTop(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
        return style;
    }

    /**
     * 标题单元格样式
     * @param wb excel工作类
     * @return 07以上版标题单元格样式
     */
    public static CellStyle getTitleCellStyle(Workbook wb) {
        CellStyle style = wb.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER);//居中
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        Font successFont = wb.createFont();
        successFont.setFontName("黑体");
        successFont.setBold(true);
        style.setFont(successFont);
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderTop(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
        return style;
    }

    /**
     * 获取普通单元格样式
     * @param wb excel工作类
     * @return 07以上版普通单元格样式
     */
    public static CellStyle getNormalCellStyle(Workbook wb) {
        CellStyle style = wb.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER);//居中
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        Font successFont = wb.createFont();
        successFont.setFontName("微软雅黑");
        short colorIndex = 76;
        successFont.setColor(colorIndex);
        style.setFont(successFont);
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderTop(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
        return style;
    }



    /**
     * 获取07以上版错误单元格样式，红色字体
     *
     * @param wb excel工作类
     * @return 07以上版错误单元格样式
     */
    public static XSSFCellStyle getErrorXSSFCellStyle(XSSFWorkbook wb) {
        XSSFCellStyle errorStyle = wb.createCellStyle();
        XSSFFont font = wb.createFont();
        font.setColor(XSSFFont.COLOR_RED);
        errorStyle.setFont(font);
        return errorStyle;
    }

    /**
     * 获取07以上版错误单元格样式，红色字体
     * @param wb excel工作类
     * @return 07以上版错误单元格样式
     */
    public static CellStyle getErrorCellStyle(Workbook wb) {
        CellStyle errorStyle = wb.createCellStyle();
        Font font = wb.createFont();
        font.setColor(XSSFFont.COLOR_RED);
        errorStyle.setFont(font);
        errorStyle.setWrapText(true);
        return errorStyle;
    }

    /**
     * 获取新增单元格样式，蓝色字体
     * @param wb excel工作类
     * @return 07以上版新增单元格样式
     */
    public static CellStyle getInsertCellStyle(Workbook wb) {
        CellStyle insertStyle = wb.createCellStyle();
        insertStyle.setAlignment(HorizontalAlignment.CENTER);//居中
        insertStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        Font font = wb.createFont();
        font.setColor((short) 12);
        insertStyle.setFont(font);
        return insertStyle;
    }

    /**
     * 获取更新单元格样式，紫色字体
     * @param wb excel工作类
     * @return 07以上版更新单元格样式
     */
    public static CellStyle getUpdateCellStyle(Workbook wb) {
        CellStyle updateStyle = wb.createCellStyle();
        updateStyle.setAlignment(HorizontalAlignment.CENTER);//居中
        updateStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        Font font = wb.createFont();
        font.setColor((short) 61);
        updateStyle.setFont(font);
        return updateStyle;
    }

    /**
     * 自适应单元格宽度
     * @param size 工作表的列数
     */
    public static void setSizeColumn(Sheet sheet, int size) {
        for (int columnNum = 0; columnNum < size; columnNum++) {
            int columnWidth = sheet.getColumnWidth(columnNum) / 256;
            for (int rowNum = 0; rowNum < sheet.getLastRowNum(); rowNum++) {
                Row currentRow=sheet.getRow(rowNum);
                //当前行未被使用过
                if (currentRow == null) {
                    continue;
                }
                if (currentRow.getCell(columnNum) != null) {
                    Cell currentCell = currentRow.getCell(columnNum);
                    currentCell.setCellType(CellType.STRING);
                    int length = currentCell.getStringCellValue().getBytes().length;
                    if (columnWidth < length) {
                        columnWidth = length;
                    }
                }
            }
            sheet.setColumnWidth(columnNum, (columnWidth+6) * 256);
        }
    }

    public static void write(HttpServletResponse response, String fileName, Workbook wb) throws Exception{
        String fn = URLEncoder.encode(fileName, "utf-8");
        fn=fn.replaceAll("\\+", "%20");
        //设置response的Header
        response.setContentType("application/msexcel");
        response.addHeader("Access-Control-Expose-Headers","filename");
        response.addHeader("filename", URLEncoder.encode(fileName, "utf-8"));
        response.addHeader("Content-Disposition", "attachment;filename=" + fn);
        OutputStream os = new BufferedOutputStream(response.getOutputStream());
        //将excel写入到输出流中
        wb.write(os);
        os.flush();
        os.close();
        wb.close();
    }

    public static String getStringCellValue(Row row,Integer index){
        Cell cell=row.getCell(index);
        String cellText;
        if(cell==null){
            cellText="";
        }else {
            cell.setCellType(CellType.STRING);
            cellText=cell.getStringCellValue().trim();
        }
        return cellText;
    }

    public static <T> void export(HttpServletResponse response, String fileName, List<T> list, Class<T> tClass, String path, int sheetIndex, int firstRow, String[] properties) throws Exception{
        Workbook wb;
        String suffix;
        if(path.endsWith("xlsx")){
            wb = new XSSFWorkbook(tClass.getResourceAsStream(path));
            suffix=".xlsx";
        }else {
            wb = new HSSFWorkbook(tClass.getResourceAsStream(path));
            suffix=".xls";
        }
        Sheet sheet = wb.getSheetAt(sheetIndex);
        CellStyle style= ExcelUtilsChw.getNormalCellStyle(wb);
        if(!CollectionUtils.isEmpty(list)){
            for (T t : list) {
                if(properties!=null){
                    Row row = sheet.createRow(firstRow);
                    for (int i = 0; i < properties.length; i++) {
                        //利用反射把对象数据注入到EXCEL
                        Field declaredField = tClass.getDeclaredField(properties[i]);
                        declaredField.setAccessible(true);
                        Cell cell = row.createCell(i);
                        cell.setCellStyle(style);
                        cell.setCellValue((String) declaredField.get(t));
                    }
                    firstRow++;
                }
            }
        }
        ExcelUtilsChw.write(response,fileName+suffix,wb);
    }

    public static <T> void export(HttpServletResponse response, String fileName, List<T> list, Class<T> tClass, String path, String[] properties) throws Exception{
        ExcelUtilsChw.export(response, fileName, list, tClass, path,0,1, properties);
    }

    public static <T> void export(HttpServletResponse response, String fileName, List<T> list, Class<T> tClass, String[] properties, String[] colNames) throws Exception{
        XSSFWorkbook wb=new XSSFWorkbook();
        XSSFSheet sheet = wb.createSheet();
        XSSFCellStyle titleStyle = ExcelUtilsChw.getTitleXSSFCellStyle(wb);
        XSSFCellStyle style = ExcelUtilsChw.getNormalXSSFCellStyle(wb);
        XSSFRow titleRow = sheet.createRow(0);
        for (int i = 0; i < colNames.length; i++) {
            XSSFCell cell = titleRow.createCell(i);
            cell.setCellStyle(titleStyle);
            cell.setCellValue(colNames[i]);
        }
        int rowNum=1;
        if(!CollectionUtils.isEmpty(list)){
            for (T t : list) {
                Row row = sheet.createRow(rowNum);
                for (int i = 0; i < properties.length; i++) {
                    //利用反射把对象数据注入到EXCEL
                    Field declaredField = tClass.getDeclaredField(properties[i]);
                    declaredField.setAccessible(true);
                    Cell cell = row.createCell(i);
                    cell.setCellValue((String) declaredField.get(t));
                    cell.setCellStyle(style);
                }
                rowNum++;
            }
        }
        ExcelUtilsChw.setSizeColumn(sheet,colNames.length);
        ExcelUtilsChw.write(response,fileName+".xlsx",wb);
    }

    public static <T> List<T> transfer(Sheet sheet,String[] colNames,String[] properties,int titleIndex,Class<T> tClass){
        int startIndex=titleIndex+1;
        int lastRowNum = ExcelUtilsChw.getLastRowNum(sheet,startIndex);
        List<T> importDTOList = new LinkedList<>();
        Row baseRow = sheet.getRow(titleIndex);
        int colLen = colNames.length;
        for (int i = 0; i < colLen; i++) {
            if(!colNames[i].equals(ExcelUtilsChw.getStringCellValue(baseRow,i))){
                throw new BizException("请勿修改Excel上传模板！");
            }
        }
        try {
            for (int r = startIndex; r < lastRowNum; r++) {
                //Excel行数据
                Row row = sheet.getRow(r);
                if (row == null) {
                    continue;
                }
                boolean blankFlag=true;
                T t = tClass.newInstance();
                Field reason = tClass.getDeclaredField("reason");
                reason.setAccessible(true);
                reason.set(t,"");
                for (int i = 0; i < colLen; i++) {
                    Field declaredField = tClass.getDeclaredField(properties[i]);
                    declaredField.setAccessible(true);
                    String stringCellValue = ExcelUtilsChw.getStringCellValue(row, i);
                    if(blankFlag && !"".equals(stringCellValue)){
                        blankFlag=false;
                    }
                    declaredField.set(t,stringCellValue);
                }
                if(!blankFlag){
                    importDTOList.add(t);
                }
            }
        } catch (Exception e) {
            log.info(e.toString());
        }
        return importDTOList;
    }
}
