import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.border.TitledBorder;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;

public class StringLinewrap extends JFrame {
    
    private JPanel contentPane;
    private JTextArea sourceTextArea;
    private JTextArea destinationTextArea;
    
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
                    StringLinewrap frame = new StringLinewrap();
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
    public StringLinewrap() {
        setTitle("\u6839\u636E\u6807\u70B9\u5BF9\u5B57\u7B26\u4E32\u5206\u884C");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 475, 441);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JLabel label = new JLabel(
                "<html>\u5728\u4E0B\u9762\u7684\u6587\u672C\u6846\u8F93\u5165\u5B57\u7B26\u4E32\u6BB5\u843D\uFF0C\u5176\u4E2D\u8981\u5305\u62EC\uFF08\uFF0C\uFF09\u6807\u70B9\u7B26\u53F7\u3002\u7136\u540E\u5355\u51FB\u201C\u5206\u884C\u663E\u793A\u201D\u6309\u94AE\uFF0C\u7A0B\u5E8F\u5C06\u6839\u636E\uFF08\uFF0C\uFF09\u7B26\u53F7\u5206\u884C\u3002</html>");
        label.setBorder(new TitledBorder(null, "\u8BF4\u660E",
                TitledBorder.LEADING, TitledBorder.TOP, null, null));
        label.setBounds(10, 10, 439, 76);
        contentPane.add(label);
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 84, 439, 98);
        contentPane.add(scrollPane);
        
        sourceTextArea = new JTextArea();
        sourceTextArea.setLineWrap(true);
        scrollPane.setViewportView(sourceTextArea);
        
        JButton button = new JButton("\u5206\u884C\u663E\u793A");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                do_button_actionPerformed(e);
            }
        });
        button.setBounds(177, 192, 101, 25);
        contentPane.add(button);
        
        JScrollPane scrollPane_1 = new JScrollPane();
        scrollPane_1.setBounds(10, 233, 439, 156);
        contentPane.add(scrollPane_1);
        
        destinationTextArea = new JTextArea();
        scrollPane_1.setViewportView(destinationTextArea);
        
    }
    
    protected void do_button_actionPerformed(ActionEvent e) {
        String sourceString = sourceTextArea.getText();// 获取用户输入字段
        String[] lines = sourceString.split(",|，");// 根据中英文逗号分割字符串为数组
        StringBuilder sbuilder = new StringBuilder();// 创建字符串构建器
        for (String line : lines) {// 遍历分割后的字符串数组
            // 把每个数组元素的字符串与回车符相连并添加到字符串构建器中
            sbuilder.append(line + "\n");
        }
        // 把字符串添加到换行显示字符串的文本域中
        destinationTextArea.setText(sbuilder.toString());
    }
}
