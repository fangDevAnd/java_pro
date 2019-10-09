package com.cdd.util;
public class LocalTableModel extends javax.swing.table.DefaultTableModel {
	Class[] types = new Class[] { java.lang.Object.class,
			java.lang.String.class, java.lang.Object.class,java.lang.Object.class,java.lang.Object.class,java.lang.Object.class,java.lang.Object.class};
	boolean[] canEdit = new boolean[] { false, false, false,false,false,false,false};
	public LocalTableModel() {
		super(new Object[][] {}, new String[] { "编号", "姓名", "语文","数学","英语","历史","物理",});
	}
	public Class getColumnClass(int columnIndex) {
		return types[columnIndex];
	}
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return canEdit[columnIndex];
	}
}