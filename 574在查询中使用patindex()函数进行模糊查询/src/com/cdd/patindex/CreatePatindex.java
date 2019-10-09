package com.cdd.patindex;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CreatePatindex {
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

public List getPatindex(String address) {
    conn = getConn(); // 获取与数据库的连接
    ResultSet rest;
    List list = new ArrayList<Order>();
    try {
        Statement statement = conn.createStatement(); // 获取Statement对象
        String sql = "select * from tb_order where patindex('" + address
                + "%',address)>0"; // 定义查询语句
        rest = statement.executeQuery(sql); // 执行查询语句获取查询结果集
        while (rest.next()) {           //循环遍历查询结果集
            Order order = new Order();      //定义与数据表对应的JavaBean对象
            order.setId(rest.getInt(1));        //设置对象属性
            order.setName(rest.getString(2));
            order.setAddress(rest.getString(3));
            order.setPhone(rest.getString(4));
            list.add(order);
        }
        return list; // 返回查询结果
    } catch (Exception e) {
        e.printStackTrace();
        return null;
    }
}
    
}
