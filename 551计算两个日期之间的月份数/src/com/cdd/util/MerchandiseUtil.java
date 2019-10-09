package com.cdd.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MerchandiseUtil {
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
                System.out.println("数据库连接成功！");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn; // 返回Connection对象
    }
    
    // 查询图书表中所有记录方法
    
public List getMerchandise() {
    conn = getConn(); // 获取数据库连接
    List list = new ArrayList(); // 定义保存返回值的List对象
    try {
        Statement staement = conn.createStatement(); // 定义Statement对象
        String sql = "select * from tb_merchandise"; // 定义查询的SQL语句
        ResultSet set = staement.executeQuery(sql); // 执行查询语句，返回查询结果集
        while (set.next()) { // 循环遍历查询结果集
            Merchandise merchandise = new Merchandise();
            merchandise.setId(set.getInt(1));
            merchandise.setWareName(set.getString(2));
            merchandise.setWareDate(set.getString(3));
            list.add(merchandise);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return list; // 返回查询结果
}
    
    // 定义按指定按指定条件降序查询数据方法

public String getgetMerchandiseDate(String term) {
    String date = "";
    conn = getConn(); // 获取数据库连接
    try {
        Statement staement = conn.createStatement();
        String sql = "select  datediff(mm,(select wareDate from tb_merchandise where wareName ='"
                + term + "'),getDate())"; // 定义将图书表中信息进行排序的SQL语句
        ResultSet set = staement.executeQuery(sql); // 执行查询语句返回查询结果集
        while (set.next()) { // 循环遍历查询结果集
            date = set.getString(1);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return date;
}
    public static void main(String[] args) {
        MerchandiseUtil util = new MerchandiseUtil();
        String date = util.getgetMerchandiseDate("A型手机");
        System.out.println(date);
    }
}
