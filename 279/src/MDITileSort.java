import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

public class MDITileSort extends JFrame {
    
    private JPanel contentPane;
    private JDesktopPane desktopPane;
    
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
                    MDITileSort frame = new MDITileSort();
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
    public MDITileSort() {
        setTitle("\u5B50\u7A97\u4F53\u5782\u76F4\u6392\u5217");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 545, 403);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);
        
        desktopPane = new JDesktopPane();
        contentPane.add(desktopPane, BorderLayout.CENTER);
        
        JToolBar toolBar = new JToolBar();
        contentPane.add(toolBar, BorderLayout.NORTH);
        
        JButton button = new JButton("\u52A0\u8F7D\u5B50\u7A97\u4F53");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                do_button_actionPerformed(e);
            }
        });
        toolBar.add(button);
        
        JButton button_1 = new JButton("\u7A97\u4F53\u5E73\u94FA");
        button_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                do_button_1_actionPerformed(e);
            }
        });
        toolBar.add(button_1);
    }
    
    private int fCount = 0;
    private Random random = new Random();
    
    protected void do_button_actionPerformed(ActionEvent e) {
        JInternalFrame jif = new JInternalFrame("子窗体" + fCount++, true, true,
                true, true);// 创建内部窗体
        jif.setSize(random.nextInt(100) + 100, random.nextInt(100) + 100);
        jif.setLocation(random.nextInt(getWidth() - 100), random
                .nextInt(getHeight() - 100));// 随机定位内部窗体
        desktopPane.add(jif);// 添加内部窗体到桌面面板
        jif.setVisible(true);// 显示内部窗体
    }
    
    protected void do_button_1_actionPerformed(ActionEvent e) {
        // 设置桌面面板使用网格布局管理器
        desktopPane.setLayout(new GridLayout((int) Math.sqrt(fCount), 0));
        desktopPane.doLayout();// 布局桌面面板中所有控件
        desktopPane.setLayout(null);// 取消桌面面板的布局管理器
    }
}
