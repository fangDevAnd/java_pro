import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;

public class FileSaveDialog extends JFrame {
    
    private JPanel contentPane;
    private JTextArea textArea;
    
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
                    FileSaveDialog frame = new FileSaveDialog();
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
    public FileSaveDialog() {
        setTitle("\u6587\u4EF6\u9009\u62E9\u5BF9\u8BDD\u6846\u6307\u5B9A\u6570\u636E\u5907\u4EFD");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 291);
        
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        
        JMenu menu = new JMenu("\u6587\u4EF6");
        menuBar.add(menu);
        
        JMenuItem menuItem = new JMenuItem("\u4FDD\u5B58");
        menuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                do_menuItem_actionPerformed(e);
            }
        });
        menu.add(menuItem);
        
        JMenuItem menuItem_1 = new JMenuItem("\u9000\u51FA");
        menuItem_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                do_menuItem_1_actionPerformed(e);
            }
        });
        menu.add(menuItem_1);
        contentPane = new JPanel();
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));
        
        JScrollPane scrollPane = new JScrollPane();
        contentPane.add(scrollPane, BorderLayout.CENTER);
        
        textArea = new JTextArea();
        textArea.setFont(new Font("宋体", Font.PLAIN, 14));
        textArea.setLineWrap(true);
        textArea.setTabSize(4);
        scrollPane.setViewportView(textArea);
    }
    
    protected void do_menuItem_actionPerformed(ActionEvent e) {
        String text = textArea.getText();// 获取用户输入
        if (text.isEmpty()) {// 过滤空文本的保存操作
            JOptionPane.showMessageDialog(this, "没有需要保存的文本");
            return;
        }
        JFileChooser chooser = new JFileChooser();// 创建文件选择器
        int option = chooser.showSaveDialog(this);// 打开文件保存对话框
        if (option == JFileChooser.APPROVE_OPTION) {// 处理文件保存操作
            File file = chooser.getSelectedFile();// 获取用户选择的文件
            try {
                FileOutputStream fout = new FileOutputStream(file);// 创建该文件的输出流
                fout.write(text.getBytes());// 把文本保存到文件
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }
    protected void do_menuItem_1_actionPerformed(ActionEvent e) {
        System.exit(0);
    }
}
