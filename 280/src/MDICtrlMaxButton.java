import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class MDICtrlMaxButton extends JFrame {
    
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
                    MDICtrlMaxButton frame = new MDICtrlMaxButton();
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
    public MDICtrlMaxButton() {
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
        
        ctrlButton = new JToggleButton(
                "\u7981\u6B62\u7A97\u4F53\u7684\u6700\u5927\u5316");
        ctrlButton.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                do_ctrlButton_itemStateChanged(e);
            }
        });
        toolBar.add(ctrlButton);
    }
    
    private int fCount = 0;
    private Random random = new Random();
    private JToggleButton ctrlButton;
    
    protected void do_button_actionPerformed(ActionEvent e) {
        final JInternalFrame jif = new JInternalFrame("子窗体" + fCount++, true,
                true, true, true);// 创建内部窗体
        jif.setSize(random.nextInt(100) + 100, random.nextInt(100) + 100);
        jif.setLocation(random.nextInt(getWidth() - 100), random
                .nextInt(getHeight() - 100));// 随机定位内部窗体
        desktopPane.add(jif);// 添加内部窗体到桌面面板
        jif.setVisible(true);// 显示内部窗体
        // 为内部窗体添加事件监听器
        jif.addInternalFrameListener(new InternalFrameAdapter() {
            @Override
            public void internalFrameActivated(InternalFrameEvent e) {
                ctrlButton.setSelected(!jif.isMaximizable());// 改变<禁止窗体的最大化>按钮状态
            }
        });
    }
    
    protected void do_ctrlButton_itemStateChanged(ItemEvent e) {
        JInternalFrame jif = desktopPane.getSelectedFrame();// 获取选择的内部窗体
        if (jif != null) {
            jif.setMaximizable(!ctrlButton.isSelected());// 激活或禁用内部窗体最大化按钮
        }
    }
}
