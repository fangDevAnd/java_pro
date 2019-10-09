import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FileDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;

public class EnctryAndUnEntryFrame extends JFrame {
    
    private JPanel contentPane;
    private JTextField entryTextField;
    private JTextField saveTextField;
    private JTextField unentryTextField;
    private JTextField unsaveTextField;
    
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    EnctryAndUnEntryFrame frame = new EnctryAndUnEntryFrame();
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
    public EnctryAndUnEntryFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 247);
        setTitle("文件简单加密解密");
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        tabbedPane.setBounds(0, 0, 434, 218);
        contentPane.add(tabbedPane);
        JPanel untryPanene = new JPanel();
        tabbedPane.addTab("解密面板", null, untryPanene, null);
        untryPanene.setLayout(null);
        JPanel entryPanel = new JPanel();
        tabbedPane.addTab("加密面板", null, entryPanel, null);
        entryPanel.setLayout(null);
        JLabel entryLabel = new JLabel("要加密的文件：");
        entryLabel.setBounds(10, 39, 100, 15);
        entryPanel.add(entryLabel);
        
        entryTextField = new JTextField();
        entryTextField.setBounds(115, 36, 168, 21);
        entryPanel.add(entryTextField);
        entryTextField.setColumns(10);
        
        JButton entryButton = new JButton("选择");
        entryButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                do_entryButton_actionPerformed(arg0);
            }
        });
        entryButton.setBounds(293, 31, 69, 23);
        entryPanel.add(entryButton);
        
        JLabel saveLabel = new JLabel("保 存 地 址 ：");
        saveLabel.setBounds(10, 84, 100, 15);
        entryPanel.add(saveLabel);
        
        saveTextField = new JTextField();
        saveTextField.setColumns(10);
        saveTextField.setBounds(115, 81, 168, 21);
        entryPanel.add(saveTextField);
        
        JButton saveButton = new JButton("选择");
        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                do_saveButton_actionPerformed(arg0);
            }
        });
        saveButton.setBounds(293, 80, 69, 23);
        entryPanel.add(saveButton);
        
        JButton confirmButton = new JButton("确认加密");
        confirmButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                do_confirmButton_actionPerformed(arg0);
            }
        });
        confirmButton.setBounds(152, 132, 101, 23);
        entryPanel.add(confirmButton);
        JLabel unentryLabel = new JLabel("要解密的文件：");
        unentryLabel.setBounds(10, 39, 100, 15);
        untryPanene.add(unentryLabel);
        
        unentryTextField = new JTextField();
        unentryTextField.setBounds(115, 36, 168, 21);
        untryPanene.add(unentryTextField);
        unentryTextField.setColumns(10);
        
        JButton unentryButton = new JButton("选择");
        unentryButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                undo_entryButton_actionPerformed(arg0);
            }
        });
        unentryButton.setBounds(293, 31, 69, 23);
        untryPanene.add(unentryButton);
        
        JLabel unsaveLabel = new JLabel("保 存 地 址 ：");
        unsaveLabel.setBounds(10, 84, 100, 15);
        untryPanene.add(unsaveLabel);
        
        unsaveTextField = new JTextField();
        unsaveTextField.setColumns(10);
        unsaveTextField.setBounds(115, 81, 168, 21);
        untryPanene.add(unsaveTextField);
        
        JButton unsaveButton = new JButton("选择");
        unsaveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                undo_saveButton_actionPerformed(arg0);
            }
        });
        unsaveButton.setBounds(293, 80, 69, 23);
        untryPanene.add(unsaveButton);
        
        JButton unconfirmButton = new JButton("确认解密");
        unconfirmButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                undo_confirmButton_actionPerformed(arg0);
            }
        });
        unconfirmButton.setBounds(152, 132, 101, 23);
        untryPanene.add(unconfirmButton);
        
    }
    
    // 选择要加密文件按钮的单击事件
    protected void do_entryButton_actionPerformed(ActionEvent arg0) {
        java.awt.FileDialog fd = new FileDialog(this);
        fd.setVisible(true);
        String filePath = fd.getDirectory() + fd.getFile();
        if (!filePath.equals("") && !(filePath == null)) {
            entryTextField.setText(filePath);
        }
        
    }
    
    // 选择加密后文件的保存地址按钮的单击事件
    protected void do_saveButton_actionPerformed(ActionEvent arg0) {
        FileDialog saveDialog = new FileDialog(this, "保存文件对话框", FileDialog.SAVE);
        saveDialog.setVisible(true);
        String filePath = saveDialog.getDirectory() + saveDialog.getFile();
        if (!filePath.equals("") && !(filePath == null)) {
            saveTextField.setText(filePath);
        }
    }
    
    // 确定加密按钮单击事件
    
protected void do_confirmButton_actionPerformed(ActionEvent arg0) {
    EncryptFile encryFile = new EncryptFile();  //创建保存有文件加密方法的类对象
    encryFile.encry(entryTextField.getText(), saveTextField.getText()); //调用对文件进行加密方法
    JOptionPane.showMessageDialog(getContentPane(), "文件加密成功！", "信息提示框",
            JOptionPane.WARNING_MESSAGE); // 为用户提供提示信息对话框
}
    
    // 选择要解密文件按钮的单击事件
    protected void undo_entryButton_actionPerformed(ActionEvent arg0) {
        FileDialog saveDialog = new FileDialog(this, "保存文件对话框", FileDialog.SAVE);
        saveDialog.setVisible(true);
        String filePath = saveDialog.getDirectory() + saveDialog.getFile();
        unentryTextField.setText(filePath);
    }
    
    // 选择解密后文件的保存地址按钮的单击事件
    protected void undo_saveButton_actionPerformed(ActionEvent arg0) {
        FileDialog saveDialog = new FileDialog(this, "保存文件对话框", FileDialog.SAVE);
        saveDialog.setVisible(true);
        String filePath = saveDialog.getDirectory() + saveDialog.getFile();
        unsaveTextField.setText(filePath);
    }
    
    // 确定解密按钮单击事件
    protected void undo_confirmButton_actionPerformed(ActionEvent arg0) {
        EncryptFile encryFile = new EncryptFile();
        encryFile
                .unEncry(unentryTextField.getText(), unsaveTextField.getText());
        JOptionPane.showMessageDialog(getContentPane(), "文件解密成功！", "信息提示框",
                JOptionPane.WARNING_MESSAGE); // 为用户提供提示信息对话框
    }
    
}
