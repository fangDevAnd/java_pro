package com.lzw;
import java.awt.EventQueue;
import javax.swing.JButton;
import javax.swing.JComboBox;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.UIManager;

import com.swtdesigner.SwingResourceManager;


public class RunDialogDemo extends JFrame {

	private JComboBox comboBox;
	private JTextArea textArea;
	/**
	 * Launch the application
	 * @param args
	 */
public static void main(String args[]) {
	EventQueue.invokeLater(new Runnable() {
		public void run() {
			try {
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
				RunDialogDemo frame = new RunDialogDemo();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	});
}

	/**
	 * Create the frame
	 */
	public RunDialogDemo() {
		super();
		getContentPane().setLayout(null);
		setBounds(100, 100, 379, 188);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		final JLabel label = new JLabel();
		label.setIcon(SwingResourceManager.getIcon(RunDialogDemo.class, "/logo.jpg"));
		label.setBounds(10, 10, 66, 51);
		getContentPane().add(label);

		textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setOpaque(false);
		textArea.setLineWrap(true);
		textArea.setText("请输入程序、文件夹、文档或Internet资源的名称，Windows将为您打开它。");
		textArea.setBounds(53, 10, 308, 51);
		getContentPane().add(textArea);

		final JLabel label_1 = new JLabel();
		label_1.setText("打开（O）：");
		label_1.setBounds(10, 67, 80, 18);
		getContentPane().add(label_1);

		comboBox = new JComboBox();
		comboBox.setBounds(96, 63, 265, 27);
		getContentPane().add(comboBox);

		final JButton button = new JButton();
		button.setText("确定");
		button.setBounds(154, 111, 60, 28);
		getContentPane().add(button);

		final JButton button_1 = new JButton();
		button_1.setText("取消");
		button_1.setBounds(227, 111, 60, 28);
		getContentPane().add(button_1);

		final JButton button_2 = new JButton();
		button_2.setText("浏览");
		button_2.setBounds(301, 111, 60, 28);
		getContentPane().add(button_2);
		//
	}

}
