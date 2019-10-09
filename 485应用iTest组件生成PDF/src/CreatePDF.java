import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.*;

public class CreatePDF {
    /**
     * @param args
     */

public void writePDF(String fileName) {
    File file = new File(fileName); // 根据参数创建File对象
    FileOutputStream out = null; // 创建FileOutputStream实例
    Document documentPDF = new Document(PageSize.A5, 50, 50, 50, 50); // 创建PDF文档对象
    try {
        out = new FileOutputStream(file); // 实例化FileOutputStream对象
        PdfWriter writer = PdfWriter.getInstance(documentPDF, out); // 为Document对象创建写入器
        documentPDF.open(); // 打开与目标文件的连接
        Font font = FontFactory.getFont(FontFactory.HELVETICA, 18.2f,
                Font.BOLDITALIC, new BaseColor(255, 0, 0));// 定义字体
        Paragraph chapter = new Paragraph(); // 定义段落对象
        Chapter chapter1 = new Chapter(chapter, 1); // 创建章节对象
        chapter1.setNumberDepth(0); // 将编号级别设置为0，表示不会在页面中显示章节编号
        font = FontFactory.getFont(FontFactory.HELVETICA, 16.0f, Font.BOLD,
                new BaseColor(255, 0, 0)); // 定义字体与字体颜色
        Paragraph section1_title1 = new Paragraph("frist itxt PDF", font); // 向文档中插入内容
        Section section1 = chapter1.addSection(section1_title1);
        Paragraph text = new Paragraph("this is frist text");
        section1.add(text); // 向文档中添加章节
        documentPDF.add(chapter1);
        documentPDF.close(); // 关闭文档
    } catch (Exception e) {
        e.printStackTrace();
    }
}

    public static void main(String[] args) {
        CreatePDF paf = new CreatePDF();
        paf.writePDF("C://temp.pdf");
        
    }
}
