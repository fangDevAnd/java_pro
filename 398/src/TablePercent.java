import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.UIManager;

public class TablePercent extends JFrame {
    
    private JPanel contentPane;
    private JTable table;
    
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        try {
            UIManager
                    .setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Throwable e) {
            e.printStackTrace();
        }
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    TablePercent frame = new TablePercent();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
    /**
     * Create the frame.
     */
    public TablePercent() {
        setTitle("在表格中显示工作进度百分比");// 设置窗体标题
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 470, 300);// 设置窗体位置与大小
        contentPane = new JPanel();// 创建内容面板
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);
        JScrollPane scrollPane = new JScrollPane();// 创建滚动面板
        contentPane.add(scrollPane, BorderLayout.CENTER);// 添加滚动面板到窗体
        table = new JTable();// 创建表格控件
        table.setModel(new DefaultTableModel(
                new Object[][] {// 设置表格数据模型
                { "油田管理系统登录模块", "李某", "应用程序", new Integer(93) },
                        { "油田管理系统部门模块", "张某", "应用程序", new Integer(63) },
                        { "油田管理系统业务模块", "刘某", "应用程序", new Integer(73) },
                        { "油田管理系统统计模块", "王某", "应用程序", new Integer(43) },
                        { "油田管理系统登录模块", "李某", "应用程序", new Integer(93) },
                        { "油田管理系统部门模块", "张某", "应用程序", new Integer(63) },
                        { "油田管理系统业务模块", "刘某", "应用程序", new Integer(73) },
                        { "油田管理系统统计模块", "王某", "应用程序", new Integer(43) },
                        { "油田管理系统登录模块", "李某", "应用程序", new Integer(93) },
                        { "油田管理系统部门模块", "张某", "应用程序", new Integer(63) },
                        { "油田管理系统业务模块", "刘某", "应用程序", new Integer(73) },
                        { "油田管理系统统计模块", "王某", "应用程序", new Integer(43) },
                        { "油田管理系统报表模块", "误某", "应用程序", new Integer(53) } },
                new String[] { "项目名称", "项目负责人", "项目类型", "开发进度" }));
        table.getColumnModel().getColumn(0).setPreferredWidth(146);// 设置列宽
        TableColumn column = table.getColumnModel().getColumn(3);// 获取表格第4列对象
        column.setCellRenderer(new TableCellRenderer() {// 设置第4列的渲染器
                    @Override
                    public Component getTableCellRendererComponent(
                            JTable table, Object value, boolean isSelected,
                            boolean hasFocus, int row, int column) {
                        if (value instanceof Integer) {// 创建整数渲染控件
                            JProgressBar bar = new JProgressBar();// 创建进度条
                            Integer percent = (Integer) value;// 把当前值转换为整数
                            bar.setValue(percent);// 设置进度条的值
                            bar.setStringPainted(true);// 显示进度条文本
                            return bar;// 把进度条作为渲染控件
                        } else {
                            return null;
                        }
                    }
                });
        scrollPane.setViewportView(table);// 把表格添加到滚动面板
    }
    
}
