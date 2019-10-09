package com.cdd.userView;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JTable;

public class GetProfitFrame extends JFrame {
    
    private JPanel contentPane;
    private JTable table;
    private LocalTableModel model = new LocalTableModel();
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    GetProfitFrame frame = new GetProfitFrame();
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
    public GetProfitFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        setTitle("使用视图与计算数据");
        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 434, 262);
        contentPane.add(panel);
        panel.setLayout(null);
        
        JLabel messageLabel = new JLabel("从视图中检索数据");
        messageLabel.setFont(new Font("新宋体", Font.PLAIN, 13));
        messageLabel.setBounds(139, 27, 135, 15);
        panel.add(messageLabel);
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(46, 77, 332,138);
        panel.add(scrollPane);
        
        table = new JTable(model);
        UserViewData userViewDate = new UserViewData();
        List list = userViewDate.selectView();
        for(int i = 0;i<list.size();i++){
            Ware ware = (Ware)list.get(i);
            model.addRow(new Object[]{ware.getwName(),ware.getProfit()});
        }
        scrollPane.setViewportView(table);
    }
}
