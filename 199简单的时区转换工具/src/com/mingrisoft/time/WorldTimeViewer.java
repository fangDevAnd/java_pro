package com.mingrisoft.time;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Calendar;
import java.util.TimeZone;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

public class WorldTimeViewer extends JFrame {
    
    /**
     * 
     */
    private static final long serialVersionUID = -5988076597643890566L;
    private JPanel contentPane;
    private JComboBox TZComboBox;
    private JLabel resultLabel;
    
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Throwable e) {
            e.printStackTrace();
        }
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    WorldTimeViewer frame = new WorldTimeViewer();
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
    public WorldTimeViewer() {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowActivated(WindowEvent e) {
                do_this_windowActivated(e);
            }
        });
        setTitle("\u4E16\u754C\u65F6\u95F4\u67E5\u770B\u5668");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 400, 140);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new GridLayout(2, 1, 5, 5));
        
        JPanel TZPanel = new JPanel();
        contentPane.add(TZPanel);
        TZPanel.setLayout(new GridLayout(1, 2, 5, 5));
        
        JLabel TZLabel = new JLabel("\u9009\u62E9\u76EE\u6807\u65F6\u533A\uFF1A");
        TZLabel.setFont(new Font("微软雅黑", Font.PLAIN, 18));
        TZPanel.add(TZLabel);
        
        TZComboBox = new JComboBox();
        TZComboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                do_foramtterComboBox_actionPerformed(e);
            }
        });
        TZComboBox.setFont(new Font("微软雅黑", Font.PLAIN, 18));
        TZPanel.add(TZComboBox);
        
        JPanel resultPanel = new JPanel();
        contentPane.add(resultPanel);
        resultPanel.setLayout(new GridLayout(1, 2, 5, 5));
        
        JLabel tipLabel = new JLabel("\u76EE\u6807\u65F6\u533A\u65E5\u671F\uFF1A");
        tipLabel.setFont(new Font("微软雅黑", Font.PLAIN, 18));
        resultPanel.add(tipLabel);
        
        resultLabel = new JLabel("");
        resultLabel.setFont(new Font("微软雅黑", Font.PLAIN, 18));
        resultPanel.add(resultLabel);
    }
    
    protected void do_this_windowActivated(WindowEvent e) {
        String[] timezones = TimeZone.getAvailableIDs();// 获得所有可用的时区ID
        for (String patten : timezones) {
            TZComboBox.addItem(patten);// 将所有ID添加到组合框中
        }
    }
    
    protected void do_foramtterComboBox_actionPerformed(ActionEvent e) {
        String currentTimezone = (String) TZComboBox.getSelectedItem();// 获得选择的ID
        Calendar calendar = Calendar.getInstance();// 获得日历对象
        calendar.setTimeZone(TimeZone.getTimeZone(currentTimezone));// 设置日历所在时区
        StringBuilder result = new StringBuilder();// 利用StringBuilder来保存结果
        result.append(calendar.getTimeZone().getDisplayName() + " ");// 获得当前时区描述名称
        result.append(calendar.get(Calendar.HOUR_OF_DAY) + ":");// 获得当前时钟的小时
        result.append(calendar.get(Calendar.MINUTE));// 获得当前时区的分钟
        resultLabel.setText(result.toString());// 更新结果
    }
    
}
