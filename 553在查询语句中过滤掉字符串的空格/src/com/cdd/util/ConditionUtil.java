package com.cdd.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ConditionUtil {
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
    //在查询结果集中去除字符串中的空格     

public List getBookSell() {
    List list = new ArrayList(); // 定义用于保存返回值的List集合
    conn = getConn(); // 获取数据库连接
    try {
        Statement staement = conn.createStatement();
        String sql = "select * from tb_book"; // 定义查询图书销售表中的全部数据
        ResultSet set = staement.executeQuery(sql); // 执行查询语句返回查询结果集
        while (set.next()) { // 循环遍历查询结果集
            BookSell sell = new BookSell();     //定义与数据表对应的JavaBean对象
            sell.setId(set.getInt(1));          //设置对象属性
            sell.setBookName(set.getString(2).replace(" ", ""));
            sell.setStock(set.getString(3).replace(" ", ""));
            sell.setPrice(set.getFloat(4));
            sell.setBookConcern(set.getString(5).replace(" ", ""));
            list.add(sell);                     //将对象添加到集合中
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return list;                    //返回查询结果
}
    
}
