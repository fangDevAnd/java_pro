package com.lzw;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Image;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PreviewFileDialog extends JFrame {
    
    private JPanel contentPane;
    private JFileChooser fileChooser;
    private ImagePreviewer imageLabel;
    private ImagePreviewer previewer;
    
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    PreviewFileDialog frame = new PreviewFileDialog();
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
    public PreviewFileDialog() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 629, 428);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);
        
        JPanel panel = new JPanel();
        contentPane.add(panel, BorderLayout.NORTH);
        
        JButton chooseButton = new JButton(
                "\u9009\u62E9\u56FE\u7247\u6587\u4EF6");
        chooseButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                do_button_actionPerformed(e);
            }
        });
        panel.add(chooseButton);
        
        imageLabel = new ImagePreviewer((JFileChooser) null);
        contentPane.add(imageLabel, BorderLayout.CENTER);
        initFileChooser();
    }
    
    /**
     * 初始化文件选择器
     */
    private void initFileChooser() {
        fileChooser = new JFileChooser();// 创建文件选择器
        previewer = new ImagePreviewer(fileChooser);// 创建图片预览标签
        fileChooser.setFileFilter(new FileNameExtensionFilter("图片文件", "jpg",
                "gif", "png"));
        // 为指定属性变更添加事件监听器
        fileChooser.addPropertyChangeListener("SelectedFileChangedProperty",
                new PropertyChangeListener() {
                    public void propertyChange(PropertyChangeEvent evt) {
                        // 属性改变时设置预览标签的图片
                        previewer.setImageFile((File) evt.getNewValue());
                    }
                });
        fileChooser.setAccessory(previewer);
    }
    
    /**
     * 选择图片文件按钮的事件处理方法
     * 
     * @param e
     */
    protected void do_button_actionPerformed(ActionEvent e) {
        int option = fileChooser.showOpenDialog(this);// 显示打开文件对话框
        if (option == JFileChooser.APPROVE_OPTION) {
            // 获取选择的文件对象
            File file = fileChooser.getSelectedFile();
            // 更新窗体中图片
            imageLabel.setImageFile(file);
        }
    }
}

/**
 * 自定义图片预览标签
 * 
 * @author 李钟尉
 */
class ImagePreviewer extends JLabel {
    public ImagePreviewer(JFileChooser chooser) {
        // 初始大小
        setPreferredSize(new Dimension(200, 200));
        setHorizontalAlignment(JLabel.CENTER);// 水平居中
        setBorder(new LineBorder(Color.GRAY));// 设置边框
        setOpaque(true);// 标签不透明
        setBackground(Color.WHITE);// 设置背景色
        setText("没有设置图片");// 默认文本
    }
    
    /**
     * 设置标签图片的方法
     * 
     * @param file
     */
    public void setImageFile(File file) {
        setText("");// 清空图片预览标签的文本
        if (file == null) {// 如果文件对象为空
            setText("没有设置图片");// 设置默认提示文本
            return;// 终止方法
        }
        // 创建图标对象
        ImageIcon icon = new ImageIcon(file.getPath());
        if (icon.getIconWidth() > getWidth()) {// 设置图标大小
            icon = new ImageIcon(icon.getImage().getScaledInstance(getWidth(),
                    -1, Image.SCALE_DEFAULT));
        }
        setIcon(icon);// 为标签设置图标
        repaint();// 重新绘制界面
    }
}