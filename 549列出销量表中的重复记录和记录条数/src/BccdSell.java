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
        String sql = "select count(id) as countId ,bccdName,sum(bccdCount) as sum from tb_bccdSell group by bccdName having count(id)>1"; // 查询有多次销售记录的销售信息
        ResultSet set = staement.executeQuery(sql); // 执行查询语句返回查询结果集
        while (set.next()) { // 循环遍历查询结果集
          Bccd bccd = new Bccd();
          bccd.setCountId(set.getInt(1));
          bccd.setBccdName(set.getString(2));
          bccd.setSum(set.getInt(3));
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
        System.out.println("有多次销售记录的销售信息：");
        for(int i = 0;i<list.size();i++){
            Bccd bccd = (Bccd)list.get(i);
            System.out.println("重复记录为："+bccd.getCountId()+" ，词典："+bccd.getBccdName()+" ，共卖出："+bccd.getSum());
        }
    }
}
