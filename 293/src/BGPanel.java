

import java.awt.*;
import java.io.Serializable;

import javax.swing.*;

/**
 * @author 李钟尉
 */
public class BGPanel extends JPanel implements Serializable {
    
    private static final long serialVersionUID = 1L;
    private Image image; // @jve:decl-index=0:
    public static final int HORIZONGTAL_FILL = 1;
    public static final int VERTICAL_FILL = 2;
    public static final int BOTH_FILL = 3;
    public static final int NO_FILL = 0;
    private int iconFill = NO_FILL;
    
    /**
     * This is the default constructor
     */
    public BGPanel() {
        super();
        initialize();
    }
    
    /**
     * This method initializes this
     * 
     * @return void
     */
    private void initialize() {
        this.setSize(new Dimension(300, 200));
        this.setLayout(new GridBagLayout());
    }
    
    public Image getImage() {
        return image;
    }
    
    public void setImage(Image icon) {
        this.image = icon;
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);// 完成原来控件外观的绘制
        if (image != null) {// 开始自定义背景的绘制
            switch (iconFill) {// 判断背景填充方式
                case NO_FILL:// 不填充
                    g.drawImage(image, 0, 0, this);// 绘制原始图片大小
                    break;
                case HORIZONGTAL_FILL:// 水平填充
                    // 绘制与控件等宽的图片
                    g.drawImage(image, 0, 0, getWidth(), image.getHeight(this),
                            this);
                    break;
                case VERTICAL_FILL:// 垂直填充
                    // 绘制与控件等高的图片
                    g.drawImage(image, 0, 0, image.getWidth(this), getHeight(),
                            this);
                    break;
                case BOTH_FILL:// 双向填充
                    // 绘制与控件同等大小的图片
                    g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
                    break;
                default:
                    break;
            }
        }
    }
    
    public int getIconFill() {
        return iconFill;
    }
    
    /**
     * 设置背景重复方式
     * 
     * @param repeat
     *            重复方式
     */
    public void setIconFill(int iconFill) {
        this.iconFill = iconFill;
    }
    
}
