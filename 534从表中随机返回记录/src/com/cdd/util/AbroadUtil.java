package com.cdd.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadPoolExecutor.AbortPolicy;

public class AbroadUtil {
    Connection conn = null;
    
    // 获取数据库连接
    public Connection getConn() {
        try {
            Class.forName("net.sourceforge.jtds.jdbc.Driver"); // 加载数据库驱动
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        String url = "jdbc:jtds:sqlserver://localhost:1433;DatabaseName=db_database21"; // 连接数据库URL
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
    
    public List getAbroad() {
        List list = new ArrayList(); // 定义用于保存返回值的List集合
        conn = getConn(); // 获取数据库连接
        try {
            Statement staement = conn.createStatement();
            String sql = "select top 3 * from tb_abroads  order by newid()"; // 定义查询数据的SQL语句
            ResultSet set = staement.executeQuery(sql); // 执行查询语句返回查询结果集
            while (set.next()) { // 循环遍历查询结果集
                Abroad abord = new Abroad(); // 定义与数据表对应的JavaBean对象
                abord.setId(set.getInt(1));
                abord.setFristName(set.getString(2)); // 设置对象属性
                abord.setLastName(set.getString(3));
                abord.setNationality(set.getString(4));
                abord.setSpeciality(set.getString(5));
                list.add(abord);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list; // 返回List集合
    }
    
}
