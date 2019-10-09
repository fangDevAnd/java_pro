package com.cdd.util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GetFrame {
    public Connection Con() {
        try {
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            Connection Con = DriverManager
                    .getConnection(
                            "jdbc:jtds:sqlserver://localhost:1433;DatabaseName=db_database22",
                            "sa", "");
            return Con;
        } catch (Exception e) {
            return null;
        }
    }
    
    // 定义查看数据库中所有数据表方法
    public List GetRs() {
        try {
            List list = new ArrayList();
            String[] tableType = { "TABLE" }; // 指定要进行查询的表类型
            Connection conn = Con(); // 调用与数据库建立连接方法
            DatabaseMetaData databaseMetaData = conn.getMetaData(); // 获取DatabaseMetaData实例
            ResultSet resultSet = databaseMetaData.getTables(null, null, "%",
                    tableType);// 获取数据库中所有数据表集合
            while (resultSet.next()) { // 遍历集合
                String tableName = resultSet.getString("TABLE_NAME");
                list.add(tableName);
            }
            return list;
        } catch (SQLException e) {
            System.out.println("记录数量获取失败！");
            return null;
        }
    }
    
    public ResultSet GetRs(final String SQL) {
        try {
            Connection Con = Con(); // 获取数据库连接
            Statement Smt = Con
                    .createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                            ResultSet.CONCUR_UPDATABLE); // 获取Statement对象
            ResultSet Rs = Smt.executeQuery(SQL); // 执行查询语句，获取查询结果集
            return Rs;
        } catch (SQLException e) {
            System.out.println("记录数量获取失败！");
            return null;
        }
    }
    
public List getMessage(String tableName) {
    List list = new ArrayList(); // 定义保存返回值的List集合
    String SQL = " Select case when c.colid=1 then  o.name end 表名,"
            + " c.ColId 字段编号,c.name 字段名,c.length 字段长度,t.name 字段类别,"
            + " p.value 描述,case when c.isnullable=0 then '1' end 是否为空,"
            + " c.scale 小数位数,REPLACE (REPLACE (REPLACE (m.text,'(',''),')',''),'''','') 默认值,"
            + " case when ("
            + " Select Count(*) From SysObjects where name in ("
            + " Select name From Sysindexes Where id=c.id and indid in ("
            + " Select indid From Sysindexkeys  where id=c.id and colid in ("
            + " Select colid From Syscolumns where id=c.id and colid=c.colid))) and xtype='pk')>0"
            + " then '1' end 是否为主键"
            + " From Sysobjects o"
            + " left join Syscolumns c on o.id=c.id"
            + " left join Sysproperties p on o.id=p.id and c.colid=p.smallid"
            + " left join Systypes t on t.xtype=c.xtype"
            + " left join Syscomments m on m.id=c.cdefault"
            + " where (o.xtype='u' or o.xtype='v') and o.status>0 and o.name='"
            + tableName + "'" + " order by o.name,c.colid"; // 定义查询SQL语句
    ResultSet res = GetRs(SQL);                 //调用执行SQL语句方法
    ResultSetMetaData Rsmd;                     //获取ResultSetMetaData方法
    try {
        Rsmd = res.getMetaData();               //实例化 ResultSetMetaData对象
        while (res.next()) {                    //循环遍历查询结果集
            Student student = new Student();    //创建对于数据库对象的JavaBean对象
            student.setId(res.getString("字段编号"));       //设置对象属性
            student.setName(res.getString("字段名"));
            student.setType(res.getString("字段类别"));
            student.setAcquiescence(res.getString("默认值"));
            student.setDepict(res.getString("描述"));
            student.setDigit(res.getString("小数位数"));
            student.setLength(res.getString("字段长度"));
            student.setIfNull(res.getString("是否为空"));
            list.add(student);                  //将对象添加到List集合中
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return list;            //返回List集合
}
    
}
