import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

public class SplitPath extends JFrame {
    
    private JPanel contentPane;
    private JTextField textField;
    private JTextArea textArea;
    
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    SplitPath frame = new SplitPath();
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
    public SplitPath() {
        setTitle("\u4ECE\u5B57\u7B26\u4E32\u4E2D\u5206\u79BB\u6587\u4EF6\u8DEF\u5F84\u3001\u6587\u4EF6\u540D\u53CA\u6269\u5C55\u540D");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 408, 252);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(5, 5));
        setContentPane(contentPane);
        
        JPanel panel = new JPanel();
        contentPane.add(panel, BorderLayout.NORTH);
        panel.setLayout(new BorderLayout(10, 0));
        
        textField = new JTextField();
        panel.add(textField);
        textField.setColumns(10);
        
        JButton button = new JButton("\u9009\u62E9\u6587\u4EF6");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                do_button_actionPerformed(e);
            }
        });
        panel.add(button, BorderLayout.EAST);
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBorder(new TitledBorder(null, "\u6587\u4EF6\u4FE1\u606F",
                TitledBorder.LEADING, TitledBorder.TOP, null, null));
        scrollPane.setOpaque(false);
        contentPane.add(scrollPane, BorderLayout.CENTER);
        
        textArea = new JTextArea();
        textArea.setOpaque(false);
        textArea.setEditable(false);
        scrollPane.setViewportView(textArea);
    }
    
    protected void do_button_actionPerformed(ActionEvent e) {
        JFileChooser chooser = new JFileChooser();// 创建文件选择器
        int option = chooser.showOpenDialog(this);// 显示文件打开对话框
        if (option == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile();// 获取用户选择的文件
            String path = file.getAbsolutePath();// 获取文件绝对路径
            textField.setText(path);// 跟新路经信息到文本框
            int splitIndex = path.lastIndexOf("\\");// 文件分隔符索引
            int typeIndex = path.lastIndexOf(".");// 文件类型分割符索引
            if (typeIndex < 0)
                typeIndex = path.length();
            String filePath = path.substring(0, splitIndex);// 截取路径
            String fileName = path.substring(splitIndex + 1, typeIndex);// 截取文件名
            String extName = path.substring(typeIndex);// 截取扩展名
            textArea.setText("");// 清空文本域
            textArea.append("文件名称：" + fileName + "\n");// 添加文件名信息到文本域
            textArea.append("扩展名称：" + extName + "\n");// 添加扩展名信息到文本域
            textArea.append("文件路径：" + filePath + "\n");// 添加文件路径信息到文本域
        }
    }
}
