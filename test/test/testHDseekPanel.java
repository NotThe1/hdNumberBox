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
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JToggleButton;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import hdNumberBox.HDNseek;
import hdNumberBox.HDNumberValueChangeEvent;
import hdNumberBox.HDNumberValueChangeListener;
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
		frame.setBounds(100, 100, 904, 652);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Test HDseek        Rev 1.0");
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
		gbl_panelLeft.columnWidths = new int[] {180, 80};
		gbl_panelLeft.rowHeights = new int[] {0, 0, 0};
		gbl_panelLeft.columnWeights = new double[]{1.0, 0.0};
		gbl_panelLeft.rowWeights = new double[]{0.0, 0.0, 1.0};
		panelLeft.setLayout(gbl_panelLeft);
		
		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 5, 5);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		panelLeft.add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0};
		gbl_panel.columnWeights = new double[]{1.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		nseek = new HDNseek();
		nseek.addHDNumberValueChangedListener(adapterForTest);
		GridBagConstraints gbc_nseek = new GridBagConstraints();
		gbc_nseek.insets = new Insets(0, 0, 0, 5);
		gbc_nseek.gridx = 0;
		gbc_nseek.gridy = 0;
		panel.add(nseek, gbc_nseek);
		nseek.setPreferredSize(new Dimension(277, 23));
		nseek.setMinimumSize(new Dimension(277, 23));
		GridBagLayout gbl_nseek = new GridBagLayout();
		gbl_nseek.columnWidths = new int[]{0};
		gbl_nseek.rowHeights = new int[]{0};
		gbl_nseek.columnWeights = new double[]{Double.MIN_VALUE};
		gbl_nseek.rowWeights = new double[]{Double.MIN_VALUE};
		nseek.setLayout(gbl_nseek);
		
		JToggleButton tglbtnNewToggleButton = new JToggleButton("Decimal");
		tglbtnNewToggleButton.setPreferredSize(new Dimension(80, 23));
		GridBagConstraints gbc_tglbtnNewToggleButton = new GridBagConstraints();
		gbc_tglbtnNewToggleButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_tglbtnNewToggleButton.gridx = 2;
		gbc_tglbtnNewToggleButton.gridy = 0;
		panel.add(tglbtnNewToggleButton, gbc_tglbtnNewToggleButton);
		tglbtnNewToggleButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				JToggleButton btn = (JToggleButton) actionEvent.getSource();
				if (btn.isSelected()) {
					btn.setText("Hex");
					nseek.setHexDisplay();
				}else {
					btn.setText("Decimal");
					nseek.setDecimalDisplay();
				}//
			}
		});
		
		Component verticalStrut_2 = Box.createVerticalStrut(20);
		GridBagConstraints gbc_verticalStrut_2 = new GridBagConstraints();
		gbc_verticalStrut_2.insets = new Insets(0, 0, 5, 5);
		gbc_verticalStrut_2.gridx = 0;
		gbc_verticalStrut_2.gridy = 1;
		panelLeft.add(verticalStrut_2, gbc_verticalStrut_2);
		
		JPanel panel_1 = new JPanel();
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.insets = new Insets(0, 0, 0, 5);
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 0;
		gbc_panel_1.gridy = 2;
		panelLeft.add(panel_1, gbc_panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel_1.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel_1.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);
		
		JLabel lblMin = new JLabel("Min");
		GridBagConstraints gbc_lblMin = new GridBagConstraints();
		gbc_lblMin.insets = new Insets(0, 0, 5, 5);
		gbc_lblMin.gridx = 2;
		gbc_lblMin.gridy = 0;
		panel_1.add(lblMin, gbc_lblMin);
		
		JLabel lblMax = new JLabel("Max");
		GridBagConstraints gbc_lblMax = new GridBagConstraints();
		gbc_lblMax.insets = new Insets(0, 0, 5, 5);
		gbc_lblMax.gridx = 4;
		gbc_lblMax.gridy = 0;
		panel_1.add(lblMax, gbc_lblMax);
		
		JLabel lblValue = new JLabel("Value");
		GridBagConstraints gbc_lblValue = new GridBagConstraints();
		gbc_lblValue.insets = new Insets(0, 0, 5, 0);
		gbc_lblValue.gridx = 6;
		gbc_lblValue.gridy = 0;
		panel_1.add(lblValue, gbc_lblValue);
		
		JButton btnNewButton = new JButton("Set Range");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				nseek.setRange((int)spinMin.getValue(),(int) spinMax.getValue());
				nseek.setValueQuiet((int)spinCurrent.getValue());
				log(String.format("Min     Value: Hex: %1$4X, Decimal: %1$d", nseek.getMinValue()));
				log(String.format("Max     Value: Hex: %1$4X, Decimal: %1$d", nseek.getMaxValue()));
				log(String.format("Current Value: Hex: %1$4X, Decimal: %1$d", nseek.getValue()));
				log(NL);
			}
		});
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton.gridx = 0;
		gbc_btnNewButton.gridy = 1;
		panel_1.add(btnNewButton, gbc_btnNewButton);
		
		spinMin = new JSpinner();
		spinMin.setPreferredSize(new Dimension(60, 20));
		spinMin.setMaximumSize(new Dimension(0, 0));
		spinMin.setMinimumSize(new Dimension(60, 20));
		GridBagConstraints gbc_spinMin = new GridBagConstraints();
		gbc_spinMin.insets = new Insets(0, 0, 5, 5);
		gbc_spinMin.gridx = 2;
		gbc_spinMin.gridy = 1;
		panel_1.add(spinMin, gbc_spinMin);
		
		spinMax = new JSpinner();
		spinMax.setPreferredSize(new Dimension(60, 20));
		spinMax.setMinimumSize(new Dimension(60, 20));
		spinMax.setMaximumSize(new Dimension(0, 0));
		GridBagConstraints gbc_spinMax = new GridBagConstraints();
		gbc_spinMax.insets = new Insets(0, 0, 5, 5);
		gbc_spinMax.gridx = 4;
		gbc_spinMax.gridy = 1;
		panel_1.add(spinMax, gbc_spinMax);
		
		spinCurrent = new JSpinner();
		spinCurrent.setPreferredSize(new Dimension(60, 20));
		spinCurrent.setMinimumSize(new Dimension(60, 20));
		spinCurrent.setMaximumSize(new Dimension(0, 0));
		GridBagConstraints gbc_spinCurrent = new GridBagConstraints();
		gbc_spinCurrent.insets = new Insets(0, 0, 5, 0);
		gbc_spinCurrent.gridx = 6;
		gbc_spinCurrent.gridy = 1;
		panel_1.add(spinCurrent, gbc_spinCurrent);
		
		Component verticalStrut_3 = Box.createVerticalStrut(20);
		GridBagConstraints gbc_verticalStrut_3 = new GridBagConstraints();
		gbc_verticalStrut_3.insets = new Insets(0, 0, 5, 5);
		gbc_verticalStrut_3.gridx = 0;
		gbc_verticalStrut_3.gridy = 2;
		panel_1.add(verticalStrut_3, gbc_verticalStrut_3);
		
		JButton btnNewButton_1 = new JButton("New Value");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nseek.setValue((int) spinNewValue.getValue());
			}
		});
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnNewButton_1.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_1.gridx = 0;
		gbc_btnNewButton_1.gridy = 3;
		panel_1.add(btnNewButton_1, gbc_btnNewButton_1);
		
		spinNewValue = new JSpinner();
		spinNewValue.setMinimumSize(new Dimension(60, 20));
		spinNewValue.setMaximumSize(new Dimension(0, 0));
		spinNewValue.setPreferredSize(new Dimension(60, 20));
		GridBagConstraints gbc_spinNewValue = new GridBagConstraints();
		gbc_spinNewValue.insets = new Insets(0, 0, 5, 5);
		gbc_spinNewValue.gridx = 2;
		gbc_spinNewValue.gridy = 4;
		panel_1.add(spinNewValue, gbc_spinNewValue);
		
		JButton btnNewValueQuiet = new JButton("New Value Quiet");
		btnNewValueQuiet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nseek.setValueQuiet((int) spinNewValue.getValue());
			}
		});
		GridBagConstraints gbc_btnNewValueQuiet = new GridBagConstraints();
		gbc_btnNewValueQuiet.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewValueQuiet.gridx = 0;
		gbc_btnNewValueQuiet.gridy = 5;
		panel_1.add(btnNewValueQuiet, gbc_btnNewValueQuiet);
		
		Hex64KSpinner hex64KSpinner = new Hex64KSpinner();
		GridBagConstraints gbc_hex64KSpinner = new GridBagConstraints();
		gbc_hex64KSpinner.insets = new Insets(0, 0, 0, 5);
		gbc_hex64KSpinner.gridx = 0;
		gbc_hex64KSpinner.gridy = 8;
		panel_1.add(hex64KSpinner, gbc_hex64KSpinner);
		
		Component verticalStrut_1 = Box.createVerticalStrut(20);
		GridBagConstraints gbc_verticalStrut_1 = new GridBagConstraints();
		gbc_verticalStrut_1.gridx = 1;
		gbc_verticalStrut_1.gridy = 2;
		panelLeft.add(verticalStrut_1, gbc_verticalStrut_1);
		splitPane.setDividerLocation(600);
		


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
//	private HDformattedTextField testTarget;
	private HDNseek nseek;
	private JSpinner spinMin;
	private JSpinner spinMax;
	private JSpinner spinCurrent;
	private JSpinner spinNewValue;
	

}//class testHDseekPanel
