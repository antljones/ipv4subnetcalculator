package calculator;

import ipv4.Address;
import ipv4.Subnet;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Calculator extends JApplet{
	
	private static final long serialVersionUID = 1744931474029911080L;
	
	private Container ui;
	private JTextField addressField;
	private JComboBox<String> maskBox;
	private JLabel broadcastLabel;
	private JLabel networkAddressLabel;
	private JButton calcBtn;
	
	private static final String IPV4_FORMAT = "(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)";

	private Subnet subnet;
	
	private String[] maskEntries;	
	
	public void init() {
		ui = getContentPane();

		createMaskEntries();
		createUI();
	}
	
	private void createUI() {	
		JPanel subnetPanel = new JPanel();
		
		Dimension d = new Dimension( 40 , 20 );
		
		subnetPanel = new JPanel( new GridLayout( 0 , 1 ) );
		addressField = new JTextField( 20 );
		maskBox = new JComboBox<String>( maskEntries );
		broadcastLabel = new JLabel( "Broadcast Address:" );
		broadcastLabel.setSize( d );
		networkAddressLabel = new JLabel( "Network Address:" );
		networkAddressLabel.setSize( d );
		calcBtn = new JButton( "Calculate" );
		setCalcListener();
		
		subnetPanel.add( addressField );
		subnetPanel.add( maskBox );
		subnetPanel.add( networkAddressLabel );
		subnetPanel.add( broadcastLabel );
		subnetPanel.add( calcBtn );

		ui.add( subnetPanel );
		ui.setVisible( true );
	}
	
	private void createMaskEntries() {	
		maskEntries = new String[ 31 ];
		
		long tempAddress = (long) 0x00000000fffffffeL;
		Address tAddress = new Address();
		
		for ( int i = 0; i <=30; i++ ) {
			tempAddress = tempAddress << 1;
			tempAddress &= ( long ) 0x00000000ffffffffL;
			tAddress.setAddress( tempAddress );
			maskEntries[ i ] = tAddress.getAddress() + " /" + Integer.toString( 30 - i );
		}
	}
	
	private void setCalcListener() {
		calcBtn.addActionListener( new ActionListener() {

			public void actionPerformed( ActionEvent event ) {
				if ( addressField.getText().matches(IPV4_FORMAT) ) {			
					String[] mask = maskEntries[ maskBox.getSelectedIndex() ].split( " /" );
					
					subnet = new Subnet( addressField.getText(), mask[ 0 ] );
					broadcastLabel.setText( "BroadcastAddress: " + subnet.getBroadAddr().getAddress() );
					networkAddressLabel.setText( "Network Address: " + subnet.getNetAddr().getAddress() );
				}
			}
		});
	}

}
