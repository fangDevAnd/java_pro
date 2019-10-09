package com.cdd.jdbc;

import java.sql.*;

public class CreateJoin {
    Connection conn = null;
static {
    try {
        Class.forName("net.sourceforge.jtds.jdbc.Driver"); // 加载数据库驱动
        System.out.println("数据库驱动加载成功！");
    } catch (ClassNotFoundException e) {
        e.printStackTrace();
    }
}
    
public Connection getConn() {
    String url = "jdbc:jtds:sqlserver://localhost:1433;DatabaseName=db_database22"; // 连接数据库URL
    String userName = "sa"; // 连接数据库的用户名
    String passWord = ""; // 连接数据库密码
    try {
        conn = DriverManager.getConnection(url, userName, passWord); // 获取数据库连接
        if (conn != null) {
            System.out.println("已成功的与SQLServer2000数据库建立连接！");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return conn; // 返回Connection对象
}
    
    public static void main(String[] args) {
        CreateJoin join = new CreateJoin();
        Connection conn = join.getConn();
    }
    
}
