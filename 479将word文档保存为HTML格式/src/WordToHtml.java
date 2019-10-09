//import java.io.*;
//import com.jacob.*;
//import com.jacob.activeX.*;
//import com.jacob.com.Dispatch;
//import com.jacob.com.Variant;
//
//public class WordToHtml {
//    /**
//     * WORDתHTML
//     *
//     * @param docfilePath
//     *            WORD�ļ�ȫ·��
//     * @param htmlfilePath
//     *            ת����HTML���·��
//     */
//
//    public void wordToHtml(String docfilePath, String htmlfilePath) {
//        ActiveXComponent app = new ActiveXComponent("Word.Application"); // ����word
//        try {
//            app.setProperty("Visible", new Variant(false)); // ����wordΪ������
//            Dispatch dispatch = app.getProperty("Documents").toDispatch(); // ��ȡ�ĵ�����ֵ
//            Dispatch doc = Dispatch.invoke(
//                    dispatch,
//                    "Open",
//                    Dispatch.Method,
//                    new Object[] { docfilePath, new Variant(false),
//                            new Variant(true) }, new int[1]).toDispatch(); // ���ܵ���
//            Dispatch.invoke(doc, "SaveAs", Dispatch.Method, new Object[] {
//                    htmlfilePath, new Variant(8) }, new int[1]); // ��html��ʽ���浽��ʱ�ļ�
//            Variant f = new Variant(false);
//            Dispatch.call(doc, "Close", f); // ���ĵ��رգ�����������Ϊ������
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static void main(String[] args) {
//        WordToHtml wth = new WordToHtml(); // �����������
//        wth.wordToHtml("c:\\��word�л��Ʊ��.doc", "c:\\��word�л��Ʊ��.html"); // ���ø�ʽת������
//
//    }
//
//}
