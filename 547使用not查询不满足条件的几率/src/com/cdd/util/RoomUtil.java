package com.cdd.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class RoomUtil {
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
    
public List getRoom() {
    List list = new ArrayList(); // 定义用于保存返回值的List集合
    conn = getConn(); // 获取数据库连接
    try {
        Statement staement = conn.createStatement();
        String sql = "select * from tb_room  where (not roomState = '入住') and ( not roomType = '普间')"; // 定义查询数据的SQL语句
        ResultSet set = staement.executeQuery(sql); // 执行查询语句返回查询结果集
        while (set.next()) { // 循环遍历查询结果集
            Room room = new Room();
            room.setRoomDate(set.getString("roomDate"));
            room.setRoomFacility(set.getString("roomFacility"));
            room.setRoomid(set.getInt(1));
            room.setRoomPrice(set.getInt("roomPrice"));
            room.setRoomState(set.getString("roomState"));
            room.setRoomType(set.getString("roomType"));
            list.add(room);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return list; // 返回List集合
}
    
    public static void main(String[] args) {
        RoomUtil util = new RoomUtil();
        List list = util.getRoom();
        System.out.println("查询没有入住的普间客房：");
        for(int i = 0;i<list.size();i++){
            Room room = (Room)list.get(i);
            System.out.println("房间号 ："+room.getRoomid()+" , 类型："+room.getRoomType()+" , 价格："+room.getRoomPrice()+
                    " , 设备："+room.getRoomFacility()+" , 状态："+room.getRoomState());
        }
    }
}
