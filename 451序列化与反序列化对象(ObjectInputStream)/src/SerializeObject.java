import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Date;

public class SerializeObject {
    
    static class Bowel implements Serializable {
        private int number1, number2; // 定义普通的实例变量
        private transient int number3; // 定义不会被序列化和反序列化的对象
        private static int number4;
        
        public Bowel(int number1, int number2, int c, int number3) { // 构造方法
            this.number1 = number1;
            this.number2 = number2;
            this.number3 = number3;
            this.number4 = number4;
        }
    }
    
    public static void serialize(String fileName) {
        try {
            File file = new File(fileName); // 根据文件地址创建文件对象
            if (!file.exists()) { // 如果该对象不存在
                file.createNewFile(); // 创建该文件对象
            }
            ObjectOutputStream out = new ObjectOutputStream(
                    new FileOutputStream(fileName)); // 创建对象输出流对象
            out.writeObject("今天是:"); // 向文件中写入数据
            out.writeObject(new Date());
            Bowel my1 = new Bowel(5, 6, 7, 3);// 定义内部类对象
            out.writeObject(my1); // 将对象写入到文件中
            out.close(); // 将流关闭
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static Object[] deserialize(String fileName) {
        try {
            File file = new File(fileName); // 根据文件地址创建文件对象
            if (!file.exists()) { // 如果该文件不存在
                file.createNewFile(); // 新建文件
            }
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(
                    fileName)); // 创建对象输入流
            String today = (String) (in.readObject()); // 从流中读取信息
            Date date = (Date) (in.readObject());
            System.out.println(date.toString());
            Object[] object = { today, date };
            Bowel my1 = (Bowel) (in.readObject());
            in.close(); // 关闭流
            return object;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
