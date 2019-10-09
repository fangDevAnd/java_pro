package com.lzw.ip;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class CText extends JTextField {
    public CText() {
        setBorder(null);// 取消边框
        setHorizontalAlignment(SwingConstants.CENTER);// 文本居中
        setFont(getFont().deriveFont(16f));// 色绘制默认16号字体
        addKeyListener(new KeyAdapter() {// 添加按键事件监听器
            @Override
            public void keyTyped(KeyEvent e) {
                if (("0123456789" + (char) 8).indexOf(e.getKeyChar()) < 0) {
                    e.consume();// 屏蔽非数字与回退键的输入
                    return;
                }
                if (e.getKeyChar() == (char) 8) {
                    return;// 屏蔽回退键
                }
                String text = getText() + e.getKeyChar();// 获取最新输入
                if (!text.isEmpty()) {// 如果输入非空
                    int value = Integer.parseInt(text);// 把输入解析为整数
                    if (value > 225) {// 如果整数大于225
                        e.consume();// 取消本次输入
                        return;
                    }
                }
                // 如果输入文本过长或输入的是dot字符
                if (getText().length() > 2 || e.getKeyChar() == '.') {
                    e.consume();// 取消本次输入
                    transferFocus();// 把输入焦点传递给下一个控件
                    return;
                }
            }
            
            @Override
            public void keyPressed(KeyEvent e) {
                // 屏蔽粘贴快捷键
                if (e.getKeyCode() == KeyEvent.VK_V && e.isControlDown()) {
                    e.consume();
                }
            }
        });
    }
    
    /**
     * 获取输入整数值的方法
     * 
     * @return
     */
    public int getInt() {
        String text = getText();// 获取输入文本
        if (text.isEmpty())// 屏蔽空输入
            return 0;
        int value = Integer.parseInt(text);// 解析输入为整数
        return value;
    }
    
    @Override
    public String toString() {
        return getInt() + "";
    }
    
}
