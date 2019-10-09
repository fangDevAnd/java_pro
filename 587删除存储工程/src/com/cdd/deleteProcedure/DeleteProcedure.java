package com.cdd.deleteProcedure;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DeleteProcedure {
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
    
    public List executeGain() {
        conn = getConn(); // 获取数据库连接
        Statement cs = null;
        String sql = "select name from sysobjects where xtype = 'p' and status > 0"; // 定义调用储存过程的SQL语句
        List list = new ArrayList(); // 定义用于返回值的集合对象
        try {
            cs = conn.createStatement();
            ResultSet rest = cs.executeQuery(sql); // 执行SQL语句
            while (rest.next()) { // 循环遍历查询结果集
                String name = rest.getString(1); // 获取查询结果集中数据
                list.add(name); // 将数据添加到集合中
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list; // 返回查询结果集
    }
    
    public boolean executeUpdate(String[] sql) {
        conn = getConn(); // 获取数据库连接
        try {
            Statement stmt = conn.createStatement(); // 实例化Statement对象
            for (int i = 0; i < sql.length; i++) {
                stmt.executeUpdate("DROP PROCEDURE " + sql[i]); // 执行删除操作
            }
            conn.close(); // 关闭连接
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    
}
