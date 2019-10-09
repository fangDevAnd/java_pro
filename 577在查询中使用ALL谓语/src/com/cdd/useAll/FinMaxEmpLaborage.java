package com.cdd.useAll;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class FinMaxEmpLaborage {
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
    
    public List getLaborage() {
        conn = getConn(); // 获取与数据库的连接
        ResultSet rest;
        List list = new ArrayList<Emp>();
        try {
            Statement statement = conn.createStatement(); // 获取Statement对象
            String sql = "select eName,headship,laborage from tb_emp  where laborage > all(select laborage from tb_emp where dept = '质量部')"; // 定义查询语句
            rest = statement.executeQuery(sql); // 执行查询语句获取查询结果集
            while (rest.next()) { // 循环遍历查询结果集
                Emp emp = new Emp(); // 定义域数据表对应的JavaBean对象
                emp.seteName(rest.getString(1)); // 设置对象属性
                emp.setHeadship(rest.getString(2));
                emp.setLaborage(rest.getFloat(3));
                list.add(emp); // 向集合中添加对象
            }
            return list; // 返回查询结果
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public static void main(String[] args) {
        FinMaxEmpLaborage maxLaborage = new FinMaxEmpLaborage();
        List list = maxLaborage.getLaborage();
        System.out.println("查询比质量部中所有员工工资都高的员工工资情况：");
        for (int i = 0; i < list.size(); i++) {
            Emp emp = (Emp) list.get(i);
            System.out.println("姓名：" + emp.geteName() + "  部门："
                    + emp.getHeadship() + "  工资：" + emp.getLaborage());
        }
    }
}
