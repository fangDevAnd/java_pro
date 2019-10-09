package com.cdd.delete;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DeleteGrade {
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
    
    // 查询所有学生信息
    public List executeGrade() {
        conn = getConn(); // 获取数据库连接
        Statement cs = null; // 定义CallableStatement对象
        String sql = "select * from tb_grade"; // 定义调用储存过程的SQL语句
        List list = new ArrayList();
        try {
            cs = conn.createStatement(); // 实例化Statement对象
            ResultSet rest = cs.executeQuery(sql); // 执行SQL语句
            while (rest.next()) { // 循环遍历查询结果集
                Grade grade = new Grade();
                grade.setId(rest.getInt(1));
                grade.setName(rest.getString(2));
                grade.setChinese(rest.getFloat("chinese"));
                grade.setEnglist(rest.getFloat("englist"));
                grade.setMath(rest.getFloat("math"));
                list.add(grade);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    
    // 查询所有学生信息
    public List executeStu() {
        conn = getConn(); // 获取数据库连接
        Statement cs = null; // 定义CallableStatement对象
        String sql = "select * from tb_stu"; // 定义调用储存过程的SQL语句
        List list = new ArrayList();
        try {
            cs = conn.createStatement(); // 实例化Statement对象
            ResultSet rest = cs.executeQuery(sql); // 执行SQL语句
            while (rest.next()) { // 循环遍历查询结果集
                Stu stu = new Stu();
                stu.setId(rest.getInt(1));
                stu.setName(rest.getString(2));
                stu.setSex(rest.getString(3));
                stu.setSpecialty(rest.getString(4));
                list.add(stu);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    
    // 删除学生成绩信息
    
    public void deleteGrade(int id) {
        conn = getConn(); // 获取数据库连接
        try {
            Statement statement = conn.createStatement(); // 定义Statement方法
            statement.executeUpdate("delete from tb_grade where id=" + id); // 执行删除操作
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
}
