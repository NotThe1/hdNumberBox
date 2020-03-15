package hdNumberBox;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
/*
 * use 277X23 for preferred size in users form
 */
public class HDNseek extends HDNbox {
	Icon firstIcon = new ImageIcon(HDNseek.class.getResource("/first16.gif"));
	Icon priorIcon = new ImageIcon(HDNseek.class.getResource("/prior16.gif"));
	Icon nextIcon = new ImageIcon(HDNseek.class.getResource("/next16.gif"));
	Icon lastIcon = new ImageIcon(HDNseek.class.getResource("/last16.gif"));

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
		setPreferredSize(new Dimension(400, 30));
		btnFirst.setText("");
		btnFirst.setIcon(firstIcon);
		btnFirst.setVisible(true);
		btnFirst.addActionListener(adapterForSeek);
		btnFirst.setName(FIRST);

		btnPrior.setVisible(true);
		btnPrior.setText("");
		btnPrior.setIcon(priorIcon);
		btnPrior.addActionListener(adapterForSeek);
		btnPrior.setName(PRIOR);

		btnNext.setVisible(true);
		btnNext.setText("");
		btnNext.setIcon(nextIcon);
		btnNext.addActionListener(adapterForSeek);
		btnNext.setName(NEXT);

		btnLast.setVisible(true);
		btnLast.setText("");
		btnLast.setIcon(lastIcon);
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
