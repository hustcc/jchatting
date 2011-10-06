package test;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GraphicsEnvironment;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class GetSystemFont extends JFrame {

	private JPanel jContentPane = null;
	private JScrollPane jScrollPane = null;
	private JTable jTable = null;
	/**
	 * This is the default constructor
	 */
	public GetSystemFont() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(300, 200);
		this.setContentPane(getJContentPane());
		this.setTitle("获取系统字体信息");

	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(new BorderLayout());
			jContentPane.add(getJScrollPane(), java.awt.BorderLayout.CENTER);
		}
		return jContentPane;
	}

	/**
	 * This method initializes jScrollPane
	 * 
	 * @return javax.swing.JScrollPane
	 */
	private JScrollPane getJScrollPane() {
		if (jScrollPane == null) {
			jScrollPane = new JScrollPane();
			jScrollPane.setViewportView(getJTable());
		}
		return jScrollPane;
	}

	/**
	 * This method initializes jTable
	 * 
	 * @return javax.swing.JTable
	 */
	private JTable getJTable() {
		if (jTable == null) {
			Font[] fonts;
			Object[][] data = null;
			Object[] ColName = {"index", "FamilyName", "FontName", "Name"};
			fonts = GraphicsEnvironment.getLocalGraphicsEnvironment()
					.getAllFonts();
			data = new Object[fonts.length][4];
			for (int i = 0; i < fonts.length; i++) {
				data[i][0] = i + 1;
				data[i][1] = fonts[i].getFamily();
				data[i][2] = fonts[i].getFontName();
				data[i][3] = fonts[i].getName();
			}
			jTable = new JTable(data, ColName);
			this.jTable.setDoubleBuffered(true);
			this.jTable.setColumnSelectionAllowed(false);
			this.jTable.setRowSelectionAllowed(true);
		}
		return jTable;
	}

	public static void main(String[] args) {
		new GetSystemFont().setVisible(true);
	}
}