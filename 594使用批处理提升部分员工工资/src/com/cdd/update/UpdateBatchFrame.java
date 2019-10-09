package com.cdd.update;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JButton;
import javax.swing.ListSelectionModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.List;

public class UpdateBatchFrame extends JFrame {
    
    private JPanel contentPane;
    private BatchUpdate update = new BatchUpdate();
    private JList deptlist;
    private JList laboragelist;
    
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    UpdateBatchFrame frame = new UpdateBatchFrame();
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
    public UpdateBatchFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        setTitle("批量更新员工工资");
        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 434, 262);
        contentPane.add(panel);
        panel.setLayout(null);
        
        JLabel messageLabel = new JLabel("提升部门：");
        messageLabel.setBounds(27, 55, 65, 15);
        panel.add(messageLabel);
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(88, 55, 116, 138);
        panel.add(scrollPane);
        
        List list = update.executeStu();
        String[] dept = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            dept[i] = list.get(i).toString();
        }
        deptlist = new JList(dept);
        scrollPane.setViewportView(deptlist);
        
        JLabel laborageLabel = new JLabel("提升工资：");
        laborageLabel.setBounds(214, 55, 65, 15);
        panel.add(laborageLabel);
        String laborage[] = new String[] { "200", "500", "600", "800", "1000" };
        laboragelist = new JList(laborage);
        laboragelist.setSelectionMode(0);
        
        laboragelist.setBounds(277, 55, 116, 138);
        panel.add(laboragelist);
        
        JButton okButton = new JButton("确定");
        okButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                do_okButton_actionPerformed(arg0);
            }
        });
        okButton.setBounds(139, 217, 65, 23);
        panel.add(okButton);
        
        JButton closeButton = new JButton("关闭");
        closeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                do_closeButton_actionPerformed(arg0);
            }
        });
        closeButton.setBounds(243, 217, 65, 23);
        panel.add(closeButton);
    }
    
    protected void do_okButton_actionPerformed(ActionEvent arg0) {
        Object[] dept = deptlist.getSelectedValues(); // 获取用户选择的要增加工资的所有部门数组
        String laborage = laboragelist.getSelectedValue().toString(); // 获取用户选择的增加工资的额度
        if (dept.length > 0 && !laborage.equals("")) { // 如果用户选择的信息不为空
            update.updateBatch(dept, Integer.parseInt(laborage)); // 调用批量修改方法
        }
        JOptionPane.showMessageDialog(getContentPane(), "数据修改成功！", "信息提示框",
                JOptionPane.WARNING_MESSAGE);
    }
    
    protected void do_closeButton_actionPerformed(ActionEvent arg0) {
        System.exit(0);
    }
}
