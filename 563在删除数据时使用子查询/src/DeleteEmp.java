import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
public class DeleteEmp {
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
    //查询部门表中的所有数据
    public List getDept() {
        List list = new ArrayList<Dept>(); // 定义List集合对象
        conn = getConn(); // 获取与数据库的连接
        try {
            Statement statement = conn.createStatement(); // 获取Statement对象
            String sql = "select * from tb_mrdept "; // 定义查询语句
            ResultSet rest = statement.executeQuery(sql); // 执行查询语句获取查询结果集
            while (rest.next()) { // 循环遍历查询结果集
                Dept dept = new Dept();
                dept.setId(rest.getInt(1));
                dept.setDeptName(rest.getString(2));
                list.add(dept);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list; // 返回查询结果
    }

public void deleteEmp() {        
    conn = getConn(); // 获取与数据库的连接
    try {
        Statement statement = conn.createStatement(); // 获取Statement对象
        String sql = "delete from tb_mrdept where person not in (select name from tb_mremp )"; //在删除语句中使用子查询
        statement.executeUpdate(sql);            //执行删除SQL语句
    } catch (Exception e) {
        e.printStackTrace();
    }        
}
    public static void main(String[] args) {
        DeleteEmp deleteEmp = new DeleteEmp();
        List list = deleteEmp.getDept();
        System.out.println("删除前的数据：");
        for(int i  = 0;i<list.size();i++){
            Dept dept = (Dept)list.get(i);
            System.out.println("编号为："+dept.getId()+"  部门名称为："+dept.getDeptName());
        }
        System.out.println("\n 删除员工表中不存在的部门信息后，部门信息表中的数据为：");
        deleteEmp.deleteEmp();
        List list2 = deleteEmp.getDept();
        for(int i  = 0;i<list2.size();i++){
            Dept dept = (Dept)list2.get(i);
            System.out.println("编号为："+dept.getId()+"  部门名称为："+dept.getDeptName());
        }
    }
    
}
