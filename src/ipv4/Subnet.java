package ipv4;

public class Subnet {
	
	String mask;
	String broadcastAddress;
	String networkAddress;

	public Subnet( String address , String mask ) {
		this.mask = mask;
		
		networkAddress = Address.toDottedDecimal( calcNetAddr( Address.toValue(address), Address.toValue(mask) ) );
		broadcastAddress = calcBroadcastAddr( networkAddress, mask );
	}
	
	public static long calcNetAddr( long address , long mask ) {
		long l = address & mask;
		
		l &= 0xffffffff;
		
		return l;
	}

	private String calcBroadcastAddr( String networkAddress, String mask ) {
		String tempAddress[] = networkAddress.split( "\\." );
		
		String tempMask[] = mask.split( "\\." );
		
		String broadcast[] = new String[ 4 ];
		
		for ( int i = 0; i < tempAddress.length; i++ ) {
			if ( Integer.parseInt( tempMask[ i ] ) == 255 ) {
				broadcast[ i ] = tempAddress[ i ];
			}else{
				broadcast[ i ] = "" + ( Integer.parseInt( tempAddress[ i ] ) + ( 255 - Integer.parseInt( tempMask[ i ] ) ) );
			}
		}
		
		String broadcastAddress = broadcast[ 0 ] + ".";
		broadcastAddress += broadcast[ 1 ] + ".";
		broadcastAddress += broadcast[ 2 ] + ".";
		broadcastAddress += broadcast[ 3 ];
		
		return broadcastAddress;
	}
	
	public String getMask() {
		return mask;
	}
	
	public String getBroadcastAddr() {
		return broadcastAddress;
	}
	
	public String getNetworkAddr() {
		return networkAddress;
	}
}