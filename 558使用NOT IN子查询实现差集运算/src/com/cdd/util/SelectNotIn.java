package com.cdd.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SelectNotIn {
    Connection conn = null;
    
    // 获取数据库连接
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
            if (conn != null) {
                
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn; // 返回Connection对象
    }

public List getNotIn() {
    List list = new ArrayList(); // 定义用于保存返回值的List集合
    conn = getConn(); // 获取数据库连接
    try {
        Statement staement = conn.createStatement();
        String sql = "select * from tb_student where name not in (select name from tb_grade)"; // 定义查询数据的SQL语句
        ResultSet set = staement.executeQuery(sql); // 执行查询语句返回查询结果集
        while (set.next()) { // 循环遍历查询结果集
            Student student = new Student();        //定义与数据库对应的JavaBean对象
            student.setId(set.getInt(1));           //设置对象属性
            student.setName(set.getString(2));
            student.setCollege(set.getString(3));
            student.setSpeciality(set.getString(4));
            student.setAddress(set.getString(5));
            list.add(student);                  //将对象添加到集合中
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return list; // 返回List集合
}
    
    public static void main(String[] args) {
        SelectNotIn notin = new SelectNotIn();
        List list = notin.getNotIn();
        System.out.println("查询没有成绩的学生信息：");
        for (int i = 0; i < list.size(); i++) {
            Student student = (Student) list.get(i);
            System.out.println("编号：" + student.getId() +"  姓名："+ student.getName()
                    +"  学院："+ student.getCollege() +"  专业："+ student.getSpeciality()
                    +"  地址："+ student.getAddress());
        }
    }
}
