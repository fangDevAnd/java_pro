import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JWindow;

import com.lzw.panel.BGPanel;

public class InfoWindow extends JWindow {
    
    /**
     * Create the frame.
     */
    public InfoWindow() {
        addMouseListener(new MouseAdapter() {// 添加鼠标事件监听器
            @Override
            public void mousePressed(MouseEvent e) {
                do_this_mousePressed(e);// 调用鼠标事件处理方法
            }
        });
        setBounds(100, 100, 359, 228);// 设置窗体大小
        BGPanel panel = new BGPanel();// 创建背景面板
        // 设置背景图片
        panel.setImage(Toolkit.getDefaultToolkit().getImage(
                InfoWindow.class.getResource("/com/lzw/panel/back.jpg")));
        getContentPane().add(panel, BorderLayout.CENTER);
    }
    
    protected void do_this_mousePressed(MouseEvent e) {// 鼠标事件处理方法
        dispose();// 鼠标单击，则销毁这个窗体
    }
}
