package com.mingrisoft.dbutils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;

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
    
    public static int operate(String sql, Object... params) {// 用于执行有参数的SQL语句
        int result = 0;
        QueryRunner runner = new QueryRunner();
        try {
            result = runner.update(getConnection(), sql, params);// 执行SQL语句
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbUtils.closeQuietly(conn);// 关闭连接
        }
        return result;
    }
    
    public static void main(String[] args) {
        String sql = "insert into users(username, password) values (?, ?)";
        Object[] params = { "mrsoft", "Java" };
        operate(sql, params);// 向数据库中插入一条数据
    }
}
