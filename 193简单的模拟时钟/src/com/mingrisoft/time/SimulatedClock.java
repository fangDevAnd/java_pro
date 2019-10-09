package com.mingrisoft.time;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.JFrame;
import javax.swing.UIManager;

public class SimulatedClock extends JFrame {
    
    /**
     * 可以修改成使用Rectangle2D
     */
    private static final long serialVersionUID = 1557083478271086551L;
    
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Throwable e) {
            e.printStackTrace();
        }
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    SimulatedClock frame = new SimulatedClock();
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
    public SimulatedClock() {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowActivated(WindowEvent arg0) {
                do_this_windowActivated(arg0);
            }
        });
        setTitle("\u6A21\u62DF\u65F6\u949F");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 300, 300);
    }
    
    public void paint(Graphics g) {
        super.paint(g);// 调用父类的paint()方法，这样能在画图时能保存外观
        Rectangle rectangle = getBounds();// 获得组件的区域
        Insets insets = getInsets();// 获得组件的边框
        int radius = 120;// 设置圆的半径是120px
        int x = (rectangle.width - 2 * radius - insets.left - insets.right) / 2 + insets.left;
        int y = (rectangle.height - 2 * radius - insets.top - insets.bottom) / 2 + insets.top;
        Point2D.Double center = new Point2D.Double(x + radius, y + radius);// 获得圆心坐标
        g.drawOval(x, y, 2 * radius, 2 * radius);// 绘制圆形
        Point2D.Double[] scales = new Point2D.Double[60];// 用60个点保存表盘的刻度
        double angle = Math.PI / 30;// 表盘上两个点之间的夹角是PI/30
        for (int i = 0; i < scales.length; i++) {// 获得所有刻度的坐标
            scales[i] = new Point2D.Double();// 初始化点对象
            scales[i].setLocation(x + radius + radius * Math.sin(angle * i), y + radius - radius * Math.cos(angle * i));// 利用三角函数计算点的坐标
        }
        for (int i = 0; i < scales.length; i++) {// 画所有刻度
            if (i % 5 == 0) {// 如果序号是5则画成大点，这些点相当于石英钟上的数字
                g.setColor(Color.RED);
                g.fillOval((int) scales[i].x - 4, (int) scales[i].y - 4, 8, 8);
            } else {// 如果序号不是5则画成小点，这些点相当于石英钟上的小刻度
                g.setColor(Color.CYAN);
                g.fillOval((int) scales[i].x - 2, (int) scales[i].y - 2, 4, 4);
            }
        }
        Calendar calendar = new GregorianCalendar();// 创建日期对象
        int hour = calendar.get(Calendar.HOUR);// 获得当前小时数
        int minute = calendar.get(Calendar.MINUTE);// 获得当前分钟数
        int second = calendar.get(Calendar.SECOND);// 获得当前秒数
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.red);// 将颜色设置成红色
        g2d.draw(new Line2D.Double(center, scales[second]));// 绘制秒针
        BasicStroke bs = new BasicStroke(3f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER);
        g2d.setStroke(bs);
        g2d.setColor(Color.blue);// 将颜色设置成蓝色
        g2d.draw(new Line2D.Double(center, scales[minute]));// 绘制分针
        bs = new BasicStroke(6f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER);
        g2d.setStroke(bs);
        g2d.setColor(Color.green);// 将颜色设置成绿色
        g2d.draw(new Line2D.Double(center, scales[hour * 5 + minute / 12]));// 绘制时针
    }
    
    private class ClockRunnable implements Runnable {
        
        @Override
        public void run() {
            while (true) {
                repaint();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            
        }
        
    }
    
    protected void do_this_windowActivated(WindowEvent arg0) {
        new Thread(new ClockRunnable()).start();
    }
}
