package com.cdd.jdbc;

import java.sql.*;
import java.io.*;

public class CreateJavaDBJoin {
    
    private static final String DRIVERCLASS = "org.apache.derby.jdbc.EmbeddedDriver"; // 数据库驱动
    private static final String URL = "jdbc:derby:db_database22";// 数据库URL
    private static final ThreadLocal<Connection> threadLocal = new ThreadLocal<Connection>(); // 创建用来保存数据库连接的线程
    private static Connection conn = null;// 数据库连接
    
    static { // 通过静态方法加载数据库驱动，并且在数据库不存在的情况下创建数据库
        try {
            Class.forName(DRIVERCLASS); // 加载数据库驱动
            System.out.println("数据库驱动加载成功！！");
            File albumF = new File("db_database22");// 创建数据库文件对象
            if (!albumF.exists()) {// 判断数据库文件是否存在
                String[] sqls = new String[1];// 定义创建数据库的SQL语句
                sqls[0] = "create table tb_album (name varchar(200) not null)";
            } else {
                conn = DriverManager.getConnection(URL + ";create=true");// 创建数据库连接
                System.out.println("已成功的与JavaDB数据库建立连接！！");
                threadLocal.set(conn);// 保存数据库连接
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    protected Connection getConnection() { // 创建数据库连接的方法
        conn = (Connection) threadLocal.get(); // 从线程中获得数据库连接
        if (conn == null) { // 没有可用的数据库连接
            try {
                conn = DriverManager.getConnection(URL);// 创建新的数据库连接
                threadLocal.set(conn); // 将数据库连接保存到线程中
                
            } catch (Exception e) {
                String[] infos = { "未能成功连接数据库！", "请确认本软件是否已经运行！" };
                
                e.printStackTrace();
            }
        }
        
        return conn;
    }
    
    protected boolean closeConnection() { // 关闭数据库连接的方法
        boolean isClosed = true; // 默认关闭成功
        conn = (Connection) threadLocal.get(); // 从线程中获得数据库连接
        threadLocal.set(null); // 清空线程中的数据库连接
        if (conn != null) { // 数据库连接可用
            try {
                conn.close(); // 关闭数据库连接
            } catch (SQLException e) {
                isClosed = false; // 关闭失败
                e.printStackTrace();
            }
        }
        return isClosed;
    }
    
    public static void main(String[] args) {
        CreateJavaDBJoin javaDBJoin = new CreateJavaDBJoin();
        javaDBJoin.getConnection();
    }
    
}
