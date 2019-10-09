import java.sql.*;

public class JDBCUtil {
    
    Connection conn = null;
    
    public Connection getConn() {
        try {
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        String url = "jdbc:jtds:sqlserver://localhost:1433;DatabaseName=db_database21"; // 连接数据库URL
        String userName = "sa"; // 连接数据库的用户名
        String passWord = ""; // 连接数据库密码
        try {
            conn = DriverManager.getConnection(url, userName, passWord); // 获取数据库连接
            if (conn != null) {
                System.out.println("数据库连接成功！");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn; // 返回Connection对象
    }
    
public void insertEmp(String[] str) {
    JDBCUtil iteacher = new JDBCUtil(); // 创建本类对象
    Connection conn = iteacher.getConn(); // 调用获取数据库连接方法
    String sql = "insert into tb_empTable  values('" + str[0] + "','"
            + str[1] + "','" + str[2] + "','" + str[3] + "')"; // 定义向数据库插入数据的SQL语句
    try {
        Statement statement = conn.createStatement();
        statement.executeUpdate(sql); // 执行插入的sql语句
    } catch (SQLException e) {
        e.printStackTrace();
    }
}
    
}
