package facilitator;

import javax.swing.JApplet;

import calculator.CalculatorController;

public class AppletFacilitator extends JApplet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void init() {
		CalculatorController calculator = new CalculatorController(this.getContentPane());	
	}
}
