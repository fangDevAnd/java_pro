package com.mingrisoft.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBHelper implements DBConfig {
    
    public static void saveImage(String name, String type, File image) {
        FileInputStream in = null;
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            in = new FileInputStream(image);// 利用image参数创建FileInputStream对象
            Class.forName(DRIVER);// 加载驱动程序
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);// 建立连接
            ps = conn.prepareStatement("insert into images values (?, ?, ?);");
            ps.setString(1, name);// 保存图片名称
            ps.setString(2, type);// 保存图片类型
            ps.setBinaryStream(3, in, (int) image.length());// 保存图片
            ps.executeUpdate();// 执行保存
        } catch (Exception e) {// 捕获异常
            e.printStackTrace();
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
