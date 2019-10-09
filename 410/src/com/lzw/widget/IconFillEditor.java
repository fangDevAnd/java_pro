package com.lzw.widget;

import java.beans.PropertyEditorSupport;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JOptionPane;

public class IconFillEditor extends PropertyEditorSupport {
    private String[] tags = { "²»Ìî³ä", "Ë®Æ½Ìî³ä", "´¹Ö±Ìî³ä", "Ë«ÏòÌî³ä" };
    private String[] values = { "NO_FILL", "HORIZONGTAL_FILL", "VERTICAL_FILL",
            "BOTH_FILL" };
    
    @Override
    public String getAsText() {
        int fill = Integer.parseInt(getValue().toString());
        return tags[fill];
    }
    
    @Override
    public String getJavaInitializationString() {
        int fill = Integer.parseInt(getValue().toString());
        String string = BGPanel.class.getName().replace('$', '.') + "."
                + values[fill];
        return string;
    }
    
    @Override
    public String[] getTags() {
        return tags;
    }
    
    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        int index = Arrays.asList(tags).indexOf(text);
        if (index >= 0)
            setValue(index);
    }
    
}
