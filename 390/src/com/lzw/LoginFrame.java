//package com.lzw;
//
//import java.awt.BorderLayout;
//import java.awt.Color;
//import java.awt.Dimension;
//import java.awt.Rectangle;
//
//import javax.swing.JButton;
//import javax.swing.JFrame;
//import javax.swing.JPanel;
//import javax.swing.JPasswordField;
//import javax.swing.JTextField;
//import javax.swing.SwingUtilities;
//import javax.swing.UIManager;
//import javax.swing.UnsupportedLookAndFeelException;
//import javax.swing.border.MatteBorder;
//import javax.swing.JLabel;
//
///**
// * @author ����ξ
// */
//public class LoginFrame extends JFrame {
//
//    private static final long serialVersionUID = 1L;
//    private JPanel jContentPane = null;
//    private JTextField userName = null;
//    private JPasswordField password = null;
//    private JButton loginButton = null;
//
//    /**
//     * �����û����ı���
//     *
//     * @return javax.swing.JTextField
//     */
//    private JTextField getUserName() {
//        if (userName == null) {
//            userName = new JTextField();// �����ı���
//            userName.setBackground(new Color(0, 0, 0, 0));// �����ı���͸������ɫ
//            // �����ı���λ�����С
//            userName.setBounds(new Rectangle(70, 26, 162, 21));// �����ı����С
//            MatteBorder border = new MatteBorder(0, 0, 2, 0, new Color(0, 250,
//                    154));// �����߿�
//            userName.setBorder(border);// ɫ���Ʊ߿�
//        }
//        return userName;
//    }
//
//    /**
//     * ���������
//     *
//     * @return javax.swing.JPasswordField
//     */
//    private JPasswordField getPassword() {
//        if (password == null) {
//            password = new JPasswordField();// ���������
//            // ���������λ�úʹ�С
//            password.setBounds(new Rectangle(71, 57, 159, 22));
//            password.setBackground(new Color(0, 0, 0, 0));// ����͸����ɫ
//            password.setOpaque(false);// ����͸��
//            password.setBorder(new MatteBorder(0, 0, 2, 0, new Color(255, 215,
//                    0)));// ���ñ߿�
//            password.setEchoChar('��');// ����������ַ�
//        }
//        return password;
//    }
//
//    /**
//     * ������¼��ť
//     *
//     * @return javax.swing.JButton
//     */
//    private JButton getLoginButton() {
//        if (loginButton == null) {
//            loginButton = new JButton("��¼");
//            // ���ð�ťλ�����С
//            loginButton.setBounds(new Rectangle(244, 22, 68, 68));
//        }
//        return loginButton;
//    }
//
//    /**
//     * �������ڷ���
//     *
//     * @param args
//     * @throws UnsupportedLookAndFeelException
//     *             ��֧�ֵ����
//     */
//    public static void main(String[] args)
//            throws UnsupportedLookAndFeelException {
//        try {
//            UIManager
//                    .setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
//        } catch (Throwable e) {
//            e.printStackTrace();
//        }
//        // ���ô������
//        SwingUtilities.invokeLater(new Runnable() {
//            public void run() {
//                // ������¼����
//                LoginFrame loginFrame = new LoginFrame();
//                // ��ʾ��¼����
//                loginFrame.setVisible(true);
//            }
//        });
//    }
//
//    /**
//     * ��¼����Ĺ��췽��
//     */
//    public LoginFrame() {
//        super();
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setTitle("�ı�����»��߱߿�");
//        jContentPane = new JPanel();
//        // ���ô����������
//        this.setContentPane(jContentPane);
//        // ���ò��ֹ�����
//        jContentPane.setLayout(new BorderLayout());
//        JPanel panel = new JPanel();
//        panel.setBackground(Color.WHITE);
//        panel.setLayout(null);
//        panel.add(getUserName(), null);// ����ı���
//        panel.add(getPassword(), null);// ��������
//        panel.add(getLoginButton(), null);// ��ӵ�¼��ť
//        jContentPane.add(panel, BorderLayout.CENTER);
//
//        JLabel label = new JLabel("�û�����");
//        label.setBounds(22, 26, 55, 18);
//        panel.add(label);
//
//        JLabel label_1 = new JLabel("�ܡ��룺");
//        label_1.setBounds(22, 59, 55, 18);
//        panel.add(label_1);
//        // ���ô����С
//        setSize(new Dimension(375, 143));
//        setLocationRelativeTo(null);// �������
//    }
//}
