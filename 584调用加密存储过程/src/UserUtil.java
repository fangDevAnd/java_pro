
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserUtil {
    
    private Connection conn;
    
    public Connection getConn() {
        try {
            Class.forName("net.sourceforge.jtds.jdbc.Driver"); // 加载数据库驱动
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        String url = "jdbc:jtds:sqlserver://localhost:1433;DatabaseName=db_database24"; // 连接数据库URL
        String userName = "sa"; // 连接数据库的用户名
        String passWord = ""; // 连接数据库密码
        try {
            conn = DriverManager.getConnection(url, userName, passWord); // 获取数据库连接
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn; // 返回Connection对象
    }    

public List executeUpdate() {
    conn = getConn(); // 获取数据库连接
    CallableStatement cs = null; // 定义CallableStatement对象
    String sql = "{call selectUser}"; // 定义调用储存过程的SQL语句
    List list = new ArrayList();
    try {
        cs = conn.prepareCall(sql); // 实例化CallableStatement对象
        ResultSet rest = cs.executeQuery(); // 执行SQL语句
        while(rest.next()){             //循环遍历查询结果集
            User user = new User();     //定义与数据表对应的JavaBean对象
            user.setId(rest.getInt(1));     //设置对象属性
            user.setUserName(rest.getString(2));
            user.setPassword(rest.getString(3));
            user.setAge(rest.getInt(4));
            user.setSex(rest.getString(5));
            user.setJob(rest.getString(6));
            list.add(user);             //向集合中添加对象
        }            
    } catch (SQLException e) {
        e.printStackTrace();
       
    }
    return list;
}      
}
