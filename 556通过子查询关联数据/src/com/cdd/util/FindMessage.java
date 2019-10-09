package com.cdd.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
public class FindMessage {
  private Connection conn; // 定义Connection对象
    
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
    /**
     * @param args
     */
    
public ResultSet getMessage() {
    ResultSet rest = null;
    conn = getConn(); // 获取与数据库的连接
    try {
        Statement statement = conn.createStatement(); // 获取Statement对象
        String sql = "select name,college,address from tb_student I where exists " +
        		"(select name from tb_grade M where M.name=I.name and english >90)"; // 定义查询语句
        rest = statement.executeQuery(sql); // 执行查询语句获取查询结果集           
    } catch (Exception e) {
        e.printStackTrace();
    }
    return rest; // 返回查询结果
}    
    /**
     * @param args
     */
    public static void main(String[] args) {
       FindMessage findMessage = new FindMessage();
       ResultSet rest = findMessage.getMessage();
       System.out.println("查询英文成绩在90分以上的学生信息：");
       try {
        while(rest.next()){
               String name = rest.getString(1);
               String college = rest.getString(2);
               String address = rest.getString(3);
               System.out.println("姓名为："+name+" 学院为："+college+" 地址为："+address);
           }
    } catch (Exception e) {       
        e.printStackTrace();
    }
    }
    
}
