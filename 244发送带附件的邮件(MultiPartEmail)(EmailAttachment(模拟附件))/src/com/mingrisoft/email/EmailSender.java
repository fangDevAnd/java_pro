package com.mingrisoft.email;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.MultiPartEmail;

public class EmailSender extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -649057193496634534L;
	private JPanel contentPane;
	private JTextField hostTextField;
	private JTextField toEmailTextField;
	private JTextField fromEmailTextField;
	private JTextField usernameTextField;
	private JTextField passwordTextField;
	private JTextField titleTextField;
	private JTextArea contentTextArea;
	private JTextField attachTextField;
	private EmailAttachment attachment;

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
					EmailSender frame = new EmailSender();
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
	public EmailSender() {
		setTitle("\u90AE\u4EF6\u53D1\u9001\u5DE5\u5177");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(2, 1, 5, 5));
		
		JPanel messagePanel = new JPanel();
		contentPane.add(messagePanel);
		messagePanel.setLayout(new GridLayout(6, 1, 5, 5));
		
		JPanel hostPanel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) hostPanel.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		messagePanel.add(hostPanel);
		
		JLabel hostLabel = new JLabel("\u670D\u52A1\u5668\u5730\u5740\uFF1A");
		hostPanel.add(hostLabel);
		
		hostTextField = new JTextField();
		hostPanel.add(hostTextField);
		hostTextField.setColumns(24);
		
		JPanel toEmailPanel = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) toEmailPanel.getLayout();
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		messagePanel.add(toEmailPanel);
		
		JLabel toEmailLabel = new JLabel("\u6536\u4EF6\u4EBA\u90AE\u7BB1\uFF1A");
		toEmailPanel.add(toEmailLabel);
		
		toEmailTextField = new JTextField();
		toEmailPanel.add(toEmailTextField);
		toEmailTextField.setColumns(24);
		
		JPanel fromEmailPanel = new JPanel();
		FlowLayout flowLayout_2 = (FlowLayout) fromEmailPanel.getLayout();
		flowLayout_2.setAlignment(FlowLayout.LEFT);
		messagePanel.add(fromEmailPanel);
		
		JLabel fromEmailLabel = new JLabel("\u53D1\u4EF6\u4EBA\u90AE\u7BB1\uFF1A");
		fromEmailPanel.add(fromEmailLabel);
		
		fromEmailTextField = new JTextField();
		fromEmailPanel.add(fromEmailTextField);
		fromEmailTextField.setColumns(24);
		
		JPanel authPanel = new JPanel();
		FlowLayout flowLayout_3 = (FlowLayout) authPanel.getLayout();
		flowLayout_3.setAlignment(FlowLayout.LEFT);
		messagePanel.add(authPanel);
		
		JLabel usernameLabel = new JLabel("\u7528\u6237\u540D\uFF1A");
		authPanel.add(usernameLabel);
		
		usernameTextField = new JTextField();
		authPanel.add(usernameTextField);
		usernameTextField.setColumns(10);
		
		JLabel passwordLabel = new JLabel("\u5BC6   \u7801\uFF1A");
		authPanel.add(passwordLabel);
		
		passwordTextField = new JTextField();
		authPanel.add(passwordTextField);
		passwordTextField.setColumns(10);
		
		JPanel titlePanel = new JPanel();
		FlowLayout flowLayout_4 = (FlowLayout) titlePanel.getLayout();
		flowLayout_4.setAlignment(FlowLayout.LEFT);
		messagePanel.add(titlePanel);
		
		JLabel titleLabel = new JLabel("\u90AE\u4EF6\u7684\u4E3B\u9898\uFF1A");
		titlePanel.add(titleLabel);
		
		titleTextField = new JTextField();
		titlePanel.add(titleTextField);
		titleTextField.setColumns(24);
		
		JPanel attachPanel = new JPanel();
		FlowLayout flowLayout_5 = (FlowLayout) attachPanel.getLayout();
		flowLayout_5.setAlignment(FlowLayout.LEFT);
		messagePanel.add(attachPanel);
		
		JLabel attachLabel = new JLabel("\u9644\u4EF6\uFF1A");
		attachPanel.add(attachLabel);
		
		attachTextField = new JTextField();
		attachPanel.add(attachTextField);
		attachTextField.setColumns(20);
		
		JButton attachButton = new JButton("\u9009\u62E9\u9644\u4EF6");
		attachButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				do_attachButton_actionPerformed(arg0);
			}
		});
		attachPanel.add(attachButton);
		
		JPanel contentPanel = new JPanel();
		contentPane.add(contentPanel);
		contentPanel.setLayout(new BorderLayout(0, 0));
		
		JLabel contentLabel = new JLabel("\u5185\u5BB9\uFF1A");
		contentPanel.add(contentLabel, BorderLayout.WEST);
		
		JPanel buttonPanel = new JPanel();
		contentPanel.add(buttonPanel, BorderLayout.SOUTH);
		
		JButton button = new JButton("\u53D1\u9001\u90AE\u4EF6");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				do_button_actionPerformed(arg0);
			}
		});
		buttonPanel.add(button);
		
		contentTextArea = new JTextArea();
		contentPanel.add(contentTextArea, BorderLayout.CENTER);
	}

	protected void do_button_actionPerformed(ActionEvent arg0) {
		String hostName = hostTextField.getText();
		String toEmail = toEmailTextField.getText();
		String fromEmail = fromEmailTextField.getText();
		String username = usernameTextField.getText();
		String password = passwordTextField.getText();
		String title = titleTextField.getText();
		String content = contentTextArea.getText();
		if(hostName.length()==0) {
			JOptionPane.showMessageDialog(this, "请输入服务器地址", "", JOptionPane.WARNING_MESSAGE);
		}
		if(toEmail.length()==0) {
			JOptionPane.showMessageDialog(this, "请输入收件人邮箱", "", JOptionPane.WARNING_MESSAGE);
		}
		if(fromEmail.length()==0) {
			JOptionPane.showMessageDialog(this, "请输入发件人邮箱", "", JOptionPane.WARNING_MESSAGE);
		}
		if(username.length()==0) {
			JOptionPane.showMessageDialog(this, "请输入用户名", "", JOptionPane.WARNING_MESSAGE);
		}
		if(password.length()==0) {
			JOptionPane.showMessageDialog(this, "请输入密    码", "", JOptionPane.WARNING_MESSAGE);
		}
		if(title.length()==0) {
			JOptionPane.showMessageDialog(this, "请输入主题", "", JOptionPane.WARNING_MESSAGE);
		}
		if(content.length()==0) {
			JOptionPane.showMessageDialog(this, "请输入内容", "", JOptionPane.WARNING_MESSAGE);
		}
		MultiPartEmail email = new MultiPartEmail();
		if (attachment!=null) {
			try {
				email.attach(attachment);
			} catch (EmailException e) {
				e.printStackTrace();
			}
		}
		email.setHostName(hostName);
		try {
			email.addTo(toEmail);
			email.setFrom(fromEmail);
		} catch (EmailException e) {
			e.printStackTrace();
		}
		email.setAuthentication(username, password);
		email.setSubject(title);
		try {
			email.setMsg(content);
			email.send();
		} catch (EmailException e) {
			e.printStackTrace();
		}
	}
	protected void do_attachButton_actionPerformed(ActionEvent arg0) {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		fileChooser.setMultiSelectionEnabled(false);
		int result = fileChooser.showOpenDialog(this);
		if (result == JFileChooser.APPROVE_OPTION) {
			File selectFile = fileChooser.getSelectedFile();
			attachTextField.setText(selectFile.getAbsolutePath());
			attachment = new EmailAttachment();
			attachment.setDescription("附件");
			attachment.setDisposition(EmailAttachment.ATTACHMENT);
			attachment.setName(selectFile.getName());
			attachment.setPath(selectFile.getAbsolutePath());
		}
	}
}
