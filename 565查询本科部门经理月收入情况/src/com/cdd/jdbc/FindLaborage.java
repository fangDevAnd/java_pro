package com.cdd.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class FindLaborage {
    
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
    
    public ResultSet getMessageEmp() {
        conn = getConn(); // 获取与数据库的连接
        try {
            Statement statement = conn.createStatement(); // 获取Statement对象
            String sql = "select distinct dName,laborage.name,laborage.laborage,lYear,lDate from tb_laborage laborage,tb_dept dept,tb_employee emp "
                    + "where laborage.name in(select name from tb_employee where job = '部门经理' "
                    + "and schoolAge = '本科' and dID =  dept.id )"; // 定义查询语句
            ResultSet rest = statement.executeQuery(sql); // 执行查询语句获取查询结果集
            return rest; // 返回查询结果
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public static void main(String[] args) {
        FindLaborage Dlaborage = new FindLaborage(); // 创建本类对象
        ResultSet rest = Dlaborage.getMessageEmp(); // 调用查询方法
        System.out.println("查询本科的部门经理的月收入情况");
        try {
            while (rest.next()) { // 循环遍历查询结果集
                String dName = rest.getString(1); // 获取结果集中信息
                String name = rest.getString(2);
                float laborage = rest.getFloat(3);
                int lYear = rest.getInt(4);
                int lDate = rest.getInt(5);
                System.out.println("姓名：" + name + " 部门：" + dName + " 工资："
                        + laborage + " 年份：" + lYear + " 月份：" + lDate);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
