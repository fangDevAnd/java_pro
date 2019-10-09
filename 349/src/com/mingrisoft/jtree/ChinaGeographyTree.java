package com.mingrisoft.jtree;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.UIManager;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ChinaGeographyTree extends JFrame {
    
    /**
     * 
     */
    private static final long serialVersionUID = -6221329006071145576L;
    private JPanel contentPane;
    private JTree tree;
    
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
                    ChinaGeographyTree frame = new ChinaGeographyTree();
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
    public ChinaGeographyTree() {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowActivated(WindowEvent e) {
                do_this_windowActivated(e);
            }
        });
        setTitle("\u4E2D\u56FD\u884C\u653F\u533A\u57DF\u6811");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);
        
        JScrollPane scrollPane = new JScrollPane();
        contentPane.add(scrollPane, BorderLayout.CENTER);
        
        tree = new JTree();
        tree.setFont(new Font("微软雅黑", Font.PLAIN, 16));
        scrollPane.setViewportView(tree);
    }
    
    protected void do_this_windowActivated(WindowEvent e) {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("中国");
        DefaultMutableTreeNode municipalities = new DefaultMutableTreeNode("直辖市");
        municipalities.add(new DefaultMutableTreeNode("北京"));
        municipalities.add(new DefaultMutableTreeNode("上海"));
        municipalities.add(new DefaultMutableTreeNode("天津"));
        municipalities.add(new DefaultMutableTreeNode("重庆"));
        DefaultMutableTreeNode province = new DefaultMutableTreeNode("省");
        province.add(new DefaultMutableTreeNode("黑龙江"));
        province.add(new DefaultMutableTreeNode("吉林"));
        province.add(new DefaultMutableTreeNode("辽宁"));
        province.add(new DefaultMutableTreeNode("江苏"));
        province.add(new DefaultMutableTreeNode("安徽"));
        province.add(new DefaultMutableTreeNode("四川"));
        province.add(new DefaultMutableTreeNode("青海"));
        province.add(new DefaultMutableTreeNode("山东"));
        province.add(new DefaultMutableTreeNode("广东"));
        province.add(new DefaultMutableTreeNode("河南"));
        province.add(new DefaultMutableTreeNode("湖南"));
        province.add(new DefaultMutableTreeNode("海南"));
        province.add(new DefaultMutableTreeNode("江西"));
        province.add(new DefaultMutableTreeNode("山西"));
        province.add(new DefaultMutableTreeNode("陕西"));
        province.add(new DefaultMutableTreeNode("河北"));
        province.add(new DefaultMutableTreeNode("湖北"));
        province.add(new DefaultMutableTreeNode("贵州"));
        province.add(new DefaultMutableTreeNode("浙江"));
        province.add(new DefaultMutableTreeNode("福建"));
        province.add(new DefaultMutableTreeNode("台湾"));
        province.add(new DefaultMutableTreeNode("甘肃"));
        province.add(new DefaultMutableTreeNode("云南"));
        DefaultMutableTreeNode ARegion = new DefaultMutableTreeNode("自治区");
        ARegion.add(new DefaultMutableTreeNode("内蒙古自治区"));
        ARegion.add(new DefaultMutableTreeNode("宁夏回族自治区"));
        ARegion.add(new DefaultMutableTreeNode("新疆维吾尔族自治区"));
        ARegion.add(new DefaultMutableTreeNode("西藏自治区"));
        ARegion.add(new DefaultMutableTreeNode("广西壮族自治区"));
        DefaultMutableTreeNode SARegion = new DefaultMutableTreeNode("特别行政区");
        SARegion.add(new DefaultMutableTreeNode("香港"));
        SARegion.add(new DefaultMutableTreeNode("澳门"));
        root.add(municipalities);
        root.add(province);
        root.add(ARegion);
        root.add(SARegion);
        DefaultTreeModel model = new DefaultTreeModel(root);
        tree.setModel(model);
    }
}
