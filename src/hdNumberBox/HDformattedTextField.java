package hdNumberBox;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.DefaultBoundedRangeModel;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.SwingConstants;
import javax.swing.event.EventListenerList;

public class HDformattedTextField extends JFormattedTextField {
	private static final long serialVersionUID = 1L;

	DefaultBoundedRangeModel rangeModel = new DefaultBoundedRangeModel();
	AdapterHDformattedTextField adapterPrimary = new AdapterHDformattedTextField();
	EventListenerList valueChangeListeneList = new EventListenerList();
	DisplayDocument displayDoc = new DisplayDocument(true);
	String decimalDisplayFormat = FORMAT_DECIMAL;
	String hexDisplayFormat = FORMAT_HEX;

	int currentValue, priorValue;
	boolean showDecimal = true;
	boolean muteNumberChangeEvent = false;

	public HDformattedTextField() {
		this(Integer.MIN_VALUE, Integer.MAX_VALUE, 0, false);
	}// Constructor

	public HDformattedTextField(boolean decimalDisplay) {
		this(Integer.MIN_VALUE, Integer.MAX_VALUE, 0, decimalDisplay);
	}// Constructor

	public HDformattedTextField(int minValue, int maxValue, int initValue, boolean decimalDisplay) {
		rangeModel.setMinimum(minValue);
		rangeModel.setMaximum(maxValue);
		rangeModel.setValue(initValue);
		showDecimal = decimalDisplay;

		initialize();
		appInit();
	}// Constructor

	private void appInit() {
		setDecimalDisplay(showDecimal);
		resetDisplayFormat();
		currentValue = rangeModel.getValue();
		muteNumberChangeEvent = false;
	}// appInit

	private void initialize() {
		this.setHorizontalAlignment(SwingConstants.RIGHT);
		this.setFont(new Font("Courier New", Font.PLAIN, 13));
		this.addFocusListener(adapterPrimary);
		this.setMaximumSize(new Dimension(0, 0));
		this.setMinimumSize(new Dimension(55, 20));
		this.setPreferredSize(new Dimension(55, 20));
		this.setDocument(displayDoc);
	}// initialize

	////////////////////////////////////////////////////////////////////////

	public int getCurrentValue() {
		return currentValue;
	}// getCurrentValue

	public void setValue(int newValue) {
		setNewValue(newValue);
	}// setValue

	public void setValueQuiet(int newValue) {
		muteNumberChangeEvent = true;
		setValue(newValue);
		muteNumberChangeEvent = false;
	}// setValueQuiet

	public void setMaxValue(int newMaxValue) {
		rangeModel.setMaximum(newMaxValue);
	}// setMaxValue
	
	public int getMaxValue() {
		return rangeModel.getMaximum();
	}// getMaxValue

	public void setMinValue(int newMinValue) {
		rangeModel.setMinimum(newMinValue);
	}// setMinValue

	public int getMinValue() {
		return rangeModel.getMinimum();
	}// getMaxValue

	public void setRange(int newMinValue, int newMaxValue) {
		setMinValue(newMinValue);
		setMaxValue(newMaxValue);
	}// setRange

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
		} // if display decimal
		displayValue();
		this.setToolTipText(tipText);
	}// setDecimalDisplay

	public void setHexDisplay() {
		setDecimalDisplay(false);
	}// setHexDisplay

	public void setHexDisplay(String format) {
		String trimmedFormat = format.trim().toUpperCase();
		Pattern hexPattern = Pattern.compile("\\%[0-9]*X");

		Matcher hexMatcher = hexPattern.matcher(trimmedFormat);
		if (hexMatcher.matches()) {
			hexDisplayFormat = trimmedFormat;
		} else {
			System.err.printf("[HDNumberBox.setHexDisplay] Invalid argument \"%s\"%n", format);
			hexDisplayFormat = FORMAT_HEX;
		} // if good format
		setDecimalDisplay(false);
	}// setHexDisplay

	public void resetDisplayFormat() {
		hexDisplayFormat = FORMAT_HEX;
		decimalDisplayFormat = FORMAT_DECIMAL;
	}// resetDisplayFormat

	public boolean isDecimalDisplay() {
		return showDecimal;
	}// isDecimalDisplay

	private void setNewValue(int newValue) {
		newValue = Math.min(newValue, (int) rangeModel.getMaximum()); // upper
		newValue = Math.max(newValue, (int) rangeModel.getMinimum()); // lower

		priorValue = rangeModel.getValue();
		currentValue = newValue;
		rangeModel.setValue(newValue);
		displayValue();
		if (muteNumberChangeEvent) {
			return;
		} // if mute

		if (priorValue != currentValue) {
			fireSeekValueChanged();
		} // if new
	}// setNewValue

	private void displayValue() {
		String displayFormat = showDecimal ? decimalDisplayFormat : hexDisplayFormat;
		// displayFormat = showDecimal? "%d":"%04X";

		currentValue = rangeModel.getValue();
		String stringValue = String.format(displayFormat, currentValue);
		// String stringValue = String.format(displayFormat, currentValue);
		this.setText(stringValue);
	}// displayValue

	////////////////////////////////////////////////////////////////////////
	// ---------------------------
	public void addHDNumberValueChangedListener(HDNumberValueChangeListener seekValueChangeListener) {
		valueChangeListeneList.add(HDNumberValueChangeListener.class, seekValueChangeListener);
	}// addSeekValueChangedListener

	public void removeHDNumberValueChangedListener(HDNumberValueChangeListener seekValueChangeListener) {
		valueChangeListeneList.remove(HDNumberValueChangeListener.class, seekValueChangeListener);
	}// addSeekValueChangedListener

	protected void fireSeekValueChanged() {
		Object[] listeners = valueChangeListeneList.getListenerList();
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

	////////////////////////////////////////////////////////////////////////
	class AdapterHDformattedTextField implements FocusListener {

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

	}// class AdapterHDformattedTextField

	private static final String FORMAT_HEX = "%X";
	private static final String FORMAT_DECIMAL = "%d";

}// class HDformattedTextField
