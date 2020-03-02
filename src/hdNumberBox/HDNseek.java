package hdNumberBox;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JComponent;

public class HDNseek extends HDNbox {
	private static final long serialVersionUID = 1L;
	AdapterForSeek adapterForSeek = new AdapterForSeek();
	private int stepValue;

	// ----------------------------//----------------------------
	private void setStepValue(int step) {
		this.stepValue = Math.max(1, step); // must be 1 or greater
	}// setStepValue

	private void stepValue(int direction) {
		int changeAmount = stepValue * direction;

		long newValue = currentValue;
		newValue += changeAmount;

		if (newValue > rangeModel.getMaximum()) {
			setNewValue(rangeModel.getMaximum());
		} else if (newValue < rangeModel.getMinimum()) {
			setNewValue(rangeModel.getMinimum());
		} else {
			setNewValue((int) newValue);
		} // if
	}// stepValue

	// ----------------------------//----------------------------

	public HDNseek() {
		this(true);
	}// Constructor

	public HDNseek(boolean decimalDisplay) {
		super(decimalDisplay);
		initialize();
		appInit();
	}// Constructor

	private void appInit() {
		txtValueDisplay.addMouseListener(adapterForSeek);
		this.setStepValue(1);
	}// appInit

	private void initialize() {
		setPreferredSize(new Dimension(385, 30));

		btnFirst.setVisible(true);
		btnFirst.addActionListener(adapterForSeek);
		btnFirst.setName(FIRST);

		btnPrior.setVisible(true);
		btnPrior.addActionListener(adapterForSeek);
		btnPrior.setName(PRIOR);

		btnNext.setVisible(true);
		btnNext.addActionListener(adapterForSeek);
		btnNext.setName(NEXT);

		btnLast.setVisible(true);
		btnLast.addActionListener(adapterForSeek);
		btnLast.setName(LAST);

	}// initialize

	// ----------------------------//----------------------------
	class AdapterForSeek implements ActionListener, MouseListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			String name = ((JComponent) actionEvent.getSource()).getName();
			switch (name) {
			case FIRST:
				setNewValue( rangeModel.getMinimum());
				break;
			case LAST:
				setNewValue( rangeModel.getMaximum());
				break;
			case NEXT:
				stepValue(UP);
				break;
			case PRIOR:
				stepValue(DOWN);
				break;
			}// switch

		}// actionPerformed

		@Override
		public void mouseClicked(MouseEvent mouseEvent) {
			if (mouseEvent.getClickCount() > 1) {
				if (showDecimal) {
					setHexDisplay();
				} else {
					setDecimalDisplay();
				} // if inner
			} // if click count
		}// mouseClicked

		@Override
		public void mouseEntered(MouseEvent mouseEvent) {
			/* Not Used */
		}// mouseEntered

		@Override
		public void mouseExited(MouseEvent mouseEvent) {
			/* Not Used */
		}// mouseExited

		@Override
		public void mousePressed(MouseEvent mouseEvent) {
			/* Not Used */
		}// mousePressed

		@Override
		public void mouseReleased(MouseEvent arg0) {
			/* Not Used */
		}// mouseReleased

	}// class AdapterForPanel
		// ----------------------------//----------------------------

	private static final int UP = 1;
	private static final int DOWN = -1;

	private static final String FIRST = "First";
	private static final String LAST = "Last";
	private static final String NEXT = "Next";
	private static final String PRIOR = "Previous";

}// class HDNseek
