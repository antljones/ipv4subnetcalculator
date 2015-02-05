package calculator;

import ipv4.Address;
import ipv4.Subnet;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JApplet;

public class CalculatorController extends JApplet{
	
	private static final long serialVersionUID = 1744931474029911081L;

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
					
					String address = calcView.getAddressField().getText();
					long addrVal = Address.toValue(address);
					long maskVal = Address.toValue(mask[0]);
					calcView.getNetworkAddressLabel().setText( "Network Address: " + Address.toDottedDecimal(Subnet.calcNetAddr(addrVal, maskVal) ) );
					calcView.getBroadcastAddressLabel().setText( "BroadcastAddress: " + Address.toDottedDecimal(Subnet.calcBroadcastAddr(addrVal, maskVal)));
					Subnet.calcBroadcastAddr(addrVal, maskVal);
				}
			}
		});
	}

}
