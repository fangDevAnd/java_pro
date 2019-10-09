import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SelectUseRight {
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
    public List getRight() {
        conn = getConn(); // 获取与数据库的连接
        ResultSet rest;
        List list = new ArrayList<MrEmp>();
        try {
            Statement statement = conn.createStatement(); // 获取Statement对象
            String sql = "select e.id,dName,person,name,sex,schoolAge  from tb_mrdept d right join tb_mremp e on d.id = e.dId"; // 定义查询语句
            rest = statement.executeQuery(sql); // 执行查询语句获取查询结果集
            while (rest.next()) {
                MrEmp mrEmp = new MrEmp(); // 定义与数据表对应的JavaBean对象
                mrEmp.setId(rest.getInt(1)); // 设置对象属性
                mrEmp.setdName(rest.getString(2));
                mrEmp.setPerson(rest.getString(3));
                mrEmp.setName(rest.getString(4));
                mrEmp.setSex(rest.getString(5));
                mrEmp.setSchoolAge(rest.getString(6));
                list.add(mrEmp);
            }
            return list; // 返回查询结果
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
