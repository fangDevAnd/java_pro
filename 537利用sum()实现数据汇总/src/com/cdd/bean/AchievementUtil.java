package com.cdd.bean;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AchievementUtil {
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

public float getBooKDesc() {
    conn = getConn(); // 获取数据库连接
    float sum = 0;
    try {
        Statement staement = conn.createStatement();
        String sql = "select sum(achievement)as sum from tb_achievement where monthCount <= 3"; // 定义查找第一季度定义总和的SQL语句
        ResultSet set = staement.executeQuery(sql); // 执行查询语句返回查询结果集
        while (set.next()) { // 循环遍历查询结果集
            sum = set.getFloat(1);         //获取查询结果       
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return sum;
}
    
    public static void main(String[] args) {
        AchievementUtil util = new AchievementUtil();
        float ft = util.getBooKDesc();
        System.out.println("订单表中第一季度的总订单金额为： "+ft);
    }
}
