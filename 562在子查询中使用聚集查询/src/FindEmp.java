import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
public class FindEmp {
    private Connection conn ;   //定义Connection对象
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


public List getSubTable() {
    List list = new ArrayList<Emp>(); // 定义List集合对象
    conn = getConn(); // 获取与数据库的连接
    try {
        Statement statement = conn.createStatement(); // 获取Statement对象
        String sql = "select eName,headship,dept,laborage from tb_emp  where laborage >(select avg(laborage) from tb_emp)"; // 定义查询语句
        ResultSet rest = statement.executeQuery(sql); // 执行查询语句获取查询结果集
        while (rest.next()) { // 循环遍历查询结果集
            Emp emp = new Emp(); // 定义于数据表对应的JavaBean对象               
            emp.setName(rest.getString(1));
            emp.setHeadship(rest.getString(2));
            emp.setDept(rest.getString(3));
            emp.setLaborage(rest.getFloat(4));
            list.add(emp); // 将对象添加到集合中
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return list; // 返回查询结果
}

    /**
     * @param args
     */
    public static void main(String[] args) {
       FindEmp findEmp = new FindEmp();
       List list = findEmp.getSubTable();
       System.out.println("查询工资高于员工平均公司的员工信息：");
       for(int i = 0;i<list.size();i++){
          Emp emp = (Emp)list.get(i);
          System.out.println("姓名："+emp.getName()+" 职位："+emp.getHeadship()+" 部门："+emp.getDept()+" 工资："+emp.getLaborage());
       }
        
    }
    
}
