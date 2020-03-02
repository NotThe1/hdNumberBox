package hdNumberBox;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

public class HDseekPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	AdapterForPanel adapterMain = new AdapterForPanel();
	HDformattedTextField hdBox;
	
	private int stepValue;
	
	
	private void stepValue(int direction) {
		int changeAmount = stepValue* direction;
		long newValue = hdBox.getCurrentValue();
		newValue += changeAmount;
		if (newValue > hdBox.getMaxValue()) {
			hdBox.setValue(hdBox.getMaxValue());
		}else if (newValue < hdBox.getMinValue()) {
			hdBox.setValue(hdBox.getMinValue());
		}else {
			hdBox.setValue(newValue);
		}//if
	}//stepValue

	public HDseekPanel() {
		setBorder(UIManager.getBorder("Spinner.border"));
		setPreferredSize(new Dimension(265, 26));
		setMinimumSize(new Dimension(265, 26));
		initialize();
		appInit();
	}//Constructor
	
	private void appInit() {
		
	}//appInit
	
	private void initialize(){
		this.setMaximumSize(new Dimension(0, 0));
		FlowLayout flowLayout = new FlowLayout(FlowLayout.CENTER, 0, 0);
		flowLayout.setAlignOnBaseline(true);
		setLayout(flowLayout);
		
		JButton btnFirst = new JButton("<<");
		btnFirst.addActionListener(adapterMain);
		btnFirst.setName(FIRST);
		btnFirst.setHorizontalAlignment(SwingConstants.RIGHT);
		btnFirst.setFont(new Font("Courier New", Font.PLAIN, 13));
		btnFirst.setMaximumSize(new Dimension(0, 0));
		btnFirst.setMinimumSize(new Dimension(50, 20));
		btnFirst.setPreferredSize(new Dimension(50, 20));
		add(btnFirst);
		
		JButton btnPrior = new JButton("<");
		btnPrior.addActionListener(adapterMain);
		btnPrior.setName(PREVIOUS);
		btnPrior.setHorizontalAlignment(SwingConstants.RIGHT);
		btnPrior.setFont(new Font("Courier New", Font.PLAIN, 13));
		btnPrior.setMaximumSize(new Dimension(0, 0));
		btnPrior.setMinimumSize(new Dimension(50, 20));
		btnPrior.setPreferredSize(new Dimension(50, 20));
		add(btnPrior);
		
		HDformattedTextField dformattedTextField = new HDformattedTextField();
		dformattedTextField.setMaximumSize(new Dimension(0, 0));
		dformattedTextField.setMinimumSize(new Dimension(50, 20));
		dformattedTextField.setPreferredSize(new Dimension(50, 20));
		dformattedTextField.setHorizontalAlignment(SwingConstants.RIGHT);
		add(dformattedTextField);
		
		JButton btnNext = new JButton(">");
		btnNext.addActionListener(adapterMain);
		btnNext.setName(NEXT);
		btnNext.setHorizontalAlignment(SwingConstants.RIGHT);
		btnNext.setFont(new Font("Courier New", Font.PLAIN, 13));
		btnNext.setMaximumSize(new Dimension(0, 0));
		btnNext.setMinimumSize(new Dimension(50, 20));
		btnNext.setPreferredSize(new Dimension(50, 20));
		add(btnNext);
		
		JButton btnLast = new JButton(">>");
		btnLast.addActionListener(adapterMain);
		btnLast.setName(LAST);
		btnLast.setHorizontalAlignment(SwingConstants.RIGHT);
		btnLast.setFont(new Font("Courier New", Font.PLAIN, 13));
		btnLast.setMaximumSize(new Dimension(0, 0));
		btnLast.setMinimumSize(new Dimension(50, 20));
		btnLast.setPreferredSize(new Dimension(50, 20));
		add(btnLast);
	}//initialize
//---------------------------------------------------------------------------//
	class AdapterForPanel implements ActionListener, MouseListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {
//			String name = ((JComponent) actionEvent.getSource()).getName();
//			switch (name) {
//			case FIRST:
//				doFirst();
//				break;
//			case LAST:
//				doLast();
//				break;
//			case NEXT:
//				doNext();
//				break;
//			case PREVIOUS:
//				doPrevious();
//				break;
//			}// switch

		}// actionPerformed

		@Override
		public void mouseClicked(MouseEvent mouseEvent) {
//			if (mouseEvent.getClickCount() > 1) {
//				if (showDecimal) {
//					setHexDisplay();
//				} else {
//					setDecimalDisplay();
//				} // if inner
//			} // if click count
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
//---------------------------------------------------------------------------//
	
	private static final int UP = 1;
	private static final int DOWN = -1;

	private static final String FIRST = "First";
	private static final String LAST = "Last";
	private static final String NEXT = "Next";
	private static final String PREVIOUS = "Previous";
}//class HDseekPanelA
