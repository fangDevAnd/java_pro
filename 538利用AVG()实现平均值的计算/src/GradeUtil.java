

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.plaf.metal.MetalIconFactory.FolderIcon16;

public class GradeUtil {
    Connection conn = null;
    
    // 获取数据库连接
    
    public Connection getConn() {
        try {
            Class.forName("net.sourceforge.jtds.jdbc.Driver"); // 加载数据库驱动
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        String url = "jdbc:jtds:sqlserver://localhost:1433;DatabaseName=db_database21"; // 连接数据库URL
        String userName = "sa"; // 连接数据库的用户名
        String passWord = ""; // 连接数据库密码
        try {
            conn = DriverManager.getConnection(url, userName, passWord); // 获取数据库连接
            if (conn != null) {
              
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn; // 返回Connection对象
    }   
    
    // 定义按指定按指定条件降序查询数据方法

public ResultSet getResult() {
    conn = getConn(); // 获取数据库连接
    float sum = 0;
    ResultSet set = null;
    try {
        Statement staement = conn.createStatement();
        String sql = "select avg(chinese)as chinese,avg(math) as math,avg(english) as english" +
        		",avg(physics) as physics,avg(history)as history from tb_Grade"; // 定义查找各科成绩的平均值的SQL语句
       set = staement.executeQuery(sql); // 执行查询语句返回查询结果集        
    } catch (Exception e) {
        e.printStackTrace();
    }
    return set;
}
    
    public static void main(String[] args) {
        GradeUtil util = new GradeUtil();
        ResultSet set = util.getResult();
        System.out.println("各科成绩的平均成绩为：");
        try {
            while(set.next()){
                float chinese = set.getFloat("chinese");
                float math = set.getFloat("math");
                float english = set.getFloat("english");
                float physics = set.getFloat("physics");
                float history = set.getFloat("history");
                System.out.println("语文："+chinese+" ,数学："+math+" ,英语："+english+" ,物理："+physics+" ，历史："+history);
            }
        } catch (SQLException e) {          
            e.printStackTrace();
        }
    }
}
