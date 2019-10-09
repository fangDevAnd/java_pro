package com.cdd.derive;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
public class DeriveTable {
    private Connection conn ;   //定义Connection对象
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
    //定义用子查询作为派生的表方法
    
    public List getSubTable() {
        List list = new ArrayList<Emp>(); // 定义List集合对象
        conn = getConn(); // 获取与数据库的连接
        try {
            Statement statement = conn.createStatement(); // 获取Statement对象
            String sql = "select * from (select id,eName,headship,laborage from tb_emp)tb"; // 定义查询语句
            ResultSet rest = statement.executeQuery(sql); // 执行查询语句获取查询结果集
            while (rest.next()) { // 循环遍历查询结果集
                Emp emp = new Emp(); // 定义于数据表对应的JavaBean对象
                emp.setId(rest.getInt(1)); // 设置对象属性
                emp.setName(rest.getString(2));
                emp.setHeadship(rest.getString(3));
                emp.setLaborage(rest.getFloat(4));
                list.add(emp); // 将对象添加到集合中
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list; // 返回查询结果
    }
    
    //定义查询员工表中所有信息方法
    public List getFullMessage() {
        List list = new ArrayList<Emp>(); // 定义List集合对象
        conn = getConn(); // 获取与数据库的连接
        try {
            Statement statement = conn.createStatement(); // 获取Statement对象
            String sql = "select * from tb_emp"; // 定义查询语句
            ResultSet rest = statement.executeQuery(sql); // 执行查询语句获取查询结果集
            while (rest.next()) { // 循环遍历查询结果集
               Emp emp = new Emp();
               emp.setId(rest.getInt(1));
               emp.setName(rest.getString(2));
               emp.setHeadship(rest.getString(3));
               emp.setDept(rest.getString(4));
               emp.setJoinDate(rest.getString(5));
               emp.setLaborage(rest.getFloat(6));
               list.add(emp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list; // 返回查询结果
    }       
}
