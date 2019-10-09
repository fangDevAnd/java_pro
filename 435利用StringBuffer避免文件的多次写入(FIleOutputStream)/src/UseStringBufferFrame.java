import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.*;

public class UseStringBufferFrame extends JFrame {
    
    private JPanel contentPane;
    JCheckBox checkBox;
    JCheckBox checkBox_1;
    JCheckBox checkBox_2;
    JCheckBox checkBox_3;
    JCheckBox checkBox_4;
    JCheckBox checkBox_5;
    StringBuffer buffer = new StringBuffer();
    
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    UseStringBufferFrame frame = new UseStringBufferFrame();
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
    public UseStringBufferFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 344, 224);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        setTitle("个人爱好");
        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 335, 186);
        contentPane.add(panel);
        panel.setLayout(null);
        
        JLabel label = new JLabel("你的个人爱好是：");
        label.setBounds(41, 21, 105, 24);
        panel.add(label);
        
        checkBox = new JCheckBox("游泳");
        checkBox.setBounds(41, 51, 103, 23);
        panel.add(checkBox);
        
        checkBox_1 = new JCheckBox("旅游");
        checkBox_1.setBounds(187, 51, 103, 23);
        panel.add(checkBox_1);
        
        checkBox_2 = new JCheckBox("看书");
        checkBox_2.setBounds(41, 76, 103, 23);
        panel.add(checkBox_2);
        
        checkBox_3 = new JCheckBox("上网");
        checkBox_3.setBounds(187, 76, 103, 23);
        panel.add(checkBox_3);
        
        checkBox_4 = new JCheckBox("乒乓球");
        checkBox_4.setBounds(41, 101, 103, 23);
        panel.add(checkBox_4);
        
        checkBox_5 = new JCheckBox("羽毛球");
        checkBox_5.setBounds(187, 101, 103, 23);
        panel.add(checkBox_5);
        
        JButton saveButton = new JButton("写入文件");
        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                do_button_actionPerformed(arg0);
            }
        });
        saveButton.setBounds(176, 140, 93, 23);
        panel.add(saveButton);
    }
    
    protected void do_button_actionPerformed(ActionEvent arg0) {
        if (checkBox.isSelected()) { // 判断指定的复选框checkBox是否被选中
            buffer.append(checkBox.getText() + " "); // 将可变的字符序列进行追加信息
        }
        if (checkBox_1.isSelected()) {
            buffer.append(checkBox_1.getText() + " ");
        }
        if (checkBox_2.isSelected()) {
            buffer.append(checkBox_2.getText() + " ");
        }
        if (checkBox_3.isSelected()) {
            buffer.append(checkBox_3.getText() + " ");
        }
        if (checkBox_4.isSelected()) {
            buffer.append(checkBox_4.getText() + " ");
        }
        if (checkBox_5.isSelected()) {
            buffer.append(checkBox_5.getText() + " ");
        }
        File file = new File("C://w.txt"); // 根据指定文件创建File对象
        try {
            FileOutputStream out = new FileOutputStream(file); // 创建FileOutputStream实例
            String str = buffer.toString(); // 将可变的字符序列转换为字符串对象
            out.write(str.getBytes()); // 向输出流中写数据
            JOptionPane.showMessageDialog(getContentPane(), "信息写入完成！", "信息提示框",
                    JOptionPane.WARNING_MESSAGE); // 给用户提供提示信息对话框
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
