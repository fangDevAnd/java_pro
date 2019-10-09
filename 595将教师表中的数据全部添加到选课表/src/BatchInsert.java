import java.sql.*;
import java.util.*;


public class BatchInsert {
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
    public List executeTeacher() {
        conn = getConn();                                // 获取数据库连接
        Statement cs = null;                                 // 定义CallableStatement对象
        String sql = "select * from tb_teacher";         // 定义调用储存过程的SQL语句
        List list = new ArrayList();
        try {
            cs = conn.createStatement();                     // 实例化Statement对象
            ResultSet rest = cs.executeQuery(sql);       // 执行SQL语句
            while (rest.next()) {                        // 循环遍历查询结果集
                Teacher teacher = new Teacher();         //定义与数据库表对应的JavaBean对象
                teacher.setId(rest.getInt(1));           //设置对象的参数值
                teacher.settName(rest.getString(2));
                teacher.setCourse(rest.getString(3));
                list.add(teacher);                       //向集合中添加对象
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    public void insertBatch() {
        conn = getConn();                                // 获取数据库连接
        Statement cs = null;                                 // 定义Statement对象
        try {
            cs = conn.createStatement();                     // 实例化Statement对象
            List list = executeTeacher();
            for (int i = 0; i < list.size(); i++) {
                Teacher teacher = (Teacher) list.get(i);
                cs.addBatch("insert into tb_elective values ('"
                        + teacher.getCourse() + "','" + teacher.gettName()
                        + "','待定')");                //添加SQL语句
            }
            System.out.println("数据添加成功！");
            cs.executeBatch();                       // 批量执行SQL语句
            cs.close();                              // 将Statement对象关闭
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        BatchInsert bi = new BatchInsert();
        bi.insertBatch();
    }

}
