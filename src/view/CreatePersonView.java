package view;

import model.*;
import model.Person.Gender;

import java.awt.Color;
import java.awt.GridLayout;
import java.text.NumberFormat;

import javax.swing.JCheckBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.SwingConstants;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CreatePersonView extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3350520130611926452L;
	private JTextField txtName;
	private JTextField txtAge;
	private JRadioButton rdbtnFemale;
	private JRadioButton rdbtnMale;
	private JLabel lblErrorMessage;
	private JLabel lblAge;
	
	
	
	public CreatePersonView() {
		super("Person");
		final JFrame f = new JFrame("create person");
		f.getContentPane().setBackground(Color.WHITE);
		f.setSize(500, 200);
		f.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		f.getContentPane().setLayout(null);
		
		JLabel lbName = new JLabel("name: ");;
		lbName.setBounds(10, 10, 100, 15);
		f.getContentPane().add(lbName);
		
		txtName = new JTextField();
		txtName.setToolTipText("name");
		txtName.setBounds(136, 6, 334, 22);
		f.getContentPane().add(txtName);
		txtName.setColumns(10);
		
		
		JLabel lblAge = new JLabel("age:");
		lblAge.setBounds(10, 44, 100, 15);
		f.getContentPane().add(lblAge);
		
		//txtAge = new JTextField();
		txtAge = new JFormattedTextField(NumberFormat.getNumberInstance());
		txtAge.setToolTipText("age");
		txtAge.setBounds(136, 41, 116, 22);
		f.getContentPane().add(txtAge);
		txtAge.setColumns(10);
		
		JLabel lblGender = new JLabel("gender:");
		lblGender.setBounds(10, 76, 100, 15);
		f.getContentPane().add(lblGender);
		
		rdbtnMale = new JRadioButton("male");
		rdbtnMale.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(rdbtnFemale.isSelected()) {
					rdbtnFemale.setSelected(false);
					rdbtnMale.setSelected(true);
				} else {
					rdbtnMale.setSelected(true);
				}
			}
		});
		rdbtnMale.setBackground(Color.WHITE);
		rdbtnMale.setBounds(180, 72, 72, 25);
		f.getContentPane().add(rdbtnMale);
		rdbtnMale.setSelected(true);
		
		rdbtnFemale = new JRadioButton("female");
		rdbtnFemale.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(rdbtnMale.isSelected()) {
					rdbtnMale.setSelected(false);
					rdbtnFemale.setSelected(true);
				} else {
					rdbtnFemale.setSelected(true);
				}
			}
		});
		rdbtnFemale.setBackground(Color.WHITE);
		rdbtnFemale.setBounds(339, 72, 72, 25);
		f.getContentPane().add(rdbtnFemale); 
		
		
		JButton btnCreate = new JButton("create");
		btnCreate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if((txtName.getText().isEmpty())||(txtAge.getText().isEmpty())) {
					lblErrorMessage.setText("Check all empty fields.");
				}
				if((!txtName.getText().isEmpty())&&(!txtAge.getText().isEmpty())) {
					lblErrorMessage.setText(" ");
					Person.Gender personGender;
					if(rdbtnFemale.isSelected()) {
						personGender = Gender.Female;
					} else personGender = Gender.Male;
					Person createPerson = new Person(txtName.getText(), Integer.parseInt(txtAge.getText()), personGender);
					//add Person to family tree
					f.dispose();
				}
			}
		});
		
		
		btnCreate.setBounds(373, 115, 97, 25);
		f.getContentPane().add(btnCreate);
		
		lblErrorMessage = new JLabel("");
		lblErrorMessage.setHorizontalAlignment(SwingConstants.RIGHT);
		lblErrorMessage.setForeground(Color.RED);
		lblErrorMessage.setBounds(90, 115, 278, 25);
		f.getContentPane().add(lblErrorMessage);
		
		
		f.setVisible( true );
	}
	
	public static void main(String[] args) {
		new CreatePersonView();
	}
}
