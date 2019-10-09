package com.cdd.findStu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class FindStu {
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
    
    public List getSubTable() {
        List list = new ArrayList<Student>(); // 定义List集合对象
        conn = getConn(); // 获取与数据库的连接
        try {
            Statement statement = conn.createStatement(); // 获取Statement对象
            String sql = "select * from tb_student where name in (select name from  tb_grade where  ((math+english+chinese)/3)>=85)"; // 定义查询语句
            ResultSet rest = statement.executeQuery(sql); // 执行查询语句获取查询结果集
            while (rest.next()) { // 循环遍历查询结果集
                Student student = new Student(); // 定义于数据表对应的JavaBean对象
                student.setId(rest.getInt(1)); // 设置对象属性
                student.setName(rest.getString(2));
                student.setCollege(rest.getString(3));
                student.setSpeciality(rest.getString(4));
                student.setAddress(rest.getString(5));
                list.add(student); // 向集合中添加元素
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list; // 返回查询结果
    }
    
}
