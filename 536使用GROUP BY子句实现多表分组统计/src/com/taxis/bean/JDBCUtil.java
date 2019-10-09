package com.taxis.bean;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCUtil {
    
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
    
    // 定义按指定按指定条件降序查询数据方法
    
public ResultSet getBooKDesc() {
    List list = new ArrayList(); // 定义用于保存返回值的List集合
    conn = getConn(); // 获取数据库连接
    ResultSet set = null;
    try {
        Statement staement = conn.createStatement();
        String sql = "select cargoId,cargoName,cargoCount,sum(sellCount)as count "
                + " from tb_cargo,tb_sell where cargoId = sellId group by sellId,cargoName,cargoId,cargoCount"; // 定义查询数据库的SQL语句
        set = staement.executeQuery(sql); // 执行查询语句返回查询结果集            
    } catch (Exception e) {
        e.printStackTrace();
    }
    return set;
}
    public static void main(String[] args) {
        JDBCUtil util = new JDBCUtil();
        ResultSet set = util.getBooKDesc();
        try {
            System.out.println("有销售记录的商品进货数与销量：");
            while(set.next()){
                String cargoId = set.getString("cargoId");
                String cargoName = set.getString("cargoName");
                int cargoCont = set.getInt("cargoCount");
                int count = set.getInt("count");
                System.out.println("编号："+cargoId+"   名称："+cargoName+"   进货数："+cargoCont+"   销量："+count);
            }
        } catch (SQLException e) {         
            e.printStackTrace();
        }
    }
}
