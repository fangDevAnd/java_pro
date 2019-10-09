package com.cdd.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UpperAndLower {
    Connection conn = null;    
    // 获取数据库连接
    public Connection getConn() {
        try {
            Class.forName("net.sourceforge.jtds.jdbc.Driver"); // 加载数据库驱动
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        String url = "jdbc:jtds:sqlserver://localhost:1433;DatabaseName=db_database22"; // 连接数据库URL
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
    
    public List getUpperAndLower() {
        List list = new ArrayList(); // 定义用于保存返回值的List集合
        conn = getConn(); // 获取数据库连接
        try {
            Statement staement = conn.createStatement();
            String sql = "select upper(FristName),lower(LastName),nationality,speciality from tb_abroad"; // 定义查询数据的SQL语句
            ResultSet set = staement.executeQuery(sql); // 执行查询语句返回查询结果集
            while (set.next()) { // 循环遍历查询结果集
                Abord abord = new Abord(); // 定义与数据表对应的JavaBean对象
                abord.setFristName(set.getString(1)); // 设置对象属性
                abord.setLastName(set.getString(2));
                abord.setNationality(set.getString(3));
                abord.setSpeciality(set.getString(4));
                list.add(abord);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list; // 返回List集合
    }
}
