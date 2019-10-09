import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.UIManager;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Random;

public class ExampleFrame extends JFrame {
    
    private JPanel contentPane;
    private JTable table;
    private DefaultTableModel model;
    
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
                    ExampleFrame frame = new ExampleFrame();
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
    public ExampleFrame() {
        // 为窗体添加打开事件监听器
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                do_this_windowOpened(e);// 调用窗体打开事件处理方法
            }
        });
        setTitle("动态加载表格数据");// 设置窗体标题
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);// 设置窗体大小
        contentPane = new JPanel();// 创建内容面板
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);
        JScrollPane scrollPane = new JScrollPane();// 创建滚动面板
        contentPane.add(scrollPane, BorderLayout.CENTER);
        table = new JTable();// 创建表格控件
        model = new DefaultTableModel(new Object[][] {}, new String[]
            { "学号", "卫生分数", "生活分数" });// 创建默认的表格数据模型
        table.setModel(model);// 设置表格数据模型
        scrollPane.setViewportView(table);// 把表格添加到滚动面板视图
    }
    
    protected void do_this_windowOpened(WindowEvent e) {
        // 创建Timer控件
        Timer timer = new Timer(500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Random random = new Random();// 创建随机数对象
                Integer[] values = new Integer[]
                    // 创建整数数组作为表格行数据
                    { random.nextInt(100), random.nextInt(100),
                            random.nextInt(100) };
                model.addRow(values);// 为表格数据模型添加一行数据
            }
        });
        timer.start();// 启动Timer控件
    }
}
