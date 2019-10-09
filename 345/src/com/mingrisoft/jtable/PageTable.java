package com.mingrisoft.jtable;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class PageTable extends JFrame {
    
    /**
     * 
     */
    private static final long serialVersionUID = 1698867170940729036L;
    private JPanel contentPane;
    private JTable table;
    private JButton firstPageButton;
    private JButton latePageButton;
    private JButton nextPageButton;
    private JButton lastPageButton;
    private int maxPageNumber;
    private int currentPageNumber = 1;
    private double pageSize = 5;
    private DefaultTableModel defaultModel;
    
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
                    PageTable frame = new PageTable();
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
    public PageTable() {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowActivated(WindowEvent e) {
                do_this_windowActivated(e);
            }
        });
        setTitle("\u8868\u683C\u7684\u5206\u9875");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);
        
        JPanel panel = new JPanel();
        contentPane.add(panel, BorderLayout.SOUTH);
        
        firstPageButton = new JButton("\u9996\u9875");
        firstPageButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                do_firstPageButton_actionPerformed(e);
            }
        });
        firstPageButton.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 14));
        panel.add(firstPageButton);
        
        latePageButton = new JButton("\u524D\u4E00\u9875");
        latePageButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                do_latePageButton_actionPerformed(e);
            }
        });
        latePageButton.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 14));
        panel.add(latePageButton);
        
        nextPageButton = new JButton("\u540E\u4E00\u9875");
        nextPageButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                do_nextPageButton_actionPerformed(e);
            }
        });
        nextPageButton.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 14));
        panel.add(nextPageButton);
        
        lastPageButton = new JButton("\u672B\u9875");
        lastPageButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                do_lastPageButton_actionPerformed(e);
            }
        });
        lastPageButton.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 14));
        panel.add(lastPageButton);
        
        JScrollPane scrollPane = new JScrollPane();
        contentPane.add(scrollPane, BorderLayout.CENTER);
        
        table = new JTable();
        table.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 14));
        table.setRowHeight(35);
        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 16));
        header.setPreferredSize(new Dimension(header.getWidth(), 40));
        scrollPane.setViewportView(table);
    }
    
    protected void do_this_windowActivated(WindowEvent e) {
        defaultModel = (DefaultTableModel) table.getModel();
        defaultModel.setRowCount(0);
        defaultModel.setColumnIdentifiers(new Object[] { "ÐòºÅ", "Æ½·½Êý" });
        for (int i = 0; i < 23; i++) {
            defaultModel.addRow(new Object[] { i, i * i });
        }
        maxPageNumber = (int) Math.ceil(defaultModel.getRowCount() / pageSize);
        table.setModel(defaultModel);
        firstPageButton.setEnabled(false);
        latePageButton.setEnabled(false);
        nextPageButton.setEnabled(true);
        lastPageButton.setEnabled(true);
    }
    
    @SuppressWarnings("unchecked")
    protected void do_firstPageButton_actionPerformed(ActionEvent e) {
        currentPageNumber = 1;
        Vector dataVector = defaultModel.getDataVector();
        DefaultTableModel newModel = new DefaultTableModel();
        newModel.setColumnIdentifiers(new Object[] { "ÐòºÅ", "Ëæ»úÊý" });
        for (int i = 0; i < pageSize; i++) {
            newModel.addRow((Vector) dataVector.elementAt(i));
        }
        table.setModel(newModel);
        firstPageButton.setEnabled(false);
        latePageButton.setEnabled(false);
        nextPageButton.setEnabled(true);
        lastPageButton.setEnabled(true);
    }
    
    @SuppressWarnings("unchecked")
    protected void do_latePageButton_actionPerformed(ActionEvent e) {
        currentPageNumber--;
        Vector dataVector = defaultModel.getDataVector();
        DefaultTableModel newModel = new DefaultTableModel();
        newModel.setColumnIdentifiers(new Object[] { "ÐòºÅ", "Ëæ»úÊý" });
        for (int i = 0; i < pageSize; i++) {
            newModel.addRow((Vector) dataVector.elementAt((int) (pageSize * (currentPageNumber - 1) + i)));
        }
        table.setModel(newModel);
        if (currentPageNumber == 1) {
            firstPageButton.setEnabled(false);
            latePageButton.setEnabled(false);
        }
        nextPageButton.setEnabled(true);
        lastPageButton.setEnabled(true);
    }
    
    @SuppressWarnings("unchecked")
    protected void do_nextPageButton_actionPerformed(ActionEvent e) {
        currentPageNumber++;
        Vector dataVector = defaultModel.getDataVector();
        DefaultTableModel newModel = new DefaultTableModel();
        newModel.setColumnIdentifiers(new Object[] { "ÐòºÅ", "Æ½·½Êý" });
        if (currentPageNumber == maxPageNumber) {
            int lastPageSize = (int) (defaultModel.getRowCount() - pageSize * (maxPageNumber - 1));
            for (int i = 0; i < lastPageSize; i++) {
                newModel.addRow((Vector) dataVector.elementAt((int) (pageSize * (maxPageNumber - 1) + i)));
            }
            nextPageButton.setEnabled(false);
            lastPageButton.setEnabled(false);
        } else {
            for (int i = 0; i < pageSize; i++) {
                newModel.addRow((Vector) dataVector.elementAt((int) (pageSize * (currentPageNumber - 1) + i)));
            }
        }
        table.setModel(newModel);
        firstPageButton.setEnabled(true);
        latePageButton.setEnabled(true);
    }
    
    @SuppressWarnings("unchecked")
    protected void do_lastPageButton_actionPerformed(ActionEvent e) {
        currentPageNumber = maxPageNumber;
        Vector dataVector = defaultModel.getDataVector();
        DefaultTableModel newModel = new DefaultTableModel();
        newModel.setColumnIdentifiers(new Object[] { "ÐòºÅ", "Æ½·½Êý" });
        int lastPageSize = (int) (defaultModel.getRowCount() - pageSize * (maxPageNumber - 1));
        
        if (lastPageSize == 5) {
            for (int i = 0; i < pageSize; i++) {
                newModel.addRow((Vector) dataVector.elementAt((int) (pageSize * (maxPageNumber - 1) + i)));
            }
        } else {
            for (int i = 0; i < lastPageSize; i++) {
                newModel.addRow((Vector) dataVector.elementAt((int) (pageSize * (maxPageNumber - 1) + i)));
            }
        }
        
        table.setModel(newModel);
        firstPageButton.setEnabled(true);
        latePageButton.setEnabled(true);
        nextPageButton.setEnabled(false);
        lastPageButton.setEnabled(false);
        
    }
}
