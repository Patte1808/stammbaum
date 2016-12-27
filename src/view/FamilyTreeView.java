package view;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JToolBar;
import javax.swing.JPopupMenu;

import java.awt.Component;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.JEditorPane;
import java.awt.Canvas;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;


public class FamilyTreeView extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8573030097625277881L;
	private JTable table;
	
	public FamilyTreeView() {
		super("Family Tree");
		getContentPane().setBackground(Color.WHITE);
		getContentPane().setLayout(null);
		
		final JFrame f = new JFrame("family tree");
		f.setSize(500, 300);
		f.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		
		JMenuBar menuBar_1 = new JMenuBar();
		setJMenuBar(menuBar_1);
		
		JMenu mnPerson = new JMenu("edit");
		menuBar_1.add(mnPerson);
		
		JMenuItem mntmCreatePerson = new JMenuItem("create person");
		mntmCreatePerson.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new CreatePersonView().setVisible(true);
			}
		});
		
		mnPerson.add(mntmCreatePerson);
		
		JMenuItem mntmEditPerson = new JMenuItem("edit person");
		mnPerson.add(mntmEditPerson);
		
		JMenuItem mntmAddRelation = new JMenuItem("add relation");
		mnPerson.add(mntmAddRelation);
		
		JMenu mnNewMenu = new JMenu("family relations");
		menuBar_1.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("siblings");
		mnNewMenu.add(mntmNewMenuItem);
		
		JMenu mnAunts = new JMenu("aunts");
		mnNewMenu.add(mnAunts);
		
		JMenuItem mntmOnFemaleSide = new JMenuItem("on female side");
		mnAunts.add(mntmOnFemaleSide);
		
		JMenuItem mntmOnMaleSide = new JMenuItem("on male side");
		mnAunts.add(mntmOnMaleSide);
		
		JMenu mnUncles = new JMenu("uncles");
		mnNewMenu.add(mnUncles);
		
		JMenuItem mntmOnFemaleSide_1 = new JMenuItem("on female side");
		mnUncles.add(mntmOnFemaleSide_1);
		
		JMenuItem mntmOnMaleSide_1 = new JMenuItem("on male side");
		mnUncles.add(mntmOnMaleSide_1);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("cousins");
		mnNewMenu.add(mntmNewMenuItem_1);
		
		JMenuItem mntmGrandchildren = new JMenuItem("grandchildren");
		mnNewMenu.add(mntmGrandchildren);
		
		JMenuItem mntmGrandparents = new JMenuItem("grandparents");
		mnNewMenu.add(mntmGrandparents);		
		
		
		//f.add(menuBar_1);
		menuBar_1.setBounds(0, 0, 500, 30);

		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{"name", "age", "gender", "parent", "spouse", "children"},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
			},
			new String[] {
				"name", "age", "gender", "parent", "spouse", "children"
			}
		));
		table.setCellSelectionEnabled(true);
		table.setBounds(0, 100, 476, 100);
		f.add(table);
		
		
		f.setVisible( true );
	}
	
	public static void main(String[] args) {
		new FamilyTreeView();
	}
}
