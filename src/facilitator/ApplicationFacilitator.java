package facilitator;

import javax.swing.JFrame;

import calculator.CalculatorController;

public class ApplicationFacilitator {

	public static void main(String[] args) {
		
		JFrame frame = new JFrame();
		CalculatorController calculator = new CalculatorController(frame.getContentPane());
		frame.setSize(210, 280);
		frame.setVisible(true);
	}

}
