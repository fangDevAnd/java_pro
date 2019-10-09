package com.mingrisoft.oop;

public class GIFSaver implements ImageSaver {
    
    @Override
    public void save() {
        System.out.println("将图片保存成GIF格式");
    }
}
