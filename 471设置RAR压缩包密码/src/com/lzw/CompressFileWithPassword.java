package com.lzw;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.JProgressBar;
import javax.swing.JPasswordField;
import javax.swing.ImageIcon;

public class CompressFileWithPassword extends JFrame {
    
    /**
     * ִ��ѹ����������ϲ�
     * 
     * @author ����ξ
     */
private final class CompressThread extends Thread {
    public void run() {
        try {
            progressBar.setString(null);// ��ʼ���������ؼ�
            progressBar.setValue(0);
            // ��ȡ����
            String pass1 = String.valueOf(passwordField1.getPassword());
            // ��ȡȷ������
            String pass2 = String.valueOf(passwordField2.getPassword());
            String passCommand = "";// �������������ַ���
            if (pass1 != null) {
                if (pass1.equals(pass2)) {// �ж����������Ƿ���ͬ
                    passCommand = "-p\"" + pass1 + "\" ";// �����������
                }else{// ����������벻һ������ֹ��ǰ����
                    JOptionPane.showMessageDialog(null, "�����������벻һ��");
                    return;
                }
            }
            // ��ȡ���ؼ�������ģ��
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            int rowCount = model.getRowCount();// ��ȡ����ģ���б������
            StringBuilder fileList = new StringBuilder();
            for (int i = 0; i < rowCount; i++) {// �������ݱ��ģ���е��ļ�����
                File file = (File) model.getValueAt(i, 2);
                fileList.append(file.getPath() + "\n");// ���ļ�·���浽�ַ�����������
            }
            // ������ʱ�ļ������ڱ���ѹ���ļ��б�
            File listFile = File.createTempFile("fileList", ".tmp");
            FileOutputStream fout = new FileOutputStream(listFile);
            fout.write(fileList.toString().getBytes());// �����ַ������������ݵ���ʱ�ļ�
            fout.close();
            
            // ����ѹ�������ַ���
            final String command = "rar a " + passCommand
                    + rarFile.getPath() + " @" + listFile.getPath();
            Runtime runtime = Runtime.getRuntime();// ��ȡRuntime����
            progress = runtime.exec(command.toString() + "\n");// ִ��ѹ������
            progress.getOutputStream().close();// �رս��������
            // ��ȡ����������
            Scanner scan = new Scanner(progress.getInputStream());
            while (scan.hasNext()) {
                String line = scan.nextLine();// ��ȡ������ʾ������Ϣ
                // ��ȡ��ʾ��Ϣ�Ľ��Ȱٷֱȵ�����λ��
                int index = line.lastIndexOf("%") - 3;
                if (index <= 0)
                    continue;
                // ��ȡ���Ȱٷֱ��ַ���
                String substring = line.substring(index, index + 3);
                // ��ȡ�����İٷֱ���ֵ
                int percent = Integer.parseInt(substring.trim());
                progressBar.setValue(percent);// �ڽ������ؼ���ʾ�ٷֱ�
            }
            progressBar.setString("���");
            scan.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
    
    private JPanel contentPane;
    private JTable table;
    private JPanel panel;
    private JButton addButton;
    private JButton removeButton;
    private JPanel panel_1;
    private JLabel label;
    private JTextField compressFileField;
    private JButton browseButton;
    private File rarFile;
    private JButton compressButton;
    private JProgressBar progressBar;
    private JButton stopButton;
    private Process progress;
    private JLabel label_1;
    private JLabel label_2;
    private JLabel label_3;
    private JPasswordField passwordField1;
    private JPasswordField passwordField2;
    
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    CompressFileWithPassword frame = new CompressFileWithPassword();
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
    public CompressFileWithPassword() {
        setTitle("\u6587\u4EF6\u538B\u7F29\u4E3ARAR\u6587\u6863");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 332);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);
        
        JScrollPane scrollPane = new JScrollPane();
        contentPane.add(scrollPane, BorderLayout.CENTER);
        
        table = new JTable();
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.setModel(new DefaultTableModel(new Object[][] {}, new String[] {
                "\u6587\u4EF6\u540D\u79F0", "\u6587\u4EF6\u5927\u5C0F",
                "\u6587\u4EF6\u8DEF\u5F84" }));
        table.getColumnModel().getColumn(0).setPreferredWidth(125);
        table.getColumnModel().getColumn(2).setPreferredWidth(250);
        table.getTableHeader().setReorderingAllowed(false);
        scrollPane.setViewportView(table);
        scrollPane.getViewport().setBackground(Color.WHITE);
        
        panel_1 = new JPanel();
        contentPane.add(panel_1, BorderLayout.SOUTH);
        GridBagLayout gbl_panel_1 = new GridBagLayout();
        gbl_panel_1.columnWidths = new int[] { 0, 60, 0, 0 };
        gbl_panel_1.rowHeights = new int[] { 0, 0, 0, 0, 0 };
        gbl_panel_1.columnWeights = new double[] { 0.0, 1.0, 0.0,
                Double.MIN_VALUE };
        gbl_panel_1.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0,
                Double.MIN_VALUE };
        panel_1.setLayout(gbl_panel_1);
        
        progressBar = new JProgressBar();
        progressBar.setStringPainted(true);
        GridBagConstraints gbc_progressBar = new GridBagConstraints();
        gbc_progressBar.gridwidth = 3;
        gbc_progressBar.insets = new Insets(0, 0, 5, 0);
        gbc_progressBar.fill = GridBagConstraints.HORIZONTAL;
        gbc_progressBar.gridx = 0;
        gbc_progressBar.gridy = 0;
        panel_1.add(progressBar, gbc_progressBar);
        
        label = new JLabel("\u538B\u7F29\u6587\u6863\uFF1A");
        GridBagConstraints gbc_label = new GridBagConstraints();
        gbc_label.fill = GridBagConstraints.HORIZONTAL;
        gbc_label.insets = new Insets(0, 0, 5, 5);
        gbc_label.gridx = 0;
        gbc_label.gridy = 1;
        panel_1.add(label, gbc_label);
        
        compressFileField = new JTextField();
        compressFileField.setEditable(false);
        GridBagConstraints gbc_compressFileField = new GridBagConstraints();
        gbc_compressFileField.insets = new Insets(0, 0, 5, 5);
        gbc_compressFileField.fill = GridBagConstraints.HORIZONTAL;
        gbc_compressFileField.gridx = 1;
        gbc_compressFileField.gridy = 1;
        panel_1.add(compressFileField, gbc_compressFileField);
        compressFileField.setColumns(10);
        
        browseButton = new JButton("\u6D4F\u89C8");
        browseButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                do_browseButton_actionPerformed(arg0);
            }
        });
        GridBagConstraints gbc_browseButton = new GridBagConstraints();
        gbc_browseButton.insets = new Insets(0, 0, 5, 0);
        gbc_browseButton.gridx = 2;
        gbc_browseButton.gridy = 1;
        panel_1.add(browseButton, gbc_browseButton);
        
        label_2 = new JLabel("\u8F93\u5165\u5BC6\u7801\uFF1A");
        GridBagConstraints gbc_label_2 = new GridBagConstraints();
        gbc_label_2.anchor = GridBagConstraints.EAST;
        gbc_label_2.insets = new Insets(0, 0, 5, 5);
        gbc_label_2.gridx = 0;
        gbc_label_2.gridy = 2;
        panel_1.add(label_2, gbc_label_2);
        
        passwordField1 = new JPasswordField();
        passwordField1.setEchoChar('A');
        GridBagConstraints gbc_passwordField1 = new GridBagConstraints();
        gbc_passwordField1.insets = new Insets(0, 0, 5, 5);
        gbc_passwordField1.fill = GridBagConstraints.HORIZONTAL;
        gbc_passwordField1.gridx = 1;
        gbc_passwordField1.gridy = 2;
        panel_1.add(passwordField1, gbc_passwordField1);
        
        label_3 = new JLabel("");
        label_3.setIcon(new ImageIcon(CompressFileWithPassword.class
                .getResource("/com/lzw/key.png")));
        GridBagConstraints gbc_label_3 = new GridBagConstraints();
        gbc_label_3.gridheight = 2;
        gbc_label_3.insets = new Insets(0, 0, 5, 0);
        gbc_label_3.gridx = 2;
        gbc_label_3.gridy = 2;
        panel_1.add(label_3, gbc_label_3);
        
        label_1 = new JLabel("\u786E\u8BA4\u5BC6\u7801\uFF1A");
        GridBagConstraints gbc_label_1 = new GridBagConstraints();
        gbc_label_1.anchor = GridBagConstraints.EAST;
        gbc_label_1.insets = new Insets(0, 0, 0, 5);
        gbc_label_1.gridx = 0;
        gbc_label_1.gridy = 3;
        panel_1.add(label_1, gbc_label_1);
        
        passwordField2 = new JPasswordField();
        passwordField2.setEchoChar('A');
        GridBagConstraints gbc_passwordField2 = new GridBagConstraints();
        gbc_passwordField2.insets = new Insets(0, 0, 0, 5);
        gbc_passwordField2.fill = GridBagConstraints.HORIZONTAL;
        gbc_passwordField2.gridx = 1;
        gbc_passwordField2.gridy = 3;
        panel_1.add(passwordField2, gbc_passwordField2);
        
        panel = new JPanel();
        contentPane.add(panel, BorderLayout.WEST);
        panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        
        addButton = new JButton("\u589E\u52A0");
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                do_addButton_actionPerformed(arg0);
            }
        });
        panel.add(addButton);
        
        removeButton = new JButton("\u79FB\u9664");
        removeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                do_removeButton_actionPerformed(arg0);
            }
        });
        panel.add(removeButton);
        
        compressButton = new JButton("\u538B\u7F29");
        compressButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                do_compressButton_actionPerformed(arg0);
            }
        });
        panel.add(compressButton);
        
        stopButton = new JButton("\u505C\u6B62");
        stopButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                do_stopButton_actionPerformed(arg0);
            }
        });
        panel.add(stopButton);
    }
    
    /**
     * ���Ӱ�ť���¼�������
     * 
     * @param arg0
     */
    protected void do_addButton_actionPerformed(ActionEvent arg0) {
        JFileChooser chooser = new JFileChooser();// �����ļ�ѡ����
        chooser.setAcceptAllFileFilterUsed(false);
        chooser.setMultiSelectionEnabled(true);// ���������ļ���ѡ
        int option = chooser.showOpenDialog(this);// ��ʾ�ļ��򿪶Ի���
        if (option != JFileChooser.APPROVE_OPTION)
            return;
        File[] files = chooser.getSelectedFiles();// ��ȡ�û�ѡ���ļ�����
        // ��ȡ���ؼ�������ģ��
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        for (File file : files) {// �����û�ѡ����ļ�����
            // ���ļ���Ϣ��ӵ����ؼ���ģ����
            model.addRow(new Object[] { file.getName(), file.length(), file });
        }
    }
    
    /**
     * �Ƴ���ť���¼�������
     * 
     * @param arg0
     */
    protected void do_removeButton_actionPerformed(ActionEvent arg0) {
        int[] rows = table.getSelectedRows();// ��ȡ�����ѡ�е�������������
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        for (int i = rows.length - 1; i >= 0; i--) {
            model.removeRow(rows[i]);// �������Ƴ�����ѡ����
        }
    }
    
    /**
     * ѡ��ѹ��RAR�ĵ��������ť���¼�������
     * 
     * @param arg0
     */
    protected void do_browseButton_actionPerformed(ActionEvent arg0) {
        JFileChooser chooser = new JFileChooser();// �����ļ�ѡ����
        // ����ѡ���ļ�����ΪRar
        chooser.setFileFilter(new FileNameExtensionFilter("RARѹ���ĵ�", "rar"));
        chooser.setAcceptAllFileFilterUsed(false);
        int option = chooser.showSaveDialog(this);// ��ʾ����Ի���
        if (option != JFileChooser.APPROVE_OPTION)
            return;
        rarFile = chooser.getSelectedFile();// ��ȡ�û����Ƶ�RAR�ļ�
        compressFileField.setText(rarFile.getPath());// ��ʾRAR�ļ�·����Ϣ
    }
    
    /**
     * ѹ����ť���¼�������
     * 
     * @param arg0
     */
    protected void do_compressButton_actionPerformed(ActionEvent arg0) {
        if (rarFile == null) {
            browseButton.doClick();
            if (rarFile == null)
                return;
        }
        progressBar.setVisible(true);
        CompressThread compressThread = new CompressThread(); // ����ѹ���߳�
        compressThread.start();// �����߳�
    }
    
    /**
     * ֹͣ��ť���¼�������
     * 
     * @param arg0
     */
    protected void do_stopButton_actionPerformed(ActionEvent arg0) {
        if (progress != null) {
            progress.destroy();
            progressBar.setValue(0);
            progressBar.setVisible(false);
        }
    }
}
