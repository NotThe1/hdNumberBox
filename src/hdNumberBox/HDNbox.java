package hdNumberBox;
/* @formatter:off */

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.DefaultBoundedRangeModel;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.event.EventListenerList;


/**
 * 
 * @author Frank Martyn. This is a JFormatted text box that only accepts either
 *         decimal or hexadecimal numbers. It supports a DefaultBoundedRangeModel
 *         to manage the values range of input data. The value input is limited
 *         byte the DefaultBoundedRangeModel. If value is greater than Max the
 *         value will be fixed to Max Value. Similarly if less than Min, the
 *         value will be fixed at Min Value.
 * 
 *         The range of the value is Integer.Min to Integer.MAX.
 * 
 *         This class supports a HDNumberValueChangeListener that will fire a
 *         HDNumberValueChangeEvent when the value changes
 * 
 *         The method mute(boolean state) disables/enables the
 *         fireSeekValueChanged() method, so values can be changed without
 *         triggering events
 *         
 *         2020-03-02 - Rewritten to accommodate seek panel. has 4 unused buttons
 *         that are placed in the class for the seek panel
 * 
 *         2018-03-01 - added setValueQuiet(int value); 2018-07-21 - Factored
 *         out (SeekDocument) DisplayDocument - Added capacity to set/reset
 *         display formats 2018-07-30 - added Apater_HDNumberBox. & selectAll on
 *         focus gained
 */
/* @formatter:on */
public class HDNbox extends JPanel{
	private static final long serialVersionUID = 1L;

	DefaultBoundedRangeModel rangeModel = new DefaultBoundedRangeModel();
	Adapter_HDNumberBox adapterHDNB = new Adapter_HDNumberBox();

	int currentValue, priorValue;
	EventListenerList hdNumberValueChangeListenerList;
	String decimalDisplayFormat;
	String hexDisplayFormat;
	boolean showDecimal = true;
	DisplayDocument displayDoc;
	boolean muteNumberChangeEvent;
	
	public void setHexDisplay() {
		setDecimalDisplay(false);
	}// setHexDisplay

	public void setHexDisplay(String format) {
		String trimmedFormat = format.toUpperCase().trim();
		Pattern hexPattern = Pattern.compile("\\%[0-9]*X");

		Matcher hexMatcher = hexPattern.matcher(trimmedFormat);
		if (hexMatcher.matches()) {
			hexDisplayFormat = trimmedFormat;
		} else {
			System.err.printf("[HDNumberBox.setHexDisplay] Invalid argument \"%s\"%n", format);
			// resetHexDisplay();
			hexDisplayFormat = FORMAT_HEX;
		} // if good

		setDecimalDisplay(false);
	}// setHexDisplay
	
	public void setDecimalDisplay() {
		setDecimalDisplay(true);
	}// setDecimalDisplay

	public void setDecimalDisplay(boolean displayDecimal) {
		String tipText = "";
		showDecimal = displayDecimal;
		if (displayDecimal) {
			displayDoc.displayDecimal();
			tipText = "Display is Decimal";
		} else {
			displayDoc.displayHex();
			tipText = "Display is Hex";
		} // if
		displayValue();
		txtValueDisplay.setToolTipText(tipText);
	}// setHexDisplay
	
	public void resetDisplayFormat() {
		hexDisplayFormat = FORMAT_HEX;
		decimalDisplayFormat = FORMAT_DECIMAL;
	}// restDisplayFormat
	
	private void displayValue() {
		String displayFormat = showDecimal ? decimalDisplayFormat : hexDisplayFormat;
		currentValue = (int) rangeModel.getValue();

		String stringValue = String.format(displayFormat, currentValue);
		txtValueDisplay.setText(stringValue);
		// txtValueDisplay.repaint();
	}// showValue

	public boolean isDecimalDisplay() {
		return showDecimal;
	}// isDecimalDisplay
	
	public int getValue() {
		return currentValue;
	}// getValue

	public void setValue(int newValue) {
		setNewValue(newValue);
		return;
	}// setValue

	public void setValueQuiet(int newValue) {
		muteNumberChangeEvent = true;
		setNewValue(newValue);
		muteNumberChangeEvent = false;
	}// setValueQuiet
	
	void setNewValue(int newValue) {
		newValue = Math.min(newValue, (int) rangeModel.getMaximum()); // upper
		newValue = Math.max(newValue, (int) rangeModel.getMinimum()); // lower

		priorValue = (int) rangeModel.getValue();
		currentValue = (newValue);
		rangeModel.setValue(currentValue);
		displayValue();
		if (muteNumberChangeEvent) {
			return;
		} // if
		if (priorValue != currentValue) {
			fireSeekValueChanged();
		} // if
	}// newValue



	public void setMaxValue(int newMaxValue) {
		rangeModel.setMaximum(newMaxValue);
	}// setMaxValue
	
	public int getMaxValue() {
		return rangeModel.getMaximum();
	}//getMaxValue

	public void setMinValue(int newMinValue) {
		rangeModel.setMinimum(newMinValue);
	}// setMinValue
	
	public int getMinValue() {
		return rangeModel.getMinimum();
	}//getMinValue

	

	public void setRange(int newMinValue, int newMaxValue) {
		setMinValue(newMinValue);
		setMaxValue(newMaxValue);
	}// setRange





	// ---------------------------	// ---------------------------
	
	public HDNbox() {
		this(Integer.MIN_VALUE, Integer.MAX_VALUE, 0, false);
	}//Constructor

	public HDNbox(boolean decimalDisplay) {
		this(Integer.MIN_VALUE, Integer.MAX_VALUE, 0, decimalDisplay);
	}// Constructor

	public HDNbox(int minValue, int maxValue, int initValue, boolean decimalDisplay) {
		this.rangeModel.setMinimum(minValue);
		this.rangeModel.setMaximum(maxValue);
		this.rangeModel.setValue(initValue);
		setBackground(UIManager.getColor("TextArea.background"));

		displayDoc = new DisplayDocument(true);

		initialize();
		appInit();

		if (decimalDisplay) {
			setDecimalDisplay();
		} else {
			setHexDisplay();
		} // if
	}// Constructor
	
	private void appInit() {
		resetDisplayFormat();

		currentValue = (int) rangeModel.getValue();
		txtValueDisplay.setDocument(displayDoc);
		hdNumberValueChangeListenerList = new EventListenerList();
		muteNumberChangeEvent = false;
		btnFirst.setVisible(false);
		btnPrior.setVisible(false);
		btnNext.setVisible(false);
		btnLast.setVisible(false);
	}// appInit
	
	private void initialize() {
		setPreferredSize(new Dimension(263, 23));
		this.setMinimumSize(new Dimension(75, 23));

		setBorder(UIManager.getBorder("TextField.border"));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, 0.0};
		gridBagLayout.columnWidths = new int[] {0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[] { 23, 0 };
		gridBagLayout.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
		setLayout(gridBagLayout);

		
		btnFirst = new JButton("<<");
		btnFirst.setVisible(false);
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnNewButton.gridx = 0;
		gbc_btnNewButton.gridy = 0;
		add(btnFirst, gbc_btnNewButton);
		
		btnPrior = new JButton("<");
		btnPrior.setVisible(false);
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnNewButton_1.gridx = 1;
		gbc_btnNewButton_1.gridy = 0;
		add(btnPrior, gbc_btnNewButton_1);
		
		txtValueDisplay = new JFormattedTextField();
		txtValueDisplay.setMaximumSize(new Dimension(0, 0));
		txtValueDisplay.setMinimumSize(new Dimension(75, 20));
		txtValueDisplay.setBackground(UIManager.getColor("TextArea.background"));
		txtValueDisplay.setFont(new Font("Courier New", Font.PLAIN, 13));
		txtValueDisplay.addFocusListener(adapterHDNB);
		txtValueDisplay.setHorizontalAlignment(SwingConstants.RIGHT);
		txtValueDisplay.setPreferredSize(new Dimension(75, 20));
		
		GridBagConstraints gbc_txtValueDisplay = new GridBagConstraints();
		gbc_txtValueDisplay.fill = GridBagConstraints.BOTH;
		gbc_txtValueDisplay.gridx = 2;
		gbc_txtValueDisplay.gridy = 0;
		add(txtValueDisplay, gbc_txtValueDisplay);
		
		btnNext = new JButton(">");
		btnNext.setVisible(false);
		GridBagConstraints gbc_btnNewButton_2 = new GridBagConstraints();
		gbc_btnNewButton_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnNewButton_2.gridx = 3;
		gbc_btnNewButton_2.gridy = 0;
		add(btnNext, gbc_btnNewButton_2);
		
		btnLast = new JButton();
		btnLast.setVisible(false);
		btnLast.setText(">>");
		GridBagConstraints gbc_btnNewButton_3 = new GridBagConstraints();
		gbc_btnNewButton_3.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnNewButton_3.gridx = 4;
		gbc_btnNewButton_3.gridy = 0;
		add(btnLast, gbc_btnNewButton_3);

	}// initialize	
	
	
	// ---------------------------	// ---------------------------
	public void addHDNumberValueChangedListener(HDNumberValueChangeListener seekValueChangeListener) {
		hdNumberValueChangeListenerList.add(HDNumberValueChangeListener.class, seekValueChangeListener);
	}// addSeekValueChangedListener

	public void removeHDNumberValueChangedListener(HDNumberValueChangeListener seekValueChangeListener) {
		hdNumberValueChangeListenerList.remove(HDNumberValueChangeListener.class, seekValueChangeListener);
	}// addSeekValueChangedListener

	protected void fireSeekValueChanged() {
		Object[] listeners = hdNumberValueChangeListenerList.getListenerList();
		// process
		HDNumberValueChangeEvent hdNumberValueChangeEvent = new HDNumberValueChangeEvent(this, priorValue,
				currentValue);

		for (int i = listeners.length - 2; i >= 0; i -= 2) {
			if (listeners[i] == HDNumberValueChangeListener.class) {
				((HDNumberValueChangeListener) listeners[i + 1]).valueChanged(hdNumberValueChangeEvent);
			} // if
		} // for

	}// fireSeekValueChanged

	// --------------------------------------------------------
	// --------------------------------------------------------

	private class Adapter_HDNumberBox implements FocusListener {

		@Override
		public void focusGained(FocusEvent focusEvent) {
			Object source = (JComponent) focusEvent.getSource();
			if (source instanceof JFormattedTextField) {
				JFormattedTextField textBox = (JFormattedTextField) source;
				textBox.selectAll();
			} // if
		}// focusGained

		@Override
		public void focusLost(FocusEvent focusEvent) {
			Object source = (JComponent) focusEvent.getSource();
			if (source instanceof JFormattedTextField) {
				JFormattedTextField textBox = (JFormattedTextField) source;
				if (textBox.getText().equals("")) {
					return;
				} // if null
				int radix = showDecimal ? 10 : 16;
				try {
					setNewValue(Integer.valueOf(textBox.getText(), radix));
				} catch (Exception e) {
					setNewValue(rangeModel.getValue());
				} // try
			} // if
		}// focusLost

	}// class Adapter_HDNumberBox

	private static final String FORMAT_HEX = "%X";
	private static final String FORMAT_DECIMAL = "%d";
	protected JButton btnFirst;
	protected JButton btnPrior;
	protected JButton btnNext;
	protected JButton btnLast;
	protected JFormattedTextField txtValueDisplay;

}//class HDNbox
