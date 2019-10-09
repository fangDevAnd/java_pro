package com.cdd.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class WareUtil {
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
    public List getWare() {
        conn = getConn(); // 获取与数据库的连接
        ResultSet rest;
        List list = new ArrayList();
        try {
            Statement statement = conn.createStatement(); // 获取Statement对象
            String sql = "select id,wName,price,convert(varchar(30),price/(select sum(price) from tb_ware) * 100)+'%' as percente from tb_ware"; // 定义查询语句
            rest = statement.executeQuery(sql); // 执行查询语句获取查询结果集
            while (rest.next()) { // 循环遍历查询结果集
                Ware ware = new Ware(); // 定义与数据表对应的JavaBean对象
                ware.setId(rest.getInt(1)); // 设置对象属性
                ware.setwName(rest.getString(2));
                ware.setPrice(rest.getFloat(3));
                ware.setPercent(rest.getString(4));
                list.add(ware); // 像集合中添加对象
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list; // 返回集合
    }
}
