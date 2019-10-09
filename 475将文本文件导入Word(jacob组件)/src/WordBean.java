import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;
import java.io.*;

/**
 * @author zhuzhen_hua@yahoo.com.cn
 */

public class WordBean {
    
    private Dispatch doc; // word文档
    private ActiveXComponent word;// word运行程序对象
    private Dispatch documents; // 所有word文档集合
    private Dispatch selection; // 选定的范围或插入点
    
    public WordBean() {
        if (word == null) {
            word = new ActiveXComponent("Word.Application"); // 启动word
            word.setProperty("Visible", new Variant(true)); // 设置word为可视状态
        }
        if (documents == null)
            documents = word.getProperty("Documents").toDispatch(); // 读取属性值
    }
    
    /**
     * 创建一个新的word文档
     */
    
    public void createNewDocument() {
        doc = Dispatch.call(documents, "Add").toDispatch(); // 方法com/dll对象
        selection = Dispatch.get(word, "Selection").toDispatch(); // 读取com对象的属性值
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
     * 在当前插入点插入字符串
     * 
     * @param newText
     *            要插入的新字符串
     */
    
    public void insertText(String newText) {
        Dispatch.put(selection, "Text", newText); // 设置属性值
    }
    
    public static void main(String args[]) {
        
    }
    
}