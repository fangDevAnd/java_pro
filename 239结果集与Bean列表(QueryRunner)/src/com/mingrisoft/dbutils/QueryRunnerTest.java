package com.mingrisoft.dbutils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

public class QueryRunnerTest {
    // 定义JDBC相关参数
    private static String URL = "jdbc:mysql://localhost:3306/db_database18";
    private static String DRIVER = "com.mysql.jdbc.Driver";
    private static String USERNAME = "root";
    private static String PASSWORD = "111";
    private static Connection conn;
    
    public static Connection getConnection() {// 用于获得数据库连接的工具方法
        try {
            DbUtils.loadDriver(DRIVER);// 加载驱动
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);// 建立连接
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
    
    public static List<User> query(String sql) {// 用来将查询结果转换成bean列表的工具方法
        QueryRunner qr = new QueryRunner();
        List<User> list = null;
        try {
            list = qr.query(getConnection(), sql, new BeanListHandler<User>(User.class));
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbUtils.closeQuietly(conn);// 关闭连接
        }
        return list;
    }
    
    public static void main(String[] args) {
        System.out.println("表users中的全部数据如下：");
        List<User> list = query("select * from users");// 查询users表中全部数据
        for (User user : list) {
            System.out.println(user);
        }
    }
}
