package testHDNumberBox;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import hdNumberBox.HDNumberBox;
import hdNumberBox.HDNumberValueChangeEvent;
import hdNumberBox.HDNumberValueChangeListener;
import hdNumberBox.HDSeekPanel;
import hdNumberBox.HDformattedTextField;
import hdNumberBox.Hex64KSpinner;

public class TestHDNumberBox {
	HDformattedTextField box;
	private AdapterHDNumberBoxTest adapter = new AdapterHDNumberBoxTest();

	private JFrame frame;
	private JToggleButton tbHexDecimal;
	private JButton btnNewButton_1;
	private JButton btnNewButton_2;
	private JButton btnFmtx;
	private JLabel lblSource;
	private JLabel lblOldValue;
	private JLabel lblNewValue;
	private HDSeekPanel seekPanel;
	private Hex64KSpinner hex64KSpinner;
	private Hex64KSpinner hex64KSpinner_1;
	private HDNumberBox numberBox;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TestHDNumberBox window = new TestHDNumberBox();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	private void clearLabels() {
		
			lblSource.setText("N/U");
			lblOldValue.setText("N/U");
			lblNewValue.setText("N/U");
		
	}//fillLabels

	/**
	 * Create the application.
	 */
	private void appInit() {
		box.setValueQuiet(17);
		tbHexDecimal.setText("Decimal");
	}// appInit

	public TestHDNumberBox() {
		initialize();
		appInit();
	}// Constructor

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 433);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 1.0, 0.0, 1.0, 1.0, 1.0, 0.0, 1.0, 1.0, 1.0, Double.MIN_VALUE };
		frame.getContentPane().setLayout(gridBagLayout);

		tbHexDecimal = new JToggleButton("Decimal");
		tbHexDecimal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearLabels();
				if (tbHexDecimal.getText().equals("Decimal")) {
					tbHexDecimal.setText("Hex");
					box.setHexDisplay();
				} else {
					tbHexDecimal.setText("Decimal");
					box.setDecimalDisplay();
				}
			}
		});
		GridBagConstraints gbc_tbHexDecimal = new GridBagConstraints();
		gbc_tbHexDecimal.insets = new Insets(0, 0, 5, 5);
		gbc_tbHexDecimal.gridx = 0;
		gbc_tbHexDecimal.gridy = 0;
		frame.getContentPane().add(tbHexDecimal, gbc_tbHexDecimal);
		
		box = new HDformattedTextField();
		box.setMinimumSize(new Dimension(55, 20));
		box.addHDNumberValueChangedListener(adapter);
		box.setHorizontalAlignment(SwingConstants.RIGHT);
		box.setName("Box");
//		box = new HDformattedTextField();
//		box.setPreferredSize(new Dimension(400, 30));
//		box.setMinimumSize(new Dimension(55, 20));
//		box.setMaximumSize(new Dimension(0, 0));
//		box.setName("box");
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 5, 5);
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 1;
		frame.getContentPane().add(box, gbc_panel);
		
		

		JButton btnNewButton = new JButton("Min 0, Max 64K");
		btnNewButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
			clearLabels();
			box.setRange(0, 0xFFFF);
			}
		});
		
		hex64KSpinner = new Hex64KSpinner();
		hex64KSpinner.setMinimumSize(new Dimension(55, 20));
		hex64KSpinner.addChangeListener(adapter);
		hex64KSpinner.setName("64KSpinner");
//		hex64KSpinner.setBorder(UIManager.getBorder("Spinner.border"));
		hex64KSpinner.setSize(new Dimension(60, 30));
		hex64KSpinner.setMaximumSize(new Dimension(0, 0));
		hex64KSpinner.setPreferredSize(new Dimension(0, 0));
		GridBagConstraints gbc_hex64KSpinner = new GridBagConstraints();
		gbc_hex64KSpinner.insets = new Insets(0, 0, 5, 0);
		gbc_hex64KSpinner.gridx = 1;
		gbc_hex64KSpinner.gridy = 1;
		frame.getContentPane().add(hex64KSpinner, gbc_hex64KSpinner);
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton.gridx = 0;
		gbc_btnNewButton.gridy = 2;
		frame.getContentPane().add(btnNewButton, gbc_btnNewButton);

		btnNewButton_1 = new JButton("Value = 0x100 Quiet");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				clearLabels();
				box.setValueQuiet(0x100);
			}
		});
		
		hex64KSpinner_1 = new Hex64KSpinner();
		GridBagConstraints gbc_hex64KSpinner_1 = new GridBagConstraints();
		gbc_hex64KSpinner_1.insets = new Insets(0, 0, 5, 0);
		gbc_hex64KSpinner_1.gridx = 1;
		gbc_hex64KSpinner_1.gridy = 2;
		frame.getContentPane().add(hex64KSpinner_1, gbc_hex64KSpinner_1);
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_1.gridx = 0;
		gbc_btnNewButton_1.gridy = 3;
		frame.getContentPane().add(btnNewButton_1, gbc_btnNewButton_1);

		btnNewButton_2 = new JButton("Value = 0x256 Event");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearLabels();
				box.setValue(0x256);
			}
		});
		
		numberBox = new HDNumberBox();
		GridBagConstraints gbc_numberBox = new GridBagConstraints();
		gbc_numberBox.insets = new Insets(0, 0, 5, 0);
		gbc_numberBox.fill = GridBagConstraints.BOTH;
		gbc_numberBox.gridx = 1;
		gbc_numberBox.gridy = 3;
		frame.getContentPane().add(numberBox, gbc_numberBox);
		GridBagLayout gbl_numberBox = new GridBagLayout();
		gbl_numberBox.columnWidths = new int[]{0, 0, 0};
		gbl_numberBox.rowHeights = new int[]{0, 0};
		gbl_numberBox.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_numberBox.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		numberBox.setLayout(gbl_numberBox);
		GridBagConstraints gbc_btnNewButton_2 = new GridBagConstraints();
		gbc_btnNewButton_2.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_2.gridx = 0;
		gbc_btnNewButton_2.gridy = 4;
		frame.getContentPane().add(btnNewButton_2, gbc_btnNewButton_2);
		
		btnFmtx = new JButton("FMT %04X");
		btnFmtx.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearLabels();
				box.setHexDisplay("%04X");
			}
		});
		
		lblSource = new JLabel("Source");
		GridBagConstraints gbc_lblSource = new GridBagConstraints();
		gbc_lblSource.fill = GridBagConstraints.VERTICAL;
		gbc_lblSource.insets = new Insets(0, 0, 5, 5);
		gbc_lblSource.gridx = 0;
		gbc_lblSource.gridy = 5;
		frame.getContentPane().add(lblSource, gbc_lblSource);
		
		lblOldValue = new JLabel("Old Value");
		GridBagConstraints gbc_lblOldValue = new GridBagConstraints();
		gbc_lblOldValue.fill = GridBagConstraints.VERTICAL;
		gbc_lblOldValue.insets = new Insets(0, 0, 5, 5);
		gbc_lblOldValue.gridx = 0;
		gbc_lblOldValue.gridy = 6;
		frame.getContentPane().add(lblOldValue, gbc_lblOldValue);
		
		lblNewValue = new JLabel("New Value");
		GridBagConstraints gbc_lblNewValue = new GridBagConstraints();
		gbc_lblNewValue.fill = GridBagConstraints.VERTICAL;
		gbc_lblNewValue.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewValue.gridx = 0;
		gbc_lblNewValue.gridy = 7;
		frame.getContentPane().add(lblNewValue, gbc_lblNewValue);
		GridBagConstraints gbc_btnFmtx = new GridBagConstraints();
		gbc_btnFmtx.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnFmtx.insets = new Insets(0, 0, 5, 5);
		gbc_btnFmtx.gridx = 0;
		gbc_btnFmtx.gridy = 8;
		frame.getContentPane().add(btnFmtx, gbc_btnFmtx);
		
		seekPanel = new HDSeekPanel();
		seekPanel.setMinimumSize(new Dimension(250, 30));
		seekPanel.setMinValue(0);
		seekPanel.setMaxValue(256);
		GridBagConstraints gbc_seekPanel = new GridBagConstraints();
		gbc_seekPanel.anchor = GridBagConstraints.WEST;
		gbc_seekPanel.insets = new Insets(0, 0, 5, 5);
		gbc_seekPanel.fill = GridBagConstraints.VERTICAL;
		gbc_seekPanel.gridx = 0;
		gbc_seekPanel.gridy = 9;
		frame.getContentPane().add(seekPanel, gbc_seekPanel);
		GridBagLayout gbl_seekPanel = new GridBagLayout();
		gbl_seekPanel.columnWidths = new int[]{0};
		gbl_seekPanel.rowHeights = new int[]{0};
		gbl_seekPanel.columnWeights = new double[]{Double.MIN_VALUE};
		gbl_seekPanel.rowWeights = new double[]{Double.MIN_VALUE};
		seekPanel.setLayout(gbl_seekPanel);

	}// initialize

	class AdapterHDNumberBoxTest implements HDNumberValueChangeListener,ChangeListener {
		/* ------------ HDNumberValueChangeListener ----------------- */
		@Override
		public void valueChanged(HDNumberValueChangeEvent changeEvent) {

			String name = ((Component) changeEvent.getSource()).getName();
			System.out.printf("%s Decimal: %d %d, Hex: %X,%X%n",name,
					changeEvent.getOldValue(),changeEvent.getOldValue(),changeEvent.getOldValue(),changeEvent.getNewValue());
			lblSource.setText(name);
			
			lblOldValue.setText(String.format("%X",changeEvent.getOldValue()));
			lblNewValue.setText(String.format("%X",changeEvent.getNewValue()));
		}//valueChanged

		@Override
		public void stateChanged(ChangeEvent changeEvent) {
			changeEvent.getSource();
			String name = ((Hex64KSpinner) changeEvent.getSource()).getName();
			int value = (int) ((Hex64KSpinner) changeEvent.getSource()).getValue();
			System.out.printf("%s %d%n", name,value);
			
		}

	}// class AdapterHDNumberBoxTest

}// class TestHDNumberBox
