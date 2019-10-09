package com.cdd.util;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ClearUtil {
    // 获取数据库连接
    private Connection conn;
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

public void deleteDate(String dataName){
    conn = getConn();   //获取数据库连接
    try {
        Statement statement = conn.createStatement();   //获取Statement对象
        statement.executeUpdate("TRUNCATE TABLE  "+dataName);               //指定删除语句
    } catch (Exception e) {       
        e.printStackTrace();
    }
    
}
    //定义查询数据库中所有数据表方法
    public List GetRs() {
        List list = new ArrayList<String>();
        try {            
            String[] tableType = { "TABLE" }; // 指定要进行查询的表类型
            Connection conn = getConn(); // 调用与数据库建立连接方法
            DatabaseMetaData databaseMetaData = conn.getMetaData(); // 获取DatabaseMetaData实例
            ResultSet resultSet = databaseMetaData.getTables(null, null, "%",
                    tableType);// 获取数据库中所有数据表集合
            while(resultSet.next()){
                String tableName = resultSet.getString("TABLE_NAME");
                list.add(tableName);
            }          
        } catch (SQLException e) {
            System.out.println("记录数量获取失败！");
          
        }
        return list;
    }
    
    public static void main(String[] args) {
        ClearUtil util = new ClearUtil();
        util.deleteDate("tb_empess");
    }
    
}
