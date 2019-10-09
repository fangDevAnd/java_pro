import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import java.io.*;
public class ReadToDateBase {
    static JDBCUtil util = new JDBCUtil();
    public static void main(String[] args) {
String fileToBeRead = "c:\\temp.xls";
try {           
    HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(fileToBeRead)); // 创建对Excel工作簿文件的引用          
    HSSFSheet sheet = workbook.getSheet("员工表");  // 创建对工作表的引用。
    int rows = sheet.getPhysicalNumberOfRows();         //获取表格的行数         
    for (int r = 1; r < rows; r++) {                //循环遍历表格的行
        String value ="";                           //定义保存读取内容的String对象
        HSSFRow row = sheet.getRow(r);              //获取单元格中指定的行对象  
        if (row != null) {
           int  cells = row.getPhysicalNumberOfCells(); //获取单元格中指定列对象
            for (short c = 1; c < cells; c++) {      //循环遍历单元格中的列                  
                HSSFCell cell = row.getCell((short) c); //获取指定单元格中的列                      
                if (cell != null) {
                    if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING) {  //判断单元格的值是否为字符串类型                                
                        value += cell.getStringCellValue()+",";
                    } else if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {  //判断单元格的值是否为数字类型                                
                        value += cell.getNumericCellValue()+",";
                    } else if(cell.getCellType() == HSSFCell.CELL_TYPE_BOOLEAN){    //判断单元格的值是否为布尔类型                      
                        value += cell.getStringCellValue()+",";
                    }
                }                       
            }                  
        }
        String [] str = value.split(",");           //将字符串进行分割
        util.insertEmp(str);                    //调用向数据库插入数据方法
    }   
} catch (Exception e) {
    e.printStackTrace();            
}
    }
}
