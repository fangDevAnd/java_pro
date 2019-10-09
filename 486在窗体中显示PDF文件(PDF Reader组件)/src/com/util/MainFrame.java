package com.util;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PageFormat;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.Timer;
import javax.swing.WindowConstants;

import javax.swing.filechooser.FileFilter;
import com.sun.pdfview.FullScreenWindow;
import com.sun.pdfview.OutlineNode;

import com.sun.pdfview.PDFFile;

import com.sun.pdfview.PDFPage;
import com.sun.pdfview.PagePanel;

public class MainFrame extends JFrame 
         {
    
    /**
     * @param args
     */
    // 放置主内容的面板
    JPanel jpmain = new JPanel();
    // 放置读取PDF文档内容的面板
    PagePanel jp;
    // 全屏显示面板
    FullScreenWindow fullScreen;
    
    JScrollPane documentScroller = new JScrollPane();
    // PDFRender包中获取PDF文档的PDFFile类
    PDFFile pdffile;
    // 用户填写页码的文本框
    JTextField pageField;
    
    int curpage = -1;
    // 用于获取大纲的OutlineNode对象
    OutlineNode outline = null;
    JButton smallButton;
    JButton fullScreenButton;
    PageFormat pformat = PrinterJob.getPrinterJob().defaultPage();
    String docName;
    
    private Timer activityMonitor;
    private SimulateActivity activity;
    PagePanel fspp;

    
    private Point loc = null;
    private Point tmp = null;
    private boolean isDragged = false;
    
    public MainFrame() {
        setTitle("显示PDF文件");
        setSize(new Dimension(721, 666));
        getContentPane().setLayout(null);
        getContentPane().add(CreateMenuBar());
        /*
         * 创建中间的内容面板
         */
        jp = new PagePanel();

        jp.addMouseMotionListener(new JPMouseMotionAction());
        contentPanel = new JScrollPane();
        contentPanel.setBounds(0, 26, 705, 607);
        getContentPane().add(contentPanel);
        contentPanel.setColumnHeaderView(jpmain);
        jpmain.setLayout(new GridLayout(0, 1));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screen.width - getWidth()) / 2;
        int y = (screen.height - getHeight()) / 2;
        setLocation(x, y);
    }
    
    public static void main(String[] args) {
        // TODO 自动生成方法存根
        new MainFrame().setVisible(true);
    }
    //定义菜单方法
    public JMenuBar CreateMenuBar() {
        
        final JMenuBar menuBar = new JMenuBar();
        menuBar.setBounds(0, 0, 705, 25);
        final JMenu newItemMenuItem1 = new JMenu();
        newItemMenuItem1.setText("文件(F)");
        
        menuBar.add(newItemMenuItem1);
        final JMenuItem openfile = new JMenuItem();
        openfile.setText("打开");
        newItemMenuItem1.add(openfile);
        openfile.setToolTipText("Open PDF file");
        openfile
                .addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        // TODO 自动生成方法存根
                        doOpen();   //打开菜单按钮调用doOpen()方法
                    }
                });
        return menuBar;
    }

    
    
    //为文件选择器添加过滤器
    private File prevDirChoice;    
    FileFilter pdfFilter = new FileFilter() {
        
        public boolean accept(File f) {
            return f.isDirectory() || f.getName().endsWith(".pdf");
        }
        
        public String getDescription() {
            return "Choose a PDF file";
        }
    };    
    private JScrollPane contentPanel;       
    public void doOpen() {
        try {
            if (jp != null) {
                doClose();
            }
            JFileChooser fc = new JFileChooser();   //创建JFileChooser实例
            fc.setCurrentDirectory(prevDirChoice);  //设置当前路径
            fc.setFileFilter(pdfFilter);            //添加文件过滤器
            fc.setMultiSelectionEnabled(false);    
            int returnVal = fc.showOpenDialog(this);    //打开文件选择器
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                try {
                    prevDirChoice = fc.getSelectedFile();   //获取用户选择的路径
                    RandomAccessFile raf = new RandomAccessFile(prevDirChoice
                            .getAbsolutePath(), "r");   //实例化可随机访问的文件对象
                    FileChannel channel = raf.getChannel(); //实例化FileChannel对象
                    ByteBuffer buf = channel.map(FileChannel.MapMode.READ_ONLY,
                            0, channel.size()); 
                    try {
                        pdffile = new PDFFile(buf);     //实例化一个PDFFile实例
                    } catch (IOException ioe) {
                        return;
                    }
                    docName = prevDirChoice.getName();
                    setTitle(docName);
                    activityMonitor = new Timer(500, new ActionListener() {
                        public void actionPerformed(ActionEvent arg0) {
                            int current = activity.getCurrent();
                            contentPanel.getVerticalScrollBar().setValue(
                                    current);
                            if (current == activity.getTarget()) {
                                activityMonitor.stop();
                                
                            }
                        }
                    });
                    
//由于将PDF文档显示出来会耗费内存，所以本实例将前10页文档显示在主窗体上，如果将for循环的终止值修改为pdffile.getNumPages()，则表示显示所有PDF文档中的所有页面
                    for (int i = 1; i < 10; i++) {
                        contentPanel.setViewportView(jpmain); // 将主面板放置在滚动面板上
                        activity = new SimulateActivity(contentPanel
                                .getVerticalScrollBar().getMaximum());
                        new Thread(activity).start();
                        activityMonitor.start();
                        PDFPage page = pdffile.getPage(i); // 获取每页PDF文档
                        PagePanel jp2 = new PagePanel(); // 实例化PagePanel对象
                        jpmain.add(jp2); // 将面板对象添加到主面板中
                        validate(); // 刷新窗体
                        jp2.showPage(page); // 显示该页PDF文档
                    }

                    try {
                        outline = pdffile.getOutline();
                    } catch (IOException ioe) {
                    }
                    if (outline != null) {
                        if (outline.getChildCount() > 0) {
                            JTree jt = new JTree(outline);
                            jt.setRootVisible(false);                          
                            JScrollPane jsp = new JScrollPane(jt);                            
                        }                        
                    }
                } catch (Exception ioe) {
                    ioe.printStackTrace();
                }
                validate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void gotoPage(int pagenum) {
        if (pagenum < 0) {
            pagenum = 0;
        } else if (pagenum >= pdffile.getNumPages()) {
            pagenum = pdffile.getNumPages() - 1;
        }
        
    }
    

    
    public void doClose() {
        if (jp != null) {
            jpmain.removeAll();
        }
        pdffile = null;
    }
    
    public void doPageSetup() {
        PrinterJob pjob = PrinterJob.getPrinterJob();
        pformat = pjob.pageDialog(pformat);
    }

  
    private final class JPMouseMotionAction extends
            java.awt.event.MouseMotionAdapter {
        public void mouseDragged(java.awt.event.MouseEvent e) {
            if (isDragged) {// 在拖放事件中不断记下和改变位置
                loc = new Point(jp.getLocation().x + e.getX() - tmp.x, jp
                        .getLocation().y
                        + e.getY() - tmp.y);
                jp.setLocation(loc);
            }
        }
    }  
    
    class SimulateActivity implements Runnable {
        private volatile int current;        
        private int target;        
        public SimulateActivity(int t) {
            // TODO 自动生成构造函数存根
            current = 0;
            target = t;
        }        
        public int getTarget() {
            return target;
        }        
        public int getCurrent() {
            return current;
        }        
        public void run() {
            try {
                while (current < target) {
                    Thread.sleep(100);
                    current++;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }  
 
}
