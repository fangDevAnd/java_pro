package com.cdd.format;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class GetFormat {
 private Connection conn;
    
    public Connection getConn() {
        try {
            Class.forName("net.sourceforge.jtds.jdbc.Driver"); // 加载数据库驱动
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        String url = "jdbc:jtds:sqlserver://localhost:1433;DatabaseName=db_database24"; // 连接数据库URL
        String userName = "sa"; // 连接数据库的用户名
        String passWord = ""; // 连接数据库密码
        try {
            conn = DriverManager.getConnection(url, userName, passWord); // 获取数据库连接
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn; // 返回Connection对象
    }
    
    // 从视图中检索数据

public List selectView() {
    conn = getConn(); // 获取数据库连接
    Statement cs = null; // 定义CallableStatement对象
    String sql = "Select * from v_emp"; // 定义查询视图的SQL语句
    List list = new ArrayList(); // 定义保存查询结果的List集合
    try {
        cs = conn.createStatement(); // 实例化Statement对象
        ResultSet rest = cs.executeQuery(sql); // 执行SQL语句
        while (rest.next()) { // 循环遍历查询结果集
            Emp emp = new Emp();
            emp.setName(rest.getString(1));
            emp.setEdate(rest.getString(2));
            list.add(emp);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return list;
}
   
    public static void main(String[] args) {
        GetFormat format = new GetFormat();
        List list = format.selectView();
        System.out.println("使用视图重新格式化检索出的数据");
        for(int i = 0;i<list.size();i++){
           Emp emp = (Emp)list.get(i);
            System.out.println("员工姓名："+emp.getName()+"  入司时间："+emp.getEdate());
       }
   }
   
}
