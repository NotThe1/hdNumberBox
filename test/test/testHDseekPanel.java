package test;

//public class testHDseekPanel {
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JToggleButton;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import hdNumberBox.HDNumberBox;
import hdNumberBox.HDNumberValueChangeEvent;
import hdNumberBox.HDNumberValueChangeListener;
import hdNumberBox.HDformattedTextField;
import hdNumberBox.HDseekPanel;
import hdNumberBox.Hex64KSpinner;

public class testHDseekPanel {

	private AdapterForTest adapterForTest = new AdapterForTest();
	private JFrame frame;
	private JTextArea txtLog;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					testHDseekPanel window = new testHDseekPanel();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}//main
	
	private void log(String message) {
		txtLog.append(message + NL);
	}//log

	/**
	 * Create the application.
	 */
	private void appInit() {
//		testTarget.setDecimalDisplay();
	}//appInit
	public testHDseekPanel() {
		initialize();
		appInit();
	}//Constructor

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 768, 652);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Test HDseekPanel   1.0");
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		frame.getContentPane().setLayout(gridBagLayout);
		
		JSplitPane splitPane = new JSplitPane();
		GridBagConstraints gbc_splitPane = new GridBagConstraints();
		gbc_splitPane.fill = GridBagConstraints.BOTH;
		gbc_splitPane.gridx = 0;
		gbc_splitPane.gridy = 0;
		frame.getContentPane().add(splitPane, gbc_splitPane);
		
		JScrollPane scrollPane = new JScrollPane();
		splitPane.setRightComponent(scrollPane);
		
		txtLog = new JTextArea();
		txtLog.setFont(new Font("Courier New", Font.PLAIN, 13));
		scrollPane.setViewportView(txtLog);
		
		JPanel panelLeft = new JPanel();
		panelLeft.setMinimumSize(new Dimension(80, 23));
		panelLeft.setPreferredSize(new Dimension(80, 23));
		panelLeft.setMaximumSize(new Dimension(0, 0));
		splitPane.setLeftComponent(panelLeft);
		GridBagLayout gbl_panelLeft = new GridBagLayout();
		gbl_panelLeft.columnWidths = new int[]{80, 80, 80, 80, 80};
		gbl_panelLeft.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panelLeft.columnWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0};
		gbl_panelLeft.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panelLeft.setLayout(gbl_panelLeft);
		
		Component verticalStrut_1 = Box.createVerticalStrut(20);
		GridBagConstraints gbc_verticalStrut_1 = new GridBagConstraints();
		gbc_verticalStrut_1.insets = new Insets(0, 0, 5, 5);
		gbc_verticalStrut_1.gridx = 1;
		gbc_verticalStrut_1.gridy = 0;
		panelLeft.add(verticalStrut_1, gbc_verticalStrut_1);
		
		JToggleButton tglbtnNewToggleButton = new JToggleButton("Decimal");
		tglbtnNewToggleButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
//				JToggleButton btn = (JToggleButton) actionEvent.getSource();
//				if (btn.isSelected()) {
//					btn.setText("Hex");
//					seekHD.setHexDisplay();
//				}else {
//					btn.setText("Decimal");
//					seekHD.setDecimalDisplay();
//				}//
			}
		});
		GridBagConstraints gbc_tglbtnNewToggleButton = new GridBagConstraints();
		gbc_tglbtnNewToggleButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_tglbtnNewToggleButton.insets = new Insets(0, 0, 5, 5);
		gbc_tglbtnNewToggleButton.gridx = 0;
		gbc_tglbtnNewToggleButton.gridy = 1;
		panelLeft.add(tglbtnNewToggleButton, gbc_tglbtnNewToggleButton);
		
		seekHD = new HDseekPanel();
		seekHD.setMaximumSize(new Dimension(265, 26));
		GridBagConstraints gbc_seekHD = new GridBagConstraints();
		gbc_seekHD.gridwidth = 4;
		gbc_seekHD.insets = new Insets(0, 0, 5, 0);
		gbc_seekHD.fill = GridBagConstraints.VERTICAL;
		gbc_seekHD.gridx = 1;
		gbc_seekHD.gridy = 1;
		panelLeft.add(seekHD, gbc_seekHD);
		GridBagLayout gbl_seekHD = new GridBagLayout();
		gbl_seekHD.columnWidths = new int[]{0};
		gbl_seekHD.rowHeights = new int[]{0};
		gbl_seekHD.columnWeights = new double[]{Double.MIN_VALUE};
		gbl_seekHD.rowWeights = new double[]{Double.MIN_VALUE};
		seekHD.setLayout(gbl_seekHD);
		
		Component verticalStrut = Box.createVerticalStrut(20);
		GridBagConstraints gbc_verticalStrut = new GridBagConstraints();
		gbc_verticalStrut.insets = new Insets(0, 0, 5, 5);
		gbc_verticalStrut.gridx = 0;
		gbc_verticalStrut.gridy = 2;
		panelLeft.add(verticalStrut, gbc_verticalStrut);
		
		HDNumberBox numberBox_1 = new HDNumberBox();
		GridBagConstraints gbc_numberBox_1 = new GridBagConstraints();
		gbc_numberBox_1.insets = new Insets(0, 0, 5, 5);
		gbc_numberBox_1.fill = GridBagConstraints.BOTH;
		gbc_numberBox_1.gridx = 0;
		gbc_numberBox_1.gridy = 3;
		panelLeft.add(numberBox_1, gbc_numberBox_1);
		
		HDNumberBox numberBox_2 = new HDNumberBox();
		GridBagConstraints gbc_numberBox_2 = new GridBagConstraints();
		gbc_numberBox_2.insets = new Insets(0, 0, 5, 5);
		gbc_numberBox_2.fill = GridBagConstraints.BOTH;
		gbc_numberBox_2.gridx = 0;
		gbc_numberBox_2.gridy = 4;
		panelLeft.add(numberBox_2, gbc_numberBox_2);
		
		numberBox = new HDNumberBox();
		GridBagConstraints gbc_numberBox = new GridBagConstraints();
		gbc_numberBox.gridwidth = 5;
		gbc_numberBox.insets = new Insets(0, 0, 5, 0);
		gbc_numberBox.fill = GridBagConstraints.BOTH;
		gbc_numberBox.gridx = 0;
		gbc_numberBox.gridy = 5;
		panelLeft.add(numberBox, gbc_numberBox);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				numberBox.btnFirst.setVisible(true);
				numberBox.btnPrior.setVisible(true);
				numberBox.btnNext.setVisible(true);
				numberBox.btnLast.setVisible(true);
			}
		});
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton.gridx = 0;
		gbc_btnNewButton.gridy = 7;
		panelLeft.add(btnNewButton, gbc_btnNewButton);
		
		JButton btnNewButton_1 = new JButton("New button");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				numberBox.btnFirst.setVisible(false);
				numberBox.btnPrior.setVisible(false);
				numberBox.btnNext.setVisible(false);
				numberBox.btnLast.setVisible(false);
			}
		});
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_1.gridx = 0;
		gbc_btnNewButton_1.gridy = 8;
		panelLeft.add(btnNewButton_1, gbc_btnNewButton_1);
		
		HDNumberBox numberBox_3 = new HDNumberBox();
		GridBagConstraints gbc_numberBox_3 = new GridBagConstraints();
		gbc_numberBox_3.insets = new Insets(0, 0, 0, 5);
		gbc_numberBox_3.fill = GridBagConstraints.BOTH;
		gbc_numberBox_3.gridx = 0;
		gbc_numberBox_3.gridy = 9;
		panelLeft.add(numberBox_3, gbc_numberBox_3);
		splitPane.setDividerLocation(400);
		


	}//initialize
	
//---------------------------------------------------------------------------------------//	
	class AdapterForTest implements HDNumberValueChangeListener, ChangeListener {
		/* ------------ HDNumberValueChangeListener ----------------- */
		@Override
		public void valueChanged(HDNumberValueChangeEvent changeEvent) {

			String name = ((Component) changeEvent.getSource()).getName();
			
			log(name);
			log(String.format("Prior Value: Hex: %1$4X, Decimal: %1$d", changeEvent.getOldValue()));
			log(String.format("New   Value: Hex: %1$4X, Decimal: %1$d", changeEvent.getNewValue()));
			log(NL);
		}// valueChanged

		@Override
		public void stateChanged(ChangeEvent changeEvent) {
			changeEvent.getSource();
			String name = ((Hex64KSpinner) changeEvent.getSource()).getName();
			int value = (int) ((Hex64KSpinner) changeEvent.getSource()).getValue();
			System.out.printf("%s %d%n", name, value);

		}//stateChanged

	}// class AdapterHDNumberBoxTest
	//---------------------------------------------------------------------------------------//	

	private static final String NL = System.lineSeparator();
	private HDformattedTextField testTarget;
	private HDseekPanel seekHD;
	private HDNumberBox numberBox;
	

}//class testHDseekPanel
