package com.cdd.getView;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class GetViews {
 private Connection conn;
    
    public Connection getConn() {
        try {
            Class.forName("net.sourceforge.jtds.jdbc.Driver"); // 加载数据库驱动
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        String url = "jdbc:jtds:sqlserver://localhost:1433;DatabaseName=db_database24"; // 连接数据库URL
        String userName = "sa"; // 连接数据库的用户名
        String passWord = ""; // 连接数据库密码
        try {
            conn = DriverManager.getConnection(url, userName, passWord); // 获取数据库连接
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn; // 返回Connection对象
    }
    

public List selectView() {
    conn = getConn(); // 获取数据库连接
    Statement cs = null; // 定义Statement对象
    String sql = "Select name from Sysobjects where xtype='V' and status > 0"; // 定义获取所有视图的SQL语句
    List list = new ArrayList(); // 定义保存查询结果的List集合
    try {
        cs = conn.createStatement(); // 实例化Statement对象
        ResultSet rest = cs.executeQuery(sql); // 执行SQL语句
        while (rest.next()) { // 循环遍历查询结果集
            String name = rest.getString(1);
            list.add(name);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return list;
}
    public static void main(String[] args) {
        GetViews getViews = new GetViews();
        List list = getViews.selectView();
        for(int i = 0;i<list.size();i++){
            String name = list.get(i).toString();
            System.out.println(name);
        }
    }
}
