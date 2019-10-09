package com.cdd.more;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
public class FindMore {
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

public ResultSet getMore() {
    conn = getConn(); // 获取与数据库的连接
    ResultSet rest;       
    try {
        Statement statement = conn.createStatement(); // 获取Statement对象
        String sql = "select p.id,p.sName,w.wId,w.wage,l.pID,l.monthL,l.lDate,l.lMoney from (tb_personnel p left join tb_wage w on p.id = w.perId)" +
        		" left join tb_leave l on l.pID = p.id"; // 定义查询语句
        rest = statement.executeQuery(sql); // 执行查询语句获取查询结果集           
        return rest; // 返回查询结果
    } catch (Exception e) {
        e.printStackTrace();
        return null;
    }
}
    public static void main(String[] args) {
        FindMore findMore = new FindMore();
        ResultSet rest = findMore.getMore();
        try {
            System.out.println("使用外连接进行多表查询");
            while(rest.next()){
                int id = rest.getInt(1);
                String sName = rest.getString(2);
                String wId = rest.getString(3);
                float wage = rest.getFloat("wage");
                String pID = rest.getString("pID");
                int mothl = rest.getInt("monthL");
                float dateL = rest.getFloat("lDate");
                float lMoney = rest.getFloat("lMoney");
                System.out.println("编号："+id+" 姓名："+sName+" 工资编号："+wId+" 工资："+wage+" 请假编号："+pID+" 请假月份："
                        +mothl+" 请假天数："+dateL+" 扣除工资："+lMoney);
                
            }
        } catch (SQLException e) {           
            e.printStackTrace();
        }
    }
}
