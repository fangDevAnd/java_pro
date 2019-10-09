package com.lzw;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

import javax.swing.Icon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.filechooser.FileSystemView;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeCellRenderer;
import javax.swing.tree.TreePath;

public class DiskTree extends JFrame {
    
    /**
     * 本实例树控件单元的渲染器
     * 
     * @author 李钟尉
     */
    private final class FileRenderer implements TreeCellRenderer {
        @Override
        public Component getTreeCellRendererComponent(JTree tree, Object value,
                boolean selected, boolean expanded, boolean leaf, int row,
                boolean hasFocus) {
            // 转换value为节点对象
            DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;
            Object userObject = node.getUserObject();
            if (!(userObject instanceof File)) {
                return new JLabel(userObject.toString());
            }
            File folder = (File) userObject;
            FileSystemView view = FileSystemView.getFileSystemView();
            Icon icon = view.getSystemIcon(folder);
            JLabel label = new JLabel("" + folder, icon, JLabel.LEADING);
            label.setBackground(Color.CYAN);
            if (selected) {
                label.setOpaque(true);
            } else {
                label.setOpaque(false);
            }
            return label;
        }
    }
    
    private JPanel contentPane;
    private JTree tree;
    private DefaultMutableTreeNode rootNode;
    
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    DiskTree frame = new DiskTree();
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
    public DiskTree() {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowActivated(WindowEvent e) {
                do_this_windowActivated(e);
            }
        });
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);
        
        JScrollPane scrollPane = new JScrollPane();
        contentPane.add(scrollPane, BorderLayout.CENTER);
        
        tree = new JTree();
        tree.addTreeSelectionListener(new TreeSelectionListener() {
            public void valueChanged(TreeSelectionEvent e) {
                do_tree_valueChanged(e);
            }
        });
        tree.setCellRenderer(new FileRenderer());
        scrollPane.setViewportView(tree);
        rootNode = new DefaultMutableTreeNode("我的电脑");
        DefaultTreeModel model = new DefaultTreeModel(rootNode);
        tree.setModel(model);
    }
    
    /**
     * 窗体激活时调用的方法
     * 
     * @param e
     */
    protected void do_this_windowActivated(WindowEvent e) {
        File[] disks = File.listRoots();// 获取磁盘列表
        for (File file : disks) {// 遍历列表
            // 使用文件对象创建树节点
            DefaultMutableTreeNode node = new DefaultMutableTreeNode(file);
            rootNode.add(node);// 添加节点到树控件的根节点
        }
        tree.expandPath(new TreePath(rootNode));// 展开根节点
    }
    
    public JTree getTree() {
        return tree;
    }
    
    /**
     * 改变节点选项时，执行的方法
     * 
     * @param e
     */
    protected void do_tree_valueChanged(TreeSelectionEvent e) {
        TreePath path = e.getPath();// 获取树选择路径
        // 获取选择路径中的节点
        DefaultMutableTreeNode node = (DefaultMutableTreeNode) path
                .getLastPathComponent();
        // 获取节点中的用户对象
        Object userObject = node.getUserObject();
        if (!(userObject instanceof File)) {
            return;
        }
        File folder = (File) userObject;// 把用户对象转换为文件对象
        if (!folder.isDirectory())// 过滤非文件夹的选择操作
            return;
        File[] files = folder.listFiles();// 获取文件夹中的文件列表
        for (File file : files) {// 遍历文件列表数组
            // 使用文件做用户对象创建节点
            node.add(new DefaultMutableTreeNode(file));
        }
    }
}
