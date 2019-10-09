import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import java.awt.FlowLayout;
import javax.swing.JRadioButton;
import javax.swing.BoxLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ShowTitleBorder extends JFrame {
    
    private JPanel contentPane;
    private ButtonGroup bg;
    private Font font = null;
    private TitledBorder titledBorder;
    private JPanel panel;
    
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
                    ShowTitleBorder frame = new ShowTitleBorder();
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
    public ShowTitleBorder() {
        setTitle("指定字体的标题边框");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 322, 221);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);
        panel = new JPanel();// 创建面板
        font = new Font("黑体", Font.BOLD, 18);// 初始化字体对象
        titledBorder = new TitledBorder(null, "自定义字体标题", TitledBorder.LEADING,
                TitledBorder.TOP, font, new Color(255, 0, 0));// 创建标题边框对象
        panel.setBorder(titledBorder);// 设置面板的边框
        contentPane.add(panel);
        panel.setLayout(null);
        
        JRadioButton radioButton = new JRadioButton("黑体");// 创建黑体单选按钮
        radioButton.setActionCommand(radioButton.getText());
        radioButton.setSelected(true);// 默认为选择状态
        radioButton.setBounds(14, 31, 104, 32);
        panel.add(radioButton);
        
        JRadioButton radioButton_1 = new JRadioButton("宋体");// 创建宋体单选按钮
        radioButton_1.setActionCommand(radioButton_1.getText());
        radioButton_1.setBounds(14, 63, 104, 32);
        panel.add(radioButton_1);
        
        JRadioButton radioButton_2 = new JRadioButton("隶书");// 创建隶书单选按钮
        radioButton_2.setActionCommand(radioButton_2.getText());
        radioButton_2.setBounds(14, 95, 104, 32);
        panel.add(radioButton_2);
        
        JRadioButton radioButton_3 = new JRadioButton("仿宋");// 创建仿宋单选按钮
        radioButton_3.setActionCommand(radioButton_3.getText());
        radioButton_3.setBounds(14, 127, 104, 32);
        panel.add(radioButton_3);
        
        bg = new ButtonGroup();// 创建按钮组
        bg.add(radioButton);// 把4个单选按钮添加到按钮组中
        bg.add(radioButton_1);
        bg.add(radioButton_2);
        bg.add(radioButton_3);
        
        JButton button = new JButton("设置");// 创建设置按钮
        button.addActionListener(new ActionListener() {// 为设置按钮添加事件监听器
                    public void actionPerformed(ActionEvent e) {
                        do_button_actionPerformed(e);
                    }
                });
        button.setBounds(161, 128, 90, 30);
        panel.add(button);
    }
    
    protected void do_button_actionPerformed(ActionEvent e) {
        // 获取被选择的单选按钮的文本
        String command = bg.getSelection().getActionCommand();
        font = new Font(command, Font.BOLD, 18);// 创建新字体对象
        titledBorder.setTitleFont(font);// 为边框对象设置字体
        panel.setBorder(titledBorder);// 更新面板的边框对象
        panel.repaint();// 跟新面板界面
    }
}
