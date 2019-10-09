import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Point;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class QQFrame extends JFrame {
    
    private JPanel contentPane;
    private boolean collection;
    private boolean over = false;
    
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    QQFrame frame = new QQFrame();
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
    public QQFrame() {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                do_this_mouseEntered(e);
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                do_this_mouseExited(e);
            }
        });
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentMoved(ComponentEvent e) {
                do_this_componentMoved(e);
            }
        });
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 412, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);
        
        JLabel label = new JLabel("<html><body align=center>我是一个仿QQ隐藏窗体"
                + "<br><font color=green size=6>把我放到窗体顶端，"
                + "我会自动隐藏</font></body></html>");
        contentPane.add(label, BorderLayout.CENTER);
    }
    
    protected void do_this_componentMoved(ComponentEvent e) {// 窗体移动事件处理方法
        if (over)// 如果鼠标在窗体中，就不做窗体隐藏操作
            return;
        Point point = getLocation();// 获取窗体位置
        if (point.y < 10) {// 如果窗体靠近屏幕顶端
            collection = true;// 确定隐藏窗体标识
            Dimension size = getSize();// 获取窗体大小
            setLocation(point.x, -size.height + 5);// 隐藏窗体
        } else {
            collection = false;// 如果窗体没有靠近屏幕顶端则取消隐藏标识
        }
    }
    
    protected void do_this_mouseEntered(MouseEvent e) {// 鼠标进入窗体的事件处理方法
        Point point = getLocation();// 获取窗体位置
        if (point.y > 0)// 如果窗体没有被隐藏不做任何操作
            return;
        setLocation(point.x, 8);// 设置窗体显示
        over = true;// 标识鼠标在窗体内部
        try {
            Thread.sleep(1000);// 给窗体1秒钟时间让鼠标就位
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
    }
    
    protected void do_this_mouseExited(MouseEvent e) {// 鼠标离开事件处理方法
        if (over) {// 如果鼠标标识在窗体内部
            over = false;// 取消鼠标位置的标识
            do_this_componentMoved(null);// 隐藏窗体
        }
    }
}
