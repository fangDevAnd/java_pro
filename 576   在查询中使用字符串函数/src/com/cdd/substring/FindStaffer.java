package com.cdd.substring;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class FindStaffer {

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

public List getBirthday() {
    conn = getConn(); // 获取与数据库的连接
    ResultSet rest;
    List list = new ArrayList<Staffer>();
    try {
        Statement statement = conn.createStatement(); // 获取Statement对象
        String sql = "select id,sName,substring(code,7,8) as birthday ,code,degree,job from tb_staffer"; // 定义查询语句
        rest = statement.executeQuery(sql); // 执行查询语句获取查询结果集
        while (rest.next()) { // 循环遍历查询结果集
            Staffer staffer = new Staffer();    //定义与数据库对应的JavaBean方法
            staffer.setId(rest.getInt(1));      //设置对象属性
            staffer.setsName(rest.getString(2));
            staffer.setBirthday(rest.getString(3));
            staffer.setCode(rest.getString(4));
            staffer.setDegree(rest.getString(5));
            staffer.setJob(rest.getString(6));
            list.add(staffer);                  //将对象添加到集合中
        }
        return list; // 返回查询结果
    } catch (Exception e) {
        e.printStackTrace();
        return null;
    }
}
}
