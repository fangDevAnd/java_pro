import java.awt.EventQueue;
import java.awt.FileDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.io.*;

public class UniteFrame extends JFrame {
    
    private JPanel contentPane;
    private JTextField savePathtextField;
    private JList fileList;
    Vector<String> vector = new Vector<String>();
    DefaultListModel list = new DefaultListModel();
    List<File> listFile = new ArrayList<File>();
   
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    UniteFrame frame = new UniteFrame();
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
    public UniteFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        setTitle("合并txt文件");
        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 434, 262);
        contentPane.add(panel);
        panel.setLayout(null);
        
        JButton choiceButton = new JButton("选择合并文件");
        choiceButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                do_choiceButton_actionPerformed(arg0);
            }
        });
        choiceButton.setBounds(22, 36, 114, 23);
        panel.add(choiceButton);
        
        JButton saveButton = new JButton("保存地址");
        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                do_saveButton_actionPerformed(arg0);
            }
        });
        saveButton.setBounds(22, 187, 114, 23);
        panel.add(saveButton);
        
        savePathtextField = new JTextField();
        savePathtextField.setBounds(152, 188, 228, 21);
        panel.add(savePathtextField);
        savePathtextField.setColumns(10);
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(152, 36, 228, 129);
        panel.add(scrollPane);
        
        fileList = new JList(list);
        
        scrollPane.setViewportView(fileList);
        
        JButton submitbutton = new JButton("确定合并");
        submitbutton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                do_button_actionPerformed(arg0);
            }
        });
        submitbutton.setBounds(102, 229, 114, 23);
        panel.add(submitbutton);
    }
    
    protected void do_choiceButton_actionPerformed(ActionEvent arg0) {
        java.awt.FileDialog fd = new FileDialog(this); // 创建选择文件对话框
        fd.setVisible(true); // 设置窗体为可视状态
        String filePath = fd.getDirectory() + fd.getFile(); // 获取用户选择的文件路径
        if (filePath.endsWith(".txt")) { // 判断用户选择的是否为txt文件
            list.addElement(fd.getDirectory() + fd.getFile()); // 将用户选择的文件添加到列表中
            listFile.add(new File((fd.getDirectory() + fd.getFile()))); // 将用户选择的文件名添加到集合对象中
        }
    }
    
    protected void do_saveButton_actionPerformed(ActionEvent arg0) {
        FileDialog saveDialog = new FileDialog(this, "保存文件对话框", FileDialog.SAVE);
        saveDialog.setVisible(true);
        if (saveDialog.getDirectory() + saveDialog.getFile() != null) {
            savePathtextField.setText(saveDialog.getDirectory()
                    + saveDialog.getFile());
        }
    }
    
    protected void do_button_actionPerformed(ActionEvent arg0) {
        UniteFile unitFile = new UniteFile(); // 创建UniteFile对象
        unitFile.writeFiles(listFile, savePathtextField.getText());// 调用合并文件方法
        JOptionPane.showMessageDialog(getContentPane(), "文件合并成功！", "信息提示框",
                JOptionPane.WARNING_MESSAGE); // 为用户提供提示信息对话框
    }
    
}
