package com.lzw;

import java.io.File;
import java.io.FileFilter;

import javax.swing.JProgressBar;
import javax.swing.table.DefaultTableModel;

public class SearchThread extends Thread {
    class TempFileFilter implements FileFilter {
        @Override
        public boolean accept(File pathname) {
            if (pathname.getName().endsWith(".tmp") || pathname.isDirectory())
                return true;
            return false;
        }
    }
    
    private File driver;
    private DefaultTableModel tableModel;
    private boolean searching = true;
    
    public boolean isSearching() {
        return searching;
    }
    
    public void setSearching(boolean searching) {
        this.searching = searching;
    }
    
    private TempFileFilter tempFileFilter = new TempFileFilter();
    private JProgressBar progressBar;
    
    public SearchThread(File driver, DefaultTableModel tableModel,
            JProgressBar progressBar) {
        this.driver = driver;
        this.tableModel = tableModel;
        progressBar.setStringPainted(true);
        this.progressBar = progressBar;
    }
    
    /*
     * (non-Javadoc)
     * @see java.lang.Thread#run()
     */
    public void run() {
        if (driver != null)
            listTempFiles(driver);
    }
    
    /**
     * 递归遍历磁盘文件夹的方法
     * 
     * @param driver
     */
    private void listTempFiles(File driver) {
        // 获取指定磁盘或文件夹的子列表
        File[] files = driver.listFiles(tempFileFilter);
        if (files == null)
            return;
        progressBar.setIndeterminate(true);// 设置进度条以不确定方式滚动
        for (File file : files) {// 遍历文件数组
            progressBar.setString(file.toString());// 进度条显示搜索文件夹
            if (file.isFile() && searching) {// 处理文件
                tableModel.addRow(new Object[] { file.getName(), file,
                        file.length(), "未处理" });// 添加文件信息到表格控件
            } else if (file.isDirectory() && searching) {// 处理文件夹
                listTempFiles(file);// 递归方法遍历文件夹
            }
        }
        progressBar.setIndeterminate(false);// 停止进度条
        progressBar.setString("搜索完成");// 提示搜索完成
    }
}
