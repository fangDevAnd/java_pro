import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.UIManager;

public class TableImage extends JFrame {
    
    private JPanel contentPane;
    private JTable table;
    
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    TableImage frame = new TableImage();
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
    public TableImage() {
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
        ImageIcon[] icons = new ImageIcon[12];
        for (int i = 0; i < icons.length; i++) {
            icons[i] = new ImageIcon(getClass().getResource(
                    "/res/" + (i + 1) + ".png"));
        }
        table.setModel(new DefaultTableModel(
                new Object[][]
                {// 设置表格数据模型
                { icons[0], "油田管理系统部门模块", "李某", "应用程序" },
                        { icons[0], "油田管理系统部门模块", "张某", "应用程序" },
                        { icons[1], "油田管理系统业务模块", "刘某", "应用程序" },
                        { icons[2], "油田管理系统统计模块", "王某", "应用程序" },
                        { icons[3], "油田管理系统登录模块", "李某", "应用程序" },
                        { icons[4], "油田管理系统部门模块", "张某", "应用程序" },
                        { icons[5], "油田管理系统业务模块", "刘某", "应用程序" },
                        { icons[6], "油田管理系统统计模块", "王某", "应用程序" },
                        { icons[7], "油田管理系统登录模块", "李某", "应用程序" },
                        { icons[8], "油田管理系统部门模块", "张某", "应用程序" },
                        { icons[9], "油田管理系统业务模块", "刘某", "应用程序" },
                        { icons[10], "油田管理系统统计模块", "王某", "应用程序" },
                        { icons[11], "油田管理系统报表模块", "误某", "应用程序" } },
                new String[]
                { "模块标识", "项目名称", "项目负责人", "项目类型" }));
        table.getColumnModel().getColumn(1).setPreferredWidth(146);// 设置列宽
        TableColumn column = table.getColumnModel().getColumn(0);// 获取表格第4列对象
        table.setRowHeight(32);
        column.setCellRenderer(new TableCellRenderer() {// 设置第4列的渲染器
                    @Override
                    public Component getTableCellRendererComponent(
                            JTable table, Object value, boolean isSelected,
                            boolean hasFocus, int row, int column) {
                        ImageIcon icon = (ImageIcon) value;
                        JLabel label = new JLabel(icon);// 创建进度条
                        label.setBackground(table.getSelectionBackground());
                        if (isSelected)// 把选择的标签设置为不透明
                            label.setOpaque(true);
                        return label;// 把进度条作为渲染控件
                    }
                });
        scrollPane.setViewportView(table);// 把表格添加到滚动面板
    }
}
