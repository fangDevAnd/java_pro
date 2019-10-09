import java.io.*;
import java.util.*;

public class GetProperties {
    
    public String getProperties(String keyName) {
        InputStream ins = getClass().getResourceAsStream(
                "ApplicationResources.properties"); // 根据属性文件创建InputStream对象
        Properties props = new Properties(); // 创建Properties对象
        String value = "";
        try {
            props.load(ins); // 从输入流中读取属性文件中信息
            value = props.getProperty(keyName); // 获取指定参数的属性值
        } catch (IOException e) {
            
            e.printStackTrace();
        }
        return value;
    }
    
}
