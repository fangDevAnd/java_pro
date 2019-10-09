package com.cdd.laborage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class FindLaborage {
    
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
    
    public List getPatindex() {
        conn = getConn(); // 获取与数据库的连接
        ResultSet rest;
        List list = new ArrayList<FindLaborage>();
        try {
            Statement statement = conn.createStatement(); // 获取Statement对象
            String sql = "select id,name,Base,round(Subsidy,0) as subsidy, round(deduct,0) as deduct from tb_particularLaborage"; // 定义查询语句
            rest = statement.executeQuery(sql); // 执行查询语句获取查询结果集
            while (rest.next()) { // 循环遍历查询结果集
                ParticularLaborage laborage = new ParticularLaborage(); // 定义域数据表对应的JavaBean对象
                laborage.setId(rest.getInt(1)); // 设置对象属性
                laborage.setName(rest.getString(2));
                laborage.setBase(rest.getFloat(3));
                laborage.setSubsidy(rest.getFloat(4));
                laborage.setDeduct(rest.getFloat(5));
                list.add(laborage); // 向集合中添加对象
            }
            return list; // 返回查询结果
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public static void main(String[] args) {
        FindLaborage findLaborage = new FindLaborage();
        List list = findLaborage.getPatindex();
        System.out.println("将数据进行四舍五入：");
        for (int i = 0; i < list.size(); i++) {
            ParticularLaborage particularLaborage = (ParticularLaborage) list
                    .get(i);
            int id = particularLaborage.getId();
            String name = particularLaborage.getName();
            float base = particularLaborage.getBase();
            float subsidy = particularLaborage.getSubsidy();
            float deduct = particularLaborage.getDeduct();
            System.out.println("编号：" + id + " 姓名：" + name + "  基本工资：" + base
                    + " 津贴：" + subsidy + " 扣除：" + deduct);
        }
    }
}
