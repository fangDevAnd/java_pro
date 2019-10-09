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
                System.out.println("数据库连接成功！");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn; // 返回Connection对象
    }
    
    // 查询图书表中所有记录方法
    
    public List getGrade() {
        conn = getConn(); // 获取数据库连接
        List list = new ArrayList(); // 定义保存返回值的List对象
        try {
            Statement staement = conn.createStatement(); // 定义Statement对象
            String sql = "select * from tb_Grade"; // 定义查询的SQL语句
            ResultSet set = staement.executeQuery(sql); // 执行查询语句，返回查询结果集
            while (set.next()) { // 循环遍历查询结果集
                Grade grade = new Grade();
                grade.setId(set.getInt(1));
                grade.setName(set.getString(2));
                grade.setChinses(set.getFloat(3));
                grade.setEnglish(set.getFloat("english"));
                grade.setHistory(set.getFloat("history"));
                grade.setMath(set.getFloat("math"));
                grade.setPhysics(set.getFloat("physics"));
                list.add(grade);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list; // 返回查询结果
    }
    
    // 定义按指定按指定条件降序查询数据方法
    
    public List getGradeDesc(String taxis) {
        List list = new ArrayList(); // 定义用于保存返回值的List集合
        conn = getConn(); // 获取数据库连接
        try {
            Statement staement = conn.createStatement();
            String sql = "select id,name,(chinese+math+english+physics+history) as sum from tb_grade order by sum "
                    + taxis; // 定义将图书表中信息进行排序的SQL语句
            ResultSet set = staement.executeQuery(sql); // 执行查询语句返回查询结果集
            while (set.next()) { // 循环遍历查询结果集
                Grade grade = new Grade(); // 创建于学生成绩表对应的Grade对象
                grade.setId(set.getInt(1)); // 设置对象属性
                grade.setName(set.getString(2));
                grade.setSum(set.getFloat("sum"));
                list.add(grade); // 将Grade对象添加到集合中
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list; // 返回查询集合
    }
    
}
