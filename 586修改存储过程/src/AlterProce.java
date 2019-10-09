import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class AlterProce {
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
    
    public boolean executeUpdate(String sql) {
        conn = getConn(); // 获取数据库连接
        try {
            Statement stmt = conn.createStatement(); // 实例化Statement对象
            int iCount = stmt.executeUpdate(sql); // 执行修改语句
            System.out.println("操作成功，所影响的记录数为" + String.valueOf(iCount)); // 给出提示信息
            conn.close(); // 关闭连接
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
