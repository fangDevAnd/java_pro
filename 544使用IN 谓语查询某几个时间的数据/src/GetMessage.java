import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class GetMessage {
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
        String sql = "select * from tb_bccdSell where bccdDate in ('2010/6/20','2010/6/21')"; // 定义查询数据的SQL语句
        ResultSet set = staement.executeQuery(sql); // 执行查询语句返回查询结果集
        while (set.next()) { // 循环遍历查询结果集
            Bccd bccd = new Bccd(); // 定义与数据对应的JavaBean对象
            bccd.setId(set.getInt(1));      //设置对象属性
            bccd.setBccdName(set.getString(2));
            bccd.setBccdCount(set.getInt(3));
            bccd.setBccdPrice(set.getFloat(4));
            bccd.setBccdDate(set.getString(5));
            list.add(bccd); // 向集合中添加对象
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return list; // 返回List集合
}
    
    public static void main(String[] args) {
        GetMessage message = new GetMessage();
        List list = message.getBccdSell();
        System.out.println("在2010/6/20和2010/6/21都销售记录的编程词典有");
        for (int i = 0; i < list.size(); i++) {
            Bccd bccd = (Bccd) list.get(i);            
            String bccdName = bccd.getBccdName();
            int count = bccd.getBccdCount();
            float price = bccd.getBccdPrice();
            String date = bccd.getBccdDate();
            System.out.println(" 名称：" + bccdName + " 数量：" + count
                    + " 价格：" + price + " 日期：" + date);
        }
    }
}
