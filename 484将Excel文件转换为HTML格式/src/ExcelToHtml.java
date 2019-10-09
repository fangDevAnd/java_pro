import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;

public class ExcelToHtml {
    
    /**
     * EXCEL转HTML
     * 
     * @param xlsfilePath
     *            EXCEL文件全路径
     * @param htmlfilePath
     *            转换后HTML存放路径
     */
    
public void excelToHtml(String xlsfilePath, String htmlfilePath) {
    ActiveXComponent app = new ActiveXComponent("Excel.Application"); // 启动excel
try {
    app.setProperty("Visible", new Variant(false));                //设置Excel对象为不可见
    Dispatch excels = app.getProperty("Workbooks").toDispatch();
    Dispatch excel = Dispatch.invoke(
            excels,
            "Open",
            Dispatch.Method,
            new Object[] { xlsfilePath, new Variant(false),
                    new Variant(true) }, new int[1]).toDispatch(); // 功能调用
    Dispatch.invoke(excel, "SaveAs", Dispatch.Method, new Object[] {
            htmlfilePath, new Variant(44) }, new int[1]); // 以html格式保存到临时文件
    Variant f = new Variant(false);
    Dispatch.call(excel, "Close", f); // 关闭excel文件
    } catch (Exception e) {
        e.printStackTrace();
    }
}

public static void main(String[] args) {
    ExcelToHtml eth = new ExcelToHtml();        //创建本类对象
    eth.excelToHtml("c:\\JAVA 周计划及完成情况报表.xls", "c:\\JAVA 周计划及完成情况报表.html"); //调用将Excel转换为Html格式方法
}

}
