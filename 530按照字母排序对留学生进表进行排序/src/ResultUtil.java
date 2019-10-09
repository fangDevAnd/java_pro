import java.sql.*;
import java.util.*;
import java.util.concurrent.ThreadPoolExecutor.AbortPolicy;

public class ResultUtil {
    // 获取数据库连接
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
    public List getGradeDesc() {
        List list = new ArrayList();    // 定义用于保存返回值的List集合
        conn = getConn();               // 获取数据库连接
        try {
            Statement staement = conn.createStatement();
               // 定义将图书表中信息进行排序的SQL语句
            String sql = "select * from tb_abroad order by substring(name,1,1)"; 
            ResultSet set = staement.executeQuery(sql);     // 执行查询语句返回查询结果集
            while (set.next()) {                        // 循环遍历查询结果集
                Abroad abrod = new Abroad();                // 创建于学生成绩表对应的Grade对象
                abrod.setId(set.getInt(1));                 // 设置对象属性
                abrod.setName(set.getString(2));
                abrod.setSurname(set.getString(3));
                abrod.setNationality(set.getString(4));
                list.add(abrod);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;                                    // 返回查询集合
    }    
    public static void main(String[] args) {
        ResultUtil util = new ResultUtil();
        List list = util.getGradeDesc();
        System.out.println("将留学生表按音序排序：");
        for(int i = 0;i<list.size();i++){
            Abroad abrod = (Abroad)list.get(i);         // 创建于学生成绩表对应的Grade对象
            System.out.println("编号为："+abrod.getId()+"  名字为："+abrod.getName()+"  姓为："+abrod.getSurname()+
                    "  国籍为："+abrod.getNationality());
        }
        
    }
}
