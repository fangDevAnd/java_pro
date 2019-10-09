import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Point;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.Color;

public class DropCustomFrame extends JFrame {
    
    private JPanel contentPane;
    private Point pressedPoint;
    
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    DropCustomFrame frame = new DropCustomFrame();
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
    public DropCustomFrame() {
        setUndecorated(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 721, 361);
        contentPane = new JPanel();
        contentPane.setBackground(Color.ORANGE);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);
        
        BackgroundPanel backgroundPanel = new BackgroundPanel();
        backgroundPanel.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                do_backgroundPanel_mouseDragged(e);
            }
        });
        backgroundPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                do_backgroundPanel_mousePressed(e);
            }
        });
        backgroundPanel.setImage(getToolkit().getImage(
                getClass().getResource("background.jpg")));
        contentPane.add(backgroundPanel, BorderLayout.CENTER);
        
        JButton button = new JButton("\u5173\u95ED");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                do_button_actionPerformed(e);
            }
        });
        button.setBounds(587, 304, 90, 30);
        backgroundPanel.add(button);
    }
    
    protected void do_button_actionPerformed(ActionEvent e) {
        dispose();// 销毁窗体
    }
    
    protected void do_backgroundPanel_mousePressed(MouseEvent e) {
        pressedPoint = e.getPoint();// 记录鼠标坐标
    }
    
    protected void do_backgroundPanel_mouseDragged(MouseEvent e) {
        Point point = e.getPoint();// 获取当前坐标
        Point locationPoint = getLocation();// 获取窗体坐标
        int x = locationPoint.x + point.x - pressedPoint.x;// 计算移动后的新坐标
        int y = locationPoint.y + point.y - pressedPoint.y;
        setLocation(x, y);// 改变窗体位置
    }
}
