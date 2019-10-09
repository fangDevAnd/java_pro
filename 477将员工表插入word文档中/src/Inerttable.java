import com.jacob.com.*;
import com.jacob.activeX.ActiveXComponent;

public class Inerttable {
    // word文档
    private Dispatch doc;
    
    // word运行程序对象
    private ActiveXComponent word;
    
    // 所有word文档集合
    private Dispatch documents;
    
    // 选定的范围或插入点
    private Dispatch selection;
    
    private boolean saveOnExit = true;
    
    public Inerttable() {
        ComThread.InitSTA();// 初始化com的线程，非常重要！！使用结束后要调用 realease方法
        if (word == null) {
            word = new ActiveXComponent("Word.Application");
            word.setProperty("Visible", new Variant(false));
        }
        if (documents == null)
            documents = word.getProperty("Documents").toDispatch();
    }
    
    /**
     * 创建一个新的word文档
     */
    public void createNewDocument() {
        doc = Dispatch.call(documents, "Add").toDispatch();
        selection = Dispatch.get(word, "Selection").toDispatch();
    }
    
    /**
     * 在指定的单元格里填写数据
     * 
     * @param tableIndex
     * @param cellRowIdx
     * @param cellColIdx
     * @param txt
     */
    
    public void putTxtToCell(int tableIndex, int cellRowIdx, int cellColIdx,
            String txt) {
        Dispatch tables = Dispatch.get(doc, "Tables").toDispatch(); // 获取表格属性
        Dispatch table = Dispatch.call(tables, "Item", new Variant(tableIndex))
                .toDispatch(); // 要填充的表格
        Dispatch cell = Dispatch.call(table, "Cell", new Variant(cellRowIdx),
                new Variant(cellColIdx)).toDispatch();
        Dispatch.call(cell, "Select");
        Dispatch.put(selection, "Text", txt); // put()方法设置表格内容
    }
    
    /**
     * 创建表格
     * 
     * @param pos
     *            位置
     * @param cols
     *            列数
     * @param rows
     *            行数
     */
    
    public void createTable(int numCols, int numRows) {
        Dispatch tables = Dispatch.get(doc, "Tables").toDispatch(); // 获取表格属性
        Dispatch range = Dispatch.get(selection, "Range").toDispatch(); // 获取表格行列属性
        Dispatch newTable = Dispatch.call(tables, "Add", range,
                new Variant(numRows), new Variant(numCols)).toDispatch(); // 向表格中添加内容
        Dispatch.call(selection, "MoveRight");
    }
    
    /**
     * 文件保存或另存为
     * 
     * @param savePath
     *            保存或另存为路径
     */
    public void save(String savePath) {
        Dispatch.call(
                (Dispatch) Dispatch.call(word, "WordBasic").getDispatch(),
                "FileSaveAs", savePath);
    }
    
    /**
     * 关闭全部应用
     */
    public void close() {
        
        if (word != null) {
            Dispatch.call(word, "Quit");
            word = null;
        }
        selection = null;
        documents = null;
    }
    
    public static void main(String args[]) {
        
        Inerttable msWordManager = new Inerttable(); // 创建本类对象
        try {
            msWordManager.createNewDocument(); // 新建文档
            msWordManager.createTable(5, 5); // 创建5行5列的表格
            msWordManager.putTxtToCell(1, 1, 1, "编号"); // 向第1行第1列中添加内容
            msWordManager.putTxtToCell(1, 2, 1, "1"); // 向第2行第1列中添加内容
            msWordManager.putTxtToCell(1, 1, 2, "姓名"); 
            msWordManager.putTxtToCell(1, 2, 2, "李四");
            msWordManager.putTxtToCell(1, 1, 3, "年龄");
            msWordManager.putTxtToCell(1, 2, 3, "30");
            msWordManager.putTxtToCell(1, 1, 4, "性别");
            msWordManager.putTxtToCell(1, 2, 4, "男");
            msWordManager.putTxtToCell(1, 1, 5, "学历");
            msWordManager.putTxtToCell(1, 2, 5, "本科");
            msWordManager.save("c:\\向word中绘制表格.doc"); // 调用保存文档方法
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            msWordManager.close();
        }
        
    }
    
}
