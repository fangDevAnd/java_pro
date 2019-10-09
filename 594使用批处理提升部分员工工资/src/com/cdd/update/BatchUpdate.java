package com.cdd.update;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BatchUpdate {
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
    
    // 查询所有学生成绩信息
    public List executeStu() {
        conn = getConn(); // 获取数据库连接
        Statement cs = null; // 定义CallableStatement对象
        String sql = "select distinct dept from tb_laborage"; // 定义调用储存过程的SQL语句
        List list = new ArrayList();
        try {
            cs = conn.createStatement(); // 实例化Statement对象
            ResultSet rest = cs.executeQuery(sql); // 执行SQL语句
            while (rest.next()) { // 循环遍历查询结果集
                String dept = rest.getString(1);
                list.add(dept);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public void updateBatch(Object[] dept, int laborage) {
        conn = getConn(); // 获取数据库连接
        Statement cs = null; // 定义Statement对象
        try {
            cs = conn.createStatement(); // 实例化Statement对象
            for (int i = 0; i < dept.length; i++) {
                cs.addBatch("update tb_laborage set laborage = laborage +"
                        + laborage + " where dept = '" + dept[i] + "'"); // 修改数据
            }
            cs.executeBatch(); // 批量执行SQL语句
            cs.close(); // 将Statement对象关闭
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
