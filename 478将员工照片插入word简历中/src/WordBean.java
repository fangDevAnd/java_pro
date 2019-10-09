
import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;

/**
 * @author zhuzhen_hua@yahoo.com.cn
 */

public class WordBean {
// word文档
private Dispatch doc;    
// word运行程序对象
private ActiveXComponent word;    
// 所有word文档集合
private Dispatch documents;    
// 选定的范围或插入点
private Dispatch selection;
    
public WordBean() {
    if (word == null) {
        word = new ActiveXComponent("Word.Application");
        word.setProperty("Visible", new Variant(false));
    }
    if (documents == null)
        documents = word.getProperty("Documents").toDispatch();
}    
  
    
    /**
     * 在当前插入点插入图片
     * 
     * @param imagePath
     *            图片路径
     */
    
public void insertImage(String imagePath, String docPath, int pos) {
    doc = Dispatch.call(documents, "Open", docPath).toDispatch();   // 打开相应的word文档
    selection = Dispatch.get(word, "Selection").toDispatch();       
    for (int i = 0; i < pos; i++)
        Dispatch.call(selection, "MoveRight");      //将插入点向右移动相应的位置
    Dispatch.call(Dispatch.get(selection, "InLineShapes").toDispatch(),
            "AddPicture", imagePath);               //向文档中插入图片
}
    

    public static void main(String args[]) {
        try {
            WordBean wordBean = new WordBean();           
            wordBean.insertImage("c:\\6.jpg", "E://a.doc", 14);           
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}