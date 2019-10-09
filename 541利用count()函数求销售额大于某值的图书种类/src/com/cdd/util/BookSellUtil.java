package com.cdd.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BookSellUtil {
    
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
    
    public int getMAXOrder() {
        List list = new ArrayList(); // 定义用于保存返回值的List集合
        conn = getConn(); // 获取数据库连接
        int count = 0;
        try {
            Statement staement = conn.createStatement();
            String sql = "select count(bookName) as bookType from tb_bookSell where total >400"; // 查询日销售额在400以上的图书种类
            ResultSet set = staement.executeQuery(sql); // 执行查询语句返回查询结果集
            while (set.next()) { // 循环遍历查询结果集
                count = set.getInt("bookType");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count; // 返回List集合
    }
    
    public static void main(String[] args) {
        BookSellUtil util = new BookSellUtil();
        int count = util.getMAXOrder();
        System.out.println("日销售额超过400的图书种类有：" + count + "种");
    }
}
