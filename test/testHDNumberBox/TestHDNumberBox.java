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

import hdNumberBox.HDNumberBox;
import hdNumberBox.HDNumberValueChangeEvent;
import hdNumberBox.HDNumberValueChangeListener;

public class TestHDNumberBox {
	HDNumberBox box;
	private AdapterHDNumberBoxTest adapter = new AdapterHDNumberBoxTest();

	private JFrame frame;
	private JToggleButton tbHexDecimal;
	private JButton btnNewButton_1;
	private JButton btnNewButton_2;
	private JButton btnFmtx;
	private JLabel lblSource;
	private JLabel lblOldValue;
	private JLabel lblNewValue;

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
		box.setPreferredSize(new Dimension(50, 30));
		box.setValueQuiet(17);
		tbHexDecimal.setText("Decimal");
		box.setDecimalDisplay();
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
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 1.0, 1.0, 0.0, Double.MIN_VALUE };
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

		box = new HDNumberBox();
		box.addHDNumberValueChangedListener(adapter);
		box.setName("box");
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.anchor = GridBagConstraints.WEST;
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
		gbc_btnFmtx.insets = new Insets(0, 0, 0, 5);
		gbc_btnFmtx.gridx = 0;
		gbc_btnFmtx.gridy = 8;
		frame.getContentPane().add(btnFmtx, gbc_btnFmtx);

	}// initialize

	class AdapterHDNumberBoxTest implements HDNumberValueChangeListener {
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

	}// class AdapterHDNumberBoxTest

}// class TestHDNumberBox
