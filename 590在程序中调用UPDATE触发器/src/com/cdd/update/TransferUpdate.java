package com.cdd.update;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class TransferUpdate {
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
    //查询所有学生成绩信息
    public List executeTeacher() {
        conn = getConn(); // 获取数据库连接
        Statement cs = null; // 定义CallableStatement对象
        String sql = "select * from tb_teacher"; // 定义调用储存过程的SQL语句
        List list = new ArrayList();
        try {
            cs = conn.createStatement(); // 实例化Statement对象
            ResultSet rest = cs.executeQuery(sql); // 执行SQL语句
            while (rest.next()) { // 循环遍历查询结果集
                Teacher teacher = new Teacher();
                teacher.setId(rest.getInt(1));
                teacher.settName(rest.getString(2));
                teacher.setCourse(rest.getString(3));
                list.add(teacher);
            }
        } catch (SQLException e) {
            e.printStackTrace();            
        }
        return list;
    }
    //查询指定编号的教师信息
    public Teacher selectTeacher(int id) {
        conn = getConn(); // 获取数据库连接
        Statement cs = null; // 定义CallableStatement对象
        String sql = "select * from tb_teacher where id ="+id; // 定义调用储存过程的SQL语句
        Teacher teacher = new Teacher();
        try {
            cs = conn.createStatement(); // 实例化Statement对象
            ResultSet rest = cs.executeQuery(sql); // 执行SQL语句
            while (rest.next()) { // 循环遍历查询结果集               
                teacher.setId(rest.getInt(1));
                teacher.settName(rest.getString(2));
                teacher.setCourse(rest.getString(3));               
            }
        } catch (SQLException e) {
            e.printStackTrace();            
        }
        return teacher;
    }

public void updateTeacher(Teacher teacher){
    conn = getConn();       //获取数据库连接
    try {
        PreparedStatement statement = conn.prepareStatement("update tb_teacher set tName=?,course = ? where id = ?") ;  //定义PreparedStatement对象
        statement.setString(1, teacher.gettName());     //设置预处理语句的参数
        statement.setString(2, teacher.getCourse());
        statement.setInt(3, teacher.getId());
        statement.executeUpdate();      //执行删除操作      
    } catch (SQLException e) {          
        e.printStackTrace();
    }        
}
   
}
