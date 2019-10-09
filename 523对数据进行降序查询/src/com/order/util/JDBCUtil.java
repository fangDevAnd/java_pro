package com.order.util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCUtil {
    
    Connection conn = null;
    
    // 获取数据库连接

public Connection getConn() {
    try {
        Class.forName("net.sourceforge.jtds.jdbc.Driver");  //加载数据库驱动
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
    
    //查询图书表中所有记录方法

public List getBook() {
    conn = getConn();   //获取数据库连接
    List list = new ArrayList();    //定义保存返回值的List对象
    try {
        Statement staement = conn.createStatement();    //定义Statement对象
        String sql = "select * from tb_book";      //定义查询的SQL语句
        ResultSet set = staement.executeQuery(sql); //执行查询语句，返回查询结果集
        while (set.next()) {            //循环遍历查询结果集
            Book book = new Book();     //定义与数据表对应的JavaBean对象
            book.setId(set.getInt(1));  //设置对象的id值
            book.setBookName(set.getString(2));
            book.setAuthor(set.getString("author"));
            book.setPrice(set.getFloat("price"));
            book.setStock(set.getInt("stock"));
            list.add(book);             //向集合中添加对象
        }
    } catch (Exception e) { 
        e.printStackTrace();
    }
    return list;                        //返回查询结果
}
    
    //定义按指定按指定条件降序查询数据方法

public List getBooKDesc(String term){
    List list = new ArrayList();    //定义用于保存返回值的List集合
    conn = getConn();               //获取数据库连接
    try {
        Statement staement = conn.createStatement();
        String sql = "select * from tb_book order by "+term +" desc";   //定义将图书表中信息进行排序的SQL语句
        ResultSet set = staement.executeQuery(sql); //执行查询语句返回查询结果集
        while (set.next()) {    //循环遍历查询结果集
            Book book = new Book();     
            book.setId(set.getInt(1));
            book.setBookName(set.getString(2));
            book.setAuthor(set.getString("author"));
            book.setPrice(set.getFloat("price"));
            book.setStock(set.getInt("stock"));
            list.add(book);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return list;    
}    
}
