package com.mingrisoft.jtable;

import javax.swing.table.AbstractTableModel;

public class ComboBoxTableModel extends AbstractTableModel {
    
    private static final long serialVersionUID = 5523252281451951512L;
    
    private static String[] states = { "缺货", "需要进货", "不需要进货" };
    private Object[][] data = { { "《Java从入门到精通（第2版）》", states[0] }, { "《PHP从入门到精通（第2版）》", states[1] }, { "《Visual C++从入门到精通（第2版）》", states[1] },
            { "《Visual Basic从入门到精通（第2版）》", states[1] }, };
    
    @Override
    public int getColumnCount() {
        return 2;
    }
    
    @Override
    public int getRowCount() {
        return data.length;
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return data[rowIndex][columnIndex];
    }
    
    @Override
    public String getColumnName(int column) {
        String[] names = { "书名", "状态" };
        return names[column];
    }
    
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex == 1;
    }
    
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        data[rowIndex][columnIndex] = aValue;
    }
    
    public static String[] getStates() {
        return states;
    }
}
