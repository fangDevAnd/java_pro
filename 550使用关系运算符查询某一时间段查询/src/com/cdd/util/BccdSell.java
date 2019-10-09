package com.cdd.util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
public class BccdSell {
    Connection conn = null;    
    // 获取数据库连接
    
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
    
public List getBccdSell() {
    List list = new ArrayList(); // 定义用于保存返回值的List集合
    conn = getConn(); // 获取数据库连接
   
    try {
        Statement staement = conn.createStatement();
        String sql = "select * from tb_bccdSell where bccdDate > '2010/6/1' and bccdDate < '2010/6/30'"; // 查询满足条件的SQL语句
        ResultSet set = staement.executeQuery(sql); // 执行查询语句返回查询结果集
        while (set.next()) { // 循环遍历查询结果集
          Bccd bccd = new Bccd();
          bccd.setId(set.getInt(1));
          bccd.setBccdName(set.getString("bccdName"));
          bccd.setBccdCount(set.getInt("bccdCount"));
          bccd.setBccdPrice(set.getFloat("bccdPrice"));
          bccd.setBccdDate(set.getString("bccdDate"));
          list.add(bccd);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return list; // 返回List集合
}
    
    public static void main(String[] args) {
        BccdSell sell = new BccdSell();
        List list = sell.getBccdSell();
        System.out.println("6月份有销售记录的词典信息：");
        for(int i = 0;i<list.size();i++){
            Bccd bccd = (Bccd)list.get(i);
            System.out.println("词典："+bccd.getBccdName()+" ，销量："+bccd.getBccdCount()+" ,售价："+bccd.getBccdPrice()+" ,日期："+bccd.getBccdDate());
        }
    }
}
