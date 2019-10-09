package com.cdd.triiger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserTrigger {
    
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
    
    public int insertGrade(Grade grade) {
        conn = getConn(); // 获取数据库连接
        PreparedStatement cs = null; // 定义PreparedStatement对象
        int count = 0;
        try {
            String sql = "insert into tb_grade values(?,?,?,?)"; // 定义插入SQL语句
            cs = conn.prepareStatement(sql);
            cs.setString(1, grade.getName()); // 设置预处理语句参数
            cs.setFloat(2, grade.getMath());
            cs.setFloat(3, grade.getEnglist());
            cs.setFloat(4, grade.getChinese());
            count = cs.executeUpdate(); // 执行预处理语句，实现插入操作
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }
    
}
