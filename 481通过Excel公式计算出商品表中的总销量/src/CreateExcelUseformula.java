import org.apache.poi.hssf.record.FormatRecord;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import java.io.*;

public class CreateExcelUseformula {
    
    /**
     * @param args
     */
    
public static void main(String[] args) {
    try {
        /** Excel 文件要存放的位置，假定在C盘根目录下 */
        String outputFile = "c://temps.xls";
        // 创建新的Excel 工作簿
        HSSFWorkbook excelbook = new HSSFWorkbook();
        // 如要新建一名为"工资表"的工作表，其语句为：
        HSSFSheet sheet = excelbook.createSheet("工资表");
        // 在索引0的位置创建行（最顶端的行）
        HSSFRow row = sheet.createRow((short) 0);
        // 在索引0的位置创建单元格（左上端）
        HSSFCell monadism = row.createCell(0);
        // 定义单元格为字符串类型
        monadism.setCellType(HSSFCell.CELL_TYPE_STRING);
        // 在单元格中输入一些内容
        monadism.setCellValue("名称");
        // 在第一行第二列添加内容
        HSSFCell cell1 = row.createCell(1);
        cell1.setCellValue("单价");
        row.createCell(2).setCellValue("重量");
        row.createCell(3).setCellValue("价钱");
        for (int i = 1; i <= 5; i++) { // 通过for循环创建表格
            HSSFRow row2 = sheet.createRow(i); // 在工作薄中创建一行
            row2.createCell(0).setCellValue("苹果"); // 在工作薄行中新建一列
            row2.createCell(1).setCellValue(i); // 设置单元格值
            row2.createCell(2).setCellValue(1.2);
            row2.createCell(3).setCellFormula(
                    "B" + (i + 1) + "*C" + (i + 1) + ""); // 为单元格添加公式
        }
        // 新建输出文件流
        FileOutputStream out = new FileOutputStream(outputFile);
        // 把相应的Excel工作簿存盘
        excelbook.write(out);
        out.flush();
        // 操作结束，关闭文件
        out.close();
        System.out.println("文件创建成功！！！");
    } catch (Exception e) {
        e.printStackTrace();
    }    
}
    
}
