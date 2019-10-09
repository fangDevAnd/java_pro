package com.cdd.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class StudentUtil {
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
    // 定义按指定按指定条件降序查询数据方法    
public List getMINStudent() {
    List list = new ArrayList(); // 定义用于保存返回值的List集合
    conn = getConn(); // 获取数据库连接
    try {
        Statement staement = conn.createStatement();
        String sql = "select name,sex,age,specialty from tb_student where age = (select min(age) from  tb_student)"; // 定义查询学生表中年龄最小的学生信息SQL语句
        ResultSet set = staement.executeQuery(sql); // 执行查询语句返回查询结果集
        while (set.next()) { // 循环遍历查询结果集
          Student student = new Student();  //定义学生对象
          student.setName(set.getString("name"));   //设置学生对象的姓名属性
          student.setSex(set.getString("sex"));
          student.setAge(set.getInt("age"));
          student.setSpecialty(set.getString("specialty"));
          list.add(student);                //将学生对象添加到集合中
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return list;        //返回List集合
}
    public static void main(String[] args) {
        StudentUtil util = new StudentUtil();
        List list = util.getMINStudent();
        System.out.println("查询年纪最小的学生信息：");
        for(int i = 0;i<list.size();i++){
            Student student = (Student)list.get(i);
            System.out.println("学生姓名："+student.getName()+
                    " ，学生性别："+student.getSex()+" ，学生年龄："+student.getAge()+" ，所学专业："
                    +student.getSpecialty());
        }
    }
}
