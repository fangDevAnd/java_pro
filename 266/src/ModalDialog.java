import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

public class ModalDialog extends JFrame {
    
    private final JPanel contentPanel = new JPanel();
    
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
        try {
            ModalDialog dialog = new ModalDialog();
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Create the dialog.
     */
    public ModalDialog() {
        setTitle("\u6A21\u6001\u5BF9\u8BDD\u6846\u4E0E\u975E\u6A21\u6001\u5BF9\u8BDD\u6846");
        setBounds(100, 100, 450, 300);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(null);
        
        JButton button = new JButton(
                "\u6A21\u6001\u663E\u793A\u5BF9\u8BDD\u6846");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                do_button_actionPerformed(e);
            }
        });
        button.setBounds(103, 64, 206, 30);
        contentPanel.add(button);
        
        JButton button_1 = new JButton(
                "\u975E\u6A21\u6001\u663E\u793A\u5BF9\u8BDD\u6846");
        button_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                do_button_1_actionPerformed(e);
            }
        });
        button_1.setBounds(103, 106, 206, 30);
        contentPanel.add(button_1);
        {
            JPanel buttonPane = new JPanel();
            buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
            getContentPane().add(buttonPane, BorderLayout.SOUTH);
            {
                JButton okButton = new JButton("\u786E\u5B9A");
                okButton.setActionCommand("OK");
                buttonPane.add(okButton);
                getRootPane().setDefaultButton(okButton);
            }
            {
                JButton cancelButton = new JButton("\u53D6\u6D88");
                cancelButton.setActionCommand("Cancel");
                buttonPane.add(cancelButton);
            }
        }
    }
    
    protected void do_button_actionPerformed(ActionEvent e) {
        JDialog dialog = new JDialog(this);// 创建当前窗体的对话框
        dialog.setModal(true);// 设置对话框为模态
        dialog.setSize(300, 200);// 设置对话框大小
        dialog.setLocationByPlatform(true);// 由系统平台布置窗体位置
        dialog.setTitle("模态对话框");// 对话框标题
        dialog.setVisible(true);// 显示对话框
    }
    
    protected void do_button_1_actionPerformed(ActionEvent e) {
        JDialog dialog = new JDialog(this);// 创建当前窗体的对话框
        dialog.setModal(false);// 设置对话框为模态
        dialog.setSize(300, 200);// 设置对话框大小
        dialog.setLocationByPlatform(true);// 由系统平台布置窗体位置
        dialog.setTitle("非模态对话框");// 对话框标题
        dialog.setVisible(true);// 显示对话框
    }
}
