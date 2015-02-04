package calculator;

import ipv4.Address;
import ipv4.Subnet;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JApplet;

public class CalculatorController extends JApplet{
	
	private static final long serialVersionUID = 1744931474029911081L;

	private Subnet subnet;
	private String[] maskEntries;
	private CalculatorView calcView;
	
	
	public void init() {
		this.calcView = new CalculatorView(this.getContentPane(),createMaskEntries());
		setCalcListener();
	}
	
	private String[] createMaskEntries() {	
		maskEntries = new String[ 31 ];
		
		long tempAddress = (long) 0x00000000fffffffeL;
		
		for ( int i = 0; i <=30; i++ ) {
			tempAddress = tempAddress << 1;
			tempAddress &= ( long ) 0x00000000ffffffffL;
			maskEntries[ i ] = Address.toDottedDecimal(tempAddress) + " /" + Integer.toString( 30 - i );
		}
		
		return maskEntries;
	}
	
	private void setCalcListener() {
		calcView.getCalcBtn().addActionListener( new ActionListener() {

			public void actionPerformed( ActionEvent event ) {
				if ( Address.isValid(calcView.getAddressField().getText()) ) {			
					String[] mask = maskEntries[ calcView.getMaskBox().getSelectedIndex() ].split( " /" );
					
					subnet = new Subnet( calcView.getAddressField().getText(), mask[ 0 ] );
					calcView.getBroadcastAddressLabel().setText( "BroadcastAddress: " + subnet.getBroadcastAddr() );
					calcView.getNetworkAddressLabel().setText( "Network Address: " + subnet.getNetworkAddr() );
				}
			}
		});
	}

}
