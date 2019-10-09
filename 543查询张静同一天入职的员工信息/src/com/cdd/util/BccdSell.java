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
        String sql = "select * from tb_emp where convert(varchar(10),ddate,21) " +
        		"like (select convert(varchar(10),ddate,21) from tb_emp where name = '张静')"; // 定义查询数据的SQL语句
        ResultSet set = staement.executeQuery(sql); // 执行查询语句返回查询结果集
        while (set.next()) { // 循环遍历查询结果集
            Emp emp = new Emp();
            emp.setId(set.getInt(1));
            emp.setName(set.getString(2));
            emp.setSex(set.getString("sex"));
            emp.setDdate(set.getString("ddate"));
            emp.setDept(set.getString("dept"));
            list.add(emp);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return list; // 返回List集合
}
    
    public static void main(String[] args) {
        BccdSell sell = new BccdSell();
        List list = sell.getBccdSell();
        System.out.println("与张静同一天入司的员工有：");
        for(int i = 0;i<list.size();i++){
            Emp emp = (Emp)list.get(i);
            System.out.println("姓名： "+emp.getName()+"  ，性别："+emp.getSex()+" ，部门："+emp.getDept()+"   ，入司时间："+emp.getDdate());
         }
    }
}
