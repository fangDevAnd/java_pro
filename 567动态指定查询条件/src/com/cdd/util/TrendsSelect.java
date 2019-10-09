package com.cdd.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TrendsSelect {
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
    
    public List getGrade(String operator, String denotation, int mark) {
        conn = getConn(); // 获取与数据库的连接
        ResultSet rest;
        List list = new ArrayList();
        try {
            Statement statement = conn.createStatement(); // 获取Statement对象
            String sql = "select * from tb_grade where " + operator
                    + denotation + mark; // 定义查询语句
            rest = statement.executeQuery(sql); // 执行查询语句获取查询结果集
            while (rest.next()) { // 循环遍历查询结果集
                Grade grade = new Grade(); // 定义与数据表对应的JavaBean对象
                grade.setId(rest.getInt(1)); // 设置对象属性值
                grade.setName(rest.getString(2));
                grade.setSex(rest.getString(3));
                grade.setEnglish(rest.getInt(4));
                grade.setChinese(rest.getInt(5));
                grade.setMath(rest.getInt(6));
                list.add(grade);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list; // 返回集合
    }
    
}
