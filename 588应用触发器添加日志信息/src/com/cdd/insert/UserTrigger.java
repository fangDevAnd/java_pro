package com.cdd.insert;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class UserTrigger {
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

public void insertInfo(User user){
    conn = getConn();       //获取数据库连接
    try {
        PreparedStatement statement = conn.prepareStatement("insert into tb_user values(?,?,?,?,?)");   //定义添加数据的SQL语句
        statement.setString(1, user.getUserName());     //设置预处理语句的参数值
        statement.setString(2, user.getPassword());
        statement.setInt(3, user.getAge());
        statement.setString(4,user.getSex());
        statement.setString(5, user.getJob());
        statement.executeUpdate();      //执行预处理语句
    } catch (Exception e) {           
        e.printStackTrace();
    }        
}
}
