package com.cdd.util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ResultUtil {
    // 获取数据库连接
    private Connection conn;
    
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

public List getGradeDesc() {
    List list = new ArrayList(); // 定义用于保存返回值的List集合
    conn = getConn(); // 获取数据库连接
    try {
        Statement staement = conn.createStatement();
        String sql = "select top 3 id ,name,english from tb_Grade order by english desc"; // 定义将图书表中信息进行排序的SQL语句
        ResultSet set = staement.executeQuery(sql); // 执行查询语句返回查询结果集
        while (set.next()) { // 循环遍历查询结果集
            Grade grade = new Grade(); // 创建于学生成绩表对应的Grade对象
            grade.setId(set.getInt(1)); // 设置对象属性
            grade.setName(set.getString(2));
            grade.setEnglish(set.getFloat(3));
            list.add(grade); // 将Grade对象添加到集合中
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return list; // 返回查询集合
}
    
    public static void main(String[] args) {
        ResultUtil util = new ResultUtil();
        List list = util.getGradeDesc();
        System.out.println("英语成绩排在前3的同学是：");
        for(int i = 0;i < list.size();i++){
            Grade grade = (Grade)list.get(i);
            System.out.println("编号："+grade.getId()+" ，姓名："+grade.getName()+"  ，英语成绩："+grade.getEnglish());
        }
    }
    
}
