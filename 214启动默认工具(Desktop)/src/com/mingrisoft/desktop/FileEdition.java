package com.mingrisoft.desktop;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

public class FileEdition {
    public static void main(String[] a) {
        if (Desktop.isDesktopSupported()) {// 测试Desktop类在当前平台是否可用
            Desktop desktop = Desktop.getDesktop();// 获得Desktop类的实例
            try {
                desktop.edit(new File("d:\\新书快递.txt"));// 编辑本地文件
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
