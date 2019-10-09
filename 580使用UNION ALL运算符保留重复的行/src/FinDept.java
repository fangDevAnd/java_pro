
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class FinDept {
    private Connection conn;
    
    public Connection getConn() {
        try {
            Class.forName("net.sourceforge.jtds.jdbc.Driver"); // 加载数据库驱动
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        String url = "jdbc:jtds:sqlserver://localhost:1433;DatabaseName=db_database23"; // 连接数据库URL
        String userName = "sa"; // 连接数据库的用户名
        String passWord = ""; // 连接数据库密码
        try {
            conn = DriverManager.getConnection(url, userName, passWord); // 获取数据库连接
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn; // 返回Connection对象
    }
    
    public ResultSet getDept() {
        conn = getConn(); // 获取与数据库的连接
        ResultSet rest;
        try {
            Statement statement = conn.createStatement(); // 获取Statement对象
            String sql = "select * from tb_dept union all select * from tb_dept2"; // 定义查询语句
            rest = statement.executeQuery(sql); // 执行查询语句获取查询结果集
            return rest; // 返回查询结果
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public static void main(String[] args) {
        FinDept dept = new FinDept();
        ResultSet rest = dept.getDept();
        System.out.println("使用UNION ALL保留重复的行");
        try {
            while (rest.next()) {
                int id = rest.getInt(1);
                String name = rest.getString(2);
                System.out.println("编号：" + id + " 部门名称：" + name);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
