package com.cdd.Descartes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
public class GetDescartes {
    
    private Connection conn;
    
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
    
    // 获取笛卡尔乘积方法

public List getDescsrtes() {
    List list = new ArrayList<MrEmp>();
    conn = getConn(); // 获取与数据库的连接
    try {
        Statement statement = conn.createStatement(); // 获取Statement对象
        String sql = "select tb_mrdept.*,tb_mremp.name,tb_mremp.sex,tb_mremp.schoolAge from tb_mrdept cross join tb_mremp"; // 定义查询语句
        ResultSet rest = statement.executeQuery(sql); // 执行查询语句获取查询结果集
        while (rest.next()) { // 循环遍历查询结果集
            MrEmp mrEmp = new MrEmp();      //定义与查询结果集对应的JavaBean对象
            mrEmp.setId(rest.getInt(1));    //设置对象属性
            mrEmp.setdName(rest.getString(2));
            mrEmp.setName(rest.getString(4));
            mrEmp.setPerson(rest.getString(3));
            mrEmp.setSex(rest.getString(5));
            mrEmp.setSchoolAge(rest.getString(6));
            list.add(mrEmp);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return list; // 返回查询结果
}
    
    public static void main(String[] args) {
        GetDescartes descartes = new GetDescartes();
        List list = descartes.getDescsrtes();
        System.out.println("笛卡尔乘积查询：");
        for (int i = 0; i < list.size(); i++) {
            MrEmp mrEmp = (MrEmp) list.get(i);
            System.out.println("编号："+mrEmp.getId()+" 部门名称："+mrEmp.getdName()+"　负责人："+mrEmp.getPerson()+" 员工姓名："+mrEmp.getName()+
                    " 员工性别："+mrEmp.getSex()+" 学历："+mrEmp.getSchoolAge());
        }
    }
    
}
