import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PIFrame extends JDialog {
    
    private JPanel contentPane;
    private static PIFrame[] frames = new PIFrame[12];
    private static Point[] points = new Point[frames.length];
    
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    for (int i = 0; i < frames.length; i++) {// 初始化窗体数组
                        frames[i] = new PIFrame();// 初始化窗体数组元素
                        frames[i].setVisible(true);// 显示窗体
                        points[i] = frames[i].getLocation();// 初始化窗体位置数组
                    }
                    Timer timer = new Timer(50, new ActionListener() {
                        int r = 100;// 移动半径
                        int angle = 0;// 角度遍历
                        double optionNum = Math.PI / 180;// 创建弧度角度转换单位
                        
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            for (int i = 0; i < frames.length; i++) {
                                int x = (int) (r * Math.cos((angle + i * 10)
                                        % 360 * optionNum));// 定义窗体X坐标
                                int y = (int) (r * Math
                                        .sin((angle + i * 10 % 360) * optionNum));// 定义窗体Y坐标
                                angle = (angle + 1) % 360;// 累加运动的角度
                                frames[i].setLocation(points[i].x + x,
                                        points[i].y + y);// 移动窗体
                            }
                        }
                    });
                    timer.start();// 启动Timer控件
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
    /**
     * Create the frame.
     */
    public PIFrame() {
        setAlwaysOnTop(true);
        setBounds(500, 400, 356, 268);
        contentPane = new JPanel();
        contentPane.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                do_contentPane_mousePressed(e);
            }
        });
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);
    }
    
    protected void do_contentPane_mousePressed(MouseEvent e) {
        System.exit(0);
    }
}
