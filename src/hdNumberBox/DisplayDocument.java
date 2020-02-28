package hdNumberBox;
	/**
	 * Used to accept only Decimal or Hex characters 
	 */

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

// ---------------------------
class DisplayDocument extends PlainDocument {

	private static final long serialVersionUID = 1L;

	private String inputPattern;

	DisplayDocument( boolean decimalDisplay) {
		if (decimalDisplay == true) {
			displayDecimal();
		} else {
			displayHex();
		} // if
	}// Constructor

	public void displayDecimal() {
		inputPattern = "-??[0-9]*";

	}// displayDecimal

	public void displayHex() {
		inputPattern = "[A-F|a-f|0-9]+";
	}// displayHex

	public void insertString(int offSet, String string, AttributeSet attributeSet) throws BadLocationException {
		if (string == null) {
			return;
		} // if

		if (!string.matches(inputPattern)) {
			return;
		} // for

		super.insertString(offSet, string, attributeSet);
	}// insertString
}// class SeekDocument
	// ______________________________