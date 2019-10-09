import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Point;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class InfoDemoFrame extends JFrame {
    
    private JPanel contentPane;
    private InfoWindow window = new InfoWindow();
    private Timer timer;
    private Point location;
    private Dimension screenSize;
    private Dimension windowSize;;
    
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    InfoDemoFrame frame = new InfoDemoFrame();
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
    public InfoDemoFrame() {
        setTitle("右下角弹出信息窗体");// 设置窗体标题
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 337, 190);// 窗体大小
        contentPane = new JPanel();// 创建内容面板
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);// 取消布局管理器
        JButton button = new JButton("获取即时信息");// 创建按钮
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                do_button_actionPerformed(e);// 调用按钮事件处理方法
            }
        });
        button.setBounds(97, 59, 122, 30);
        contentPane.add(button);
    }
    
    protected void do_button_actionPerformed(ActionEvent e) {
        // 创建Timer控件
        timer = new Timer(1, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                location.y -= 1;// 提升信息窗体垂直坐标
                // 在信息窗体显示而且没有达到上升位置之前持续移动窗体
                if (window.isShowing()
                        && location.y > screenSize.height - windowSize.height)
                    window.setLocation(location);
                else {// 窗体未显示或超出移动范围是停止
                    Timer source = (Timer) e.getSource();
                    source.stop();
                }
            }
        });
        screenSize = getToolkit().getScreenSize();// 获取屏幕大小
        window.setVisible(true);// 显示信息窗体
        window.setAlwaysOnTop(true);// 把信息窗体置顶
        windowSize = window.getSize();// 获取信息窗体大小
        location = new Point();// 创建位置对象
        location.x = screenSize.width - windowSize.width;// 初始化窗体位置
        location.y = screenSize.height;
        timer.start();// 启动Timer控件
    }
}
