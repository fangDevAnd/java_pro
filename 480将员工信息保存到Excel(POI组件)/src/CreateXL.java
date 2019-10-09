import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFCell;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class CreateXL {
    /** Excel 文件要存放的位置，假定在C盘根目录下 */
    String outputFile = "c:/temp.xls";
    static HSSFSheet sheet = null;
    HSSFWorkbook excelbook;
    
    // 定义生成Excel表格方法
    
public void createExcel() {
    try {
        excelbook=new HSSFWorkbook();
        sheet = excelbook.createSheet("工资表"); // 在索引0的位置创建行（最顶端的行）
        HSSFRow row = sheet.createRow((short) 0);
        HSSFCell monadism = row.createCell((short) 0); // 在索引0的位置创建单元格（左上端）
        monadism.setCellType(HSSFCell.CELL_TYPE_STRING); // 定义单元格为字符串类型
        monadism.setCellValue("姓名");// 在单元格中输入一些内容
        row.createCell((short) 1).setCellValue("性别"); // 在第一行第二列添加内容
        row.createCell((short) 2).setCellValue("年龄");
        row.createCell((short) 3).setCellValue("部门");
        row.createCell((short) 4).setCellValue("职位");
        row.createCell((short) 5).setCellValue("工资信息");
        FileOutputStream out = new FileOutputStream(outputFile); // 新建输出文件流
        excelbook.write(out);// 把相应的Excel工作簿存盘
        out.flush();
        out.close(); // 操作结束，关闭文件
        System.out.println("文件创建成功！！！");
    } catch (Exception e) {
        e.printStackTrace();
    }
}
    
    // 向员工表中添加数据方法

public void insertvalue(String name, String sex, String age, String dept,
        String job, String laborage) {
    try {
        excelbook = new HSSFWorkbook(new FileInputStream(outputFile));  //定义Excel表对象
        System.out.println(excelbook);
        HSSFSheet sheet = excelbook.getSheet("工资表");        //获取指定的工作表
        int count = sheet.getPhysicalNumberOfRows();        //获取工作表中总的行数
        HSSFRow row = sheet.createRow((short) count);       //新建一行
        row.createCell((short) 0).setCellValue(name);        // 在索引0的位置创建单元格（左上端）
        row.createCell((short) 1).setCellValue(sex);
        row.createCell((short) 2).setCellValue(age);
        row.createCell((short) 3).setCellValue(dept);
        row.createCell((short) 4).setCellValue(job);
        row.createCell((short) 5).setCellValue(laborage);        
        FileOutputStream out;// 新建输出文件流        
        out = new FileOutputStream(outputFile);
        excelbook.write(out);// 把相应的Excel工作簿存盘
        out.flush();
    } catch (Exception e) {
        e.printStackTrace();
    }
}
    
}
