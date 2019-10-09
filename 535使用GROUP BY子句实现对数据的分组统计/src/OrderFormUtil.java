import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class OrderFormUtil {
    private Connection conn;
    
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
    
public List getOrderDesc() {
    List list = new ArrayList(); // 定义用于保存返回值的List集合
    conn = getConn(); // 获取数据库连接
    try {
        Statement staement = conn.createStatement();
        String sql = "select visePerson,sum(clientMoney) as money from tb_orderForm  group by visePerson order by money desc"; // 定义将订单表进行分组统计的SQL语句
        ResultSet set = staement.executeQuery(sql); // 执行查询语句返回查询结果集
        while (set.next()) { // 循环遍历查询结果集
            OrderForm orderForm = new OrderForm();             
            orderForm.setClientMoney(set.getFloat("money"));
            orderForm.setVisePerson(set.getString("visePerson"));
            list.add(orderForm);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return list;
}
    public static void main(String[] args) {
        OrderFormUtil util = new OrderFormUtil();
        List list = util.getOrderDesc();
        System.out.println("员工签约排行榜：");
        for(int i = 0;i<list.size();i++){
            OrderForm orderForm = (OrderForm)list.get(i);
            System.out.println("签约人： "+orderForm.getVisePerson()+"，  订单金额： "+orderForm.getClientMoney());
        }
    }
}
