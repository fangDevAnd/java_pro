package com.cdd.util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GradeOrderUtil {
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
    
    /**
     * @return
     */
    
public List getGradeOrder() {
    List list = new ArrayList(); // 定义用于保存返回值的List集合
    conn = getConn(); // 获取数据库连接
    try {
        Statement staement = conn.createStatement();
        String sql = "select * from tb_Grade order by name  collate chinese_prc_stroke_cs_as_ks_ws"; // 定义按笔画排序的SQL语句
        ResultSet set = staement.executeQuery(sql); // 执行查询语句返回查询结果集
        while (set.next()) { // 循环遍历查询结果集
            Grade grade = new Grade();  //实例化学生成绩对象
            grade.setId(set.getInt(1)); //设置对象的编号
            grade.setName(set.getString(2));
            grade.setChinses(set.getFloat("chinese"));
            grade.setEnglish(set.getFloat("english"));
            grade.setHistory(set.getFloat("history"));
            grade.setMath(set.getFloat("math"));
            grade.setPhysics(set.getFloat("physics"));
            list.add(grade);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return list; // 返回查询集合
}

    public static void main(String[] args) {
        GradeOrderUtil util = new GradeOrderUtil();
        List list = util.getGradeOrder();
        System.out.println("按姓氏笔画排序：");
        for (int i = 0; i < list.size(); i++) {
            Grade grade = (Grade)list.get(i);
            System.out.println("编号为："+grade.getId()+"  姓名："+grade.getName()+
                    "  语文："+grade.getChinses()+"  英语："+grade.getEnglish()+
                    "  历史："+grade.getHistory()+"  数学："+grade.getMath()+
                    "  物理："+grade.getPhysics());            
        }
    }
}
