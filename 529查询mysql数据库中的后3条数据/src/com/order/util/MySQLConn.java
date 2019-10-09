package com.order.util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLConn {
    
    Connection conn = null;    
    public Connection getConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver"); // 加载MySQL数据库驱动
            String url = "jdbc:mysql://localhost:3306/db_database21"; // 定义与连接数据库的url
            String user = "root"; // 定义连接数据库的用户名
            String passWord = "111"; // 定义连接数据库的密码
            conn = DriverManager.getConnection(url, user, passWord); // 连接连接
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }    
    // 定义按指定按指定条件降序查询数据方法    
    public List getOrderDesc() {
        List list = new ArrayList(); // 定义用于保存返回值的List集合
        conn = getConnection(); // 获取数据库连接
        try {
            Statement staement = conn.createStatement();
            String sql = "select id,name,sex,className,chinese from tb_student order by chinese limit 0,3"; // 定义查询数据表中后3条记录的SQL语句
            ResultSet set = staement.executeQuery(sql); // 执行查询语句返回查询结果集
            while (set.next()) { // 循环遍历查询结果集
                Student student = new Student(); // 定义与数据库对应的JavaBean对象
                student.setId(set.getInt(1)); // 设置对象属性值
                student.setName(set.getString("name"));
                student.setSex(set.getString("sex"));
                student.setClassName(set.getString("className"));
                student.setChinese(set.getFloat("chinese"));
                list.add(student); // 将JavaBean添加到集合中
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public static void main(String[] args) {
        MySQLConn mySqlConn = new MySQLConn();
        List list = mySqlConn.getOrderDesc();
        System.out.println("查询语文成绩排在后3名的同学信息：");
        for (int i = 0; i < list.size(); i++) {
            Student student = (Student) list.get(i);
            System.out.println("编号为：" + student.getId() + "，姓名："
                    + student.getName() + "，性别：" + student.getSex() + "，语文成绩："
                    + student.getChinese());
        }
    }
}
