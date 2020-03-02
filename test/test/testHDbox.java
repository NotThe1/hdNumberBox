package test;

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
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import hdNumberBox.HDNbox;
import hdNumberBox.HDNumberValueChangeEvent;
import hdNumberBox.HDNumberValueChangeListener;
import hdNumberBox.Hex64KSpinner;

public class testHDbox {

	private AdapterForTest adapterForTest = new AdapterForTest();
	private JFrame frame;
	private JTextArea txtLog;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					testHDbox window = new testHDbox();
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
		testTarget.setDecimalDisplay();
	}//appInit
	public testHDbox() {
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
		frame.setTitle("Test HDNbox   1.0");
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
		gbl_panelLeft.columnWidths = new int[]{200, 80, 80, 80, 0};
		gbl_panelLeft.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panelLeft.columnWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0};
		gbl_panelLeft.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panelLeft.setLayout(gbl_panelLeft);
		
		Component verticalStrut_1 = Box.createVerticalStrut(20);
		GridBagConstraints gbc_verticalStrut_1 = new GridBagConstraints();
		gbc_verticalStrut_1.insets = new Insets(0, 0, 5, 5);
		gbc_verticalStrut_1.gridx = 0;
		gbc_verticalStrut_1.gridy = 0;
		panelLeft.add(verticalStrut_1, gbc_verticalStrut_1);
		
		testTarget = new HDNbox();
		testTarget.addHDNumberValueChangedListener(adapterForTest);
		
		tbHexDecimal = new JToggleButton("Decimal");
		tbHexDecimal.setPreferredSize(new Dimension(120, 23));
		tbHexDecimal.setMaximumSize(new Dimension(0, 0));
		tbHexDecimal.setMinimumSize(new Dimension(120, 23));
		tbHexDecimal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				JToggleButton btn = (JToggleButton) actionEvent.getSource();
				if (btn.isSelected()) {
					btn.setText("Hex");
					testTarget.setHexDisplay();
				}else {
					btn.setText("Decimal");
					testTarget.setDecimalDisplay();
				}//
			}
		});
		GridBagConstraints gbc_tbHexDecimal = new GridBagConstraints();
		gbc_tbHexDecimal.fill = GridBagConstraints.HORIZONTAL;
		gbc_tbHexDecimal.insets = new Insets(0, 0, 5, 5);
		gbc_tbHexDecimal.gridx = 0;
		gbc_tbHexDecimal.gridy = 1;
		panelLeft.add(tbHexDecimal, gbc_tbHexDecimal);
		testTarget.setName("Target Component");
		GridBagConstraints gbc_testTarget = new GridBagConstraints();
		gbc_testTarget.insets = new Insets(0, 0, 5, 5);
		gbc_testTarget.fill = GridBagConstraints.HORIZONTAL;
		gbc_testTarget.gridx = 1;
		gbc_testTarget.gridy = 1;
		panelLeft.add(testTarget, gbc_testTarget);
		
		JLabel lblTestBox = new JLabel("Test Box");
		GridBagConstraints gbc_lblTestBox = new GridBagConstraints();
		gbc_lblTestBox.insets = new Insets(0, 0, 5, 5);
		gbc_lblTestBox.anchor = GridBagConstraints.EAST;
		gbc_lblTestBox.gridx = 2;
		gbc_lblTestBox.gridy = 1;
		panelLeft.add(lblTestBox, gbc_lblTestBox);
		
		Component verticalStrut_2 = Box.createVerticalStrut(20);
		GridBagConstraints gbc_verticalStrut_2 = new GridBagConstraints();
		gbc_verticalStrut_2.insets = new Insets(0, 0, 5, 5);
		gbc_verticalStrut_2.gridx = 0;
		gbc_verticalStrut_2.gridy = 2;
		panelLeft.add(verticalStrut_2, gbc_verticalStrut_2);
		
		JButton btnSetRange = new JButton("Set Range");
		btnSetRange.setPreferredSize(new Dimension(120, 23));
		btnSetRange.setMaximumSize(new Dimension(0, 0));
		btnSetRange.setMinimumSize(new Dimension(120, 23));
		btnSetRange.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnSetRange.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				testTarget.setRange((int)spinMin.getValue(), (int)spinMax.getValue());
				testTarget.setValueQuiet((int)spinCurrent.getValue());
				log(String.format("Min     Value: Hex: %1$4X, Decimal: %1$d", testTarget.getMinValue()));
				log(String.format("Max     Value: Hex: %1$4X, Decimal: %1$d", testTarget.getMaxValue()));
				log(String.format("Current Value: Hex: %1$4X, Decimal: %1$d", testTarget.getValue()));
				log(NL);
			}//actionPerformed
		});
		
		JLabel lblMin = new JLabel("Min");
		lblMin.setHorizontalAlignment(SwingConstants.CENTER);
		lblMin.setPreferredSize(new Dimension(80, 23));
		lblMin.setMinimumSize(new Dimension(80, 23));
		lblMin.setMaximumSize(new Dimension(0, 0));
		GridBagConstraints gbc_lblMin = new GridBagConstraints();
		gbc_lblMin.anchor = GridBagConstraints.EAST;
		gbc_lblMin.insets = new Insets(0, 0, 5, 5);
		gbc_lblMin.gridx = 1;
		gbc_lblMin.gridy = 3;
		panelLeft.add(lblMin, gbc_lblMin);
		
		JLabel lblMax = new JLabel("Max");
		lblMax.setHorizontalAlignment(SwingConstants.CENTER);
		lblMax.setPreferredSize(new Dimension(80, 23));
		lblMax.setMinimumSize(new Dimension(80, 23));
		lblMax.setMaximumSize(new Dimension(0, 0));
		GridBagConstraints gbc_lblMax = new GridBagConstraints();
		gbc_lblMax.anchor = GridBagConstraints.EAST;
		gbc_lblMax.insets = new Insets(0, 0, 5, 5);
		gbc_lblMax.gridx = 2;
		gbc_lblMax.gridy = 3;
		panelLeft.add(lblMax, gbc_lblMax);
		
		JLabel lblCurrent = new JLabel("Current");
		lblCurrent.setHorizontalAlignment(SwingConstants.CENTER);
		lblCurrent.setPreferredSize(new Dimension(80, 23));
		lblCurrent.setMinimumSize(new Dimension(80, 23));
		lblCurrent.setMaximumSize(new Dimension(0, 0));
		GridBagConstraints gbc_lblCurrent = new GridBagConstraints();
		gbc_lblCurrent.anchor = GridBagConstraints.EAST;
		gbc_lblCurrent.insets = new Insets(0, 0, 5, 5);
		gbc_lblCurrent.gridx = 3;
		gbc_lblCurrent.gridy = 3;
		panelLeft.add(lblCurrent, gbc_lblCurrent);
		GridBagConstraints gbc_btnSetRange = new GridBagConstraints();
		gbc_btnSetRange.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnSetRange.insets = new Insets(0, 0, 5, 5);
		gbc_btnSetRange.gridx = 0;
		gbc_btnSetRange.gridy = 4;
		panelLeft.add(btnSetRange, gbc_btnSetRange);
		
		spinMin = new JSpinner();
		spinMin.setMinimumSize(new Dimension(80, 23));
		spinMin.setMaximumSize(new Dimension(0, 0));
		spinMin.setPreferredSize(new Dimension(80, 23));
		GridBagConstraints gbc_spinMin = new GridBagConstraints();
		gbc_spinMin.insets = new Insets(0, 0, 5, 5);
		gbc_spinMin.gridx = 1;
		gbc_spinMin.gridy = 4;
		panelLeft.add(spinMin, gbc_spinMin);
		
		spinMax = new JSpinner();
		spinMax.setMinimumSize(new Dimension(80, 23));
		spinMax.setMaximumSize(new Dimension(0, 0));
		spinMax.setPreferredSize(new Dimension(80, 23));
		GridBagConstraints gbc_spinMax = new GridBagConstraints();
		gbc_spinMax.insets = new Insets(0, 0, 5, 5);
		gbc_spinMax.gridx = 2;
		gbc_spinMax.gridy = 4;
		panelLeft.add(spinMax, gbc_spinMax);
		
		spinCurrent = new JSpinner();
		spinCurrent.setMinimumSize(new Dimension(80, 23));
		spinCurrent.setMaximumSize(new Dimension(0, 0));
		spinCurrent.setPreferredSize(new Dimension(80, 23));
		GridBagConstraints gbc_spinCurrent = new GridBagConstraints();
		gbc_spinCurrent.insets = new Insets(0, 0, 5, 5);
		gbc_spinCurrent.gridx = 3;
		gbc_spinCurrent.gridy = 4;
		panelLeft.add(spinCurrent, gbc_spinCurrent);
		
		Component verticalStrut = Box.createVerticalStrut(20);
		GridBagConstraints gbc_verticalStrut = new GridBagConstraints();
		gbc_verticalStrut.insets = new Insets(0, 0, 5, 5);
		gbc_verticalStrut.gridx = 0;
		gbc_verticalStrut.gridy = 5;
		panelLeft.add(verticalStrut, gbc_verticalStrut);
		
		JButton btnSetValue = new JButton("Set Value");
		btnSetValue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				testTarget.setValue((int)spinValue.getValue());
			}
		});
		btnSetValue.setPreferredSize(new Dimension(120, 23));
		btnSetValue.setMaximumSize(new Dimension(0, 0));
		btnSetValue.setMinimumSize(new Dimension(120, 23));
		GridBagConstraints gbc_btnSetValue = new GridBagConstraints();
		gbc_btnSetValue.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnSetValue.insets = new Insets(0, 0, 5, 5);
		gbc_btnSetValue.gridx = 0;
		gbc_btnSetValue.gridy = 6;
		panelLeft.add(btnSetValue, gbc_btnSetValue);
		
		JLabel lblValue = new JLabel("Value");
		lblValue.setPreferredSize(new Dimension(80, 23));
		lblValue.setMinimumSize(new Dimension(80, 23));
		lblValue.setMaximumSize(new Dimension(0, 0));
		lblValue.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblValue = new GridBagConstraints();
		gbc_lblValue.insets = new Insets(0, 0, 5, 5);
		gbc_lblValue.gridx = 2;
		gbc_lblValue.gridy = 6;
		panelLeft.add(lblValue, gbc_lblValue);
		
		Component verticalStrut_3 = Box.createVerticalStrut(20);
		GridBagConstraints gbc_verticalStrut_3 = new GridBagConstraints();
		gbc_verticalStrut_3.insets = new Insets(0, 0, 5, 5);
		gbc_verticalStrut_3.gridx = 0;
		gbc_verticalStrut_3.gridy = 7;
		panelLeft.add(verticalStrut_3, gbc_verticalStrut_3);
		
		JButton btnSetValueQuiet = new JButton("Set Value Quiet");
		btnSetValueQuiet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				testTarget.setValueQuiet((int)spinValue.getValue());
			}
		});
		
		spinValue = new JSpinner();
		spinValue.setPreferredSize(new Dimension(80, 23));
		spinValue.setMinimumSize(new Dimension(80, 23));
		spinValue.setMaximumSize(new Dimension(0, 0));
		GridBagConstraints gbc_spinValue = new GridBagConstraints();
		gbc_spinValue.insets = new Insets(0, 0, 5, 5);
		gbc_spinValue.gridx = 2;
		gbc_spinValue.gridy = 7;
		panelLeft.add(spinValue, gbc_spinValue);
		btnSetValueQuiet.setMaximumSize(new Dimension(0, 0));
		btnSetValueQuiet.setPreferredSize(new Dimension(120, 23));
		btnSetValueQuiet.setMinimumSize(new Dimension(120, 23));
		GridBagConstraints gbc_btnSetValueQuiet = new GridBagConstraints();
		gbc_btnSetValueQuiet.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnSetValueQuiet.insets = new Insets(0, 0, 5, 5);
		gbc_btnSetValueQuiet.gridx = 0;
		gbc_btnSetValueQuiet.gridy = 8;
		panelLeft.add(btnSetValueQuiet, gbc_btnSetValueQuiet);
		
		Component verticalStrut_4 = Box.createVerticalStrut(20);
		GridBagConstraints gbc_verticalStrut_4 = new GridBagConstraints();
		gbc_verticalStrut_4.insets = new Insets(0, 0, 5, 5);
		gbc_verticalStrut_4.gridx = 0;
		gbc_verticalStrut_4.gridy = 9;
		panelLeft.add(verticalStrut_4, gbc_verticalStrut_4);
		
		JButton btnNewButton = new JButton("Hex format \"%04X\"");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				testTarget.setHexDisplay("%04X");
				tbHexDecimal.setSelected(true);
				tbHexDecimal.setText("Hex");
			}
		});
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton.gridx = 0;
		gbc_btnNewButton.gridy = 10;
		panelLeft.add(btnNewButton, gbc_btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Reset Format");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				testTarget.resetDisplayFormat();
			}
		});
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnNewButton_1.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_1.gridx = 0;
		gbc_btnNewButton_1.gridy = 11;
		panelLeft.add(btnNewButton_1, gbc_btnNewButton_1);
		splitPane.setDividerLocation(450);
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
	private JSpinner spinMin;
	private JSpinner spinMax;
	private JSpinner spinCurrent;
	private HDNbox testTarget;
	private JSpinner spinValue;
	private JToggleButton tbHexDecimal;
}//class testHDformattedTextField
