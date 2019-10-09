import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
public class WriteObjectFrame extends JFrame {
    
    private JPanel contentPane;
    private JTextField pathTextField;
    private JTextArea textArea = new JTextArea();
    
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    WriteObjectFrame frame = new WriteObjectFrame();
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
    public WriteObjectFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        setTitle("实现对象序列化化与反序列化");
        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 434, 262);
        contentPane.add(panel);
        panel.setLayout(null);
        
        JLabel messageLabel = new JLabel("文件地址：");
        messageLabel.setBounds(42, 40, 68, 15);
        panel.add(messageLabel);
        
        pathTextField = new JTextField();
        pathTextField.setBounds(111, 37, 185, 21);
        panel.add(pathTextField);
        pathTextField.setColumns(10);
        
        JButton pathButton = new JButton("选择");
        pathButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                do_pathButton_actionPerformed(arg0);
            }
        });
        pathButton.setBounds(306, 36, 93, 23);
        panel.add(pathButton);
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(42, 81, 357, 159);
        panel.add(scrollPane);
        scrollPane.setViewportView(textArea);
    }
    
    // 定义获取只可以选择文件夹的选择框
    public JFileChooser getChooser() {
        JFileChooser fd = new JFileChooser();
        String windows = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
        try {
            UIManager.setLookAndFeel(windows);
        } catch (Exception e) {
            e.printStackTrace();
        }// 设置见面风格
        SwingUtilities.updateComponentTreeUI(fd);// 使设置的界面风格生效
        fd.setFileSelectionMode(fd.DIRECTORIES_ONLY); // 指示只显示目录
        fd.showOpenDialog(this);
        return fd;
    }
    
    protected void do_pathButton_actionPerformed(ActionEvent arg0) {
        JFileChooser chooser = getChooser();
        if ((chooser.getSelectedFile() != null)
                && (!chooser.getSelectedFile().equals(""))) {
            String strPath = chooser.getSelectedFile().getPath();
            pathTextField.setText(strPath+"ClassControl.sert");
        }      
        
        String file = "c://MyClass.txt";
        SerializeObject.serialize(file);
        Object obk[] = SerializeObject.deserialize(file);
        textArea.append("实现对象反序列化，读取的内容为："+"\n");
        textArea.append(obk[0].toString()+"\n");
        textArea.append(obk[1].toString());
    }
}
