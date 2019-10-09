package com.cdd.avg;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class GetEnglishAvg {
    private Connection conn; // 定义Connection对象
    
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
    /**
     * @param args
     */
    
    public List getAvg() {
        List list = new ArrayList<Grade>(); // 定义List集合对象
        conn = getConn(); // 获取与数据库的连接
        try {
            Statement statement = conn.createStatement(); // 获取Statement对象
            String sql = "select id,name,english,( select avg(english) from tb_grade ) as avgEnglish,"
                    + "(english-( select avg(english) from tb_grade )) as diffAvgEnglish from tb_grade"; // 定义查询语句
            ResultSet rest = statement.executeQuery(sql); // 执行查询语句获取查询结果集
            while (rest.next()) { // 循环遍历查询结果集
                Grade grade = new Grade(); // 定义与数据表对应的JavaBean对象
                grade.setId(rest.getInt(1)); // 设置对象属性
                grade.setName(rest.getString(2));
                grade.setEnglish(rest.getFloat(3));
                grade.setAvgEng(rest.getFloat(4));
                grade.setBalance(rest.getFloat(5));
                list.add(grade); // 向集合中添加元素
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list; // 返回查询结果
    }    
    public static void main(String[] args) {
        GetEnglishAvg getEnglishAvg = new GetEnglishAvg();
        List list = getEnglishAvg.getAvg();
        System.out.println("查询学生英语成绩、英语平均成绩、成绩差额：");
        for (int i = 0; i < list.size(); i++) {
            Grade grade = (Grade) list.get(i);
            System.out.println("编号：" + grade.getId() + " 姓名：" + grade.getName()
                    + "  英语成绩：" + grade.getEnglish() + " 平均成绩："
                    + grade.getAvgEng() + " 成绩差额：" + grade.getBalance());
        }
        
    }
    
}
