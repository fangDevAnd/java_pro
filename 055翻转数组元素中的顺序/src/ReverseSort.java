import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.UIManager;
import javax.swing.JTextField;
import javax.swing.JLabel;

public class ReverseSort extends JFrame {
    
    private JPanel contentPane;
    
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
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ReverseSort frame = new ReverseSort();
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
    public ReverseSort() {
        setTitle("\u53CD\u8F6C\u6570\u7EC4\u4E2D\u5143\u7D20\u7684\u987A\u5E8F");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        GridBagLayout gbl_contentPane = new GridBagLayout();
        gbl_contentPane.columnWidths = new int[] { 0, 0 };
        gbl_contentPane.rowHeights = new int[] { 0, 0, 0, 0, 0, 0 };
        gbl_contentPane.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
        gbl_contentPane.rowWeights = new double[] { 0.0, 0.0, 0.0, 1.0, 0.0,
                Double.MIN_VALUE };
        contentPane.setLayout(gbl_contentPane);
        
        label = new JLabel(
                "\u8F93\u5165\u6570\u7EC4\u5185\u5BB9\uFF0C\u4EE5\u7A7A\u683C\u5206\u9694\u6570\u7EC4\u5143\u7D20");
        GridBagConstraints gbc_label = new GridBagConstraints();
        gbc_label.anchor = GridBagConstraints.WEST;
        gbc_label.insets = new Insets(0, 0, 5, 0);
        gbc_label.gridx = 0;
        gbc_label.gridy = 0;
        contentPane.add(label, gbc_label);
        
        textField = new JTextField();
        GridBagConstraints gbc_textField = new GridBagConstraints();
        gbc_textField.fill = GridBagConstraints.HORIZONTAL;
        gbc_textField.insets = new Insets(0, 0, 5, 0);
        gbc_textField.gridx = 0;
        gbc_textField.gridy = 1;
        contentPane.add(textField, gbc_textField);
        
        label_1 = new JLabel("\u6570\u7EC4\u5143\u7D20\u53CD\u8F6C\uFF1A");
        GridBagConstraints gbc_label_1 = new GridBagConstraints();
        gbc_label_1.anchor = GridBagConstraints.WEST;
        gbc_label_1.insets = new Insets(0, 0, 5, 0);
        gbc_label_1.gridx = 0;
        gbc_label_1.gridy = 2;
        contentPane.add(label_1, gbc_label_1);
        
        JScrollPane scrollPane_1 = new JScrollPane();
        GridBagConstraints gbc_scrollPane_1 = new GridBagConstraints();
        gbc_scrollPane_1.insets = new Insets(0, 0, 5, 0);
        gbc_scrollPane_1.fill = GridBagConstraints.BOTH;
        gbc_scrollPane_1.gridx = 0;
        gbc_scrollPane_1.gridy = 3;
        contentPane.add(scrollPane_1, gbc_scrollPane_1);
        
        textArea = new JTextArea();
        scrollPane_1.setViewportView(textArea);
        
        JButton button_1 = new JButton("\u53CD\u8F6C\u6392\u5E8F\u6CD5");
        button_1.setActionCommand("\u5FEB\u901F\u6392\u5E8F\u6CD5");
        button_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                do_button_1_actionPerformed(e);
            }
        });
        GridBagConstraints gbc_button_1 = new GridBagConstraints();
        gbc_button_1.gridx = 0;
        gbc_button_1.gridy = 4;
        contentPane.add(button_1, gbc_button_1);
    }
    
    private JTextField textField;
    private JTextArea textArea;
    private JLabel label;
    private JLabel label_1;
    
    protected void do_button_1_actionPerformed(ActionEvent e) {
        String inText = textField.getText();// 获取用户输入
        String[] array = inText.split(" {1,}");
        int len = array.length;
        textArea.setText("");
        for (int i = 0; i < len / 2; i++) {
            String temp = array[i];
            array[i] = array[len - 1 - i];
            array[len - 1 - i] = temp;
            for (String string : array) {
                textArea.append(string + "  ");
            }
            textArea.append("\n");
        }
    }
    
}
