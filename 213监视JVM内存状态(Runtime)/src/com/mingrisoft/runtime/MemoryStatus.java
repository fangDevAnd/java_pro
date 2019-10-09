package com.mingrisoft.runtime;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JProgressBar;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.SwingConstants;
import java.awt.GridLayout;
import javax.swing.UIManager;

public class MemoryStatus extends JFrame {
    
    /**
     * 
     */
    private static final long serialVersionUID = 3225753245258184286L;
    private JPanel contentPane;
    private JPanel statusPanel;
    private JLabel totalLabel;
    private JLabel freeLabel;
    private JScrollPane scrollPane;
    private JProgressBar progressBar;
    
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
                    MemoryStatus frame = new MemoryStatus();
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
    public MemoryStatus() {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowActivated(WindowEvent e) {
                do_this_windowActivated(e);
            }
        });
        setTitle("\u5185\u5B58\u72B6\u6001");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 300, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);
        
        statusPanel = new JPanel();
        contentPane.add(statusPanel, BorderLayout.SOUTH);
        statusPanel.setLayout(new GridLayout(2, 1, 5, 5));
        
        freeLabel = new JLabel("");
        freeLabel.setFont(new Font("微软雅黑", Font.PLAIN, 18));
        statusPanel.add(freeLabel);
        
        totalLabel = new JLabel("");
        totalLabel.setFont(new Font("微软雅黑", Font.PLAIN, 18));
        statusPanel.add(totalLabel);
        
        scrollPane = new JScrollPane();
        contentPane.add(scrollPane, BorderLayout.CENTER);
        
        progressBar = new JProgressBar();
        progressBar.setStringPainted(true);
        progressBar.setFont(new Font("微软雅黑", Font.PLAIN, 18));
        progressBar.setOrientation(SwingConstants.VERTICAL);
        scrollPane.setViewportView(progressBar);
    }
    
    protected void do_this_windowActivated(WindowEvent e) {
        new Thread(new Memory()).start();
    }
    
    private class Memory implements Runnable {
        @Override
        public void run() {
            while (true) {
                System.gc();// 强制虚拟机进行垃圾回收以释放内存
                int free = (int) Runtime.getRuntime().freeMemory() / 1024;// 获得可用内存
                int total = (int) Runtime.getRuntime().totalMemory() / 1024;// 获得总共内存
                int status = free * 100 / total;// 获得内存使用率
                freeLabel.setText("可用内存：" + free + "Kb"); // 显示可用内存
                totalLabel.setText("总共内存：" + total + "Kb"); // 显示总共内存
                progressBar.setValue(status); // 显示内存的使用率
                progressBar.setString("可用内存：" + status + "%");
                try {
                    Thread.sleep(1000);// 线程休眠1秒钟进行动态更新
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
}
